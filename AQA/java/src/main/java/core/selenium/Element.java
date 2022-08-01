package core.selenium;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import core.selenium.wait.DriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;
import java.util.concurrent.Callable;

public class Element implements WebElement {

    private By _by;
    private Element _parent;
    private RemoteWebElement _webElement;
    private int index = 0;

    public Element(By by) {
        this._by = by;
    }

    public Element(By by, int index) {
        this._by = by;
        this.index = index;
    }

    private Element(RemoteWebElement webElement) {
        this._webElement = webElement;
    }

    private Element(Element parent, By by) {
        this._parent = parent;
        this._by = by;
    }

    public By get_by() {
        return _by;
    }

    public void click() {
        doElementAction(() -> getRemoteElement().click());
    }

    public void submit() {
        doElementAction(() -> getRemoteElement().submit());
    }

    public void sendKeys(CharSequence... keysToSend) {
        doElementAction(() -> getRemoteElement().sendKeys(keysToSend));
    }

    public void clear() {
        doElementAction(() -> getRemoteElement().clear());
    }

    public String getTagName() {
        return doElementAction(() -> getRemoteElement().getTagName(), String.class);
    }

    public String getAttribute(String name) {
        return doElementAction(() -> getRemoteElement().getAttribute(name), String.class);
    }

    public boolean isSelected() {
        return doElementAction(() -> getRemoteElement().isSelected(), Boolean.class);
    }

    public boolean isEnabled() {
        return doElementAction(() -> getRemoteElement().isEnabled(), Boolean.class);
    }

    public String getText() {
        return doElementAction(() -> getRemoteElement().getText().trim(), String.class);
    }

    public boolean isDisplayed() {
        return doElementAction(() -> getRemoteElement().isDisplayed(), Boolean.class);
    }

    public boolean isPresent() {
        return !getRemoteElements().isEmpty();
    }

    public boolean isDisplayed(boolean waitForElementPresent) {
        if (waitForElementPresent)
            return doElementAction(() -> getRemoteElements().size() > index && getRemoteElement().isDisplayed(), Boolean.class);
        else
            return doElementAction(() -> getRemoteElements().size() > index && getRemoteElement(false).isDisplayed(), Boolean.class);
    }

    public Point getLocation() {
        return doElementAction(() -> getRemoteElement().getLocation(), Point.class);
    }

    public Dimension getSize() {
        return doElementAction(() -> getRemoteElement().getSize(), Dimension.class);
    }

    public Rectangle getRect() {
        return doElementAction(() -> getRemoteElement().getRect(), Rectangle.class);
    }

    public String getCssValue(String propertyName) {
        return doElementAction(() -> getRemoteElement().getCssValue(propertyName), String.class);
    }

    private <T> T doElementAction(Callable action, Class<T> clazz) {
        int iterations = 3;
        do {
            try {
                return clazz.cast(action.call());
            } catch (StaleElementReferenceException se) {
            } catch (Exception e) {
                throw new WebDriverException(e.getMessage());
            }
        } while (iterations-- > 0);
        return null;
    }

    private void doElementAction(Runnable action) {
        int iterations = 3;
        do {
            try {
                action.run();
                break;
            } catch (StaleElementReferenceException se) {
            } catch (Exception e) {
                throw new NoSuchElementException(e.getMessage());
            }
        } while (iterations-- > 0);
    }

    public List<WebElement> findElements(By by) {
        return Lists.transform(getRemoteElement().findElements(by), new Function<WebElement, WebElement>() {
            public Element apply(WebElement element) {
                return new Element((RemoteWebElement) element);
            }
        });
    }

    public Element findElement(By by) {
        return new Element(this, by);
    }

    public String toString() {
        return getRemoteElement().toString();
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    private RemoteWebElement getRemoteElement() {
        return getRemoteElement(true);
    }

    private List<WebElement> getRemoteElements() {
        if (_parent != null)
            return _parent.getRemoteElement().findElements(_by);
        return DriverManager.getInstance().getDriver().findElements(_by);
    }

    private RemoteWebElement getRemoteElement(boolean waitForPresence) {
        if (waitForPresence && _webElement == null)
            DriverWait.waitElementPresent(_by);
        if (_webElement != null)
            return _webElement;
        if (_parent != null)
            return (RemoteWebElement) _parent.getRemoteElement().findElements(_by).get(index);
        return (RemoteWebElement) DriverManager.getInstance().getDriver().findElements(_by).get(index);
    }
}
