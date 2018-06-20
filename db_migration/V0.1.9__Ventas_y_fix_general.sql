 -- MySQL Workbench Synchronization
-- Generated: 2018-02-24 18:53
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Mateo Pagnucco

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `Sucursal`.`Productos` 
CHANGE COLUMN `Stock` `Stock` MEDIUMINT(7) NOT NULL DEFAULT '0' ,
CHANGE COLUMN `Descuento` `Descuento` TINYINT(3) NOT NULL DEFAULT '0' ;

ALTER TABLE `Sucursal`.`Sucursal` 
CHANGE COLUMN `Ubicacion` `Ubicacion` VARCHAR(120) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Ventas` (
  `idVenta` INT(11) NOT NULL AUTO_INCREMENT,
  `idCliente` INT(11) NOT NULL,
  `Fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TipoComprobante` VARCHAR(60) NOT NULL,
  `NmroDeComprobante` VARCHAR(16) NOT NULL,
  `PuntoDeVenta` VARCHAR(4) NOT NULL,
  `Neto1050` DECIMAL(3,1) NOT NULL DEFAULT 0,
  `Neto2100` DECIMAL(3,1) NOT NULL DEFAULT 0,
  `Neto2700` DECIMAL(3,1) NOT NULL DEFAULT 0,
  `Iva1050` DECIMAL(3,1) NOT NULL DEFAULT 0,
  `Iva2100` DECIMAL(3,1) NOT NULL DEFAULT 0,
  `Iva2700` DECIMAL(3,1) NOT NULL DEFAULT 0,
  `Total` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`idVenta`),
  INDEX `fk_Ventas_Clientes1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Ventas_Clientes1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Sucursal`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Metodo_Pago_De_Ventas` (
  `idVenta` INT(11) NOT NULL,
  `idMetodo` INT(11) NOT NULL,
  PRIMARY KEY (`idVenta`, `idMetodo`),
  INDEX `fk_Ventas_has_Metodo_Pago_Metodo_Pago1_idx` (`idMetodo` ASC),
  INDEX `fk_Ventas_has_Metodo_Pago_Ventas1_idx` (`idVenta` ASC),
  CONSTRAINT `fk_Ventas_has_Metodo_Pago_Ventas1`
    FOREIGN KEY (`idVenta`)
    REFERENCES `Sucursal`.`Ventas` (`idVenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ventas_has_Metodo_Pago_Metodo_Pago1`
    FOREIGN KEY (`idMetodo`)
    REFERENCES `Sucursal`.`Metodo_Pago` (`idMetodo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Ventas_Detalle` (
  `idProductos` INT(11) NOT NULL,
  `idVenta` INT(11) NOT NULL,
  `Cantidad` SMALLINT(5) NOT NULL,
  `PrecioUnitario` DOUBLE NOT NULL,
  `Descuento` TINYINT(3) NOT NULL,
  `Neto` DOUBLE NOT NULL,
  `PorcentajeIVA` DECIMAL(3,1) NOT NULL,
  PRIMARY KEY (`idProductos`, `idVenta`),
  INDEX `fk_Productos_has_Ventas_Ventas1_idx` (`idVenta` ASC),
  INDEX `fk_Productos_has_Ventas_Productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Productos_has_Ventas_Productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_has_Ventas_Ventas1`
    FOREIGN KEY (`idVenta`)
    REFERENCES `Sucursal`.`Ventas` (`idVenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `Sucursal`.`Ordenes_Detalle` ;

DROP TABLE IF EXISTS `Sucursal`.`Ordenes` ;

DROP TABLE IF EXISTS `Sucursal`.`Metodo_Pago_De_Orden` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
