import org.jsoup.nodes.*;
import org.jsoup.*;

public class WebScrape {
    public static void main(String[] args) {

        int total = 0; //Variable to store total number of cases
        Stack<String> myStack = new Stack<String>(); //Stack to store all the data of date and cases

        String country = args[0]; //First user-entered argument specifies the country
        int days = Integer.parseInt(args[1]); //Second user-entered argument specifies the number of days

        final String url = "https://www.google.com/search?q="+country+"+corona+cases"; //URL to search

        try {
            final Document myDoc = Jsoup.connect(url).get();

            for (Element list : myDoc.select("div.FMJSFf ol li")) { //Select all the elements beginning with <li> inside <ol> under div class='FMJSFf'
                myStack.push(list.toString().replace("<li>", "").replace("</li>","")); //Removes <li>and</li> from results obtained and push it into stack
            }

            if (days > myStack.size())
              days = myStack.size();

            System.out.println("\nListed below is the number of new cases each day in "+country+" for the past "+days+" days:");
            for (int i=0; i < days; i++) { //Pops x entry from the stack, returning the cases each day and accumulates total cases
                String data = myStack.pop();
                System.out.println(data); //Prints out 'numberOfCases on date'
                total += Integer.parseInt((data.split(" on ")[0]).replaceAll(",","")); //Data obtained is in the form 'numberOfCases on date', hence split the string to {numberOfCases, date} and convert numberOfCases to Integer
            }
            System.out.println("The total number of cases for the past "+days+" days is "+total+".");

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
