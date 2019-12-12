package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.Token;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunnerTest {
    /**
    * FIELD
    **/
    @Test
    public void executeTest1() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CO_EMPRESA");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CO_EMPRESA")).thenReturn(0004.);
        objRunner.setInputRecord(record);

        String value = objRunner.execute();
        assertEquals(String.valueOf(0004.),value);
    }
    /**
     * STRING_LITERAL
     **/
    @Test
    public void executeTest2() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'STRING_LITERAL'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("CO_EMPRESA")).thenReturn(0004.);
        objRunner.setInputRecord(record);

        String value = objRunner.execute();
        assertEquals("STRING_LITERAL",value);
    }

    /**
     * NUMERIC_LITERAL
     **/
    @Test
    public void executeTest3() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("3544");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("CO_EMPRESA")).thenReturn(0004.);
        objRunner.setInputRecord(record);

        String value = objRunner.execute();
        assertEquals("3544",value);
    }

    /**
     * LPAD_TRIM
     *
     **/
    @Test
    public void executeTest4() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(TRIM(CO_EMPRESA),15,'X')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CO_EMPRESA")).thenReturn("    TRIM");
        objRunner.setInputRecord(record);

        String value = objRunner.execute();
        //XXXXXXXXXXXXX4.0
        assertEquals("XXXXXXXXXXXTRIM",value);
    }

    /**
     * LPAD_TRIM_CONCAT
     *
     **/
    @Test
    public void executeTest5() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(TRIM(CO_EMPRESA||CO_PRODUCTO),15,'X')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CO_EMPRESA")).thenReturn(0004.);
        Mockito.when(record.getValue("CO_PRODUCTO")).thenReturn("55555");
        objRunner.setInputRecord(record);

        String value = objRunner.execute();
        //4.055555
        assertEquals("XXXXXXX4.055555",value);
    }

    @Test
    public void executeTest_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(CO_EMPRESA,5,'0')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("CO_EMPRESA")).thenReturn("0").thenReturn("1");
        objRunner.setInputRecord(record);

        String value = objRunner.execute();
        assertEquals("00000",value);

        Runner newRunner = new Runner();
        newRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());
        newRunner.setInputRecord(record);

        String value2 =  newRunner.execute();
        assertEquals("00001",value2);


    }

}
