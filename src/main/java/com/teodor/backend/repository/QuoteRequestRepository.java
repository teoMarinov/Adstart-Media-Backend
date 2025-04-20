package com.teodor.backend.repository;

import com.teodor.backend.entity.QuoteRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
    @NotNull Page<QuoteRequest> findAll(@NotNull Pageable pageable);
}
