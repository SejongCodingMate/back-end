package com.creativesemester.SejongCodingMate.domain.member.service;

import com.creativesemester.SejongCodingMate.domain.member.dto.request.AuthRequestDto;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${sejong.url}")
    private String apiUrl;
    public JsonObject authenticateSejongStudent(AuthRequestDto authRequestDto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthRequestDto> requestEntity = new HttpEntity<>(authRequestDto);
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject()
                    .getAsJsonObject("result");

            boolean isAuth = jsonObject.getAsJsonPrimitive("is_auth").getAsBoolean();
            if (isAuth) {
                return jsonObject.getAsJsonObject("body");
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
