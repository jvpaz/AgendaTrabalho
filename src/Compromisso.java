import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compromisso {
    private String descricao;
    private LocalDateTime periodo;
    private Estado estado = Estado.Pendente;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");

    public Compromisso(){};

    public Compromisso(String descricao, LocalDateTime periodo)
    {
        this.descricao = descricao;
        this.periodo = periodo;
    }

    public Compromisso(String descricao, LocalDateTime periodo, Estado estado)
    {
        this.descricao = descricao;
        this.periodo = periodo;
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getPeriodo() {
        return periodo;
    }

    public Estado getEstado()
    {
        return estado;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPeriodo(LocalDateTime periodo) {
        this.periodo = periodo;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getDescricao() + " | " + getPeriodo().format(formatador) + " | " + getEstado().getDescricao();
    } 
}
