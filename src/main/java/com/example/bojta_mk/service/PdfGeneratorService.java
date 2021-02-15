package com.example.bojta_mk.service;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.OrderItem;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileNotFoundException;
import java.util.List;

public interface PdfGeneratorService {
    void init(Order order) throws FileNotFoundException, DocumentException;
}
