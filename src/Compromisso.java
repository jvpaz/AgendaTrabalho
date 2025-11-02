import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compromisso {
    private String descricao;
    private LocalDateTime periodo;
    private Estado estado;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");

    public Compromisso(){};

    public Compromisso(String descricao, LocalDateTime periodo)
    {
        this.descricao = descricao;
        this.periodo = periodo;
        this.estado = Estado.Pendente;
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

    public Estado geEstado()
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
        return getDescricao() + " | " + getPeriodo().format(formatador) + " | " + geEstado().getDescricao();
    } 
}
