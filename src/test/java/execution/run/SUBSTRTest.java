package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class SUBSTRTest {
    /**
     *SUBSTR NULL NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_nvl_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD,2,2)");
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
        assertNull(value);
    }

    /**
     *SUBSTR FIELD NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD,2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("SUBSTR");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("UB",value);
    }

    /**
     *SUBSTR STRING_LITERAL NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR('PALABRA',2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("SUBSTR");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("AL",value);
    }

    /**
     *SUBSTR NUMERIC_LITERAL NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(5444222,2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("SUBSTR");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("44",value);
    }

    /**
     *SUBSTR TRIM NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(TRIM(FIELD),2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("     TRIMED");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("RI",value);
    }

    /**
     *SUBSTR LPAD NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(LPAD(FIELD,5,'.'),2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("XX");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("..",value);
    }

    /**
     *SUBSTR CONCAT NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD1||FIELD2,2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("AA");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XA",value);
    }

    /**
     *SUBSTR NVL SUNMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(NVL(FIELD,'XABXXXX'),2,2)");
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
        assertEquals("AB",value);
    }

    /**
     *SUBSTR SUBSTR NUMERIC_LITERAL NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_substr_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(SUBSTR(FIELD,1,4),2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("ABCDEF");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("BC",value);
    }


}
