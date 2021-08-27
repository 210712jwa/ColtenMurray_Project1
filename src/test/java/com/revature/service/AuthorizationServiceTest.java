package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.Account;
import com.revature.model.Client;

public class AuthorizationServiceTest {
	
	@Test
	public void test_getAllAccountsFromClient_positive() throws BadParameterException, DatabaseException, ClientNotFoundException, SQLException {
		
		when(clientDao.getClientById(eq(10))).thenReturn(new Client(10, "jim", 100));
		
		List<Account> mockAccounts = new ArrayList<>();
		mockAccounts.add(new Account(1, "account1", 30, 10));
		mockAccounts.add(new Account(2, "account2", 33, 10));
		
		when(accountDao.getAllAccountsFromClient(eq(10))).thenReturn(mockAccounts);
		
		List<Account> actualAccounts = accountService.getAllAccountsFromClient("10", null, null);
		
		assertEquals(mockAccounts, actualAccounts);
	}

}
