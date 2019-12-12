package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class LPADTest {
    /**
     *LPAD NULL
     **/
    @Test
    public void executeTest_trim_null() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD,10,'.')");
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
     *LPAD STRING_LITERAL
     **/
    @Test
    public void executeTest_lpad_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD('STR_LIT',10,'.')");
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
        assertEquals("...STR_LIT",value);
    }
    /**
     *LPAD NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_lpad_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(0,10,'.')");
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
        assertEquals(".........0",value);
    }
    /**
     *LPAD FIELD
     **/
    @Test
    public void executeTest_lpad_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD,10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("XXXXX");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals(".....XXXXX", value);

    }
    /**
     *LPAD TRIM
     **/
    @Test
    public void executeTest_lpad_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(TRIM(FIELD),10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("    XXX");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals(".......XXX",value);
    }

    /**
     *LPAD LPAD
     **/
    @Test
    public void executeTest_lpad_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(LPAD(FIELD,3,'X'),10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("aa");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals(".......Xaa",value);
    }

    /**
     *LPAD CONCAT
     **/
    @Test
    public void executeTest_lpad_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD1||FIELD2,10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("aa");
        Mockito.when(record.getValue("FIELD2")).thenReturn("bb");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("......aabb",value);
    }

    /**
     *LPAD NVL
     **/
    @Test
    public void executeTest_lpad_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(NVL(FIELD,'X'),10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);
        //Mockito.when(record.getValue("FIELD2")).thenReturn("bb");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals(".........X",value);
    }

    /**
     *LPAD SUBSTR
     **/
    @Test
    public void executeTest_lpad_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(SUBSTR(FIELD,2,2),10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("SUBSTR");
        //Mockito.when(record.getValue("FIELD2")).thenReturn("bb");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("........UB",value);
    }
}
