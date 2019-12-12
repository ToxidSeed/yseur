package execution.run;

import execution.plan.Token;

import java.util.List;

public class TRIM extends RunnerBase{

    public TRIM() {
        super(Token.FUNCTION_TRIM);
    }

    protected void evalBranch(){
        List<Token> arguments = this.mainToken.getChilds();
        //Solo tiene un argumento que es una expression string
        String stringExpression = arguments.get(0).getValue();
        String response = this.makeTrim(stringExpression);
        this.setResponse(response);
    }
    private String makeTrim(String stringExpression){
        if(stringExpression == null){
            return null;
        }else{
            return stringExpression.trim();
        }
    }
}
