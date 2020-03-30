### JNDISpringBootTom8

Steps for deploying:-

### generate the war File
   1. go to project path
   2. mvn clean package -DskipTests
   3. copy war file from target/SpringBootDataSourceConfigJNDILookUpEmbTomcat-0.0.1-SNAPSHOT.war to webapps folder
 
 ### Configure the datasource
    1. add the below configuration in tomcat conf/server.xml inside <GlobalNamingResource> tag.
      <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver"
                           maxActive="20"
                           maxIdle="0"
                           maxWait="10000"
                           name="jdbc/deepeshuniyal1"
                           password=""
                           username="root"
                           type="javax.sql.DataSource"
                           url="jdbc:mysql://localhost:3306/test1" />
        <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver"
                           maxActive="20"
                           maxIdle="0"
                           maxWait="10000"
                           name="jdbc/deepeshuniyal2"
                           password=""
                           username="root"
                           type="javax.sql.DataSource"
                           url="jdbc:mysql://localhost:3306/test2" />
  
    2. add the below configuration conf/context.xml inside <Context> tag.
    
        <ResourceLink auth="Container" name="jdbc/deepeshuniyal1" global="jdbc/deepeshuniyal1" type="javax.sql.DataSource" />
        <ResourceLink auth="Container" name="jdbc/deepeshuniyal2" global="jdbc/deepeshuniyal2" type="javax.sql.DataSource" />
    

#### Database configuration (Mysql)
  run below queries:-
  
    create database test1;
    use test1;
    create table customers(Cust_id integer, cust_Name varchar(255), country varchar(255));
    insert into customers values(1,'deepesh', 'Gurgaon');
    insert into customers values(2,'deepesh', 'Gurgaon');
    create database test2;
    use test2;
    create table customers(Cust_id integer, cust_Name varchar(255), country varchar(255));
    insert into customers values(3,'deepesh', 'Gurgaon');
    insert into customers values(4,'deepesh', 'Gurgaon');
  
  #### Start the websever and check the data using below url:-
    1. http://localhost:8080/SpringBootDataSourceConfigJNDILookUpEmbTomcat-0.0.1-SNAPSHOT/get-cust-info1
    2. http://localhost:8080/SpringBootDataSourceConfigJNDILookUpEmbTomcat-0.0.1-SNAPSHOT/get-cust-info2
