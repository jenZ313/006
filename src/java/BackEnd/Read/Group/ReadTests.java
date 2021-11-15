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
        try {
            String[] IDs = testIDs.trim().split(",");
            int[] result = new int[IDs.length];
            for (int i = 0; i < IDs.length; i++) {
                result[i] = Integer.parseInt(IDs[i]);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}
