CREATE TABLE `user_groups` (
                               `group_id` bigint NOT NULL AUTO_INCREMENT,
                               `group_name` varchar(45) NOT NULL,
                               PRIMARY KEY (`group_id`),
                               UNIQUE KEY `id_UNIQUE` (`group_id`),
                               UNIQUE KEY `name_UNIQUE` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user_groups` (
                               `group_id` bigint NOT NULL AUTO_INCREMENT,
                               `group_name` varchar(45) NOT NULL,
                               PRIMARY KEY (`group_id`),
                               UNIQUE KEY `id_UNIQUE` (`group_id`),
                               UNIQUE KEY `name_UNIQUE` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci