package resource;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Tasks{
    private int id;
    private String name;
    private String dataInicio, dataEntrega, tempoRestante, tempoOutros;
    private String description;
    @SuppressWarnings("deprecation")
    private Locale locale = new Locale("pt", "BR");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
 
    public Tasks(String name, String description, String dataEntrega, int size){
        this.name = name;
        this.description = description;
        this.dataEntrega = dataEntrega;
        this.id = size+1;
        getToSystemDate();
    }

    private void getToSystemDate(){//ok
        this.dataInicio = simpleDateFormat.format( new GregorianCalendar().getTime());
    }

    public String getName(){//ok
        return this.name;
    }

    public int getId() {//ok
        return this.id;
    }

    public String getDataInicio() {//ok
        return this.dataInicio;
    }

    public String getDataEntrega() {//ok
        return this.dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {// tem que formatar a data
        String[] dateEntrega = dataEntrega.split("/.- ");
        for(String d : dateEntrega){
            System.out.println(d);
        }
        this.dataEntrega = dataEntrega;
    }

    public String getTempoRestante() {// tem que formatar a data
        return tempoRestante;
    }

    public void setTempoRestante(String tempoRestante) {// tem que formatar a data
        this.tempoRestante = tempoRestante;
    }

    public String getTempoOutros() {// tem que formatar a data
        return this.tempoOutros;
    }

    public void setTempoOutros(String tempoOutros) {
        this.tempoOutros = tempoOutros;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return  "\n"+
                "["+this.name.toUpperCase()+"]\n"+
                "Date Inicialize: "+this.dataInicio+"\n"+
                "Date to finish :  "+this.dataEntrega+"\n"+
                "Progress       :"+"-----CRIAR----"+"\n"+
                "Task Status    :"+"-----CRIAR----"+"\n"+
                "Task Map       :"+"-----CRIAR----"+"\n"+
                "To be warned   :"+"-----YES/NO---"+"\n"+
                "Description:\n"+
                    "\t"+this.description+"\n"+
                "___________________________________________________________________________\n";
    }
    

    
}
