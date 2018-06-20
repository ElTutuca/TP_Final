UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='5';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='10';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='12';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='22';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='25';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='26';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='30';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='35';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='39';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='44';
UPDATE `Sucursal`.`Productos` SET `Descuento`='0', `PorcentajeIVA`='0' WHERE `idProductos`='45';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='1';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='2';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='3';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='4';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='6';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='7';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='8';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='9';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='11';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='13';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='14';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='15';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='16';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='17';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='18';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='19';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='20';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='21';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='23';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='24';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='27';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='28';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='29';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='31';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='32';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='33';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='34';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='36';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='37';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='38';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='40';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='41';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='42';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='43';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='46';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='0' WHERE `idProductos`='47';

-- MySQL Workbench Synchronization
-- Generated: 2018-02-26 23:11
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Mateo Pagnucco

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `Sucursal`.`Clientes` 
DROP FOREIGN KEY `fk_Clientes_Cuentas1`;

ALTER TABLE `Sucursal`.`Productos` 
DROP FOREIGN KEY `fk_Productos_Sucursal1`;

ALTER TABLE `Sucursal`.`Clientes` 
DROP COLUMN `idCuenta`,
DROP COLUMN `DNI`,
DROP COLUMN `Genero`,
DROP COLUMN `Edad`,
DROP COLUMN `Apellido`,
CHANGE COLUMN `Nombre` `Nombre` VARCHAR(30) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL COMMENT 'El nombre puede ser persona fisica o juridica (sociedad)' ,
CHANGE COLUMN `Direccion` `Direccion` VARCHAR(60) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL COMMENT 'SOLO para el consumidor final deberia poder ser null' ,
CHANGE COLUMN `Telefono` `Telefono` VARCHAR(40) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL ,
ADD COLUMN `NombreDeFantasia` VARCHAR(45) NULL DEFAULT NULL AFTER `Nombre`,
ADD COLUMN `NmroIngresosBrutos` VARCHAR(11) NULL DEFAULT NULL AFTER `Telefono`,
ADD COLUMN `CUIT` VARCHAR(13) NULL DEFAULT NULL COMMENT 'SOLO para el consumidor final deberia poder ser null' AFTER `NmroIngresosBrutos`,
ADD COLUMN `idCategoriasIVA` INT(11) NOT NULL AFTER `CUIT`,
ADD INDEX `fk_Clientes_CategoriasIVA1_idx` (`idCategoriasIVA` ASC),
DROP INDEX `fk_Clientes_Cuentas1_idx` ,
DROP INDEX `DNI_UNIQUE` ;

ALTER TABLE `Sucursal`.`Mayorista` 
CHANGE COLUMN `Nombre` `Nombre` VARCHAR(30) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ,
ADD COLUMN `NombreDeFantasia` VARCHAR(45) NULL DEFAULT NULL AFTER `Nombre`,
ADD COLUMN `Direccion` VARCHAR(60) NULL DEFAULT NULL AFTER `NombreDeFantasia`,
ADD COLUMN `Telefono` VARCHAR(40) NULL DEFAULT NULL AFTER `Direccion`,
ADD COLUMN `NmroIngresosBrutos` VARCHAR(11) NULL DEFAULT NULL AFTER `Telefono`,
ADD COLUMN `CUIT` VARCHAR(13) NULL DEFAULT NULL AFTER `NmroIngresosBrutos`,
ADD COLUMN `idCategoriasIVA` INT(11) NOT NULL AFTER `CUIT`,
ADD INDEX `fk_Mayorista_CategoriasIVA1_idx` (`idCategoriasIVA` ASC);

