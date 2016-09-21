package br.gov.rs.fepagro.aquasaude_camarao.modelo;

/**
 * Created by eduardo-pooch on 14/09/2016.
 */
public class Resposta {
    boolean certa;
    String resposta;
    int resIdFoto;

    public boolean isCerta() {
        return certa;
    }

    public void setCerta(boolean certa) {
        this.certa = certa;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getResIdFoto() {
        return resIdFoto;
    }

    public void setResIdFoto(int resIdFoto) {
        this.resIdFoto = resIdFoto;
    }
}
