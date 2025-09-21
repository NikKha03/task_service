package NikKha03.TaskService.model;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@Data
public class WebSocketUser {
    public WebSocketUser(Long projectId, String keycloakId, WebSocketSession session) {
        this.keycloakId = keycloakId;
        this.projectId = projectId;
        this.session = session;
    }

    private String keycloakId;
    private Long projectId;
    private WebSocketSession session;
}
