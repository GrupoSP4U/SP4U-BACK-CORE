package com.bandtec.sp4u.application.responses;

import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.notifications.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends Response {

	private Usuario usuario;

}
