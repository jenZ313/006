package BackEnd.Command;

import BackEnd.Read.Group.GroupReader;
import BackEnd.Read.Group.ReadStudents;
import BackEnd.Read.Student.StudentReader;
import BackEnd.Write.Group.GroupWriter;
import BackEnd.Write.Group.WriteStudents;
import BackEnd.Write.Student.StudentWriter;
import BackEnd.Write.Student.WriteGroups;

//int studentID, int groupID -> SUCCESS/FAILED
public class joinGroupCommand extends Command {
    int studentID;
    int groupID;

    public joinGroupCommand(int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        //add group to student
        ////get all the groups joined by the student
        StudentReader studentReader = new BackEnd.Read.Student.ReadGroups(studentID);
        String allGroups = (String) studentReader.read();
        ////add groupID to the string
        if (isInString(allGroups, groupID, ",")) {
            return GROUPALREADYJOINED;
        }
        if (allGroups.length() == 0) {
            allGroups = groupID + "";
        } else {
            allGroups = allGroups + "," + groupID;
        }
        ////rewrite the new string to the database
        StudentWriter studentWriter = new WriteGroups(studentID, allGroups);
        int result = (int) studentWriter.set();
        if (result == FAILED) {
            return FAILED;
        }


        //add student to group
        ////get all the students form the group
        GroupReader groupReader = new ReadStudents(groupID);
        String allStudents = (String) groupReader.read();

        ////add studentID to the group
        if (allStudents.length() == 0) {
            allStudents = studentID + "";
        } else {
            allStudents = studentID + "," + allStudents;
        }
        ////rewrite the new string to the database
        GroupWriter groupWriter = new WriteStudents(allStudents, groupID);
        return groupWriter.set();

    }
}
