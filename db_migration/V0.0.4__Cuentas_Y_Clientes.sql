INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('LocoMax', '12345678', '500');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('KingOfTheNorth', '15978456', '700');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('MiracleMan', '15647893', '200');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('MineCrafter', '52198765', '5000');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('Rasparra', '35489462', '2550');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('Ringtone', '23458791', '50');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('RastaWoman', '98562314', '0');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('Heaven', '54687452', '350');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('HideNSeek', '23456781', '3000');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('NotGarbarino', '15985214', '10000');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('GanjaRanch', '69874512', '0');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('MineSweeper', '14785236', '950');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('TetrisGod', '25198743', '700');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('Comandante', '12345789', '5550');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('Alexa', '5821749', '15000');
INSERT INTO `Sucursal`.`Cuentas` (`Usuario`, `Pin`, `Puntos`) VALUES ('Flashing', '87456123', '0');
 
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='1' WHERE `idCliente`='3';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='2' WHERE `idCliente`='17';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='3' WHERE `idCliente`='31';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='4' WHERE `idCliente`='44';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='5' WHERE `idCliente`='47';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='6' WHERE `idCliente`='48';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='7' WHERE `idCliente`='63';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='8' WHERE `idCliente`='55';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='9' WHERE `idCliente`='82';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='10' WHERE `idCliente`='88';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='11' WHERE `idCliente`='95';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='12' WHERE `idCliente`='100';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='13' WHERE `idCliente`='74';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='14' WHERE `idCliente`='58';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='15' WHERE `idCliente`='22';
UPDATE `Sucursal`.`Clientes` SET `idCuenta`='16' WHERE `idCliente`='7';

ALTER TABLE `Sucursal`.`Cuentas` 
CHANGE COLUMN `email` `email` VARCHAR(30) NULL DEFAULT NULL ;

UPDATE `Sucursal`.`Cuentas` SET `email`='machine@gmail.com' WHERE `idCuenta`='2';
UPDATE `Sucursal`.`Cuentas` SET `email`='lifesavior@hotmail.com' WHERE `idCuenta`='5';
UPDATE `Sucursal`.`Cuentas` SET `email`='lasagna@outlook.com' WHERE `idCuenta`='6';
UPDATE `Sucursal`.`Cuentas` SET `email`='retrogamer@gmail.com' WHERE `idCuenta`='13';
UPDATE `Sucursal`.`Cuentas` SET `email`='dingdong@gmail.com' WHERE `idCuenta`='15';
UPDATE `Sucursal`.`Cuentas` SET `email`='requester@gmail.com' WHERE `idCuenta`='9';
