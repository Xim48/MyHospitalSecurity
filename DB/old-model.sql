CREATE TABLE announcement
(
    idEvent  SERIAL       NOT NULL,
    idTeam   int4         NOT NULL,
    name     varchar(200) NOT NULL,
    location varchar(200) NOT NULL,
    category varchar(200) NOT NULL,
    "date"   timestamp(8) NOT NULL,
    prize    varchar(200) NOT NULL,
    PRIMARY KEY (idEvent)
);

CREATE TABLE announcement_player
(
    idEvent  int4 NOT NULL,
    idPlayer int4 NOT NULL,
    status   int4 NOT NULL,
    PRIMARY KEY (idEvent, idPlayer)
);

CREATE TABLE catalogue
(
    type  varchar(5)   NOT NULL,
    value varchar(200) NOT NULL,
    PRIMARY KEY (type)
);

CREATE TABLE game
(
    idGame   SERIAL       NOT NULL,
    idPlayer int4         NOT NULL,
    title    varchar(200) NOT NULL,
    level    varchar(200) NOT NULL,
    PRIMARY KEY (idGame)
);

CREATE TABLE player
(
    idPlayer       SERIAL       NOT NULL,
    useremail      varchar(200) NOT NULL,
    idTeam         int4,
    name           varchar(200) NOT NULL,
    middleLastName varchar(200),
    lastName       varchar(200) NOT NULL,
    role           varchar(100) NOT NULL,
    PRIMARY KEY (idPlayer)
);

CREATE TABLE team
(
    idTeam    SERIAL       NOT NULL,
    useremail varchar(200) NOT NULL,
    name      varchar(200) NOT NULL UNIQUE,
    leader    varchar(200) NOT NULL,
    game      varchar(200) NOT NULL,
    PRIMARY KEY (idTeam)
);

CREATE TABLE "user"
(
    email    varchar(200) NOT NULL,
    password varchar(200) NOT NULL,
    PRIMARY KEY (email)
);

ALTER TABLE player
    ADD CONSTRAINT FKplayer867850 FOREIGN KEY (idTeam) REFERENCES team (idTeam);
ALTER TABLE player
    ADD CONSTRAINT FKplayer682531 FOREIGN KEY (useremail) REFERENCES "user" (email);
ALTER TABLE team
    ADD CONSTRAINT FKteam627253 FOREIGN KEY (useremail) REFERENCES "user" (email) ON DELETE CASCADE;
ALTER TABLE announcement
    ADD CONSTRAINT FKannounceme403751 FOREIGN KEY (idTeam) REFERENCES team (idTeam) ON DELETE CASCADE;
ALTER TABLE game
    ADD CONSTRAINT FKgame637300 FOREIGN KEY (idPlayer) REFERENCES player (idPlayer);
ALTER TABLE announcement_player
    ADD CONSTRAINT FKannounceme258725 FOREIGN KEY (idEvent) REFERENCES announcement (idEvent);
ALTER TABLE announcement_player
    ADD CONSTRAINT FKannounceme291841 FOREIGN KEY (idPlayer) REFERENCES player (idPlayer);

-- this was for be able to delete on cascade
alter table team
    drop constraint FKteam627253;
alter table announcement
    drop constraint fkannounceme403751;

-- Documentation

ALTER TABLE game
    ALTER COLUMN idplayer DROP NOT NULL;
