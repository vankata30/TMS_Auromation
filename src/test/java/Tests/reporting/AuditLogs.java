package Tests.reporting;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.reporting.AuditLogsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuditLogs extends BaseTest {

    AuditLogsPage auditLogs;

    @Test
    public void exportToExcel() throws IOException {
        auditLogs= new AuditLogsPage(driver);
        Assert.assertEquals(auditLogs.exportToExcel(),auditLogs.getAuditsNumber());

    }
}
