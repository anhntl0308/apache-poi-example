package com.example.apachepoiexample.service;

import com.example.apachepoiexample.model.Style;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

    private final ObjectMapper objectMapper;

    public void readFile(MultipartFile file) {
        try (XWPFDocument doc = new XWPFDocument(
                file.getInputStream())) {

            Iterator<IBodyElement> docElementsIterator = doc.getBodyElementsIterator();

            while (docElementsIterator.hasNext()) {
                IBodyElement docElement = docElementsIterator.next();
                if ("PARAGRAPH".equalsIgnoreCase(docElement.getElementType().name())) {
                    List<XWPFParagraph> list = doc.getParagraphs();
                    for (XWPFParagraph paragraph : list) {
                        System.out.print(paragraph.getText());
                        List<XWPFRun> runs = paragraph.getRuns();
                        if (!runs.isEmpty()) {
                            Style style = getStyle(runs.get(0), doc);;
                            style.setType(getStringOrEmpty(paragraph.getStyle()));
                            System.out.print(" " + objectMapper.writeValueAsString(style));
                        }
                        System.out.println();
                    }
                } else if ("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
                    List<XWPFTable> xwpfTableList = docElement.getBody().getTables();
                    for (XWPFTable xwpfTable : xwpfTableList) {
                        System.out.println("Total Rows : " + xwpfTable.getNumberOfRows());
                        for (int i = 0; i < xwpfTable.getRows().size(); i++) {
                            for (int j = 0; j < xwpfTable.getRow(i).getTableCells().size(); j++) {
                                String text = getStringOrNull(xwpfTable.getRow(i).getCell(j).getText());
                                System.out.print(text + " | ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Style getStyle(XWPFRun run, XWPFDocument doc){
        Style style = new Style();
        style.setSize(run.getFontSize() == -1 ? doc.getStyles().getDefaultRunStyle().getFontSize() : run.getFontSize());
        style.setBold(run.isBold());
        style.setItalic(run.isItalic());
        String color = run.getColor() == null ? "default" : getStringOrEmpty(run.getColor());
        style.setColor(color);
        return style;
    }

    private String getStringOrNull(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        return text;
    }

    private String getStringOrEmpty(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        return text;
    }
}
