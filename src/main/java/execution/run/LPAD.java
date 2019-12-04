package execution.run;

import execution.plan.Token;

import java.util.List;

public class LPAD {
    private Token mainToken;
    public void run(Token token) throws Exception {
        this.mainToken = token;
        if(token.getType() != Token.FUNCTION_LPAD){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.evalBranch();
    }

    //LPAD tiene 3 argumentos, si no tiene 3 valores debe devolver error
    private void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String stringExpression = arguments.get(0).getValue();

        //Segundo argumento es un entero
        Integer length = Integer.parseInt(arguments.get(1).getValue());

        //Tercer argumento es una cadena para hacer el padding
        String stringPad = arguments.get(2).getValue();

        //make LPAD
        String response = this.makePad(stringExpression, length, stringPad);

        //Eliminar hojas
        this.mainToken.setChilds(null);
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(response);
    }

    //make LPAD
    private String makePad(String stringExpression, int length, String stringPad){
        if (stringExpression.length() >= length) {
            return stringExpression;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - stringExpression.length()) {
            sb.append(stringPad);
        }
        sb.append(stringExpression);

        return sb.toString();
    }
}
