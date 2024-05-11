package com.rs.findyourpet.service;

import com.rs.findyourpet.domain.Organization;
import com.rs.findyourpet.repository.OrganizationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public List<Organization> find() {
        return organizationRepository.findAll();
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }
}
