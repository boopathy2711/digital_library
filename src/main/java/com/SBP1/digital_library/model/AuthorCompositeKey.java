package com.SBP1.digital_library.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthorCompositeKey {
    private String id;
    private String email;
}
