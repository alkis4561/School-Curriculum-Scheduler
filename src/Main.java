import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.stream.FileCacheImageOutputStream;

public class Main {
	  public static void main(String[] args) throws IOException {
	    	// Get a Timetable object with all the available information.
	        Timetable timetable = initializeTimetable();
	        
	        
	        
	        
	        // Initialize GA
	        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
	        
	        // Initialize population
	        Population population = ga.initPopulation(timetable);
	        
	        // Evaluate population
	        ga.evalPopulation(population, timetable);
	        
	        // Keep track of current generation
	        int generation = 1;
	        
	        // Start evolution loop
	      // int y=0;
	        while (ga.isTerminationConditionMet(generation, 1000) == false && ga.isTerminationConditionMet(population) == false) {
	            // Print fitness
	            System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());

	            // Apply crossover
	            population = ga.crossoverPopulation(population);

	            // Apply mutation
	            population = ga.mutatePopulation(population, timetable);

	            // Evaluate population
	            ga.evalPopulation(population, timetable);

	            // Increment the current generation
	          //  y++;
	            generation++;
	        }
	     // Print fitness
	        timetable.createClasses(population.getFittest(0));
	        System.out.println();
	        System.out.println("Solution found in " + generation + " generations");
	        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
	        System.out.println("Clashes: " + timetable.calcClashes());
	        
	       File file = new File("src\\schedule.txt");
	        
	      //Create the file
	      
	    
			
			FileOutputStream writer;
			
			writer = new FileOutputStream(file);
			
