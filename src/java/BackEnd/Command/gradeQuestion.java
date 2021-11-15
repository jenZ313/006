package BackEnd.Command;

import BackEnd.Write.Answer.AnswerWriter;
import BackEnd.Write.Answer.WriteMark;

//int answer id, int mark --> SUCCESS/FAILED
public class gradeQuestion extends Command {
    private final int answerID;
    private final int mark;

    public gradeQuestion(int answerID, int mark) {
        this.answerID = answerID;
        this.mark = mark;
    }

    @Override
    public Object execute() {
        AnswerWriter answerWriter = new WriteMark(answerID, mark);
        return answerWriter.set();
    }
}
