package com.banking.app.services.impl;

import com.banking.app.dtos.AccountDtos;
import com.banking.app.entities.Bank;
import com.banking.app.exceptions.DuplicateResourceException;
import com.banking.app.exceptions.ResourceNotFoundException;
import com.banking.app.mappers.AccountMapper;
import com.banking.app.repositories.AccountRepository;
import com.banking.app.repositories.BankRepository;
import com.banking.app.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public AccountDtos.AccountResponse createAccount(AccountDtos.CreateAccountRequest request) {
        if (accountRepository.existsByAccountNumber(request.accountNumber())) {
            throw DuplicateResourceException.accountNumber(request.accountNumber());
        }

        Bank bank = bankRepository.findById(request.bankId())
                .orElseThrow(() -> ResourceNotFoundException.bank(request.bankId()));

        var account = accountMapper.toEntity(request, bank);
        var saved = accountRepository.save(account);
        return accountMapper.toResponse(saved);
    }

    @Override
    public List<AccountDtos.AccountResponse> getAllAccounts() {
        return accountRepository.findAllWithBank()
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public List<AccountDtos.AccountResponse> getAccountsByBank(Long bankId) {
        if (!bankRepository.existsById(bankId)) {
            throw ResourceNotFoundException.bank(bankId);
        }
        return accountRepository.findAllByBankId(bankId)
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public AccountDtos.AccountResponse getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> ResourceNotFoundException.account(id));
    }
}
