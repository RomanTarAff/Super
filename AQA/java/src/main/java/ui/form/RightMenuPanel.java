package ui.form;

import core.control.Link;
import org.apache.log4j.Logger;

public class RightMenuPanel {

    private static final Logger log = Logger.getLogger(RightMenuPanel.class);

    private final Link documentsLnk = new Link("Documents");
    private final Link signOutLnk = new Link("Sign Out");

//    public LogoutPage signOut() {
//        signOutLnk.click();
//        log.info("Sign out was clicked");
//        return new LogoutPage();
//    }
}
