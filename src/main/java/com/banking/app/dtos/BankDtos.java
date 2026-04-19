package com.banking.app.dtos;

import jakarta.validation.constraints.*;

public class BankDtos {

    public record CreateBankRequest(
            @NotBlank(message = "Le nom de la banque est obligatoire")
            @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
            String name,

            @NotBlank(message = "Le code de la banque est obligatoire")
            @Size(min = 3, max = 20, message = "Le code doit contenir entre 3 et 20 caractères")
            String code,

            @Size(max = 255)
            String address,

            @Size(max = 50)
            String country
    ) {}

    public record BankResponse(
            Long id,
            String name,
            String code,
            String address,
            String country,
            int accountCount
    ) {}
}
