package BackEnd.Command;

import BackEnd.Read.Question.ReadID;
import BackEnd.Read.Test.ReadQuestions;
import BackEnd.Read.Test.TestReader;
import BackEnd.Write.Test.TestWriter;
import BackEnd.Write.Test.WriteQuestions;

//int question id, int test id -> FAILED/SUCCESS
public class addQuestionToTestCommand extends Command {
    private final int questionID;
    private final int testID;

    public addQuestionToTestCommand(int questionID, int testID) {
        this.questionID = questionID;
        this.testID = testID;
    }

    public addQuestionToTestCommand(int testID, String question, String answer, int mark) {
        this.testID = testID;
        this.questionID = (int) new ReadID(question, answer).read();
    }

    @Override
    public Object execute() {

        TestReader testReader = new ReadQuestions(testID);
        String allQuestions = (String) testReader.read();
        if (allQuestions.length() == 0) {
            allQuestions = "" + questionID;
        } else {
            allQuestions = allQuestions + "," + questionID;
        }

        TestWriter testWriter = new WriteQuestions(allQuestions, testID);
        return testWriter.set();


    }
}
