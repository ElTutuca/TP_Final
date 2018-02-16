ALTER TABLE `Sucursal`.`Archivos` 
CHANGE COLUMN `Contenido` `Contenido` VARCHAR(255) NOT NULL ;

ALTER TABLE `Sucursal`.`Productos_has_Archivos` 
CHANGE COLUMN `Orden` `Orden` INT(11) NULL ;

INSERT INTO `Sucursal`.`Archivos` (`Contenido`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im1.jpg', 'image/jpeg', '64 ');
INSERT INTO `Sucursal`.`Archivos` (`Contenido`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im2.jpg', 'image/jpeg', '42 ');
INSERT INTO `Sucursal`.`Archivos` (`Contenido`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im3.jpg', 'image/jpeg', '26 ');
INSERT INTO `Sucursal`.`Archivos` (`Contenido`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im4.jpg', 'image/jpeg', '566 ');
INSERT INTO `Sucursal`.`Archivos` (`Contenido`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im5.jpg', 'image/jpeg', '17 ');
INSERT INTO `Sucursal`.`Archivos` (`Contenido`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im6.jpg', 'image/jpeg', '157 ');

INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('26', '0');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('26', '1');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('26', '2');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('26', '3');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '0');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '1');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '2');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '3');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '4');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '0');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '1');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '2');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '3');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '4');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('30', '0');
INSERT INTO `Sucursal`.`Productos_has_Archivos` (`idProductos`, `idArchivos`) VALUES ('30', '1');
