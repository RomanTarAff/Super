package api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class Payload {

    private List<String> errors;
}
