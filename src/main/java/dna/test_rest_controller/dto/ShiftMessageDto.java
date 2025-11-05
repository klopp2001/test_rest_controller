package dna.test_rest_controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftMessageDto {
    String messageId;
    String messageBody;
    String senderId;
    String senderUsername;
}
