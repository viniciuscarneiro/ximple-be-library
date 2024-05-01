CREATE TABLE `book` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `author` varchar(255) NOT NULL,
                        `isbn` varchar(255) NOT NULL,
                        `title` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `isbn_unique_constraint` (`isbn`)
);

CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) NOT NULL,
                        `name` varchar(255) NOT NULL,
                        `phone` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `email_unique_constraint` (`email`)
);

CREATE TABLE `reservation` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `created_at` datetime(6) NOT NULL,
                               `return_date` datetime(6) NOT NULL,
                               `book_id` bigint NOT NULL,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `fk_book_id` (`book_id`),
                               KEY `fk_user_id` (`user_id`),
                               CONSTRAINT `fk_book_id_reservation_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
                               CONSTRAINT `fk_user_id_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `review` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `created_at` datetime(6) NOT NULL,
                          `description` varchar(255) DEFAULT NULL,
                          `score` double NOT NULL,
                          `reservation_id` bigint NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_reservation_id` (`reservation_id`),
                          CONSTRAINT `fk_reservation_id_review_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
);