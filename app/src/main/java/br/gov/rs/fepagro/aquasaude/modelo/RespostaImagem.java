package br.gov.rs.fepagro.aquasaude.modelo;

/**
 * Created by eduardo-pooch on 14/09/2016.
 */
public class RespostaImagem {
    boolean certa;
    int resIdFoto;

    public void Resposta(int resIdFoto, boolean certa) {
        this.resIdFoto = resIdFoto;
        this.certa = certa;
    }

    public boolean isCerta() {
        return certa;
    }

}
