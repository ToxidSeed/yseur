package execution.lexor;

import execution.plan.Token;

import java.util.HashMap;

public class ParserBase {
    public static boolean isSpace(Character paramChar){
        return String.valueOf(paramChar).matches("\\s");
    }

    public static Integer getTokenReservedWords(String word){
        HashMap<String, Integer> reservedWords = new HashMap<String, Integer>();
        reservedWords.put("LPAD", Token.FUNCTION_LPAD);
        reservedWords.put("TRIM", Token.FUNCTION_TRIM);
        reservedWords.put("CASE", Token.CASE);
        reservedWords.put("WHEN", Token.WHEN);
        reservedWords.put("THEN", Token.THEN);
        reservedWords.put("AND", Token.AND);
        reservedWords.put("OR", Token.OR);
        reservedWords.put("END", Token.END);
        reservedWords.put("ELSE", Token.ELSE);
        reservedWords.put("NOT", Token.NOT);
        reservedWords.put("IN", Token.IN);
        reservedWords.put("LIKE", Token.LIKE);
        reservedWords.put("NVL", Token.NVL);
        reservedWords.put("SUBSTR", Token.SUBSTR);
        return reservedWords.get(word);
    }
}
