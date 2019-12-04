package execution.plan;

import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TokenTest {
    @Test
    public void getParentRootTest(){
        Token child = new Token(Token.FIELD, "CHILD_LVL1");
        Token parent = child.getRootParent();
        assertNull(parent);
    }

    @Test
    public void getParentRootTest1(){
        Token child = new Token(Token.FIELD, "CHILD_LVL1");
        Token parent1 = new Token(Token.FIELD, "PARENT1");
        Token parent2 = new Token(Token.FIELD, "PARENT2");
        parent1.addChild(child);
        parent2.addChild(parent1);

        Token parent = child.getRootParent();
        assertEquals(parent2, parent);
    }
}
