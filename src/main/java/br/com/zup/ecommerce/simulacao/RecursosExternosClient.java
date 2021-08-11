package br.com.zup.ecommerce.simulacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "recursosExternos", url = "http://localhost:8080/")
public interface RecursosExternosClient {

    @PostMapping("/sefaz/nfce")
    String gerarNotaFiscal( @RequestHeader("Authorization") String token, NotaFiscalRequest notaFiscalRequest);

    @PostMapping("/rankingVendedores")
    String atualizarRankingCompradores(@RequestHeader("Authorization") String token,RankingVendedorRequest rankingVendedorRequest);
}
