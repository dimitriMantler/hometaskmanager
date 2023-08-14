CREATE TABLE IF NOT EXISTS `temp_table` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`temp_col` VARCHAR(50) NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_general_ci'
;

INSERT INTO temp_table (temp_col) VALUES ('Value 1');