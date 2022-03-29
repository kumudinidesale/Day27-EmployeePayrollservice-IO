package com.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.java.EmployeePayrollService.IOStream;

public class EmployeePayrollServiceTest {
	@Test
	public void writeEmpDataToFile() {
		EmployeePayroll[] empsData = { new EmployeePayroll(1, "Rushi", 100000),
				new EmployeePayroll(2, "Akshay", 200000), new EmployeePayroll(4, "Kartik", 400000),
				new EmployeePayroll(3, "Kumud", 300000), };

		EmployeePayrollService empService = new EmployeePayrollService(empsData);
		empService.writeEmpPayrollData(IOStream.FILE_IO);
		long count = empService.countEntries();
		System.out.println("Number of entries : " + count);
		assertEquals(4, count);
		// empService.readEmpPayrollData(IOStream.FILE_IO);
	}

	@Test
	public void getEmpPayrollDataFromFile() {
		EmployeePayrollFileService fileService = new EmployeePayrollFileService();
		System.out.println(fileService.getEmpPayrollData());
		assertEquals(4, fileService.getEmpPayrollData().size());
	}
}
