package resource;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)

public class Tasks{
    private int id;
    private String name;
    private String dataInicio, dataEntrega, tempoRestante, tempoOutros;
    private String description;
<<<<<<< HEAD

    public Tasks(String name, String description){
        this.name = name;
        this.description = description;
        this.id = new Random().nextInt(99);
        getToSystemDate();

    }
 
    public Tasks(String name, String description, String dataEntrega){
        this.name = name;
        this.description = description;
        this.dataEntrega = dataEntrega;
        this.id = new Random().nextInt(99);
=======
    private Locale locale = new Locale("pt", "BR");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM 'de' yyyy", locale);
 
    public Tasks(String name, String description, String dataEntrega, int size){
        this.name = name;
        this.description = description;
        this.dataEntrega = dataEntrega;
        this.id = size+1;
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
        getToSystemDate();
    }

    private void getToSystemDate(){//ok
<<<<<<< HEAD
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "EEEE, 'de' dd MMMM 'de' yyyy", new Locale("pt","BR"));
        this.dataInicio = simpleDateFormat.format( new GregorianCalendar().getTime());
    }

=======
        this.dataInicio = simpleDateFormat.format( new GregorianCalendar().getTime());
    }

    public String getName(){
        return this.name;
    }

>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
    public int getId() {//ok
        return this.id;
    }

    public String getDataInicio() {//ok
        return this.dataInicio;
    }

    public String getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {// tem que formatar a data
<<<<<<< HEAD
=======
        String[] dateEntrega = dataEntrega.split("/.- ");
        for(String d : dateEntrega){
            System.out.println(d);
        }
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
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
                "------["+this.name.toUpperCase()+"]------\n"+
                "Date Inicialize: "+this.dataInicio+"\n"+
                "Date to finish:  "+this.dataEntrega+"\n"+
<<<<<<< HEAD
                "Date to progress"+"\n"+
                "Description:\n"+
                    "\t"+this.description;
=======
                "Date to progress:"+this.tempoRestante+"\n"+
                "Description:\n"+
                    "\t"+this.description+"\n"+
                "___________________________________________________________________________\n";
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
    }
    

    
}
