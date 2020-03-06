package services;

import java.util.HashMap;

public class Session {
    private HashMap<String, Object> data;
    private static Session _session;
    private Session() {
        data = new HashMap<>();
    }
    public static Session getInstance() {
        if(_session == null) _session = new Session();
        return _session;
    }
    public void addToSession(String key, Object value){
        if(data.containsKey(key)) return;
        data.put(key, value);
    }
    public Object getValue(String key){
        if(data.containsKey(key)) return data.get(key);
        return null;
    }
    public void clearSession(){
        data.clear();
    }
}
