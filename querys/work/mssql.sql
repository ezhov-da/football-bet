-- ========================================================================
-- ВСТАВЛЯЕМ ИГРОКОВ
DELETE FROM [dbo].[T_E_football_users];
INSERT INTO [dbo].[T_E_football_users] (
  [fio]
  , [username]
  , [PASSWORD]
  , [doublefio]
  , [doubleusername]
  , [chempion]
) VALUES (
  'Ежов Денис Анатольевич',
  '1',
  '1',
  'Ежов Денис Анатольевич (2)',
  'ezhov_da',
  'Германия'
);

INSERT INTO [dbo].[T_E_football_users] (
  [fio]
  , [username]
  , [PASSWORD]
  , [doublefio]
  , [doubleusername]
  , [chempion]
) VALUES (
  'Ежов Денис Анатольевич',
  '1',
  '1',
  'Ежов Денис Анатольевич',
  '1',
  'Германия'
);

INSERT INTO [dbo].[T_E_football_users] (
  [fio]
  , [username]
  , [PASSWORD]
  , [doublefio]
  , [doubleusername]
  , [chempion]
) VALUES (
  'Ежов Денис Анатольевич',
  'ezhov_da',
  '1',
  'Ежов Денис Анатольевич Вот так вот',
  'ezhov_da',
  'Германия'
);

INSERT INTO [dbo].[T_E_football_users] (
  [fio]
  , [username]
  , [PASSWORD]
  , [doublefio]
  , [doubleusername]
  , [chempion]
) VALUES (
  'Ежов Денис Анатольевич',
  'javaf',
  '1',
  'Ежов Денис Анатольевич Вот так вот',
  'ezhov_da',
  'Германия'
);

SELECT *
FROM [dbo].[T_E_football_users];

--========================================================================
DELETE FROM [dbo].[T_E_football_respisanie];
INSERT INTO [dbo].[T_E_football_respisanie] ([data], [gamer]) VALUES (getdate() + 1, 'Грузия - Румыния');
INSERT INTO [dbo].[T_E_football_respisanie] ([data], [gamer]) VALUES (getdate() + 1, 'Грузия - Молдова');
INSERT INTO [dbo].[T_E_football_respisanie] ([data], [gamer]) VALUES (getdate() + 1, 'Грузия - Словакия');

SELECT *
FROM [dbo].[T_E_football_respisanie]

SELECT *
FROM [dbo].[T_E_football_itogschet]

SELECT *
FROM [dbo].[T_E_football_users];

--========================================================================
--Внесение результатов матча
--========================================================================
SELECT *
FROM [dbo].[T_E_football_itogschet];
INSERT INTO [dbo].[T_E_football_itogschet]
(
  [idmatcha]
  , [strana1]
  , [strana2]
) VALUES (
  2,
  3,
  4
);

SELECT
  t0.id,
  t0.data,
  t0.gamer
FROM [dbo].[T_E_football_respisanie] t0
  LEFT JOIN [dbo].[T_E_football_itogschet] t1 ON t0.id = t1.idmatcha
WHERE
  t1.idmatcha IS NULL;