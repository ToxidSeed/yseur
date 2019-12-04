package execution.plan;

import java.util.ArrayList;
import java.util.List;
public class TokenTreeFactory {
    List<Token> listToken = new ArrayList<Token>();
    List<Integer> expectedTypes = new ArrayList<Integer>();
    List<Integer> literals = new ArrayList<Integer>();
    int currentIndex = 0;

    public TokenTreeFactory(){
        literals.add(Token.STRING_LITERAL);
        literals.add(Token.NUMERIC_LITERAL);
    }

    public void setListToken(List<Token> listToken){
        this.listToken = listToken;
    }

    public List<Token> getExecutionPlan() {
        return listToken;
    }

    public Token getNextToken(Token currentToken){
        int index = this.listToken.indexOf(currentToken);
        index++;
        if(index < this.listToken.size()){
            return this.listToken.get(index);
        }else{
            return null;
        }
    }

    public Token getNextToken() throws Exception {
        if(this.listToken.size() == 0){
            throw new Exception("No existe ningun token que evaluar");
        }
        return this.listToken.get(0);
    }

    public Token getPrevToken(Token currentToken){
        int index = this.listToken.indexOf(currentToken);
        if(index <= 0){
            return null;
        }
        index--;
        return this.listToken.get(index);
    }
    public Token getFirstToken(){
        if(this.listToken.size()>0){
            return this.listToken.get(0);
        }else{
            return null;
        }
    }

    public boolean isLastToken(Token token){
        int lastIndex = this.listToken.size()-1;
        int tokenIndex = this.listToken.indexOf(token);
        return lastIndex == tokenIndex;
    }

    public boolean isLiteral(Token token){
        return literals.contains(token.getType());
    }

    public void removeFromTree(Token token){
        int index = this.listToken.indexOf(token);
        if(index == -1){
            this.removeRootParent(token);
        }else{
            this.listToken.remove(index);
        }
    }
    private void removeRootParent(Token token){
        if(token.getRootParent()!=null){
            this.removeFromTree(token);
        }
    }

    public void make() throws Exception {
        Token token = null;
        boolean firstToken = true;
        boolean lastToken = false;
        while(this.listToken.size() > 1 && !lastToken){
            if(firstToken){
                token = this.getFirstToken();
                this.evaluate(token);
                firstToken = false;
                continue;
            }
            token = this.getNextToken(token);
            this.evaluate(token);
            if(isLastToken(token)){
                lastToken = true;
            }
        }
    }

    public void makeRoot() throws Exception{
        Token firstToken = this.getNextToken();
        this.evalExpectedTokens(firstToken);
        Token root = this.getRoot(firstToken);
        this.evaluate(root);
    }

    private void evalExpectedTokens(Token token) throws Exception {
        List<Integer> tokensList = new ArrayList<Integer>();
        tokensList.add(Token.WHEN);
        tokensList.add(Token.NUMERIC_LITERAL);
        tokensList.add(Token.STRING_LITERAL);
        tokensList.add(Token.FIELD);
        tokensList.add(Token.FUNCTION_TRIM);
        tokensList.add(Token.FUNCTION_LPAD);
        if(!tokensList.contains(token.getType())){
            String exMessage = String.format("Invalid token %s",token.getValue());
            throw new Exception(exMessage);
        }
    }

    private Token getRoot(Token token) throws Exception {
        Token root = null;
        if(token.getType()==Token.WHEN){
            root = this.createCASE();
        }else{
            root = this.createResult();
        }
        return root;
    }

    private Token createCASE() throws Exception {
        Token caseToken = new Token(Token.CASE, "CASE");
        this.listToken.add(0,caseToken);
        return caseToken;
    }
    private Token createResult() throws Exception{
        Token resultToken = new Token(Token.RESULT, "RESULT");
        this.listToken.add(0,resultToken);
        return resultToken;
    }

