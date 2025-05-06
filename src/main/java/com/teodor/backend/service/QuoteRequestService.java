package com.teodor.backend.service;

import com.teodor.backend.dto.QuoteRequestDto;
import com.teodor.backend.entity.QuoteRequest;
import com.teodor.backend.repository.QuoteRequestRepository;
import com.teodor.backend.util.QuoteRequestSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class QuoteRequestService {
    private final QuoteRequestRepository quoteRequestRepository;

    public QuoteRequestService(QuoteRequestRepository quoteRequestRepository) {
        this.quoteRequestRepository = quoteRequestRepository;
    }

    public QuoteRequest createQuoteRequest(QuoteRequestDto quoteRequestDto){
        QuoteRequest quoteRequest = buildQuoteRequest(quoteRequestDto);

        return quoteRequestRepository.save(quoteRequest);
    }

    private QuoteRequest buildQuoteRequest(QuoteRequestDto quoteRequestDto){
        return QuoteRequest.builder()
                .fullname(quoteRequestDto.getFullname())
                .email(quoteRequestDto.getEmail())
                .phoneNumber(quoteRequestDto.getPhoneNumber())
                .company(quoteRequestDto.getCompany())
                .service(quoteRequestDto.getService())
                .priceRange(quoteRequestDto.getPriceRange().getDisplayValue())
                .build();
    }

    public Page<QuoteRequest> getAllQuoteRequests(int page, int pageSize, String service, LocalDate fromDate, LocalDate endDate) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Specification<QuoteRequest> spec = QuoteRequestSpecifications.filterBy(service, fromDate, endDate);
        return quoteRequestRepository.findAll(spec, pageable);
    }

    public Optional<QuoteRequest> getQuoteSubmissionById(Long id) {return quoteRequestRepository.findById(id); }
}
