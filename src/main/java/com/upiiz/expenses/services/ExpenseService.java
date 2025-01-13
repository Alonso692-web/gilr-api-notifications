package com.upiiz.expenses.services;

import com.upiiz.expenses.entities.Notification;
import com.upiiz.expenses.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public List<Notification> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Notification> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Notification createExpense(Notification notification) {
        return expenseRepository.save(notification);
    }

    public Notification updateExpense(Notification notification) {
        return expenseRepository.save(notification);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}