package br.com.zup.ecommerce.simulacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RecursosExternosController {

    @PostMapping("/sefaz/nfce")
    public ResponseEntity<?> gerarNota(@RequestBody @Valid NotaFiscalRequest notaFiscalRequest) {
        System.out.println("Nota fiscal criada: \n"+ notaFiscalRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rankingVendedores")
    public ResponseEntity<?> ranking(@RequestBody @Valid RankingVendedorRequest rankingVendedorRequest) {
        System.out.println("Ranking atualizado: \n"+ rankingVendedorRequest);
        return ResponseEntity.ok().build();
    }
}
