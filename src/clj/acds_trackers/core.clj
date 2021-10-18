(ns acds-trackers.core
  (:require
    [clojure.java.io    :as io]
    [clojure.spec.alpha :as s]
    [clojure.string     :as str]
    [net.tcp.server     :as nts]
    [taoensso.timbre    :as timbre]
    [cprop.core         :as cprop]
    [hikari-cp.core     :as hikari]
    [clojure.java.jdbc  :as jdbc]))

(s/def ::msg (s/coll-of string? :kind vector? :count 22))

(def config (cprop/load-config))

(def ds-acds (delay (try
                      (hikari/make-datasource (:acds config))
                      (catch Exception _ nil))))

(defn insert-event
  [imei azimuth altitude longitude latitude gps-time]
  (jdbc/with-db-transaction [conn {:datasource @ds-acds}]
    (jdbc/execute! conn
                   ["INSERT INTO trc_events
                      (tracker_imei,
                       azimuth,
                       altitude,
                       longitude,
                       latitude,
                       gps_time,
                       sys_time)
                     VALUES (?, ?, ?, ?, ?,
                             to_date(?, 'dd.mm.yy hh24:mi:ss'),
                             sysdate)"
                    imei, azimuth, altitude, longitude, latitude, gps-time])))

(defn format-date
  [s]
  (letfn [(f [[yy mm dd hh24 mi ss]]
            (str dd "." mm "." yy " " hh24 ":" mi ":" ss))]
    (f (map #(apply str %) (rest (partition 2 (seq s)))))))

(defn handle-msg
  [[_ _ imei _ _ _ _ _ _ azimuth altitude longitude latitude gps-time
    _ _ _ _ _ _ _ _]]
  (insert-event (biginteger imei)
                (Integer/parseInt azimuth)
                (Double/parseDouble altitude)
                (Double/parseDouble longitude)
                (Double/parseDouble latitude)
                (format-date gps-time)))

(defn handler
  [^java.io.BufferedReader reader _]
  (let [raw-msg (try (.readLine reader) (catch Exception e "e"))
        msg (str/split raw-msg #",")
        msg-type (first msg)]
    (when (and (s/valid? ::msg msg)
               (or (= msg-type "+RESP:GTFRI")
                   (= msg-type "+BUFF:GTFRI")))
      (future (handle-msg msg)))))

(def server
  (nts/tcp-server
    :port    5001
    :host    "192.168.1.116"
    :handler (nts/wrap-io handler)))

(comment
  (nts/start server))

(comment
  (nts/stop server))
