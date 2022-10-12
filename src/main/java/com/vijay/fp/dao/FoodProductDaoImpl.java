package com.vijay.fp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vijay.fp.entity.Foodproduct;

@Repository
public class FoodProductDaoImpl implements foodProductdaoInterface {
	@Autowired
	private SessionFactory factory;

	@Override
	public boolean saveData(Foodproduct foodproduct) {
		Session session = factory.openSession();
		boolean isSaved = false;
		try {

			Transaction transaction = session.beginTransaction();
			session.save(foodproduct);
			transaction.commit();
			isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isSaved;
	}

	@Override
	public List<Foodproduct> getAllDataOfProduct() {
		Session session = factory.openSession();
		List<Foodproduct> list = null;
		try {

			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Foodproduct.class);
			list = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	@Override
	public String getFPById(String productId) {
		List<Foodproduct> list = getAllDataOfProduct();
		String isFetched = null;
		Session session = factory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			for (Foodproduct foodproduct : list) {
				if (foodproduct.getProductId().equals(productId)) {
					session.get(Foodproduct.class, productId);
					isFetched = productId;
					transaction.commit();

//					isDeleted = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isFetched;

	}

	@Override
	public boolean deleteFPinformationByProductId(String productName) {
		List<Foodproduct> list = getAllDataOfProduct();
		boolean isDeleted = false;
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Foodproduct foodproduct = session.get(Foodproduct.class, productName);
		session.delete(foodproduct);
		transaction.commit();
		return isDeleted = true;
	}

	@Override
	public boolean updateProductinformation(Foodproduct product) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUpdated = false;

		Foodproduct prd = session.get(Foodproduct.class, product.getProductId());
		if (prd != null) {
//			session.evict(prd);
			session.clear();
			session.update(product);
			transaction.commit();
			isUpdated = true;
			System.out.println("Product Updated ");
		} else {
			System.out.println(" Product not found for update ");
		}

		return isUpdated;
	}

	@Override
	public List<Foodproduct> sortInformations(String sortBy) {

		return null;

	}

	@Override
	public int uploadProductList(List<Foodproduct> list) {
		Session session = null;
		Transaction transaction = null;
		int count = 0;
		try {
			for (Foodproduct foodproduct : list) {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.save(foodproduct);
				transaction.commit();
				count = count + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return count;
	}

}
