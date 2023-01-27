insert into announcement (teamidteam, name, location, category, dateevent, prize, gameidgame)
values (1, 'EDC', 'Ciudad de Mexico', 'Mayor', '2023/01/13', 'Sin premio', 1),
       (1, 'EDC', 'Ciudad de Mexico', 'Mayor', '10/01/2024', 'Sin premio', 2),
       (2, 'Cerva-Fest', 'Guanajuato Capital', 'Intermedia', '2022/08/13', '10,000', 1),
       (2, 'Guelagetza', 'Oaxaca Capital', 'Intermedia', '2022/12/10', '5,000', 1);

insert into "user"
values ('tes@mail.com', 'password', true),
       ('test2@mail.com', 'password', false),
       ('juan@mail.com', 'password', true);

insert into player (useremail, name, middlelastname, lastname)
values ('tes@mail.com', 'Javier', 'Az', 'Vg'),
    ('test2@mail.com', 'Mauricio', 'Diaz', 'Diaz');

insert into team (useremail, name, leader)
values ('test2@mail.com', 'Bombones del bajio', 'Mauricio'),
       ('tes@mail.com', 'test', 'Javier');

insert into game (title, "desc")
values ('Halo', 'Ciencia ficcion'),
       ('Free Fire', 'RPG para jugadores moviles y PC');

select *
from announcement;
select *
from player;
select *
from game;
select *
from team;

select * from "user";
select * from player_team;

/*
 *
 * 				JUEGOS
 *
 */

select *
FROM game;

alter table game
    alter column "desc" type varchar(255);

insert into game (title, "desc")
values ('League of Legends',
        'Juego de estrategia por equipos en el que dos equipos conformados por cinco poderosos campeones se enfrentan para destruir la base del otro.');

insert into game (title, "desc")
values ('Fortnite',
        'Juego multiplataforma gratuito perteneciente al género battle royale desarrollado y publicado por Epic Games.');

insert into game (title, "desc")
values ('Overwatch',
        'Juego de disparos en primera persona multijugador, desarrollado por Blizzard Entertainment.');

insert into game (title, "desc")
values ('Valorant',
        'Hero shooter en primera persona multijugador gratuito desarrollado y publicado por Riot Games.');

insert into game (title, "desc")
values ('Tom Clancy''s Rainbow Six: Siege',
        'Juego de disparos táctico en línea desarrollado por Ubisoft Montreal y distribuidor por Ubisoft.');

insert into game (title, "desc")
values ('Apex Legends',
        'juego gratuito perteneciente a los géneros battle royale y hero shooter en primera persona, desarrollado por Respawn Entertainment y publicado por Electronic Arts.');

insert into game (title, "desc")
values ('Call of Duty: Warzone',
        'Juego de disparos en primera persona, perteneciente al género Battle royale gratuito.');


/*
 *
 * 				User
 *
 */

select *
from "user";

select *
from player_team;

insert into player_team(playeridplayer, teamidteam)
values (19, 1)

select *
from player;

delete
from "user"
where email = 'tes@mail.com';

select *
from team;


/*

Modificación Constraint

*/

alter table player
    drop constraint fkplayer682531,
    add constraint fkplayer682531 foreign key (useremail) references "user" (email) on delete cascade;