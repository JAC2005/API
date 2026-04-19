package com.banking.app.dtos;

import com.banking.app.enums.AccountStatus;
import com.banking.app.enums.AccountType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// ── Request DTOs ─────────────────────────────────────────────────────────────

public class AccountDtos {

    public record CreateAccountRequest(
            @NotBlank(message = "Le numéro de compte est obligatoire")
            @Size(min = 10, max = 34, message = "Le numéro de compte doit contenir entre 10 et 34 caractères")
            String accountNumber,

            @NotBlank(message = "Le nom du titulaire est obligatoire")
            @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
            String ownerName,

            @NotBlank(message = "L'email du titulaire est obligatoire")
            @Email(message = "L'email fourni est invalide")
            String ownerEmail,

            @NotNull(message = "Le solde initial est obligatoire")
            @DecimalMin(value = "0.0", message = "Le solde initial ne peut pas être négatif")
            BigDecimal initialBalance,

            @NotNull(message = "Le type de compte est obligatoire")
            AccountType accountType,

            @NotNull(message = "L'identifiant de la banque est obligatoire")
            @Positive(message = "L'identifiant de la banque doit être positif")
            Long bankId
    ) {}

    // ── Response DTOs ─────────────────────────────────────────────────────────

    public record AccountResponse(
            Long id,
            String accountNumber,
            String ownerName,
            String ownerEmail,
            BigDecimal balance,
            AccountType accountType,
            AccountStatus status,
            BankSummary bank,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record BankSummary(
            Long id,
            String name,
            String code,
            String country
    ) {}
}
