package execution.run;

import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;

public class IN extends RunnerBase{
    public IN() {
        super(Token.IN);
    }
    //LPAD tiene 3 argumentos, si no tiene 3 valores debe devolver error
    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String stringExpression = arguments.get(0).getValue();
        //Removemos la referencia en la lista
        arguments.remove(0);

        String response = this.make(stringExpression, arguments);

        //Eliminar hojas
        this.setResponse(response);
    }

    //make LPAD
    private String make(String stringExpression, List<Token> listToken){
        if(stringExpression == null){
            return null;
        }

        List<String> listValues = new ArrayList<String>();
        //Convertimos la lista de tokens a una de valores
        for(Token token: listToken){
            listValues.add(token.getValue());
        }

        if(listValues.contains(stringExpression)){
            return "true";
        }else{
            return "false";
        }

    }

}
