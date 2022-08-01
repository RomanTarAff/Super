package util;

import api.enums.DatePeriod;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class DateHelper {

    private final DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private final Faker faker = new Faker();

    public String getDatePlus(DatePeriod period) {
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM d, yyyy hh:mm aa");
        Calendar c = Calendar.getInstance();
        switch (period) {
            case DAY_1:
                c.add(Calendar.DATE, 1);
                break;
            case DAYS_3:
                c.add(Calendar.DATE, 3);
                break;
            case DAYS_5:
                c.add(Calendar.DATE, 5);
                break;
            case DAYS_7:
                c.add(Calendar.DATE, 7);
                break;
            case DAYS_10:
                c.add(Calendar.DATE, 10);
                break;
            case MONTHS_6:
                c.add(Calendar.DATE, 180);
                break;
        }
        return currentDate.format(c.getTime());
    }

    @SneakyThrows
    public Instant getInstantFromString(String dateStr) {
        return Instant.parse(dateStr);
    }

    public String getRandomDateFromInterval(String dateAft, String dateBef) {
        try {
            Date dateAfter = formatter.parse(dateAft);
            Date dateBefore = formatter.parse(dateBef);
            return formatter.format(faker.date().between(dateAfter, dateBefore));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
