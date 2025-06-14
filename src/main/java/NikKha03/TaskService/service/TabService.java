package NikKha03.TaskService.service;

import NikKha03.TaskService.DTO.TabRequest;
import NikKha03.TaskService.model.Tab;
import org.springframework.http.ResponseEntity;

public interface TabService {

    ResponseEntity<?> findTabById(Long id);

    ResponseEntity<?> createTab(TabRequest request);

    ResponseEntity<?> changeTab(Long tabId, TabRequest request);

    void deleteTab(Long tabId);

}
