package tester;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Label;

public class BugDetails implements Composer{

	private Label summary,desc,pname,b_id;
	private String summ,description,name;
	private int id;
	
	public Label getB_id() {
		return b_id;
	}

	public Label getSummary() {
		return summary;
	}

	public void setSummary(Label summary) {
		this.summary = summary;
	}

	public Label getDesc() {
		return desc;
	}

	public void setDesc(Label desc) {
		this.desc = desc;
	}

	public Label getPname() {
		return pname;
	}

	public void setPname(Label pname) {
		this.pname = pname;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = (HashMap<String, Object>) Sessions.getCurrent().getAttribute("bugDetail");
		Bug bug = (Bug) map.get("bugModel");
		
		
		summ = bug.getSummary();
		description = bug.getDesc();
		name = bug.getPname();
		id = bug.getB_id();
		
		summary = (Label) comp.getFellow("summary");
		desc = (Label) comp.getFellow("desc");
		pname = (Label) comp.getFellow("pname");
		b_id = (Label) comp.getFellow("b_id");
		
		summary.setValue(summ);
		desc.setValue(description);
		pname.setValue(name);
		b_id.setValue(String.valueOf(id));
	}

}
