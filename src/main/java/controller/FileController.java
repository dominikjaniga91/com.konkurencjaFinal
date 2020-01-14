package controller;

import model.Pomocnik;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

import java.util.List;

/**
 * A Java servlet that handles file upload from client.
 *
 */

@WebServlet("/FileController")
public class FileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private Pomocnik pomocnik = new Pomocnik();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            uploadFile(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String theMessage = e.getMessage();
            accessDeniedMessage(request, response, theMessage);

        }
    }

    private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setFileSizeMax(MAX_FILE_SIZE);

            System.out.println(upload.getFileItemFactory());

            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {

                if (item.getContentType().equals("text/plain")) {

                    String brandName = item.getName().substring(0, item.getName().length() - 4).toLowerCase();

                    List<Document> htmlCodeFromURLs = readingFile(item);

                    XSSFWorkbook xslxSpreadsheet = pomocnik.startAnalyzeBrand(brandName, htmlCodeFromURLs);

                    downloadExcelFile(response, xslxSpreadsheet, brandName);

                } else {
                    String theMessage = "Brak pliku lub niepoprawne rozszerzenie";
                    accessDeniedMessage(request, response, theMessage);
                }


            }
        }
    }

    private void downloadExcelFile(HttpServletResponse response, XSSFWorkbook xslxSpreadsheet, String brandName) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + brandName + ".xlsx");

        OutputStream outStream = response.getOutputStream();
        xslxSpreadsheet.write(outStream);

        outStream.flush();
        outStream.close();

    }

    /**
     * Method that sends back message to client if operations goes wrong
     *
     */

    protected static void  accessDeniedMessage(HttpServletRequest request, HttpServletResponse response, String theMessage) throws ServletException, IOException {

        request.setAttribute("information", theMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Method that handles reading txt file upload by client and connect to each URL address using Jsoup
     *
     * @return A List<Document> containing the parse html from URLs
     */

    private List<Document> readingFile(FileItem item)  throws IOException {

        List<Document> documentsArray = new ArrayList<>();
        InputStream stream = item.getInputStream();
        BufferedReader readFile = new BufferedReader(new InputStreamReader(stream));
        String html;

        while ((html = readFile.readLine()) != null) {

            System.out.println(html);
            Document documentHtml = Jsoup.connect(html).get();
            documentsArray.add(documentHtml);
        }

        readFile.close();
        stream.close();
        return documentsArray;
    }

}
