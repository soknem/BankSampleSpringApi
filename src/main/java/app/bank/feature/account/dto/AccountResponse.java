package app.bank.feature.account.dto;

import app.bank.feature.users.dto.UserResponse;
import lombok.Builder;
import org.hibernate.boot.model.process.internal.UserTypeResolution;

import java.math.BigDecimal;
@Builder
public record AccountResponse(String id,
                             String accountNumber,
                             String accountName,
                             BigDecimal accountBalance,
                             UserResponse user,
                             String accountType) {
}
