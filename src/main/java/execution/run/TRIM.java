package execution.run;

import execution.plan.Token;

import java.util.List;

public class TRIM {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.FUNCTION_TRIM){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }
    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Solo tiene un argumento que es una expression string
        String stringExpression = arguments.get(0).getValue();
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(this.makeTrim(stringExpression));
    }
    private String makeTrim(String stringExpression){
        return stringExpression.trim();
    }

}
