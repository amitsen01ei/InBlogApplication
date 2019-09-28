package com.app.converters;

import com.app.dtos.response.PageableResponseDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PageableResponseConverter<S, T> {


    /**
     * Convert the page object of type {@code S} to pageabel response of target type {@code T}.
     *
     * @param source    the pageable object to convert, which must be an instance of
     *                  {@link org.springframework.data.domain.Page} of {@code S} (never {@code null})
     * @param converter converter of type {@code S} to target type {@code T}.
     * @return the converted object, which must be an instance of
     * {@link com.app.dtos.response.PageableResponseDTO} of {@code T} (potentially {@code null})
     */
    public PageableResponseDTO<T> convert(Page<S> source, Converter<S, T> converter) {
        List<T> dataList = source.get().map(converter::convert)
                .collect(Collectors.toList());
        return make(source,dataList);
    }

    private PageableResponseDTO<T> make(Page<S> pageInformation, Collection<T> data){
        PageableResponseDTO<T> responseDTO = new PageableResponseDTO<>();
        responseDTO.setTotal(pageInformation.getTotalElements());
        responseDTO.setData(data);
        responseDTO.setTotalPage(pageInformation.getTotalPages());
        return responseDTO;
    }
}
