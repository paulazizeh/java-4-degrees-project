package edu.cscc.degrees.data;

import edu.cscc.degrees.domain.MenuCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MenuCategoryRepository extends PagingAndSortingRepository <MenuCategory, Long> {

    List<MenuCategory> findAllByOrderBySortOrderAscCategoryTitleAsc();



}
