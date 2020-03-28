package utils;

import java.util.HashMap;

public class FlashMessages {
    private static FlashMessages _instance;
    private HashMap<String, String> _messages;

    private FlashMessages() { _messages = new HashMap<>(); }

    public static FlashMessages getInstance() {
        if(_instance == null) _instance = new FlashMessages();
        return _instance;
    }

    public <T> void sendMessage(Class<T> clazz, String message) { _messages.put(clazz.getName(), message); }

    public <T> String receiveMessages(Class<T> clazz) {
        String message = null;
        if(_messages.containsKey(clazz.getName())) {
            message = _messages.get(clazz.getName());
            deleteMessage(clazz);
        }
        return message;
    }

    public <T> void deleteMessage(Class<T> clazz) { _messages.remove(clazz.getName()); }
}
