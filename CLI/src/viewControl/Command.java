package viewControl;

public class Command {
    public enum Operator {c, d, r, u, p, Error}

    public Operator operator;
    public String commandoString;

    public int commandoInt;

    public Command(String text) {
        String op = text.substring(0, 2);
        try {
                commandoString=text.substring(3);
        } catch (Exception e) {
            commandoString = "";
        }
        switch (op) {
            case ":c":
                this.operator = Operator.c;
                break;
            case ":d":
                this.operator = Operator.d;
                break;
            case ":r":
                this.operator = Operator.r;
                break;
            case ":u":
                this.operator = Operator.u;
                break;
            case ":p":
                this.operator = Operator.p;
                break;
            default:
                this.operator = Operator.Error;
                break;
        }

    }

}

