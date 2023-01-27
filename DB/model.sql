------- MOD -----
CREATE TABLE announcement
(
    idEvent    SERIAL       NOT NULL,
    teamidTeam int4         NOT NULL,
    name       varchar(200) NOT NULL,
    location   varchar(200) NOT NULL,
    category   varchar(200) NOT NULL,
    dateEvent  timestamp(8) NOT NULL,
    prize      varchar(200) NOT NULL,
    gameidGame int4         NOT NULL,
    PRIMARY KEY (idEvent)
);
CREATE TABLE game
(
    idGame SERIAL       NOT NULL,
    title  varchar(200) NOT NULL,
    "desc" varchar(100) NOT NULL,
    PRIMARY KEY (idGame)
);
CREATE TABLE player
(
    idPlayer       SERIAL       NOT NULL,
    useremail      varchar(200) NOT NULL UNIQUE,
    name           varchar(200) NOT NULL,
    middleLastName varchar(200),
    lastName       varchar(200) NOT NULL,
    PRIMARY KEY (idPlayer)
);
CREATE TABLE player_game
(
    playeridPlayer int4        NOT NULL,
    gameidGame     int4        NOT NULL,
    level          varchar(30) NOT NULL,
    PRIMARY KEY (playeridPlayer,
                 gameidGame)
);
CREATE TABLE player_team
(
    playeridPlayer int4 NOT NULL,
    teamidTeam     int4 NOT NULL,
    PRIMARY KEY (playeridPlayer,
                 teamidTeam)
);
CREATE TABLE team
(
    idTeam    SERIAL       NOT NULL,
    useremail varchar(200) NOT NULL UNIQUE,
    name      varchar(200) NOT NULL UNIQUE,
    leader    varchar(200) NOT NULL,
    PRIMARY KEY (idTeam)
);
CREATE TABLE "user"
(
    email    varchar(200) NOT NULL,
    password varchar(200) NOT NULL,
    type     bool         NOT NULL,
    PRIMARY KEY (email)
);
ALTER TABLE player
    ADD CONSTRAINT FKplayer682531 FOREIGN KEY (useremail) REFERENCES "user" (email);

/*
delete on cascade para  equipos y eventos
*/
ALTER TABLE team
    drop constraint FKteam627253,
    ADD CONSTRAINT FKteam627253 FOREIGN KEY (useremail) REFERENCES "user" (email) on delete cascade;

ALTER TABLE player_team
    DROP CONSTRAINT FKplayer_tea186945,
    ADD CONSTRAINT FKplayer_tea186945 FOREIGN KEY (playeridPlayer) REFERENCES player (idPlayer) ON DELETE CASCADE;

ALTER TABLE player_team
    DROP CONSTRAINT FKplayer_tea731973,
    ADD CONSTRAINT FKplayer_tea731973 FOREIGN KEY (teamidTeam) REFERENCES team (idTeam) ON DELETE CASCADE;

ALTER TABLE player_game
    ADD CONSTRAINT FKplayer_gam577708 FOREIGN KEY (playeridPlayer) REFERENCES player (idPlayer);
ALTER TABLE player_game
    ADD CONSTRAINT FKplayer_gam58605 FOREIGN KEY (gameidGame) REFERENCES game (idGame);

/*
 delete on cascade para evento y equipo
 */
ALTER TABLE announcement
    drop constraint fkannounceme73290,
    ADD CONSTRAINT FKannounceme73290 FOREIGN KEY (teamidTeam) REFERENCES team (idTeam) on delete cascade ;
ALTER TABLE announcement
    ADD CONSTRAINT FKannounceme108052 FOREIGN KEY (gameidGame) REFERENCES game (idGame);
