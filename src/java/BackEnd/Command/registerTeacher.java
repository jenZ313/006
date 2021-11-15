package BackEnd.Command;

import BackEnd.Write.Teacher.TeacherWriter;
import BackEnd.Write.Teacher.WriteNewTeacher;

//String name, String pass, String email, int type(teacher11, student12)-> USERNAMEALREADYUSED/SUCCESS/FAILED
public class registerTeacher extends Command {
    private final String name;
    private final String pass;
    private final String email;

    public registerTeacher(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;

    }

    @Override
    public Object execute() {

        TeacherWriter teacherWriter = new WriteNewTeacher(name, pass, email, TEACHER);
        return teacherWriter.set();


    }
}
