package execution.run;

import execution.plan.Token;
import java.util.ArrayList;
import java.util.List;
import com.ibm.is.cc.javastage.api.InputRecord;

public class Runner {
    private List<Token> executionPlan = new ArrayList<Token>();
    private List<Token> originalPlan = new ArrayList<Token>();
    private InputRecord inputRecord;
    private StringBuilder stringTree = new StringBuilder();

    public void setExecutionPlan(List<Token> executionPlan) throws CloneNotSupportedException {
        this.originalPlan = executionPlan;
        this.executionPlan = createExecutionPlanCopy(executionPlan);
    }

    public void setInputRecord(InputRecord inputRecord) {
        this.inputRecord = inputRecord;
    }



    public String execute() throws Exception {
        /**
         * Para propositos de debug
         * */
        stringTree = new StringBuilder();
        String originalTree = tokenTreeAsString(executionPlan,"");
        try{
            this.valExecutionPlan();
            Token root = executionPlan.get(0);
            this.evalLeaf(root);
            return root.getValue();
        }catch(IllegalArgumentException ex){
            throw new IllegalArgumentException(ex.getMessage());
        }catch(Exception ex){
            stringTree = new StringBuilder();
            String stringTree = tokenTreeAsString(executionPlan,"");
            String exceptionMessage = ex.getMessage();
            if(exceptionMessage == null){
                exceptionMessage = "null";
            }
            String exMessage = String.format("Excepcion %s\nArbol de ejecución original\n%sArbol de ejecucion \n%s ",exceptionMessage, originalTree, stringTree);
            throw new RuntimeException(exMessage);
        }

    }



    private List<Token> createExecutionPlanCopy(List<Token> initialList) throws CloneNotSupportedException {
        List<Token> copiedList = this.copyBranch(initialList);
        return copiedList;
    }

    private List<Token> copyBranch(List<Token> tokenList) throws CloneNotSupportedException {

        List<Token> responseList = new ArrayList<Token>();

        for(Token token:tokenList){
            if(token.getChilds().size() == 0){
                Token copiedToken = (Token)token.clone();
                responseList.add(copiedToken);
            }else{
                List<Token> newChilds = this.copyBranch(token.getChilds());
                Token copiedToken = (Token)token.clone();
                copiedToken.setChilds(newChilds);
                responseList.add(copiedToken);
            }
        }
        return responseList;
    }

    private void valExecutionPlan() throws Exception {
        if(this.executionPlan.size() == 0){
            throw new Exception("El plan te ejecucion no se ha generado");
        }

        if(this.executionPlan.size() > 1){
            throw new Exception("El plan te ejecucion no se ha generado de forma correcta");
        }
    }

    private void evalBranch(List<Token> tokenList) throws Exception {
        for (Token token : tokenList) {
            this.evalLeaf(token);
        }
    }

    private void evalLeaf(Token token) throws Exception {
        if(token.getChilds().size() == 0){
            this.runToken(token);
        }else{
            this.evalBranch(token.getChilds());
            this.runToken(token);
        }
    }

