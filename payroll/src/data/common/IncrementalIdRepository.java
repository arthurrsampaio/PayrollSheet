package data.common;

import model.common.IdObject;

import java.util.ArrayList;
import java.util.List;

public abstract class IncrementalIdRepository<T extends IdObject> {

    private int nextId;
    private final List<T> data;

    public IncrementalIdRepository() {
        this.nextId = 1;
        this.data = new ArrayList<>();
    }

    public List<T> load() {
        return data;
    }

    public void insert(T item) {
        item.setId(nextId++);
        data.add(item);
    }

    public void update(int id, T newItem) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id) {
                data.set(i, newItem);
            }
        }
    }

    public void delete(T item) {
        data.remove(item);
    }

}
