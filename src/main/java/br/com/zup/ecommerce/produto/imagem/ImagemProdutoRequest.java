package br.com.zup.ecommerce.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImagemProdutoRequest {

    @NotNull @Size(min = 1, message = "É esperado no mínimo uma imagem.")
    private List<MultipartFile> imagens;

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return this.imagens;
    }
}
