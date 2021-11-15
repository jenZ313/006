package BackEnd.Command;

import BackEnd.Read.Group.GroupReader;
import BackEnd.Read.Group.ReadStudents;
import BackEnd.Write.Group.DeleteGroup;
import BackEnd.Write.Group.GroupWriter;

//int teacherID, int groupID -> SUCCESS/FAILED
public class deleteGroupCommand extends Command {
    int teacherID;
    int groupID;

    public deleteGroupCommand(int teacherID, int groupID) {
        this.teacherID = teacherID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        //remove group from teacher
        int result = removeGroupFromUser(teacherID, groupID, TEACHER);
        if (result != SUCCESS) {
            return FAILED;
        }
        //remove group from students
        GroupReader groupReader = new ReadStudents(groupID);
        String allStudents = (String) groupReader.read();
        if (allStudents.trim().length() != 0) {
            String[] students = allStudents.trim().split(",");
            for (String student : students) {
                removeGroupFromUser(Integer.parseInt(student), groupID, STUDENT);
            }
        }

        //delete the group from the group table
        GroupWriter groupWriter = new DeleteGroup(groupID);
        return groupWriter.set();
    }

}
