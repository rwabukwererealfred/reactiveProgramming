package rw.project.bkaccount.Util

import rw.project.bkaccount.Dto.AccountDto
import rw.project.bkaccount.model.Account

object AccountMapper {

    fun toDto(account:Account):AccountDto{
        var accountDto:AccountDto = AccountDto();
        accountDto.accountNumber = account.accountNumber;
        accountDto.accountName = account.accountName;
        accountDto.customerId = account.customerId;
        return accountDto;
    }

    fun toEntity(accountDto:AccountDto):Account{
        var account:Account = Account();
        account.customerId =accountDto.customerId;
        account.accountNumber = accountDto.accountNumber;
        account.accountName = accountDto.accountName;
        return account;
    }
}