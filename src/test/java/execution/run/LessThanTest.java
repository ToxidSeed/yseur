package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LessThanTest {
    /**
     * FIELD < NULL
     **/
    @Test
    public void executeTest_field_eq_null() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 < FIELD2");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("5");
        Mockito.when(record.getValue("FIELD2")).thenReturn(null);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD < FIELD
     **/
    @Test
    public void executeTest_field_lt_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 < CAMPO2");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(5);
        Mockito.when(record.getValue("CAMPO2")).thenReturn("20");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * FIELD < STRING_LITERAL
     **/
    @Test
    public void executeTest_field_eq_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 < '0'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(15);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD < NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_field_eq_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 < 0");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(15);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD < LPAD
     **/
    @Test
    public void executeTest_field_eq_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 < LPAD(CAMPO2,10,'3')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(50);
        Mockito.when(record.getValue("CAMPO2")).thenReturn("2");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * FIELD < TRIM
     **/
    @Test
    public void executeTest_field_eq_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 < TRIM(CAMPO1)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("      20");
        Mockito.when(record.getValue("CAMPO2")).thenReturn("1");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * FIELD < CONCAT
     **/
    @Test
    public void executeTest_field_eq_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 < CAMPO2||CAMPO3");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("50");
        Mockito.when(record.getValue("CAMPO2")).thenReturn("4");
        Mockito.when(record.getValue("CAMPO3")).thenReturn("1");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD < NVL
     **/
    @Test
    public void executeTest_field_eq_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 < NVL(CAMPO2,35)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(50);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(null);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD < SUBSTR
     **/
    @Test
    public void executeTest_field_eq_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 < SUBSTR(FIELD,2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("30");
        Mockito.when(record.getValue("FIELD")).thenReturn("5000");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }
}
