package com.teodor.backend.service;

import com.teodor.backend.dto.QuoteRequestDto;
import com.teodor.backend.entity.QuoteRequest;
import com.teodor.backend.repository.QuoteRequestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<QuoteRequest> getAllQuoteRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return quoteRequestRepository.findAll(pageable);
    }

    public Optional<QuoteRequest> getQuoteSubmissionById(Long id) {return quoteRequestRepository.findById(id); }
}
