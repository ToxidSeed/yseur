package execution.lexor;

import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LexorTest {
    /**
     * probando todos los tokens en condicion
     * */
    @Test
    public void startParseTest_Lit() throws Exception {
        Lexor testLexor = new Lexor();
        testLexor.setScript("WHEN CAMPO1 =        'LETRA' AND _CAMPO2 ¬= 344. THEN LPAD(CAMPO4,12,'.')" +
                "WHEN CAMPO3 != 'LETRA' OR TRIM(CAMPO4) = 34 THEN 56" +
                "ELSE 655");
        testLexor.parse();
        testLexor.printListToken();

        int tokenType1 = testLexor.getTokenList().get(0).getType();
        assertEquals(Token.WHEN, tokenType1);
        int tokenType2 = testLexor.getTokenList().get(1).getType();
        assertEquals(Token.FIELD, tokenType2);
        int tokenType3 = testLexor.getTokenList().get(2).getType();
        assertEquals(Token.EQUAL, tokenType3);
        int tokenType4 = testLexor.getTokenList().get(3).getType();
        assertEquals(Token.STRING_LITERAL, tokenType4);
        int tokenType5 = testLexor.getTokenList().get(4).getType();
        assertEquals(Token.AND, tokenType5);
        int tokenType6 = testLexor.getTokenList().get(5).getType();
        assertEquals(Token.FIELD, tokenType6);
        int tokenType7 = testLexor.getTokenList().get(6).getType();
        assertEquals(Token.NOTEQUAL, tokenType7);
        int tokenType8 = testLexor.getTokenList().get(7).getType();
        assertEquals(Token.NUMERIC_LITERAL, tokenType8);
        int tokenType9 = testLexor.getTokenList().get(8).getType();
        assertEquals(Token.THEN, tokenType9);
        int tokenType10 = testLexor.getTokenList().get(9).getType();
        assertEquals(Token.FUNCTION_LPAD, tokenType10);
        int tokenType11 = testLexor.getTokenList().get(10).getType();
        assertEquals(Token.LPAREN, tokenType11);
        int tokenType12 = testLexor.getTokenList().get(11).getType();
        assertEquals(Token.FIELD, tokenType12);
        int tokenType13 = testLexor.getTokenList().get(12).getType();
        assertEquals(Token.COLON, tokenType13);
        int tokenType14 = testLexor.getTokenList().get(13).getType();
        assertEquals(Token.NUMERIC_LITERAL, tokenType14);
        int tokenType15 = testLexor.getTokenList().get(14).getType();
        assertEquals(Token.COLON, tokenType15);
        int tokenType16 = testLexor.getTokenList().get(15).getType();
        assertEquals(Token.STRING_LITERAL, tokenType16);
        int tokenType17 = testLexor.getTokenList().get(16).getType();
        assertEquals(Token.RPAREN, tokenType17);
        int tokenType18 = testLexor.getTokenList().get(17).getType();
        assertEquals(Token.WHEN, tokenType18);
        int tokenType19 = testLexor.getTokenList().get(18).getType();
        assertEquals(Token.FIELD, tokenType19);
        int tokenType20 = testLexor.getTokenList().get(19).getType();
        assertEquals(Token.NOTEQUAL, tokenType20);
        int tokenType21 = testLexor.getTokenList().get(20).getType();
        assertEquals(Token.STRING_LITERAL, tokenType21);
        int tokenType22 = testLexor.getTokenList().get(21).getType();
        assertEquals(Token.OR, tokenType22);
        int tokenType23 = testLexor.getTokenList().get(22).getType();
        assertEquals(Token.FUNCTION_TRIM, tokenType23);
        int tokenType24 = testLexor.getTokenList().get(23).getType();
        assertEquals(Token.LPAREN, tokenType24);
        int tokenType25 = testLexor.getTokenList().get(24).getType();
        assertEquals(Token.FIELD, tokenType25);
        int tokenType26 = testLexor.getTokenList().get(25).getType();
        assertEquals(Token.RPAREN, tokenType26);
        int tokenType27 = testLexor.getTokenList().get(26).getType();
        assertEquals(Token.EQUAL, tokenType27);
        int tokenType28 = testLexor.getTokenList().get(27).getType();
        assertEquals(Token.NUMERIC_LITERAL, tokenType28);
        int tokenType29 = testLexor.getTokenList().get(28).getType();
        assertEquals(Token.THEN, tokenType29);
        int tokenType30 = testLexor.getTokenList().get(29).getType();
        assertEquals(Token.NUMERIC_LITERAL, tokenType30);
        int tokenType31 = testLexor.getTokenList().get(30).getType();
        assertEquals(Token.ELSE, tokenType31);
        int tokenType32 = testLexor.getTokenList().get(31).getType();
        assertEquals(Token.NUMERIC_LITERAL, tokenType32);

        assertEquals(testLexor.getTokenList().size(), 32);
    }
}
