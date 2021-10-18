-- TRACKERS (abbr: trc_*)------------------------------------------------------

CREATE TABLE trc_events (
  event_id     NUMBER GENERATED ALWAYS AS IDENTITY,
  tracker_imei NUMBER(15,0) NOT NULL,
  azimuth      NUMBER(3,0)  NOT NULL,
  altitude     NUMBER(7,1)  NOT NULL,
  longitude    NUMBER(9,6)  NOT NULL,
  latitude     NUMBER(9,6)  NOT NULL,
  gps_time     DATE         NOT NULL,
  sys_time     DATE         NOT NULL,
  CONSTRAINT trc_events_pk
    PRIMARY KEY (event_id)
);
/
