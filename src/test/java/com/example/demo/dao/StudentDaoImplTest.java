package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentDaoImplTest {

	@Autowired
	private StudentDao studentDao;
	
	@Test
	public void getById() {
		Student s = studentDao.getById(3);
		assertNotNull(s);
		assertEquals("Judy", s.getName());
		assertEquals(100, s.getScore());
		assertTrue(s.isGraduate());
		assertNotNull(s.getCreateDate());
	}
	
	@Test
	@Transactional
	public void deleteById() {
		studentDao.deleteById(2);
		
		Student s = studentDao.getById(2);
		assertNull(s);
	}
	
	@Test
	@Transactional
	public void insert() {
		Student student = new Student();
		student.setName("Lisa");
		student.setScore(90.5);
		student.setGraduate(true);
		Integer id = studentDao.insert(student);
		
		Student result = studentDao.getById(id);
		assertNotNull(result);
		assertEquals("Lisa", result.getName());
		assertEquals(90.5, result.getScore());
		assertTrue(result.isGraduate());
		assertNotNull(result.getCreateDate());
	}
	
	@Test
	@Transactional
	public void update() {
		Student s = studentDao.getById(3);
		s.setName("John");
		studentDao.update(s);
		
		Student result = studentDao.getById(3);
		assertNotNull(result);
		assertEquals("John", result.getName());
	}
}
