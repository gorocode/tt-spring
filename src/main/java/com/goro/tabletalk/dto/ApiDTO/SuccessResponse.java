package com.goro.tabletalk.dto.ApiDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessResponse<T> {
    private String status;
    private T data;
}
