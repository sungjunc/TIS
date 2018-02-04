package uz.inha.tis.menu.model;

/**
 * Created by sardor on 9/26/17.
 */

public class Menu {

    String name;
    String url;
    int hasSubMenu;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHasSubMenu() {
        return hasSubMenu;
    }

    public void setHasSubMenu(int hasSubMenu) {
        this.hasSubMenu = hasSubMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
