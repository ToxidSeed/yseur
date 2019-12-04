package execution.run;

import execution.plan.Token;

import java.util.List;

public class WHEN {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.WHEN){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }

    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un booleano
        Boolean leftBoolean = Boolean.parseBoolean(arguments.get(0).getValue());

        //Segundo argumento es un string
        String right = arguments.get(1).getValue();

        //Peocesa el concatenado
        this.make(leftBoolean, right);

        //Eliminar hojas
        this.mainToken.setChilds(null);
    }
    private void make(Object left, Object right){
        if(Boolean.parseBoolean(left.toString())){
            this.mainToken.setType(Token.RESPONSE);
            this.mainToken.setValue(right.toString());
        }else{
            this.mainToken.setType(Token.FALSE);
            this.mainToken.setValue("");
        }
    }
}
