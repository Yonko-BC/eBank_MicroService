package org.sid.ebankservice.services;

import org.sid.ebankservice.dto.BankAccountRequestDTO;
import org.sid.ebankservice.dto.BankAccountResponseDTO;
import org.sid.ebankservice.entities.BankAccount;
import org.sid.ebankservice.mappers.AccountMapper;
import org.sid.ebankservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

//        BankAccount bankAccount = BankAccount.builder()
//                .id(UUID.randomUUID().toString())
//                .createdAt(new Date())
//                .currency(bankAccountDTO.getCurrency())
//                .balance(bankAccountDTO.getBalance())
//                .type(bankAccountDTO.getType())
//                .build();

        BankAccount bankAccount =  accountMapper.fromAccountRequestDTO(bankAccountDTO);

        BankAccount savedBankAccount =bankAccountRepository.save(bankAccount);

//        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
//                .id(savedBankAccount.getId())
//                .createdAt(savedBankAccount.getCreatedAt())
//                .type(savedBankAccount.getType())
//                .balance(savedBankAccount.getBalance())
//                .currency(savedBankAccount.getCurrency())
//                .build();

        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .currency(bankAccountDTO.getCurrency())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .build();

        BankAccount savedBankAccount =bankAccountRepository.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

        return bankAccountResponseDTO;
    }
}
