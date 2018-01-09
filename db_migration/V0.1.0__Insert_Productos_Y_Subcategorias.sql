ALTER TABLE `Sucursal`.`Subcategorias_Productos` 
DROP FOREIGN KEY `fk_Productos_has_Subcategoria_Subcategoria1`;
ALTER TABLE `Sucursal`.`Subcategorias_Productos` 
CHANGE COLUMN `Subcategoria_idSubcategoria` `idSubcategoria` INT(11) NOT NULL ;
ALTER TABLE `Sucursal`.`Subcategorias_Productos` 
ADD CONSTRAINT `fk_Productos_has_Subcategoria_Subcategoria1`
  FOREIGN KEY (`idSubcategoria`)
  REFERENCES `Sucursal`.`Subcategoria` (`idSubcategoria`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('7669', 'SSD de 128GB TOSHIBA', '0', '58', '25', '41', '59', '0', '4600', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('1', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('28058', 'SSD de 256GB TOSHIBA', '1', '27', '11', '19', '30', '0', '16834', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('2', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('12195', 'SSD de 512GB TOSHIBA', '0', '51', '22', '36', '59', '0', '7316', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('3', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('16026', 'HDD de 512GB TOSHIBA', '1', '45', '19', '32', '37', '0', '9615', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('4', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('26422', 'HDD de 1024GB TOSHIBA', '1', '30', '12', '21', '33', '24', '15853', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('5', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('28591', 'HDD de 1536GB TOSHIBA', '0', '27', '11', '19', '25', '0', '17154', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('6', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('26466', 'HDD de 2048GB TOSHIBA', '1', '30', '12', '21', '26', '0', '15879', '1', '1', '1');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('7', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('11233', 'NVIDIA GTX 1050', '1', '53', '22', '37', '46', '0', '6739', '1', '1', '3');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('8', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('21981', 'NVIDIA GTX 180ti', '1', '37', '15', '26', '49', '0', '13188', '1', '1', '3');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('9', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('21173', 'NVIDIA GTX 1080', '0', '38', '16', '27', '38', '31', '12703', '1', '1', '3');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('10', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('7523', 'NVIDIA GTX 1050ti', '1', '58', '25', '41', '50', '0', '4513', '1', '1', '3');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('11', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('22973', 'NVIDIA GTX 1040', '1', '35', '15', '25', '48', '26', '13783', '1', '1', '3');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('12', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('23182', 'AMD Ryzen 5', '1', '35', '14', '24', '43', '0', '13909', '1', '1', '2');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('13', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('8060', 'AMD Ryzen 3', '0', '57', '24', '40', '56', '0', '4836', '1', '1', '2');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('14', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('19735', 'Mother ASUS', '1', '40', '17', '28', '51', '0', '11840', '1', '1', '6');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('15', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('6735', 'Mother GIGABYTE', '1', '59', '25', '42', '60', '0', '4040', '1', '1', '7');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('8', '1');








