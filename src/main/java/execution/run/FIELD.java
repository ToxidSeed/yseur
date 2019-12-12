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

        Object recordValue;
        try{
            recordValue = record.getValue(mainToken.getValue());
        }catch(IllegalArgumentException ex){
            String exMessage = String.format("La columna %s no existe",mainToken.getValue());
            throw new IllegalArgumentException(exMessage);
        }
        if(recordValue == null){
            this.mainToken.setValue(null);
        }else{
            String fieldValue = recordValue.toString();
            this.mainToken.setValue(fieldValue);
        }
    }
}
