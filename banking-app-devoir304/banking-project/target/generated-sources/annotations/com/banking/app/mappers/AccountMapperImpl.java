package com.banking.app.mappers;

import com.banking.app.dtos.AccountDtos;
import com.banking.app.entities.Account;
import com.banking.app.entities.Bank;
import com.banking.app.enums.AccountStatus;
import com.banking.app.enums.AccountType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-19T15:12:50+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDtos.AccountResponse toResponse(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDtos.BankSummary bank = null;
        Long id = null;
        String accountNumber = null;
        String ownerName = null;
        String ownerEmail = null;
        BigDecimal balance = null;
        AccountType accountType = null;
        AccountStatus status = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        bank = toBankSummary( account.getBank() );
        id = account.getId();
        accountNumber = account.getAccountNumber();
        ownerName = account.getOwnerName();
        ownerEmail = account.getOwnerEmail();
        balance = account.getBalance();
        accountType = account.getAccountType();
        status = account.getStatus();
        createdAt = account.getCreatedAt();
        updatedAt = account.getUpdatedAt();

        AccountDtos.AccountResponse accountResponse = new AccountDtos.AccountResponse( id, accountNumber, ownerName, ownerEmail, balance, accountType, status, bank, createdAt, updatedAt );

        return accountResponse;
    }

    @Override
    public Account toEntity(AccountDtos.CreateAccountRequest request, Bank bank) {
        if ( request == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.balance( request.initialBalance() );
        account.accountNumber( request.accountNumber() );
        account.accountType( request.accountType() );
        account.ownerEmail( request.ownerEmail() );
        account.ownerName( request.ownerName() );

        account.status( AccountStatus.ACTIVE );

        Account accountResult = account.build();

        setBank( accountResult, bank );

        return accountResult;
    }
}
