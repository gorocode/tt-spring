package com.goro.tabletalk.dto.ApiDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MultipleErrorResponse {
    private String status;
    private List<ErrorResponse> errors;
}
