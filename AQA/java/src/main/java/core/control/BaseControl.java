package core.control;

import core.selenium.DriverManager;
import core.selenium.Element;
import core.selenium.wait.DriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;

public abstract class BaseControl {

    private Element element;
    private int index;

    public BaseControl(Element element){
        this.element = element;
    }

    public BaseControl(By by) {
        element = new Element(by);
    }

    public BaseControl(By by, int index) {
        element = new Element(by, index);
    }

    public Element getElement() {
        return element;
    }

    public boolean isDisplayed(){
        return isDisplayed(false);
    }

    public boolean isPresent() {
        return element.isPresent();
    }

    public boolean isEnabled(){
        return element.isEnabled();
    }

    public String getText(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        Point location = getElement().getLocation();
        String script = String.format("window.scrollTo(0, %d);", location.getY());
        js.executeScript(script);
        return getElement().getText();
    }

    public String getTextWithout(){
        return getElement().getText();
    }

    public String getValue(){
        return getElement().getAttribute("value");
    }

    public boolean isDisplayed(boolean withoutWait){
        if(withoutWait) {
            DriverWait.turnOffImplicitWaits();
            try {
                return getElement().isDisplayed(false);
            } finally {
                DriverWait.turnOnImplicitWaits();
            }
        }else
            return getElement().isDisplayed();
    }
}
