package kz.narxoz.rabbit.dist1rabbit.api;

import kz.narxoz.rabbit.dist1rabbit.dto.OrderDTO;
import kz.narxoz.rabbit.dist1rabbit.service.MessageSender;
import kz.narxoz.rabbit.dist1rabbit.service.OrderPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbit")
@RequiredArgsConstructor
public class RabbitController {

    private final MessageSender messageSender;
    private final OrderPublisherService orderPublisher;

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        try{
            messageSender.sendMessage(message);
            return new ResponseEntity<>("Message send successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed send message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/send-to-all")
    public ResponseEntity<String> sendNotification(@RequestBody OrderDTO orderDTO){
        try{
            orderPublisher.sendOrderToAll(orderDTO);
            return new ResponseEntity<>("Order sent Successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to send to all", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}