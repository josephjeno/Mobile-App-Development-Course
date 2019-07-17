package utils;

public class CharacterInfoAdapterItem {

    int imageResID;
    String characterName;

    public CharacterInfoAdapterItem(int imageResID, String characterName) {
        this.imageResID = imageResID;
        this.characterName = characterName;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

}
