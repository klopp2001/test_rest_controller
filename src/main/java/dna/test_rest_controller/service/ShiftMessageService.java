package dna.test_rest_controller.service;

import dna.test_rest_controller.dto.ShiftMessageDto;
import dna.test_rest_controller.model.ShiftMessage;
import dna.test_rest_controller.repository.ShiftMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShiftMessageService {
    @Autowired
    ShiftMessageRepository shiftMessageRepository;

    public List<ShiftMessage> getNotWrittenShiftMessages() {
        return shiftMessageRepository.findAllByIsWrittenFalse();
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
}
