package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BPI;

public interface BPIRepository extends CrudRepository<BPI, Integer>{

	List<BPI> findByRefID(Integer refID);
}
