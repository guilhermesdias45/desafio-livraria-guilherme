package vc.com.programar.repositorio;

import jakarta.persistence.TypedQuery;
import vc.com.programar.acessoBanco.DAO;
import vc.com.programar.entidade.Livro;

import java.util.List;

public class LivroRepositorio {

    DAO dao;

    public LivroRepositorio(DAO dao) {
        this.dao = dao;
    }

    public void salvar(Livro livro){
        try{
            dao.em.getTransaction().begin();
            dao.em.merge(livro);
        } catch (Exception e){
            dao.em.getTransaction().rollback();
            System.out.println(e);
        } finally {
            dao.em.getTransaction().commit();
        }
    }

    public void excluir(Livro livro){
        try{
            dao.em.getTransaction().begin();
            dao.em.remove(livro);
        } catch (Exception e){
            dao.em.getTransaction().rollback();
            System.out.println(e);
        } finally {
            dao.em.getTransaction().commit();
        }
    }

    public Livro encontrarPorNumero(int numero){
        dao.em.getTransaction().begin();
        Livro livro = dao.em.find(Livro.class, numero);
        dao.em.getTransaction().commit();
        return livro;
    }

    public List<Livro> listarTodos(){
        dao.em.getTransaction().begin();
        TypedQuery consulta = dao.em.createQuery("Select livros from Livro livros", Livro.class);
        List<Livro> livros = consulta.getResultList();
        dao.em.getTransaction().commit();

        return livros;
    }
}
