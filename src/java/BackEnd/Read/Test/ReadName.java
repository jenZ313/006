package BackEnd.Read.Test;

public class ReadName extends TestReader {
    private final int ID;

    public ReadName(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, NAMECol, STRING);
    }
}
