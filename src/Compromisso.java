import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compromisso {
    private String descricao;
    private LocalDateTime periodo;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");

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
        return this.periodo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPeriodo(LocalDateTime periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return getDescricao() + " | " + getPeriodo().format(formatador);
    } 
}
