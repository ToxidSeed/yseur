package execution.run;

import execution.plan.Token;

import java.util.List;

public class OR extends RunnerBase{


    public OR() {
        super(Token.OR);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //primer argumento es un booleano almacenado como un string
        Boolean leftBoolean = Boolean.parseBoolean(arguments.get(0).getValue());

        //Segundo argumento es otro booleano como string
        Boolean rightBoolean = Boolean.parseBoolean(arguments.get(1).getValue());

        //procesa la condicion
        String response = this.make(leftBoolean, rightBoolean);

        //Eliminar hojas
        this.setResponse(response);
    }

    private String make(Boolean left, Boolean right){
        if(left || right){
            return "true";
        }else{
            return "false";
        }
    }

}
