USE [AZ_DEV]
GO

IF OBJECT_ID('dbo.T_E_football_itogschet', 'U') IS NOT NULL
  DROP TABLE [dbo].[T_E_football_itogschet];
CREATE TABLE [dbo].[T_E_football_itogschet] (
  [idmatcha] [INT] NULL,
  [strana1]  [INT] NULL,
  [strana2]  [INT] NULL
) ON [PRIMARY];

IF OBJECT_ID('dbo.T_E_football_respisanie', 'U') IS NOT NULL
  DROP TABLE [dbo].[T_E_football_respisanie];
CREATE TABLE [dbo].[T_E_football_respisanie] (
  [id]    [INT] IDENTITY (1, 1) NOT NULL,
  [data]  [DATE]                NULL,
  [gamer] [VARCHAR](150)        NULL
) ON [PRIMARY];

IF OBJECT_ID('dbo.T_E_football_users', 'U') IS NOT NULL
  DROP TABLE [dbo].[T_E_football_users];
--select * from dbo.T_E_football_users;
CREATE TABLE [dbo].[T_E_football_users] (
  [fio]            [VARCHAR](100) NULL, --оригинальный игрок
  [username]       [VARCHAR](50)  NULL, --логин оригинального играка
  [password]       [VARCHAR](50)  NULL, -- пароль оригинального игрока
  [doublefio]      [VARCHAR](50)  NULL, -- за кого может вносить
  [doubleusername] [VARCHAR](50)  NULL, -- логин того за кого может внсить
  [chempion]       [VARCHAR](50)  NULL, -- чемпион оригинального игрока
) ON [PRIMARY];

IF OBJECT_ID('dbo.T_E_football_userSchet', 'U') IS NOT NULL
  DROP TABLE [dbo].[T_E_football_userSchet];
CREATE TABLE [dbo].[T_E_football_userSchet] (
  [username]    [VARCHAR](50) NULL,
  [idmatcha]    [INT]         NULL,
  [userstrana1] [INT]         NULL,
  [userstrana2] [INT]         NULL
) ON [PRIMARY];

--========================================================================
-- ВНЕСЕНИЕ МАТЧЕЙ
--========================================================================
-- DELETE FROM [dbo].[T_E_football_respisanie];
-- INSERT INTO [dbo].[T_E_football_respisanie] ([data], [gamer]) VALUES (getdate() + 1, 'Грузия - Румыния');
-- INSERT INTO [dbo].[T_E_football_respisanie] ([data], [gamer]) VALUES (getdate() + 1, 'Грузия - Молдова');
-- INSERT INTO [dbo].[T_E_football_respisanie] ([data], [gamer]) VALUES (getdate() + 1, 'Грузия - Словакия');
