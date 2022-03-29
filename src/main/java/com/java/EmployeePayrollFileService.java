package com.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class EmployeePayrollFileService {
	public static String EMP_DATA_FILE_PATH = "emp-payroll-data.txt";

	public void writeEmpPayrollData(List<EmployeePayroll> employeePayrollList) {

		final StringBuffer empBuffer = new StringBuffer();
		employeePayrollList.forEach(new Consumer<EmployeePayroll>() {
			@Override
			public void accept(EmployeePayroll emp) {
				empBuffer.append(emp + "\n");
			}
		});
		try {
			Files.write(Paths.get(EMP_DATA_FILE_PATH), empBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public long countLines() {
		long count = 0;
		try {
			count = Files.lines(Paths.get(EMP_DATA_FILE_PATH)).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void readEmpPayrollData() {
		System.out.println("Reading Emp Payroll data from the file");
		try {
			Files.lines(Paths.get(EMP_DATA_FILE_PATH)).map(new Function<String, Object>() {
				@Override
				public Object apply(String line) {
					return line.trim();
				}
			}).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<EmployeePayroll> getEmpPayrollData() {
		System.out.println("Storing Emp Payroll data from the file to obj");
		final List<EmployeePayroll> empPayrollDataFromFile = new ArrayList<>();
		try {
			Files.lines(Paths.get(EMP_DATA_FILE_PATH)).map(new Function<String, Object>() {
				@Override
				public Object apply(String line) {
					int id = Integer.parseInt(line.split(",")[0].split("=")[1]);
					String name = line.split(",")[1].split("=")[1];
					double salary = Double.parseDouble(line.split(",")[2].split("=")[1]);
					EmployeePayroll emp = new EmployeePayroll(id, name, salary);
					return emp;
				}
			}).forEach(new Consumer<Object>() {
				@Override
				public void accept(Object emp) {
					empPayrollDataFromFile.add((EmployeePayroll) emp);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empPayrollDataFromFile;
	}
}
