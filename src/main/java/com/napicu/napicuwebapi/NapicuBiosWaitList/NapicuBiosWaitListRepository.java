package com.napicu.napicuwebapi.NapicuBiosWaitList;

import com.napicu.napicuwebapi.Database.NapicuBiosWaitListDatabase;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface NapicuBiosWaitListRepository extends CrudRepository<NapicuBiosWaitListDatabase, Integer> {
}
