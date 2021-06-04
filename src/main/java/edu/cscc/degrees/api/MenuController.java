package edu.cscc.degrees.api;


import edu.cscc.degrees.data.MenuCategoryRepository;
import edu.cscc.degrees.data.MenuItemCrudRepository;
import edu.cscc.degrees.domain.MenuCategory;
import edu.cscc.degrees.domain.MenuOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemCrudRepository menuItemCrudRepository;

    public MenuController(MenuCategoryRepository menuCategoryRepository, MenuItemCrudRepository menuItemCrudRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemCrudRepository = menuItemCrudRepository;
    }

    @GetMapping ("/public/api/menus")
    public Iterable<MenuOptions> getAllMenuCategories() {
        List<MenuOptions> menus = new ArrayList<>();
        for (MenuCategory menuCategory : menuCategoryRepository.findAllByOrderBySortOrderAscCategoryTitleAsc()) {
            MenuOptions menuOptions = new MenuOptions();
            menuOptions.setMenuItemList(menuItemCrudRepository.findByMenuCategory_IdOrderBySortOrderAscNameAsc(menuCategory.getId()));
            menuOptions.setMenuCategory(menuCategory);
            menus.add(menuOptions);
        }

        return menus;
    }


}
