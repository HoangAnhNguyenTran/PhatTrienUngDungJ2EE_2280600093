package BaiTapBuoi4.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import BaiTapBuoi4.model.Product;

@Service
public class ProductService {
    List<Product> listProducts = new ArrayList<>();

    public List<Product> getAll() {
        return listProducts;
    }

    public Product get(int id) {
        return listProducts.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void add(Product newProduct) {
        int maxId = listProducts.stream().mapToInt(Product::getId).max().orElse(0);
        newProduct.setId(maxId + 1);
        listProducts.add(newProduct);
    }

    public void update(Product editProduct) {
        Product find = get(editProduct.getId());
        if (find != null) {
            find.setName(editProduct.getName());
            find.setPrice(editProduct.getPrice());
            if (editProduct.getImage() != null) {
                find.setImage(editProduct.getImage());
            }
        }
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (imageProduct == null || imageProduct.isEmpty()) {
            return; // Không có ảnh mới thì bỏ qua
        }

        String contentType = imageProduct.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Tệp tải lên không phải là hình ảnh!");
        }

        try {
            Path dirImages = Paths.get("src/main/resources/static/images");
            if (!Files.exists(dirImages)) {
                Files.createDirectories(dirImages);
            }

            String newFileName = UUID.randomUUID() + "-" + imageProduct.getOriginalFilename();
            Path pathFileUpload = dirImages.resolve(newFileName);

            Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
            newProduct.setImage(newFileName);

        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload ảnh", e);
        }
    }
}
