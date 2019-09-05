package wp.jdbc;
import java.sql.*;
public class JDBCDemo {

	public static void main(String[] args) throws Exception {
		//Load Driver 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Load Driver");
		//Create Connection
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		Connection con= DriverManager.getConnection(url,"system","abcd1234");
		System.out.println("Connection Created");
		//
		//updateData(con);
		//alterTable(con);
		deleteData(con);
		con.close();
	}
	public static void createTable(Connection con) throws Exception
	{
		Statement stmt = con.createStatement();
		String sql="create table temp (id number(10) primary key, name varchar2(40))";
		stmt.executeUpdate(sql);
		System.out.println("Table Created");
	}
	
	public static void insertData(Connection con) throws Exception
	{
		Statement stmt = con.createStatement();
		String sql= "insert into temp values(1, 'Prakhar')";
		stmt.executeUpdate(sql);
		System.out.println("Data inserted");
	}
	
	public static void deleteData(Connection con) throws Exception
	{
		Statement stmt = con.createStatement();
		String sql= "delete from temp where id=1";
		stmt.executeUpdate(sql);
		System.out.println("Data deleted");
	}
	
	public static void updateData(Connection con) throws Exception
	{
		Statement stmt = con.createStatement();
		String sql= "update temp set name='Ayushi' where id=1";
		stmt.executeUpdate(sql);
		System.out.println("Data updated");
	}
	
	public static void alterTable(Connection con) throws Exception
	{
		Statement stmt = con.createStatement();
		String sql= "alter table temp add sal number";
		stmt.executeUpdate(sql);
		System.out.println("Table altered");
	}
}
