package com.experis.springlamiapizzeriacrud.repository;

import com.experis.springlamiapizzeriacrud.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
}
