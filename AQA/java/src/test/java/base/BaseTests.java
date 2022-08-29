package base;

import api.enums.Account;
import api.model.response.social.CollectionResponse;
import api.model.response.social.ProfileResponse;
import api.service.admin.AdminService;
import api.service.nft.NftService;
import api.service.social.SocialService;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    private static final Logger log = Logger.getLogger(BaseTests.class);

    protected SocialService socialService = new SocialService();
    protected NftService nftService = new NftService();
    protected AdminService adminService = new AdminService();

    protected SoftAssert soft = new SoftAssert();
    protected final Faker faker = new Faker();

    protected static final String HOME = String.valueOf(Paths.get(new File("").getAbsolutePath()).getParent());
    protected static final String NPM_RUN_DEV = String.format("npm --prefix %s/node run tokenDev", HOME);
    protected static final String NPM_RUN_TEST = String.format("npm --prefix %s/node run tokenTest", HOME);
    protected static final String NPM_RUN_MINT = String.format("npm --prefix %s/node run mint", HOME);
    protected String MINT_TOKEN = "";
    protected String MINT_SIGNATURE = "";
    protected String ADMIN_TOKEN = "";
    protected String ADMIN_SIGNATURE = "";
    protected String BUY_TOKEN = "";
    protected String BUY_SIGNATURE = "";
    protected String MINT_NFT_NAME = "";
    protected String TEST_COLLECTION_CONTRACT_ADDRESS = "0x902dfd8f9f9d72feeb2457b45f528cd873b7669b";
    protected ProfileResponse userMint;
    protected ProfileResponse userBuy;

    @BeforeSuite
    @SneakyThrows
    protected void initTokens() {
        Runtime rt = Runtime.getRuntime();
        printOutput errorReported, outputMessage = null;
        System.out.println(HOME);
        Process proc = null;

        try {
            if (System.getProperty("host").equals("develop")) {
                proc = rt.exec(NPM_RUN_DEV);
            } else if (System.getProperty("host").equals("test")) {
                proc = rt.exec(NPM_RUN_TEST);
            }

            errorReported = getStreamWrapper(proc.getErrorStream(), "ERROR");
            outputMessage = getStreamWrapper(proc.getInputStream(), "OUTPUT");
            errorReported.start();
            outputMessage.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(10);
    }

    @BeforeClass
    public void initUsers() {
        userMint = socialService.getMyProfile(System.getProperty(Account.MINT.getENV())).asClass(ProfileResponse.class);
        userBuy = socialService.getMyProfile(System.getProperty(Account.BUY.getENV())).asClass(ProfileResponse.class);
    }

    @SneakyThrows
    protected void mint() {
        Runtime rt = Runtime.getRuntime();
        printOutput errorReported, outputMessage = null;

        try {
            Process proc = rt.exec(NPM_RUN_MINT);
            errorReported = getStreamWrapper(proc.getErrorStream(), "ERROR");
            outputMessage = getStreamWrapper(proc.getInputStream(), "OUTPUT");
            errorReported.start();
            outputMessage.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(60);
    }

    protected CollectionResponse getTestCollectionData() {
        return socialService.getCollection(TEST_COLLECTION_CONTRACT_ADDRESS, MINT_TOKEN).asClass(CollectionResponse.class);
    }

    protected void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    protected String getTestCollectionRoyalties() {
        if(getTestCollectionData().getRoyalties() == null) {
            return "0";
        } else return getTestCollectionData().getRoyalties().toString();
    }

    public printOutput getStreamWrapper(InputStream is, String type) {
        return new printOutput(is, type);
    }

    private class printOutput extends Thread {
        InputStream is = null;

        printOutput(InputStream is, String type) {
            this.is = is;
        }

        public void run() {
            String s = null;
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                    if (s.contains("Access token MINT DEV") || s.contains("Access token MINT TEST")) {
                        String[] parts = s.split(" ");
                        MINT_TOKEN = parts[4];
                        System.setProperty(Account.MINT.getENV(), MINT_TOKEN);
                    }
                    if (s.contains("Access token BUY DEV") || s.contains("Access token BUY TEST")) {
                        String[] parts = s.split(" ");
                        BUY_TOKEN = parts[4];
                        System.setProperty(Account.BUY.getENV(), BUY_TOKEN);
                    }
                    if (s.contains("Access token ADMIN DEV") || s.contains("Access token ADMIN TEST")) {
                        String[] parts = s.split(" ");
                        ADMIN_TOKEN = parts[4];
                        System.setProperty(Account.ADMIN.getENV(), ADMIN_TOKEN);
                    }
                    if (s.contains("Mint nft name:")) {
                        String[] parts = s.split(" ");
                        MINT_NFT_NAME = parts[3];
                    }
                    if (s.contains("Signature MINT DEV") || s.contains("Signature MINT TEST")) {
                        String[] parts = s.split(" ");
                        MINT_SIGNATURE = parts[3];
                        System.setProperty(Account.MINT.getSignature(), MINT_SIGNATURE);
                    }
                    if (s.contains("Signature BUY DEV") || s.contains("Signature BUY TEST")) {
                        String[] parts = s.split(" ");
                        BUY_SIGNATURE = parts[3];
                        System.setProperty(Account.BUY.getSignature(), BUY_SIGNATURE);
                    }
                    if (s.contains("Signature ADMIN DEV") || s.contains("Signature ADMIN TEST")) {
                        String[] parts = s.split(" ");
                        ADMIN_SIGNATURE = parts[3];
                        System.setProperty(Account.ADMIN.getSignature(), ADMIN_SIGNATURE);
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
