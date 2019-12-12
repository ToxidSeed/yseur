package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class WHENTest {
    /**
     * WHEN Equal THEN FIELD
     **/
    @Test
    public void executeTest_when_eq_then() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 THEN 'XXX'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XXX",value);
    }

    /**
     * WHEN NotEqual THEN FIELD
     **/
    @Test
    public void executeTest_when_not_eq() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 != 0 THEN 'XXX'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("",value);
    }
    /**
     * WHEN GreatherThan THEN FIELD
     **/
    @Test
    public void executeTest_when_gt() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 > 0 THEN 'XXX'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(20);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XXX",value);
    }

    /**
     * WHEN GreaterTHANOrEqual THEN FIELD
     **/
    @Test
    public void executeTest_when_gto() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 >= 0 THEN 'XXX'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(20);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XXX",value);
    }

    /**
     * WHEN LessTHAN THEN FIELD
     **/
    @Test
    public void executeTest_when_lt() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 < 50 THEN 'AAAA'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(20);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("AAAA",value);
    }

    /**
     * WHEN LessTHANOrEqual THEN FIELD
     **/
    @Test
    public void executeTest_when_lto() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 <= 50 THEN 'AAAA'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(50);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("AAAA",value);
    }


    /**
     *WHEN IN THEN FIELD
     **/
    @Test
    public void executeTest_when_in() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 IN (1,2,3,4) THEN 'VALOR1'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(1);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1",value);
    }

    /**
     *WHEN NOT IN THEN FIELD
     **/
    @Test
    public void executeTest_when_not_in() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 NOT IN (1,2,3,4) THEN 'VALOR1'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(20);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1",value);
    }

    /**
     *WHEN LIKE THEN FIELD
     **/
    @Test
    public void executeTest_when_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 LIKE '%TEXTO%' THEN 'VALOR1'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn("aaaTEXTOBbb");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1",value);
    }

    /**
     *WHEN AND THEN FIELD
     **/
    @Test
    public void executeTest_when_and() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 = FIELD4 THEN 'VALOR1'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD3")).thenReturn("AA");
        Mockito.when(record.getValue("FIELD4")).thenReturn("AA");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR1",value);
    }

    /**
     *WHEN AND IN THEN FIELD
     **/
    @Test
    public void executeTest_when_and_in() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 IN (1,2,3,4) THEN 'VALOR2'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD3")).thenReturn(1);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR2",value);
    }

    /**
     *WHEN AND NOT IN THEN FIELD
     **/
    @Test
    public void executeTest_when_and_not_in() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 NOT IN (1,2,3,4) THEN 'VALOR2'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD3")).thenReturn(20);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR2",value);
    }

    /**
     *WHEN AND LIKE THEN FIELD
     **/
    @Test
    public void executeTest_when_and_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 LIKE '%TEXTO%' THEN 'VALOR2'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD3")).thenReturn("aaaaTEXTObb");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR2",value);
    }

    /**
     *WHEN AND NOT LIKE THEN FIELD
     **/
    @Test
    public void executeTest_when_and_not_like() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 NOT LIKE '%TEXTO%' THEN 'VALOR2'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD3")).thenReturn("RRSSSS");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR2",value);
    }

    /**
     *WHEN OR THEN FIELD
     **/
    @Test
    public void executeTest_when_or() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN FIELD1 = FIELD2 OR FIELD3 = FIELD4 THEN 'VALOR2'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD2")).thenReturn("XX");
        Mockito.when(record.getValue("FIELD3")).thenReturn("FDD");
        Mockito.when(record.getValue("FIELD4")).thenReturn("ZZZ");
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR2",value);
    }
}
