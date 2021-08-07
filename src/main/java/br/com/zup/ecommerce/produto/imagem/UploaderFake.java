package br.com.zup.ecommerce.produto.imagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderFake {
    public Set<String> enviarFotos(List<MultipartFile> imagens){
        return imagens.stream().map(imagem -> "https://s3.amazon.com/"+imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}
