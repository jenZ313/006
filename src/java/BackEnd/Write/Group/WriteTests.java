package BackEnd.Write.Group;

public class WriteTests extends GroupWriter {
    private final int groupID;
    private final String tests;

    public WriteTests(String tests, int groupID) {
        this.groupID = groupID;
        this.tests = tests;
    }

    @Override
    public Object set() {
        return setGroupInfo(TESTS, groupID, tests);
    }
}
