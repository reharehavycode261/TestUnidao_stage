import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        // Choisir la langue et le pays
        Locale locale = new Locale("fr", "FR");

        // Charger la ressource
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);

        // Utiliser les ressources
        System.out.println(messages.getString("greetings"));

        // Code existant...
    }
}