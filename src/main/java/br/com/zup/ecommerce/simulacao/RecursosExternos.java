package br.com.zup.ecommerce.simulacao;

import br.com.zup.ecommerce.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

@EnableFeignClients
@Component
public class RecursosExternos {

    @Autowired
    RecursosExternosClient recursosExternosClient;

    public void gerarNotaFiscal(Compra compra, String token) {
        recursosExternosClient.gerarNotaFiscal(token,new NotaFiscalRequest(compra.getUsuario().getId(),compra.getId()));
    }
    public void atualizarRankingVendedores(Compra compra, String token) {
        recursosExternosClient.atualizarRankingCompradores(token,new RankingVendedorRequest(compra.getProduto().getUsuario().getId(),compra.getId()));
    }
}
