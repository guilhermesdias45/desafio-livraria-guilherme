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
            venda = dao.em.find(Venda.class, numero);
        } catch (Exception e){
            System.out.println(e);
        } finally {
            dao.em.close();
        }

        return venda;
    }

    public List<Venda> listarTodos(){
        dao.em = dao.emf.createEntityManager();
        try {
            return dao.em.createQuery(
                    "select distinct v from Venda v join fetch v.livros order by v.numero",
                    Venda.class
            ).getResultList();
        } finally {
            dao.em.close();
        }
    }

    public long qtdVendas(){
        try {
            dao.em = dao.emf.createEntityManager();
            TypedQuery<Long> consulta = dao.em.createQuery(
                    "Select COUNT(vendas) from Venda vendas", Long.class);
            return consulta.getSingleResult();
        } finally {
            dao.em.close();

        }
    }
}
