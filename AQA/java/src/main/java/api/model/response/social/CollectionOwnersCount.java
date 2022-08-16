package api.model.response.social;

import lombok.Data;

import java.util.List;

@Data
public class CollectionOwnersCount {

    private List<Period> periods;
}
