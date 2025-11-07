package dna.test_rest_controller.controller;

import dna.test_rest_controller.dto.ShiftMessageDto;
import dna.test_rest_controller.model.ShiftMessage;
import dna.test_rest_controller.service.ShiftMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class MessageController {
    WebClient client = WebClient.create("http://localhost:8090");
    @Autowired
    ShiftMessageService shiftMessageService;

    @GetMapping("/get_messages")
    public List<ShiftMessageDto> getMessages() {
        return shiftMessageService.getNotWrittenShiftMessages();
    }

    @PostMapping("/add_message")
    public void addMessage(@RequestBody ShiftMessageDto dto) {
        shiftMessageService.saveShiftMessage(dto);
    }

    @GetMapping("/test")
    public void testRESTClient() {
        ShiftMessageDto dto = new ShiftMessageDto();
        dto.setMessageId("asdasd");
        dto.setMessageBody("asssssdd");
        dto.setSenderId("ddddd");
        dto.setSenderUsername("tsoi");
        var rest = RestClient.create();
//        var client = HttpClient.newHttpClient()

        String result = client.post()
                .uri("/add_not_written")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        String body = response.block(); // блокирует до получения ответа
//        System.out.println(body);
//
//        var resp = rest
//                .post()
//                .uri("/add_not_written")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(dto)
//                .retrieve();
//        System.out.println(resp);
    }
}
