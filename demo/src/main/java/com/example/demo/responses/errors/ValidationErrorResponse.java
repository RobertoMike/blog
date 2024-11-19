package com.example.demo.responses.errors;

import java.util.ArrayList;
import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
    public ValidationErrorResponse() {
        this(new ArrayList<>());
    }
}
