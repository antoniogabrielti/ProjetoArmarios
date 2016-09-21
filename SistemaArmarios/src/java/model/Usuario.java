package model;

public class Usuario {
  private String login;
  private String senha;
  private boolean EFuncionario;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEFuncionario(boolean eFuncionario) {
        this.EFuncionario = eFuncionario;
    }

    public boolean isEFuncionario() {
        return EFuncionario;
    }
    public boolean verificaLogin(String login, String senha){
        return(this.login.equals(login) && this.senha.equals(senha));
    }
}
