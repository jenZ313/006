package BackEnd.Read.Group;

public class ReadTests extends GroupReader {
    private final int groupID;

    public ReadTests(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + groupID + "'";
        String testIDs = (String) readInfo(sql, TESTSCol, INT);
        if (testIDs.equals(FAILED + "")) {
            return FAILED;
        }

        String[] IDs = testIDs.trim().split(",");
        return convert(IDs);
    }
}
