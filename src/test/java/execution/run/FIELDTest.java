package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FIELDTest {
    /**
     * FIELD existe
     **/
    @Test
    public void executeTest_field_existe() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("XXXX");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XXXX",value);
    }

    /**
     * FIELD no existe
     **/
    @Rule
    public ExpectedException fieldNotFoundException = ExpectedException.none();

    @Test
    public void executeTest_field_no_existe() throws Exception {
        fieldNotFoundException.expect(IllegalArgumentException.class);
        fieldNotFoundException.expectMessage("La columna FIELD no existe");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XXXX");
        Mockito.when(record.getValue("FIELD")).thenThrow(IllegalArgumentException.class);

        objRunner.setInputRecord(record);
        objRunner.execute();
    }

    /**
     * FIELD no existe
     **/
    @Test
    public void executeTest_field_null() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD");
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
}
