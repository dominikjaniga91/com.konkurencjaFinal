package model;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Befree{

    private Map<Integer, Object[]> data = new HashMap<>();
    /**
     * Method receive as parameters list of html codes and brands name.
     * Use Jsoup method to catch class from html code and save as Elements type.
     * Next, founded class values are save in <code>List<String></code>
     * In the nested for loop data are saving to <code>Map<Integer, Object[]></code> and sending as argument to writeToExcel method.
     * Html code from beFree doesn't provide class with product ID so ID has to be cut from href links.
     * cutFrom is an integer number that determine a beginning of range in the href link tha should contains product ID
     * cutTo an end of range.
     *
     * @param htmlCodeFromURLs a List od parsed html from provided URLs via txt file
     * @param brand provided by client in name of txt upload file
*
     * @return a XSSFWorkbook spreadsheet
     */


    protected XSSFWorkbook getDataFromHtml(List<Document> htmlCodeFromURLs, String brand) {

        XSSFWorkbook xslxSpreadsheet = Excel.createNewXlsxFile();

        for (Document htmlCodeFromURL : htmlCodeFromURLs) {

            Elements productName = htmlCodeFromURL.getElementsByClass("Item__Name-cev3xd-2 feAiUU");
            Elements price = htmlCodeFromURL.getElementsByClass("Item__Price-cev3xd-3 liLswN");
            Elements modeloColor = htmlCodeFromURL.getElementsByAttribute("itemprop");
            Elements countryData = htmlCodeFromURL.getElementsByAttribute("lang");

            List<String> nameTable = productName.eachText();
            List<String> priceTable = price.eachText();
            List<String> modeloColorTable = modeloColor.eachAttr("href");
            List<String> country = countryData.eachAttr("lang");

            for (int j = 0; j < nameTable.size(); j++) {

                data.put(j, new Object[]{brand, country, modeloColorTable.get(j).substring(19, 28), nameTable.get(j), priceTable.get(j)});

            }

            Excel.writeToExcel(data, xslxSpreadsheet);
            data.clear();

        }
        return xslxSpreadsheet;
    }

}
