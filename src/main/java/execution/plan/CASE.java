package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class CASE {
    private TokenTreeFactory treeFactory;
    private Token mainToken;

    public void setObjFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token caseToken) throws Exception {
        this.mainToken = caseToken;
        this.makeConditions();
    }
    public void makeConditions() throws Exception {
        Token nextCondition = treeFactory.getNextToken(mainToken);
        evalCondition(nextCondition);
        this.addChild(nextCondition);

        //If not last token keep going ahead
        if(!this.isLastToken(nextCondition)){
            this.makeConditions();
        }
    }

    private void evalCondition(Token token) throws Exception {
        List<Integer> expectedConditions = new ArrayList<Integer>();
        expectedConditions.add(Token.WHEN);
        expectedConditions.add(Token.ELSE);
        if(!expectedConditions.contains(token.getType())){
            String exMessage = String.format("Unexpected execution.plan.Token %s",token.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(token);
    }

    public void addChild(Token child){
        Token childToAdd;
        if(child.getRootParent() != null){
            childToAdd = child.getRootParent();
        }else{
            childToAdd = child;
        }
        treeFactory.removeFromTree(childToAdd);
        this.mainToken.addChild(childToAdd);
    }
    private boolean isLastToken(Token token){
        Token rootParent;
        if(token.getRootParent() != null){
            rootParent = token.getRootParent();
        }else{
            rootParent = token;
        }
        return treeFactory.isLastToken(rootParent);
    }
}
