package developer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tester.Bug;


public class ViewBugs {

	private static List<Bug> bugList = new ArrayList<Bug>();
	public static void viewBugs(){
		if(!bugList.isEmpty())
			bugList.clear();
		String priority,summary,description,pname,status;
		int p_id,d_id,b_id;
		Connection conn;
		PreparedStatement stmt1;
		Statement stmt2;
		ResultSet rs1,rs2;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/BTS?user=root&password=password");
			stmt1 = conn.prepareStatement("select p_id,priority,summary,description,d_id,b_id,status from bug where status='Open'");
			rs1 = stmt1.executeQuery();
			while(rs1.next()){
				p_id = rs1.getInt(1);
				priority = rs1.getString(2);
				summary = rs1.getString(3);
				description = rs1.getString(4);
				d_id = rs1.getInt(5);
				b_id = rs1.getInt(6);
				status = rs1.getString(7);
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery("select name from project where p_id="+p_id);
				while(rs2.next()){
					pname = rs2.getString(1);
					bugList.add(new Bug(b_id,p_id,pname,priority,summary,description,d_id,status));
				}
			}
		}catch(Exception e){}
	}
	
	public static List<Bug> getAllBugs(){
		viewBugs();
		return new ArrayList<Bug>(bugList);
	}
	
	public static List<Bug> getFilterBug(ViewFilter bugFilter)
	{
		List<Bug> someBugs = new ArrayList<Bug>();
		String priority = bugFilter.getPriority().toLowerCase();
		String pname = bugFilter.getPname().toLowerCase();
		
		for (Iterator<Bug> i = bugList.iterator(); i.hasNext();) {
            Bug tmp = i.next();
            if (tmp.getPname().toLowerCase().contains(pname) &&
                tmp.getPriority().toLowerCase().contains(priority)) {
                someBugs.add(tmp);
            }
        }
        return someBugs;
	}
}
