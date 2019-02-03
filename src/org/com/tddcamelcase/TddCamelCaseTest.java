package org.com.tddcamelcase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TddCamelCaseTest {
	
	@BeforeAll
	public static void init() {
	}
	
	@Test
	public void stringIsValid() {
		assertTrue(TddCamelCase.isValid("nome"));
		assertTrue(TddCamelCase.isValid("nomeComposto"));
		assertTrue(TddCamelCase.isValid("recupera10Primeiros"));
		assertTrue(TddCamelCase.isValid("numeroCPFContribuinte"));
		assertTrue(TddCamelCase.isValid("CamelCase"));
	}
	
	@Test
	public void stringNotValid() {
		assertFalse(TddCamelCase.isValid("10Primeiros"));
		assertFalse(TddCamelCase.isValid("nome#Composto"));
		assertFalse(TddCamelCase.isValid("nome Composto"));
	}

	@Test
	public void invalidConstruct() {
		assertThrows(CamelCaseFormatException.class, () -> {
			TddCamelCase.converterCamelCase("nome#Composto").toString();
		});
	}
	
	@Test
	public void checkConsistenceOfPositions() {
		int nextWordStart = TddCamelCase.getEndPosition("nomeComposto");
		assertEquals(nextWordStart, 4);
		assertEquals("nomeComposto".substring(nextWordStart, 12), "Composto");
		
		nextWordStart = TddCamelCase.getEndPosition("recupera10Primeiros");
		assertEquals(nextWordStart, 8);
		assertEquals("recupera10Primeiros".substring(nextWordStart, 10), "10");
	}
	
	@Test
	public void wordListFromCamelCaseString() {
		List<String> list = new ArrayList<String>();
		TddCamelCase.getList(list, "nomeComposto");
		
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), "nome");
		assertEquals(list.get(1), "composto");
	}
	
	@Test
	public void wordListFromCamelCaseStringLevel3() {
		List<String> list = new ArrayList<String>();
		TddCamelCase.getList(list, "recupera10Primeiros");
		
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "recupera");
		assertEquals(list.get(1), "10");
		assertEquals(list.get(2), "primeiros");
	}
	
	@Test
	public void wordListFromCamelCaseStringLevel4() {
		List<String> list = new ArrayList<String>();
		TddCamelCase.getList(list, "recupera10Primeiros20Teste");
		
		assertEquals(list.size(), 5);
		assertEquals(list.get(0), "recupera");
		assertEquals(list.get(1), "10");
		assertEquals(list.get(2), "primeiros");
		assertEquals(list.get(3), "20");
		assertEquals(list.get(4), "teste");
	}
	
	
	@Test
	public void converterCamelCaseTest() {
		List<String> list =
				TddCamelCase.converterCamelCase("NomeComposto");
		
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), "nome");
		assertEquals(list.get(1), "composto");
	}
}
