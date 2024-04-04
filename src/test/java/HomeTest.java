import org.example.gui.pages.desktop.HomePage;
import org.example.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends AbstractTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(HomeTest.class);

    @Test
    public void verifyHomePage() {
        String textLogo = R.TEST_DATA.getString("logotext");
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        LOGGER.info(" automation exercise page is opened");
        Assert.assertTrue(homePage.isPageOpened(), "Page is not opened");
        String logoText = homePage.getHeaderMenu().getTextLogo();
        Assert.assertEquals(logoText, textLogo, "Logo text is not the same");
    }

    @Test
    public void verifyHomePage2() {
        String textLogo = R.TEST_DATA.getString("logotext");
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        LOGGER.info(" automation exercise page is opened");
        Assert.assertTrue(homePage.isPageOpened(), "Page is not opened");
        String logoText = homePage.getHeaderMenu().getTextLogo();
        Assert.assertEquals(logoText, textLogo, "Logo text is not the same");
    }
}
