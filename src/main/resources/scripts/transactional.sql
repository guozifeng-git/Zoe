/*创建一个test_db的表*/
-- ----------------------------
-- Table structure for user1DO
-- ----------------------------
CREATE TABLE `user1DO` (
                         `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(45) NOT NULL DEFAULT '',
                         PRIMARY KEY(`id`)
)
    ENGINE = InnoDB;
-- ----------------------------
-- Table structure for user2DO
-- ----------------------------
CREATE TABLE `user2DO` (
                         `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(45) NOT NULL DEFAULT '',
                         PRIMARY KEY(`id`)
)
    ENGINE = InnoDB;