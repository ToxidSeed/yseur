package execution.run;

import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;

public class CASE extends RunnerBase{

    public CASE() {
        super(Token.CASE);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        String response = null;
        for(Token token : arguments){
            if(token.getType() == Token.RESPONSE){
                response = token.getValue();
                break;
            }
        }
        //setear respuesta
        this.setResponse(response);
    }
}
