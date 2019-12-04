package execution.run;

import execution.plan.Token;

import java.util.List;

public class CASE {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.CASE){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }
    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        String response = null;
        for(Token token : arguments){
            if(token.getType() == Token.RESPONSE){
                response = token.getValue();
                break;
            }
        }
        //Eliminar hojas
        this.mainToken.setChilds(null);
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(response);
    }
}
