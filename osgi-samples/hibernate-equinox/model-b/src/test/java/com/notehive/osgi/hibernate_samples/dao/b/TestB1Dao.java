package com.notehive.osgi.hibernate_samples.dao.b;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.notehive.osgi.hibernate_samples.dao.b.B1Dao;
import com.notehive.osgi.hibernate_samples.model.b.B1;

import junit.framework.TestCase;

@Transactional
public class TestB1Dao extends AbstractTransactionalDataSourceSpringContextTests {
	
	@Resource
	private B1Dao a1Dao;

	@Override
	protected String[] getConfigLocations() {
		return new String[] {"/META-INF/spring/bundle-context.xml",
				"/test-context.xml"};
	}
	
	/**
	 * Test Create, read, update and delete.
	 * @throws SystemException 
	 */
	public void testCrud() throws SystemException {
		
		B1 saved = new B1();
		saved.setString1("String 1, value 1");
		saved.setString2("String 2, value 1");
		
		a1Dao.save(saved);
		
		// load and test
		B1 loaded = a1Dao.get(saved.getId());

		assertEquals(saved.getId(), loaded.getId());
		assertEquals(saved.getString1(), loaded.getString1());
		assertEquals(saved.getString2(), loaded.getString2());
		
		// update
		saved.setString1("String 1, value 2");
		saved.setString2("String 2, value 2");
		
		a1Dao.save(saved);
		
		// load and test
		loaded = a1Dao.get(saved.getId());

		assertEquals(saved.getId(), loaded.getId());
		assertEquals(saved.getString1(), loaded.getString1());
		assertEquals(saved.getString2(), loaded.getString2());
	}

}
