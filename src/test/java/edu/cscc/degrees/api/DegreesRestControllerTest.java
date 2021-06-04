package edu.cscc.degrees.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cscc.degrees.data.MenuCategoryRepository;
import edu.cscc.degrees.domain.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




public class DegreesRestControllerTest {

    @MockBean
    private MenuCategoryRepository mockRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final MenuCategory testPosting = new MenuCategory(0L, "category", "title", 6);
    //private static final MenuCategory savedPosting = new MenuCategory(2L, "category", "title", 6);


    private static final String RESOURCE_URI = "/api/menu/categories";


    @Test
    @DisplayName("T01 - POST accepts and returns blog post representation")
    public void postCreatesMenuCategoryEntry_Test(@Autowired MockMvc mockMvc)
            throws Exception {
        when(mockRepository.save(refEq(testPosting))).thenReturn(testPosting);
        //when(mockRepository.save(any(MenuCategory.class))).thenReturn(savedPosting);
        MvcResult result = mockMvc.perform(post(RESOURCE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testPosting)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(
                        "$.id").value(testPosting.getId()))
                .andExpect(jsonPath(
                        "$.categoryTitle").value(testPosting.getCategoryTitle()))
                .andExpect(jsonPath(
                        "$.categoryNotes").value(testPosting.getCategoryNotes()))
                .andExpect(
                        jsonPath("$.sortOrder").value(testPosting.getSortOrder()))
                .andReturn();
        MockHttpServletResponse mockResponse = result.getResponse();
        assertEquals(String.format(
                "http://localhost/api/menu/categories/%d", testPosting.getId()),
                mockResponse.getHeader("Location"));
        verify(mockRepository,times(1)).save(refEq(testPosting));

    }

    @Test
    @DisplayName("ST01: POST without JWT is forbidden")
    public void sTest01(@Autowired MockMvc mockMvc)
            throws Exception {
        when(mockRepository.save(any(MenuCategory.class))).thenReturn(testPosting);
        mockMvc.perform(post(RESOURCE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testPosting)))
                .andExpect(status().isForbidden());
        verify(mockRepository, never()).save(any(MenuCategory.class));
    }
    @Test
    @DisplayName("ST02: PUT without JWT is forbidden")
    public void sTest02(@Autowired MockMvc mockMvc) throws Exception {
        when(mockRepository.existsById(anyLong())).thenReturn(true);
        mockMvc.perform(put(RESOURCE_URI + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testPosting)))
                .andExpect(status().isForbidden());
        verify(mockRepository, never()).save(any(MenuCategory.class));
    }
    @Test
    @DisplayName("ST03: DELETE without JWT is forbidden")
    public void sTest03(@Autowired MockMvc mockMvc) throws Exception {
        when(mockRepository.findById(1L))
                .thenReturn(Optional.of(testPosting));
        mockMvc.perform(delete(RESOURCE_URI + "/1"))
                .andExpect(status().isForbidden());
        verify(mockRepository, never()).delete(any(MenuCategory.class));
    }




}
