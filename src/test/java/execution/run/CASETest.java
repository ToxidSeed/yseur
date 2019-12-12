package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CASETest {
    /**
     * CASE WHEN THEN
     **/
    @Test
    public void executeTest_case_when() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 THEN FIELD3");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        //tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(60);
        Mockito.when(record.getValue("FIELD2")).thenReturn(60);
        Mockito.when(record.getValue("FIELD3")).thenReturn(200);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("200",value);
    }

    /**
     * CASE WHEN THEN ELSE
     **/
    @Test
    public void executeTest_case_when_else() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 THEN FIELD3 ELSE FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        //tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(60);
        Mockito.when(record.getValue("FIELD2")).thenReturn(70);
        Mockito.when(record.getValue("FIELD3")).thenReturn(200);
        Mockito.when(record.getValue("FIELD4")).thenReturn(500);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("500",value);
    }
    /**
     * CASE WHEN WHEN ELSE
     **/
    @Test
    public void executeTest_case_when_when_else() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 THEN FIELD3 " +
                "WHEN FIELD4 = FIELD5 THEN FIELD6"+
                " ELSE FIELD7");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        //tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(60);
        Mockito.when(record.getValue("FIELD2")).thenReturn(70);
        Mockito.when(record.getValue("FIELD3")).thenReturn(200);
        Mockito.when(record.getValue("FIELD4")).thenReturn(400);
        Mockito.when(record.getValue("FIELD5")).thenReturn(500);
        Mockito.when(record.getValue("FIELD6")).thenReturn(900);
        Mockito.when(record.getValue("FIELD7")).thenReturn(1000);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("1000",value);
    }
}
