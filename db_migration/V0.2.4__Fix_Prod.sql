ALTER TABLE `Sucursal`.`Productos` 
CHANGE COLUMN `Precio` `Precio` DOUBLE NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Ubicacion` `Ubicacion` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL ,
CHANGE COLUMN `StockMaximo` `StockMaximo` MEDIUMINT(7) NOT NULL DEFAULT 0 ,
CHANGE COLUMN `StockMinimo` `StockMinimo` MEDIUMINT(7) NOT NULL DEFAULT 0 ,
CHANGE COLUMN `StockIdeal` `StockIdeal` MEDIUMINT(7) NOT NULL DEFAULT 0 ,
CHANGE COLUMN `Stock` `Stock` MEDIUMINT(7) NOT NULL DEFAULT 0 ;
CHANGE COLUMN `Descuento` `Descuento` DECIMAL(4,2) NOT NULL DEFAULT 0 ;

