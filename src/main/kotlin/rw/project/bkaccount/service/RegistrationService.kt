package rw.project.bkaccount.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import rw.project.bkaccount.Dto.AccountDto
import rw.project.bkaccount.Util.AccountMapper
import rw.project.bkaccount.model.Account
import rw.project.bkaccount.model.Customer
import rw.project.bkaccount.repository.AccountRepository

import rw.project.bkaccount.repository.CustomerRepository
import java.lang.RuntimeException
import java.util.*

@Service
class RegistrationService {

    @Autowired
    lateinit var customerRepository:CustomerRepository;

    var webClient = WebClient.create("http://localhost:8181");



    @Autowired
    lateinit var accountRepository: AccountRepository;

    fun registerCustomer(customer:Customer):Mono<Customer>{
        var cust:Optional<Customer> = customerRepository.findById(customer.id)
        if(cust.isPresent) return throw RuntimeException("customer id is already exist")else {
            var cust:Mono<Customer> = webClient.post().uri ( "/api/elasticsearch/registerCustomer" )
                .body(Mono.just(customer), Customer::class.java)
                .retrieve()
                .bodyToMono(Customer::class.java);
            if(cust !=null){
                println("out put ${cust} --------------")
                return customerRepository.save(customer).toMono();
            }else{
                throw RuntimeException("customer does not registered into elasticRepository")
            }

        }
    }

    fun registerNewAccount(accountDto: AccountDto):Mono<Account> {

            var customer: Optional<Customer> = customerRepository.findById(accountDto.customerId);
            var account :Optional<Account> = accountRepository.findById(accountDto.accountNumber);
        if(account.isPresent) return throw RuntimeException("account number is already exist")
            if (customer.isPresent) {
                    return accountRepository.save(AccountMapper.toEntity(accountDto)).toMono();

            }
            return throw RuntimeException("Customer id not found");

    }

    fun customerAccount():Flux<Account>{
        return accountRepository.findAll().toFlux();
    }


}