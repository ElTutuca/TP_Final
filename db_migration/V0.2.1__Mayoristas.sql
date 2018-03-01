UPDATE `Sucursal`.`Mayorista` SET `Nombre`='Juan Prerez', `NombreDeFantasia`='Distribuidora informatica' WHERE `idMayorista`='1';
UPDATE `Sucursal`.`Mayorista` SET `Nombre`='Martina Montenegro', `NombreDeFantasia`='Electrodomesticos Plus' WHERE `idMayorista`='2';
UPDATE `Sucursal`.`Mayorista` SET `Nombre`='Elon Musk', `NombreDeFantasia`='Sound\'s Owners' WHERE `idMayorista`='3';

ALTER TABLE `Sucursal`.`CategoriasIVA` 
CHANGE COLUMN `Nombre` `Nombre` VARCHAR(30) NOT NULL COMMENT '*' ;

INSERT INTO `Sucursal`.`CategoriasIVA` (`Nombre`, `Tasa`, `Discrimina`) VALUES ('Responsable Inscripto', '21.00', '1');
INSERT INTO `Sucursal`.`CategoriasIVA` (`Nombre`, `Tasa`, `Discrimina`) VALUES ('Monotributista', '21.00', '1');
INSERT INTO `Sucursal`.`CategoriasIVA` (`Nombre`, `Tasa`, `Discrimina`) VALUES ('Consumidor Final', '21.00', '0');

UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='1' WHERE `idCliente`='1';
UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='1' WHERE `idCliente`='2';
UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='1' WHERE `idCliente`='4';
UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='1' WHERE `idCliente`='3';
UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='2' WHERE `idCliente`='5';
UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='2' WHERE `idCliente`='6';
UPDATE `Sucursal`.`Clientes` SET `idCategoriasIVA`='2' WHERE `idCliente`='7';

