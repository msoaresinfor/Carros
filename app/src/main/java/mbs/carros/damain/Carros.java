package mbs.carros.damain;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Marcelo on 10/03/2015.
 */
public class Carros implements Serializable {

    private Integer id;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
    private String marca;
    private String placa;
    private String combustivel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return nome + " " + marca;
    }
}
