#Third project for the OpenClassRooms/Capgemini training.
<br/>

##requirement
To run this project you need a Apache Tomcat 9 server, PostgreSQL 9.x with a dbuser with a pasword 'dbuser' owning a "climbing" database. You also need maven and java 1.8.

##set-up
###maven/tomcat
*In $CATALINA_HOME\conf\tomcat-users:
	`<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="admin" password="password" roles="manager-gui"/>`
*In  ${maven.home}/conf/settings.xml (or ${user.home}/.m2/settings.xml):
	`<server>
	    <id>tomcat</id>
	    <username>admin</username>
	    <password>password</password>
	</server>`
You then need to run the script to create the tables and the script to add demo-datas: 
	*~$ psql -f /home/utilisateur/dev/OCR/project_3/db_create_script.sql -U dbuser climbing
	*~$ psql -f /home/utilisateur/dev/OCR/project_3/db_add_entities.sql -U dbuser climbing
###running the project:
*the first time:
	`mvn clean install tomcat7:deploy`
*then: 
	`mvn clean install tomcat7:redeploy`
*The project will be then running on: http://localhost:8080/climbing/home

