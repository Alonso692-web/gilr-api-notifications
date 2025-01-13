package com.upiiz.expenses.controller;

import com.upiiz.expenses.entities.Notification;
import com.upiiz.expenses.responses.CustomResponse;
import com.upiiz.expenses.services.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://wcbdf-adl-examen-2.onrender.vercel.app"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1/notifications")
@Tag(
        name = "Notifications"
)
public class NotificationController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<List<Notification>>> getExpenses() {
        List<Notification> expens = new ArrayList<>();
        Link allExpensesLink = linkTo(NotificationController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);
        try {
            expens = expenseService.getAllExpenses();
            if (!expens.isEmpty()) {
                CustomResponse<List<Notification>> response = new CustomResponse<>(1, "Notificaciones encontradas", expens, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Notificaciones no encontradas", expens, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Notification>> response = new CustomResponse<>(500, "Error interno delservidor", expens, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<Notification>> getExpenseById(@PathVariable Long id) {
        Optional<Notification> expense = null;
        CustomResponse<Notification> response = null;
        Link allExpensesLink = linkTo(NotificationController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);
        try {
            expense = expenseService.getExpenseById(id);
            if (expense.isPresent()) {
                response = new CustomResponse<>(1, "Notificacion encontrada", expense.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Notificacion no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno delservidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CustomResponse<Notification>> crearExpense(@RequestBody Notification notification) {
        Link allExpensesLink = linkTo(NotificationController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);
        try {
            Notification notification1 = expenseService.createExpense(notification);
            if (notification1 != null) {
                CustomResponse<Notification> response = new CustomResponse<>(1, "Notificacion creada", notification1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Notificacion no encontrada", notification1, links));
            }
        } catch (Exception e) {
            CustomResponse<Notification> response = new CustomResponse<>(500, "Error interno delservidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomResponse<Notification>> updateExpense(@RequestBody Notification notification, @PathVariable Long id) {
        Link allExpensesLink = linkTo(NotificationController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);
        try {
            notification.setNotificationId(id);
            if (!expenseService.getExpenseById(id).equals("")) {
                Notification notificationEntity = expenseService.updateExpense(notification);
                CustomResponse<Notification> response = new CustomResponse<>(1, "Notificacion actualizado", notificationEntity, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                CustomResponse<Notification> response = new CustomResponse<>(0, "Notificacion no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            CustomResponse<Notification> response = new CustomResponse<>(500, "Error interno delservidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomResponse<Notification>> deleteExpenseById(@PathVariable Long id) {
        Optional<Notification> expenseEntity = null;
        CustomResponse<Notification> response = null;
        Link allExpensesLink = linkTo(NotificationController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);

        try {
            expenseEntity = expenseService.getExpenseById(id);
            if (expenseEntity.isPresent()) {
                expenseService.deleteExpense(id);
                response = new CustomResponse<>(1, "Notificacion eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Notificacion no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno delservidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}