ALTER TABLE `Sucursal`.`Productos` 
DROP COLUMN `idSucursal`,
DROP COLUMN `PuntosReq`,
CHANGE COLUMN `idMarca` `idMarca` INT(11) NOT NULL AFTER `PorcentajeIVA`,
CHANGE COLUMN `Deposito` `Ubicacion` VARCHAR(20) NULL DEFAULT NULL ,
CHANGE COLUMN `StockMaximo` `StockMaximo` MEDIUMINT(7) NULL DEFAULT NULL ,
CHANGE COLUMN `StockMinimo` `StockMinimo` MEDIUMINT(7) NULL DEFAULT NULL ,
CHANGE COLUMN `StockIdeal` `StockIdeal` MEDIUMINT(7) NULL DEFAULT NULL ,
CHANGE COLUMN `Descuento` `Descuento` DECIMAL(3,2) NULL DEFAULT NULL ,
CHANGE COLUMN `Eliminado` `Eliminado` TINYINT(4) NOT NULL DEFAULT 0 ,
CHANGE COLUMN `PorcentajeIVA` `PorcentajeIVA` DECIMAL(3,2) NOT NULL ,
ADD COLUMN `Codigo` VARCHAR(8) NOT NULL AFTER `idProductos`,
DROP INDEX `fk_Productos_Sucursal1_idx` ;

ALTER TABLE `Sucursal`.`Ventas` 
DROP COLUMN `TipoComprobante`,
CHANGE COLUMN `NmroDeComprobante` `NmroDeComprobante` VARCHAR(13) NOT NULL ,
CHANGE COLUMN `Neto1050` `Neto1050` DOUBLE NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Neto2100` `Neto2100` DOUBLE NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Neto2700` `Neto2700` DOUBLE NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Iva1050` `Iva1050` DOUBLE NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Iva2100` `Iva2100` DOUBLE NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Iva2700` `Iva2700` DOUBLE NOT NULL DEFAULT 0 ,
ADD COLUMN `idTiposDeComprov` INT(11) NOT NULL AFTER `idCliente`,
ADD INDEX `fk_Ventas_TiposDeComprobantes1_idx` (`idTiposDeComprov` ASC);

ALTER TABLE `Sucursal`.`Ventas_Detalle` 
CHANGE COLUMN `idVenta` `idVenta` INT(11) NOT NULL FIRST,
CHANGE COLUMN `PorcentajeIVA` `PorcentajeIVA` DECIMAL(3,2) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idVenta`, `idProductos`);

