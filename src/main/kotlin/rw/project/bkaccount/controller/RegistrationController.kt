package rw.project.bkaccount.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import rw.project.bkaccount.Dto.AccountDto
import rw.project.bkaccount.model.Account
import rw.project.bkaccount.model.Customer
import rw.project.bkaccount.service.RegistrationService


@RestController
@RequestMapping("/api/bk/")
class RegistrationController {

    var webClient = WebClient.create("http://localhost:8181")

    @Autowired
    lateinit var registrationService:RegistrationService;

    @PostMapping("newCustomer")
    fun registerNewCustomer(@RequestBody customer:Customer):Mono<ResponseEntity<String>>{
      try {
          return registrationService.registerCustomer(customer).then(Mono.just
              (ResponseEntity<String>("WELL SUCCESSFUL SAVED",HttpStatus.OK)));
      }catch (ex:Exception){
          return Mono.just(ResponseEntity(ex.message,HttpStatus.BAD_REQUEST))
      }

    }

    @GetMapping("findCustomerId/{id}")
    fun findByCustomerId(@PathVariable id:String):Mono<Customer>{
        return webClient.get()
            .uri("/api/elasticsearch/findCustomerById/{id}", id)
            .retrieve()
            .bodyToMono(Customer::class.java)
    }

    @PostMapping("newAccount")
    fun registerNewAccount(@RequestBody accountDto:AccountDto):Mono<ResponseEntity<String>>{
        try {
            return registrationService.registerNewAccount(accountDto).then(Mono.just
                (ResponseEntity<String>("WELL SUCCESSFUL SAVED",HttpStatus.OK)));
        }catch (ex:Exception){
            return Mono.just(ResponseEntity(ex.message,HttpStatus.BAD_REQUEST))
        }

    }

    @GetMapping("findAllAccount")
    fun allCustomerAccount():Flux<Account>{
        return registrationService.customerAccount();
    }



}