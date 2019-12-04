package execution.run;
import execution.plan.Token;

import java.util.List;
public class NotEQUAL {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.NOTEQUAL){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }

    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String leftStringExpression = arguments.get(0).getValue();

        //Segundo argumento es un string
        String rightStringExpression = arguments.get(1).getValue();

        //Peocesa el concatenado
        String response = this.make(leftStringExpression, rightStringExpression);

        //Eliminar hojas
        this.mainToken.setChilds(null);
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(response);
    }
    private String make(Object left, Object right){
        if(left != right){
            return "true";
        }else{
            return "false";
        }
    }
}
