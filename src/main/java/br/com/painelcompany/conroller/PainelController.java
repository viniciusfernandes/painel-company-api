package br.com.painelcompany.conroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elevadorcompany.Elevador;
import br.com.elevadorcompany.ItemEmbarcavel;
import br.com.elevadorcompany.Mensagem;

@CrossOrigin
@RestController
@RequestMapping("elevador")
public class PainelController {
	private static final Elevador elevador;
	private static final int andarMin = -1;
	private static final int andarMax = 15;

	static {
		elevador = new ElevadorTeste(andarMin, andarMax, 300, 10);
	}

	public PainelController() {

	}

	@GetMapping("/config")
	public ResponseEntity<ElevadorConfig> configuracao() {
		return ResponseEntity.ok(new ElevadorConfig(andarMin, andarMax, elevador.descricaoTecnica()));
	}

	@GetMapping("/movimentar/{indice}/{peso}/{andar}")
	public ResponseEntity<Mensagem> movimentarElevador(@PathVariable Integer indice, @PathVariable Integer peso,
			@PathVariable int andar) {
		if (indice >= 0 && !elevador.desembarcar(indice)) {
			return ResponseEntity.badRequest().body(montarMensagem(elevador));
		}

		if (peso >= 0 && !elevador.embarcar(new ItemEmbarcavel(peso))) {
			return ResponseEntity.badRequest().body(montarMensagem(elevador));
		}

		if (!elevador.movimentar(andar)) {
			return ResponseEntity.badRequest().body(montarMensagem(elevador));
		}
		return ResponseEntity.ok((montarMensagem(elevador)));
	}

	private Mensagem montarMensagem(Elevador elevador) {
		return elevador.mensagem().append("\n").append(elevador.descricaoItens());
	}

}
