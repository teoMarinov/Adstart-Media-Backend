package com.teodor.backend.util;

import com.teodor.backend.entity.QuoteRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class QuoteRequestSpecifications {
    public static Specification<QuoteRequest> filterBy(String service, LocalDate fromDate, LocalDate endDate) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (service != null && !service.isEmpty()) {
                predicates = cb.and(predicates, cb.equal(root.get("service"), service));
            }

            if (fromDate != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("createdAt"), fromDate.atStartOfDay()));
            }

            if (endDate != null) {
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("createdAt"), endDate.atTime(23, 59, 59)));
            }

            return predicates;
        };
    }
}
