package com.homestore.ad;

public enum StatusEnum {
    PENDING,     // The object is waiting for a response from the realtor
    ACTIVE,      // The object is active/ has been accepted by the realtor
    INACTIVE,    // The object is inactive
    REJECTED     // The object has been rejected by the realtor
}