package com.biometry.app.repository;

import com.biometry.app.entity.AttendanceMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceMasterRepository extends JpaRepository<AttendanceMaster,Integer> {
	@Query("From AttendanceMaster am where am.courseMaster.cmID = :cmid AND am.studentMaster.div.divId = :divid")
	List<AttendanceMaster> findAttendanceListByCourseMasterAndDivision(@Param("cmid")int cmid,@Param("divid") int divid);
}
