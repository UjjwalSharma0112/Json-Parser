import java.util.*;
class Parser {
    private List<Token> tokens;
    private int index = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    Object parse() {
        Token token = tokens.get(index);
        if (token.type == TokenType.LEFT_BRACE) {
            return parseObject();
        } else if (token.type == TokenType.LEFT_BRACKET) {
            return parseArray();
        } else {
            throw new RuntimeException("Invalid JSON start");
        }
    }

    private JSONObject parseObject() {
        JSONObject obj = new JSONObject();
        index++;  // Skip {

        while (tokens.get(index).type != TokenType.RIGHT_BRACE) {
            String key = tokens.get(index++).value;  // Key
            index++;  // Skip :

            Object value = parseValue();
            obj.put(key, value);

            if (tokens.get(index).type == TokenType.COMMA) {
                index++;  // Skip ,
            }
        }
        index++;  // Skip }
        return obj;
    }

    private JSONArray parseArray() {
        JSONArray array = new JSONArray();
        index++;  // Skip [

        while (tokens.get(index).type != TokenType.RIGHT_BRACKET) {
            array.add(parseValue());

            if (tokens.get(index).type == TokenType.COMMA) {
                index++;  // Skip ,
            }
        }
        index++;  // Skip ]
        return array;
    }

    private Object parseValue() {
        Token token = tokens.get(index++);
        switch (token.type) {
            case STRING: return token.value;
            case NUMBER: return Double.parseDouble(token.value);
            case BOOLEAN: return Boolean.parseBoolean(token.value);
            case NULL: return null;
            case LEFT_BRACE: index--; return parseObject();
            case LEFT_BRACKET: index--; return parseArray();
            default: throw new RuntimeException("Unexpected token: " + token.type);
        }
    }
}
