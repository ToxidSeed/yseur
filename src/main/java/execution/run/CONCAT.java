package execution.run;

import execution.plan.Token;

import java.util.List;

public class CONCAT {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.OPERATOR_CONCAT){
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
    private String make(String leftStringExpression, String rightStringExpression  ){
        String response = String.format("%s%s",leftStringExpression,rightStringExpression);
        return response;
    }
}
