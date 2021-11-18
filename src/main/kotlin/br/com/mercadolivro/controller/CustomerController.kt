package br.com.mercadolivro.controller

import br.com.mercadolivro.model.CustomerModel
import br.com.mercadolivro.model.request.PostCustomerRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController {

    var customerFakeList = mutableListOf<CustomerModel>()

    @GetMapping()
    fun getCostumers(@RequestParam name: String?): List<CustomerModel> {
        name?.let {
            return customerFakeList.filter { it.name.contains(name, true) }
        }

        return customerFakeList
    }

    @GetMapping("/{id}")
    fun getCostumer(@PathVariable id: String): CustomerModel {
        return customerFakeList.filter { it.id == id }.first()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {
        createCustomer(customer)
        return customer
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCostumer(@PathVariable id: String, @RequestBody customer: PostCustomerRequest) {
        customerFakeList.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCostumer(@PathVariable id: String) {
        customerFakeList.removeIf { it.id == id }
    }

    private fun createCustomer(customer: PostCustomerRequest) {
        val id = if (customerFakeList.isEmpty()) {
            1
        } else {
            customerFakeList.last().id.toInt() + 1
        }.toString()
        customerFakeList.add(CustomerModel(id, customer.name, customer.email))

    }
}