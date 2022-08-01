package core.control;

import core.selenium.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextField extends BaseControl {

    public TextField(By by){
        super(by);
    }

    public TextField(String inputDataAuto){
        super(By.cssSelector(String.format("input[data-auto='%s']", inputDataAuto)));
    }

    public TextField(Element element){
        super(element);
    }

    public void setValue(String text){
        getElement().clear();
        getElement().sendKeys(text);
    }

    public void setAndConfirmValue(String text){
        getElement().clear();
        getElement().sendKeys(text);
        getElement().sendKeys(Keys.ENTER);
    }

    public void setValueAndTab(String text){
        getElement().clear();
        getElement().sendKeys(text);
        getElement().sendKeys(Keys.TAB);
    }
}
