<em> Proyecto desafio literalua </em>
<p>lo primero que se debe haver es instalar la base de datos postgresql.</p>
<p>una vez instalada la BD se debe crear la base de datos(literalura),despues de crear la base de datos, se debe proceder a crear las siguientes variables de ambiente</p>
<img width="437" alt="Captura de pantalla 2024-10-23 163157" src="https://github.com/user-attachments/assets/56d29688-3d79-46ea-95a6-4ec3a429ae8c">
<h1>spring.datasource.url=jdbc:postgresql://${DB_HOST}/literalura</h1>
<h1>spring.datasource.username=${DB_USER}</h1>
<h1>spring.datasource.password=${DB_PASSWORD}</h1>
<p>estos datos de configuracion estan en el siguiente archivo (desafio_literalura/src/main/resources/application.properties)</p>

