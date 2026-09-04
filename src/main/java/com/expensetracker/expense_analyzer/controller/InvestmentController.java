package com.expensetracker.expense_analyzer.controller;
import com.expensetracker.expense_analyzer.model.Investment;
import com.expensetracker.expense_analyzer.repository.Investmentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private Investmentrepo investmentRepo;

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentRepo.findAll();
    }

    @PostMapping
    public Investment saveInvestment(@RequestBody Investment investment) {
        return investmentRepo.save(investment);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable Long id) {
        investmentRepo.deleteById(id);
    }
}
