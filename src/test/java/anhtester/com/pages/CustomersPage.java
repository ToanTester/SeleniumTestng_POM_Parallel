package anhtester.com.pages;

import anhtester.com.common.BaseTest;
import anhtester.com.drivers.DriverManager;
import anhtester.com.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CustomersPage{
    //Các thuộc tính xác thực
    private String URL = "https://crm.anhtester.com/admin/clients";
    private  String PAGETEXT = "Customers Summary";
    //Object thuộc tính Customers Page
    String headerPageCustomer = "(//div[@class='panel-body']//h4)[1]";
    private By buttonNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By buttonImportCustomers = By.xpath("//a[normalize-space()='Import Customers']");
    private By buttonContacts = By.xpath("//a[normalize-space()='Contacts']");
    private By inputSeach = By.xpath("//div[@id='DataTables_Table_0_filter']//input");
    private By chucNangSearch = By.xpath("//input[@aria-controls='DataTables_Table_0']");
    private By tdCompany = By.xpath("//tbody/tr[1]/td[3]/a");
    //private By chucNangSearch = By.xpath("");



    //Các hàm xử lý
    public void verifyCustomer(){
        Assert.assertEquals(WebUI.getCurrentUrl(),URL ,"URL chưa đúng với trang Darsboard");
        System.out.println("URL của Customer: " + WebUI.getCurrentUrl());
        Assert.assertTrue(WebUI.checkElementExist(headerPageCustomer),"Element không tồn tại");
        Assert.assertEquals(WebUI.getTextElement(By.xpath(headerPageCustomer)),PAGETEXT,"Tên headerPage của Customer Chưa đúng");

    }

    public AddCustomerPage clickbuttonNewCustomer(){
        WebUI.waitForElementVisible(buttonNewCustomer);
        WebUI.clickElement(buttonNewCustomer);
        return new AddCustomerPage();
    }

    public void seachDataCustomer(String company){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(chucNangSearch);
        //WebUI.setText(chucNangSearch,company, Keys.);
        DriverManager.getDriver().findElement(chucNangSearch).sendKeys(company, Keys.ENTER);
        WebUI.sleep(2);

    }

    public CustomerDetailPage clickTdCompany(){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(tdCompany);
        WebUI.clickElement(tdCompany);
        return new CustomerDetailPage();
    }

}
