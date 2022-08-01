package ui.page.metamask;

import core.control.Button;
import core.control.TextField;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import ui.BasePage;

public class SecretPage extends BasePage {

    private static final Logger log = Logger.getLogger(SecretPage.class);

    private final TextField secret_1 = new TextField(By.xpath("//*[@id='import-srp__srp-word-0']"));
    private final TextField secret_2 = new TextField(By.xpath("//*[@id='import-srp__srp-word-1']"));
    private final TextField secret_3 = new TextField(By.xpath("//*[@id='import-srp__srp-word-2']"));
    private final TextField secret_4 = new TextField(By.xpath("//*[@id='import-srp__srp-word-3']"));
    private final TextField secret_5 = new TextField(By.xpath("//*[@id='import-srp__srp-word-4']"));
    private final TextField secret_6 = new TextField(By.xpath("//*[@id='import-srp__srp-word-5']"));
    private final TextField secret_7 = new TextField(By.xpath("//*[@id='import-srp__srp-word-6']"));
    private final TextField secret_8 = new TextField(By.xpath("//*[@id='import-srp__srp-word-7']"));
    private final TextField secret_9 = new TextField(By.xpath("//*[@id='import-srp__srp-word-8']"));
    private final TextField secret_10 = new TextField(By.xpath("//*[@id='import-srp__srp-word-9']"));
    private final TextField secret_11 = new TextField(By.xpath("//*[@id='import-srp__srp-word-10']"));
    private final TextField secret_12 = new TextField(By.xpath("//*[@id='import-srp__srp-word-11']"));

    private final TextField passwordField = new TextField(By.xpath("//input[@id='password']"));
    private final TextField passwordAgain = new TextField(By.xpath("//input[@id='confirm-password']"));
    private final Button applyBtn = new Button(By.cssSelector("div[class='create-new-vault__terms'] input"));
    private final Button importBtn = new Button(By.cssSelector("button[type='submit']"));

    public void setSecret(String secret) {
        String[] secretPhrases = secret.split(" ");
        secret_1.setValue(secretPhrases[0]);
        secret_2.setValue(secretPhrases[1]);
        secret_3.setValue(secretPhrases[2]);
        secret_4.setValue(secretPhrases[3]);
        secret_5.setValue(secretPhrases[4]);
        secret_6.setValue(secretPhrases[5]);
        secret_7.setValue(secretPhrases[6]);
        secret_8.setValue(secretPhrases[7]);
        secret_9.setValue(secretPhrases[8]);
        secret_10.setValue(secretPhrases[9]);
        secret_11.setValue(secretPhrases[10]);
        secret_12.setValue(secretPhrases[11]);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void confirmPassword(String password) {
        passwordAgain.setValue(password);
    }

    public void apply() {
        applyBtn.press();
    }

    public CongratsPage importAcc() {
        importBtn.press();
        return new CongratsPage();
    }

    public SecretPage() {
        super(By.cssSelector("form[class='create-new-vault__form']"));
        log.info("Secret page is opened");
    }
}
