
public class Class {
    private final int classId;
    private int groupId;
    private int moduleId;
    private final  int professorId;
    private int timeslotId;
    private int roomId;
    
    
    public Class(int classId, int professorId, int moduleId){
        this.classId = classId;
        this.moduleId = moduleId;
        this.professorId = professorId;
    }
  
    public void addGroup(int groupId){
        this.groupId = groupId;
    }

    public void addTimeslot(int timeslotId){
        this.timeslotId = timeslotId;
    }    
    
   
    public void setRoomId(int roomId){
        this.roomId = roomId;
    }
    
 
    public int getClassId(){
        return this.classId;
    }

    public int getGroupId(){
        return this.groupId;
    }
    
    public void setModuleId(int moduleId){
        this.moduleId=moduleId;
    }
    
   
    public int getModuleId(){
        return this.moduleId;
    }
  
    public int getProfessorId(){
        return this.professorId;
    }
    
  
    public int getTimeslotId(){
        return this.timeslotId;
    }
    
  
    public int getRoomId(){
        return this.roomId;
    }
}

