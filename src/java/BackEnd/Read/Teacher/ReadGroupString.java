/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.Read.Teacher;

/**
 *
 * @author darcy
 */
public class ReadGroupString extends TeacherReader{
        private final int ID;

    public ReadGroupString(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, GROUPSCol, STRING);
    }
    
}
