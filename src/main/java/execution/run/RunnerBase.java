package execution.run;

import execution.plan.Token;

public abstract class RunnerBase {
    protected Token mainToken;
    protected int expectedTokenType;

    public RunnerBase( int expectedTokenType) {
        this.expectedTokenType = expectedTokenType;
    }

    public void setMainToken(Token mainToken) {
        this.mainToken = mainToken;
    }

    public void run(Token token) throws Exception {
        this.setMainToken(token);
        this.valInputToken(token);
        this.evalBranch();
    }

    protected void valInputToken(Token token) throws Exception {
        if(token.getType() != expectedTokenType){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
    }

    protected void setResponse(String response){
        this.mainToken.resetChilds();
        this.mainToken.setValue(response);
        this.mainToken.setType(Token.RESPONSE);
    }

    protected abstract void evalBranch();

}
