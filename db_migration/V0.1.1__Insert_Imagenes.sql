ALTER TABLE `Sucursal`.`Productos_Archivos` 
CHANGE COLUMN `Orden` `Orden` INT(11) NULL ; 

ALTER TABLE `Sucursal`.`Archivos` 
CHANGE COLUMN `Contenido` `PATH` VARCHAR(255) NOT NULL ;

ALTER TABLE `Sucursal`.`Archivos` 
CHANGE COLUMN `Tamaño` `Tamaño` MEDIUMINT(6) NOT NULL ;

INSERT INTO `Sucursal`.`Archivos` (`PATH`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im1.jpg', 'image/jpeg', '64 ');
INSERT INTO `Sucursal`.`Archivos` (`PATH`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im2.jpg', 'image/jpeg', '42 ');
INSERT INTO `Sucursal`.`Archivos` (`PATH`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im3.jpg', 'image/jpeg', '26 ');
INSERT INTO `Sucursal`.`Archivos` (`PATH`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im4.jpg', 'image/jpeg', '566 ');
INSERT INTO `Sucursal`.`Archivos` (`PATH`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im5.jpg', 'image/jpeg', '17 ');
INSERT INTO `Sucursal`.`Archivos` (`PATH`, `mimeType`, `Tamaño`) VALUES ('/home/mateo/Workspaces/Workspace_Eclipse/T.P. Final/extra/Imagenes/Heladera/im6.jpg', 'image/jpeg', '157 ');

INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('26', '1');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('26', '2');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '1');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '2');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '3');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '4');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('28', '5');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '1');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '2');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '3');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '4');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('29', '5');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('30', '1');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('30', '2');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('30', '3');
INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES ('30', '4');
