 
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Sucursal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Sucursal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Sucursal` DEFAULT CHARACTER SET utf8mb4 ;
USE `Sucursal` ;

-- -----------------------------------------------------
-- Table `Sucursal`.`Archivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Archivos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Archivos` (
  `idArchivos` INT(11) NOT NULL AUTO_INCREMENT,
  `Contenido` LONGBLOB NOT NULL,
  `Nombre` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `mimeType` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL DEFAULT 'application/octet-stream',
  `Tamaño` BIGINT(20) NOT NULL,
  PRIMARY KEY (`idArchivos`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `Categoria` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Cuentas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Cuentas` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Cuentas` (
  `email` INT(11) NULL DEFAULT NULL,
  `Usuario` VARCHAR(16) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `Pin` VARCHAR(8) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `create_time` DATE NOT NULL,
  `Puntos` INT NOT NULL,
  `idCuenta` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idCuenta`),
  UNIQUE INDEX `Usuario_UNIQUE` (`Usuario` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Clientes` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Clientes` (
  `idCliente` INT(11) NOT NULL,
  `Nombre` VARCHAR(12) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `Apellido` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `Direccion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `Telefono` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `DNI` VARCHAR(8) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `idCuenta` INT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Clientes_Cuentas1_idx` (`idCuenta` ASC),
  CONSTRAINT `fk_Clientes_Cuentas1`
    FOREIGN KEY (`idCuenta`)
    REFERENCES `Sucursal`.`Cuentas` (`idCuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Mayorista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Mayorista` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Mayorista` (
  `idMayorista` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`idMayorista`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Ordenes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Ordenes` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Ordenes` (
  `idOrden` INT(11) NOT NULL,
  `Fecha` DATETIME NULL DEFAULT NULL,
  `idCliente` INT(11) NOT NULL,
  PRIMARY KEY (`idOrden`),
  INDEX `fk_Ordenes_Clientes1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Ordenes_Clientes1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Sucursal`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Sucursal` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Sucursal` (
  `idSucursal` INT(11) NOT NULL AUTO_INCREMENT,
  `Telefono` INT(11) NULL DEFAULT NULL,
  `Ubicacion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `IP` VARCHAR(16) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`idSucursal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Productos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Productos` (
  `idProductos` INT(11) NOT NULL AUTO_INCREMENT,
  `Precio` DOUBLE NOT NULL,
  `Nombre` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `Deposito` TINYINT(4) NOT NULL,
  `StockMaximo` INT(11) NOT NULL,
  `StockMinimo` INT(11) NOT NULL,
  `StockIdeal` INT(11) NOT NULL,
  `Stock` INT(11) NOT NULL DEFAULT '0',
  `Descuento` INT(11) NOT NULL DEFAULT '0',
  `PuntosReq` INT(11) NOT NULL,
  `idMayorista` INT(11) NOT NULL,
  `idSucursal` INT(11) NOT NULL,
  PRIMARY KEY (`idProductos`),
  INDEX `fk_Productos_Mayorista1_idx` (`idMayorista` ASC),
  INDEX `fk_Productos_Sucursal1_idx` (`idSucursal` ASC),
  CONSTRAINT `fk_Productos_Mayorista1`
    FOREIGN KEY (`idMayorista`)
    REFERENCES `Sucursal`.`Mayorista` (`idMayorista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_Sucursal1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `Sucursal`.`Sucursal` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Ordenes_Detalle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Ordenes_Detalle` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Ordenes_Detalle` (
  `idOrden` INT(11) NOT NULL,
  `idProductos` INT(11) NOT NULL,
  `canidad` INT(11) NOT NULL,
  `Precio` DOUBLE NOT NULL,
  PRIMARY KEY (`idOrden`, `idProductos`),
  INDEX `fk_Ordenes_Detalle_Productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Ordenes_Detalle_Ordenes1`
    FOREIGN KEY (`idOrden`)
    REFERENCES `Sucursal`.`Ordenes` (`idOrden`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ordenes_Detalle_Productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Productos_has_Archivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Productos_has_Archivos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Productos_has_Archivos` (
  `idProductos` INT(11) NOT NULL,
  `idArchivos` INT(11) NOT NULL,
  `Orden` INT(11) NOT NULL,
  PRIMARY KEY (`idProductos`, `idArchivos`),
  INDEX `fk_Productos_has_Archivos_Archivos1_idx` (`idArchivos` ASC),
  INDEX `fk_Productos_has_Archivos_Productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Productos_has_Archivos_Archivos1`
    FOREIGN KEY (`idArchivos`)
    REFERENCES `Sucursal`.`Archivos` (`idArchivos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_has_Archivos_Productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Subcategoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Subcategoria` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Subcategoria` (
  `idSubcategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `Subcategoria` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `idCategoria` INT(11) NOT NULL,
  PRIMARY KEY (`idSubcategoria`),
  INDEX `fk_Subcategoria_Categoria1_idx` (`idCategoria` ASC),
  CONSTRAINT `fk_Subcategoria_Categoria1`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `Sucursal`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`Subcategorias_Productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Subcategorias_Productos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Subcategorias_Productos` (
  `Productos_idProductos` INT(11) NOT NULL,
  `Subcategoria_idSubcategoria` INT(11) NOT NULL,
  PRIMARY KEY (`Productos_idProductos`, `Subcategoria_idSubcategoria`),
  INDEX `fk_Productos_has_Subcategoria_Subcategoria1_idx` (`Subcategoria_idSubcategoria` ASC),
  INDEX `fk_Productos_has_Subcategoria_Productos1_idx` (`Productos_idProductos` ASC),
  CONSTRAINT `fk_Productos_has_Subcategoria_Productos1`
    FOREIGN KEY (`Productos_idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_has_Subcategoria_Subcategoria1`
    FOREIGN KEY (`Subcategoria_idSubcategoria`)
    REFERENCES `Sucursal`.`Subcategoria` (`idSubcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `Sucursal`.`flyway_schema_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`flyway_schema_history` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`flyway_schema_history` (
  `installed_rank` INT(11) NOT NULL,
  `version` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `description` VARCHAR(200) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `type` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `script` VARCHAR(1000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `checksum` INT(11) NULL DEFAULT NULL,
  `installed_by` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT(11) NOT NULL,
  `success` TINYINT(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  INDEX `flyway_schema_history_s_idx` (`success` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
