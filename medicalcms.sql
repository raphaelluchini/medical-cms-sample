
DROP DATABASE IF EXISTS medicalcms; 
CREATE DATABASE medicalcms; 

USE medicalcms;

# Dump of table anamneses
# ------------------------------------------------------------

DROP TABLE IF EXISTS `medicalcms`;

CREATE TABLE `anamneses` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `drugs` varchar(64) DEFAULT NULL,
  `orders` varchar(64) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `medic_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table images
# ------------------------------------------------------------

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `anamnese_id` int(11) DEFAULT NULL,
  `src` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table medics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `medics`;

CREATE TABLE `medics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table patients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patients`;

CREATE TABLE `patients` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  `age` int(11) NOT NULL,
  `email` varchar(32) NOT NULL DEFAULT '',
  `gender` varchar(32) NOT NULL DEFAULT '',
  `city` varchar(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
