package io.ab.climbing.consumer;

import java.util.List;

import io.ab.climbing.model.Owner;

public interface OwnerDao {
	public Owner findOneById(int id);
	public Owner findOneByEmail(String email);
	public List<Owner> findByTopo(int id);
	public boolean exists(String email);
	public boolean checkPassword(String email, String password);
	public void updateEmail(String email, int id);
	public void updatePhone(String phone, int id);
	public void addTopo(int topoId, int ownerId);
	public int createOne(String firstName, String lastName, String email, String password);
	void deleteOne(int id);
	public void updateOne(Owner owner);
	public void deleteTopos(Integer id);
}
