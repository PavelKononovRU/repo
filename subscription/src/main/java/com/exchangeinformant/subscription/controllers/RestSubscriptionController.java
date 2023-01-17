package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.repository.SubscriptionRepository;
import com.exchangeinformant.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestSubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public RestSubscriptionController(SubscriptionService subscriptionService,
                                      SubscriptionRepository subscriptionRepository) {
        this.subscriptionService = subscriptionService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getSubscription() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
    }

    @GetMapping("/get-subscriptions-by-status")
    public ResponseEntity<Page<SubscriptionDTO>> getSubscriptionsByStatus(@RequestParam String status, @RequestParam int offset, @RequestParam int limit, Pageable pageable) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsWithPagination(status, offset, limit, pageable));
    }

    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @PostMapping("/subscriptions")
    @ResponseBody
    public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @PutMapping("/subscriptions")
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.updateSubscription(subscriptionDTO);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
