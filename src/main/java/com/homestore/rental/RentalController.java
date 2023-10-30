package com.homestore.rental;

import com.homestore.security.user.User;
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
@RequestMapping("/api/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<Page<RentalResponse>> getRentals(@AuthenticationPrincipal User realtor,
                                                           Pageable pageable){
        return ResponseEntity.ok(rentalService.getRentals(realtor, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.getRental(id));
    }

    @PostMapping
    public ResponseEntity<String> saveRental(@RequestBody RentalRequest request){
        rentalService.saveRental(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}