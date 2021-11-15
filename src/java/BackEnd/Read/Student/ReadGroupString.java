/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.Read.Student;

/**
 *
 * @author darcy
 */
public class ReadGroupString extends StudentReader{
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
