package com.project.others;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.layout.font.FontProvider;
import com.project.model.WorkPDFRevenueM;
import com.project.modelview.InvoiceViewU;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class OutputWorkData {
	private static OutputWorkData instance = null;

	private static XSSFWorkbook workExcel;
	private XSSFSheet sheetData;
	private int startRow = 0;
	private BigDecimal totalSumProfit = BigDecimal.ZERO;
	private SpringTemplateEngine templateEngine;

	private int monthExcel = 0;
	private int yearExcel = 0;

	public static OutputWorkData getInstance() {
		if (instance == null) {
			instance = new OutputWorkData();
		}
		return instance;
	}

	/**
	 * @Excel Output
	 */

	public boolean createRevenueExcel(HttpServletResponse response, int Month, int Year, List<InvoiceViewU> lsData) {
		boolean flagCreate = true;
		try {
			workExcel = new XSSFWorkbook();
			/**
			 * @Create Work Book
			 */
			getMonthAndYearExcel(Month, Year);
			sheetData = workExcel.createSheet(
					"Invoice_" + String.format("%02d", monthExcel) + "_" + String.format("%03d", yearExcel));
			//
			createHeaderColumnData();
			createBodyColumnData(lsData);
			createCellInformationOutput();
			/**
			 * @Mapping Response
			 */
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment;filename=" + "InvoiceRevenue_" + String.format("%02d", monthExcel) + "_"
					+ String.format("%03d", yearExcel) + ".xlsx";
			response.setHeader(headerKey, headerValue);
			//
			ServletOutputStream responseOutput = response.getOutputStream();
			workExcel.write(responseOutput);
			workExcel.close();
			responseOutput.close();
		} catch (Exception e) {
			flagCreate = false;
			//
			System.out.println("ERROR createRevenueExcel");
			System.out.println(e.getMessage());
		}
		return flagCreate;
	}

	private boolean createHeaderColumnData() {
		boolean flagCreate = true;
		try {
			Row rowHeaderCol = sheetData.createRow(4);
			/**
			 * @Row
			 */
			int startCell = 1;
			for (String itemCol : StringValue.headerOutputRevenue()) {
				rowHeaderCol.setHeight((short) 400);
				Cell cellDateTime = rowHeaderCol.createCell(startCell);
				cellDateTime.setCellStyle(getStyleForOutputColumnHeader());
				cellDateTime.setCellValue(itemCol);
				/**
				 * @AutoSize
				 */
				if (startCell != 1) {
					sheetData.autoSizeColumn(startCell);
				} else {
					sheetData.setColumnWidth(startCell, 1000);
				}
				startCell += 1;
			}
			//
		} catch (Exception e) {
			flagCreate = false;
			//
			System.out.println("ERROR createHeaderColumnData");
			System.out.println(e.getMessage());
		}
		return flagCreate;
	}

	private CellStyle getStyleForOutputColumnHeader() {
		XSSFCellStyle returnStyle = workExcel.createCellStyle();
		try {
			/**
			 * @Font
			 */
			Font font = workExcel.createFont();
			font.setFontName("Segoe UI");
			font.setFontHeightInPoints((short) 14);
			font.setColor(HSSFColorPredefined.BLACK.getIndex()); // #CCFFFF 204,255,255)
			returnStyle.setFont(font);
			//
			returnStyle.setFillForegroundColor(
					new XSSFColor(new java.awt.Color(204, 255, 255), new DefaultIndexedColorMap()));
			returnStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			/**
			 * @Border
			 */
			returnStyle.setBorderBottom(BorderStyle.THIN);
			returnStyle.setBorderTop(BorderStyle.THIN);
			returnStyle.setBorderRight(BorderStyle.THIN);
			returnStyle.setBorderLeft(BorderStyle.THIN);
			//
			returnStyle
					.setTopBorderColor(new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));
			returnStyle.setBottomBorderColor(
					new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));
			returnStyle.setRightBorderColor(
					new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));
			returnStyle
					.setLeftBorderColor(new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));

			/**
			 * @Alight
			 */
			returnStyle.setAlignment(HorizontalAlignment.LEFT);
			returnStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		} catch (Exception e) {
			System.out.println("ERROR getStyleForOutputColumnHeader");
			System.out.println(e.getMessage());
		}
		return returnStyle;
	}

	private boolean createBodyColumnData(List<InvoiceViewU> lsData) {
		boolean flagCreate = true;
		totalSumProfit = BigDecimal.ZERO;
		try {
			/**
			 * @Row
			 */
			int columnStart = 1;
			startRow = 5;
			for (InvoiceViewU bodyData : lsData) {
				Row rowBodyCol = sheetData.createRow(startRow);

				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getCodeInvoice(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getUserName(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getAddress(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getUserPhone(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getCreateDate(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getQuantity(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getTotalPrice(), getStyleForOutputBody());
				setDataForBodySheet(rowBodyCol, columnStart++, bodyData.getProfitsAdmin(), getStyleForOutputBody());
				/**
				 * @Value
				 */
				startRow += 1;
				columnStart = 1;
				totalSumProfit = totalSumProfit.add(bodyData.getProfitsAdmin());
			}
			//
		} catch (Exception e) {
			flagCreate = false;
			//
			System.out.println("ERROR createHeaderColumnData");
			System.out.println(e.getMessage());
		}
		return flagCreate;
	}

	private CellStyle getStyleForOutputBody() {
		XSSFCellStyle returnStyle = workExcel.createCellStyle();
		try {
			/**
			 * @Font
			 */
			Font font = workExcel.createFont();
			font.setFontName("Segoe UI");
			font.setFontHeightInPoints((short) 14);
			font.setColor(HSSFColorPredefined.BLACK.getIndex()); // #CCFFFF 204,255,255)
			returnStyle.setFont(font);
			/**
			 * @Border
			 */
			returnStyle.setBorderBottom(BorderStyle.THIN);
			returnStyle.setBorderTop(BorderStyle.THIN);
			returnStyle.setBorderRight(BorderStyle.THIN);
			returnStyle.setBorderLeft(BorderStyle.THIN);
			//
			returnStyle
					.setTopBorderColor(new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));
			returnStyle.setBottomBorderColor(
					new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));
			returnStyle.setRightBorderColor(
					new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));
			returnStyle
					.setLeftBorderColor(new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap()));

			/**
			 * @Alight
			 */
			returnStyle.setAlignment(HorizontalAlignment.LEFT);
			returnStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		} catch (Exception e) {
			System.out.println("ERROR getStyleForOutputBody");
			System.out.println(e.getMessage());
		}
		return returnStyle;
	}

	private void setDataForBodySheet(Row currentRow, int currentColumn, Object dataIn, CellStyle style) {
		sheetData.autoSizeColumn(currentColumn);
		Cell cellBody = currentRow.createCell(currentColumn);
		//
		switch (dataIn.getClass().toString()) {
		case "Integer": {
			cellBody.setCellValue((Integer) dataIn);
			break;
		}
		default:
			cellBody.setCellValue(dataIn.toString());
			break;
		}
		cellBody.setCellStyle(style);
	}

	private boolean createCellInformationOutput() {
		boolean flagCreate = true;
		try {
			Row rowHeader = sheetData.createRow(2);
			/**
			 * @Row
			 */
			rowHeader.setHeight((short) 500);
			//
			Cell cellDateTime = rowHeader.createCell(1);
			cellDateTime.setCellStyle(getStyleForOutputInformationInvoice());
			cellDateTime.setCellValue("Invoice No DateTime: " + String.format("%02d", monthExcel) + "/"
					+ String.format("%03d", yearExcel));
			// s
			Row rowTest = sheetData.createRow(startRow + 3);
			/**
			 * @Row
			 */
			rowTest.setHeight((short) 500);
			//
			Cell celTest = rowTest.createCell(1);
			celTest.setCellStyle(getStyleForOutputInformationInvoice());
			celTest.setCellValue("Total Profits Invoice: " + totalSumProfit);

		} catch (Exception e) {
			flagCreate = false;
			//
			System.out.println("ERROR createCellInformationOutput");
			System.out.println(e.getMessage());
		}
		return flagCreate;
	}

	private CellStyle getStyleForOutputInformationInvoice() {
		CellStyle returnStyle = workExcel.createCellStyle();
		try {
			/**
			 * @Font
			 */
			Font font = workExcel.createFont();
			font.setFontName("Segoe UI");
			font.setBold(true);
			font.setFontHeightInPoints((short) 16);
			font.setUnderline(Font.U_SINGLE);
			font.setColor(HSSFColorPredefined.BLACK.getIndex());
			returnStyle.setFont(font);

			returnStyle.setAlignment(HorizontalAlignment.LEFT);
			returnStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		} catch (Exception e) {
			System.out.println("ERROR getStyleForOutputInformationInvoice");
			System.out.println(e.getMessage());
		}
		return returnStyle;
	}

	private void getMonthAndYearExcel(int Month, int Years) {
		Calendar calend = Calendar.getInstance();
		int currentYear = calend.get(Calendar.YEAR);
		int currentMonth = calend.get(Calendar.MONTH);
		//
		if (Years == 0 && Month == 0) {
			monthExcel = currentMonth;
			yearExcel = currentYear;
		}
		if (Years != 0 && Month != 0) {
			monthExcel = Month;
			yearExcel = Years;
		}
		if (Years == 0 && Month != 0) {
			monthExcel = Month;
			yearExcel = currentYear;
		}
		if (Years != 0 && Month == 0) {
			monthExcel = 0;
			yearExcel = Years;
		}
	}

	/**
	 * @Ouput PDF
	 */
	public byte[] outputRevenueInvoicePDF(int Month, int Years) {
		
		templateEngine = new SpringTemplateEngine();
		/* Setup Thymeleaf Template*/
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(0);
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addTemplateResolver(templateResolver);
		
		/* Setup Source and target I/O streams */
		ByteArrayOutputStream target = new ByteArrayOutputStream();

		ConverterProperties converterProperties = new ConverterProperties();
		converterProperties.setBaseUri("http://localhost:8080");
		/**
		 * @Context
		 */
		Context cThyme = new Context();
		//
		List<WorkPDFRevenueM> ls = ilaProjectCoffeeRepository.getInstance().getInvoiceForOutputPDF(Month,Years);
		cThyme.setVariable("lsWorkData", ls);		
		cThyme.setVariable("sumProfits", ls.get(0).getSumProfitsA());
		String htmlContent = templateEngine.process("Admin/RevenueInvoicePDF.html", cThyme);
	    /* Call Convert Method */
	    HtmlConverter.convertToPdf(htmlContent, target, converterProperties);  

	    /* Extract Output As Bytes */
	    byte[] bytes = target.toByteArray();
	    return bytes;

	}

	
	
	
	
}