			BufferedWriter Br=new BufferedWriter(new OutputStreamWriter(writer));
	        // Print classes
	      //  System.out.println();
	        Class classes[] = timetable.getClasses();
	        int classIndex = 1;
	        for (Class bestClass : classes) {
	        	
	        	Br.write("Module: " + timetable.getLesson(bestClass.getModuleId()).getModuleName()); 
	        	
	        	
	        	Br.write(" Group: " +  timetable.getGroup(bestClass.getGroupId()).getGroupName1());
	        	
	        	Br.write(" Time: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
	        	Br.write(" Professor: " + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
	        	
	        
	            
	        	Br.write("-----");
	        	Br.newLine();
	        	Br.newLine();
	            classIndex++;
	        }
	     Br.close();
		writer.close();
		
	     
	    
	  }
	  
	  
	  
	  
	  private static Timetable initializeTimetable() {
			// Create timetable
			Timetable timetable = new Timetable();
			
			
			List<String> strings = new ArrayList<String>();
			BufferedReader LessonsBr = null;
			try {
				LessonsBr = new BufferedReader(new FileReader("src\\lessons.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BufferedReader TeacherBr = null;
			try {
				TeacherBr = new BufferedReader(new FileReader("src\\teachers.txt"));
			} 
			catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			String saveLesson = "";
			String saveTeacher = "";
			int temp=0;
			try {
				while ((saveLesson = LessonsBr.readLine()) != null) {
					while	((saveTeacher = TeacherBr.readLine()) != null){
						
						temp++;
						strings.add(saveLesson + " " + saveTeacher);
						saveLesson= LessonsBr.readLine();
						
				    
					
						//strings.add(saveLesson);
					
				    
				}
					if(TeacherBr.readLine()==null)
					strings.add(saveLesson);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int y=0;
			for(String x: strings) {
				
				//System.out.println(moduleId);
						
					StringTokenizer stn=new StringTokenizer(x);
					
					int moduleId=Integer.parseInt(stn.nextToken());
					String moduleName=stn.nextToken();
					String groupName1=stn.nextToken();
					int grouId1=controlGroup(groupName1);
					String groupName2=stn.nextToken();
					int grouId2=controlGroup(groupName2);
					String groupName3=stn.nextToken();
					int grouId3=controlGroup(groupName3);
					int moduleHours=Integer.parseInt(stn.nextToken());
					
					
					
					if(y<temp) {
						int professorId=Integer.parseInt(stn.nextToken());
						String professorsName=stn.nextToken();
						
						int moduleId1=Integer.parseInt(stn.nextToken());
						int moduleId2=Integer.parseInt(stn.nextToken());
						int moduleId3=Integer.parseInt(stn.nextToken());					
						int hoursPerDay=Integer.parseInt(stn.nextToken());
						int hoursPerWeek=Integer.parseInt(stn.nextToken());
						timetable.addTeacher(professorId,professorsName,new int[] {moduleId1,moduleId2,moduleId3},hoursPerDay,hoursPerWeek);
						
						
						y++;
						
						
					}
				
				
					timetable.addLessons(moduleId, moduleName,groupName1,groupName2,groupName3,new int[] {grouId1,grouId2,grouId3},moduleHours);
					
				
					
			}
			timetable.addTimeslot(1, "Mon 8:00 - 9:00");
			timetable.addTimeslot(2, "Mon 9:00 - 10:00");
			timetable.addTimeslot(3, "Mon 10:00 - 11:00");
			timetable.addTimeslot(4, "Mon 11:00 - 12:00");
			timetable.addTimeslot(5, "Mon 12:00 - 13:00");
			timetable.addTimeslot(6, "Mon 13:00 - 14:00");
			timetable.addTimeslot(7, "Mon 14:00 - 15:00");
			
			timetable.addTimeslot(8, "tue 8:00 - 9:00");
			timetable.addTimeslot(9, "tue 9:00 - 10:00");
			timetable.addTimeslot(10, "tue 10:00 - 11:00");
			timetable.addTimeslot(11, "tue 11:00 - 12:00");
			timetable.addTimeslot(12, "tue 12:00 - 13:00");
			timetable.addTimeslot(13, "tue 13:00 - 14:00");
			timetable.addTimeslot(14, "tue 14:00 - 15:00");
			
			timetable.addTimeslot(15, "wen 8:00 - 9:00");
			timetable.addTimeslot(16, "wen 9:00 - 10:00");
			timetable.addTimeslot(17, "wen 10:00 - 11:00");
			timetable.addTimeslot(18, "wen 11:00 - 12:00");
			timetable.addTimeslot(19, "wen 12:00 - 13:00");
			timetable.addTimeslot(20, "wen 13:00 - 14:00");
			timetable.addTimeslot(21, "wen 14:00 - 15:00");
			
			timetable.addTimeslot(21, "thi 8:00 - 9:00");
			timetable.addTimeslot(22, "thi 9:00 - 10:00");
			timetable.addTimeslot(23, "thi 10:00 - 11:00");
			timetable.addTimeslot(24, "thi 11:00 - 12:00");
			timetable.addTimeslot(25, "thi 12:00 - 13:00");
			timetable.addTimeslot(26, "thi 13:00 - 14:00");
			timetable.addTimeslot(27, "thi 14:00 - 15:00");
			
			timetable.addTimeslot(28, "fri 8:00 - 9:00");
			timetable.addTimeslot(29, "fri 9:00 - 10:00");
			timetable.addTimeslot(30, "fri 10:00 - 11:00");
			timetable.addTimeslot(31, "fri 11:00 - 12:00");
			timetable.addTimeslot(32, "fri 12:00 - 13:00");
			timetable.addTimeslot(33, "fri 13:00 - 14:00");
			timetable.addTimeslot(34, "fri 14:00 - 15:00");
			
			
			
			
			// Set up rooms
			/*timetable.addRoom(1, "A1");
			timetable.addRoom(2, "B1");
			timetable.addRoom(4, "D1");
			timetable.addRoom(5, "F1");
			
			
			// Set up timeslots
			timetable.addTimeslot(1, "Mon 9:00 - 11:00");
			timetable.addTimeslot(2, "Mon 11:00 - 13:00");
			timetable.addTimeslot(3, "Mon 13:00 - 15:00");
			timetable.addTimeslot(4, "Tue 9:00 - 11:00");
			timetable.addTimeslot(5, "Tue 11:00 - 13:00");
			timetable.addTimeslot(6, "Tue 13:00 - 15:00");
			timetable.addTimeslot(7, "Wed 9:00 - 11:00");
			timetable.addTimeslot(8, "Wed 11:00 - 13:00");
			timetable.addTimeslot(9, "Wed 13:00 - 15:00");
			timetable.addTimeslot(10, "Thu 9:00 - 11:00");
			timetable.addTimeslot(11, "Thu 11:00 - 13:00");
			timetable.addTimeslot(12, "Thu 13:00 - 15:00");
			timetable.addTimeslot(13, "Fri 9:00 - 11:00");
			timetable.addTimeslot(14, "Fri 11:00 - 13:00");
			timetable.addTimeslot(15, "Fri 13:00 - 15:00");

			// Set up professors
			timetable.addProfessor(1, "Maria_kontogianni");
			timetable.addProfessor(2, "Mrs E Mitchell");
			timetable.addProfessor(3, "Dr R Williams");
			timetable.addProfessor(4, "Mr A Thompson");

			// Set up modules and define the professors that teach them
			timetable.addModule(1,"Computer Science", 3, new int[] { 1, 2 });
			timetable.addModule(2,"English", 3, new int[] { 1, 3 });
			timetable.addModule(3,"Maths", 3, new int[] { 1, 2 });
			timetable.addModule(4,"Physics", 3, new int[] { 3, 4 });
			timetable.addModule(5,"History", 3, new int[] { 4 });
			timetable.addModule(6,"Drama", 3, new int[] { 1, 4 });

			// Set up student groups and the modules they take.
			timetable.addGroup(1,  new int[] { 1, 3, 4 });
			timetable.addGroup(2,  new int[] { 2, 3, 5, 6 });
			timetable.addGroup(3,  new int[] { 3, 4, 5 });
			timetable.addGroup(4,  new int[] { 1, 4 });
			timetable.addGroup(5,  new int[] { 2, 3, 5 });
			timetable.addGroup(6,  new int[] { 1, 4, 5 });
			timetable.addGroup(7,  new int[] { 1, 3 });
			timetable.addGroup(8,  new int[] { 2, 6 });
			timetable.addGroup(9,  new int[] { 1, 6 });
			timetable.addGroup(10, new int[] { 3, 4 });*/
			
			return timetable;
		}
	  private static int controlGroup(String GroupName) {
			if(GroupName.equals("A1")) {
				return 1;
			}
			else if(GroupName.equals("A2")) {
				return 2;
			}
			else if(GroupName.equals("A3")) {
				return 3;
			}
			else if(GroupName.equals("B1")) {
				return 4;
			}
			else if(GroupName.equals("B2")) {
				return 5;
			}
			else if(GroupName.equals("B3")) {
				return 6;
			}
			else if(GroupName.equals("C1")) {
				return 7;
			}
			else if(GroupName.equals("C2")) {
				return 8;
			}
			else {
				return 9;
			}
			
			
		}

}
