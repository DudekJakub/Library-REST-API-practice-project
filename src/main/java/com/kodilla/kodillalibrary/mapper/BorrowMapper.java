package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Borrow;
import com.kodilla.kodillalibrary.domain.BorrowDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowMapper {

    public Borrow mapToBorrow(final BorrowDto borrowDto) {
        return new Borrow(
                borrowDto.getId(),
                borrowDto.getCopy(),
                borrowDto.getReader(),
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate(),
                borrowDto.isReturned()
        );
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getCopy(),
                borrow.getReader(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                borrow.isReturned()
        );
    }

    public List<BorrowDto> mapToBorrowDtoList(final List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
