package core.control;

import core.selenium.DriverManager;
import core.selenium.Element;
import core.selenium.wait.DriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;

public class Button extends BaseControl {

    public Button(String buttonText) {
        super(By.xpath(String.format("//button[text()='%s']", buttonText)));
    }

    public Button(By by, int index) {
        super(by, index);
    }

    public Button(By by) {
        super(by);
    }

    public Button(Element element) {
        super(element);
    }

    public void press() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        Point location = getElement().getLocation();
        String script = String.format("window.scrollTo(0, %d);", location.getY() - 20);
        js.executeScript(script);
        getElement().isDisplayed(true);
        DriverWait.waitElementClickable(getElement().get_by());
        getElement().click();
    }

    public void pressWithScrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        Point location = getElement().getLocation();
        String script = String.format("window.scrollTo(0, %d);", location.getY() + 20);
        js.executeScript(script);
        getElement().isDisplayed(true);
        DriverWait.waitElementClickable(getElement().get_by());
        getElement().click();
    }

    public void pressWithout() {
        getElement().isDisplayed(true);
        DriverWait.waitElementClickable(getElement().get_by());
        getElement().click();
    }

    public void pressWithScrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        String script = "window.scrollTo(0, 0);";
        js.executeScript(script);
        getElement().isDisplayed(true);
        DriverWait.waitElementClickable(getElement().get_by());
        getElement().click();
    }
}
