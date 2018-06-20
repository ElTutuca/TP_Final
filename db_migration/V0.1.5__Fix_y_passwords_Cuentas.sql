ALTER TABLE `Sucursal`.`Cuentas` 
CHANGE COLUMN `Pin` `Password` VARCHAR(16) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;

UPDATE `Sucursal`.`Cuentas` SET `Password`='contraFlash13' WHERE `idCuenta`='1';
UPDATE `Sucursal`.`Cuentas` SET `Password`='thefbiisontous' WHERE `idCuenta`='2';
UPDATE `Sucursal`.`Cuentas` SET `Password`='parlantes5' WHERE `idCuenta`='3';
UPDATE `Sucursal`.`Cuentas` SET `Password`='Wesalocura' WHERE `idCuenta`='4';
UPDATE `Sucursal`.`Cuentas` SET `Password`='Celcius87' WHERE `idCuenta`='5';
UPDATE `Sucursal`.`Cuentas` SET `Password`='itsyaboy48' WHERE `idCuenta`='6';
UPDATE `Sucursal`.`Cuentas` SET `Password`='Anaconda' WHERE `idCuenta`='7';
UPDATE `Sucursal`.`Cuentas` SET `Password`='dubaiWasLit' WHERE `idCuenta`='8';
UPDATE `Sucursal`.`Cuentas` SET `Password`='elDiegoteSape' WHERE `idCuenta`='9';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password1' WHERE `idCuenta`='10';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password2' WHERE `idCuenta`='11';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password3' WHERE `idCuenta`='12';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password4' WHERE `idCuenta`='13';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password5' WHERE `idCuenta`='14';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password6' WHERE `idCuenta`='15';
UPDATE `Sucursal`.`Cuentas` SET `Password`='password7' WHERE `idCuenta`='16';
