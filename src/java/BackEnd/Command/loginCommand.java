package BackEnd.Command;

import BackEnd.Read.Student.StudentReader;
import BackEnd.Read.Teacher.TeacherReader;

//String name,String pass -> an id/FAILED
public class loginCommand extends Command {
    private final String name;
    private final String pass;

    public loginCommand(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public Object execute() {

        StudentReader studentReader = new BackEnd.Read.Student.ReadNameAndPass(name, pass);
        int id = (int) studentReader.read();
        if (id != FAILED) {
            return id;
        }

        TeacherReader teacherReader = new BackEnd.Read.Teacher.ReadNameAndPass(name, pass);
        id = (int) teacherReader.read();
        return id;

    }
}
