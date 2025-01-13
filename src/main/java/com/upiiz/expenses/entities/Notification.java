package com.upiiz.expenses.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "notification_text", nullable = false, columnDefinition = "TEXT")
    private String notificationText;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "notification_date")
    private LocalDate notificationDate;
}