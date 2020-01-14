package model;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Superclass for subclasses PullBear, Bershka
 */

class Inditex {

    private Map<Integer, Object[]> data = new HashMap<>();

    /**
     * Method receive as parameters list of html codes and brands name.
     * Use Jsoup method to catch attributes from html code and save as Elements type.
     * Next, founded attributes values are save in <code>List<String></code>
     * In the nested for loop data are saving to <code>Map<Integer, Object[]></code> and sending as argument to writeToExcel method.
     * Html code from Inditex brands doesn't provide class with product ID so ID has to be cut from href links.
     *
     * @param htmlCodeFromURLs a List od parsed html from provided URLs via txt file
     * @param brand provided by client in name of txt upload file
     * @param cutFrom an integer number that determine a beginning of range in the href link tha should contains product ID
     * @param cutTo an end of range
     * @return a XSSFWorkbook spreadsheet
     */

    protected XSSFWorkbook getDataFromHtml(List<Document> htmlCodeFromURLs, String brand, int cutFrom, int cutTo) {

    XSSFWorkbook xslxSpreadsheet = Excel.createNewXlsxFile();

        for (Document htmlCodeFromURL : htmlCodeFromURLs) {

            Elements namesAndPrices = htmlCodeFromURL.getElementsByAttribute("href");
            Elements modeloColor = htmlCodeFromURL.getElementsByAttribute("alt");
            Elements countryData = htmlCodeFromURL.getElementsByAttributeValue("http-equiv", "content-language");

            List<String> namesAndPricesTable = namesAndPrices.eachText();
            List<String> modeloColorTable = modeloColor.eachAttr("src");
            String country = countryData.attr("content");


            for (int j = 0; j < modeloColorTable.size(); j++) {
                if (!("null".equals(modeloColorTable.get(j)))) {
                    if (modeloColorTable.get(j).contains("photo")) {

                        data.put(j, new Object[]{ brand, country, modeloColorTable.get(j).substring(cutFrom, cutTo), namesAndPricesTable.get(j)});

                    }
                }
            }

            Excel.writeToExcel(data, xslxSpreadsheet);
            data.clear();
        }

    return xslxSpreadsheet;
    }
}
