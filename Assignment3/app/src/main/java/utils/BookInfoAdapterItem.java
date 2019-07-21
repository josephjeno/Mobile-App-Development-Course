package utils;

public class BookInfoAdapterItem {

    int name;
    int imageResID;
    int description;

    public BookInfoAdapterItem(int name, int imageResID, int description) {
        this.name = name;
        this.imageResID = imageResID;
        this.description = description;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }
}
