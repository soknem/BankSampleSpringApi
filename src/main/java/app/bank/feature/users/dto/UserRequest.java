package app.bank.feature.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Singular;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record UserRequest(
        String id,
        String username,
        String fullName,
        String gender,
        @Size(min = 6,max =6, message = "pin only 6 digits")
        String pin,
        @Email(message = "Email format is not correct")
        String email,
        String password,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String employeeType,
        String companyName,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange,
        String oneSignalId,
        String studentIdCard,
        Boolean isDeleted,
        Boolean isBlocked,
        LocalDateTime createdAt,
        @NotNull
        Set<String> roles) {
}
