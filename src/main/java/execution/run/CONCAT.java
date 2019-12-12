package execution.run;

import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;

public class CONCAT extends RunnerBase{
    public CONCAT(){
        super(Token.OPERATOR_CONCAT);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String leftStringExpression = arguments.get(0).getValue();

        //Segundo argumento es un string
        String rightStringExpression = arguments.get(1).getValue();

        //Peocesa el concatenado
        String response = this.make(leftStringExpression, rightStringExpression);

        //Eliminar hojas
        this.setResponse(response);
    }
    private String make(String leftStringExpression, String rightStringExpression  ){
        if(leftStringExpression == null || rightStringExpression == null){
            return null;
        }
        return  String.format("%s%s",leftStringExpression,rightStringExpression);

    }
}
