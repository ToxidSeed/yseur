package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class INTest {
    /**
     * NULL IN
     **/
    @Test
    public void executeTest_field_eq_null() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 IN (1,2,3,4,5)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertNull(value);
    }
    /**
     * FIELD IN
     **/
    @Test
    public void executeTest_cumple_condicion_1() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 IN (1,2,3,4,5)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(1);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * STRING_LITERAL IN
     **/
    @Test
    public void executeTest_field_eq_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'FIELD1' IN (1,2,3,4,5)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * NUMERIC_LITERAL IN
     **/
    @Test
    public void executeTest_field_eq_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("5 IN (1,2,3,4,5)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * LPAD IN
     **/
    @Test
    public void executeTest_field_eq_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD,2,'5') IN ('10','20','30','40','50')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("0");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * TRIM IN
     **/
    @Test
    public void executeTest_field_eq_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD) IN ('10','20','30','40','50')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("      30");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * CONCAT IN
     **/
    @Test
    public void executeTest_field_eq_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1||FIELD2 IN ('10','20','30','40','50')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("2");
        Mockito.when(record.getValue("FIELD2")).thenReturn("0");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     *NVL IN
     **/
    @Test
    public void executeTest_field_eq_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(FIELD,'40') IN ('10','20','30','40','50')");
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
        assertEquals("true",value);
    }

    /**
     * SUBSTR IN
     **/
    @Test
    public void executeTest_field_eq_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD,2,2) IN ('10','20','30','40','50')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("4100");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }
}
