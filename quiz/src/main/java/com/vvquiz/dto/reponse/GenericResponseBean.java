package com.vvquiz.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponseBean <T>{
    private T data;
    private String message;
    private boolean success;
    private List<T> result;
    private PageBean pageBean;
    private String errorCode;

}
