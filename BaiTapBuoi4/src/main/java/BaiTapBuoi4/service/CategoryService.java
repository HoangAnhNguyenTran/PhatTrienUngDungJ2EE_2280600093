package BaiTapBuoi4.service;

import org.springframework.stereotype.Service;

import BaiTapBuoi4.model.Category;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final List<Category> categories = new ArrayList<>();
    private int autoId = 1;

    public List<Category> getAll() {
        return categories;
    }

    public Category get(int id) {
        return categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Category category) {
        category.setId(autoId++);
        categories.add(category);
    }

    public void update(Category category) {
        Category old = get(category.getId());
        if (old != null) {
            old.setName(category.getName());
        }
    }

    public void delete(int id) {
        categories.removeIf(c -> c.getId() == id);
    }
}
