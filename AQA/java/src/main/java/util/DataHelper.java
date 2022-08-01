package util;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataHelper {

    private final Faker faker = new Faker();

    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = faker.random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
