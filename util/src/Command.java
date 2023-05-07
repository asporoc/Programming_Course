public class Command {
    public enum Operator{c,d,r,u,p,Error}
    Operator operator;
    String name;
    String[] commandArgs;
    public Command(String text) {
        if (text.length() == 2) {

            String op = text.substring(0, 2);
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
        }else if(text.contains(" ")){
            commandArgs = text.split(" ");
        }else if(text.equals("")){
            this.operator = Operator.Error;
        }else{
            name = text;
        }
    }
}

