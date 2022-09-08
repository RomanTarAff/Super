package api.social;

import api.BaseApiTests;
import api.enums.Account;
import api.enums.Period;
import api.model.response.social.CollectionOwnersCount;
import api.util.conditions.Conditions;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;
import static org.testng.Assert.assertTrue;

@Epic("Get collection owners count")
public class GetCollectionOwnersCountTests extends BaseApiTests {

    @Test(description = "Get collection owners count")
    public void getCollectionOwnersCount() {

        CollectionOwnersCount ownersCount = socialService.getCollectionOwnersCount(getTestCollectionData().getContractAddress(), System.getProperty(Account.MINT.getENV()))
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(CollectionOwnersCount.class);

        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_HOUR.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_DAY.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_WEEK.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_MONTH.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_QUARTER.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_YEAR.getValue())));
    }

    @Test(description = "Get collection owners count unauthorized")
    public void getCollectionOwnersCountUnauthorized() {

        CollectionOwnersCount ownersCount = socialService.getCollectionOwnersCount(getTestCollectionData().getContractAddress(), null)
                .shouldHave(Conditions.statusCode(HTTP_OK))
                .asClass(CollectionOwnersCount.class);

        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_HOUR.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_DAY.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_WEEK.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_MONTH.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_QUARTER.getValue())));
        assertTrue(ownersCount.getPeriods().stream().anyMatch(period -> period.getName().equals(Period.ONE_YEAR.getValue())));
    }
}
