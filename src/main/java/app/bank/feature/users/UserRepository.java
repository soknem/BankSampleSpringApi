package app.bank.feature.users;

import app.bank.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.StyledEditorKit;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDeleted = :status WHERE u.id = :userId")
    int updateDeletedStatusById(String userId, boolean status);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isBlocked = :status WHERE u.id = :userId")
    int updateBlockedStatusById(String userId, boolean status);
}
