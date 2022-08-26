package ui.page.app;

import core.control.Button;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Table;

public class MyOffersPage extends BasePage {

    public Table table = new Table();
    public String STATUS = "//div[@class='switch-item__label'][text()='%s']";

    public MyOffersPage() {
        super(By.xpath("//div[@class='app-container']"));
    }


    public void status(String item) {
        new Button(By.xpath(String.format(STATUS, item))).pressElementAndScrollToTheTop();
        sleep(2);
    }
}
