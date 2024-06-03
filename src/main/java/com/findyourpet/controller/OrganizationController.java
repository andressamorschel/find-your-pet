package com.findyourpet.controller;

import static com.findyourpet.converter.OrganizationConverter.fromOrganizationToResponse;
import static com.findyourpet.converter.OrganizationConverter.fromRequest;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.findyourpet.dto.request.OrganizationRequest;
import com.findyourpet.dto.response.OrganizationResponse;
import com.findyourpet.service.OrganizationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizations")
public class OrganizationController { // TODO: use spring security

    private final OrganizationService organizationService;

    @GetMapping
    public List<OrganizationResponse> findOrganizations() {
        var organizations = organizationService.find();

        return fromOrganizationToResponse(organizations);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrganizationResponse createOrganization(@RequestBody @Valid OrganizationRequest organizationRequest) {
        var organization = fromRequest(organizationRequest);

        var saved = organizationService.save(organization);

        return fromOrganizationToResponse(saved);
    }

    @PutMapping("/{organizationId}")
    public OrganizationResponse editOrganization(@RequestBody @Valid OrganizationRequest organizationRequest, @PathVariable long organizationId) {
        var editedOrganization = organizationService.editOrganization(organizationId, organizationRequest);

        return fromOrganizationToResponse(editedOrganization);
    }

    @DeleteMapping("/{organizationId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteOrganization(@PathVariable long organizationId) {
        organizationService.deleteOrganization(organizationId);
    }
}
