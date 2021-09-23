package rw.project.bkaccount.model


import javax.persistence.Entity
import javax.persistence.Id


@Entity
class Customer {

     @Id lateinit var id:String;
     lateinit var firstName:String;
     lateinit var lastName:String;
     lateinit var age:Integer;

     constructor(id:String, firstName:String, lastName:String, age:Integer){
         this.id = id;
         this.lastName = lastName;
         this.firstName = firstName;
         this.age = age;
     }
    constructor(){}

}