package br.mackenzie.ps2.cmdcrud;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="titulares")
public class Titular {
    @Id
    private long nroTitular;
    private String nome;
    private String rg;
    private String cpf;
    public Titular() {}
    public Titular(long nro, String n, String r, String c) {
        nroTitular = nro;
        nome = n;
        rg = r;
        cpf = c;
    }
    public long getNroTitular() {
        return nroTitular;
    }
    public void setNroTitular(long nroTitular) {
        this.nroTitular = nroTitular;
    }
    public String getNome() { return nome; }
    public void setNome(String n) { nome = n; }
    public String getRg() { return rg; }
    public void setRg(String r) { rg = r; }
    public String getCpf() { return cpf; }
    public void setCpf(String c) { cpf = c; }    
}