    public Token evaluate(Token token) throws Exception {
        Token responseToken = token;
        switch(token.getType()){
            case Token.NUMERIC_LITERAL:
                NumericLiteral nLiteral = new NumericLiteral();
                nLiteral.setTreeFactory(this);
                nLiteral.makeBranch(token);
                break;
            case Token.STRING_LITERAL:
                StringLiteral sLiteral = new StringLiteral();
                sLiteral.setTreeFactory(this);
                sLiteral.makeBranch(token);
                break;
            case Token.FIELD:
                FIELD field = new FIELD();
                field.setTreeFactory(this);
                field.makeBranch(token);
                break;
            case Token.EQUAL:
                Equal equalFactory = new Equal(this);
                equalFactory.makeBranch(token);
                break;
            case Token.IN:
                IN inFactory = new IN(this);
                inFactory.makeBranch(token);
                break;
            case Token.LIKE:
                LIKE likeFactory = new LIKE(this);
                likeFactory.makeBranch(token);
                break;
            case Token.LESS_THAN:
                LessTHAN lessTHANFactory = new LessTHAN(this);
                lessTHANFactory.makeBranch(token);
                break;
            case Token.LESS_THAN_OR_EQUAL:
                LessThanOrEqual objLessThanOrEqual = new LessThanOrEqual(this);
                objLessThanOrEqual.makeBranch(token);
                break;
            case Token.GREATER_THAN:
                GreaterTHAN greaterThan = new GreaterTHAN(this);
                greaterThan.makeBranch(token);
                break;
            case Token.GREATER_THAN_OR_EQUAL:
                GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(this);
                greaterThanOrEqual.makeBranch(token);
                break;
            case Token.NOTEQUAL:
                NotEqual notEqualFactory = new NotEqual(this);
                notEqualFactory.setTreeFactory(this);
                notEqualFactory.makeBranch(token);
                break;
            case Token.AND:
                AND objAND = new AND(this);
                objAND.makeBranch(token);
                break;
            case Token.OR:
                OR objOR = new OR(this);
                objOR.makeBranch(token);
                break;
            case Token.NOT:
                NOT notFactory = new NOT(this);
                notFactory.makeBranch(token);
                break;
            case Token.CASE:
                CASE objCASE = new CASE();
                objCASE.setObjFactory(this);
                objCASE.makeBranch(token);
                break;
            case Token.RESULT:
                RESULT objResult = new RESULT();
                objResult.setTreeFactory(this);
                objResult.makeBranch(token);
                break;
            case Token.WHEN:
                WHEN whenClause = new WHEN(this);
                whenClause.makeBranch(token);
                break;
            case Token.THEN:
                THEN thenClause = new THEN();
                thenClause.setObjFactory(this);
                thenClause.makeBranch(token);
                break;
            case Token.ELSE:
                ELSE elseClause = new ELSE();
                elseClause.setTreeFactory(this);
                elseClause.makeBranch(token);
                break;
            case Token.FUNCTION_LPAD:
                LPAD lpadFuntion = new LPAD();
                lpadFuntion.setTreeFactory(this);
                lpadFuntion.makeBranch(token);
                break;
            case Token.FUNCTION_TRIM:
                TRIM trimFuntion = new TRIM();
                trimFuntion.setTreeFactory(this);
                trimFuntion.makeBranch(token);
                break;
            case Token.OPERATOR_CONCAT:
                CONCAT concatFunction = new CONCAT();
                concatFunction.setObjFactory(this);
                concatFunction.makeBranch(token);
                break;
            default:
                break;
        }
        return responseToken;
    }

    private void printTokensTree(List<Token> listToken, String nivel){
        String textToPrint = "";
        String tokenValue = "";
        Token token = null;
        String currentLevel = nivel;
        String nextLevel = null;
        for(int i = 0; i<listToken.size();i++){
            token = listToken.get(i);
            textToPrint = String.format("%s %s",currentLevel, token.getValue());
            System.out.println(textToPrint);
            if(token.getChilds().size()>0){
                nextLevel = currentLevel + " | ";
                this.printTokensTree(token.getChilds(), nextLevel);
            }

        }
    }
    public void printTokensTree(){
        List<Token> listTokens = this.listToken;
        this.printTokensTree(listToken,"");
    }
}
