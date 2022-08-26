package ui.page.app;

import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Table;

public class CollectionActivityPage extends BasePage {

    public Table table = new Table();

    public CollectionActivityPage() {
        super(By.xpath("//div[contains(@class,'collection-activity-tab')]"));
    }
}
