package core.control;

import core.selenium.Element;
import org.openqa.selenium.By;

public class TextLabel extends BaseControl {

    public TextLabel(String inputDataAuto){
        super(By.xpath(String.format("//*[@data-auto='%s']", inputDataAuto)));
    }

    public TextLabel(Element parent, String inputDataAuto){
        super(parent.findElement(By.xpath(String.format("//*[@data-auto='%s']", inputDataAuto))));
    }

    public TextLabel(By by) {
        super(by);
    }

    public TextLabel(By by, int index) {
        super(by, index);
    }

    public TextLabel(Element element) {
        super(element);
    }
}
