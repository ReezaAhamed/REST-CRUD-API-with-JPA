package com.reeza.springboot.crudapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reeza.springboot.crudapp.entity.Worker;

@Repository
public class WorkerDAOJPAmpl implements WorkerDAO {
	
	//define field for entity manager 
	@Autowired
	private EntityManager entityManager; //Spring automatically creates for us, so just inject

	@Override
	public List<Worker> findAll() {
		
		// create a query
		Query query = entityManager.createQuery("from Worker");
		
		// execute the query and get the result list
		List<Worker> workers = query.getResultList();
		
		//return the list
		return workers;
	}

	@Override
	public Worker findById(int wId) {
		
		// get worker
		Worker worker = entityManager.find(Worker.class, wId);
		
		//return worker
		return worker;
	}

	@Override
	public void save(Worker worker) {
		
		//save or update worker
		Worker dbWorker = entityManager.merge(worker); 
		
		//update with id from db... so we can get generated id for save/insert
		worker.setId(dbWorker.getId());

	}

	@Override
	public void deleteById(int wId) {
		
		//delete object with primary key
		Query query = entityManager.createQuery("delete from Worker where id=:workerId"); //Capital 'W'orker, becase, that's the enity in Java and ofc =:
		
		query.setParameter("workerId", wId);
		
		query.executeUpdate();

	}

}
