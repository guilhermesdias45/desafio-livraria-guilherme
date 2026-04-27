package vc.com.programar.repositorio;

import jakarta.persistence.TypedQuery;
import vc.com.programar.acessoBanco.DAO;
import vc.com.programar.entidade.Venda;

import java.util.List;

public class VendaRepositorio {

    DAO dao;

    public VendaRepositorio() {
        this.dao = new DAO();
    }

    public void salvar(Venda venda){
        try{
            dao.em = dao.emf.createEntityManager();
            dao.em.getTransaction().begin();
            dao.em.merge(venda);
            dao.em.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }
    }

    public void excluir(Venda venda){
        try{
            dao.em = dao.emf.createEntityManager();
            dao.em.getTransaction().begin();
            dao.em.remove(venda);
            dao.em.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }
    }

    public Venda encontrarPorNumero(int numero){
        dao.em = dao.emf.createEntityManager();
        Venda venda = null;

        try{
            dao.em.getTransaction().begin();
            venda = dao.em.find(Venda.class, numero);
            dao.em.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }

        return venda;
    }

    public List<Venda> listarTodos(){
        dao.em = dao.emf.createEntityManager();
        dao.em.getTransaction().begin();
        TypedQuery consulta = dao.em.createQuery("Select vendas from Venda vendas", Venda.class);
        List<Venda> venda = consulta.getResultList();
        dao.em.getTransaction().commit();
        dao.em.close();

        return venda;
    }
}
