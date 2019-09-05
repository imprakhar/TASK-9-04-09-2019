package wp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
	int eno;
	String ename;
	double salary;
	String desg;
	String dept;


	static Scanner sc = new Scanner(System.in);

	public void addEmp(Connection con) throws Exception {

		System.out.println("Enter Employee Number");
		eno = sc.nextInt();
		System.out.println("Enter Employee Name");
		ename = sc.next();
		System.out.println("Enter Employee Salary");
		salary = sc.nextDouble();
		System.out.println("Enter Employee Designation");
		desg = sc.next();
		System.out.println("Enter Employee Department (IT,HR,MKT,SALES)");
		dept = sc.next();
		String sql="insert into Emp values(?,?,?,?,?)";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setInt(1, eno);
		ps.setString(2, ename);
		ps.setDouble(3, salary);
		ps.setString(4, desg);
		ps.setString(5, dept);
		ps.executeUpdate();
		ps.executeBatch();
		System.out.println("Data Entered for: "+ename);
	}

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = DriverManager.getConnection(url, "system", "abcd1234");
		outer: while (true) {
			System.out.println(
					"\n1. Add Emp \n 2.View All Emp \n 3.Remove Emp \n 4. Clear Data \n 5. Change Sal \n 6.Search Emp \n 7. View dept Wise \n 8. Exit");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				Employee e = new Employee();
				e.addEmp(con);
				break;
			case 2:
				String sql="Select * from emp";
				Statement stmt = con.createStatement();
				ResultSet rs =stmt.executeQuery(sql);
				while(rs.next())
				{
					int x = rs.getInt(1);
					String str = rs.getString(2);
					double sal=rs.getDouble(3);
					String des=rs.getString(4);
					String dep=rs.getString(5);
					System.out.println("Employee Number: "+x+" Employee Name: "+str+" Salary: "+sal+" Designation: "+des+" Department: "+dep);
				}
				break;
			case 3:
				System.out.println("Enter Employee number to be deleted");
				int x=sc.nextInt();
				String sql1="Delete from emp where eno= "+x;
				Statement stmt1 = con.createStatement();
				stmt1.executeUpdate(sql1);
				System.out.println("Employee "+x +" Detelted");
				break;
			case 4:
				String sql2="delete from emp";
				Statement stmt2 = con.createStatement();
				stmt2.executeUpdate(sql2);
				System.out.println("Data Cleared");
				break;
			case 5:
				System.out.println("Enter Employee Number whose Salary is to be Changed");
				int en= sc.nextInt();
				System.out.println("Enter new Salary");
				double newSal= sc.nextDouble();
				CallableStatement cs= con.prepareCall("{call updateSalary(?,?)}");
				cs.setInt(1, en);
				cs.setDouble(2, newSal);
				cs.executeUpdate();
				System.out.println("Salary Updated");
				break;
			case 6:
				System.out.println("Enter Employee Number to be Searched");
				int en1= sc.nextInt();
				String sql4="Select * from emp where eno="+en1;
				Statement stmt4 = con.createStatement();
				ResultSet rs1 =stmt4.executeQuery(sql4);
				while(rs1.next())
				{
					int x1 = rs1.getInt(1);
					String str = rs1.getString(2);
					double sal=rs1.getDouble(3);
					String des=rs1.getString(4);
					String dep=rs1.getString(5);
					System.out.println("Employee Number: "+x1+" Employee Name: "+str+" Salary: "+sal+" Designation: "+des+" Department: "+dep);
				}
				break;
			case 7:
				System.out.println("Department wise Employee List");
				String sql5="Select ename,dept from emp group by ename,dept";
				Statement stmt5 = con.createStatement();
				ResultSet rs2 =stmt5.executeQuery(sql5);
				while(rs2.next())
				{
					String str = rs2.getString(1);
					String dep=rs2.getString(2);
					System.out.println(" Employee Name: "+str+"  Department: "+dep);
				}
				break;

			case 8:
				break outer;
				default:
					System.out.println("Inavlid Input");
			}

		}
	}

}
