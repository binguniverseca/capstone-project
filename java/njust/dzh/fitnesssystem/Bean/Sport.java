package njust.dzh.fitnesssystem.Bean;

import java.io.Serializable;

public class Sport implements Serializable {
    private String name;
    private int imageId;

    public Sport(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
