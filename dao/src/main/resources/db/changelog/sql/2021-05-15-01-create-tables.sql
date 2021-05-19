CREATE TABLE `description` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_description` varchar(1000) DEFAULT NULL,
  `short_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `description_id` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_activity` datetime(6) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_email` (`email`),
  UNIQUE KEY `UK_phone` (`phone`),
  UNIQUE KEY `UK_username` (`username`),
  KEY `FK80iueb4cevo5hfnwuhusj7q0a` (`description_id`),
  CONSTRAINT `FK80iueb4cevo5hfnwuhusj7q0a` FOREIGN KEY (`description_id`) REFERENCES `description` (`id`) ON DELETE CASCADE
);

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt5dpb664voj4x94khaso754r3` (`user_id`),
  CONSTRAINT `FKt5dpb664voj4x94khaso754r3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);

CREATE TABLE `tournament` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description_id` bigint(20) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqkp9qijr6gdphr4eskgy1yge5` (`description_id`),
  CONSTRAINT `FKqkp9qijr6gdphr4eskgy1yge5` FOREIGN KEY (`description_id`) REFERENCES `description` (`id`) ON DELETE CASCADE
);

CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` varchar(255) DEFAULT NULL,
  `tournament_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27it38md3uamywwipgn0gnhjx` (`tournament_id`),
  KEY `FKky64jnwxps8e4pq6253893tb5` (`user_id`),
  CONSTRAINT `FK27it38md3uamywwipgn0gnhjx` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FKky64jnwxps8e4pq6253893tb5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `master_id` bigint(20) DEFAULT NULL,
  `service_id` bigint(20) DEFAULT NULL,
  `schedule_id` bigint(20) DEFAULT NULL,
  `tournament_id` bigint(20) DEFAULT NULL,
  `submit_date` datetime(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4rtcqpjdts0i4npp1y02rcyp1` (`master_id`),
  KEY `FK8aykrxnjm02bt6unpwj2n5ayr` (`service_id`),
  KEY `FKgc5ij95nuyax608k64bbqn788` (`tournament_id`),
  KEY `FKjobles2pd64dkyopjixx00vli` (`schedule_id`),
  CONSTRAINT `FK4rtcqpjdts0i4npp1y02rcyp1` FOREIGN KEY (`master_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK8aykrxnjm02bt6unpwj2n5ayr` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKgc5ij95nuyax608k64bbqn788` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FKjobles2pd64dkyopjixx00vli` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE CASCADE
);