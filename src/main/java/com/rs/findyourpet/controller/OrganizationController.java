package com.rs.findyourpet.controller;

import static com.rs.findyourpet.converter.OrganizationConverter.fromOrganizationToResponse;
import static com.rs.findyourpet.converter.OrganizationConverter.fromRequest;
import static org.springframework.http.HttpStatus.CREATED;

import com.rs.findyourpet.converter.OrganizationConverter;
import com.rs.findyourpet.dto.request.OrganizationRequest;
import com.rs.findyourpet.dto.response.OrganizationResponse;
import com.rs.findyourpet.service.OrganizationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public List<OrganizationResponse> findOrganizations() {
        var organizations = organizationService.find();

        return fromOrganizationToResponse(organizations);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrganizationResponse createOrganization(@RequestBody @Valid OrganizationRequest organizationRequest) { // TODO: use spring security
        var organization = fromRequest(organizationRequest);

        var saved = organizationService.save(organization); // TODO: add logs

        return fromOrganizationToResponse(saved);
    }
}
