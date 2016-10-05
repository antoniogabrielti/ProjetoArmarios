
package model;

import java.util.Date;


public class Emprestimo {
    private Armario armario;
    private Aluno proprietario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Armario getArmario() {
        return armario;
    }

    public void setArmario(Armario armario) {
        this.armario = armario;
    }

    public Aluno getProprietario() {
        return proprietario;
    }

    public void setProprietario(Aluno proprietario) {
        this.proprietario = proprietario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    
    
    
}
