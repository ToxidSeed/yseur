package execution.run;
import execution.plan.Token;

public class NumericLiteral {
    public void run(Token token) throws Exception {
        if(token.getType() != Token.NUMERIC_LITERAL){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        token.setType(Token.RESPONSE);
        token.setValue(token.getValue());
    }
}
