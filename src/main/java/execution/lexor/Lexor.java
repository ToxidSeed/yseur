package execution.lexor;

import execution.plan.Token;
import java.util.ArrayList;
import java.util.List;

public class Lexor {
    private String script;
    private int currentIndex;
    private List<Token> tokenList = new ArrayList<Token>();

    public void setScript(String script) {
        this.script = script.trim();
    }

    public String getScript() {
        return script;
    }

    public void parse() throws Exception {
        this.startParse();
    }
    private void startParse() throws Exception {
        while(this.currentIndex < this.script.length() && this.currentIndex != -1){
            this.findNextToken(this.getCurrentChar());
            this.next();
        }
    }
    private void findNextToken(Character c) throws Exception {
        switch (c){
            case '=':
                //Equal Token
                EQUAL.findToken(this,c);
                break;
            case ',':
                //Colon Token
                COLON.findToken(this,c);
                break;
            case '|':
                //Concat Token
                CONCAT.findToken(this,c);
                break;
            case '\'':
                //String literal token
                StringLiteral.findToken(this,c);
                break;
            case '<':
                //Podría ser Menor que, menor o igual que o diferente
                LessTHAN.findToken(this,c);
                break;
            case '¬': case '!':
                //Puede ser diferente o menor o menor igual
                NotEQUAL.findToken(this,c);
                break;
            case '>':
                //Podria ser, mayor que, mayor o igual que
                GreaterTHAN.findToken(this, c);
                break;
            case '(':
                //LPAREN token
                LPAREN.findToken(this,c );
                break;
            case ')':
                //RPAREN token
                RPAREN.findToken(this, c);
                break;
            default:
                FIELD.findToken(this,c);
                //Se procesan todos como campo y luego la clase evalua el tipo verdadero
                break;
        }
    }

    public Character getCurrentChar(){
        if(this.currentIndex < this.getScript().length() && this.currentIndex != -1){
            return this.script.charAt(this.currentIndex);
        }else{
            return null;
        }
    }

    public Character getNextChar(){
        int nextIndex = currentIndex+1;
        if(nextIndex < this.script.length()){
            return this.script.charAt(nextIndex);
        }else{
            return null;
        }
    }

    public Lexor next(){
        int nextIndex = this.currentIndex+1;
        if(nextIndex < this.getScript().length()){
            this.currentIndex++;
        }else{
            this.currentIndex = -1;
        }
        return this;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void addToken(Token token){
        this.tokenList.add(token);
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void printListToken(){
        String message = "";
        for(Token token: this.tokenList){
            message = String.format("Tipo: %s Valor: %s length: %s", token.getType(), token.getValue(), token.getValue().length());
            System.out.println(message);
        }
    }
}
