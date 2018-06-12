-- DROP TABLE IF EXISTS T_FOOTBALL_FINAL_SCORE;
-- CREATE TABLE T_FOOTBALL_FINAL_SCORE (
--   ID_GAME int NULL,
--   ID_COMMAND_ONE int NULL,
--   ID_COMMAND_TWO int NULL
-- );
--
-- DROP TABLE IF EXISTS T_FOOTBALL_GAME;
-- CREATE TABLE T_FOOTBALL_GAME(
--   ID_GAME int IDENTITY(1,1) NOT NULL,
--   GAME_DT date NULL,
--   GAME varchar(150) NULL
-- );
--
-- DROP TABLE IF EXISTS T_E_FOOTTBAL_PLAYER;
-- CREATE TABLE T_E_FOOTTBAL_PLAYER(
--   ID_PLAYER int IDENTITY(1,1) NOT NULL,
--   FIO varchar(100) NULL,
--   USERNAME varchar(50) NULL,
--   DOUBLE_FIO varchar(50) NULL,
--   DOUBLE_USERNAME varchar(50) NULL,
--   WINNER varchar(50) NULL
-- );
--
-- DROP TABLE IF EXISTS T_FOOTBALL_USER_SCORE;
-- CREATE TABLE T_FOOTBALL_USER_SCORE(
--   ID_PLAYER int NULL,
--   ID_GAME int NULL,
--   SCORE_COMMAND_ONE int NULL,
--   SCORE_COMMAND_TWO int NULL
-- );
--
-- --наполнение игроками
-- INSERT INTO T_E_FOOTBALL_PLAYERS(
--    FIO varchar(100)
--   ,USERNAME varchar(50)
--   ,DOUBLE_FIO varchar(50)
--   ,DOUBLE_USERNAME varchar(50)
--   ,WINNER varchar(50)
-- ) VALUES(
--     'Ежов Денис Анатольевич'
--     ,'ezhov_da'
--     ,'ХЗ ЧТО ЭТО'
--     ,'ХЗ ЧТО ЭТО'
--     ,''
-- );

DROP TABLE IF EXISTS T_E_football_itogschet;
CREATE TABLE T_E_football_itogschet (
  idmatcha INT NULL,
  strana1  INT NULL,
  strana2  INT NULL
);

DROP TABLE IF EXISTS T_E_football_respisanie;
CREATE TABLE T_E_football_respisanie (
  id    INT IDENTITY (1, 1) NOT NULL,
  data  DATE                NULL,
  gamer VARCHAR(150)        NULL
);

DROP TABLE IF EXISTS T_E_football_users;
CREATE TABLE T_E_football_users (
  fio            VARCHAR(100) NULL,
  username       VARCHAR(50)  NULL,
  password       VARCHAR(50)  NULL,
  doublefio      VARCHAR(50)  NULL,
  doubleusername VARCHAR(50)  NULL,
  chempion       VARCHAR(50)  NULL
);

DROP TABLE IF EXISTS T_E_football_userSchet;
CREATE TABLE T_E_football_userSchet (
  username    VARCHAR(50) NULL,
  idmatcha    INT         NULL,
  userstrana1 INT         NULL,
  userstrana2 INT         NULL
);


INSERT INTO T_E_football_users (
  fio
  , username
  , password
  , doublefio
  , doubleusername
  , chempion
) VALUES (
  'Ежов Денис Анатольевич'
  , 'ezhov_da'
  , '1'
  , 'ХЗ что здаесь'
  , 'И здесь'
  , 'Германия'
);

-- список игр для подгрузки в выпадающий список
SELECT
  DISTINCT
  t0.id,
  t0.data,
  t0.gamer,
  t1.*
FROM
  (
    SELECT
      t0.id,
      t0.data,
      t0.gamer,
      t1.doubleusername
    FROM T_E_football_respisanie t0, T_E_football_users t1
  ) t0
  LEFT JOIN T_E_football_userSchet t1 ON
                                        t1.idmatcha = t0.id
                                        AND
                                        t1.username = t0.doubleusername
WHERE
  data BETWEEN CURRENT_DATE + 1
  AND
  CURRENT_DATE + 40
  AND
  t0.doubleusername = 'ХЗ что здесь должно быть ((('
  AND t1.idmatcha IS NULL
ORDER BY data;

-- пользователь?
SELECT
  t0.id,
  data,
  t0.gamer,
  t1.*
FROM T_E_football_respisanie t0
  LEFT JOIN T_E_football_itogschet t1 ON t1.idmatcha = t0.id
WHERE t1.idmatcha IS NULL
ORDER BY t0.data;

