package org.thirdyearproject.thirdyear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thirdyearproject.thirdyear.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
