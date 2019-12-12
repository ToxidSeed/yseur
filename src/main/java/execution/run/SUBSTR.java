package execution.run;

import execution.plan.Token;

import java.text.ParseException;
import java.util.List;

public class SUBSTR extends RunnerBase{
    public SUBSTR(){
        super(Token.SUBSTR);
    }
    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //primer argumento es la cadena de la cual queremos hacer el substring
        String stringExpression = arguments.get(0).getValue();

        //segundo argumento es la posicion inicial que queremos hacer el substring
        String start = arguments.get(1).getValue();

        //tercer argumento es la cantidad de caracteres que se quiere extraer
        String len = arguments.get(2).getValue();

        String response = this.make(stringExpression, start, len);

        this.setResponse(response);
    }

    private String make(String stringExpression, String start, String len){
        if(stringExpression == null){
            return null;
        }

        int intStart = Integer.parseInt(start) -1;
        int intLen = Integer.parseInt(len);
        int lastIndex = intStart + intLen;

        return  stringExpression.substring(intStart, lastIndex);
    }
}
