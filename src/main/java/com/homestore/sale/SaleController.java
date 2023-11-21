package com.homestore.sale;

import com.homestore.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<Page<SaleResponse>> getSales(@AuthenticationPrincipal User realtor,
                                                       Pageable pageable){
        return ResponseEntity.ok(saleService.getSales(realtor, pageable));
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