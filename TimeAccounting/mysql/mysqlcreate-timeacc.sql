-- MySQL Script generated by MySQL Workbench
-- Wed Jun 16 01:44:38 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema timeacc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema timeacc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `timeacc` DEFAULT CHARACTER SET utf8 ;
USE `timeacc` ;

-- -----------------------------------------------------
-- Table `timeacc`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeacc`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(30) NOT NULL,
  `lastname` VARCHAR(30) NOT NULL,
  `roles_id` INT NOT NULL,
  `locale` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  INDEX `fk_users_roles1_idx` (`roles_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `timeacc`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeacc`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `description` TEXT(1500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeacc`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `last_date` DATETIME NOT NULL,
  `timecount` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_activity_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_activity_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `timeacc`.`category` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeacc`.`users_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`users_activity` (
  `users_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  INDEX `fk_users_activity_users_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_users_activity_activity1_idx` (`activity_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_activity_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `timeacc`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `timeacc`.`activity` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeacc`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `timeacc`.`agreement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timeacc`.`agreement` (
  `users_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  INDEX `fk_agreement_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_agreement_activity1_idx` (`activity_id` ASC) VISIBLE,
  INDEX `fk_agreement_status1_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_agreement_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `timeacc`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agreement_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `timeacc`.`activity` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agreement_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `timeacc`.`status` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
