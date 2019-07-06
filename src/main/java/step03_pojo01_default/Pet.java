package step03_pojo01_default;

import java.io.Serializable;

public class Pet implements Serializable {
    private String petName;

    public Pet() {
    }

    public Pet(String petName) {
        this.petName = petName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
