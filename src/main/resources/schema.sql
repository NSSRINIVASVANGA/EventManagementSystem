CREATE TABLE if not exists event(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    date TEXT
);

CREATE TABLE if not exists sponsor(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    industry TEXT
);

CREATE TABLE if not exists event_sponsor(
    eventId INTEGER,
    sponsorId INTEGER,
    PRIMARY KEY (eventId,sponsorId),
    FOREIGN KEY (eventId) REFERENCES sponsor(id),
    FOREIGN KEY (sponsorId) REFERENCES event(id)
);