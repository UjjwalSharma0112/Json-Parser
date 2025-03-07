import java.util.List;

public class Main {
    public static void main(String[] args) {

        String json = """
        {
            "name": "Alice",
            "age": 25,
            "isStudent": false,
            "skills": ["Java", "Python"],
            "address": {
                "city": "New York",
                "zip": "10001"
            }
        }
        """;

        Tokenizer tokenizer = new Tokenizer(json);
        List<Token> tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);
        JSONObject parsedJson = (JSONObject) parser.parse();

        System.out.println("Parsed JSON:");
        System.out.println(parsedJson);

        System.out.println("\nExtracting values:");
        System.out.println("Name: " + parsedJson.get("name"));
        System.out.println("City: " + ((JSONObject) parsedJson.get("address")).get("city"));;
    }
}