package uz.inha.tis.home.model;

/**
 * Created by sardor on 11/7/17.
 */

public class Article {

    String title;
    int imageId;

    public Article(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
