package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class TRIMTest {
    /**
     *TRIM NULL
     **/
    @Test
    public void executeTest_trim_null() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD)");
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
     *TRIM STRING_LITERAL
     **/
    @Test
    public void executeTest_trim_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(' STRING_LITERAL ')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("STRING_LITERAL",value);
    }

    /**
     *TRIM NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_trim_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(45)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("45",value);
    }

    /**
     *TRIM FIELD
     **/
    @Test
    public void executeTest_trim_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(" valor del field ");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("valor del field",value);
    }

    /**
     *TRIM TRIM
     **/
    @Test
    public void executeTest_trim_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(TRIM(FIELD))");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(" trim anidado ");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("trim anidado",value);
    }

    /**
     *TRIM LPAD
     **/
    @Test
    public void executeTest_trim_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(LPAD(FIELD,10,' '))");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("X");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("X",value);
    }
    /**
     *TRIM CONCAT
     **/
    @Test
    public void executeTest_trim_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD1||FIELD2)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("   B");
        Mockito.when(record.getValue("FIELD2")).thenReturn("A   ");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("BA",value);
    }
    /**
     *TRIM NVL
     **/
    @Test
    public void executeTest_trim_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(NVL(FIELD,' HOLA'))");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);
        //Mockito.when(record.getValue("FIELD2")).thenReturn("A   ");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("HOLA",value);
    }

    /**
     *TRIM NVL
     **/
    @Test
    public void executeTest_trim_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(SUBSTR(FIELD,6,3))");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("     SUBSTR_ESPACIOS");
        //Mockito.when(record.getValue("FIELD2")).thenReturn("A   ");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("SUB",value);
    }
}
