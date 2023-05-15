package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Table {

    private WebElement tableElement;

    public Table(WebElement tableElement) {
        this.tableElement = tableElement;

    }

    public List<WebElement> getRows() {
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    public List<WebElement> getHeadings() {
        WebElement headingsRow = tableElement.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headingsColumns = headingsRow.findElements(By.xpath(".//a"));
        return headingsColumns;
    }

    public List<List<WebElement>> getRowsWithColumns() {
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }

        return rowsWithColumns;
    }


    public List<Map<String, WebElement>> getRowsWithColumnsByHeadings() {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeadings = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeadings;
        List<WebElement> headingsColumns = getHeadings();

        for (List<WebElement> row : rowsWithColumns) {
            rowByHeadings = new HashMap<String, WebElement>();
            for (int i = 0; i < headingsColumns.size(); i++) {
                String heading = headingsColumns.get(i).getText();
                WebElement cell = row.get(i);
                rowByHeadings.put(heading, cell);
            }
            rowsWithColumnsByHeadings.add(rowByHeadings);
        }
        return rowsWithColumnsByHeadings;
    }



    public String getValueFromCell(int rowNumber, String columnName) {
        List<Map<String, WebElement>> rowsWithColumnsByHeadings = getRowsWithColumnsByHeadings();
        return rowsWithColumnsByHeadings.get(rowNumber -1).get(columnName).getText();
    }
}
