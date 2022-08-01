package ui.page.app.collection;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;
import ui.form.Table;

public class CollectionActivityPage extends BasePage {

    private static final Logger log = Logger.getLogger(CollectionActivityPage.class);

    public Table table = new Table();

    public CollectionActivityPage() {
        super(By.xpath("//div[@class='table-container activity__table']"));
        log.info("Collection Activity page is opened");
    }
}
