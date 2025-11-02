package model;
public enum Estado {
    Concluida("Concluida"),
    Pendente("Pendente");

    private String descricao;

    Estado(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return descricao;
    }
}
