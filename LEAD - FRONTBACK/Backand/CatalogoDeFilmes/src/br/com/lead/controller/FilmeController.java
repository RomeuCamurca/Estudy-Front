package br.com.lead.controller;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.lead.modelo.Filme;
import br.com.lead.util.JPAUtil;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class FilmeController {
	
	@RequestMapping(value = "/persistir-filme", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Filme persistirFilme(@RequestBody Filme filme) {
	
	    EntityManager entityManager = JPAUtil.getEntityManager();
	
	    entityManager.getTransaction().begin();
	    entityManager.persist(filme.getAutor());
	    entityManager.persist(filme);
	    entityManager.getTransaction().commit();
	    entityManager.close();       
	    
	    return filme;
	}
	
	@RequestMapping(value = "/consultar-filme", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Filme consultarFilme(@RequestParam Integer id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
	
	    Filme filme = entityManager.find(Filme.class, id);
	    
	    return filme;
	}
}
