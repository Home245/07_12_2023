package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private final List<String> topics = new ArrayList<>();
    private final List<List<String>> messagesInTopic = new ArrayList<>();

    //3
    //curl -X POST -H "Content-Type: text/plain" http://localhost:8080/topics -d "Majula"
    @PostMapping("topics")
    public ResponseEntity<Void> addTopic(@RequestBody String text) {
        topics.add(text);
        List<String> thislist = new ArrayList<>();
        messagesInTopic.add(thislist);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/topics/1
    @DeleteMapping("topics/{index}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("index") Integer index) {
        topics.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X GET http://localhost:8080/topics
    @GetMapping("topics")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(topics);
    }

    //curl -X PUT -H "Content-Type: text/plain" http://localhost:8080/topics/1 -d "Pinwheel"
    @PutMapping("topics/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        topics.remove((int) i);
        topics.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET http://localhost:8080/topics/count
    @GetMapping("topics/count")
    public ResponseEntity<Integer> getTopic(){
        return ResponseEntity.ok(topics.size());
    }

    //curl -X DELETE http://localhost:8080/alltopics
    @DeleteMapping("alltopics")
    public ResponseEntity<Void> deleteTopics() {
        topics.clear();
        messagesInTopic.clear();
        return ResponseEntity.noContent().build();
    }

    //curl -X GET http://localhost:8080/topics/2
    @GetMapping("topics/{index}")
    public ResponseEntity<String> getTopic(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(topics.get(index));
    }

    //4
    //curl -X POST -H "Content-Type: text/plain" http://localhost:8080/topics/1/messages -d "Majula"
    @PostMapping("topics/{index}/messages")
    public ResponseEntity<Void> addTopic(@PathVariable("index") Integer index, @RequestBody String text) {
        messagesInTopic.get(index).add(messagesInTopic.get(index).size(), text);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/topics/1/messages/1
    @DeleteMapping("topics/{index}/messages/{number}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("index") Integer index, @PathVariable("index") Integer number) {
        messagesInTopic.get((int)index).remove((int) number);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: text/plain" http://localhost:8080/topics/1/messages/1 -d "Pinwheel"
    @PutMapping("topics/{index}/messages/{number}")
    public ResponseEntity<Void> updateMessage(@PathVariable("index") Integer index, @PathVariable("index") Integer number, @RequestBody String message) {
        messagesInTopic.get((int)index).remove((int) number);
        messagesInTopic.get((int) index).add(message);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET http://localhost:8080/topics/1/messages
    @GetMapping("topics/{index}/messages")
    public ResponseEntity<List<String>> getMessages(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(messagesInTopic.get((int)index));
    }
}
