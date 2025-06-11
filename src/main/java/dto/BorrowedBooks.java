package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BorrowedBooks {
    private Integer id;
    private String title;
    private String author;
    private String status;
}
