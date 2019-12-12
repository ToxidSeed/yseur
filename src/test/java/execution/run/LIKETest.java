package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LIKETest {
    /**
     * NULL LIKE
     **/
    @Test
    public void executeTest_null_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 LIKE '%ASA%'");
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
     * FIELD LIKE %TEXTO%
     **/
    @Test
    public void executeTest_field_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("PASATEXTORRRR");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * FIELD LIKE TEXTO%
     **/
    @Test
    public void executeTest_field_like_right() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 LIKE 'TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("TEXTORRRR");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * FIELD LIKE %TEXTO
     **/
    @Test
    public void executeTest_field_like_left() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 LIKE '%TEXTO'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("AAAAAATEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * STRING_LITERAL LIKE %TEXTO%
     **/
    @Test
    public void executeTest_str_lit_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'aaaTEXTObbbb' LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("FIELD1")).thenReturn("AAAAAATEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * NUMERIC_LITERAL LIKE %TEXTO%
     **/
    @Test
    public void executeTest_num_lit_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("544456999 LIKE '%569%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("FIELD1")).thenReturn("AAAAAATEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }
    /**
     * LPAD LIKE
     **/
    @Test
    public void executeTest_lpad_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD,10,'.') LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("TEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * TRIM LIKE
     **/
    @Test
    public void executeTest_trim_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD) LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("     TEXTO...");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * CONCAT LIKE
     **/
    @Test
    public void executeTest_concat_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1||FIELD2 LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("AAAAA");
        Mockito.when(record.getValue("FIELD2")).thenReturn("TEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }
    /**
     * NVL LIKE
     **/
    @Test
    public void executeTest_nvl_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(FIELD,'AAAAATEXTOBBB') LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);
        //Mockito.when(record.getValue("FIELD2")).thenReturn("TEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * SUBSTR LIKE
     **/
    @Test
    public void executeTest_substr_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD,2,15) LIKE '%TEXTO%'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("AAAAAABBBBBTEXTOXXX");
        //Mockito.when(record.getValue("FIELD2")).thenReturn("TEXTO");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }
}
