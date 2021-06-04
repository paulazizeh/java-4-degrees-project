package edu.cscc.degrees.domain;

import java.util.List;

public class MenuOptions {

    private MenuCategory menuCategory;
    private List<MenuItem> menuItemList;

    public MenuOptions() {
    }

    public MenuOptions(MenuCategory menuCategory, List<MenuItem> menuItemList) {
        this.menuCategory = menuCategory;
        this.menuItemList = menuItemList;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
