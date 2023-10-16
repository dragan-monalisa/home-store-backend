package com.homestore.sale;

import com.homestore.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getSales(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(saleService.getSales(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<SaleResponse> getSale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.getSale(id));
    }

    @PostMapping
    public ResponseEntity<String> saveSale(@RequestBody SaleRequest request){
        saleService.saveSale(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}