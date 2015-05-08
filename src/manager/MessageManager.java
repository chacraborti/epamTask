package manager;

import java.util.ResourceBundle;

public class MessageManager {
    private  ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.message"); // класс извлекает информацию из файла messages.properties
   public MessageManager() { }
    public  String getProperty(String key) {
return resourceBundle.getString(key);
}
}
