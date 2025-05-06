package com.teodor.backend.repository;

import com.teodor.backend.entity.QuoteRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface  QuoteRequestRepository extends JpaRepository<QuoteRequest, Long>, JpaSpecificationExecutor<QuoteRequest> {}
