package lk.ijse.backend.payloads.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MassegeResponse {
    private String massege;

    public MassegeResponse(String massege) {
        this.massege = massege;
    }
}
