package execution.run;

import execution.plan.Token;

import java.math.BigDecimal;
import java.util.List;

public class GreaterThanOrEqual extends RunnerBase{

    public GreaterThanOrEqual(){
        super(Token.GREATER_THAN_OR_EQUAL);
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

        BigDecimal leftDecimal = new BigDecimal(left.toString());

        BigDecimal rightDecimal = new BigDecimal(right.toString());

        int result = leftDecimal.compareTo(rightDecimal);

        if( result >= 0){
            return "true";
        }else{
            return "false";
        }

    }

}
