CREATE DATABASE {DATABASE_NAME};

GRANT ALL PRIVILEGES ON *.* TO ‘{USERNAME}'@'localhost’ IDENTIFIED BY '{PASSWORD}' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON *.* TO '{USERNAME}'@'%' IDENTIFIED BY '{PASSWORD}' WITH GRANT OPTION;
