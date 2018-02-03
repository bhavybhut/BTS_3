package tester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class DbConnectivity extends SelectorComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox proj;
	@Wire
	private Combobox priority;
	@Wire
	private Textbox summary;
	@Wire
	private Textbox description;
	@Wire
	private Combobox developer;
	private byte[] img;

	public void storeBytes(byte[] img){
		this.img = img;
	}
	
	
	@Listen("onClick = button")
	public void insert(){
		int p_id = 0,d_id = 0;
		Connection conn;
		Statement stmt1,stmt2;
		PreparedStatement stmt3;
		ResultSet rs1,rs2;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/BTS?user=root&password=password");
			stmt1 = conn.createStatement();
			rs1 = stmt1.executeQuery("select p_id from project where name='"+proj.getSelectedItem().getValue().toString()+"'");
			while(rs1.next()){
				p_id = rs1.getInt("p_id");
			}
			rs1.close();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery("select u_id from user where name='"+developer.getSelectedItem().getValue().toString()+"'");
			while(rs2.next()){
			d_id = rs2.getInt(1);}
			rs2.close();
			stmt3 = conn.prepareStatement("insert into bug(p_id,priority,summary,description,d_id,img) values (?,?,?,?,?,?)");
			stmt3.setInt(1, p_id);
			stmt3.setString(2, String.valueOf(priority.getSelectedItem().getValue()));
			stmt3.setString(3, summary.getValue());
			stmt3.setString(4, description.getValue());
			stmt3.setInt(5, d_id);
			stmt3.setBytes(6, img);
			stmt3.executeUpdate();
			conn.close();
			Executions.sendRedirect("bug_report.zul");
		}catch(Exception e){ System.out.println("In catch"); }
	}
	
	public void closeBug(Bug bug){
		Connection conn;
		Statement stmt;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/BTS?user=root&password=password");
			stmt = conn.createStatement();
			stmt.execute("update bug set status='Closed' where b_id="+bug.getB_id());
			conn.close();
			Executions.sendRedirect("bug_report.zul");
		}catch(Exception e){}
	}
	
}
