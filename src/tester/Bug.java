package tester;

public class Bug {

	private int p_id,d_id,b_id;
	private String priority,summary,desc,pname,status;
	
	public Bug(int b_id,int p_id,String pname,String priority,String summary,String desc,int d_id,String status){
		this.b_id = b_id;
		setPname(pname);
		setP_id(p_id);
		setD_id(d_id);
		setPriority(priority);
		setSummary(summary);
		setDesc(desc);
		setStatus(status);
	}
	
	public Bug(int b_id,String pname,String summary,String desc){
		this.b_id = b_id;
		setPname(pname);
		setSummary(summary);
		setDesc(desc);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getB_id() {
		return b_id;
	}
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
