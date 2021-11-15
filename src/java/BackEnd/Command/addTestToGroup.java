package BackEnd.Command;

import BackEnd.Read.Group.GroupReader;
import BackEnd.Read.Group.ReadTests;
import BackEnd.Write.Group.GroupWriter;
import BackEnd.Write.Group.WriteTests;

public class addTestToGroup extends Command {
    private final int testID;
    private final int groupID;

    public addTestToGroup(int groupID, int testID, java.time.LocalDateTime due) {
        this.groupID = groupID;
        this.testID = testID;
    }

    @Override
    public Object execute() {
        //get groups in test
        GroupReader groupReader = new ReadTests(groupID);
        int[] things = (int[]) groupReader.read();

        //add to the end
        String newString = "";
        for (int i : things) {
            newString = newString + "," + testID;
        }
        newString = newString.substring(1);
        //write
        GroupWriter groupWriter = new WriteTests(newString, groupID);
        return groupWriter.set();
    }
}
