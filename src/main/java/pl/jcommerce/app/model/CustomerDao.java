package pl.jcommerce.app.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDao {

	@PersistenceContext
	public EntityManager manager;

	@Transactional
	public void save(Customer customer) {
		manager.persist(customer);
	}

	public Customer findById(long id) {
		TypedQuery<Customer> findByFirstNameQuery = manager.
				createQuery("SELECT c FROM Customer c where c.id is :id",Customer.class);
		findByFirstNameQuery.setParameter("id", id);
		return findByFirstNameQuery.getSingleResult();
	}
	
	//@NamedQuery - zdefiniowana w klasie Customer	
	public List<Customer> findByFirstName(String name) {
		return manager.
				createNamedQuery("firstName", Customer.class).
				setParameter("firstName", name).
				getResultList();
	}
	// Query odnosi się do nazw pól zdefiniowanych w klasie Javy, a nie w
	// bazie danych@@@@@@@@@@@@@@@@@@
	public List<Customer> findByLastName(String name) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
		
		Root<Customer> root = query.from(Customer.class);
		query.select(root).where(cb.equal(root.get("name").get("lastName"), name));
		return manager.createQuery(query).getResultList();
	}

	
	@Transactional
	public void delete(Customer entity) {
			manager.remove(entity);
	}
	
	@Transactional
	public void deleteAll() {
		List<Customer> list = findAll();
		
		for(Customer cst : list) {
			manager.remove(cst);
		}
		
	}
	
	public String printAll() {
		Query query = manager.createQuery("SELECT c FROM Customer c");
		return query.getResultList().toString();
	}
	public List<Customer> findAll() {
		Query query = manager.createQuery("SELECT c FROM Customer c");
		return query.getResultList();
	}
	
	

}
