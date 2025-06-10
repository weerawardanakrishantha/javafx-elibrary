package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String contact;

}
