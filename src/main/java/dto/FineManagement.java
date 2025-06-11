package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FineManagement {
    private Integer id;
    private String name;
    private String title;
    private Integer numberOfDates;
    private int totalFine;
}
