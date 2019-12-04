package execution.run;

import execution.plan.Token;

import java.util.List;

public class THEN {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.THEN){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }
    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un booleano
        String stringExpression = arguments.get(0).getValue();

        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(stringExpression);
        this.mainToken.setChilds(null);
    }
}
