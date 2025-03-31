package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Tab;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TabRepository extends JpaRepository<Tab, Long> {

    Tab findByTabId(Long tabId);

    @Modifying
    @Query(value = "DELETE FROM tabs WHERE project=:projectId", nativeQuery = true)
    void deleteByProject(@Param("projectId") Long projectId);

}
