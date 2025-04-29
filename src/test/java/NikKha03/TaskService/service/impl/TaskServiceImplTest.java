//package NikKha03.TaskService.service.impl;
//
//import NikKha03.TaskService.DTO.TaskRequest;
//import NikKha03.TaskService.repository.TabRepository;
//import NikKha03.TaskService.repository.TaskRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class TaskServiceImplTest {
//
//    @Mock
//    private TaskRepository taskRepository;
//
//    @Mock
//    private TabRepository categoryRepository;
//
//    @InjectMocks
//    private TaskServiceImpl serviceImpl;
//
//    @Test
//    void createTaskTestFailed() {
//        Long categoryId = 1L;
//        String creator = "test_user";
//        TaskRequest taskRequest = new TaskRequest(categoryId, "Тестовая задача");
//
//        when(categoryRepository.existsById(categoryId)).thenReturn(false);
//
//        ResponseEntity<?> expected = ResponseEntity.badRequest().body("Category doesn't exist");
//        assertEquals(expected, serviceImpl.createTask(creator, taskRequest));
//    }
//
//}