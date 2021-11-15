package BackEnd.Read.Test;

import BackEnd.Read.Question.QuestionReader;
import BackEnd.Read.Question.ReadAnswer;


public class ReadAllAnswers extends TestReader {
    private final int testID;

    public ReadAllAnswers(int testID) {
        this.testID = testID;
    }

    @Override
    public Object read() {

        String allQuestionID = (String) new ReadQuestions(testID).read();
        if (allQuestionID.equals(FAILED + "")) {
            return new String[0];
        }

        String[] questionList = allQuestionID.split(",");
        String[] answers = new String[questionList.length];
        for (int i = 0; i < questionList.length; i++) {
            if(!questionList[i].equals("")){
            QuestionReader questionReader = new ReadAnswer(Integer.parseInt(questionList[i]));
            answers[i] =  questionReader.read().toString();
            }
        }
        return answers;
    }

}
