package com.vijay.fp.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.vijay.fp.entity.Foodproduct;
import com.vijay.fp.exception.ProductNotFoundException;
import com.vijay.fp.service.FoodProductServiceImpl;

@RestController
public class FpController {
	@Autowired
	private FoodProductServiceImpl fProductServiceImpl;

	@PostMapping("/saveData")
	public ResponseEntity<Boolean> saveData(@Valid @RequestBody Foodproduct product) {
		boolean isSaved = fProductServiceImpl.saveData(product);

		if (isSaved) {
			return new ResponseEntity<Boolean>(isSaved, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<Boolean>(isSaved, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAllDataOfProduct")
	public ResponseEntity<List<Foodproduct>> getAllDataOfProduct() {

		List<Foodproduct> allDataOfProduct = fProductServiceImpl.getAllDataOfProduct();

		if (allDataOfProduct.isEmpty()) {
			return new ResponseEntity<List<Foodproduct>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Foodproduct>>(allDataOfProduct, HttpStatus.OK);
		}

	}

	@GetMapping("/getFPById/{productId}")
	public ResponseEntity<Foodproduct> getFPById(@Valid @PathVariable String productId) {
		Foodproduct product = fProductServiceImpl.getFPById(productId);
		if (product != null) {
			return new ResponseEntity<Foodproduct>(product, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product Not Found For Id :" + productId);
		}

	}

	@DeleteMapping("/deleteFPinformationByProductId")
	public ResponseEntity<Boolean> deleteFPinformationByteamName(@RequestParam String productName) {
		boolean isDeleted = fProductServiceImpl.deleteFPinformationByProductId(productName);

		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);

		} else {
			throw new ProductNotFoundException("Product Not Found" + productName);
		}

	}

	@PutMapping("/updateProductinformation")
	public boolean updateProductinformation(@Valid @RequestBody Foodproduct product) {

		boolean isUpdated = fProductServiceImpl.updateProductinformation(product);

		return isUpdated;

	}

	@GetMapping("/sortInformations")
	public List<Foodproduct> sortInformations(@Valid @RequestParam String sortBy) {
		List<Foodproduct> list = fProductServiceImpl.sortInformations(sortBy);

		return list;

	}

	@GetMapping("/getMaxPriceOfFoodproduct")
	public ResponseEntity<Foodproduct> getMaxPriceOfFoodproduct() {

		Foodproduct foodProduct = fProductServiceImpl.getMaxPriceOfFoodproduct();
		if (foodProduct != null) {
			return new ResponseEntity<Foodproduct>(foodProduct, HttpStatus.OK);

		} else {
			return new ResponseEntity<Foodproduct>(foodProduct, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getTotalCountofProduct")
	public ResponseEntity<Integer> getTotalCountofProduct() {
		int count = fProductServiceImpl.getTotalCountofProduct();
		if (count > 0) {
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(count, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/sumOfProductPrice")
	public ResponseEntity<Double> sumOfProductPrice() {
		double sumOfPrice = fProductServiceImpl.sumOfProductPrice();
		if (sumOfPrice > 0) {
			return new ResponseEntity<Double>(sumOfPrice, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(sumOfPrice, HttpStatus.NO_CONTENT);

		}

	}

	@PostMapping(value = "/uploadSheet")
	public ResponseEntity<Integer> uploadSheet(@RequestParam CommonsMultipartFile file, HttpSession session) {
//		String path = session.getServletContext().getRealPath("WEB-INF/upload");
//		System.out.println(path);
		int count = fProductServiceImpl.uploadSheet(file, session);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}

}
