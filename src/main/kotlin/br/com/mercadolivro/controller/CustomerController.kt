package br.com.mercadolivro.controller

import br.com.mercadolivro.model.CustomerModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customers")
class CustomerController {

    @GetMapping
    fun helloWorld(): CustomerModel{
        return CustomerModel("1", "John Doe", "email@email.com")
    }
}