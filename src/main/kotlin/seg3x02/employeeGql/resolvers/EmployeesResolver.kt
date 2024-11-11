package seg3x02.employeeGql.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver : GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private lateinit var employeesRepository: EmployeesRepository

    fun employees(): List<Employee> {
        return employeesRepository.findAll()
    }

    fun addEmployee(input: CreateEmployeeInput): Employee {
        val employee = Employee(
            name = input.name ?: "",
            dateOfBirth = input.dateOfBirth ?: "",
            city = input.city ?: "",
            salary = input.salary ?: 0.0f,
            gender = input.gender,
            email = input.email
        )
        return employeesRepository.save(employee)
    }
}
