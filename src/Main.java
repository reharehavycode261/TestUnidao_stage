import org.ff4j.FF4j;
import org.ff4j.core.Feature;

public class Main {
    public static void main(String[] args) {
        // Initialisation de FF4j avec des feature flags
        FF4j ff4j = new FF4j();
        ff4j.createFeature(new Feature("newSearchFeature"));
        ff4j.createFeature(new Feature("notificationsFeature"));
        
        // Verification et utilisation des feature flags
        if (ff4j.check("newSearchFeature")) {
            System.out.println("La fonctionnalité de recherche avancée est activée!");
            // Appel du code pour la fonctionnalité de recherche avancée
        }

        if (ff4j.check("notificationsFeature")) {
            System.out.println("La fonctionnalité de notifications multi-canaux est activée!");
            // Appel du code pour la fonctionnalité de notifications
        }
    }
}