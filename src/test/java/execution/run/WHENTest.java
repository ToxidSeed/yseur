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
     * Cumple condicion WHEN
     **/
    @Test
    public void executeTest_cumple_condicion_1() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 THEN 'XXX' ELSE 'AAA'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XXX",value);
    }

    /**
     * WHEN
     * EQUAL
     * THEN
     * ELSE
     **/
    @Test
    public void executeTest_cumple_eq() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 THEN 'XXX' ELSE 'AAA'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(1);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("AAA",value);
    }
    /**
     * WHEN
     * IGUAL
     * AND
     * IGUAL
     * THEN
     * ELSE
     **/
    @Test
    public void executeTest_and_eq_and() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 AND CAMPO2 = 3 THEN '03' ELSE 'NO03'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(3);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("03",value);
    }
    /**
     * WHEN
     * IGUAL
     * AND
     * IGUAL
     * THEN
     * ELSE
     **/
    @Test
    public void executeTest_eq_or_eq() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 OR CAMPO2 = 3 THEN '03' ELSE 'NO03'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(5);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(3);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("03",value);
    }
    /**
     * WHEN
     * IGUAL
     * AND
     * IGUAL
     * OR
     * IGUAL
     * THEN
     * ELSE
     **/
    @Test
    public void executeTest_eq_and_eq_or_eq() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 AND CAMPO2 = 3 OR CAMPO3 = 5 THEN '03' ELSE 'NO03'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(3);
        Mockito.when(record.getValue("CAMPO3")).thenReturn(10);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("03",value);
    }
    /**
     * WHEN
     * IGUAL
     * AND
     * IGUAL
     * OR
     * IGUAL
     * THEN
     * ELSE
     * STRING_LITERAL
     **/
    @Test
    public void executeTest_eq_and_eq_or_eq_false() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 AND CAMPO2 = 3 OR CAMPO3 = 5 THEN '03' " +
                "ELSE 'NO03'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(1);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(2);
        Mockito.when(record.getValue("CAMPO3")).thenReturn(10);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("NO03",value);
    }

    /**
     * WHEN
     * IGUAL
     * AND
     * IGUAL
     * OR
     * IGUAL
     * THEN
     * ELSE
     * STRING_LITERAL
     **/
    @Test
    public void executeTest_when_when() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("WHEN CAMPO1 = 0 AND CAMPO2 = 3 OR CAMPO3 = 5 THEN '03' " +
                "WHEN CAMPO1 = 0 AND CAMPO2 = 10 THEN 'XX' "+
                "ELSE 'NO03'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CAMPO1")).thenReturn(0);
        Mockito.when(record.getValue("CAMPO2")).thenReturn(10);
        Mockito.when(record.getValue("CAMPO3")).thenReturn(10);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XX",value);
    }
}
