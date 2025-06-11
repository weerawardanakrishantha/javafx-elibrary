package dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDetail {
    private Integer id;
    private String title;
    private String name;
    private Date issueDate;
    private Date dueDate;
    private String status;
}
