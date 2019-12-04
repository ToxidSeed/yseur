package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class Token {
    private Token parent = null;
    private int type = 0;
    private String value = "";
    private List<Token> childs = new ArrayList<Token>();

    Token(){

    }
    public Token(int type, String value){
        this.type = type;
        this.value = value;
    }

    public Token getParent() {
        return parent;
    }

    public void setParent(Token parent) {
        this.parent = parent;
    }

    public static final int UNDEFINED = 0;
    public static final int STRING_LITERAL = 1;
    public static final int NUMERIC_LITERAL = 2;
    public static final int FIELD = 3;
    public static final int FUNCTION_LPAD = 4;
    public static final int FUNCTION_TRIM = 5;
    public static final int OPERATOR_CONCAT = 6;
    public static final int COLON = 7;
    public static final int LPAREN = 8;
    public static final int RPAREN = 9;
    public static final int CASE = 10;
    public static final int WHEN = 11;
    public static final int ELSE = 12;
    public static final int AND = 13;
    public static final int OR = 14;
    public static final int EQUAL = 15;
    public static final int END = 16;
    public static final int CONDITION = 17;
    public static final int THEN = 18;
    public static final int NOT = 19;
    public static final int NOTEQUAL = 20;
    public static final int RESULT = 21;
    public static final int LESS_THAN = 22;
    public static final int LESS_THAN_OR_EQUAL = 23;
    public static final int GREATER_THAN = 24;
    public static final int GREATER_THAN_OR_EQUAL = 25;
    public static final int SUBSTR = 26;
    public static final int NVL = 27;
    public static final int IN = 28;
    public static final int LIKE = 29;

    //Auxiliar Token Types
    public static final int RESPONSE = 30;
    public static final int FALSE = 31;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public void buildValue(Character value){
        this.value = this.value + value.toString();
    }


    public List<Token> getChilds() {
        return childs;
    }
    public void setChilds(List<Token> childs) {
        this.childs = childs;
    }
    public void setChild(int index, Token token){
        token.setParent(this);
        this.childs.set(index,token);
    }


    public void addChild(Token token){
        token.setParent(this);
        this.childs.add(token);
    }
    public void addChildAsReference(Token token){
        this.childs.add(token);
    }



    public Token getRootParent(){
        Token rootParent;
        Token currentParent = this.parent;
        Token nextParent;
        if(currentParent!=null){
            nextParent = currentParent.getRootParent();
        }else{
            return null;
        }
        if(nextParent==null){
            rootParent = currentParent;
        }else{
            rootParent = nextParent;
        }
        return rootParent;
    }
}