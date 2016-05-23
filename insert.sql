insert into Departamentos(departamento) values('Sistemas');
insert into Departamentos(departamento) values('Electrónica');

insert into Rol(rol) values ('Laboratorista');
insert into Rol(rol) values ('Estudiante');
insert into Rol(rol) values ('Profesor');
insert into Rol(rol) values ('admin');

insert into Personas (carne,nombre,apellido,email,telefono) values ('2105684','Julián','Devia','julian.devia@mail.escuelaing.edu.co','3115086950');
insert into Personas (carne,nombre,apellido,email,telefono) values ('30285974','Pepito','Perez','pepito.perez@mail.escuelaing.edu.co','3105650732');
insert into Personas (carne,nombre,apellido,email,telefono) values ('2105533','Hugo','Álvarez','hugo.alvarez@mail.escuelaing.edu.co','0316706243');
insert into Personas (carne,nombre,apellido,email,telefono) values ('1020816982','Carmenzo','Rodríguez','carmenzo.rodriguez@mail.escuelaing.edu.co','3129876543');
insert into Personas (carne,nombre,apellido,email,telefono) values ('2107646','Daniela','Sepúlveda','daniela.sepulveda@mail.escuelaing.edu.co','3204615289');
insert into Personas (carne,nombre,apellido,email,telefono) values ('2105403','Germán','Lopez','german.lopez-p@mail.escuelaing.edu.co','123456789');
insert into Personas (carne,nombre,apellido,email,telefono) values ('admin','admin','','admin.admin@mail.escuelaing.edu.co','00000000000');
insert into Personas (carne,nombre,apellido,email,telefono) values ('laboratorista','laboratorista','','laboratorista.laboratorista@mail.escuelaing.edu.co','00000000000');
insert into Personas (carne,nombre,apellido,email,telefono) values ('estudiante','estudiante','','estudiante.estudiante@mail.escuelaing.edu.co','00000000000');
insert into Personas (carne,nombre,apellido,email,telefono) values ('108763526','Camilo','Lopez','camilo.lopez@mail.escuelaing.edu.co','983746232');
insert into Personas (carne,nombre,apellido,email,telefono) values ('109328347','Juan','Castaño','juan.castano@mail.escuelaing.edu.co','97532352');
insert into Personas (carne,nombre,apellido,email,telefono) values ('2107653','Laura','Gonzalez','laura.gonzalez@mail.escuelaing.edu.co','579283624');
insert into Personas (carne,nombre,apellido,email,telefono) values ('2101234','Cielo','Rodríguez','cielo.rodriguez@mail.escuelaing.edu.co','5367883632');

insert into Departamento_persona(departamento,persona) values ('Sistemas','2105684');
insert into Departamento_persona(departamento,persona) values ('Sistemas','2105533');
insert into Departamento_persona(departamento,persona) values ('Sistemas','30285974');
insert into Departamento_persona(departamento,persona) values ('Electrónica','30285974');
insert into Departamento_persona(departamento,persona) values ('Electrónica','1020816982');
insert into Departamento_persona(departamento,persona) values ('Electrónica','admin');
insert into Departamento_persona(departamento,persona) values ('Sistemas','2107646');
insert into Departamento_persona(departamento,persona) values ('Electrónica','2107646');
insert into Departamento_persona(departamento,persona) values ('Sistemas','2105403');
insert into Departamento_persona(departamento,persona) values ('Electrónica','laboratorista');
insert into Departamento_persona(departamento,persona) values ('Sistemas','estudiante');
insert into Departamento_persona(departamento,persona) values ('Sistemas','108763526');
insert into Departamento_persona(departamento,persona) values ('Sistemas','109328347');
insert into Departamento_persona(departamento,persona) values ('Sistemas','2107653');
insert into Departamento_persona(departamento,persona) values ('Sistemas','2101234');

