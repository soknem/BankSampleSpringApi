package app.bank.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity(name = "user_accounts_tbl")
@Data
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Boolean isDisable;
    private Timestamp createdAt;

}
