package com.vijay.fp.service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.vijay.fp.entity.Foodproduct;

public interface foodProductserviceInterface {
	public boolean saveData(Foodproduct product);

	public List<Foodproduct> getAllDataOfProduct();

	public Foodproduct getFPById(String productId);

	public boolean deleteFPinformationByProductId(String productName);

	public boolean updateProductinformation(Foodproduct product);

	public List<Foodproduct> sortInformations(String sortBy);

	public Foodproduct getMaxPriceOfFoodproduct();

	public int getTotalCountofProduct();

	public double sumOfProductPrice();

	public int uploadSheet(CommonsMultipartFile file, HttpSession session);

}
