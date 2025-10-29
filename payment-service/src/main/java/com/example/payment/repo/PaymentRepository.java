package com.example.payment.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.payment.model.Payment;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
