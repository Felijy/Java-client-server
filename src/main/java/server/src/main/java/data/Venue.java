package data;

import exceptions.IncorrectIDException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Класс для хранения мест встреч
 */
public class Venue implements Serializable {
    @Serial
    private static final long serialVersionUID = 902;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long capacity; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null


    /**
     * Пустой конструктор для создания пустого класса
     */
    public Venue(){

    }

    /**
     * Валидирует все значения класса Venue на корректность
     * @return true если данные верны, false в обратном случае
     */
    public boolean validateValues() {
        if ((getName() == null) || (getName() == "")) return false;
        if (getCapacity() <= 0) return false;
        return getType() != null;
    }

    public String getName() {
        return name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public VenueType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return getId() == venue.getId() && Objects.equals(getName(), venue.getName()) && Objects.equals(getCapacity(), venue.getCapacity()) && getType() == venue.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCapacity(), getType());
    }

    @Override
    public String toString() {
        return " {" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", capacity=" + getCapacity() +
                ", type=" + getType() +
                '}';
    }

    public long getId() {
        return id;
    }


    public void setIDA(long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public void setType(VenueType type) {
        this.type = type;
    }
}

