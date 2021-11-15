package BackEnd.Command;

import BackEnd.Read.Student.StudentReader;
import BackEnd.Read.Teacher.TeacherReader;

//int id -> STUDENT/TEACHER/FAILED
public class checkIdentity extends Command {
    int id;

    public checkIdentity(int id) {
        this.id = id;
    }

    @Override
    public Object execute() {

        TeacherReader teacherReader = new BackEnd.Read.Teacher.ReadName(id);
        String name = (String) teacherReader.read().toString();
        if (!name.equals(FAILED + "")) {
            return TEACHER;
        }
        StudentReader studentReader = new BackEnd.Read.Student.ReadName(id);
        name = (String) studentReader.read();
        if (!name.equals(FAILED + "")) {
            return STUDENT;
        }

        return FAILED;

    }
}
