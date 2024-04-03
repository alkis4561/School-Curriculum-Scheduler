import java.util.HashMap;



public class Timetable {
	private final HashMap<Integer, Teachers> teachers;
	private final HashMap<Integer, Lessons> lessons;
	private final HashMap<Integer, Timeslot> timeslots;
	private Class classes[];
	
	private int numclasses=0;
	
	
	public Timetable() {
		this.teachers=new HashMap<Integer, Teachers>();
		this.lessons=new HashMap<Integer,Lessons>();
		this.timeslots = new HashMap<Integer, Timeslot>();
	}
	
	public Timetable(Timetable cloneable) {
		this.teachers=cloneable.getTeachers();
		this.lessons=cloneable.getModules();
		this.timeslots = cloneable.getTimeslots();
	}

	public HashMap<Integer, Teachers> getTeachers() {
		this.numclasses = 0;
		return teachers;
		
	}

	public HashMap<Integer, Lessons> getModules() {
		return lessons;
	}
	private HashMap<Integer, Timeslot> getTimeslots() {
		return this.timeslots;
	}
	
	public void addTeacher(int professorId, String professorName,int[] moduleId,int hoursPerDay,int hoursPerWeek) {
		this.teachers.put(professorId, new Teachers(professorId, professorName,moduleId,hoursPerDay,hoursPerWeek));
	}
	
	public void addLessons(int moduleid,String moduleName,String groupName1,String groupName2,String groupName3, int[] groupId,int moduleHours) {
		this.lessons.put(moduleid, new Lessons(moduleid, moduleName, groupName1,groupName2,groupName3,groupId,moduleHours));
	}
	public void addTimeslot(int timeslotId, String timeslot) {
		this.timeslots.put(timeslotId, new Timeslot(timeslotId, timeslot));
	}
	
	public void createClasses(Individual individual) {
		Class classes[] = new Class[this.getNumClasses()];
		int chromosome[] = individual.getChromosome();
		int chromosomePos = 0;
		int classIndex = 0;
		
		for(Teachers teacher : this.getTeachersAsArray()) {
			int moduleIds[] = teacher.getModuleId1();
			for(int moduleId : moduleIds) {
				classes[classIndex] = new Class(classIndex, teacher.getProfessorId(), moduleId);
				
				classes[classIndex].addTimeslot(chromosome[chromosomePos]);
				chromosomePos++;
				
				classes[classIndex].addGroup(chromosome[chromosomePos]);
				chromosomePos++;

				classIndex++;
				
			}
		}
		
		
		this.classes = classes;
	
	
	}
	
	public Timeslot getRandomTimeslot() {
		Object[] timeslotArray = this.timeslots.values().toArray();
		Timeslot timeslot = (Timeslot) timeslotArray[(int) (timeslotArray.length * Math.random())];
		return timeslot;
	}
	public Lessons getLesson(int moduleId) {
		return (Lessons) this.lessons.get(moduleId);
	}
	public Teachers[] getTeachersAsArray() {
		return (Teachers[]) this.teachers.values().toArray(new Teachers[this.teachers.size()]);
	}
	public Class[] getClasses() {
		return this.classes;
	}
	public Timeslot getTimeslot(int timeslotId) {
		return (Timeslot) this.timeslots.get(timeslotId);
	}
	public Teachers getProfessor(int professorId) {
		return (Teachers) this.teachers.get(professorId);
	}
	public Lessons getGroup(int groupId) {
		return (Lessons) this.lessons.get(groupId);
	}
	
	
	public int getNumClasses() {
		if (this.numclasses > 0) {
			return this.numclasses;
		}

		int numClasses = 0;
		Teachers Professor[] = (Teachers[]) this.teachers.values().toArray(new Teachers[this.teachers.size()]);
		for (Teachers teacher : Professor) {
			numClasses += teacher.getModuleId1().length;
			
			
		}
		this.numclasses = numClasses;
		
		return this.numclasses;
	}
	public int calcClashes() {
		int clashes = 0;
		int hours = 0;
		int proHours=0;
		for (Class classA : this.classes) {
			//int modulehours=this.getLesson(classA.getClassId()).getModuleHours();
			for (Class classB : this.classes) {
				//Check if professor is available
				if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId()&& classA.getClassId() != classB.getClassId()) {
					clashes++;
					break;
				}
			}
			//Check group is available
			for (Class classB : this.classes) {
				if(classA.getGroupId()==classB.getGroupId() && classA.getTimeslotId()==classB.getTimeslotId() && classA.getClassId() != classB.getClassId());
				clashes++;
				break;
			}
			//check if hours of modules 
			for (Class classB : this.classes) {
				if(classA.getModuleId()==classB.getModuleId() && classA.getGroupId()==classB.getGroupId()) {
					hours++;
				}
			}
			if(hours>this.getLesson(classA.getModuleId()).getModuleHours()) {
				clashes++;
			}
			for(Class classB:this.classes) {
				if(classA.getProfessorId() == classB.getProfessorId() && classA.getClassId() != classB.getClassId()) {
					if(classA.getTimeslotId()<8 && classB.getTimeslotId()<8) {
					proHours++;
					}
				}
			}
			if(proHours>this.getProfessor(classA.getProfessorId()).getHoursPerDay()) {
				clashes++;
			}
			
			
			
			
		}
			
	
		return clashes;
			
	}
		
		
		
		
		
}
	
	
	
	
	
	
	
	
	
	
	

