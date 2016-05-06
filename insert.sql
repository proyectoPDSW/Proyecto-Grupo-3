insert into Departamentos(departamento) values('Sistemas');
insert into Departamentos(departamento) values('Electrónica');

insert into Rol(rol) values ('Laboratorista');
insert into Rol(rol) values ('Estudiante');
insert into Rol(rol) values ('Profesor');

insert into Personas (carne,nombre,apellido,email,telefono) values ('2105684','Julian','Devia','julian.devia@mail.escuelaing.edu.co','3115086950');
insert into Personas (carne,nombre,apellido,email,telefono) values ('30285974','Pepito','Perez','pepito.perez@mail.escuelaing.edu.co','3105650732');
insert into Personas (carne,nombre,apellido,email,telefono) values ('2105533','Hugo','Alvarez','hugo.alvarez@mail.escuelaing.edu.co','0316706243');
insert into Personas (carne,nombre,apellido,email,telefono) values ('1020816982','Carmenzo','Rodriguez','carmenzo.rodriguez@mail.escuelaing.edu.co','3129876543');

insert into Departamento_persona(departamento,persona) values ('Sistemas','2105684');
insert into Departamento_persona(departamento,persona) values ('Sistemas','2105533');
insert into Departamento_persona(departamento,persona) values ('Sistemas','30285974');
insert into Departamento_persona(departamento,persona) values ('Electrónica','30285974');
insert into Departamento_persona(departamento,persona) values ('Electrónica','1020816982');

-- jd
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2105684','Estudiante','100968680','abc');
-- pp
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('30285974','Profesor','106819634','123');
-- ha
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('2105533','Estudiante','98983608','012');
-- cr
insert into Rol_Persona(Personas_carne,Rol_rol,contrasena,sal) values ('1020816982','Laboratorista','94935969','ppp');

insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cable UTP','50','Cable','https://www.victronenergy.com.es/upload/cache/1423219110_upload_products_555_1000-301_2.png','2000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cautin de pistola 100w','10','Cautin','http://medios.plazavip.com/fotos/productos_sears1/original/427626.jpg','68000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('cautin de lapiz 30w','20','Cautin','http://mexbusa.com/media/catalog/product/cache/4/image/700x700/9df78eab33525d08d6e5fb8d27136e95/t/s/ts032.jpg','25000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('destornillador de estrella','50','Destornillador','http://iessaenzburuaga.juntaextremadura.net/carrotools/imagenes/337.jpg','10000');
insert into Equipos_Sencillos(nombre,cantidad_total,clase,fotografia,valor_comercial) values('destornillador de pala','50','Destornillador','http://3.bp.blogspot.com/-vdk38XMtooo/UJntj4nXz3I/AAAAAAAAAAc/TfXo8c0tceI/s1600/dstornillador+pala.jpg','10000');

insert into Modelos(vida_util,fotografia,valor_comercial,clase,descripcion,accesorios,nombre) values('10','https://www.pce-instruments.com/espanol/slot/4/artimg/large/pce-instruments-generador-de-funciones-pce-sdg1010-358155_635055.jpg','1139601','Generador de señales','generador de funciones','software de edición','generador de señales 5MHz');
insert into Modelos(vida_util,fotografia,valor_comercial,clase,descripcion,accesorios,nombre) values('10','http://www.ar.all.biz/img/ar/catalog/134366.png','1514998','Osciloscopio','Instrumento de visualización electrónico para la representación gráfica de señales eléctricas que pueden variar en el tiempo.','2 puntas de sonda, 1 software, 1 cable de red, instrucciones de uso','osciloscopio digital 50MHz');
insert into Modelos(vida_util,fotografia,valor_comercial,clase,descripcion,accesorios,nombre) values('10','http://2.bp.blogspot.com/_FOyXBkqQWYA/SxGfTGtKtDI/AAAAAAAAAF4/w4DfEEf2I0I/s1600/380-multimetro-digital-con-funda.jpg','100000','Multimetro','es un instrumento eléctrico portátil para medir directamente magnitudes eléctricas activas como corrientes y potenciales (tensiones) y/o pasivas como resistencias, capacidades y otras','2 puntas de sonda','multimetro digital');

insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,marca) values('abd24b','1',TRUE,'prestamo 24 horas','generador de señales 5MHz',TRUE,'GW Instek');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,marca) values('kb132k','2',TRUE,'prestamo 24 horas','generador de señales 5MHz',FALSE,'BK Precision');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,marca) values('asd123','3',FALSE,'en reparacion','multimetro Digital',TRUE,'Fluke');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,marca) values('d23j3s','4',TRUE,'prestamo diario','multimetro Digital',TRUE,'Fluke');
insert into Equipos_Complejos(serial,num_placa,disponibilidad,estado,modelo,asegurado,marca) values('klñlkopk2421k','5',TRUE,'prestamo diario','osciloscopio digital 50MHz',FALSE,'PCE');

insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-04-29 12:00:00','2105684','2016-04-30 12:00:00',NULL,'prestamo 24 horas');
insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-04-29 07:00:00','2105533','2016-04-29 19:00:00',NULL,'prestamo diario');
insert into Prestamos(fecha_inicio,persona,fecha_fin_estimada,fecha_fin_real,tipo_prestamo) values('2016-04-27 12:00:00','2105684','2016-04-28 12:00:00','2016-04-28 11:00:00','prestamo 24 horas');

insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo) values('2016-04-29 12:00:00','2105684','abd24b','generador de señales 5MHz');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo) values('2016-04-29 12:00:00','2105684','kb132k','generador de señales 5MHz');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo) values('2016-04-29 07:00:00','2105533','d23j3s','multimetro Digital');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo) values('2016-04-29 07:00:00','2105533','klñlkopk2421k','osciloscopio digital 50MHz');
insert into Equipo_prestamo_complejo (Prestamos_fecha_inicio,Prestamos_persona,Equipos_Complejos_serial,Equipos_Complejos_modelo) values ('2016-04-27 12:00:00','2105684','klñlkopk2421k','osciloscopio digital 50MHz');

insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('cable UTP','30','0','2016-04-29 12:00:00','2105684');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('destornillador de estrella','30','7','2016-04-29 12:00:00','2105684');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('cable UTP','10','2','2016-04-29 07:00:00','2105533');
insert into Equipo_prestamo_sencillo (equipo,cantidad,cantidad_devuelta,Prestamos_fecha_inicio,Prestamos_persona) values('destornillador de estrella','15','3','2016-04-29 07:00:00','2105533');
