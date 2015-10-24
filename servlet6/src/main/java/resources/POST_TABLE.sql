use server_db;

create table posts
(
  `id` int not null primary key auto_increment,
  `text` varchar(500) not null,
  `date` date not null,
  `user_id` int NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
)