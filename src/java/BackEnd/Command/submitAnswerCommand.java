package BackEnd.Command;

import BackEnd.Read.Test.ReadQuestions;
import BackEnd.Read.Test.TestReader;
import BackEnd.Write.Answer.AnswerWriter;
import BackEnd.Write.Answer.WriteNewAnswer;

//int student id, string answer, int question id -> SUCCESS/FAILED
public class submitAnswerCommand extends Command {
    private final String[] answer;
    private final int testID;
    private final int studentID;
    private final int groupID;

    public submitAnswerCommand(int studentID, String[] answer, int testID, int groupID) {
        this.answer = answer;
        this.testID = testID;
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {
        TestReader testReader = new ReadQuestions(testID);
        String question = (String) testReader.read();
        if (question.equals(FAILED + "")) {
            return FAILED;
        }
        try {
            String[] questions = question.trim().split(",");
            for (int i = 0; i < answer.length; i++) {
                AnswerWriter answerWriter = new WriteNewAnswer(studentID, answer[i], Integer.parseInt(questions[i]), groupID);
                int result = (int) answerWriter.set();
                if (result == FAILED) {
                    return FAILED;
                }
                Command c = new autoGrade();
                c.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;
        }

        return SUCCESS;
    }
    public static void main(String[] args){
        Command c = new registerTeacher("a","a","a");
        c.execute();
    }
}
