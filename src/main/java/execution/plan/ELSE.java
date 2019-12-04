package execution.plan;

public class ELSE {
    private TokenTreeFactory treeFactory;
    private Token mainToken;

    public TokenTreeFactory getTreeFactory() {
        return treeFactory;
    }

    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token elseToken) throws Exception{
        this.mainToken = elseToken;
        this.getStringExpression();
    }
    public void getStringExpression() throws Exception {
        Token stringExpression = treeFactory.getNextToken(mainToken);
        treeFactory.evaluate(stringExpression);
        this.addChild(stringExpression);
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
}
