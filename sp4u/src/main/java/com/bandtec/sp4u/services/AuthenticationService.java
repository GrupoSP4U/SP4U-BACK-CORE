package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import com.bandtec.sp4u.application.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
