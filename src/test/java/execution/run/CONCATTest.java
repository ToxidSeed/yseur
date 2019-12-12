package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class CONCATTest {

    /**
     *FIELD || NULL
     **/
    @Test
    public void executeTest_nvl_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || FIELD1");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("NONULL");
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertNull(value);
    }

    /**
     *FIELD || FIELD
     **/
    @Test
    public void executeTest_field_concat_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || FIELD1");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD1")).thenReturn("VALOR2");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1VALOR2",value);
    }

    /**
     *FIELD || STRING_LITERAL
     **/
    @Test
    public void executeTest_field_concat_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || 'STR_LIT'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        //Mockito.when(record.getValue("FIELD1")).thenReturn("VALOR2");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1STR_LIT",value);
    }
    /**
     *FIELD || NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_field_concat_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || 5231");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        //Mockito.when(record.getValue("FIELD1")).thenReturn("VALOR2");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR15231",value);
    }

    /**
     *FIELD || TRIM
     **/
    @Test
    public void executeTest_field_concat_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || TRIM(FIELD1)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD1")).thenReturn("     VALOR2");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1VALOR2",value);
    }

    /**
     *FIELD || LPAD
     **/
    @Test
    public void executeTest_field_concat_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || LPAD(FIELD1,5,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD1")).thenReturn("X");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1....X",value);
    }

    /**
     *FIELD || FIELD || FIELD
     **/
    @Test
    public void executeTest_field_concat_field_concat_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || FIELD1 || FIELD2");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD1")).thenReturn("VALOR2");
        Mockito.when(record.getValue("FIELD2")).thenReturn("VALOR3");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1VALOR2VALOR3",value);
    }

    /**
     *FIELD || NVL
     **/
    @Test
    public void executeTest_field_concat_field_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || NVL(FIELD1,'X')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);


        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1X",value);
    }

    /**
     *FIELD || SUBSTR
     **/
    @Test
    public void executeTest_field_concat_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD || SUBSTR(FIELD1,2,2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR1");
        Mockito.when(record.getValue("FIELD1")).thenReturn("SUBSTR");


        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1UB",value);
    }
}
