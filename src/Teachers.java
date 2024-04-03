
public class Teachers extends Lessons {
	private int professorId;
	private String professorName;
	private int moduleId1[];
	private int hoursPerDay;
	private int hoursPerWeek;
	
	public Teachers(int moduleid, String moduleName, String groupName1, String groupName2,String groupName3, int[] grouId, int moduleHours) {
		super(moduleid, moduleName, groupName1, groupName2, groupName3,grouId, moduleHours);
	}
	
	
	public Teachers(int professorId, String professorName, int moduleId1[],int hoursPerDay,int hoursPerWeek) {
		
		this.professorId = professorId;
		this.professorName = professorName;
		this.moduleId1 = moduleId1;
		this.hoursPerDay = hoursPerDay;
		this.hoursPerWeek = hoursPerWeek;
		
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public int[] getModuleId1() {
		return moduleId1;
	}
	public void setModuleId1(int[] moduleId1) {
		this.moduleId1 = moduleId1;
	}
	public int getHoursPerDay() {
		return hoursPerDay;
	}
	public void setHoursPerDay(int hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}
	public void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
	
	public int getRandomModuleId(){
        int professorId = moduleId1[(int) (moduleId1.length * Math.random())];
        return professorId;
    }
	
	
	
}
