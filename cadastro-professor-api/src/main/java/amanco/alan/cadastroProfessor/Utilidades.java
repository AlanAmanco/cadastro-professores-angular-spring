package amanco.alan.cadastroProfessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {

    public static int calcularIdade(String dataNascimentoString) {


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try {
            dataNascimento = formato.parse(dataNascimentoString);
        } catch (ParseException e) {
            throw new RuntimeException("Formato de data inválido. Use dd/MM/yyyy", e);
        }

        Calendar dataNascimentoCal = Calendar.getInstance();
        dataNascimentoCal.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimentoCal.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimentoCal.get(Calendar.MONTH) ||
                (hoje.get(Calendar.MONTH) == dataNascimentoCal.get(Calendar.MONTH) &&
                        hoje.get(Calendar.DAY_OF_MONTH) < dataNascimentoCal.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }

}
