package project;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Counter requests = Counter.build()
            .name("requests_total")
            .help("Total requests.")
            .register();
    private static final Summary requestLatency = Summary.build()
            .name("requests_latency_seconds")
            .help("Request latency in seconds.")
            .register();

    public static void main(String[] args) {
        DefaultExports.initialize();
        try {
            // Crée un serveur HTTP Prometheus sur le port 1234
            HTTPServer server = new HTTPServer(1234);

            while (true) {
                requests.inc();
                Summary.Timer requestTimer = requestLatency.startTimer();
                try {
                    // Placeholder pour le code principal de l'application
                } catch (Exception e) {
                    logger.error("Erreur dans le traitement de la requête : ", e);
                } finally {
                    requestTimer.observeDuration();
                }
                // Simule une pause entre les requêtes
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            logger.error("Erreur lors de l'initialisation du serveur de metrics : ", e);
        }
    }
}