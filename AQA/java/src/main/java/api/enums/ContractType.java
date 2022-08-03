package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContractType {

    ERC721("ERC721");

    private final String value;
}
