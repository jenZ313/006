package BackEnd.Command;

import BackEnd.Write.Answer.AnswerWriter;
import BackEnd.Write.Answer.AutoGrade;

//void --> mark/FAILED
public class autoGrade extends Command {

    public autoGrade() {
    }

    @Override
    public Object execute() {
        AnswerWriter answerWriter = new AutoGrade();
        return answerWriter.set();

    }
}
