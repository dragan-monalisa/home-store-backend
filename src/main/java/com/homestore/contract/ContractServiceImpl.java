package com.homestore.contract;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService{

    private final ContractRepository contractRepository;
    
    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }
}