package vc.com.programar.repositorio;

import jakarta.persistence.TypedQuery;
import vc.com.programar.acessoBanco.DAO;
import vc.com.programar.entidade.Eletronico;
import vc.com.programar.entidade.Impresso;
import vc.com.programar.entidade.Livro;

import java.util.List;

public class LivroRepositorio {

    DAO dao;

    public LivroRepositorio() {
        this.dao = new DAO();
    }

    public void salvar(Livro livro){
        try{
            dao.em = dao.emf.createEntityManager();
            dao.em.getTransaction().begin();
            dao.em.merge(livro);
            dao.em.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }
    }

    public void excluir(Livro livro){
        try{
            dao.em = dao.emf.createEntityManager();
            dao.em.getTransaction().begin();
            dao.em.remove(livro);
            dao.em.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }
    }

    public Livro encontrarPorNumero(int numero){
        dao.em = dao.emf.createEntityManager();
        Livro livro = null;

        try{
            dao.em.getTransaction().begin();
            livro = dao.em.find(Livro.class, numero);
            dao.em.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }

        return livro;
    }

//    public void atualizarLivro(Livro livro){
//        try{
//            dao.em = dao.emf.createEntityManager();
//            dao.em.getTransaction().begin();
//
//            Impresso novoLivro = dao.em.find(Impresso.class,livro.getId());
//
//            dao.em.getTransaction().commit();
//
//        } catch (Exception e){
//            System.out.println(e);
//        } finally {
//            dao.em.close();
//        }
//    }

    public List<Livro> listarTodos(){
        dao.em = dao.emf.createEntityManager();
        dao.em.getTransaction().begin();
        TypedQuery consulta = dao.em.createQuery("Select l from Livro l", Livro.class);
        List<Livro> livros = consulta.getResultList();
        dao.em.getTransaction().commit();
        dao.em.close();

        return livros;
    }

    public List<Impresso> listarTodosImpressos(){
        dao.em = dao.emf.createEntityManager();
        dao.em.getTransaction().begin();
        TypedQuery consulta = dao.em.createQuery("Select i from Impresso i inner join Livro l on l.id = i.id", Impresso.class);
        List<Impresso> impressos = consulta.getResultList();
        dao.em.getTransaction().commit();
        dao.em.close();

        return impressos;
    }

    public List<Eletronico> listarTodosEletronicos(){
        dao.em = dao.emf.createEntityManager();
        dao.em.getTransaction().begin();
        TypedQuery consulta = dao.em.createQuery("Select e from Eletronico e inner join Livro l on l.id = e.id", Eletronico.class);
        List<Eletronico> eletronicos = consulta.getResultList();
        dao.em.getTransaction().commit();
        dao.em.close();

        return eletronicos;
    }
}
