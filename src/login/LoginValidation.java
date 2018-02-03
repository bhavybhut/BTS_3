package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class LoginValidation extends SelectorComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox unm;
	
	@Wire
	private Textbox pwd;
	
	@Wire
	private Label msg;
	
	@Listen("onClick = button")
	public void validate(){
		Connection conn;
		PreparedStatement stmt;
		ResultSet rs;
		String uname = null,password = null,designation = null;
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/BTS?user=root&password=password");
			stmt = conn.prepareStatement("select unm,pwd,desig from user");
			rs = stmt.executeQuery();
			while(rs.next()){
				uname = rs.getString(1);
				password = rs.getString(2);
				designation = rs.getString(3);
				if(unm.getValue().equals(uname) && pwd.getValue().equals(password)){
					flag = true;
					break;
				}	
			}
			if(flag== true){
				if(designation.equals("Developer")){
					Sessions.getCurrent().setAttribute("user", unm.getValue());
					Executions.sendRedirect("/developer/dev_home.zul");
				}
				else if(designation.equals("Project Manager")){
					Sessions.getCurrent().setAttribute("user", unm.getValue());
					Executions.sendRedirect("/projectmanager/pm_home.zul");
				}
				else if(designation.equals("Tester")){
					Sessions.getCurrent().setAttribute("user", unm.getValue());
					Executions.sendRedirect("/tester/tester_home.zul");
				}
			}
			else if(unm.getValue().equals("admin") && pwd.getValue().equals("admin")){
				Sessions.getCurrent().setAttribute("user", unm.getValue());
				Executions.sendRedirect("/admin/admin_home.zul");
			}
			else{
				unm.setValue("");
				pwd.setValue("");
				msg.setVisible(true);
			}
		}catch(Exception e){}
	}
}
