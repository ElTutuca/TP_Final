ALTER TABLE `Sucursal`.`Subcategorias_Productos` 
DROP FOREIGN KEY `fk_Productos_has_Subcategoria_Productos1`;
ALTER TABLE `Sucursal`.`Subcategorias_Productos` 
CHANGE COLUMN `Productos_idProductos` `idProductos` INT(11) NOT NULL ;
ALTER TABLE `Sucursal`.`Subcategorias_Productos` 
ADD CONSTRAINT `fk_Productos_has_Subcategoria_Productos1`
  FOREIGN KEY (`idProductos`)
  REFERENCES `Sucursal`.`Productos` (`idProductos`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
 
