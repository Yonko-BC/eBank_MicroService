package org.sid.ebankservice.web;

import org.sid.ebankservice.dto.BankAccountRequestDTO;
import org.sid.ebankservice.dto.BankAccountResponseDTO;
import org.sid.ebankservice.entities.BankAccount;
import org.sid.ebankservice.mappers.AccountMapper;
import org.sid.ebankservice.repositories.BankAccountRepository;
import org.sid.ebankservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    @PostMapping("/bankAccounts")
//    public BankAccount save(@RequestBody BankAccount bankAccount){
//        if (bankAccount.getId() == null) {
//            bankAccount.setId(UUID.randomUUID().toString());
//        }
//        if (bankAccount.getCreatedAt() == null) {
//            bankAccount.setCreatedAt(new Date());
//        }
//        return  bankAccountRepository.save(bankAccount);
//    }
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccount){
        return  accountService.addAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount save(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null)
            account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null)
            account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType()!=null)
            account.setType(bankAccount.getType());

        account.setCreatedAt(new Date());

        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
