package com.cicom.relatorioviaturas.DAO;

import static com.cicom.relatorioviaturas.DAO.AbstractDAO.administracao;
import com.cicom.relatorioviaturas.model.Mesa;
import com.cicom.relatorioviaturas.model.Unidade;
import java.util.List;

/**
 *
 * @author icaro.bastos
 */
public class UnidadeDAO extends AbstractDAO<Unidade> {

    public UnidadeDAO() {
        super(Unidade.class);
    }

    @SuppressWarnings("unchecked")
    public Unidade buscaPorNome(String nome) {
        List<Unidade> resultados = null;
        try {
            resultados = administracao.createQuery("SELECT u FROM Unidade u WHERE u.nome=:nome")
                    .setParameter("nome", nome)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        }

        if (resultados.size() > 0) {
            return resultados.get(0);
        } else {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Unidade> getListAtivos() {

        List<Unidade> t = null;

        try {
            transacao.begin();
            t = administracao.createQuery("SELECT u FROM Unidade u WHERE u.ativo=1").getResultList();
            transacao.commit();
        } catch (Exception e) {
            if (transacao != null) {
                transacao.rollback();
            }
            e.printStackTrace();
        }
        return t;
    }
}
