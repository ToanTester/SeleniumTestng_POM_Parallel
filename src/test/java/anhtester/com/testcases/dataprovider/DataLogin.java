package anhtester.com.testcases.dataprovider;

import org.testng.annotations.DataProvider;

public class DataLogin {

    @DataProvider(name = "dataProviderLoginCRM")
    public Object[][] dataLoginCRM() {

        return new Object[][]{
                {"admin@example", "123456"},
                {"admin2@example", "123456"},
                {"admin3@example", "123456"}
        };
    }

    @DataProvider(name = "dataProviderLoginCMS", parallel = true)
    public Object[][] dataLoginCMS() {

        return new Object[][]{
                {"admin1@example", "123456", 123},
                {"admin2@example", "123456", 1234},
                {"admin3@example", "123456", 12345}
        };
    }
}
