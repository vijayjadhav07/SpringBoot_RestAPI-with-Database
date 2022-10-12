package com.vijay.fp.sort;

import java.util.Comparator;

import com.vijay.fp.entity.Foodproduct;

public class ProductNameComparator implements Comparator<Foodproduct> {

	@Override
	public int compare(Foodproduct fp1, Foodproduct fp2) {

		return fp1.getProductName().compareTo(fp2.getProductName());
	}

}
