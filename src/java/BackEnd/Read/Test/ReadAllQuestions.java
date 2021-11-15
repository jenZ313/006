package BackEnd.Read.Test;

import BackEnd.Read.Question.QuestionReader;
import BackEnd.Read.Question.ReadAnswer;
import BackEnd.Read.Question.ReadQuestion;

public class ReadAllQuestions extends TestReader {
    private final int testID;

    public ReadAllQuestions(int testID) {
        this.testID = testID;
    }

    @Override
    public Object read() {
        String allQuestionID = (String) new ReadQuestions(testID).read();
        if (allQuestionID.equals(FAILED + "")) {
            return new String[0];
        }
        String[] questionList = allQuestionID.split(",");

        String[] result = new String[questionList.length];
        for (int i = 0; i < questionList.length; i++) {
            if(!questionList[i].equals("")){
            QuestionReader questionReader = new ReadQuestion(Integer.parseInt(questionList[i]));
            result[i] = questionReader.read().toString();
            }
        }
        return result;

    }
}
