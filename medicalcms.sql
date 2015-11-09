SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema medicalcms
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema medicalcms
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `medicalcms` DEFAULT CHARACTER SET utf8 ;
USE `medicalcms` ;

-- -----------------------------------------------------
-- Table `medicalcms`.`medics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medicalcms`.`medics` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `medicalcms`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medicalcms`.`patients` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL DEFAULT '',
  `age` INT(11) NOT NULL,
  `email` VARCHAR(32) NOT NULL DEFAULT '',
  `gender` VARCHAR(32) NOT NULL DEFAULT '',
  `city` VARCHAR(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `medicalcms`.`anamneses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medicalcms`.`anamneses` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `drugs` VARCHAR(64) NULL DEFAULT NULL,
  `orders` VARCHAR(64) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `medics_id` INT(11) UNSIGNED NOT NULL,
  `patients_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_anamneses_medics1_idx` (`medics_id` ASC),
  INDEX `fk_anamneses_patients1_idx` (`patients_id` ASC),
  CONSTRAINT `fk_anamneses_medics1`
    FOREIGN KEY (`medics_id`)
    REFERENCES `medicalcms`.`medics` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_anamneses_patients1`
    FOREIGN KEY (`patients_id`)
    REFERENCES `medicalcms`.`patients` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `medicalcms`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medicalcms`.`images` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `anamnese_id` INT(11) NULL DEFAULT NULL,
  `src` VARCHAR(255) NULL DEFAULT NULL,
  `anamneses_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_images_anamneses_idx` (`anamneses_id` ASC),
  CONSTRAINT `fk_images_anamneses`
    FOREIGN KEY (`anamneses_id`)
    REFERENCES `medicalcms`.`anamneses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
