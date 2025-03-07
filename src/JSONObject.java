import java.util.*;

class JSONObject {
    private Map<String, Object> map = new LinkedHashMap<>();

    void put(String key, Object value) {
        map.put(key, value);
    }

    Object get(String key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}

class JSONArray {
    private List<Object> list = new ArrayList<>();

    void add(Object value) {
        list.add(value);
    }

    Object get(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
