package com.ssafy.teongbin.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileResponseDto {
    String email;
    String name;
}