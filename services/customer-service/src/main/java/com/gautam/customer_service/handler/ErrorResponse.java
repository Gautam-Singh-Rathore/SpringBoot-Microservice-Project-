package com.gautam.customer_service.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
