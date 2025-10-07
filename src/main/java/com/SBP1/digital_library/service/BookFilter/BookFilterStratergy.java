package com.SBP1.digital_library.service.BookFilter;

import com.SBP1.digital_library.dto.BookFilterResponse;
import com.SBP1.digital_library.enums.Operator;

import java.util.List;

public interface BookFilterStratergy {

    List<BookFilterResponse> getFilteredBook(Operator operator, String value);

}
