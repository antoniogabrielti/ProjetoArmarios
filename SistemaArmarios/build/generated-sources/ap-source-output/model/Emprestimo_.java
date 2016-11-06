package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Aluno;
import model.Armario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-06T20:32:54")
@StaticMetamodel(Emprestimo.class)
public class Emprestimo_ { 

    public static volatile SingularAttribute<Emprestimo, Armario> armario;
    public static volatile SingularAttribute<Emprestimo, Date> dataEmprestimo;
    public static volatile SingularAttribute<Emprestimo, Aluno> proprietario;
    public static volatile SingularAttribute<Emprestimo, Long> id;
    public static volatile SingularAttribute<Emprestimo, Date> dataDevolucao;

}