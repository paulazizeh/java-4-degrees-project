package edu.cscc.degrees.api;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu/categories")
public class DegreesRestController {

    @PostMapping
    public ResponseEntity createBlogEntry () {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost/api/menu/categories/1");
        return new ResponseEntity("", headers, HttpStatus.CREATED);
    }

}
