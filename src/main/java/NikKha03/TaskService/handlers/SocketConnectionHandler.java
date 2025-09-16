package NikKha03.TaskService.handlers;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** https://www.geeksforgeeks.org/springboot/spring-boot-web-socket/ */
public class SocketConnectionHandler extends TextWebSocketHandler {
    /** Список, который хранит в себе соединения с websocket
     * TODO может быть заменен на redis */
    List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("Connected: " + session.getId());
        webSocketSessions.add(session); // добавляем сеанс в список
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("Disconnected: " + session.getId());
        webSocketSessions.remove(session); // удаляем подключение
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);

        for (WebSocketSession webSocketSession : webSocketSessions) {
            if (webSocketSession == session)
                continue;
            webSocketSession.sendMessage(message); // отправляем сообщение в сеанс
        }
    }
}
