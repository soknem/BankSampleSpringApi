package app.bank.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authorities_tbl")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


}
