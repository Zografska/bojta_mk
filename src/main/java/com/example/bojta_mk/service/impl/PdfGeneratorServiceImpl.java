package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.service.PdfGeneratorService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    public List<String> parseData(OrderItem orderItem){
        return orderItem.getParsedData();
    }

    public void addRows(PdfPTable table, Order order) {
        for(int i=0; i<order.getShoppingCart().getProductList().size(); i++) {
            List<String> parsed = parseData(order.getShoppingCart().getProductList().get(i));

            for (String element : parsed) table.addCell(element);
        }
    }
    public void addTableHeader(PdfPTable table) {
        Stream.of("ID", "name", "shape", "category", "dimension", "quantity")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    @Override
    public void init(Order order) throws FileNotFoundException, DocumentException {

        String customerName = order.getShoppingCart().getUser().getName();
        String customerSurname = order.getShoppingCart().getUser().getSurname();
        String customerNumber = order.getShoppingCart().getUser().getPhone();


        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(customerName + "Order" + order.getId() + ".pdf"));

        document.open();

        Font font_title = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, BaseColor.BLACK);
        Chunk chunk1 = new Chunk("      Order from " + customerName + " " + customerSurname, font_title);
        document.add(chunk1);

        document.add(new Paragraph("\n"));

        Font font_random_row = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        Chunk chunk3 = new Chunk("  Telephone number: " + customerNumber, font_random_row);
        document.add(chunk3);

        document.add(new Paragraph("\n"));


        Chunk chunk2 = new Chunk("  The order consists of the following products: ", font_random_row);
        document.add(chunk2);

        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(6);
        addTableHeader(table);
        addRows(table, order);
        //addCustomRows(table);

        document.add(table);

        document.add(new Paragraph("\n"));

        document.close();
    }
}
