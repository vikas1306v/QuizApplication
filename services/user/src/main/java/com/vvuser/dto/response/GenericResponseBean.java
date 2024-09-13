package com.vvuser.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenericResponseBean<T> {
    private String message;
    private Boolean status;
    private T data;
    private List<T> result;
}
