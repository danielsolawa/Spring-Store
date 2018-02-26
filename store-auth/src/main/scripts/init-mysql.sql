#CREATE Databases
CREATE DATABASE spring_store_dev;
CREATE DATABASE spring_store_prod;

#Create database accounts
CREATE USER 'store_user_dev'@'localhost' IDENTIFIED BY 'springstore';
CREATE USER 'store_user_prod'@'localhost' IDENTIFIED BY 'springstore';
CREATE USER 'store_user_dev'@'%' IDENTIFIED BY 'springstore';
CREATE USER 'store_user_prod'@'%' IDENTIFIED BY 'springstore';


#Database permissions
GRANT SELECT ON spring_store_dev.* to 'store_user_dev'@'localhost';
GRANT INSERT ON spring_store_dev.* to 'store_user_dev'@'localhost';
GRANT UPDATE ON spring_store_dev.* to 'store_user_dev'@'localhost';
GRANT DELETE ON spring_store_dev.* to 'store_user_dev'@'localhost';

GRANT SELECT ON spring_store_prod.* to 'store_user_prod'@'localhost';
GRANT INSERT ON spring_store_prod.* to 'store_user_prod'@'localhost';
GRANT UPDATE ON spring_store_prod.* to 'store_user_prod'@'localhost';
GRANT DELETE ON spring_store_prod.* to 'store_user_prod'@'localhost';

GRANT SELECT ON spring_store_dev.* to 'store_user_dev'@'%';
GRANT INSERT ON spring_store_dev.* to 'store_user_dev'@'%';
GRANT UPDATE ON spring_store_dev.* to 'store_user_dev'@'%';
GRANT DELETE ON spring_store_dev.* to 'store_user_dev'@'%';

GRANT SELECT ON spring_store_prod.* to 'store_user_prod'@'%';
GRANT INSERT ON spring_store_prod.* to 'store_user_prod'@'%';
GRANT UPDATE ON spring_store_prod.* to 'store_user_prod'@'%';
GRANT DELETE ON spring_store_prod.* to 'store_user_prod'@'%';
