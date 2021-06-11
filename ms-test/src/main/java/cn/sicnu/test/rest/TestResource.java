package cn.sicnu.test.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;

@RestController
public class TestResource {

    @GetMapping("/hello")
    public String hello(String name, @RequestHeader("Authorization") String header, HttpServletRequest request) {
        request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
        System.out.println(header);
        return "hello " + name;
    }

}
