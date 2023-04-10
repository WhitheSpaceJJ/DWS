package ws;

import com.google.gson.Gson;
import entidad.Cliente;
import entidad.Mensaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.json.JsonException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocketendpoint")
public class WebSocket {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Cliente, Session> users = Collections.synchronizedMap(new HashMap<Cliente, Session>());

    @OnOpen
    public void onOpen(Session sesion) {
        String queryString = sesion.getQueryString();
        String nombre = queryString.substring(queryString.indexOf("=") + 1);
        System.out.println("Open Connection ..." + sesion.toString());
        System.out.println("Usuario conectado: " + nombre);
        Cliente cliente = new Cliente(nombre);
        if (users.containsKey(cliente)) {
            System.out.println("El cliente " + cliente.toString() + " ya existe. Cerrando conexión.");
            try {
                sesion.close();
            } catch (IOException e) {
                System.out.println("Error; " + e.getMessage());
            }
        } else {

            clients.add(sesion);
            users.put(cliente, sesion);
            List<Cliente> connectedUsers = new ArrayList<Cliente>(users.keySet());
            if (!clients.isEmpty()) {
                synchronized (clients) {
                    for (Session client : clients) {
                        try {
                            client.getBasicRemote().sendText(new Gson().toJson(connectedUsers));
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }
    }

    @OnClose
    public void onClose(Session sesion) {
        System.out.println("Close Connection ...");
        clients.remove(sesion);
        for (Map.Entry<Cliente, Session> entry : users.entrySet()) {
            if (entry.getValue().equals(sesion)) {
                users.remove(entry.getKey());
                System.out.println("Usuario desconectado: " + entry.getKey());
                break;
            }
        }
        List<Cliente> connectedUsers = new ArrayList<Cliente>(users.keySet());
        if (!clients.isEmpty()) {
            synchronized (clients) {
                for (Session client : clients) {
                    try {
                        client.getBasicRemote().sendText(new Gson().toJson(connectedUsers));
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
//        if (!clients.isEmpty() && clients.size() > 1) {
            try {

                Mensaje mensajeObj = new Gson().fromJson(message, Mensaje.class
                );
                String destinatario = mensajeObj.getDestinatario();
                synchronized (clients) {
                    if (destinatario.equals("Todo el Publico")) {
                        for (Session client : clients) {
                            if (!client.equals(session)) {
                                try {
                                    client.getBasicRemote().sendText(new Gson().toJson(mensajeObj));
                                } catch (IOException ex) {
                                    System.err.println("Error al enviar mensaje a cliente: " + ex.getMessage());
                                }
                            }

                        }
                    } else {
                        Session destinatarioSession = users.get(new Cliente(destinatario));
                        if (destinatarioSession != null) {
                            try {
                                destinatarioSession.getBasicRemote().sendText(new Gson().toJson(mensajeObj));
                            } catch (IOException ex) {
                                System.err.println("Error al enviar mensaje directo: " + ex.getMessage());
                            }
                        } else {
                            System.err.println("No se encontró el usuario destinatario: " + destinatario);
                        }
                    }
                }
            } catch (JsonException | NullPointerException ex) {
                System.err.println("Error al leer mensaje JSON: " + ex.getMessage());
            }
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println("Error; " + e.getMessage());
    }

}
