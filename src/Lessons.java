import java.util.Random;

public class Lessons {
	 private int moduleid;
	 private String moduleName;
	 private String groupName1;
	 private int grouId[];
	 private String groupName2;
	 private String groupName3;
	 private int moduleHours;
	 
	 public Lessons() {}
	 
	 public Lessons(int moduleid,String moduleName,String groupName1,String groupName2,String groupName3,int[] grouId,int moduleHours) {
		 this.moduleid=moduleid;
		 this.moduleName=moduleName;
		 this.groupName1=groupName1;
		 this.grouId=grouId;
		 this.groupName2=groupName2;
		 this.groupName3=groupName3;
	 
	 
	 
	 }
	 
	public int[] getGrouId() {
		return grouId;
	}

	public void setGrouId(int[] grouId) {
		this.grouId = grouId;
	}

	public int getModuleid() {
		return moduleid;
	}

	public void setModuleid(int moduleid) {
		this.moduleid = moduleid;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getGroupName() {
		if(groupName1.equals("A1")) {
			return groupName1;
		}
		else if(groupName2.equals("A2")) {
			return groupName2;
		}
		else {
			return groupName3;
			}
		
	}
	public String getGroupName1() {
		return groupName1;
	}

	public void setGroupName1(String groupName1) {
		this.groupName1 = groupName1;
	}

	public String getGroupName2() {
		return groupName2;
	}

	public void setGroupName2(String groupName2) {
		this.groupName2 = groupName2;
	}
	
	public String getGroupName3() {
		return groupName3;
	}

	public void setGroupName3(String groupName3) {
		this.groupName3 = groupName3;
	}

	

	public int getModuleHours() {
		return moduleHours;
	}

	public void setModuleHours(int moduleHours) {
		this.moduleHours = moduleHours;
	}
	public int getRandomGroupId(){
        int groupid = grouId[(int) (grouId.length * Math.random())];
       
        return groupid;
    }
	 
	 
	 
}
