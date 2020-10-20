package com.bandtec.sp4u.api.clients;

import com.bandtec.sp4u.domain.models.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "sp4u-auth", url = "ubuntu@ec2-100-26-215-123.compute-1.amazonaws.com:8080")
public interface AuthClient {
    @Bean
    @PostMapping("/token")
    ResponseEntity validateToken(@RequestHeader("Authorization") String token,
                                 @RequestBody String email);

    @Bean
    @PostMapping("/user")
    ResponseEntity createUser(@RequestBody UsuarioDTO usuarioDTO);
}
