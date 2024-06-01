package com.rs.findyourpet.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class ZipCodeResultResponse {

    private Map<String, List<ZipCodeLocationResponse>> results;
}
