enum TokenType {
    LEFT_BRACE, RIGHT_BRACE,
    LEFT_BRACKET, RIGHT_BRACKET,
    COLON, COMMA,
    STRING, NUMBER, BOOLEAN, NULL,
    EOF
}

class Token {
    TokenType type;
    String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + " : " + value;
    }
}
