import java.util.*;

class Tokenizer {
    private String json;
    private int index = 0;

    Tokenizer(String json) {
        this.json = json.trim();
    }

    List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (index < json.length()) {
            char c = json.charAt(index);

            if (Character.isWhitespace(c)) {
                index++;
            } else if (c == '{') {
                tokens.add(new Token(TokenType.LEFT_BRACE, "{"));
                index++;
            } else if (c == '}') {
                tokens.add(new Token(TokenType.RIGHT_BRACE, "}"));
                index++;
            } else if (c == '[') {
                tokens.add(new Token(TokenType.LEFT_BRACKET, "["));
                index++;
            } else if (c == ']') {
                tokens.add(new Token(TokenType.RIGHT_BRACKET, "]"));
                index++;
            } else if (c == ':') {
                tokens.add(new Token(TokenType.COLON, ":"));
                index++;
            } else if (c == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                index++;
            } else if (c == '"') {
                tokens.add(new Token(TokenType.STRING, readString()));
            } else if (Character.isDigit(c) || c == '-') {
                tokens.add(new Token(TokenType.NUMBER, readNumber()));
            } else if (json.startsWith("true", index)) {
                tokens.add(new Token(TokenType.BOOLEAN, "true"));
                index += 4;
            } else if (json.startsWith("false", index)) {
                tokens.add(new Token(TokenType.BOOLEAN, "false"));
                index += 5;
            } else if (json.startsWith("null", index)) {
                tokens.add(new Token(TokenType.NULL, "null"));
                index += 4;
            } else {
                throw new RuntimeException("Unexpected character: " + c);
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private String readString() {
        index++;  // Skip opening "
        StringBuilder sb = new StringBuilder();
        while (json.charAt(index) != '"') {
            sb.append(json.charAt(index++));
        }
        index++;  // Skip closing "
        return sb.toString();
    }

    private String readNumber() {
        StringBuilder sb = new StringBuilder();
        while (index < json.length() && (Character.isDigit(json.charAt(index)) || json.charAt(index) == '.')) {
            sb.append(json.charAt(index++));
        }
        return sb.toString();
    }
}
