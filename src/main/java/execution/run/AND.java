package execution.run;
import execution.plan.Token;

import java.util.List;

public class AND {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.AND){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }
    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //primer argumento es un booleano almacenado como un string
        Boolean leftBoolean = Boolean.parseBoolean(arguments.get(0).getValue());

        //Segundo argumento es otro booleano como string
        Boolean rightBoolean = Boolean.parseBoolean(arguments.get(1).getValue());

        //procesa la condicion
        String response = this.make(leftBoolean, rightBoolean);

        //Eliminar hojas
        this.mainToken.setChilds(null);
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(response);
    }

    private String make(Boolean left, Boolean right){
        if(left && right){
            return "true";
        }else{
            return "false";
        }
    }
}
