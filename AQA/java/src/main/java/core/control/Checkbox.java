package core.control;

import core.selenium.Element;
import core.selenium.wait.DriverWait;
import org.openqa.selenium.By;

public class Checkbox extends BaseControl {

    public Checkbox(By by) {
        super(by);
    }

    public Checkbox(Element element) {
        super(element);
    }

    public void setState(boolean state){
        if(getState() == state) return;
        getElement().click();
        DriverWait.waitForPageLoaded();
    }

    public boolean getState(){
        return getElement().isSelected();
    }

}
