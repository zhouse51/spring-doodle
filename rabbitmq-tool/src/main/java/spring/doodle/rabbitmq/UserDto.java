package spring.doodle.rabbitmq;

import java.io.Serializable;

public class UserDto implements Serializable {

    private int id;
    private String name;

    public UserDto() {
    }

    public UserDto(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
