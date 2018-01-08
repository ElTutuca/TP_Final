 -- MySQL Workbench Synchronization
-- Generated: 2018-01-08 14:45
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Mateo Pagnucco

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `Sucursal`.`Metodo_Pago` (
  `idMetodo` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMetodo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Metodo_Pago_De_Orden` (
  `idMetodo` INT(11) NOT NULL,
  `idOrden` INT(11) NOT NULL,
  PRIMARY KEY (`idMetodo`, `idOrden`),
  INDEX `fk_Metodo_Pago_has_Ordenes_Ordenes1_idx` (`idOrden` ASC),
  INDEX `fk_Metodo_Pago_has_Ordenes_Metodo_Pago1_idx` (`idMetodo` ASC),
  CONSTRAINT `fk_Metodo_Pago_has_Ordenes_Metodo_Pago1`
    FOREIGN KEY (`idMetodo`)
    REFERENCES `Sucursal`.`Metodo_Pago` (`idMetodo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Metodo_Pago_has_Ordenes_Ordenes1`
    FOREIGN KEY (`idOrden`)
    REFERENCES `Sucursal`.`Ordenes` (`idOrden`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `Sucursal`.`Metodo_Pago` (`Descripcion`) VALUES ('Efectivo');
INSERT INTO `Sucursal`.`Metodo_Pago` (`Descripcion`) VALUES ('Tarjeta');
