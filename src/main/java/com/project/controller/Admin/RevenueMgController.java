package com.project.controller.Admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.DocumentException;
import com.project.model.WorkPDFRevenueM;
import com.project.modelview.InvoiceSupplierView;
import com.project.modelview.InvoiceViewU;
import com.project.others.ExcelGenerator;
import com.project.others.PdfGenerator;
import com.project.repository.ilaProjectCoffeeRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Admin")
public class RevenueMgController {

	@GetMapping("/revenue-management")
	public String dashboard(Model model) {
		Calendar currentCal = Calendar.getInstance();
		int currentYear = currentCal.get(Calendar.YEAR);
		//int currentMonth = currentCal.get(Calendar.MONTH);
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		var currentMonth = current.getMonth() + 1;

		model.addAttribute("lsRevenue",
				ilaProjectCoffeeRepository.getInstance().getInvoiceForOutputExcel(currentMonth, currentYear));
		model.addAttribute("chartData",
				ilaProjectCoffeeRepository.getInstance().getChartsRevenueAdmin(currentMonth, currentYear));
		return "Admin/revenueMg";
	}

	@GetMapping("/revenue-management/chart")
	public String chart(Model lstData, int month, int year) {

		var obj = ilaProjectCoffeeRepository.getInstance().getChartsRevenueAdmin(month, year);
		lstData.addAttribute("lstChartChange", obj);

		return "Admin/Share/partialChart";
	}

	@GetMapping("/revenue-management/option")
	public String option(Model model, int month, int year) {
		model.addAttribute("lsRevenue", ilaProjectCoffeeRepository.getInstance().getInvoiceForOutputExcel(month, year));
		return "Admin/Share/partialRevenueMg";
	}

	@GetMapping("/revenue-management/printExcel")
	public void printExcel(Model lstData, int month, int year, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=revenueExcel" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		var monthCurr = current.getMonth() + 1;
		var yearCurr = current.getYear() + 1900;
		if (year == 0 && month != 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance()
					.CallRevenueByMonthYear(month, yearCurr);
			ExcelGenerator generator = new ExcelGenerator(listOfRevenues);
			generator.generateExcelFile(response);
		} else if (year != 0 && month == 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance().CallRevenueByYear(year);
			ExcelGenerator generator = new ExcelGenerator(listOfRevenues);
			generator.generateExcelFile(response);
		} else if (year != 0 && month != 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance()
					.CallRevenueByMonthYear(month, year);
			ExcelGenerator generator = new ExcelGenerator(listOfRevenues);
			generator.generateExcelFile(response);
		} else if (year == 0 && month == 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance()
					.CallRevenueByMonthYear(monthCurr, yearCurr);
			ExcelGenerator generator = new ExcelGenerator(listOfRevenues);
			generator.generateExcelFile(response);
		}
	}

	@GetMapping("/revenue-management/printPdf")
	public void printPdf(Model lstData, int month, int year, HttpServletResponse response)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=revenuePdf" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		var monthCurr = current.getMonth() + 1;
		var yearCurr = current.getYear() + 1900;
		if (year == 0 && month != 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance()
					.CallRevenueByMonthYear(month, yearCurr);
			PdfGenerator generator = new PdfGenerator();
			generator.generate(listOfRevenues, "Revenue of " + month + "-" + yearCurr, response);
		} else if (year != 0 && month == 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance().CallRevenueByYear(year);
			PdfGenerator generator = new PdfGenerator();
			generator.generate(listOfRevenues, "Revenue of " + year, response);
		} else if (year != 0 && month != 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance()
					.CallRevenueByMonthYear(month, year);
			PdfGenerator generator = new PdfGenerator();
			generator.generate(listOfRevenues, "Revenue of " + month + "-" + year, response);
		} else if (year == 0 && month == 0) {
			List<InvoiceSupplierView> listOfRevenues = ilaProjectCoffeeRepository.getInstance()
					.CallRevenueByMonthYear(monthCurr, yearCurr);
			PdfGenerator generator = new PdfGenerator();
			generator.generate(listOfRevenues, "Revenue of " + monthCurr + "-" + yearCurr, response);
		}

	}

	/**
	 * @Autor Vinh
	 */
	@GetMapping("/revenue-management/checkDataBeforeOutput")
	@ResponseBody
	public boolean checkDataBeforeOutput(int Month, int Years) {
		List<InvoiceViewU> lsData = ilaProjectCoffeeRepository.getInstance().getInvoiceForOutputExcel(Month, Years);
		if (lsData.size() == 0) {
			return false;
		}
		return true;
	}

	@GetMapping("/revenue-management/checkDataBeforeOutputPDF")
	@ResponseBody
	public boolean checkDataBeforeOutputPDF(int Month, int Years) {
		List<WorkPDFRevenueM> ls = ilaProjectCoffeeRepository.getInstance().getInvoiceForOutputPDF(Month, Years);
		if (ls.size() == 0) {
			return false;
		}
		return true;
	}

	@GetMapping("/revenue-management/outputDataWorkExcel")
	public void outputWorkDataExcep(HttpServletResponse response, int Month, int Years) {
		List<InvoiceViewU> lsData = ilaProjectCoffeeRepository.getInstance().getInvoiceForOutputExcel(Month, Years);
		ilaProjectCoffeeRepository.getInstance().createRevenueExcel(response, Month, Years, lsData);
	}

	@GetMapping("/revenue-management/outputDataWorkPDF")
	public ResponseEntity<?> outputDataWorkPDF(int Month, int Years) {
		byte[] bytePDF = ilaProjectCoffeeRepository.getInstance().outputRevenueInvoicePDF(Month, Years);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytePDF);
	}

}
