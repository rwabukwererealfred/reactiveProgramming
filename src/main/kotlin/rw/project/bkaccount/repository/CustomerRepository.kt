package rw.project.bkaccount.repository

import rw.project.bkaccount.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository:JpaRepository<Customer, String> {
}
