package com.upiiz.expenses.repository;

import com.upiiz.expenses.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Notification, Long> {
}