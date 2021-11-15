package BackEnd.Command;

//int studentID, int groupID ->Success/failed
public class deleteMemberCommand extends Command {
    int studentID;
    int groupID;

    public deleteMemberCommand(int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        int result1 = removeGroupFromUser(studentID, groupID, STUDENT);
        int result3 = removeStudentFromGroup(studentID, groupID);
        if (result1 == SUCCESS && result3 == SUCCESS) {
            return SUCCESS;
        }
        return FAILED;
    }

}
