package execution.plan;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
public class AND_Backup {
    private TokenTreeFactory objFactory;
    private Token mainToken = null;

    public void setObjFactory(TokenTreeFactory objFactory) {
        this.objFactory = objFactory;
    }

    public Token makeBranch(Token andToken) throws Exception {
        this.mainToken = andToken;
        this.addLeftCondition(mainToken);
        this.addRightCondition(mainToken);
        /**
         * AND siempre absorvera los tokens a ambos lados por lo que se evalúa el siguiente token
         * cuando se hayan agregado ambas condiciones
         * */
        this.evalNextToken();
        return mainToken;
    }
    private void addLeftCondition(Token tokenAnd) throws Exception{
        Token tokenLeft = objFactory.getPrevToken(tokenAnd);
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.AND);
        expectedTokens.add(Token.OR);
        expectedTokens.add(Token.EQUAL);
        expectedTokens.add(Token.NOTEQUAL);
        expectedTokens.add(Token.LESS_THAN_OR_EQUAL);
        expectedTokens.add(Token.LESS_THAN);
        expectedTokens.add(Token.GREATER_THAN_OR_EQUAL);
        expectedTokens.add(Token.GREATER_THAN);

        if(!expectedTokens.contains(tokenLeft.getType())){
            String prevTokenValue = objFactory.getPrevToken(tokenLeft).getValue();
            String exMessage =       String.format("Token %s inesperado cerca de %s",tokenLeft.getValue(),prevTokenValue );
            throw new Exception(exMessage);
        }
        tokenAnd.addChild(tokenLeft);
        objFactory.removeFromTree(tokenLeft);
    }
    private void addRightCondition(Token tokenAnd) throws Exception {
        Token rightToken = objFactory.getNextToken(tokenAnd);
        Condition objCondition = new Condition();
        objCondition.setIniToken(rightToken);
        objCondition.setObjFactory(objFactory);
        objCondition.setMove(false);
        Token conditionToken = objCondition.makeBranch();
        mainToken.addChild(conditionToken);
        objFactory.removeFromTree(conditionToken);
    }

    private void evalNextToken() throws Exception {
        Token nextToken = objFactory.getNextToken(mainToken);
        if(nextToken == null){
            return;
        }

        List<Integer> expectedTokenTypes = new ArrayList<Integer>();
        expectedTokenTypes.add(Token.THEN);
        expectedTokenTypes.add(Token.AND);
        expectedTokenTypes.add(Token.OR);

        if(!expectedTokenTypes.contains(nextToken.getType())){
            String strPrevTokenVal = objFactory.getPrevToken(nextToken).getValue();
            String ex_message = String.format("Token %s inesperado cerca de %s", nextToken.getValue(),strPrevTokenVal);
            throw new Exception(ex_message);
        }

        List<Integer> continueEvalTokenTypes = new ArrayList<Integer>();
        continueEvalTokenTypes.add(Token.AND);
        continueEvalTokenTypes.add(Token.OR);

        if(continueEvalTokenTypes.contains(nextToken.getType())){
            objFactory.evaluate(nextToken);
        }
    }


}
