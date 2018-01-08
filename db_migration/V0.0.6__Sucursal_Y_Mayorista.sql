ALTER TABLE `Sucursal`.`Sucursal` 
CHANGE COLUMN `Telefono` `Telefono` INT(11) NOT NULL ,
CHANGE COLUMN `Ubicacion` `Ubicacion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ,
CHANGE COLUMN `IP` `IP` VARCHAR(16) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;

ALTER TABLE `Sucursal`.`Sucursal` 
CHANGE COLUMN `Telefono` `Telefono` VARCHAR(10) NOT NULL ;
INSERT INTO `Sucursal`.`Sucursal` (`Telefono`, `Ubicacion`, `IP`) VALUES ('3543986532', 'Av. San Martin 500', '190.230.100.250');

INSERT INTO `Sucursal`.`Mayorista` (`Nombre`) VALUES ('Distribuidora informatica');
INSERT INTO `Sucursal`.`Mayorista` (`Nombre`) VALUES ('Electrodomesticos Plus');
INSERT INTO `Sucursal`.`Mayorista` (`Nombre`) VALUES ('Sound\'s Owners');
INSERT INTO `Sucursal`.`Mayorista` (`Nombre`) VALUES ('Games for y\'all');