//это пользователь, сразу его выбираем, не даем выбирать за других
SELECT
  fio,
  username,
  doublefio,
  doubleusername,
  chempion
FROM T_E_football_users
WHERE username = '"+StaticPublic.getUserName()+"';

--====================================
-- РЕЗУЛЬТАТЫ ПО ИГРАМ
--====================================
SELECT
  t1.data                                                                           AS "Дата игры",
  t1.gamer                                                                          AS "Играющие команды",
  t4.doublefio                                                                      AS "Игрок",
  cast(t2.strana1 AS VARCHAR(10)) + '-' + cast(t2.strana2 AS VARCHAR(10))           AS "Итоговый счет",
  cast(t3.userstrana1 AS VARCHAR(10)) + ' - ' + cast(t3.userstrana2 AS VARCHAR(10)) AS "Прогноз игрока",
  CASE
  WHEN t2.strana1 = t3.userstrana1 AND t2.strana2 = t3.userstrana2
    THEN 3
  WHEN
    abs(t2.strana1 - t2.strana2) = abs(t3.userstrana1 - t3.userstrana2)
    AND
    (
      (t2.strana1 >= t2.strana2 AND t3.userstrana1 >= t3.userstrana2)
      OR
      (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2)
    )
    THEN 2
  WHEN
    (t2.strana1 > t2.strana2 AND t3.userstrana1 > t3.userstrana2)
    OR
    (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2)
    THEN 1
  ELSE 0 END                                                                           "Очки"
FROM T_E_football_respisanie t1
  JOIN T_E_football_itogschet t2 ON t1.id = t2.idmatcha
  JOIN T_E_football_userSchet t3 ON t1.id = t3.idmatcha
  JOIN (SELECT DISTINCT
          doubleusername,
          doublefio
        FROM T_E_football_users) t4 ON t4.doubleusername = t3.username
ORDER BY 1 DESC, 2, 3;

--====================================
-- ПРОГНОЗ
--====================================
SELECT
  t1.data                                                                           AS "Дата игры",
  t1.gamer                                                                          AS "Играющие команды",
  cast(t3.userstrana1 AS VARCHAR(10)) + ' - ' + cast(t3.userstrana2 AS VARCHAR(10)) AS "Прогноз игрока"
FROM T_E_football_respisanie t1
  JOIN T_E_football_userSchet t3 ON t1.id = t3.idmatcha
  JOIN T_E_football_users t4 ON t4.doubleusername = t3.username
WHERE t4.doubleusername = 'ХЗ, что здесь должно быть' AND t4.username = 'пользователь'
ORDER BY t1.data, t1.gamer, t4.doublefio;

--====================================
-- ИТОГОВЫЕ РЕЗУЛЬТАТЫ
--====================================
SELECT
  t1.doublefio        AS "Игрок",
  t1.chempion         AS "Чемпион",
  t1.point            AS "Очки",
  t1.chemp            AS "+5 за чемпиона",
  t1.chemp + t1.point AS "Итоговые очки"
FROM (
       SELECT
         t4.doublefio,
         CASE WHEN t4.chempion = ''
           THEN 'Россия'
         ELSE t4.chempion END AS chempion,
         sum(
             CASE
             WHEN t2.strana1 = t3.userstrana1 AND t2.strana2 = t3.userstrana2
               THEN 3
             WHEN
               abs(t2.strana1 - t2.strana2) = abs(t3.userstrana1 - t3.userstrana2)
               AND
               (
                 (t2.strana1 >= t2.strana2 AND t3.userstrana1 >= t3.userstrana2)
                 OR
                 (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2)
               )
               THEN 2
             WHEN
               (t2.strana1 > t2.strana2 AND t3.userstrana1 > t3.userstrana2)
               OR
               (t2.strana1 < t2.strana2 AND t3.userstrana1 < t3.userstrana2)
               THEN 1
             ELSE 0 END
         )                    AS point,
         CASE WHEN t4.chempion = 'Германия'
           THEN 5
         ELSE 0 END           AS chemp
       FROM T_E_football_respisanie t1
         JOIN T_E_football_itogschet t2 ON t1.id = t2.idmatcha
         JOIN T_E_football_userSchet t3 ON t1.id = t3.idmatcha
         JOIN (SELECT DISTINCT
                 doubleusername,
                 doublefio,
                 chempion
               FROM T_E_football_users) t4 ON t4.doubleusername = t3.username
       GROUP BY t4.doublefio, t4.chempion
     ) t1
WHERE t1.doublefio <> 'Ежов Денис Анатольевич'
ORDER BY 5 DESC, 1;