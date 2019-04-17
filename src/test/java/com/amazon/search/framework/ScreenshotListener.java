package com.amazon.search.framework;

import com.amazon.search.tests.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ScreenshotListener implements ITestListener {
  @Override
  public void onTestStart(ITestResult result) {

  }

  @Override
  public void onTestSuccess(ITestResult result) {

  }

  @Override
  public void onTestFailure(ITestResult result) {
    Object currentClass = result.getInstance();
    WebDriver webDriver = ((BaseTest) currentClass).getWebDriver();
    attachScreenshot(webDriver);
  }

  @Override
  public void onTestSkipped(ITestResult result) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  }

  @Override
  public void onStart(ITestContext context) {

  }

  @Override
  public void onFinish(ITestContext context) {

  }

  @Attachment(value = "Screenshot", type = "image/png")
  public byte[] attachScreenshot(WebDriver driver) {

    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }
}