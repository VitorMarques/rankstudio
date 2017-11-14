package br.com.tcc.rankstudio.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DataUtils {


    public static int calculaIdade(Date dataNascimentoInformada) {

        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.setTime(dataNascimentoInformada);

        Calendar hoje = Calendar.getInstance();
        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        dataNascimento.add(Calendar.YEAR, idade);

        if(hoje.before(dataNascimento)) {
            idade--;
        }

        return idade;
    }


    /**
     * inverte o formato da data para que o MySql possa gravar com sucesso
     *
     * @param dataNascimento
     * @return
     * @throws ParseException
     */
    public static String formataDataBanco(String dataNascimento) throws ParseException {

        StringBuilder dataFormatada = new StringBuilder();

        String camposData[] = dataNascimento.split("-");

        dataFormatada.append(camposData[2]);
        dataFormatada.append("-");
        dataFormatada.append(camposData[1]);
        dataFormatada.append("-");
        dataFormatada.append(camposData[0]);

        return dataFormatada.toString();
    }

    public static Date converteStringParaDate(String data) throws Exception {

        if (data == null || data.equals("")) {
            return null;
        }

        Date date = null;

        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            date = formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    public static String calendarToStringDate(Calendar data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(data);
        return sdf.format(data.getTime());
    }

}
