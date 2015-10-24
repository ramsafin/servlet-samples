use server_db;

create table users
(
	`id` int not null primary key auto_increment,
    `email` nvarchar(100) not null,
    `password` varchar(64) not null,
    `salt` varchar(48) not null,
    `sex` varchar(6) not null,
    `subscribtion` varchar(3) not null,
    `about` nvarchar(50),
    `remember` varchar(24)
)