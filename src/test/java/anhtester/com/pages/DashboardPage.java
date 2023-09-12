package anhtester.com.pages;

import anhtester.com.keywords.WebUI;
import static anhtester.com.keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {

    //Data trong nội bộ trang
    private String URL ="https://crm.anhtester.com/admin/";
    private String PAGETEXT = " Dashboard Options ";

    //Các Object
    By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
    String buttonDashboarh = "//div[@class='screen-options-btn']";

    By widgetQuickStatistics = By.xpath("//div[@id='widget-top_stats']");
    By checkboxQuickStatistics = By.xpath("//label[normalize-space()='Quick Statistics']");


    //Hàm xử lý
    public  void verifyDashboard(){
        //Kiểm tra URL chứa phần thuộc trang
        Assert.assertEquals(WebUI.getCurrentUrl(),URL,"URL chưa đúng với trang Darsboard");
        System.out.println("URL của DashBoarh: " + WebUI.getCurrentUrl());
        Assert.assertTrue(WebUI.checkElementExist(buttonDashboarh),"Dashboard không tồn tại");
    }
    public CustomersPage openCustomer(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuCustomer);
        return  new CustomersPage();
    }
    public void checkboxQuickStatistics(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath(buttonDashboarh));
        //driver.findElement(By.xpath(buttonDashboarh)).click();
        WebUI.waitForElementVisible(checkboxQuickStatistics);
        WebUI.clickElement(checkboxQuickStatistics);

    }
    public void verifyFilterQuickStatistics(){
        //Kiểm tra hiển thi mặc định: checked
        Assert.assertTrue(WebUI.verifyElementVisible(widgetQuickStatistics),"Widget QuickStatistics Không hiển thị mặc định checked ");
        //Kiểm tra khi Click vào: unchecked
        checkboxQuickStatistics();
        //Kiểm tra Widget trên đã bị ẩn (not visible)
        Assert.assertTrue(WebUI.verifyElementNOTVisible(widgetQuickStatistics),"Widget QuickStatistics hiện tại chưa ẩn ");


    }
}
