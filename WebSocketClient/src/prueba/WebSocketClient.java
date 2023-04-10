package prueba;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import entidad.Cliente;
import entidad.Mensaje;
import java.io.IOException;
import java.io.StringReader;
import org.glassfish.tyrus.client.ClientManager;
import javax.websocket.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.Observable;
import javax.swing.JOptionPane;
import pantallas.EnvioMensajes;
import pantallas.Historial;
import pantallas.Ingreso;
import pantallas.MensajesEspecificos;

@ClientEndpoint
public class WebSocketClient {

    private static CountDownLatch latch;
    private static Mensaje mensaje;
    private static Cliente[] clientes;

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            JsonReader reader = new JsonReader(new StringReader(message));
            reader.setLenient(true);
            if (reader.peek() == JsonToken.BEGIN_ARRAY) {
                clientes = new Gson().fromJson(reader, Cliente[].class);
                EnvioMensajes.getInstance().update(clientes);
            } else {
                mensaje = new Gson().fromJson(reader, Mensaje.class);
                if (mensaje.getDestinatario().equalsIgnoreCase("Todo el publico")) {
                    Historial.getInstance().update(mensaje);
                } else {
                    MensajesEspecificos.getInstance().update(mensaje);
                }
            }
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("[CLIENT]: Session " + session.getId() + " close, because " + closeReason);
        latch.countDown();
    }

    @OnError
    public void onError(Session session, Throwable err) {
        System.out.println("[CLIENT]: Error!!!!!, Session ID: " + session.getId() + ", " + err.getMessage());
    }
    private static Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to server");
        WebSocketClient.session = session;
        Ingreso.getInstance().conexion(true, nombre, this);
    }

    public WebSocketClient() {
    }
    private static String nombre;

    public void connect(String nombre) {
        try {
            this.nombre = nombre;
            ClientManager clientManager = ClientManager.createClient();
            URI uri2 = new URI("ws://localhost:8080/WebSocket/websocketendpoint" + "?nombre=" + nombre);
            clientManager.connectToServer(WebSocketClient.class, uri2);
        } catch (URISyntaxException | DeploymentException | IOException ex) {
            System.out.println("Errro; " + ex.getMessage());
            Ingreso.getInstance().conexion(false, nombre, this);

        }
    }

    public void sendMessage(Mensaje message) throws Exception {
        WebSocketClient.session.getBasicRemote().sendText(new Gson().toJson(message));
    }

}