CREATE TABLE IF NOT EXISTS `Sucursal`.`Compras` (
  `idCompra` INT(11) NOT NULL AUTO_INCREMENT,
  `idMayorista` INT(11) NOT NULL,
  `idTiposDeComprov` INT(11) NOT NULL,
  `Fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NmroDeComprobante` VARCHAR(15) NOT NULL,
  `Neto1050` DOUBLE NOT NULL DEFAULT 0,
  `Neto2100` DOUBLE NOT NULL DEFAULT 0,
  `Neto2700` DOUBLE NOT NULL DEFAULT 0,
  `Iva1050` DOUBLE NOT NULL DEFAULT 0,
  `Iva2100` DOUBLE NOT NULL DEFAULT 0,
  `Iva2700` DOUBLE NOT NULL DEFAULT 0,
  `Total` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCompra`),
  INDEX `fk_Compras_Mayorista1_idx` (`idMayorista` ASC),
  INDEX `fk_Compras_TiposDeComprobantes1_idx` (`idTiposDeComprov` ASC),
  CONSTRAINT `fk_Compras_Mayorista1`
    FOREIGN KEY (`idMayorista`)
    REFERENCES `Sucursal`.`Mayorista` (`idMayorista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compras_TiposDeComprobantes1`
    FOREIGN KEY (`idTiposDeComprov`)
    REFERENCES `Sucursal`.`TiposDeComprobantes` (`idTiposDeComprov`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Compras_Detalle` (
  `idCompra` INT(11) NOT NULL,
  `idProductos` INT(11) NOT NULL,
  `Cantidad` SMALLINT(5) NOT NULL,
  PRIMARY KEY (`idCompra`, `idProductos`),
  INDEX `fk_Compras_Detalle_Compras1_idx` (`idCompra` ASC),
  INDEX `fk_Compras_Detalle_Productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Compras_Detalle_Compras1`
    FOREIGN KEY (`idCompra`)
    REFERENCES `Sucursal`.`Compras` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compras_Detalle_Productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Metodo_Pago_De_Compras` (
  `idMetodo` INT(11) NOT NULL,
  `idCompra` INT(11) NOT NULL,
  PRIMARY KEY (`idMetodo`, `idCompra`),
  INDEX `fk_Metodo_Pago_has_Compras_Compras1_idx` (`idCompra` ASC),
  INDEX `fk_Metodo_Pago_has_Compras_Metodo_Pago1_idx` (`idMetodo` ASC),
  CONSTRAINT `fk_Metodo_Pago_has_Compras_Metodo_Pago1`
    FOREIGN KEY (`idMetodo`)
    REFERENCES `Sucursal`.`Metodo_Pago` (`idMetodo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Metodo_Pago_has_Compras_Compras1`
    FOREIGN KEY (`idCompra`)
    REFERENCES `Sucursal`.`Compras` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Sucursal`.`TiposDeComprobantes` (
  `idTiposDeComprov` INT(11) NOT NULL,
  `Nombre` VARCHAR(15) NOT NULL,
  `Letra` VARCHAR(1) NOT NULL,
  `Abreviatura` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`idTiposDeComprov`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Sucursal`.`CategoriasIVA` (
  `idCategoriasIVA` INT(11) NOT NULL,
  `Nombre` VARCHAR(20) NOT NULL COMMENT '*',
  `Tasa` DECIMAL(3,2) NOT NULL,
  `Discrimina` TINYINT(4) NOT NULL COMMENT 'Si discrimina IVA o no (Fac-A discrima iva, Factura b no duscrimina iva (No te lo mustra, ya esta incluido. Osea SOLO LOS PRECIOS FINALES))\n\n*Inscripto (Discrimina)\n*Monotributo\n*Excento\n\n//Discrimina significa que puede ver que iva lleva tal producto\n// Todos son iva del 21 en la tasa',
  PRIMARY KEY (`idCategoriasIVA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `Sucursal`.`Sucursal` ;

DROP TABLE IF EXISTS `Sucursal`.`Cuentas` ;

ALTER TABLE `Sucursal`.`Clientes` 
ADD CONSTRAINT `fk_Clientes_CategoriasIVA1`
  FOREIGN KEY (`idCategoriasIVA`)
  REFERENCES `Sucursal`.`CategoriasIVA` (`idCategoriasIVA`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `Sucursal`.`Mayorista` 
ADD CONSTRAINT `fk_Mayorista_CategoriasIVA1`
  FOREIGN KEY (`idCategoriasIVA`)
  REFERENCES `Sucursal`.`CategoriasIVA` (`idCategoriasIVA`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `Sucursal`.`Ventas` 
ADD CONSTRAINT `fk_Ventas_TiposDeComprobantes1`
  FOREIGN KEY (`idTiposDeComprov`)
  REFERENCES `Sucursal`.`TiposDeComprobantes` (`idTiposDeComprov`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

ALTER TABLE `Sucursal`.`Productos` 
CHANGE COLUMN `Descuento` `Descuento` DECIMAL(4,2) NULL DEFAULT NULL ;

ALTER TABLE `Sucursal`.`Productos` 
CHANGE COLUMN `PorcentajeIVA` `PorcentajeIVA` DECIMAL(4,2) NOT NULL ;

UPDATE `Sucursal`.`Productos` SET `Descuento`='24.50' WHERE `idProductos`='3';
UPDATE `Sucursal`.`Productos` SET `Descuento`='45.00' WHERE `idProductos`='16';
UPDATE `Sucursal`.`Productos` SET `Descuento`='30.55' WHERE `idProductos`='36';
UPDATE `Sucursal`.`Productos` SET `Descuento`='10.50' WHERE `idProductos`='44';

UPDATE `Sucursal`.`Productos` SET `Codigo`='K-455' WHERE `idProductos`='1';
UPDATE `Sucursal`.`Productos` SET `Codigo`='K-456' WHERE `idProductos`='2';
UPDATE `Sucursal`.`Productos` SET `Codigo`='K-454' WHERE `idProductos`='3';
UPDATE `Sucursal`.`Productos` SET `Codigo`='J-486' WHERE `idProductos`='4';
UPDATE `Sucursal`.`Productos` SET `Codigo`='J-001' WHERE `idProductos`='5';
UPDATE `Sucursal`.`Productos` SET `Codigo`='158-J' WHERE `idProductos`='6';
UPDATE `Sucursal`.`Productos` SET `Codigo`='JK-81' WHERE `idProductos`='7';
UPDATE `Sucursal`.`Productos` SET `Codigo`='JH-5987' WHERE `idProductos`='8';
UPDATE `Sucursal`.`Productos` SET `Codigo`='GE-G66' WHERE `idProductos`='9';
UPDATE `Sucursal`.`Productos` SET `Codigo`='GE-G12' WHERE `idProductos`='10';
UPDATE `Sucursal`.`Productos` SET `Codigo`='GE-1586' WHERE `idProductos`='11';
UPDATE `Sucursal`.`Productos` SET `Codigo`='5464-G' WHERE `idProductos`='12';
UPDATE `Sucursal`.`Productos` SET `Codigo`='1-GEA' WHERE `idProductos`='13';
UPDATE `Sucursal`.`Productos` SET `Codigo`='64-HAE' WHERE `idProductos`='14';
UPDATE `Sucursal`.`Productos` SET `Codigo`='HA-FA' WHERE `idProductos`='15';
UPDATE `Sucursal`.`Productos` SET `Codigo`='H-456' WHERE `idProductos`='16';
UPDATE `Sucursal`.`Productos` SET `Codigo`='4-4564' WHERE `idProductos`='17';
UPDATE `Sucursal`.`Productos` SET `Codigo`='5-568' WHERE `idProductos`='18';
UPDATE `Sucursal`.`Productos` SET `Codigo`='FA-468' WHERE `idProductos`='19';
UPDATE `Sucursal`.`Productos` SET `Codigo`='FA156' WHERE `idProductos`='20';
UPDATE `Sucursal`.`Productos` SET `Codigo`='1586' WHERE `idProductos`='21';
UPDATE `Sucursal`.`Productos` SET `Codigo`='1568' WHERE `idProductos`='22';
UPDATE `Sucursal`.`Productos` SET `Codigo`='111' WHERE `idProductos`='23';
UPDATE `Sucursal`.`Productos` SET `Codigo`='FAW-FA' WHERE `idProductos`='24';
UPDATE `Sucursal`.`Productos` SET `Codigo`='5-4816' WHERE `idProductos`='25';
UPDATE `Sucursal`.`Productos` SET `Codigo`='39-88' WHERE `idProductos`='26';
UPDATE `Sucursal`.`Productos` SET `Codigo`='F-FAW' WHERE `idProductos`='27';
UPDATE `Sucursal`.`Productos` SET `Codigo`='FEA-1' WHERE `idProductos`='28';
UPDATE `Sucursal`.`Productos` SET `Codigo`='486-D' WHERE `idProductos`='29';
UPDATE `Sucursal`.`Productos` SET `Codigo`='4-85' WHERE `idProductos`='30';
UPDATE `Sucursal`.`Productos` SET `Codigo`='44-456' WHERE `idProductos`='31';
UPDATE `Sucursal`.`Productos` SET `Codigo`='48-588' WHERE `idProductos`='32';
UPDATE `Sucursal`.`Productos` SET `Codigo`='DA-486' WHERE `idProductos`='33';
UPDATE `Sucursal`.`Productos` SET `Codigo`='FF-FF' WHERE `idProductos`='34';
UPDATE `Sucursal`.`Productos` SET `Codigo`='486-W' WHERE `idProductos`='35';
UPDATE `Sucursal`.`Productos` SET `Codigo`='D4-55' WHERE `idProductos`='36';
UPDATE `Sucursal`.`Productos` SET `Codigo`='1DWA' WHERE `idProductos`='37';
UPDATE `Sucursal`.`Productos` SET `Codigo`='1-5-84' WHERE `idProductos`='38';
UPDATE `Sucursal`.`Productos` SET `Codigo`='FWP-01' WHERE `idProductos`='39';
UPDATE `Sucursal`.`Productos` SET `Codigo`='ṔQ-48-88' WHERE `idProductos`='40';
UPDATE `Sucursal`.`Productos` SET `Codigo`='486-88' WHERE `idProductos`='41';
UPDATE `Sucursal`.`Productos` SET `Codigo`='D-001' WHERE `idProductos`='42';
UPDATE `Sucursal`.`Productos` SET `Codigo`='9-999' WHERE `idProductos`='43';
UPDATE `Sucursal`.`Productos` SET `Codigo`='10-001' WHERE `idProductos`='44';
UPDATE `Sucursal`.`Productos` SET `Codigo`='13-5' WHERE `idProductos`='45';
UPDATE `Sucursal`.`Productos` SET `Codigo`='4-4816' WHERE `idProductos`='46';
UPDATE `Sucursal`.`Productos` SET `Codigo`='2-488' WHERE `idProductos`='47';

UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='1';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='2';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='3';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='4';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='5';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='6';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='7';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='8';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='10.50' WHERE `idProductos`='9';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='10';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='11';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='12';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='13';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='14';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='15';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='16';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='17';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='18';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='19';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='20';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='21';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='22';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='23';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='24';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='25';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='26';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='27';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='28';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='29';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='30';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='31';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='32';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='33';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='34';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='35';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='36';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='37';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='38';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='39';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='27.00' WHERE `idProductos`='40';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='41';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='42';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='43';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='44';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='45';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='46';
UPDATE `Sucursal`.`Productos` SET `PorcentajeIVA`='21.00' WHERE `idProductos`='47';

UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=1;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=2;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=3;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=4;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=5;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`='J1' WHERE `idProductos`=6;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`='A2' WHERE `idProductos`=7;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=8;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=9;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=10;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=11;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=12;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=13;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=14;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=15;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=16;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=17;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=18;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=19;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=20;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=21;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=22;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=23;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=24;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=25;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=26;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=27;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=28;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=29;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=30;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=31;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=32;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=33;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=34;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=35;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=36;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`='B6' WHERE `idProductos`=37;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=38;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=39;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=40;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=41;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=42;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=43;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=44;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=45;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=46;
UPDATE `Sucursal`.`Productos` SET `Ubicacion`=NULL WHERE `idProductos`=47;

ALTER TABLE `Sucursal`.`CategoriasIVA` 
CHANGE COLUMN `Tasa` `Tasa` DECIMAL(4,2) NOT NULL ;

ALTER TABLE `Sucursal`.`Ventas_Detalle` 
CHANGE COLUMN `PorcentajeIVA` `PorcentajeIVA` DECIMAL(4,2) NOT NULL ;

-- MySQL Workbench Synchronization
-- Generated: 2018-02-27 00:10
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Mateo Pagnucco

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `Sucursal`.`Productos` 
CHANGE COLUMN `Stock` `Stock` MEDIUMINT(7) NOT NULL ,
CHANGE COLUMN `Descuento` `Descuento` DECIMAL(4,2) NULL DEFAULT NULL ;

ALTER TABLE `Sucursal`.`TiposDeComprobantes` 
CHANGE COLUMN `idTiposDeComprov` `idTiposDeComprov` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `Sucursal`.`CategoriasIVA` 
CHANGE COLUMN `idCategoriasIVA` `idCategoriasIVA` INT(11) NOT NULL AUTO_INCREMENT ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



ALTER TABLE `Sucursal`.`TiposDeComprobantes` 
CHANGE COLUMN `idTiposDeComprov` `idTiposDeComprob` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `Sucursal`.`Ventas` 
DROP FOREIGN KEY `fk_Ventas_TiposDeComprobantes1`;
ALTER TABLE `Sucursal`.`Ventas` 
CHANGE COLUMN `idTiposDeComprov` `idTiposDeComprob` INT(11) NOT NULL ;
ALTER TABLE `Sucursal`.`Ventas` 
ADD CONSTRAINT `fk_Ventas_TiposDeComprobantes1`
  FOREIGN KEY (`idTiposDeComprob`)
  REFERENCES `Sucursal`.`TiposDeComprobantes` (`idTiposDeComprob`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `Sucursal`.`Compras` 
DROP FOREIGN KEY `fk_Compras_TiposDeComprobantes1`;
ALTER TABLE `Sucursal`.`Compras` 
CHANGE COLUMN `idTiposDeComprov` `idTiposDeComprob` INT(11) NOT NULL ;
ALTER TABLE `Sucursal`.`Compras` 
ADD CONSTRAINT `fk_Compras_TiposDeComprobantes1`
  FOREIGN KEY (`idTiposDeComprob`)
  REFERENCES `Sucursal`.`TiposDeComprobantes` (`idTiposDeComprob`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
