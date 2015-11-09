use server_db;

create table posts
(
  `id` int not null primary key auto_increment,
  `text` nvarchar(500) not null,
  `date` DATETIME not null,
  `userId` int NOT NULL,
  FOREIGN KEY (userId) REFERENCES users(id)
)