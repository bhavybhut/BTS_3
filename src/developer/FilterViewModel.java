package developer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import projectManager.Project;
import tester.Bug;
import tester.BugStatus;

public class FilterViewModel implements Composer{

	private static final String bugFooterMessage = "Total %d bugs";
	private static final String projFooterMessage = "Total %d project(s)";
	private ViewFilter bugFilter = new ViewFilter();
	private ViewFilter projectFilter = new ViewFilter();
	private List<Bug> currentBug = ViewBugs.getAllBugs();
	 List<Project> currentProject = ViewProjects.getAllProjects();
	private final List<BugStatus> bugStatuses = generateStatusList(ViewBugs.getAllBugs());
	
	public ViewFilter getBugFilter(){
		return bugFilter;
	}
	
	public ListModel<Bug> getBugModel(){
		return new ListModelList<Bug>(currentBug);
	}
	
	public String getBugFooter(){
		return String.format(bugFooterMessage, currentBug.size());
	}
	
	public String getProjectFooter(){
		return String.format(projFooterMessage, currentProject.size());
	}
	
	public List<BugStatus> getBugStatus(){
		return bugStatuses;
	}
	
	public ListModel<Project> getProjectModel() {
        return new ListModelList<Project>(currentProject);
    }
	
	public ViewFilter getProjectFilter(){
		return projectFilter;
	}
	
	@Command
	public void viewBugDetail(@BindingParam("bugStatus") final BugStatus bs){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bugModel", bs.getBug());
			Sessions.getCurrent().setAttribute("bugDetail", map);
			Executions.sendRedirect("bug_details.zul");
	}
	
	 public void refreshRowTemplate(BugStatus bs) {
			/*
			 * This code is special and notifies ZK that the bean's value
			 * has changed as it is used in the template mechanism.
			 * This stops the entire Grid's data from being refreshed
			 */
			BindUtils.postNotifyChange(null, null, bs, "editingStatus");
	}
	    
	private static List<BugStatus> generateStatusList(List<Bug> bugs) {
		List<BugStatus> bs = new ArrayList<BugStatus>();
		for(Bug b : bugs) {
			bs.add(new BugStatus(false,b));
		}
		return bs;
	}
	
	@Command
	@NotifyChange({"bugModel","footer"})
	public void changeBugFilter(){
		currentBug = ViewBugs.getFilterBug(bugFilter);
	}
	
	@Command
	@NotifyChange({"projectModel","footer"})
	public void changeProjectFilter(){
		currentProject = ViewProjects.getFilterProject(projectFilter);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
