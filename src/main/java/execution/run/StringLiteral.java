package execution.run;

import execution.plan.Token;

public class StringLiteral {
    public void run(Token token) throws Exception {
        if(token.getType() != Token.STRING_LITERAL){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        token.setType(Token.RESPONSE);
        token.setValue(this.make(token.getValue()));
    }
    private String make(String stringExpression){
        String response = stringExpression.substring(1,stringExpression.length()-1);
        return response;
    }
}
