package execution.run;

import execution.plan.Token;
import java.util.ArrayList;
import java.util.List;
import com.ibm.is.cc.javastage.api.InputRecord;

public class Runner {
    private List<Token> executionPlan = new ArrayList<Token>();
    private InputRecord inputRecord;

    public void setExecutionPlan(List<Token> executionPlan) {
        this.executionPlan = executionPlan;
    }

    public void setInputRecord(InputRecord inputRecord) {
        this.inputRecord = inputRecord;
    }

    public String execute() throws Exception {
        this.valExecutionPlan();
        Token root = executionPlan.get(0);
        this.evalLeaf(root);
        return root.getValue();
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
            case Token.OR:
                OR ORRunner = new OR();
                ORRunner.run(token);
                break;
            case Token.AND:
                AND ANDRunner = new AND();
                ANDRunner.run(token);
                break;
            case Token.EQUAL:
                EQUAL EQUALRunner = new EQUAL();
                EQUALRunner.run(token);
                break;
            case Token.NOTEQUAL:
                NotEQUAL NotEQUALRunner = new NotEQUAL();
                NotEQUALRunner.run(token);
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



}
