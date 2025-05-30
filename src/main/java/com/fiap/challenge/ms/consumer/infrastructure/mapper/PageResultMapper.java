package com.fiap.challenge.ms.consumer.infrastructure.mapper;

import com.fiap.challenge.ms.consumer.domain.model.PageResult;

import java.util.function.Function;

public class PageResultMapper {

    public static <T, R> PageResult<R> transformContent(PageResult<T> page, Function<T, R> mapper) {
        return PageResult.<R>builder()
            .content(page.getContent().stream().map(mapper).toList())
            .pageNumber(page.getPageNumber())
            .pageSize(page.getPageSize())
            .totalElements(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .build();
    }
}
