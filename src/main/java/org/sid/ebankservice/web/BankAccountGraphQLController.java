package org.sid.ebankservice.web;

import org.sid.ebankservice.dto.BankAccountRequestDTO;
import org.sid.ebankservice.dto.BankAccountResponseDTO;
import org.sid.ebankservice.entities.BankAccount;
import org.sid.ebankservice.mappers.AccountMapper;
import org.sid.ebankservice.repositories.BankAccountRepository;
import org.sid.ebankservice.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountServiceImpl accountService;
    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount account(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

//    @MutationMapping
//    public BankAccount addAccount(@Argument BankAccountRequestDTO bankAccountRequestDTO){
//        BankAccount bankAccount = accountMapper.fromAccountRequestDTO(bankAccountRequestDTO);
//        return bankAccountRepository.save(bankAccount);
//    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccountDTO){

        BankAccountResponseDTO bankAccount = accountService.addAccount(bankAccountDTO);
        return bankAccount;
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccountDTO){

        BankAccountResponseDTO bankAccount = accountService.updateAccount(id,bankAccountDTO);

        return bankAccount;
    }

    @MutationMapping
    public String deleteAccount(@Argument String id){

        bankAccountRepository.deleteById(id);

        return "account deleted successfully";
    }

}
