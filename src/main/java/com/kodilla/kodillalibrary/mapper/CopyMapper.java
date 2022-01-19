package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.CopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    public Copy mapToCopy(final CopyDto copyDto) {
        return new Copy(
                copyDto.getId(),
                copyDto.getBook(),
                copyDto.isInCirculation(),
                copyDto.isDestroyed(),
                copyDto.isLost(),
                copyDto.isInLibrary()
        );
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getId(),
                copy.getBook(),
                copy.isInCirculation(),
                copy.isDestroyed(),
                copy.isLost(),
                copy.isInLibrary()
        );
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }
}
