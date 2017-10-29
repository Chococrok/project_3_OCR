package io.ab.consumer;

import java.util.List;

import io.ab.model.Owner;

public interface OwnerDao {
	public List<Owner> findByTopo(int id);
}
