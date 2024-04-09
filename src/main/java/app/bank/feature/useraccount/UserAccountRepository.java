package app.bank.feature.useraccount;

import app.bank.domain.UserAccount;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Negative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    @Query("select COUNT(ua) FROM user_accounts_tbl ua where ua.user.id=?1")
    Long countUserAccountsByUserId(@Param("userId") String userId);

    Optional<UserAccount> findByAccount_Id(String id);
    Optional<UserAccount> findByAccount_AccountNumber(String accountNumber);
    List<UserAccount> findByUser_Id(String userId);
}
