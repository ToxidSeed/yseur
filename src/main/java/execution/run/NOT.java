package execution.run;

import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;

public class NOT extends RunnerBase{
    public NOT() {
        super(Token.NOT);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String booleanExpression =  arguments.get(0).getValue();

        String response = this.make(booleanExpression);

        //Eliminar hojas
        this.setResponse(response);
    }

    private String make(String booleanExpression){
        if(booleanExpression == null){
            return null;
        }

        if(Boolean.parseBoolean(booleanExpression)){
            return "false";
        }else{
            return "true";
        }
    }

}
