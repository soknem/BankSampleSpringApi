package app.bank.feature.account.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record AccountRequest(String accountNumber, String accountName, BigDecimal accountBalance, String accountType,
                             String userId) {
}
