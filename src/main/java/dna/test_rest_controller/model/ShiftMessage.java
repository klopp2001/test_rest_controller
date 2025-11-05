package dna.test_rest_controller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShiftMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    String messageId;

    @Column
    @Getter
    @Setter
    String messageBody;

    @Column
    @Getter
    @Setter
    String senderId;

    @Column
    @Getter
    @Setter
    String senderUsername;

    @Column
    @Getter
    @Setter
    Boolean isWritten;

    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createdAt;
}
