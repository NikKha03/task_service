package NikKha03.TaskService.component;

import NikKha03.TaskService.model.WebSocketUser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/** https://www.geeksforgeeks.org/springboot/spring-boot-web-socket/ */
@Component
public class SocketConnectionHandler extends TextWebSocketHandler {
    /** Список, который хранит в себе соединения с websocket
     * TODO может быть заменен на redis */
    List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    private final Map<Long, Set<WebSocketUser>> projectToUser = new ConcurrentHashMap<>();
    private final Map<String, WebSocketUser> sessionToUser = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, String> params = extractParamsFromSession(session);
        WebSocketUser socketUser = new WebSocketUser(Long.parseLong(params.get("projectId")), "", session);
        sessionToUser.put(session.getId(), socketUser);

        super.afterConnectionEstablished(session);
        projectToUser.computeIfAbsent(Long.parseLong(params.get("projectId")), k -> ConcurrentHashMap.newKeySet()).add(socketUser); // добавляем сеанс
        System.out.println("WebSocket connected: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WebSocketUser socketUser = sessionToUser.get(session.getId());
        sessionToUser.remove(session.getId());

        super.afterConnectionClosed(session, status);
        projectToUser.computeIfPresent(socketUser.getProjectId(), (id, users) -> {
            users.remove(socketUser); // удаляем пользователя
            return users.isEmpty() ? null : users; // если множество пустое → убираем ключ совсем
        });
        System.out.println("WebSocket disconnected: " + session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        WebSocketUser socketUser = sessionToUser.get(session.getId());
        System.out.println(sessionToUser);

        Set<WebSocketUser> users = projectToUser.get(socketUser.getProjectId());

        users.forEach(user -> {
            // скорее всего самому себе тоже надо будет отправлять
            if (user.getSession() != session) {
                try {
                    user.getSession().sendMessage(message);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
    }

    private Map<String, String> extractParamsFromSession(WebSocketSession session) {
        URI uri = session.getUri();
        Map<String, String> paramsMap = new HashMap<>();

        if (uri != null && uri.getQuery() != null) {
            String[] params = uri.getQuery().split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    paramsMap.put(keyValue[0], keyValue[1]);
                }
            }
        }

        return paramsMap;
    }

    public void handleData(String sessionId, String data) throws Exception {
        WebSocketUser socketUser = this.sessionToUser.get(sessionId);
        handleMessage(socketUser.getSession(), new TextMessage(data));
    }
}
