
package resource;

public class Checker {

    public Checker(){}

    public static boolean dateFormat(String date){
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
        date = dateReturn;
        return true;
    }
}