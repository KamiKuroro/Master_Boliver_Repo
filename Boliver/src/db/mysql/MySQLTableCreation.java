package db.mysql;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// Step 1 Connect to MySQL.
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
			
			if (conn == null) {
				return;
			}
			// Step 2 Drop tables in case they exist.
			Statement statement = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS OrderHistory";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS CurrentOrder";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS Robot";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS RobotType";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS Base";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS Users";
			statement.executeUpdate(sql);
			
			// Step 3 Create new tables
			sql = "CREATE TABLE Users ("
					+ "user_id VARCHAR(255) NOT NULL UNIQUE,"
					+ "username VARCHAR(255) NOT NULL UNIQUE,"
					+ "pwd VARCHAR(255) NOT NULL,"
					+ "email VARCHAR(255) NOT NULL,"
					+ "fname VARCHAR(255) NOT NULL,"
					+ "lname VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY(user_id)"
					+ ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE RobotType ("
					+ "type_id VARCHAR(255) NOT NULL,"
					+ "type VARCHAR(255) NOT NULL UNIQUE,"
					+ "speed VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (type_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE Base ("
					+ "base_id VARCHAR(255) NOT NULL,"
					+ "address VARCHAR(255) NOT NULL,"
					+ "lat VARCHAR(255) NOT NULL,"
					+ "lon VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (base_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE Robot ("
					+ "robot_id VARCHAR(255) NOT NULL,"
					+ "type_id VARCHAR(255) NOT NULL,"
					+ "base_id VARCHAR(255),"
					+ "robotStatus VARCHAR(255) NOT NULL,"
					+ "curLocation VARCHAR(255) NOT NULL,"
					+ "power VARCHAR(255) NOT NULL,"
					+ "destination VARCHAR(255),"
					+ "PRIMARY KEY (robot_id),"
					+ "FOREIGN KEY (base_id) REFERENCES Base(base_id),"
					+ "FOREIGN KEY (type_id) REFERENCES RobotType(type_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE CurrentOrder ("
					+ "order_id VARCHAR(255) NOT NULL,"
					+ "robot_id VARCHAR(255) NOT NULL,"
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "order_Status VARCHAR(255) NOT NULL,"
					+ "origin VARCHAR(255) NOT NULL,"
					+ "destination VARCHAR(255) NOT NULL,"
					+ "e_arrival VARCHAR(255) NOT NULL,"
					+ "create_time VARCHAR(255) NOT NULL,"
					+ "cost VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (order_id),"
					+ "FOREIGN KEY (robot_id) REFERENCES Robot(robot_id),"
					+ "FOREIGN KEY (user_id) REFERENCES Users(user_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderHistory ("
					+ "order_id VARCHAR(255) NOT NULL,"
					+ "robot_id VARCHAR(255) NOT NULL,"
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "order_Status VARCHAR(255) NOT NULL,"
					+ "origin VARCHAR(255) NOT NULL,"
					+ "destination VARCHAR(255) NOT NULL,"
					+ "e_arrival VARCHAR(255) NOT NULL,"
					+ "a_arrival VARCHAR(255) NOT NULL,"
					+ "create_time VARCHAR(255) NOT NULL,"
					+ "cost VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (order_id),"
					+ "FOREIGN KEY (robot_id) REFERENCES Robot(robot_id),"
					+ "FOREIGN KEY (user_id) REFERENCES Users(user_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			
			// Step 4: insert fake data
			   // INSERT INTO Users
			sql = "INSERT INTO Users VALUES('1','neko','neko','neko@email.com','ko','neko')";
			statement.execute(sql);
			sql = "INSERT INTO Users VALUES('2','inu','inu','inu@email.com','ko','inu')";
			statement.execute(sql);
			sql = "INSERT INTO Users VALUES('3','usagi','usagi','usagi@email.com','ko','usagi')";
			statement.execute(sql);
			sql = "INSERT INTO Users VALUES('4','sakana','sakana','sakana@email.com','yaki','sakana')";
			statement.execute(sql);
			sql = "INSERT INTO Users VALUES('5','hito','hito','hito@email.com','hito','bito')";
			statement.execute(sql);
			
				// INSERT INTO RobotType
			sql = "INSERT INTO RobotType VALUES('1','ground','40')";
			statement.execute(sql);
			sql = "INSERT INTO RobotType VALUES('2','drone','60')";
			statement.execute(sql);
			
		    // INSERT INTO Base
			sql = "INSERT INTO Base VALUES('-1','not in base','-1','-1')";
			statement.execute(sql);
			sql = "INSERT INTO Base VALUES('1','1935 32nd Ave, San Francisco, CA 94116','37.750990','-122.490540')";
			statement.execute(sql);
			sql = "INSERT INTO Base VALUES('2','75 Geary Blvd, San Francisco, CA 94118','37.780550','-122.476180')";
			statement.execute(sql);
			sql = "INSERT INTO Base VALUES('3','448 Cortland Ave, San Francisco, CA 94110','37.738890','-122.416720')";
			statement.execute(sql);	
			
				// INSERT INTO Robot
			sql = "INSERT INTO Robot VALUES('1','1','1','available','1935 32nd Ave, San Francisco, CA 94116','100','')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('2','2','2','available','75 Geary Blvd, San Francisco, CA 94118','100','')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('3','1','3','available','448 Cortland Ave, San Francisco, CA 94110','100','')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('4','1','-1','retrieving','3639 18th St, San Francisco, CA 94110','90','375 Valencia St, San Francisco, CA 94103')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('5','1','2','available','1935 32nd Ave, San Francisco, CA 94116','100','')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('6','2','-1','returning','3369 Mission St, San Francisco, CA 94110','40','448 Cortland Ave, San Francisco, CA 94110')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('7','1','-1','delivering','3639 Taraval, San Francisco, CA 94116','80','3132 Vicente St, San Francisco, CA 94116')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('8','2','-1','delivering','2450 Sutter St, San Francisco, CA 94115','80','1050 Van Ness Ave, San Francisco, CA 94109')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('9','1','-1','retrieving','555 Tompkins Ave, San Francisco, CA 94110','85','299 Bayshore Blvd, San Francisco, CA 94124')";
			statement.execute(sql);
			sql = "INSERT INTO Robot VALUES('10','2','-1','delivering','3138 Noriega St, San Francisco, CA 94122','60','1916 Irving St, San Francisco, CA 94122')";
			statement.execute(sql);
			
			    // INSERT INTO CurrentOrder
			sql = "INSERT INTO CurrentOrder VALUES('1','4','1','1','3639 18th St, San Francisco, CA 94110','375 Valencia St, San Francisco, CA 94103','5:00pm 3/25/2019','20190305123521','12')";
			statement.execute(sql);
			sql = "INSERT INTO CurrentOrder VALUES('2','7','2','1','3639 Taraval, San Francisco, CA 94116','3132 Vicente St, San Francisco, CA 94116','4:00pm 3/24/2019','20190305123521','12')";
			statement.execute(sql);
			sql = "INSERT INTO CurrentOrder VALUES('3','8','3','1','2450 Sutter St, San Francisco, CA 94115','1050 Van Ness Ave, San Francisco, CA 94109','1:00pm 3/23/2019','20190305123521','12')";
			statement.execute(sql);		
			sql = "INSERT INTO CurrentOrder VALUES('4','9','1','1','555 Tompkins Ave, San Francisco, CA 94110','299 Bayshore Blvd, San Francisco, CA 94124','2:00pm 3/26/2019','20190305123521','12')";
			statement.execute(sql);
			sql = "INSERT INTO CurrentOrder VALUES('5','10','2','1','3138 Noriega St, San Francisco, CA 94122','1916 Irving St, San Francisco, CA 94122','11:00am 3/25/2019','20190305123521','12')";
			statement.execute(sql);
			
			//0-arrived; 1-waiting; 2-sending; 3-canceled;
			    // INSERT INTO OrderHistory
			sql = "INSERT INTO OrderHistory VALUES('6','2','5','0','3369 Mission St, San Francisco, CA 94110','448 Cortland Ave, San Francisco, CA 94110','11:30am 3/17/2019','12:00pm 3/17/2019','20190305123521','12')";
			statement.execute(sql);
			
		
			conn.close();
			System.out.println("Import done successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
