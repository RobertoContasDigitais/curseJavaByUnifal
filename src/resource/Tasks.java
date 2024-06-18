package resource;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Tasks{
    private int id;
    private String name;
    private String dataInicio, dataEntrega;
    private String description;
    private String status = "INICIADO";
    @SuppressWarnings("deprecation")
    private Locale locale = new Locale("pt", "BR");
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
 
    public Tasks(int id){this.id = id;}

    public Tasks(String name, String description, String dataEntrega, int size){
        this.name = name;
        this.description = description;
        this.dataEntrega = dataEntrega;
        this.id = size+1;
        getToSystemDate();
    }

    private void getToSystemDate(){this.dataInicio = simpleDateFormat.format( new GregorianCalendar().getTime());}
    
    private String getDateNow(){return simpleDateFormat.format( new GregorianCalendar().getTime());}

    public void setStatus(String status){this.status = status;}

    public String getName(){return this.name;}

    public void setId(int id){this.id = id;}
    
    public void setName(String name){this.name = name;}
    
    public void setDateInitialize(String dateInit){this.dataInicio = dateInit;}
    
    public void setDateFinal(String dateFinal){this.dataEntrega = dateFinal;}
    
    public void setDescription(String description) {this.description = description;}
    
    public int getId() {return this.id;}
    
    public String getDescription() {return this.description;}

    public String getDateFinal(){return this.dataEntrega;}
    
    public String getDareInitial(){return dataInicio;}
    
    @Override
    public String toString(){
        return  "["+this.name.toUpperCase()+"]\n"+
                "Date Inicialize: "+this.dataInicio+"\n"+
                "Date to finish : "+this.dataEntrega+"\n"+
                "Progress       : "+Checker.dateCheckProgress(this.dataEntrega, this.dataInicio)+"\n"+
                "Task Status    : "+this.status+"\n"+
                "Task Map       : "+"-----CRIAR----"+"\n"+
                "To be warned   : "+"-----YES/NO---"+"\n"+
                "Description:\n "+
                    "\t"+this.description+"\n"+
                "_________________________________________|ultima edição:"+getDateNow()+"\n";
    }
    

    
}
