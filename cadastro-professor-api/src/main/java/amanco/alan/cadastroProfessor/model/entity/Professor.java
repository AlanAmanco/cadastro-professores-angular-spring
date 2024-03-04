package amanco.alan.cadastroProfessor.model.entity;

import amanco.alan.cadastroProfessor.Utilidades;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 150)
    private String nome;
    @Column(nullable = false,length = 50)
    private String endereco;
    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataNascimento;
    @Column(length = 11)
    private String telefone;
    @Column(length = 30)
    private String email;
    @Column(nullable = false,length = 50)
    private String curso;
    @Column(nullable = false,length = 50)
    private String disciplina;
    @Column(nullable = false,length = 50)
    private String titulacao;
    @Column(name = "documento_entregue")
    private boolean documentosEntregues;
    private Integer idade;

    public Professor() {
    }

    public Professor(Integer id, String nome, String endereco, String dataNascimento, String telefone, String email, String curso, String disciplina, String titulacao, boolean documentosEntregues) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.curso = curso;
        this.disciplina = disciplina;
        this.titulacao = titulacao;
        this.documentosEntregues = documentosEntregues;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public boolean isDocumentosEntregues() {
        return documentosEntregues;
    }

    public void setDocumentosEntregues(boolean documentosEntregues) {
        this.documentosEntregues = documentosEntregues;
    }

    public Integer getIdade() {
        if(!dataNascimento.isEmpty()){
            idade = Utilidades.calcularIdade(getDataNascimento());
        }else{
            idade = 0;
        }
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
