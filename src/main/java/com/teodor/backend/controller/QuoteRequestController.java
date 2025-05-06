package com.teodor.backend.controller;

import com.teodor.backend.dto.BaseResponseDto;
import com.teodor.backend.dto.QuoteRequestDto;
import com.teodor.backend.entity.QuoteRequest;
import com.teodor.backend.service.QuoteRequestService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController()
@CrossOrigin("*")
@RequestMapping("/quote-request")
public class QuoteRequestController {
    private final QuoteRequestService quoteRequestService;

    public QuoteRequestController(QuoteRequestService quoteRequestService) {
        this.quoteRequestService = quoteRequestService;
    }

    @PostMapping("")
    public ResponseEntity<BaseResponseDto<QuoteRequest>> createRequest(@RequestBody @Valid QuoteRequestDto requestDto) {
        BaseResponseDto<QuoteRequest> baseResponseDto = new BaseResponseDto<>();

        QuoteRequest createdRequest = quoteRequestService.createQuoteRequest(requestDto);
        baseResponseDto.setSuccessResult(createdRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRequest.getId())
                .toUri();

        return ResponseEntity.created(location).body(baseResponseDto);
    };

    @GetMapping("")
    public ResponseEntity<BaseResponseDto<Page<QuoteRequest>>> getAllRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String service,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate toDate
            ) {
        int pageSize = 10;
        BaseResponseDto<Page<QuoteRequest>> baseResponseDto = new BaseResponseDto<>();

        Page<QuoteRequest> requests = quoteRequestService.getAllQuoteRequests(
                page,
                pageSize,
                service,
                fromDate,
                toDate
                );
        baseResponseDto.setSuccessResult(requests);

        return ResponseEntity.ok(baseResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto<Optional<QuoteRequest>>> getRequestById(
            @PathVariable Long id
    ){
        BaseResponseDto<Optional<QuoteRequest>> baseResponseDto = new BaseResponseDto<>();

        Optional<QuoteRequest> request = quoteRequestService.getQuoteSubmissionById(id);
        baseResponseDto.setSuccessResult(request);

        return ResponseEntity.ok(baseResponseDto);
    }
}
