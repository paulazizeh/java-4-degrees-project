package edu.cscc.degrees.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cscc.degrees.data.MenuCategoryRepository;
import edu.cscc.degrees.data.MenuItemCrudRepository;
import edu.cscc.degrees.domain.MenuCategory;
import edu.cscc.degrees.domain.MenuItem;
import edu.cscc.degrees.domain.MenuOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

    @MockBean
    private MenuItemCrudRepository mockItemRepository;

    @MockBean
    private MenuCategoryRepository mockRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final String RESOURCE_URI = "/public/api/menus";

    @Test
    void getAllMenuCategories(@Autowired MockMvc mockMvc)
        throws Exception {
            List<MenuOptions> menuOptionsList = new ArrayList<>();
            MenuOptions menuOptions = new MenuOptions();
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setId(1L);
        menuOptions.setMenuCategory(menuCategory);
        List<MenuItem> menuItemList = new ArrayList<>();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(2L);
        menuItem.setMenuCategory(menuCategory);
        menuItemList.add(menuItem);
        menuOptions.setMenuItemList(menuItemList);
        menuOptionsList.add(menuOptions);
        List<MenuCategory> menuCategoryList = new ArrayList<>();
        menuCategoryList.add(menuCategory);
            when(mockItemRepository.findByMenuCategory_IdOrderBySortOrderAscNameAsc(menuCategory.getId())).thenReturn(menuItemList);
            when(mockRepository.findAllByOrderBySortOrderAscCategoryTitleAsc()).thenReturn(menuCategoryList);
            //when(mockRepository.save(any(MenuCategory.class))).thenReturn(savedPosting);
            MvcResult result = mockMvc.perform(get(RESOURCE_URI))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(
                            "[0].menuCategory.id").value(menuCategory.getId()))
                    .andExpect(jsonPath(
                            "[0].menuItemList.[0].id").value(menuItem.getId()))
                    .andReturn();
            MockHttpServletResponse mockResponse = result.getResponse();
            verify(mockItemRepository, times(1)).findByMenuCategory_IdOrderBySortOrderAscNameAsc(1L);
    }
}