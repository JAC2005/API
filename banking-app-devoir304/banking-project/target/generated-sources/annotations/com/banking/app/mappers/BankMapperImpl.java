package com.banking.app.mappers;

import com.banking.app.dtos.BankDtos;
import com.banking.app.entities.Bank;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-19T15:12:49+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class BankMapperImpl implements BankMapper {

    @Override
    public Bank toEntity(BankDtos.CreateBankRequest request) {
        if ( request == null ) {
            return null;
        }

        Bank.BankBuilder bank = Bank.builder();

        bank.address( request.address() );
        bank.code( request.code() );
        bank.country( request.country() );
        bank.name( request.name() );

        return bank.build();
    }

    @Override
    public BankDtos.BankResponse toResponse(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String code = null;
        String address = null;
        String country = null;

        id = bank.getId();
        name = bank.getName();
        code = bank.getCode();
        address = bank.getAddress();
        country = bank.getCountry();

        int accountCount = bank.getAccounts() != null ? bank.getAccounts().size() : 0;

        BankDtos.BankResponse bankResponse = new BankDtos.BankResponse( id, name, code, address, country, accountCount );

        return bankResponse;
    }
}
