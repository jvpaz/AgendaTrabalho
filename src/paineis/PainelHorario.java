package paineis;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import model.Compromisso;

public class PainelHorario extends JPanel {
    public PainelHorario(Compromisso newCompromisso) {
        // Define layout do próprio painel
        setLayout(new GridLayout(2, 2, 5, 5));

        //Criação de calendário e relógio.
        DatePicker datePicker = new DatePicker();
        TimePicker timePicker = new TimePicker();

        // Adiciona os componentes diretamente no PainelHorario
        add(new JLabel("Selecione a data:")); 
        add(datePicker);
        add(new JLabel("Selecione o horário:"));
        add(timePicker);

        //Exibe PainelHorario ao usuário, com opções de cancelar o procedimento ou prosseguir com OK.
        int result = JOptionPane.showConfirmDialog(this, this, "Selecione a data e horário", JOptionPane.OK_CANCEL_OPTION);
       if (result == JOptionPane.OK_OPTION && (datePicker.getDate() == null || timePicker.getTime() == null)) {
            JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma data/horário válido!");
            return;
        }
        else if(result == JOptionPane.OK_OPTION){
            LocalDateTime dateTime = LocalDateTime.of(datePicker.getDate(), timePicker.getTime());
    //System.out.println(dateTime);
        newCompromisso.setPeriodo(dateTime);
    }
}
}

