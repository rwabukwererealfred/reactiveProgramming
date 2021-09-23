package rw.project.bkaccount.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account {

    @Id lateinit var accountNumber:String;
    lateinit var accountName:String;
    lateinit var customerId: String;



    constructor(accountNumber:String, accountName:String, customerId:String){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
    }
    constructor(){}
}