package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentEntity {
    private Long id;
    private String name;
    private String address;
    private String course;
}
