package edu.cscc.degrees.api;


import edu.cscc.degrees.data.MenuCategoryRepository;
import edu.cscc.degrees.domain.MenuCategory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/menu/categories")
public class DegreesRestController {

private final MenuCategoryRepository menuCategoryRepository;

    public DegreesRestController(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @PostMapping
    public ResponseEntity<MenuCategory> createMenuEntry(
            @RequestBody MenuCategory menuCategory, UriComponentsBuilder uriComponentsBuilder) {
        MenuCategory savedItem = menuCategoryRepository.save(menuCategory);
        UriComponents uriComponents =
                uriComponentsBuilder.path("/api/menu/categories/{id}")
                        .buildAndExpand(savedItem.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uriComponents.toUri().toString());
        return new ResponseEntity<>(savedItem, headers, HttpStatus.CREATED);
    }

}
