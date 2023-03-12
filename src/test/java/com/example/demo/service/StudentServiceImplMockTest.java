package com.example.demo.service;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceImplMockTest {
	
	@Autowired
	private StudentService studentService;
	
	@MockBean
	private StudentDao studentDao;
	
	@Test
	public void getById() {
		Student mockStudent = new Student();
		mockStudent.setId(100);
		mockStudent.setName("mock");
		
		// 只有當 studentDao.getById(3) 參數是3時，才會返回mockStudent，不然預設是返回 null
		Mockito.when(studentDao.getById(3)).thenReturn(mockStudent); 
		
		// 當條件為任何參數時，都會返回 mockStudent
//		Mockito.when(studentDao.getById(Mockito.any())).thenReturn(mockStudent); 
		
		Student s = studentService.getById(3);
		assertNotNull(s);
		assertEquals(100, s.getId());
		assertEquals("mock", s.getName());
	}
}
