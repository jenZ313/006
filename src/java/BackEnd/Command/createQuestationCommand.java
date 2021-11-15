package BackEnd.Command;

import BackEnd.Write.Question.QuestionWriter;
import BackEnd.Write.Question.WriteNewQuestion;

//string question name, string question, string answer -> SUCCESS/FAILED
public class createQuestationCommand extends Command {
    private final String name;
    private final String question;
    private final String answer;
    private final int mark;

    public createQuestationCommand(String name, String question, String answer, int mark) {
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.mark = mark;
    }

    @Override
    public Object execute() {
        QuestionWriter questionWriter = new WriteNewQuestion(name, question, answer, mark);
        return questionWriter.set();
    }
}
