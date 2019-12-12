package execution.run;

import execution.plan.Token;

import java.util.List;

public class WHEN extends RunnerBase{
    public WHEN(){
        super(Token.WHEN);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un booleano
        Boolean leftBoolean = Boolean.parseBoolean(arguments.get(0).getValue());

        //Segundo argumento es un string
        String right = arguments.get(1).getValue();

        //Peocesa el concatenado
        this.make(leftBoolean, right);

        //Eliminar hojas
        this.mainToken.resetChilds();
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
