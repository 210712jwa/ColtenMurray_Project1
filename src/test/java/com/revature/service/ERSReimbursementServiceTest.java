package com.revature.service;

import com.revature.dao.reimbursementDAO;
import com.revature.dto.AddReimbursementDTO;
import com.revature.exception.BadParameterException;
import com.revature.model.ERSReimbursement;
import com.revature.model.ERSReimbursementStatus;
import com.revature.model.ERSReimbursementType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ERSReimbursementServiceTest {
	private reimbursementDAO reimbursementDao;
	private ERSReimbursementService reimbursementService;
	
	@Before
	public void setUp() {
		this.reimbursementDao = mock(reimbursementDAO.class);
		this.reimbursementService = mock(ERSReimbursementService.class);
		this.reimbursementService = new ERSReimbursementService(reimbursementDao);
		
	}
	
	@Test
	public void test_getAllReimbursementFromUserId_positive() throws BadParameterException  {
	
		List<ERSReimbursement> mockReimbursements = new ArrayList<>();
		mockReimbursements.add(new ERSReimbursement(1, 5, "mock 1"));
		mockReimbursements.add(new ERSReimbursement(1, 15, "mock 2"));
		mockReimbursements.add(new ERSReimbursement(1, 25, "mock 3"));
		
		when(reimbursementDao.getAllReimbursementsFromUserId(eq(1))).thenReturn(mockReimbursements);
		List<ERSReimbursement> actualReimbursements = reimbursementService.getAllReimbursementsFromUserId("1");
		
		assertEquals(mockReimbursements, actualReimbursements);
	}
	
	@Test
	public void test_getAllReimbursementFromUserId_invalidFormatUserId() throws BadParameterException{
		try {
			reimbursementService.getAllReimbursementsFromUserId("abc");
			
			fail();
		} catch(BadParameterException e) {
			assertEquals("abc was passed in by the user as the id, but it is not an int", e.getMessage());
		}
	}
	
	@Test
	public void test_addAccountToUser_positive() throws BadParameterException  {
	
		AddReimbursementDTO dto = new AddReimbursementDTO();
		dto.setReimbAmount(5);
		dto.setReimbDescription("mock 1");
		dto.setType(1);
		when(reimbursementDao.addAccountToUser(eq(1),eq(dto))).thenReturn(new ERSReimbursement(1, 5, "mock 1"));
		List<ERSReimbursement> actualReimbursements = reimbursementService.getAllReimbursementsFromUserId("1");
		ERSReimbursement actual = reimbursementService.addAccountToUser("1", dto);
		ERSReimbursement expected = new ERSReimbursement(1, 5, "mock 1");
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_addAccountToUser_invalidFormatUserId() throws BadParameterException{
		try {
			AddReimbursementDTO dto = new AddReimbursementDTO();
			dto.setReimbAmount(5);
			dto.setReimbDescription("mock 1");
			dto.setType(1);
			reimbursementService.addAccountToUser("abc",dto);
			
			fail();
		} catch(BadParameterException e) {
			assertEquals("abc was passed in by the user as the id, but it is not an int", e.getMessage());
		}
	}
	
	@Test
	public void test_getAllReimbursementsFromStatus_positive() throws BadParameterException  {
	
		List<ERSReimbursement> mockReimbursements = new ArrayList<>();
		mockReimbursements.add(new ERSReimbursement(1, 5, "mock 1"));
		mockReimbursements.add(new ERSReimbursement(1, 15, "mock 2"));
		mockReimbursements.add(new ERSReimbursement(1, 25, "mock 3"));
		
		when(reimbursementDao.getAllReimbursementsFromStatus(eq(1))).thenReturn(mockReimbursements);
		List<ERSReimbursement> actualReimbursements = reimbursementService.getAllReimbursementsFromStatus("1");
		
		assertEquals(mockReimbursements, actualReimbursements);
	}
	
	@Test
	public void test_getAllReimbursementsFromStatus_invalidFormatUserId() throws BadParameterException{
		try {
			reimbursementService.getAllReimbursementsFromStatus("abc");
			
			fail();
		} catch(BadParameterException e) {
			assertEquals("abc was passed in by the user as the id, but it is not an int", e.getMessage());
		}
	}
	
	

}
