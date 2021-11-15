package BackEnd.Read.Teacher;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import BackEnd.Read.Group.ReadName;
public class ReadGroups extends TeacherReader {

    private final int teacherID;

    public ReadGroups(int teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public Object read() {
        List<Integer> result = new ArrayList<>();
        String sql = "select * from " + TABLE + " where id='" + teacherID + "'";
        String IDs =  readInfo(sql, GROUPSCol, STRING).toString();
        if (IDs.equals(FAILED + "")) {
            return new ArrayList<Integer>();
        }

        try {
            String[] IDList = IDs.trim().split(",");
            for (int i = 0; i < IDList.length; i++) {
                result.add(i, Integer.parseInt(IDList[i]));
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Integer>();        }
    }
}
