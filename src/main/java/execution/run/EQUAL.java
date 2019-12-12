package execution.run;
import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;
public class EQUAL extends RunnerBase{


    public EQUAL() {
        super(Token.EQUAL);
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
    private String make(Object left, Object right){
        if(left == null || right == null){
            return "false";
        }

        if(left.equals(right)){
            return "true";
        }else{
            return "false";
        }
    }
}
