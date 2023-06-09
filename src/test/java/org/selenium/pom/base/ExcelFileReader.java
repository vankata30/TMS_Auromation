package org.selenium.pom.base;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class ExcelFileReader extends BaseTest{
     Wait<WebDriver> wait;
     WebDriver driver;



    public static void main(String args[]) throws IOException {
        //Create an object of File class to open xlsx file
        File file = new File("\"C:\\Users\\IvanPetrov\\Downloads\\Application audit logs export.xlsx\"");

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        //creating a Sheet object
        HSSFSheet sheet = wb.getSheet("STUDENT_DATA");

        //get all rows in the sheet
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();



        //iterate over all the row to print the data present in each cell.
        for (int i = 0; i <= rowCount; i++) {

            //get cell count in a row
            int cellcount = sheet.getRow(i).getLastCellNum();

            //iterate over each cell to print its value
            System.out.println("Row" + i + " data is :");

            for (int j = 0; j < cellcount; j++) {
                System.out.print(sheet.getRow(i).getCell(j).getStringCellValue() + ",");
            }
            System.out.println();
        }

    }
    public ExpectedCondition<Boolean> fileIsPresent() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                File f = new File("C:\\Users\\IvanPetrov\\Downloads\\Application audit logs export.xlsx");
                return f.exists();
            }
            @Override
            public String toString() {
                return String.format("file to be present within the time specified");
            }
        };
    }
    public int getRowCount(WebDriver driver) throws IOException {
//        driver = new ChromeDriver();
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        File file = new File("C:\\Users\\IvanPetrov\\Downloads\\Application audit logs export.xlsx");


        //Create an object of FileInputStream class to read excel file
        wait.until(fileIsPresent());
        FileInputStream inputStream = new FileInputStream(file);

        //creating workbook instance that refers to .xls file
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        //creating a Sheet object
        XSSFSheet sheet = wb.getSheet("audits");

        //get all rows in the sheet
        return sheet.getLastRowNum() - sheet.getFirstRowNum();
    }
}
