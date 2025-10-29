import java.time.LocalDateTime;

public class Compromisso {
    private String descricao;
    private LocalDateTime periodo;

    public Compromisso(){};

    public Compromisso(String descricao, LocalDateTime periodo)
    {
        this.descricao = descricao;
        this.periodo = periodo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getPeriodo() {
        return periodo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPeriodo(LocalDateTime periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return descricao + " | " + periodo;
    }
}
