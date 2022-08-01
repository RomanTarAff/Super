package api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CodeMessageResponse {

    private String code;
    private String message;
    private Payload payload;
}
