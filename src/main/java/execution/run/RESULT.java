package execution.run;
import execution.plan.Token;

import java.util.List;
public class RESULT {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.RESULT){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }
    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Despues de procesar todo
        Token token = arguments.get(0);
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(token.getValue());
        this.mainToken.resetChilds();
    }
}
