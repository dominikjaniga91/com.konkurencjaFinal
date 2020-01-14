package model;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import java.util.List;

public class Pomocnik {

    /**
     * A method that choose right option base on clients demands. Create object of specific brand
     * and invoke method getDaraFromHtml.
     *
     * @return a xslxSpreadsheet
     */

    XSSFWorkbook xslxSpreadsheet;

    public XSSFWorkbook startAnalyzeBrand(String providedBrand, List<Document> htmlCodeFromURLs) throws Exception {


        if ("bershka".equals(providedBrand)) {
            Bershka bershka = new Bershka();
            xslxSpreadsheet = bershka.getDataFromHtml(htmlCodeFromURLs, providedBrand,50,62);
        } else if("house".equals(providedBrand)) {
            House house = new House();
            xslxSpreadsheet = house.getDataFromHtml(htmlCodeFromURLs,providedBrand,"es-product-name", "es-product-price", "data-sku");
        }else if("reserved".equals(providedBrand)) {
            Reserved reserved = new Reserved();
            xslxSpreadsheet =  reserved.getDataFromHtml(htmlCodeFromURLs,providedBrand,"es-product-name", "es-product-price", "data-sku");
        }else if ("h&m".equals(providedBrand)) {
            Hm hm = new Hm();
            xslxSpreadsheet = hm.getDataFromHtml(htmlCodeFromURLs,providedBrand,"item-heading", "item-price", "data-articlecode");
        } else if ("pull&bear".equals(providedBrand)) {
            PullBear pullBear = new PullBear();
            xslxSpreadsheet = pullBear.getDataFromHtml(htmlCodeFromURLs, providedBrand,53,61);
        } else if ("terranova".equals(providedBrand)) {
            Terranova terranova = new Terranova();
            xslxSpreadsheet = terranova.getDataFromHtml(htmlCodeFromURLs,providedBrand,"product-item-link", "price-box price-final_price", "data-product-sku");
        } else if ("befree".equals(providedBrand)) {
            Befree befree = new Befree();
            xslxSpreadsheet = befree.getDataFromHtml(htmlCodeFromURLs, providedBrand);
        }else {
            throw new Exception("Nie odnaleziono brandu: " + providedBrand);
        }

        return xslxSpreadsheet;

    }



}
