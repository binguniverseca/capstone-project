package njust.dzh.fitnesssystem.Bean;

public class User {
    private String id;
    private String name;
    private String age;
    private String gender;
    private String nation;
    private String height;
    private String weight;

    public User(String id, String name, String age, String gender, String nation, String height, String weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.nation = nation;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
