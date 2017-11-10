package br.edu.ulbra.gestaovinhos.repository;

import br.edu.ulbra.gestaovinhos.model.Avaliacao;
import br.edu.ulbra.gestaovinhos.model.TipoVinho;
import org.springframework.data.repository.CrudRepository;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {

}
