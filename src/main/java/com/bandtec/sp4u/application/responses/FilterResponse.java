package com.bandtec.sp4u.application.responses;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FilterResponse extends Response {

    private List<Estabelecimento> estabelecimentos;

    public FilterResponse() {
        this.estabelecimentos = new ArrayList<>();
    }
}
