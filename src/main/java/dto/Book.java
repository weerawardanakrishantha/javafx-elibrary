package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer qty;

    public Book(String title, String author, Integer qty) {
        this.title = title;
        this.author = author;
        this.qty = qty;
    }
}
