package io.ab.consumer;

import java.util.List;

import io.ab.model.Owner;

public interface OwnerDao {
	public Owner findOneById(int id);
	public Owner findOneByEmail(String email);
	public List<Owner> findByTopo(int id);
	public boolean exists(String email);
	public boolean checkPassword(String email, String password);
}