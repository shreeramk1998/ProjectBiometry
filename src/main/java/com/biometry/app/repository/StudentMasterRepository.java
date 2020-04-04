package com.biometry.app.repository;

import com.biometry.app.entity.StudentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentMasterRepository extends JpaRepository<StudentMaster,Integer> {
	@Query("Select sm FROM StudentMaster sm WHERE roll= :roll AND sm.div.divId = (Select d.divId FROM Division d where divName = :div)")
	StudentMaster findByRollAndDiv(@Param("roll")Integer roll,@Param("div")String div);
}
