package com.sijune.timing.web;

import com.sijune.timing.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON 반환 컨트롤러로 변환
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, //외부 API에서 파라미터를 가져온다.
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}
