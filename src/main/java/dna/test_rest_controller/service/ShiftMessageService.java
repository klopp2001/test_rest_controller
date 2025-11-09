package dna.test_rest_controller.service;

import dna.test_rest_controller.dto.ShiftMessageDto;
import dna.test_rest_controller.model.ShiftMessage;
import dna.test_rest_controller.repository.ShiftMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftMessageService {
    @Autowired
    ShiftMessageRepository shiftMessageRepository;

    public List<ShiftMessageDto> getNotWrittenShiftMessages() {
        List<ShiftMessage> messages = shiftMessageRepository.findAllByIsWrittenFalse();
        return messages.stream()
                .map(message -> new ShiftMessageDto(message.getMessageId(),
                        message.getMessageBody(), message.getSenderId(), message.getSenderUsername()))
                .collect(Collectors.toList());
    }

    //TODO:: not implemented yet. Save shift message
    public void saveShiftMessage(ShiftMessageDto dto) {
        ShiftMessage shiftMessage = new ShiftMessage();
        shiftMessage.setMessageId(dto.getMessageId());
        shiftMessage.setMessageBody(dto.getMessageBody());
        shiftMessage.setSenderId(dto.getSenderId());
        shiftMessage.setSenderUsername(dto.getSenderUsername());
        shiftMessage.setIsWritten(Boolean.FALSE);
        shiftMessageRepository.save(shiftMessage);
    }

    @Transactional
    public void updateShiftMessage(ShiftMessageDto dto) {
        ShiftMessage message = shiftMessageRepository.findByMessageId(dto.getMessageId());
        if (message == null) return;

        message.setMessageBody(dto.getMessageBody());
        message.setIsWritten(false);
    }
}
