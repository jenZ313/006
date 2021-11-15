package BackEnd.Command;

import BackEnd.Write.Group.GroupWriter;
import BackEnd.Write.Group.WriteNewGroup;

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
        return groupWriter.set();
    }
}
