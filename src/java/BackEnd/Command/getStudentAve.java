package BackEnd.Command;

import BackEnd.Read.Test.ReadAve;
import BackEnd.Read.Test.TestReader;

import java.sql.SQLException;
import java.sql.Statement;

//int studentID, int groupID --> int student average
public class getStudentAve extends Command {
    private final int studentID;

    public getStudentAve(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            Statement statement = connection.createStatement();

            //get student answer

            TestReader testReader = new ReadAve(studentID);
            double ave = (double) testReader.read();

            return ave;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}
