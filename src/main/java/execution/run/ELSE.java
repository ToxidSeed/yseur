package execution.run;
import execution.plan.Token;

import java.util.ArrayList;
import java.util.List;
public class ELSE extends RunnerBase{
    public ELSE(){
        super(Token.ELSE);
    }


    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();

        //Primer argumento es un string
        String stringExpression = arguments.get(0).getValue();

        //Eliminar hojas
        this.setResponse(stringExpression);
    }
}
