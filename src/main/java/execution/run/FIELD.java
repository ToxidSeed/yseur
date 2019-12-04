package execution.run;
import execution.plan.Token;
import com.ibm.is.cc.javastage.api.InputRecord;

public class FIELD {
    private Token mainToken;
    public void run(Token token, InputRecord record) throws Exception{
        this.mainToken = token;
        if(token.getType() != Token.FIELD){
            String exMessage = String.format("Token inesperado %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.mainToken.setType(Token.RESPONSE);
        this.mainToken.setValue(record.getValue(mainToken.getValue()).toString());
    }
}
