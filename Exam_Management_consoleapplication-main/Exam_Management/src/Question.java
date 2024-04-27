public class Question {
    private String id;
    private String name;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String answer;

    public Question(String id, String name, String opt1, String opt2, String opt3, String opt4, String answer) {
        this.id = id;
        this.name = name;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public String getAnswer() {
        return answer;
    }
}

