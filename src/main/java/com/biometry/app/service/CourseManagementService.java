package com.biometry.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biometry.app.entity.Course;
import com.biometry.app.entity.CourseMaster;
import com.biometry.app.repository.CourseMasterRepository;
import com.biometry.app.repository.CourseRepository;

@Service
public class CourseManagementService {

	@Autowired
	CourseMasterRepository courseMasterRepository;
	
	@Autowired
	CourseRepository courseRepository;
	List<Integer> courseIds;
	public List<CourseMaster> getCourseMastersByTeacherId(int id) {
		Optional<List<CourseMaster>> ocm= courseMasterRepository.findByTeacherId(id);
		if(ocm.isPresent()) {
			List<CourseMaster> courseMasters = ocm.get();
			System.out.println(ocm.get());
			courseIds = new ArrayList<Integer>();
			for(CourseMaster cm : courseMasters) {
				courseIds.add(cm.getCourse().getCourseId());
			}
			return courseMasters;
		}
		return null;
	}
	public List<Course> getCoursesByIds() {
		if(courseIds.isEmpty())
			return null;
		System.out.println(courseRepository.findByCourseIdIn(courseIds));
		return courseRepository.findByCourseIdIn(courseIds);
	}
}
