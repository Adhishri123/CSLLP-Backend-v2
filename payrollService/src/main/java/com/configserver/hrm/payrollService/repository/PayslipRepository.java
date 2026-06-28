package com.configserver.hrm.payrollService.repository;

import com.configserver.hrm.payrollService.entity.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PayslipRepository extends JpaRepository<Payslip, Long> {
    List<Payslip> findByMonth(LocalDate month);

    // Find payslip by employee ID and month
    Optional<Payslip> findByEmployeeIdAndMonth(Long employeeId, LocalDate month);

}
