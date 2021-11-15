package BackEnd.Command;

import BackEnd.Read.Teacher.ReadTests;
import BackEnd.Read.Teacher.TeacherReader;
import BackEnd.Write.Teacher.TeacherWriter;
import BackEnd.Write.Teacher.WriteTests;
import BackEnd.Write.Test.TestWriter;
import BackEnd.Write.Test.WriteNewTest;

//String test name, String author, int price -> SUCCESS/FAILED
public class createTestCommand extends Command {
    private final String name;
    private final int author;
    private final int price;
    private final java.util.Date date;

    public createTestCommand(String name, int author, java.util.Date date, int price) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.price = price;
    }

    @Override
    public Object execute() {
        TestWriter testWriter = new WriteNewTest(name, author, price, date);
        int id = (int) testWriter.set();
        if (id == FAILED) {
            return FAILED;
        }

        TeacherReader teacherReader = new ReadTests(author);
        String allTest = (String) teacherReader.read();
        if (allTest.length() == 0) {
            allTest = "" + id;
        } else {
            allTest = allTest + "," + id;
        }

        TeacherWriter teacherWriter = new WriteTests(author, allTest);
        return teacherWriter.set();
    }
}
