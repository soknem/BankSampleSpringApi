package app.bank.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles_tbl")
@Data

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // stuff , admin , ...
    @Column(nullable = false, length = 25)
    private String name;
}
