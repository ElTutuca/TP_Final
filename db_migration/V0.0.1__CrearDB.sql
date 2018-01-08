 
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
CREATE SCHEMA IF NOT EXISTS `Sucursal` DEFAULT CHARACTER SET utf8 ;
USE `Sucursal` ;

-- -----------------------------------------------------
-- Table `Sucursal`.`Sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Sucursal` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Sucursal` (
  `idSucursal` INT NOT NULL AUTO_INCREMENT,
  `Telefono` INT NULL,
  `Ubicacion` VARCHAR(45) NULL,
  `IP` VARCHAR(16) NULL,
  PRIMARY KEY (`idSucursal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Mayorista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Mayorista` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Mayorista` (
  `idMayorista` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMayorista`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Productos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Productos` (
  `idProductos` INT NOT NULL AUTO_INCREMENT,
  `Precio` DOUBLE NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Deposito` TINYINT NOT NULL,
  `StockMaximo` INT NOT NULL,
  `StockMinimo` INT NOT NULL,
  `StockIdeal` INT NOT NULL,
  `Stock` INT NOT NULL DEFAULT 0,
  `Descuento` INT NOT NULL DEFAULT 0,
  `PuntosReq` INT NOT NULL,
  `idMayorista` INT NOT NULL,
  `idSucursal` INT NOT NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Cuentas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Cuentas` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Cuentas` (
  `email` INT NULL,
  `Usuario` VARCHAR(16) NOT NULL,
  `Pin` VARCHAR(8) NOT NULL,
  `create_time` DATE NOT NULL,
  `Puntos` INT NOT NULL,
  `idCuenta` INT NOT NULL,
  UNIQUE INDEX `Usuario_UNIQUE` (`Usuario` ASC),
  PRIMARY KEY (`idCuenta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Clientes` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Clientes` (
  `idCliente` INT NOT NULL,
  `Nombre` VARCHAR(12) NOT NULL,
  `Apellido` VARCHAR(20) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(10) NOT NULL,
  `DNI` VARCHAR(8) NOT NULL,
  `idCuenta` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Clientes_Cuentas1_idx` (`idCuenta` ASC),
  CONSTRAINT `fk_Clientes_Cuentas1`
    FOREIGN KEY (`idCuenta`)
    REFERENCES `Sucursal`.`Cuentas` (`idCuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Ordenes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Ordenes` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Ordenes` (
  `idOrden` INT NOT NULL,
  `Fecha` DATETIME NULL,
  `idCliente` INT NULL,
  PRIMARY KEY (`idOrden`),
  INDEX `fk_Ordenes_Clientes1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Ordenes_Clientes1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Sucursal`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `Categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Archivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Archivos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Archivos` (
  `idArchivos` INT NOT NULL AUTO_INCREMENT,
  `Contenido` LONGBLOB NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `mimeType` VARCHAR(45) NOT NULL DEFAULT 'application/octet-stream',
  `Tamaño` BIGINT NOT NULL,
  PRIMARY KEY (`idArchivos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Subcategoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Subcategoria` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Subcategoria` (
  `idSubcategoria` INT NOT NULL AUTO_INCREMENT,
  `Subcategoria` VARCHAR(45) NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idSubcategoria`),
  INDEX `fk_Subcategoria_Categoria1_idx` (`idCategoria` ASC),
  CONSTRAINT `fk_Subcategoria_Categoria1`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `Sucursal`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Ordenes_Detalle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Ordenes_Detalle` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Ordenes_Detalle` (
  `idOrden` INT NOT NULL,
  `idProductos` INT NOT NULL,
  `canidad` INT NULL,
  `Precio` DOUBLE NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Productos_has_Archivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Productos_has_Archivos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Productos_has_Archivos` (
  `idProductos` INT NOT NULL,
  `idArchivos` INT NOT NULL,
  `Orden` INT NOT NULL,
  PRIMARY KEY (`idProductos`, `idArchivos`),
  INDEX `fk_Productos_has_Archivos_Archivos1_idx` (`idArchivos` ASC),
  INDEX `fk_Productos_has_Archivos_Productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Productos_has_Archivos_Productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `Sucursal`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_has_Archivos_Archivos1`
    FOREIGN KEY (`idArchivos`)
    REFERENCES `Sucursal`.`Archivos` (`idArchivos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sucursal`.`Subcategorias_Productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sucursal`.`Subcategorias_Productos` ;

CREATE TABLE IF NOT EXISTS `Sucursal`.`Subcategorias_Productos` (
  `Productos_idProductos` INT NOT NULL,
  `Subcategoria_idSubcategoria` INT NOT NULL,
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
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
