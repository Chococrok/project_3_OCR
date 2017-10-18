package io.ab.consumer;

import java.util.List;
import io.ab.model.Longueur;

public interface LongueurDao {
	public List<Longueur> findAllByVoie(int id);
}
