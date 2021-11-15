package BackEnd.Command;

import BackEnd.Read.Answer.AnswerReader;
import BackEnd.Read.Answer.ReadMark;
import BackEnd.Read.Test.ReadQuestions;
import BackEnd.Read.Test.TestReader;
import BackEnd.Write.Test.TestWriter;

//int studentID, int testID --> int mark/FAILED
public class gradeTest extends Command {
    private final int studentID;
    private final int testID;

    public gradeTest(int studentID, int testID) {
        this.studentID = studentID;
        this.testID = testID;
    }

    @Override
    public Object execute() {
        TestReader testReader = new ReadQuestions(testID);
        String question = (String) testReader.read();
        try {
            String[] questions = question.trim().split(",");
            int sum = 0;
            for (int i = 0; i < questions.length; i++) {
                AnswerReader answerReader = new ReadMark(studentID, Integer.parseInt(questions[i]));
                int mark = (int) answerReader.read();
                sum += mark;
            }
            TestWriter testWriter = new BackEnd.Write.Test.WriteMark(testID, sum);
            return testWriter.set();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;

        }

    }
}
