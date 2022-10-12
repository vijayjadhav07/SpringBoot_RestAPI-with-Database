package com.vijay.fp.dao;

import java.util.List;

import com.vijay.fp.entity.Foodproduct;

public interface foodProductdaoInterface {

	public boolean saveData(Foodproduct product);

	public List<Foodproduct> getAllDataOfProduct();

	public String getFPById(String productId);

	public boolean deleteFPinformationByProductId(String productName);

	public boolean updateProductinformation(Foodproduct product);

	public List<Foodproduct> sortInformations(String sortBy);

	public int uploadProductList(List<Foodproduct> list);

}
