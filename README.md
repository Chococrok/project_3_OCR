Third project for the OpenClassRooms/Capgemini training.

To run this project you need a Apache Tomcat 9 server, PostgreSQL 9.x with a dbuser with a pasword 'dbuser' owning a "climbing" database. You also need maven and java 1.8.

You then need to run the script to create the tables and the script to add demo-datas: 

You can then run the project by simply entering in a terminal: $ mvn tomcat7:run
If this one doesn't do the trick use: $ mvn tomcat7:run -pl :web -am

You can also configure a maven run in Eclipse with the goal: tomcat7:run

The project will be then running on: http://localhost:8080/climbing

