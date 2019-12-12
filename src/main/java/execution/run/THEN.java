package execution.run;

import execution.plan.Token;

import java.util.List;

public class THEN extends RunnerBase{
    public THEN(){
        super(Token.THEN);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String stringExpression = arguments.get(0).getValue();

        //Eliminar hojas
        this.setResponse(stringExpression);
    }
}
