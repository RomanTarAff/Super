package ui.page.app;

import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Table;

public class MyActivityPage extends BasePage {

    public Table table = new Table();

    public MyActivityPage() {
        super(By.xpath("//div[contains(@class,'profile-my-activity')]"));
    }
}
