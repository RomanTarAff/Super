package core.control;

import core.selenium.Element;
import org.openqa.selenium.By;

public class Link extends BaseControl {

    public Link(String linkText){
        super(By.linkText(linkText));
    }

    public Link(Element parent, String linkText){
        super(parent.findElement(By.linkText(linkText)));
    }

    public Link(Element element) {
        super(element);
    }

    public void click(){
        getElement().click();
    }

    public String linkText() {
        return getElement().getText();
    }

    public String linkAddress() {
        return getElement().getAttribute("href");
    }
}
