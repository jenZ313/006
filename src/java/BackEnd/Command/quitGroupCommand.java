package BackEnd.Command;

//int studentID, int groupID -> SUCCESS/FAILED
public class quitGroupCommand extends Command {
    int studentID;
    int groupID;

    public quitGroupCommand(int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        //remove group from student
        int result = removeGroupFromUser(studentID, groupID, STUDENT);
        if (result != SUCCESS) {
            return FAILED;
        }
        //remove student from group
        result = removeStudentFromGroup(studentID, groupID);
        if (result != SUCCESS) {
            return FAILED;
        }
        return SUCCESS;
    }
}
