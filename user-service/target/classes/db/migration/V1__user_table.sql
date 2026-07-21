CREATE TABLE `users` (
                         `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                         `first_name` VARCHAR(100) NOT NULL,
                         `last_name` VARCHAR(100),
                         `email` VARCHAR(255) NOT NULL,
                         `address` VARCHAR(255),
                         `alerting` TINYINT(1) DEFAULT FALSE,
                         `energy_alert_threshold` DOUBLE DEFAULT 0.0,

                         CONSTRAINT `uk_user_email` UNIQUE (`email`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `device` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(255),
                          `type` VARCHAR(255),
                          `location` VARCHAR(255),
                          `user_id` BIGINT NOT NULL,

                          PRIMARY KEY (`id`),
                          KEY `idx_device_user_id` (`user_id`),

                          CONSTRAINT `fk_device_user`
                              FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;