package com.project.others;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.modelview.InvoiceSupplierView;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {
	private List<InvoiceSupplierView> revenueList;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelGenerator(List<InvoiceSupplierView> revenueList) {
		this.revenueList = revenueList;
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		sheet = workbook.createSheet("Revenue");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "ID Invoice", style);
		createCell(row, 1, "Date", style);
		createCell(row, 2, "Use Voucher iLA", style);
		createCell(row, 3, "Total Invoice", style);
		createCell(row, 4, "iLA Pay", style);
		createCell(row, 5, "Commissions from orders", style);
		createCell(row, 6, "Total Revenue", style);
	}

	private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (valueOfCell instanceof Integer) {
			cell.setCellValue((Integer) valueOfCell);
		} else if (valueOfCell instanceof Long) {
			cell.setCellValue((Long) valueOfCell);
		} else if (valueOfCell instanceof String) {
			cell.setCellValue((String) valueOfCell);
		} else {
			cell.setCellValue((Boolean) valueOfCell);
		}
		cell.setCellStyle(style);
	}

	private void write() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for (InvoiceSupplierView record : revenueList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, record.getIdInvoice(), style);
			createCell(row, columnCount++, String.valueOf(record.getDateInvoice()), style);
			createCell(row, columnCount++, "Order enough " + record.getCondition() + "$ to get " + record.getDiscount() + "$ off", style);
			createCell(row, columnCount++, String.valueOf(record.getTotalInvoice()), style);
			createCell(row, columnCount++, String.valueOf(record.getiLAPay()), style);
			createCell(row, columnCount++, String.valueOf(record.getAmountReceived()), style);
			createCell(row, columnCount++, String.valueOf(record.getTotalA()), style);
		}
	}

	public void generateExcelFile(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	/**
	 * @Vinh
	 */
}
