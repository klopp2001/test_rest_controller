package dna.test_rest_controller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dna.test_rest_controller.dto.ShiftMessageDto;
import dna.test_rest_controller.service.ShiftMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MessageController.class)
public class MessageControllerTest {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShiftMessageService messageService;

    @Test
    void getMessages_shouldReturnOk() throws Exception {
        Mockito.when(messageService.getNotWrittenShiftMessages()).thenReturn(List.of(new ShiftMessageDto(), new ShiftMessageDto()));

        mockMvc.perform(get("/get_messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void addMessage_shouldReturnOk() throws Exception {
        Mockito.doNothing().when(messageService).saveShiftMessage(Mockito.any());
        String json = mapper.writeValueAsString(new ShiftMessageDto());
        mockMvc.perform(post("/add_message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void addMessage_generatesCorrectRequestBodyParam() throws Exception {
        ShiftMessageDto messageDto = new ShiftMessageDto("123", "hello_world", "55", "KOLYA");
        String json = mapper.writeValueAsString(messageDto);
        mockMvc.perform(post("/add_message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        Mockito.verify(messageService).saveShiftMessage(Mockito.argThat(dto ->
                dto.getMessageId().equals(messageDto.getMessageId()) &&
                        dto.getMessageBody().equals(messageDto.getMessageBody()) &&
                        dto.getSenderId().equals(messageDto.getSenderId()) &&
                        dto.getSenderUsername().equals(messageDto.getSenderUsername())
        ));
    }
}
