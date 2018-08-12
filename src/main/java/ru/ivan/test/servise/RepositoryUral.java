package ru.ivan.test.servise;



import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ivan.test.Model.UralModel;


@Repository
public interface RepositoryUral  extends CrudRepository<UralModel, Long>{
	
	List<UralModel> findAllByOrderByDateAscCorrespondentINNAsc();
	List<UralModel> findAllByOrderByCorrespondentINNAsc();
}
