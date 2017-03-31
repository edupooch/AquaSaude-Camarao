package br.gov.rs.fepagro.aquasaude.modelo;

/**
 * Created by eduardo-pooch on 14/09/2016.
 */
public class Resposta {
    public static final int TIPO_IMAGEM = 1;
    public static final int TIPO_TEXTO = 2;

    private int id;
    private boolean certa;
    private int fotoResId;
    private String texto;
    private int tipo;

    public Resposta(String texto, boolean certa) {
        this.texto = texto;
        this.certa = certa;
        this.tipo = TIPO_TEXTO;
    }

    public Resposta(int resIdFoto, boolean certa) {
        this.fotoResId = resIdFoto;
        this.certa = certa;
        this.tipo = TIPO_IMAGEM;
    }

    public int getId() {
        return id;
    }

    public boolean isCerta() {
        return certa;
    }

    public int getFotoResId() {
        return fotoResId;
    }

    public String getTexto() {
        return texto;
    }

    public int getTipo() {
        return tipo;
    }
}
