package com.mironov.image.studio.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessageDto {

    private Long id;
    private String email;
    private String text;

}
