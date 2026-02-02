package BaiTapBuoi4.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;

    @NotBlank(message = "Name không được để trống")
    private String name;

    @Length(min = 0, max = 200, message = "Image không quá 200 ký tự")
    private String image;

    @NotNull(message = "Price không được để trống")
    @Min(value = 0, message = "Price không được nhỏ hơn 0")
    @Max(value = 9_999_999, message = "Price không được lớn hơn 9,999,999")
    private Long price; // 👉 dùng Long thay vì long để @NotNull hoạt động

    @NotNull(message = "Category không được để trống")
    private Category category;
}
