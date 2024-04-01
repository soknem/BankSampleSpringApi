package app.bank.feature.users.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record UserRequest (String id,
                                  String username,
                                  String fullName,
                                  String gender ,
                                  String pin,
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
                                  Set<String> roles){
}
