package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity {
    private Long id;
    private String title;
    private String author;
    private Integer qty;
}
