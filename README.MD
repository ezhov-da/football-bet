# football-bet

Приложение для ставок на футбольные матчи.  

На данный момент приложение настроено на работу с БД MSSQL с конкретной БД и авторизацией.  

Скрипт для создания таблиц в БД располагается по пути: football-bet\querys\mssql.sql  
Закоменитрованный код - функционал внесения списка игр, который необходим для начала работы.

Для сборки проекта необходимо выполнить **mvn clean package**.  
Для запуска необходимо расположить с итоговым jar фалом [football-bet-server.jar] файлы:  
1. run.bat - запуск приложения
1. admins.properties - файл с логинами администраторов, если необходимо, 
чтоб зарегистрированному пользователю стал доступен функционал внесения результатаов по играм, 
просто внесите его логин в это файл и перезапустите сервер.


**ВАЖНО!** Данное приложение является реинкарнацией приложения написанного 4 года назад на коленке глубокой ночью.   
И в нем просто огромное кол-во кровоточащего кода.

В данное приложении перенесен некоторый код из старого, а так же был произведен рефакторинг компонентов.

В данном приложение еще уйма работы, но необходимый функционал на данный момент он выполняет.


