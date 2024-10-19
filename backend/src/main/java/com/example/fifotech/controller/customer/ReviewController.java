package com.example.fifotech.controller.customer;

import com.example.fifotech.dto.OrderedProductResponseDto;
import com.example.fifotech.dto.ReviewDto;
import com.example.fifotech.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin(origins = {"https://wetechhub.com", "http://localhost:4200"},  allowCredentials = "true")
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }


    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {

        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);

        if (reviewDto1 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
    }


}
