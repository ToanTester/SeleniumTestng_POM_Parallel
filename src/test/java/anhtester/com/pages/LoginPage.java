package anhtester.com.pages;

import anhtester.com.drivers.DriverManager;
import anhtester.com.keywords.WebUI;
import static anhtester.com.keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {


    private String URL = "https://crm.anhtester.com/admin/authentication";
    private String PAGETEXT = "Login";
    //Lưu object của trang Login
    //Dùng đối tượng By trong Selenium để khai báo object cùng giá trị Locator tương ứng
    By headerPage = By.xpath("//h1");
    By inputEmail = By.xpath("//input[@id='email']");
    By inputPass = By.xpath("//input[@id='password']");
    By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    By messageErrorEmail = By.xpath("//div[@class='text-center alert alert-danger']");


    //Viết các hàm xử lý cho trang Login
    public void verifyheaderPage(){
        Assert.assertEquals(WebUI.getTextElement(headerPage),"Login","Tên headerPage chưa đúng");

    }
    public void verifyErrorMessageDisplay(){
        Assert.assertTrue(WebUI.getElement(messageErrorEmail).isDisplayed(),"Fail. message Không hiển thị");
        Assert.assertEquals(WebUI.getTextElement(messageErrorEmail),"Invalid email or password","Fail. Nội dung message Email chưa đúng");

    }
    public void  enterEmail(String email){

        DriverManager.getDriver().findElement(inputEmail).sendKeys(email);
    }
    public void  enterPass(String pass){
        WebUI.setText(inputPass, pass);

    }
    public void  clickOnLoginButton(){
        WebUI.clickElement(buttonLogin);


    }
    //Liên kết Page bài 19
    public DashboardPage login (String email, String pass){
        WebUI.openURL(URL);
        verifyheaderPage();
        enterEmail(email);
        enterPass(pass);
        clickOnLoginButton();

        return  new DashboardPage();
    }


//    public void login (String email, String pass){
//        driver.get(URL);
//        verifyheaderPage();
//        enterEmail(email);
//        enterPass(pass);
//        clickOnLoginButton();
//    }

    public void loginInvalidEmail (String email, String pass){
       WebUI.openURL(URL);
        verifyheaderPage();
        enterEmail(email);
        enterPass(pass);
        clickOnLoginButton();
        //Kiểm tra message thông báo lỗi khi nhập sai email
        verifyErrorMessageDisplay();
    }

}//Đóng class
