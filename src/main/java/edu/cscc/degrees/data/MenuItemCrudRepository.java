package edu.cscc.degrees.data;

import edu.cscc.degrees.domain.MenuItem;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MenuItemCrudRepository extends PagingAndSortingRepository<MenuItem, Long> {
    List<MenuItem>
    findByMenuCategory_IdOrderBySortOrderAscNameAsc(Long menuCategoryId);
}
