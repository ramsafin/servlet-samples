use server_db;

create table users
(
	  `id` int not null primary key auto_increment,
    `email` nvarchar(100) not null,
    `password` nvarchar(64) not null,
    `salt` nvarchar(48) not null,
    `sex` nvarchar(6) not null,
    `subscription` nvarchar(3) not null,
    `about` nvarchar(50),
    `remember` nvarchar(24)
)