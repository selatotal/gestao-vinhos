package br.edu.ulbra.gestaovinhos.demos.input;

import org.springframework.web.multipart.MultipartFile;

public class UploadInput {

    private String descricao;
    private MultipartFile multipartFile;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
