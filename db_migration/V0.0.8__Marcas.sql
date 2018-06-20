-- MySQL Workbench Synchronization
-- Generated: 2018-01-08 16:09
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Mateo Pagnucco

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `Sucursal`.`Productos` 
ADD COLUMN `Marca_idMarca` INT(11) NOT NULL AFTER `idSucursal`,
ADD INDEX `fk_Productos_Marca1_idx` (`Marca_idMarca` ASC);

CREATE TABLE IF NOT EXISTS `Sucursal`.`Marca` (
  `idMarca` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMarca`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

ALTER TABLE `Sucursal`.`Productos` 
ADD CONSTRAINT `fk_Productos_Marca1`
  FOREIGN KEY (`Marca_idMarca`)
  REFERENCES `Sucursal`.`Marca` (`idMarca`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

ALTER TABLE `Sucursal`.`Productos` 
DROP FOREIGN KEY `fk_Productos_Marca1`;
ALTER TABLE `Sucursal`.`Productos` 
CHANGE COLUMN `Marca_idMarca` `idMarca` INT(11) NOT NULL ;
ALTER TABLE `Sucursal`.`Productos` 
ADD CONSTRAINT `fk_Productos_Marca1`
  FOREIGN KEY (`idMarca`)
  REFERENCES `Sucursal`.`Marca` (`idMarca`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('TOSHIBA');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('AMD');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('NVIDIA');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('SAMSUNG');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('KINGSTON');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('ASUS');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('GIGABYTE');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('SENTEY');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('NOGA');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('INTEL');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('WESTERN DIGITAL');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('ACER');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('HP');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('PCBOX');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('DELL');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('LENOVO');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('VAIO');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('BANGHO');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('LOGITECH');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('NOGANET');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('NISUTA');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('CORSAIR');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('RAZER');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('GENIUS');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('PHILIPS');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('DREAN');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('PHILCO');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('LG');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('WHIRLPOOL');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('PEABODY');
INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES ('OSTER');




