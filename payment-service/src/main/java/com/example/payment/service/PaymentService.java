package com.example.payment.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.payment.repo.PaymentRepository;
import com.example.payment.model.Payment;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository repo;
    private final KafkaTemplate<String, String> kafka;
    private final String topic = "payments.created";

    public PaymentService(PaymentRepository repo, KafkaTemplate<String,String> kafka) {
        this.repo = repo;
        this.kafka = kafka;
    }

    @Transactional
    public Payment createPayment(Payment p) {
        if (p.getId() == null) p.setId(UUID.randomUUID());
        p.setStatus("CREATED");
        p.setCreatedAt(java.time.OffsetDateTime.now());
        Payment saved = repo.save(p);

        // Emit a simple JSON event (could be Avro or more structured in prod)
        String event = "{\"id\":\""+saved.getId()+"\",\"orderId\":\""+saved.getOrderId()+"\",\"amount\":"+saved.getAmount()+",\"currency\":\""+saved.getCurrency()+"\",\"status\":\""+saved.getStatus()+"\"}";
        kafka.send(topic, saved.getId().toString(), event);
        return saved;
    }

    public Payment getPayment(UUID id) {
        return repo.findById(id).orElseThrow();
    }

}
