package execution.run;

import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;

public class LIKE extends RunnerBase{
    public LIKE(){
        super(Token.LIKE);
    }
    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String stringExpression = arguments.get(0).getValue();

        //Segundo argumento es el patron
        String stringPattern = arguments.get(1).getValue();

        String response = this.make(stringExpression, stringPattern);

        //Eliminar hojas
        this.setResponse(response);
    }

    //make LPAD
    private String make(String stringExpression, String pattern){
        if(stringExpression == null){
            return null;
        }
        String javaPattern = "^"+pattern.replace("%",".*")+"$";
        if (stringExpression.matches(javaPattern)){
            return "true";
        }else{
            return "false";
        }
    }

}
