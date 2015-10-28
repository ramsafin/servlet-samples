use server_db;

create TABLE users
(
	  `id` int not null primary key auto_increment,
    `email` varchar(100) UNIQUE KEY not null ,
    `password` varchar(64) not null,
    `salt` varchar(48) not null,
    `sex` varchar(6) not null,
    `subscription` varchar(3) not null,
    `about` nvarchar(50),
    `remember` varchar(24)
)