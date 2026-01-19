import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Book {
    private int id;
    private String title;
    private String author;
    private long price;
    public Book() {}
    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public long getPrice() { return price; }
    public void input(Scanner sc) {
        System.out.print("Nhap ma sach: ");
        id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap ten sach: ");
        title = sc.nextLine();
        System.out.print("Nhap tac gia: ");
        author = sc.nextLine();
        System.out.print("Nhap don gia: ");
        price = Long.parseLong(sc.nextLine());
    }
    public void output() {
        System.out.println(
            "BOOK: id=" + id +
            ", title=" + title +
            ", author=" + author +
            ", price=" + price
        );
    }
}

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String menu = """
            CHUONG TRINH QUAN LY SACH
            1. Them sach
            2. Xoa sach
            3. Sua sach
            4. Xuat danh sach
            5. Tim sach lap trinh
            6. Tim sach theo gia
            7. Tim sach theo tac gia
            0. Thoat
            Chon chuc nang:
            """;
        int chon;
        do {
            System.out.print(menu);
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1 -> {
                    Book b = new Book();
                    b.input(sc);
                    listBook.add(b);
                    System.out.println("Da them sach!");
                }
                case 2 -> {
                    System.out.print("Nhap ma sach can xoa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Book find = listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);
                    if (find == null) {
                        System.out.println("Khong tim thay sach!");
                    } else {
                        listBook.remove(find);
                        System.out.println("Xoa sach thanh cong!");
                    }
                }
                case 3 -> {
                    System.out.print("Nhap ma sach can sua: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Book find = listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);
                    if (find == null) {
                        System.out.println("Khong tim thay sach!");
                    } else {
                        find.input(sc);
                        System.out.println("Cap nhat thanh cong!");
                    }
                }
                case 4 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sach rong!");
                    } else {
                        listBook.forEach(Book::output);
                    }
                }
                case 5 -> {
                    System.out.println("Sach lap trinh:");
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lap trinh"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhap gia toi da: ");
                    long maxPrice = Long.parseLong(sc.nextLine());
                    System.out.print("Nhap so luong sach muon lay: ");
                    int limit = Integer.parseInt(sc.nextLine());
                    System.out.println("Danh sach sach phu hop:");
                    listBook.stream()
                            .filter(b -> b.getPrice() <= maxPrice)
                            .limit(limit)                          
                            .forEach(Book::output);
                }
                case 7 -> {
                    Set<String> authorSet = new HashSet<>();
                    System.out.println("Nhap ten tac gia (nhap rong de dung):");
                    while (true) {
                        String name = sc.nextLine().trim().toLowerCase();
                        if (name.isEmpty()) break;
                        authorSet.add(name);
                    }
                    System.out.println("Ket qua tim kiem:");
                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Thoat chuong trinh!");
                default -> System.out.println("Chuc nang khong hop le!");
            }
        } while (chon != 0);
        sc.close();
    }
}
