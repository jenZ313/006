package BackEnd.Command;

import BackEnd.Write.Student.StudentWriter;
import BackEnd.Write.Student.WriteNewStudent;

//String name, String pass, String email, int type(teacher11, student12)-> USERNAMEALREADYUSED/SUCCESS/FAILED
public class registerStudent extends Command {
    private final String name;
    private final String pass;
    private final String email;


    public registerStudent(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;

    }

    @Override
    public Object execute() {

        StudentWriter studentWriter = new WriteNewStudent(name, pass, email, STUDENT);
        return studentWriter.set();


    }
}
