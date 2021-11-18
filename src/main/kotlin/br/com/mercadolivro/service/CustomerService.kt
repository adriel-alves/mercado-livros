package br.com.mercadolivro.service

import br.com.mercadolivro.model.CustomerModel
import br.com.mercadolivro.model.request.PostCustomerRequest
import org.springframework.stereotype.Service

@Service
class CustomerService {

    var customerFakeList = mutableListOf<CustomerModel>()

    fun getCustomers(name: String?): List<CustomerModel> {
        name?.let {
            return customerFakeList.filter { it.name.contains(name, true)  }
        }
        return customerFakeList
    }

    fun getCustomer(id: String): CustomerModel {
        return customerFakeList.let { customers ->
            customers.first {
                it.id == id
            }
        }
    }

    fun createCustomer(customer: PostCustomerRequest): PostCustomerRequest {
        val id = if (customerFakeList.isEmpty()) {
            1
        } else {
            customerFakeList.last().id.toInt() + 1
        }.toString()
        customerFakeList.add(CustomerModel(id, customer.name, customer.email))

        return customer
    }

    fun updateCustomer(id: String, customer: PostCustomerRequest) {

        customerFakeList.first { it.id == id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id: String) {
        customerFakeList.removeIf { it.id == id }
    }


}
