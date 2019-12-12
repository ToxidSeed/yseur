package execution.run;

import execution.plan.Token;

import java.util.List;

public class NVL extends RunnerBase{

    public NVL(){
        super(Token.NVL);
    }


    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //primer argumento es un valor que puede ser nulo
        String stringExpression = arguments.get(0).getValue();

        //Segundo argumento es una cadena que reemplaza el primer valor
        String stringReplacement = arguments.get(1).getValue();

        //procesa la condicion
        String response = this.make(stringExpression, stringReplacement);

        this.setResponse(response);
    }

    private String make(String stringExpression, String stringReplacement){
        String response;
        if(stringExpression == null){
            response = stringReplacement;
        }else{
            response = stringExpression;
        }
        return response;
    }
}
