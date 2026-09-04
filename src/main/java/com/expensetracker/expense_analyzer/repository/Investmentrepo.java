package com.expensetracker.expense_analyzer.repository;
import com.expensetracker.expense_analyzer.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Investmentrepo extends JpaRepository<Investment, Long> {
}
