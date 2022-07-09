-- drop existed database & users
drop database if exists breweryOrderService;
drop user if exists `brewery_order_service`@`%`;


-- create database for beer service
create database if not exists breweryOrderService character set utf8mb4 collate utf8mb4_unicode_ci;

-- create a user for managing beer service
create user if not exists `brewery_order_service`@`%` identified with mysql_native_password by 'mysqlpw';

-- grant user privileges
grant select, insert, update, delete, create, drop, references, index, alter, execute, create view, show view,
create routine, alter routine, event, trigger on `breweryOrderService`.* to `brewery_order_service`@`%`;

