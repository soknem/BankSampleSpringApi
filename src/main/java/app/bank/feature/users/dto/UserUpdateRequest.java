package app.bank.feature.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record UserUpdateRequest(String fullName, String gender, String profileImage, String phoneNumber,
                                String cityOrProvince, String khanOrDistrict, String sangkatOrCommune,
                                String employeeType, String companyName, String mainSourceOfIncome,
                                BigDecimal monthlyIncomeRange, String studentCardId

) {

}
