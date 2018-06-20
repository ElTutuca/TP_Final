 -- MySQL Workbench Synchronization
-- Generated: 2018-02-16 09:42
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Mateo Pagnucco

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `Sucursal`.`Productos` 
DROP FOREIGN KEY `fk_Productos_Mayorista1`;

ALTER TABLE `Sucursal`.`Productos` 
DROP COLUMN `idMayorista`,
DROP INDEX `fk_Productos_Mayorista1_idx` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Mayorista_Productos` (
  `idMayorista` INT(11) NOT NULL,
  `idProductos` INT(11) NOT NULL,
  PRIMARY KEY (`idMayorista`, `idProductos`),
  INDEX `fk_Mayorista_Productos_Productos1_idx` (`idProductos` ASC),
  INDEX `fk_Mayorista_Productos_Mayorista1_idx` (`idMayorista` ASC),
  CONSTRAINT `fk_Mayorista_Productos_Mayorista1`
    FOREIGN KEY (`idMayorista`)
    REFERENCES `Sucursal`.`Mayorista` (`idMayorista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mayorista_Productos_Productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
