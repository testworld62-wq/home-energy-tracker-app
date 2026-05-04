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