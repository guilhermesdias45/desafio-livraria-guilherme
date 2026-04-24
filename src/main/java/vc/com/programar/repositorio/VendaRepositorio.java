package vc.com.programar.repositorio;

import jakarta.persistence.TypedQuery;
import vc.com.programar.acessoBanco.DAO;
import vc.com.programar.entidade.Venda;

import java.util.List;

public class VendaRepositorio {

    DAO dao;

    public VendaRepositorio(DAO dao) {
        this.dao = dao;
    }

    public void salvar(Venda venda){
        try{
            dao.em.getTransaction().begin();
            dao.em.merge(venda);
        } catch (Exception e){
            dao.em.getTransaction().rollback();
            System.out.println(e);
        } finally {
            dao.em.getTransaction().commit();
        }
    }

    public void excluir(Venda venda){
        try{
            dao.em.getTransaction().begin();
            dao.em.remove(venda);
        } catch (Exception e){
            dao.em.getTransaction().rollback();
            System.out.println(e);
        } finally {
            dao.em.getTransaction().commit();
        }
    }

    public Venda encontrarPorNumero(int numero){
        dao.em.getTransaction().begin();
        Venda venda = dao.em.find(Venda.class, numero);
        dao.em.getTransaction().commit();
        return venda;
    }

    public List<Venda> listarTodos(){
        dao.em.getTransaction().begin();
        TypedQuery consulta = dao.em.createQuery("Select vendas from Venda vendas", Venda.class);
        List<Venda> venda = consulta.getResultList();
        dao.em.getTransaction().commit();

        return venda;
    }
}
