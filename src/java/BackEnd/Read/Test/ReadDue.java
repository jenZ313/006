package BackEnd.Read.Test;

public class ReadDue extends TestReader {
    private final int testID;

    public ReadDue(int testID) {
        this.testID = testID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + testID + "'";
        return readInfo(sql, DUECol, DATE);
    }
}
