package BackEnd.Read.Test;

public class ReadQuestionsID extends TestReader {
    private final int ID;

    public ReadQuestionsID(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String temp = (String) new ReadQuestions(ID).read();
        if (temp.equals(FAILED + "")) {
            return FAILED;
        }
        String[] arr = temp.trim().split(",");
        return convert(arr);

    }

}
