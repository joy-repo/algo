package com.pdf.split;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PDFtest {

    public static void main(String[] args) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(
                "/Users/indranil.das/Downloads/Sammelmappe1.pdf");

        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy = null;// = parser.processContent(1, new SimpleTextExtractionStrategy());
        //System.out.println(strategy.getResultantText());

        Map<String, Integer> pageMap = new HashMap<>();

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());

            String data = strategy.getResultantText();

            String pageNum0 = data.split("\n")[0];
            String pageNum1 = data.split("\n")[1];
            String pageNum = pageNum0.length() > 3 ? pageNum1 : pageNum0;
            //  System.out.print(strategy.getResultantText().split("\n")[0] +"----"+strategy.getResultantText().split("\n")[1] );
            System.out.println(pageNum);
            System.out.println();
            pageMap.put(pageNum, i);

        }



        List<String> sorted = pageMap.keySet().stream().sorted((s1,s2)->customCompare(s1,s2)).collect(Collectors.toList());

        List<Integer> pageList = new ArrayList<>();

        for (String s : sorted)
            pageList.add(pageMap.get(s));

        Document doc = new Document();

        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("res.pdf"));
        doc.open();
        PdfContentByte cb = writer.getDirectContent();

        for (int n : pageList) {
            doc.newPage();
            PdfImportedPage page = writer.getImportedPage(reader, n);
            cb.addTemplate(page, 0, 0);
        }
        doc.close();
        reader.close();


    }

    private static int customCompare(String s1, String s2) {
        try {
            String s1F1 = s1.trim().charAt(0) + "";
            String s2F1 = s2.trim().charAt(0) + "";

            if (s1F1.compareTo(s2F1) != 0) return s1F1.compareTo(s2F1);

            Integer s1R = Integer.parseInt(s1.trim().substring(1));
            Integer s2R = Integer.parseInt(s2.trim().substring(1));
            System.out.println(s1 +"---"+s2+"----"+s1R.compareTo(s2R));

            return s1R.compareTo(s2R);
        } catch (Exception e){
            return 0;
        }


    }


}
