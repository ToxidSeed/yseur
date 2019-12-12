package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class NVLTest {
    /**
     *NVL FIELD STRING_LITERAL
     **/
    @Test
    public void executeTest_nvl_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(FIELD,'REP')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("REP",value);
    }

    /**
     *NVL TRIM STRING_LITERAL
     **/
    @Test
    public void executeTest_nvl_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(TRIM(FIELD),'REP')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("REP",value);
    }

    /**
     *NVL LPAD STRING_LITERAL
     **/
    @Test
    public void executeTest_nvl_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(LPAD(FIELD,10,'.'),'REP')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("REP",value);
    }

    /**
     *NVL CONCAT STRING_LITERAL
     **/
    @Test
    public void executeTest_nvl_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(FIELD1||FIELD2,'REP')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);
        Mockito.when(record.getValue("FIELD2")).thenReturn("NO NULL");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("REP",value);
    }

    /**
     *NVL NVL STRING_LITERAL
     **/
    @Test
    public void executeTest_nvl_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(NVL(FIELD,FIELD2),'REP')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);
        Mockito.when(record.getValue("FIELD2")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("REP",value);
    }

    /**
     *NVL SUBSTR STRING_LITERAL
     **/
    @Test
    public void executeTest_nvl_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(SUBSTR(FIELD,3,3),'REP')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("REP",value);
    }
}
