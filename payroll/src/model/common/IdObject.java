package model.common;

import java.util.Objects;

public abstract class IdObject {

    private int id;

    public IdObject() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdObject object = (IdObject) o;
        return id == object.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
