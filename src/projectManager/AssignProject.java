package projectManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;


public class AssignProject extends SelectorComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void assignProject(String name){
		int p_id = 0,d_id = 0;
		System.out.println("In method");
		
		System.out.println("2");
		Connection conn = null;
		Statement stmt1,stmt2;
		ResultSet rs1,rs2;
		PreparedStatement stmt3;
		System.out.println("3");
		try{
			System.out.println("4");
			System.out.println("5");
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/BTS?user=root&password=password");
			System.out.println("db connected");
			stmt1 = conn.createStatement();
			System.out.println();
			System.out.println("6");
			rs1 = stmt1.executeQuery("select p_id from project where name=");
			System.out.println("1st query");
			while(rs1.next()){
				p_id = rs1.getInt(1);
			}
			System.out.println("7");
			
				System.out.println("8");
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery("select u_id from user where name='"+name+"' and desig='Developer'");
				while(rs2.next()){
					d_id = rs2.getInt(1);
				}
				System.out.println("2nd query");
				stmt3 = conn.prepareStatement("insert into assign_project(p_id,d_id) values(?,?)");
				stmt3.setInt(1, p_id);
				stmt3.setInt(2, d_id);
				stmt3.executeUpdate();
				Executions.sendRedirect("view_proj.zul");
			
			conn.close();
			
		}
		catch(ClassNotFoundException e){System.out.println("Class not found");}
		catch(Exception e){System.out.println("In catch");}
	}
	
}
