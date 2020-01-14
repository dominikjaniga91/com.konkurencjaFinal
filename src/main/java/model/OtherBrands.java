package model;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Superclass for subclasses House, Reserved, Terranova, Hm
 *
 */

class OtherBrands {

    private Map<Integer, Object[]> data = new HashMap<>();

    /**
     * Method receive as parameters list of html codes and class names that should be look up.
     * Use Jsoup method to catch class from html code and save as Elements type.
     * Next, founded class values are save in <code>List<String></code>
     * In the nested for loop data are saving to <code>Map<Integer, Object[]></code> and sending as argument to writeToExcel method.
     *
     * @param htmlCodeFromURLs a List od parsed html from provided URLs via txt file
     * @param brand  provided by client in name of txt upload file
     * @param productClassName a name of html class that contains name of each product
     * @param priceClassName a name of html class that contains price of each product
     * @param modeloColorClassName a name of html class that contains specific ID of each product
     * @return a XSSFWorkbook spreadsheet
     */

    protected XSSFWorkbook getDataFromHtml(List<Document> htmlCodeFromURLs, String brand, String productClassName, String priceClassName, String modeloColorClassName) {

        XSSFWorkbook xslxSpreadsheet = Excel.createNewXlsxFile();

        for (Document htmlCodeFromURL : htmlCodeFromURLs) {

            Elements productName = htmlCodeFromURL.getElementsByClass(productClassName);
            Elements price = htmlCodeFromURL.getElementsByClass(priceClassName);
            Elements modeloColor = htmlCodeFromURL.getElementsByAttribute(modeloColorClassName);
            Elements countryData = htmlCodeFromURL.getElementsByAttribute("lang");

            List<String> nameTable = productName.eachText();
            List<String> priceTable = price.eachText();
            List<String> modeloColorTable = modeloColor.eachAttr(modeloColorClassName);
            String country = countryData.attr("lang");

            for (int j = 0; j < priceTable.size(); j++) {

                data.put(j, new Object[]{brand, country, modeloColorTable.get(j), nameTable.get(j), priceTable.get(j)});
            }

            Excel.writeToExcel(data, xslxSpreadsheet);
            data.clear();
        }

        return xslxSpreadsheet;
    }

}