    private void runToken(Token token) throws Exception {
        switch (token.getType()){
            case Token.NUMERIC_LITERAL:
                NumericLiteral nLiteralRunner = new NumericLiteral();
                nLiteralRunner.run(token);
                break;
            case Token.STRING_LITERAL:
                StringLiteral sLiteralRunner = new StringLiteral();
                sLiteralRunner.run(token);
                break;
            case Token.FIELD:
                FIELD fieldRunner = new FIELD();
                fieldRunner.run(token, this.inputRecord);
                break;
            case Token.FUNCTION_LPAD:
                LPAD lpadRunner = new LPAD();
                lpadRunner.run(token);
                break;
            case Token.FUNCTION_TRIM:
                TRIM trimRunner = new TRIM();
                trimRunner.run(token);
                break;
            case Token.OPERATOR_CONCAT:
                CONCAT concatRunner = new CONCAT();
                concatRunner.run(token);
                break;
            case Token.NVL:
                NVL nvlRunner = new NVL();
                nvlRunner.run(token);
                break;
            case Token.SUBSTR:
                SUBSTR substrRunner = new SUBSTR();
                substrRunner.run(token);
                break;
            case Token.OR:
                OR ORRunner = new OR();
                ORRunner.run(token);
                break;
            case Token.AND:
                AND ANDRunner = new AND();
                ANDRunner.run(token);
                break;
            case Token.NOT:
                NOT NOTRunner = new NOT();
                NOTRunner.run(token);
                break;
            case Token.EQUAL:
                EQUAL EQUALRunner = new EQUAL();
                EQUALRunner.run(token);
                break;
            case Token.NOTEQUAL:
                NotEQUAL NotEQUALRunner = new NotEQUAL();
                NotEQUALRunner.run(token);
                break;

            case Token.GREATER_THAN:
                GreaterThan gtRunner = new GreaterThan();
                gtRunner.run(token);
                break;

            case Token.GREATER_THAN_OR_EQUAL:
                GreaterThanOrEqual gtoRunner = new GreaterThanOrEqual();
                gtoRunner.run(token);
                break;
            case Token.IN:
                IN inRunner = new IN();
                inRunner.run(token);
                break;
            case Token.LIKE:
                LIKE likeRunner = new LIKE();
                likeRunner.run(token);
                break;

            case Token.LESS_THAN:
                LessThan ltRunner = new LessThan();
                ltRunner.run(token);
                break;
            case Token.LESS_THAN_OR_EQUAL:
                LessThanOrEqual ltoRunner = new LessThanOrEqual();
                ltoRunner.run(token);
                break;
            case Token.CASE:
                CASE CASERunner = new CASE();
                CASERunner.run(token);
                break;
            case Token.WHEN:
                WHEN WHENRunner = new WHEN();
                WHENRunner.run(token);
                break;
            case Token.THEN:
                THEN THENRunner = new THEN();
                THENRunner.run(token);
                break;
            case Token.ELSE:
                ELSE ELSERunner = new ELSE();
                ELSERunner.run(token);
                break;
            case Token.RESULT:
                RESULT RESULTRunner = new RESULT();
                RESULTRunner.run(token);
                break;
            default:
                break;
        }
    }

    private String tokenTreeAsString(List<Token> listToken, String strNivel) throws Exception{
        String localStr = strNivel;
        for(Token token : listToken){
            if(token == null){
                stringTree.append(localStr);
                stringTree.append("null");
                stringTree.append("\n");
            }else{
                stringTree.append(localStr);
                stringTree.append(token.getValue());
                stringTree.append("\n");
                if(token.getChilds() == null){
                    stringTree.append(localStr);
                    stringTree.append("\t");
                    stringTree.append("childs null");
                    stringTree.append("\n");
                    continue;
                }
                if(token.getChilds().size() == 0){
                    stringTree.append(localStr);
                    stringTree.append("\t");
                    stringTree.append("no childs");
                    stringTree.append("\n");
                    continue;
                }
                if(token.getChilds().size() > 0){
                    localStr = localStr+" | ";
                    this.tokenTreeAsString(token.getChilds(), localStr);
                }
            }
        }
        return stringTree.toString();
    }

    /*private void printTokensTree(List<Token> listToken, String nivel){
        String textToPrint = "";
        String tokenValue = "";
        Token token = null;
        String currentLevel = nivel;
        String nextLevel = null;
        for(int i = 0; i<listToken.size();i++){
            token = listToken.get(i);
            textToPrint = String.format("%s %s",currentLevel, token.getValue());
            stringTree.append(textToPrint+"\n");

            if(token.getChilds() == null){
                continue;
            }
            if(token.getChilds().size()>0){
                nextLevel = currentLevel + " | ";
                this.printTokensTree(token.getChilds(), nextLevel);
            }

        }
    }
    public void printTokensTree(){
        this.printTokensTree(executionPlan,"");
    }*/



}
