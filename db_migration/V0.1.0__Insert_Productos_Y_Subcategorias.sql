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
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('16', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('6735', 'INTEl i5 7400k', '1', '59', '25', '42', '60', '0', '4040', '1', '1', '10');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('17', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('7063', 'SENTEY Gabinete', '1', '59', '25', '42', '47', '0', '4237', '1', '1', '8');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('18', '1');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('1452', 'Notebook SAMSUNG', '1', '67', '29', '48', '68', '0', '871', '1', '1', '4');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('19', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('26240', 'ACER Nitro 5', '1', '30', '12', '21', '28', '0', '15744', '1', '1', '12');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('20', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('11738', 'ACER Swift 3', '1', '52', '22', '37', '42', '0', '7042', '1', '1', '12');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('21', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('29222', 'HP Pavilon', '1', '26', '11', '18', '27', '35', '17533', '1', '1', '13');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('22', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('8140', 'HP Neon', '0', '57', '24', '40', '45', '0', '4884', '1', '1', '13');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('23', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('15642', 'DELL Ultrathin', '1', '46', '19', '32', '40', '0', '9385', '1', '1', '15');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('24', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('19949', 'Netbook PCBOX', '1', '40', '17', '28', '36', '47', '11968', '1', '1', '14');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('25', '2');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('11485', 'DREAN Heladera Doble', '1', '52', '22', '37', '45', '25', '6890', '2', '1', '26');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('26', '5');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('10964', 'DREAN Freezer Mediano', '1', '53', '22', '37', '44', '0', '6578', '2', '1', '26');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('27', '5');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('8831', 'PHILCO Heladera ph7j-4g', '1', '56', '24', '40', '46', '0', '5298', '2', '1', '27');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('28', '5');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('6778', 'PHILIPS Heladera Doble con Dispenser', '1', '59', '25', '42', '47', '0', '4066', '2', '1', '25');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('29', '5');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('14514', 'LG Heladera sin Freezer', '1', '48', '20', '34', '39', '26', '8708', '2', '1', '28');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('30', '5');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('17877', 'OSTER Cafetera con Filtro', '1', '43', '18', '30', '35', '0', '10725', '2', '1', '31');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('31', '7');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('8744', 'OSTER Licuadora de acero', '1', '56', '24', '40', '47', '0', '5246', '2', '1', '31');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('32', '7');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('5396', 'PEABODY Freidora', '1', '61', '26', '43', '49', '0', '3237', '2', '1', '30');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('33', '7');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('16925', 'PHILCO Minipimer', '1', '44', '18', '31', '40', '0', '10154', '2', '1', '27');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('34', '7');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('12660', 'PEABODY Minipimer de acero', '1', '51', '21', '36', '44', '20', '7596', '2', '1', '30');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('35', '7');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('25774', 'PHILIPS Cafetera Express Doble', '1', '31', '13', '22', '30', '0', '15464', '2', '1', '25');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('36', '7');
INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES ('22347', 'PHILIPS AirFryer', '1', '36', '15', '25', '30', '0', '13407', '2', '1', '25');
INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES ('37', '7');




















