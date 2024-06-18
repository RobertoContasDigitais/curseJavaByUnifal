
package resource;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Checker {

    public Checker(){}

    public static boolean dateFormatValidate(String date){
        //tamanho [01/34/6789] ou [01/34/67]
        String numbers = "0123456789"; int i = 0;
        String dateReturn = "";
        System.out.println(date.length());

        if((date.length() == 10) || (date.length() == 8)){
                    
            for(char l : date.toCharArray()){
                if((i!=2)||(i!=5)){
                    dateReturn += numbers.contains(Character.toString(l)) == true  ? Character.toString(l) : "";
                }
                if((i==2)||(i==5)){
                    dateReturn += (Character.toString(l) == "/") ? Character.toString(l) : "/";
                }
                i++;
            }

            if((dateReturn.length() == 10) || (dateReturn.length() == 8)){
                String[] sep = dateReturn.split("/");

                if((Integer.valueOf(sep[0]) < 1) && (Integer.valueOf(sep[0]) > 31)){
                    return false;
                }
                if((Integer.valueOf(sep[1]) < 1) && (Integer.valueOf(sep[1]) > 12)){
                    return false;
                }
                if(sep[2].length()== 4 && (Integer.valueOf(sep[2]) < 2024)){
                    return false;
                }
                if(sep[2].length()== 2 && (Integer.valueOf(sep[2]) < 24)){
                    return false;
                }

            }
            
            

        }else{
            return false;
        }
        //daqui pra baixo vou validar quanto a data, se ela não for uma data real
        String[] dateSepare = dateReturn.split("/");
        GregorianCalendar dateImput = new GregorianCalendar(Integer.valueOf(dateSepare[2]),
        Integer.valueOf(dateSepare[1]),Integer.valueOf(dateSepare[0]));
        GregorianCalendar datePresent = new GregorianCalendar();
        if(!(dateImput.compareTo(datePresent) > 0))
            return false;

        return true;
    }

    public static String formatDateDefalt(String date){
        String dateByReturn = "" ;
        String numbers = "0123456789";

        for(char l : date.toCharArray()){
            if(!numbers.contains(Character.toString(l))){
                dateByReturn += "/";
            }else{
                dateByReturn += l;
            }   
        }
        return dateByReturn;
    }

    public static float dateCheckProgress(String dateFinaly, String dateInitial){
        float status = 0; //status tem que retornar um numero de 0 a 1
        double init, finaly, now;

        String[] dateInitialSepare = dateInitial.split("/");
        init = (double)(Integer.valueOf(dateInitialSepare[0])) +
            (double)(Integer.valueOf(dateInitialSepare[1])*30) +
            (double)(Integer.valueOf(dateInitialSepare[2])*365);
        
        
        String[] dateFinalySepare = dateFinaly.split("/");
        finaly = (double)(Integer.valueOf(dateFinalySepare[0])) +
        (double)(Integer.valueOf(dateFinalySepare[1])*30) +
        (double)(Integer.valueOf(dateFinalySepare[2])*365);
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String[] dateNow = simpleDateFormat.format(new GregorianCalendar().getTime()).split("/");
        now = (double)(Integer.valueOf(dateNow[0])) +
        (double)(Integer.valueOf(dateNow[1])*30) +
        (double)(Integer.valueOf(dateNow[2])*365);

        System.out.println(init);
        System.out.println(now);
        System.out.println(finaly);

        status = (float)((finaly-now)/(finaly-init));

        return status;
    }


}