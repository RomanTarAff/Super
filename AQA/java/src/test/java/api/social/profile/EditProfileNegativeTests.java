package api.social.profile;

import api.BaseApiTests;
import api.enums.Account;
import api.model.request.social.EditProfileRequest;
import api.model.response.CodeMessageResponse;
import api.util.conditions.Conditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EditProfileNegativeTests extends BaseApiTests {

    @Test(testName = "Edit profile invalid username", dataProvider = "usernames")
    public void editProfileInvalidUsername(String username, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .username(username).build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(testName = "Edit profile invalid email", dataProvider = "emails")
    public void editProfileInvalidEmail(String email, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .email(email).build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), error);
    }

    @Test(testName = "Edit profile invalid description")
    public void editProfileInvalidDescription() {
        EditProfileRequest request = EditProfileRequest.builder()
                .description(faker.lorem().characters(1005)).build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertEquals(codeMessageResponse.getPayload().getErrors().get(0), "data/data/description must NOT have more than 1000 characters");
    }

    @Test(testName = "Edit profile invalid website url", dataProvider = "urls")
    public void editProfileInvalidWebsiteUrl(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .websiteUrl(url).build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(testName = "Edit profile invalid banner url", dataProvider = "urls")
    public void editProfileInvalidBannerUrl(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .bannerUrl(url).build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @Test(testName = "Edit profile invalid avatar url", dataProvider = "urls")
    public void editProfileInvalidAvatarUrl(String url, String error) {
        EditProfileRequest request = EditProfileRequest.builder()
                .avatarUrl(url).build();

        CodeMessageResponse codeMessageResponse = socialService.editProfile(request, System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(422))
                .asClass(CodeMessageResponse.class);

        assertEquals(codeMessageResponse.getCode(), "ValidationError");
        assertEquals(codeMessageResponse.getMessage(), "Data validation error");
        assertTrue(codeMessageResponse.getPayload().getErrors().get(0).contains(error));
    }

    @DataProvider(name = "usernames")
    public Object[][] getUsernames() {
        return new Object[][]
                {{"невалидный текст", "data/data/username must match pattern"},
                        {"!#$@", "data/data/username must match pattern"},
                        {faker.name().firstName() + ' ' + faker.name().lastName(), "data/data/username must match pattern"},
                        {faker.lorem().characters(105), "data/data/username must NOT have more than 100 characters"},
                };
    }

    @DataProvider(name = "emails")
    public Object[][] getEmails() {
        return new Object[][]
                {{"testemail.com", "data/data/email must match format \"email\""},
                        {"testemail@com", "data/data/email must match format \"email\""},
                        {faker.lorem().characters(105), "data/data/email must match format \"email\""},
                };
    }

    @DataProvider(name = "urls")
    public Object[][] getUrls() {
        return new Object[][]
                {{"http://ggf", "must match pattern"},
                        {"gfgf", "must match pattern"},
                };
    }
}
