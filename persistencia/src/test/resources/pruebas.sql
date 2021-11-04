insert into ciudad values (123,"Armenia");
insert into ciudad values (3312,"Buga");
insert into ciudad values (0987,"Cali");

insert into usuario values ("456", "santiago@12", "santiago Mejia", "usuario04", 123);
insert  into usuario values("777", "michell@123", "carlos el pibe", "carlitos13", 123);
insert into usuario values ("888", "julio39@12", "julio jaramillo", "julito12", 3312);
insert into usuario values ("459", "santiago@12870", "andres santiago Mejia", "usuarioRE4", 123);

insert into usuario_telefono values("456", "7476239", "fijo");
insert into usuario_telefono values("456", "312 714 9268", "personal");


insert into administrador values ("119", "Santiago Mejia", "andress.mejiaf@", "119289");
insert into administrador values ("229", "Jacobo Sanchez", "jac@gmail", "1233");
insert into administrador values ("339", "Alexis Salazar", "alexS.socha@", "55678");

insert into chat values ("100", "456");
insert into chat values ("200", "777");
insert into chat values ("300", "888");

insert into mensaje values ("999", "carlos Fernando", "2000/10/12", "Hola que tal","100");
insert into mensaje values ("787", "Julian santiago", "2020/10/13", "como esta?","200");
insert into mensaje values ("090", "karol Natalia", "2020/10/14", "buenas tarde","300");


insert into producto values ("9090", "consola ultimo modelo", 0.10,"2021/10/9","Xbox one",950.000, 30, 123, "777");
insert into producto values ("22324", "moto ultimo modelo", 0.13,"2021/10/4","yamaha 650",800.000, 2,3312, "888");
insert into producto values ("4546", "nevera ultimo modelo", 0.30,"2021/09/1","Nevera",550.000, 15, 123, "456");

insert into subasta values ("8787",  "2000/11/12", "9090");
insert into subasta values ("9477",  "2021/09/22", "22324");
insert into subasta values ("7480", "2021/08/19", "4546");

insert into comentario values ("934",12,"2021/04/29","Buen producto", "Producto bien calificado", "9090", "456");
insert into comentario values ("936",10,"2021/04/29","Mal producto", "Producto mal calificado", "22324", "777");
insert into comentario values ("937",12,"2021/04/29","Perfecto funcionamiento", "Producto bien calificado", "9090", "888");


insert into compra values ("020", "2021/11/19", "tarjeta debito", "456");
insert into compra values ("021", "2020/02/29", "tarjeta credito", "777");
insert into compra values ("032", "2019/10/25", "efectivo", "888");

insert into detalle_compra values ("372", 89.000, 2, "020", "9090");
insert into detalle_compra values ("373", 90.000,12, "021", "22324");
insert into detalle_compra values ("984", 28.000,10, "032", "4546");


insert into categoria values ("3672", "Electrodomesticos");
insert into categoria values ("3462", "Area de juegos");
insert into categoria values ("3214","Automoviles");

insert into subasta_usuario values("4567","2020/02/29", 500000, "8787", "888" );
insert into subasta_usuario values("4568","2020/02/27", 600000, "9477", "456" );
insert into subasta_usuario values("4569","2020/02/22", 300000, "7480", "777" );