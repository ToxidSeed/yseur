package execution.run;

import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;

public class LPAD extends RunnerBase{


    public LPAD() {
        super(Token.FUNCTION_LPAD);
    }

    //LPAD tiene 3 argumentos, si no tiene 3 valores debe devolver error
    protected void evalBranch(){
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
        this.setResponse(response);
    }

    //make LPAD
    private String makePad(String stringExpression, int length, String stringPad){
        if(stringExpression == null){
            return null;
        }

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
