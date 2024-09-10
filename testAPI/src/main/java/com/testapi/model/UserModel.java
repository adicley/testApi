
package com.testapi.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
        
public class UserModel {
    private int cpf;
    private String nome;
    private Date dataNascimento;
	private SimpleDateFormat df;
    
    public UserModel(){
        this.df = new SimpleDateFormat("dd/MM/yyyy");
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) throws ParseException {
        this.dataNascimento = new Date(df.parse(dataNascimento).getTime());
    }
    
    public String toDateFormat(){
        return df.format(this.dataNascimento);
    }
}
