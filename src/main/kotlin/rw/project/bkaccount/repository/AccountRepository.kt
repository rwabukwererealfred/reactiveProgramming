package rw.project.bkaccount.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rw.project.bkaccount.model.Account

@Repository
interface AccountRepository: JpaRepository<Account, String> {
}