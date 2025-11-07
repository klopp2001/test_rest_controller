package dna.test_rest_controller.service;

import dna.test_rest_controller.dto.ShiftMessageDto;
import dna.test_rest_controller.model.ShiftMessage;
import dna.test_rest_controller.repository.ShiftMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class ShiftMessageServiceTest {
    @Mock
    private ShiftMessageRepository shiftMessageRepository;

    @InjectMocks
    private ShiftMessageService shiftMessageService;

    @Test
    public void saveShiftMessage_shouldReturnSameShiftMessage() {
        LocalDateTime time = LocalDateTime.now();
        ShiftMessage shiftMessage = new ShiftMessage(1L,
                "228",
                "hello_world",
                "1488",
                "userName",
                false,
                time
        );

        ShiftMessageDto dto = new ShiftMessageDto(shiftMessage.getMessageId(),
                shiftMessage.getMessageBody(),
                shiftMessage.getSenderId(),
                shiftMessage.getSenderUsername());

        Mockito.when(shiftMessageRepository.save(shiftMessage)).thenReturn(shiftMessage);

        assertDoesNotThrow(() -> shiftMessageService.saveShiftMessage(dto));
        Mockito.verify(shiftMessageRepository).save(Mockito.argThat(arg ->
                arg.getMessageId().equals(shiftMessage.getMessageId()) &&
                        arg.getMessageBody().equals(shiftMessage.getMessageBody()) &&
                        arg.getSenderUsername().equals(shiftMessage.getSenderUsername())));
    }
}
