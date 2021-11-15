package BackEnd.Command;

import BackEnd.Read.Answer.AnswerReader;
import BackEnd.Read.Answer.ReadMark;
import BackEnd.Read.Answer.ReadAnswerByMore;
import BackEnd.Read.Group.GroupReader;
import BackEnd.Read.Group.ReadStudents;
import BackEnd.Read.Question.QuestionReader;
import BackEnd.Read.Question.ReadID;
import BackEnd.Read.Question.ReadQuestion;
import BackEnd.Read.Student.StudentReader;
import BackEnd.Read.Teacher.ReadTests;
import BackEnd.Read.Teacher.TeacherReader;
import BackEnd.Read.Test.*;
import BackEnd.Write.Answer.AnswerWriter;
import BackEnd.Write.Answer.AutoGrade;
import BackEnd.Write.Answer.WriteMark;
import BackEnd.Write.Answer.WriteNewAnswer;
import BackEnd.Write.Group.*;
import BackEnd.Write.Question.QuestionWriter;
import BackEnd.Write.Question.WriteNewQuestion;
import BackEnd.Write.Student.StudentWriter;
import BackEnd.Write.Student.WriteGroups;
import BackEnd.Write.Student.WriteNewStudent;
import BackEnd.Write.Teacher.TeacherWriter;
import BackEnd.Write.Teacher.WriteNewTeacher;
import BackEnd.Write.Teacher.WriteTests;
import BackEnd.Write.Test.TestWriter;
import BackEnd.Write.Test.*;
import BackEnd.Write.Test.WriteNewTest;
import BackEnd.Write.Test.WriteQuestions;


import java.sql.*;


public abstract class Command {
    public final int SUCCESS = 0;
    public final int FAILED = -1;
    public final int USERNAMEALREADYUSED = -2;
    public final int GROUPALREADYJOINED = -3;
    public final int TEACHER = 11;
    public final int STUDENT = 12;

    public final String STUDENTTABLENAME = "STUDENT";


    public Connection connection = null;

    public String driver = "com.mysql.cj.jdbc.Driver";//驱动程序名
    public String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5449780";//url指向要访问的数据库study
    public String user = "sql5449780";//MySQL配置时的用户名
    public String password = "HNzHR6WEhn";//MySQL配置时的密码

    public Command() {
        getConnection();
    }

    public void getConnection() {
        try {
            // 加载驱动类
            Class.forName(driver);
            // 建立连接
            this.connection = DriverManager.getConnection(url,
                    user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int removeGroupFromUser(int userID, int groupID, int userType) {

        if (userType == STUDENT) {
            //get all group ids
            StudentReader reader = new BackEnd.Read.Student.ReadGroupString(userID);
            String allGroups = (String) reader.read();
            if (allGroups.equals(FAILED + "")) {
                return FAILED;
            }
            //check if group id is in the string
            if (!isInString(allGroups, groupID, ",")) {
                return FAILED;
            }
            //remove id from string
            String newGroups = removeIDFromString(allGroups, groupID, ",");
            //reset the new string
            StudentWriter writer = new BackEnd.Write.Student.WriteGroups(userID, newGroups);
            return (int) writer.set();

        } else {
            TeacherReader reader = new BackEnd.Read.Teacher.ReadGroupString(userID);
            String allGroups = reader.read().toString();
            if (allGroups.equals(FAILED + "")) {
                return FAILED;
            }
            if (!isInString(allGroups, groupID, ",")) {
                return FAILED;
            }
            String newGroups = removeIDFromString(allGroups, groupID, ",");
            TeacherWriter writer = new BackEnd.Write.Teacher.WriteGroups(userID, newGroups);
            return (int) writer.set();
        }
    }

    public int removeStudentFromGroup(int userID, int groupID) {

        //get all students
        GroupReader groupReader = new ReadStudents(groupID);
        String allStudents = groupReader.read().toString();
        if (allStudents.equals(FAILED + "")) {
            return FAILED;
        }
        //check if student is in the group
        if (!isInString(allStudents, groupID, ",")) {
            return FAILED;
        }
        //remove student from the group
        String newStudents = removeIDFromString(allStudents, userID, ",");
        //reset the new string
        GroupWriter writer = new BackEnd.Write.Group.WriteStudents(newStudents, groupID);
        return (int) writer.set();
    }

    public boolean isInString(String string, int id, String split) {
        String[] array = string.split(split);
        for (String s : array) {
            if (s.trim().equals(id + "")) {
                return true;
            }
        }
        return false;
    }

    public String removeIDFromString(String string, int id, String split) {
        if (string.trim().length() == (id + "").length()) {
            return "";
        }
        String[] array = string.split(split);
        String result = "";
        for (String s : array) {
            if (!s.equals(id + "")) {
                result += s + ",";
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public abstract Object execute();
}

