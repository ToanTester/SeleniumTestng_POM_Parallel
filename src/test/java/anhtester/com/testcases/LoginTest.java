package anhtester.com.testcases;

import anhtester.com.common.BaseTest;
import anhtester.com.drivers.DriverManager;
import anhtester.com.pages.DashboardPage;
import anhtester.com.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginpage;
    DashboardPage dashboardPage;

    @Test
    public void loginTestSuccess() {

        //Khởi tạo đối tượng trang LoginPage
        //Truyền driver từ BaseTest
        loginpage = new LoginPage();

        //Gọi hàm Login từ LoginPage để dùng
       loginpage.login("admin@example.com", "123456");
    }

    @Test
    public void loginTestInvalidEmail() {

        loginpage = new LoginPage();
        loginpage.loginInvalidEmail("admin@example.com123", "123456");


    }


}//Đóng class
