package BackEnd.Command;

import BackEnd.Read.Group.GroupReader;
import BackEnd.Read.Group.ReadID;
import BackEnd.Read.Teacher.ReadGroupString;
import BackEnd.Write.Group.GroupWriter;
import BackEnd.Write.Group.WriteNewGroup;
import BackEnd.Write.Teacher.WriteGroups;
import BackEnd.Read.Teacher.ReadGroups;
import BackEnd.Read.Teacher.TeacherReader;
import BackEnd.Write.Teacher.TeacherWriter;
import java.util.List;


//int teacherID, String groupName -> GroupID/FAILED
public class createGroupCommand extends Command {
    int teacherID;
    String groupName;

    public createGroupCommand(int teacherID, String groupName) {
        this.teacherID = teacherID;
        this.groupName = groupName;
    }

    @Override
    public Object execute() {
        GroupWriter groupWriter = new WriteNewGroup(teacherID, groupName);
         groupWriter.set();
        TeacherReader reader = new ReadGroupString(teacherID);
        GroupReader groupReader = new ReadID(groupName);
        int id =(int) groupReader.read();
        if(id==FAILED){
            return FAILED;
        }
        //String allGroups = reader.read().toString();
        String allGroups = reader.read().toString();
        if(allGroups.equals(FAILED+"")){
            return FAILED;
        }else if(allGroups.length()==0){
            allGroups=id+"";
        }else{
            allGroups = allGroups+","+id;
        }
        TeacherWriter writer = new WriteGroups(teacherID, allGroups);
        return writer.set();
    }
}
