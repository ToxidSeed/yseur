package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ANDTest {
    /**
     * Equal AND Equal
     **/
    @Test
    public void executeTest_eq_and_eq() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 = FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(20);
        Mockito.when(record.getValue("FIELD2")).thenReturn(20);
        Mockito.when(record.getValue("FIELD3")).thenReturn(50);
        Mockito.when(record.getValue("FIELD4")).thenReturn(50);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND Equal
     **/
    @Test
    public void executeTest_eq_and_not_eq() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 != FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(20);
        Mockito.when(record.getValue("FIELD2")).thenReturn(20);
        Mockito.when(record.getValue("FIELD3")).thenReturn(50);
        Mockito.when(record.getValue("FIELD4")).thenReturn(60);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND GreatherThan
     **/
    @Test
    public void executeTest_eq_and_gt() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 > FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(20);
        Mockito.when(record.getValue("FIELD2")).thenReturn(20);
        Mockito.when(record.getValue("FIELD3")).thenReturn(80);
        Mockito.when(record.getValue("FIELD4")).thenReturn(60);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND GreatherThanOrEqual
     **/
    @Test
    public void executeTest_eq_and_gto() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 > FIELD2 AND FIELD3 >= FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(80);
        Mockito.when(record.getValue("FIELD2")).thenReturn(60);
        Mockito.when(record.getValue("FIELD3")).thenReturn(70);
        Mockito.when(record.getValue("FIELD4")).thenReturn(70);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND LessThan
     **/
    @Test
    public void executeTest_eq_and_lt() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 < FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(80);
        Mockito.when(record.getValue("FIELD2")).thenReturn(80);
        Mockito.when(record.getValue("FIELD3")).thenReturn(50);
        Mockito.when(record.getValue("FIELD4")).thenReturn(70);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND LessThanOrEqual
     **/
    @Test
    public void executeTest_eq_and_lto() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 <= FIELD4");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(80);
        Mockito.when(record.getValue("FIELD2")).thenReturn(80);
        Mockito.when(record.getValue("FIELD3")).thenReturn(50);
        Mockito.when(record.getValue("FIELD4")).thenReturn(70);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND IN
     **/
    @Test
    public void executeTest_eq_and_in() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 IN (1,2,3)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(80);
        Mockito.when(record.getValue("FIELD2")).thenReturn(80);
        Mockito.when(record.getValue("FIELD3")).thenReturn(2);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }

    /**
     * Equal AND NOT IN
     **/
    @Test
    public void executeTest_eq_and_not_in() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 NOT IN (1,2,3)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(80);
        Mockito.when(record.getValue("FIELD2")).thenReturn(80);
        Mockito.when(record.getValue("FIELD3")).thenReturn(15);
        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("true",value);
    }
}
