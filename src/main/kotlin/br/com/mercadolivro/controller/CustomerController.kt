package br.com.mercadolivro.controller

import br.com.mercadolivro.model.CustomerModel
import br.com.mercadolivro.model.request.PostCustomerRequest
import br.com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController( val customerService: CustomerService) {

    @GetMapping
    fun getCostumers(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getCustomers(name)
    }

    @GetMapping("/{id}")
    fun getCostumer(@PathVariable id: String): CustomerModel {
        return customerService.getCustomer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {
        return customerService.createCustomer(customer)
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCostumer(@PathVariable id: String, @RequestBody customer: PostCustomerRequest) {
        return customerService.updateCustomer(id, customer)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCostumer(@PathVariable id: String) {
        customerService.deleteCustomer(id)
    }
}