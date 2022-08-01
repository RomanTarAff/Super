package ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Page {
    MAIN("main"),
    METAMASK("metamask");

    private String page;
}
