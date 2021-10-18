(defproject acds_net "0.1.0"
  :description "ACDS: personal trackers"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cprop "0.1.13"]
                 [com.oracle.jdbc/ojdbc8 "12.2.0.1"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [hikari-cp "2.6.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [org.slf4j/slf4j-api "1.7.25"]
                 [com.fzakaria/slf4j-timbre "0.3.12"]
                 [com.taoensso/encore "2.105.0"]
                 [tcp-server "0.1.0"]]

  :repositories [["XWiki External Repository" "https://maven.xwiki.org/externals/"]]

  :pedantic? false

  :jvm-opts ["-Dconf=config.edn"]

  :global-vars {*warn-on-reflection* true}

  :plugins [[lein-ancient "0.6.15"]]

  :min-lein-version "2.8.1"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} [:target-path :compile-path]

  :main acds-trackers.core

  :profiles {:dev {:prep-tasks ["clean"]
                   :dependencies [[proto-repl "0.3.1"]]}
             :uberjar {:uberjar-name "acds-trackers.jar"
                       :source-paths ^:replace ["src/clj"]
                       :prep-tasks ["compile"]
                       :hooks []
                       :omit-source true
                       :aot :all}})
