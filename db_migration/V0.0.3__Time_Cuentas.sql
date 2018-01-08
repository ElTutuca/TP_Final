ALTER TABLE `Sucursal`.`Cuentas` 
CHANGE COLUMN `create_time` `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp() ;
