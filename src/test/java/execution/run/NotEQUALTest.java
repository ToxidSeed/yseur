package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class NotEQUALTest {
    /**
     * FIELD != NULL
     **/
    @Test
    public void executeTest_field_eq_null() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 != FIELD2");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD2")).thenReturn(null);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }
    /**
     * FIELD != FIELD
     **/
    @Test
    public void executeTest_cumple_condicion_1() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 != CAMPO1");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != STRING_LITERAL
     **/
    @Test
    public void executeTest_field_eq_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 != '0'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_field_eq_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 != 0");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        //Mockito.when(record.getValue("CAMPO2")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != LPAD
     **/
    @Test
    public void executeTest_field_eq_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 != LPAD(CAMPO1,10,'0')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(1);
        Mockito.when(record.getValue("CAMPO2")).thenReturn("0000000001");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != TRIM
     **/
    @Test
    public void executeTest_field_eq_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO2 != TRIM(CAMPO1)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("      1");
        Mockito.when(record.getValue("CAMPO2")).thenReturn("1");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != CONCAT
     **/
    @Test
    public void executeTest_field_eq_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 != CAMPO2||CAMPO3");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("00001111");
        Mockito.when(record.getValue("CAMPO2")).thenReturn("0000");
        Mockito.when(record.getValue("CAMPO3")).thenReturn("1111");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != NVL
     **/
    @Test
    public void executeTest_field_eq_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 != NVL(CAMPO2,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(".");
        Mockito.when(record.getValue("CAMPO2")).thenReturn(null);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }

    /**
     * FIELD != SUBSTR
     **/
    @Test
    public void executeTest_field_eq_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 != SUBSTR(FIELD,2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("BC");
        Mockito.when(record.getValue("FIELD")).thenReturn("ABCD");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("false",value);
    }
}