-- jd
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2105684','Estudiante','$shiro1$SHA-256$500000$au4DbmdVFwERxtVlrBiklw==$YcbdtRn7ngj/BbjICVctUY9GarFdDA9/VOpN+RNSRDk=','bXlwcml2YXRlc2FsdA==');
-- pp
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('30285974','Profesor','$shiro1$SHA-256$500000$LuJhV230oma/66/GNsTHXA==$6oCsexdCvI+qzeVkQLITU2+PoBEBFE1IXUAtAPhLShg=','bXlwcml2YXRlc2FsdA==');
-- ha
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2105533','Estudiante','$shiro1$SHA-256$500000$BIjJiJBXUIQ1wF15Ezez+Q==$2nfl7K/AR7DCaWuhtCUmqO88XMo+gHdTodeuP5gon1c=','bXlwcml2YXRlc2FsdA==');
-- cr
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('1020816982','Laboratorista','$shiro1$SHA-256$500000$B46i8ACGEOUiRua3tOutUw==$JnDa/hCdq3ES4IMKYgGYKZxe4nVoc7oRPotm+FgtHZY=','bXlwcml2YXRlc2FsdA==');
-- admin
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('admin','admin','$shiro1$SHA-256$500000$/E/R5ARZcEBM9tITGFZaEQ==$ElPz7Xh0360LnKxaEtIK28ilE3y4rxAsTe+IBFm8QRk=','bXlwcml2YXRlc2FsdA==');
-- gl
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2105403','Estudiante','$shiro1$SHA-256$500000$d/FEHjBt/uhXMcYyXq+sGw==$LDOAtCKgelKODj3lh+jH/GNzpG50+la9tEdyCwhoI2A=','bXlwcml2YXRlc2FsdA==');
-- ds
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2107646','Estudiante','$shiro1$SHA-256$500000$B5cXJ+eXggsRuzTxZIswqA==$531aCUx6ErfA64zyU3WqZ9sv/Y1JyXmvfi43e8uVXM4=','bXlwcml2YXRlc2FsdA==');
-- laboratorista
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('laboratorista','Laboratorista','$shiro1$SHA-256$500000$3w3mjQnwRu/k8jEAKZtQIQ==$wXU2o3uplaCbyWtRg1RYP4tPBRuso7kqLIVV63di020=','bXlwcml2YXRlc2FsdA==');
-- estudiante
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('estudiante','Estudiante','$shiro1$SHA-256$500000$UYQIxz6nvGvo/9Kt/6VyVg==$vg/RwUwILPCFG49UMzLJX5WLAvwVZ85ZkInygMFwrpo=','bXlwcml2YXRlc2FsdA==');
-- profe
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('108763526','Profesor','','bXlwcml2YXRlc2FsdA==');
-- pr
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('109328347','Profesor','','bXlwcml2YXRlc2FsdA==');
-- pres
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('109328347','Estudiante','','bXlwcml2YXRlc2FsdA==');
-- es
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2107653','Estudiante','','bXlwcml2YXRlc2FsdA==');
-- es1
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2101234','Estudiante','','bXlwcml2YXRlc2FsdA==');

insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cable UTP','50','Cable','https://www.victronenergy.com.es/upload/cache/1423219110_upload_products_555_1000-301_2.png','2000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cautin de pistola 100w','10','Cautin','http://medios.plazavip.com/fotos/productos_sears1/original/427626.jpg','68000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cautin de lapiz 30w','20','Cautin','http://mexbusa.com/media/catalog/product/cache/4/image/700x700/9df78eab33525d08d6e5fb8d27136e95/t/s/ts032.jpg','25000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('destornillador de estrella','50','Destornillador','http://iessaenzburuaga.juntaextremadura.net/carrotools/imagenes/337.jpg','10000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('destornillador de pala','50','Destornillador','http://3.bp.blogspot.com/-vdk38XMtooo/UJntj4nXz3I/AAAAAAAAAAc/TfXo8c0tceI/s1600/dstornillador+pala.jpg','10000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cronómetro','300','Cronometro','http://www.gisiberica.com/cron%C3%B3metros/CRONOM8.jpg','10000');

insert into Modelos(vida_util,fotografia,valor_comercial,clase,descripcion,accesorios,nombre,marca) values('10','https://www.pce-instruments.com/espanol/slot/4/artimg/large/pce-instruments-generador-de-funciones-pce-sdg1010-358155_635055.jpg','1139601','Generador de señales','generador de funciones','software de edición','generador de señales 5MHz','GW Instek');
insert into Modelos(vida_util,fotografia,valor_comercial,clase,descripcion,accesorios,nombre,marca) values('10','http://www.ar.all.biz/img/ar/catalog/134366.png','1514998','Osciloscopio','Instrumento de visualización electrónico para la representación gráfica de señales eléctricas que pueden variar en el tiempo.','2 puntas de sonda, 1 software, 1 cable de red, instrucciones de uso','osciloscopio digital 50MHz','PCE');
insert into Modelos(vida_util,fotografia,valor_comercial,clase,descripcion,accesorios,nombre,marca) values('10','http://2.bp.blogspot.com/_FOyXBkqQWYA/SxGfTGtKtDI/AAAAAAAAAF4/w4DfEEf2I0I/s1600/380-multimetro-digital-con-funda.jpg','100000','Multimetro','es un instrumento eléctrico portátil para medir directamente magnitudes eléctricas activas como corrientes y potenciales (tensiones) y/o pasivas como resistencias, capacidades y otras','2 puntas de sonda','multimetro digital','Fluke');

insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('abd24b','1',TRUE,'prestamo 24 horas','generador de señales 5MHz',TRUE,'10');

INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-29 12:00:00','Electrix S.A','2015-04-29 12:00:00','abd24b','generador de señales 5MHz','njcas','2bj');

insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('kb132k','2',TRUE,'prestamo 24 horas','generador de señales 5MHz',FALSE,'7');

INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-02-11 12:00:00','Homecenter','2015-06-11 12:00:00','kb132k','generador de señales 5MHz','akjdsnajhd','fejfnj');

insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('asd123','3',FALSE,'en reparacion','multimetro Digital',TRUE,'6');

INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2016-01-01 12:00:00','Alkosto','2016-03-01 12:00:00','asd123','multimetro Digital','dey28','dasjhb');


insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('d23j3s','4',TRUE,'prestamo diario','multimetro Digital',TRUE,'9');

INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2014-01-01 12:00:00','Torniexpress','2015-01-01 12:00:00','d23j3s','multimetro Digital','iunwc0q','dcowq9');

insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('klñlkopk2421k','5',TRUE,'prestamo diario','osciloscopio digital 50MHz',FALSE,'10');

INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2013-01-01 12:00:00','Construcctor','2014-01-01 12:00:00','klñlkopk2421k','osciloscopio digital 50MHz','98cas','cnasoi8');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('sdfsadfsafew','6',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'9');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2010-01-01 12:00:00','Construcctor','2011-05-01 12:00:00','sdfsadfsafew','osciloscopio digital 50MHz','97vgre','87aasd');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('vasldvnf','7',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'7');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2013-01-01 12:00:00','Electrix S.A','2014-05-01 12:00:00','vasldvnf','osciloscopio digital 50MHz','v7asnc','87dc');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('zcbkkihg','8',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'3');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2016-01-01 12:00:00','Electrix S.A','2016-02-01 12:00:00','zcbkkihg','osciloscopio digital 50MHz','gf54ns','8vdbsv');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('asdwszsx','9',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'1');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-05-01 12:00:00','Electrix S.A','2015-10-01 12:00:00','asdwszsx','osciloscopio digital 50MHz','34asfhc','45gh');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('mdjisj','10',TRUE,'en almacen','multimetro Digital',TRUE,'0');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-05-01 12:00:00','Electrix S.A','2015-10-01 12:00:00','mdjisj','multimetro Digital','32ghfma','v8a6s');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('mdasd','11',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-05-01 12:00:00','Electrix S.A','2015-10-01 12:00:00','mdasd','multimetro Digital','9dfs0','7sdaf6');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('mdfjop','12',TRUE,'en almacen','multimetro Digital',TRUE,'9');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-05-01 12:00:00','Electrix S.A','2015-10-01 12:00:00','mdfjop','multimetro Digital','n43sfg','0sd5fasd');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('oiuhy','13',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-05-01 12:00:00','Constructor','2015-10-01 12:00:00','oiuhy','multimetro Digital','ad4f8','54gfs');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('cndus','14',TRUE,'en almacen','generador de señales 5MHz',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2016-01-01 12:00:00','Constructor','2016-01-05 12:00:00','cndus','generador de señales 5MHz','bfd6va','s65gfda');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('mdsai','15',TRUE,'en almacen','generador de señales 5MHz',TRUE,'8');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2016-01-01 12:00:00','Constructor','2016-01-05 12:00:00','mdsai','generador de señales 5MHz','cvqmow','7n5gsf');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('cdsdwasi','16',TRUE,'en almacen','generador de señales 5MHz',TRUE,'5');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2013-01-01 12:00:00','Alkosto','2014-01-05 12:00:00','cdsdwasi','generador de señales 5MHz','morqnqio13','m2d345s67');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('casdfesi','17',TRUE,'en almacen','generador de señales 5MHz',TRUE,'4');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2013-01-01 12:00:00','Alkosto','2014-01-05 12:00:00','casdfesi','generador de señales 5MHz','23jnh45fdc67s','7g6nsf476sfv');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('asgdasb','18',TRUE,'en almacen','generador de señales 5MHz',TRUE,'2');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2014-01-01 12:00:00','Alkosto','2014-10-04 12:00:00','asgdasb','generador de señales 5MHz','87DF5SAC','m7shg5gb4');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('hcgvdjfeha','19',TRUE,'en almacen','generador de señales 5MHz',TRUE,'1');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2014-01-01 12:00:00','Alkosto','2014-10-04 12:00:00','hcgvdjfeha','generador de señales 5MHz','9sd8fg7h6j5','09876mh');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('niusdhfno','20',TRUE,'en almacen','generador de señales 5MHz',TRUE,'9');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2014-01-01 12:00:00','Alkosto','2014-10-04 12:00:00','niusdhfno','generador de señales 5MHz','57fdjh254','o37641bfn');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('ausonassa','21',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2013-01-01 12:00:00','Electrix S.A','2014-10-04 12:00:00','ausonassa','multimetro Digital','4nsg56g','m456svf');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('msaiudag','22',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','msaiudag','multimetro Digital','v76sdaf','avd6fs7');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('afghjkpok','23',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','afghjkpok','multimetro Digital','sdck54bv3','s7adf623b');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('qazwsxrf','24',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','qazwsxrf','multimetro Digital','n9e83nf','cd7g2d');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('asdjkasdas','25',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','asdjkasdas','multimetro Digital','ch2u39','cdqy8ehfnc');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('nciasds','26',TRUE,'en almacen','multimetro Digital',TRUE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','nciasds','multimetro Digital','7iujhnfecd','ccudeq9yf93rf');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('asdhbsay','27',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'5');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','asdhbsay','osciloscopio digital 50MHz','cne9deuw','cn7w6d9qyc');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('aasdweads','28',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'0');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','asdhbsay','osciloscopio digital 50MHz','23dcwdf','cnwuc8ec');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('nsaihhadg','29',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'9');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','nsaihhadg','osciloscopio digital 50MHz','n93dd223','casdcsce2');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('buhsncafdby','30',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'8');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','buhsncafdby','osciloscopio digital 50MHz','n083rnvd','23rewse23');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('msiaudy','31',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'10');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','msiaudy','osciloscopio digital 50MHz','nwe98ci8wu8','ocnwi823r');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,vida_restante) values('basdwscmo','32',TRUE,'en almacen','osciloscopio digital 50MHz',FALSE,'0');
INSERT INTO Informacion_Compra(fecha_compra,proveedor,fecha_garantia,Equipos_Complejos_serial,Equipos_Complejos_modelo,codigo_orden_compra,codigo_activo)
	VALUES ('2015-03-01 12:00:00','Electrix S.A','2014-03-04 12:00:00','basdwscmo','osciloscopio digital 50MHz','87hbdchwe7','dvanosiva8fu');

insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-04-29 12:00:00','2105684','2016-04-30 12:00:00',NULL,'prestamo 24 horas');
insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-04-29 07:00:00','2105533','2016-04-29 19:00:00',NULL,'prestamo diario');
insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-04-27 12:00:00','2105684','2016-04-28 12:00:00','2016-04-28 11:00:00','prestamo 24 horas');
insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-05-08 12:00:00','2107646','2016-05-09 12:00:00',NULL,'prestamo 24 horas');

insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo,estado) values('2016-04-29 12:00:00','2105684','abd24b','generador de señales 5MHz','Activo');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo,estado) values('2016-04-29 12:00:00','2105684','kb132k','generador de señales 5MHz','Activo');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo,estado) values('2016-04-29 07:00:00','2105533','d23j3s','multimetro Digital','Activo');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo,estado) values('2016-04-29 07:00:00','2105533','klñlkopk2421k','osciloscopio digital 50MHz','Activo');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo,estado) values ('2016-04-27 12:00:00','2105684','klñlkopk2421k','osciloscopio digital 50MHz','No activo');

insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('cable UTP','30','0','2016-04-29 12:00:00','2105684');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('destornillador de estrella','30','7','2016-04-29 12:00:00','2105684');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('cable UTP','10','2','2016-04-29 07:00:00','2105533');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('destornillador de estrella','15','3','2016-04-29 07:00:00','2105533');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values ('cronómetro','27','2','2016-05-08 12:00:00','2107646');
