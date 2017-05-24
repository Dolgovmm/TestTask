CREATE DATABASE  IF NOT EXISTS `contactmanager` ;

use contactmanager;


drop table if exists `contacts`;

CREATE TABLE `contactmanager`.`contacts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telephone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));