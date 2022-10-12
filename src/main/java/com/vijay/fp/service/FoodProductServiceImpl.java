package com.vijay.fp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.vijay.fp.dao.FoodProductDaoImpl;
import com.vijay.fp.entity.Foodproduct;
import com.vijay.fp.sort.ProductNameComparator;

@Service
public class FoodProductServiceImpl implements foodProductserviceInterface {
	@Autowired
	private FoodProductDaoImpl daoImpl;

	@Override
	public boolean saveData(Foodproduct product) {
		// String Id = new
		// SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		if (product.getProductId() == null) {
			String Id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
			product.setProductId(Id);
		}
		boolean isSaved = daoImpl.saveData(product);

		return isSaved;
	}

	@Override
	public List<Foodproduct> getAllDataOfProduct() {
		List<Foodproduct> list = daoImpl.getAllDataOfProduct();
		return list;
	}

	@Override
	public Foodproduct getFPById(String productId) {
		List<Foodproduct> list = getAllDataOfProduct();
		Foodproduct isFetched = null;

		for (Foodproduct foodproduct : list) {
			if (foodproduct.getProductId().equals(productId)) {
				isFetched = foodproduct;
			}
		}
		return isFetched;
	}

	@Override
	public boolean deleteFPinformationByProductId(String productName) {
		boolean isDeleted = daoImpl.deleteFPinformationByProductId(productName);

		return isDeleted;
	}

	@Override
	public boolean updateProductinformation(Foodproduct product) {
		boolean isUpdated = daoImpl.updateProductinformation(product);
		return isUpdated;
	}

	@Override
	public List<Foodproduct> sortInformations(String sortBy) {
		List<Foodproduct> list = getAllDataOfProduct();
		if (sortBy.equalsIgnoreCase("productName")) {
			Collections.sort(list, new ProductNameComparator());
			// Collections.reverse(list);// if we want in DESC condition.
		} else if (sortBy.equalsIgnoreCase("productId")) {
			Collections.sort(list, new ProductNameComparator());
			// Collections.reverse(list);// if we want in DESC condition.
		}

		return list;
	}

	@Override
	public Foodproduct getMaxPriceOfFoodproduct() {
		List<Foodproduct> list = getAllDataOfProduct();
		Foodproduct foodproduct = null;
		if (!list.isEmpty()) {
			foodproduct = list.stream().max(Comparator.comparingDouble(Foodproduct::getProductPrice)).get();

		}
		return foodproduct;

	}

	@Override
	public int getTotalCountofProduct() {
		List<Foodproduct> list = getAllDataOfProduct();
		int size = 0;
		if (!list.isEmpty()) {
			size = list.size();
		}
		return size;
	}

	@Override
	public double sumOfProductPrice() {
		List<Foodproduct> list = getAllDataOfProduct();
		double sum = 0;
		if (!list.isEmpty()) {
			sum = list.stream().mapToDouble(Foodproduct::getProductPrice).sum();
		}

		return sum;
	}

	@Override
	public int uploadSheet(CommonsMultipartFile file, HttpSession session) {
		String path = session.getServletContext().getRealPath("/");
		String filename = file.getOriginalFilename();
		FileOutputStream fos = null;
		byte[] data = file.getBytes();
		List<Foodproduct> list = null;
		int count = 0;
		try {
			System.out.println(path);
			fos = new FileOutputStream(new File(path + File.separator + filename));
			fos.write(data);
			list = readExcel(path + File.separator + filename);
			count = daoImpl.uploadProductList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public List<Foodproduct> readExcel(String filepath) {
		FileInputStream fis = null;
		List<Foodproduct> list = new ArrayList<>();
		Foodproduct product = null;
		try {
			fis = new FileInputStream(new File(filepath));
			Workbook workbook = new XSSFWorkbook(fis);
			// workbook.getSheet("Sheet1"); if we use String.
			Sheet sheet = workbook.getSheetAt(0);// XSSFSheet.
			Iterator<Row> rows = sheet.rowIterator();// This iterate multiple rows.
//			int cnt = 0;
			while (rows.hasNext()) {

				Row row = rows.next();
				product = new Foodproduct();
				if (product.getProductId() == null) {
					Thread.sleep(1);
					String Id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
//					product.setProductId(Id + cnt++);
					product.setProductId(Id);
				}
				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = cells.next();
//					String data = cell.getStringCellValue();
//					System.out.println(data);
					int column = cell.getColumnIndex();
					switch (column) {
					case 0: {
//						System.out.print(cell.getStringCellValue() + "\t");// "\t"= Space of 4 line.
						product.setProductName(cell.getStringCellValue());
						break;
					}
					case 1: {
//						System.out.print(cell.getNumericCellValue() + "\t");
						product.setProductPrice(cell.getNumericCellValue());
						break;
					}
					case 2: {
//						System.out.print(cell.getNumericCellValue() + "\t");
						product.setProductQnty((int) cell.getNumericCellValue());

						break;
					}
					case 3: {
//						System.out.print(cell.getStringCellValue() + "\t");
						product.setProductRegion(cell.getStringCellValue());

						break;
					}
					}
				}
//				System.out.println();
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
}
