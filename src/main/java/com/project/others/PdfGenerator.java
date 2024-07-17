package com.project.others;

import java.io.IOException;
import java.util.List;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.*;
import com.project.modelview.InvoiceSupplierView;

import jakarta.servlet.http.HttpServletResponse;

public class PdfGenerator {
	public void generate(List<InvoiceSupplierView> revenueList, String contentParagraph, HttpServletResponse response)
			throws DocumentException, IOException {
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		// Opening the created document to change it
		document.open();
		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		// Creating paragraph
		Paragraph paragraph1 = new Paragraph(contentParagraph, fontTiltle);
		// Aligning the paragraph in the document
		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		// Adding the created paragraph in the document
		document.add(paragraph1);
		// Creating a table of the 4 columns
		PdfPTable table = new PdfPTable(7);
		// Setting width of the table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 3, 4, 3,3,3,3 });
		table.setSpacingBefore(5);
		// Create Table Cells for the table header
		PdfPCell cell = new PdfPCell();
		// Setting the background color and padding of the table cell
		cell.setBackgroundColor(CMYKColor.GRAY);
		cell.setPadding(5);
		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);
		// Adding headings in the created table cell or header
		// Adding Cell to table
		cell.setPhrase(new Phrase("ID Invoice", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Use Voucher iLA", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Total Invoice", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("iLA Pay", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Commissions from orders", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Total Revenue", font));
		table.addCell(cell);
		// Iterating the list of students
		for (com.project.modelview.InvoiceSupplierView revenue : revenueList) {
			// Adding student id
			table.addCell(String.valueOf(revenue.getIdInvoice()));
			// Adding student name
			table.addCell(String.valueOf(revenue.getDateInvoice()));
			// Adding student email
			table.addCell("Order enough " + revenue.getCondition() + "$ to get " + revenue.getDiscount() + "$ off");
			table.addCell(String.valueOf(revenue.getTotalInvoice()));
			table.addCell(String.valueOf(revenue.getiLAPay()));
			table.addCell(String.valueOf(revenue.getAmountReceived()));
			table.addCell(String.valueOf(revenue.getTotalA()));
		}
		// Adding the created table to the document
		document.add(table);
		// Closing the document
		document.close();
	}
}
