package dna.test_rest_controller.repository;

import dna.test_rest_controller.model.ShiftMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftMessageRepository extends JpaRepository<ShiftMessage, Long> {
    //List<ShiftMessage> findAllByWrittenFalse();
    List<ShiftMessage> findAllByIsWrittenFalse();
    ShiftMessage findByMessageId(String messageId);
}
