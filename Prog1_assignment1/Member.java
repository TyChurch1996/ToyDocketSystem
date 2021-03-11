
/*-------BUGS---------
 * -------------------
 * --application lacks some verification processors when entering details/mem id/docket number.
 * --Menu occasionally repeats itself upon opening to a new main menu.
 * --occasionally menu requires a keypressed for next menu however this may be an issue with my eclipse.
 * --Docket currently does not fully record all details .
 * --currently some aspects of assignment are incomplete due to Internet issues and lack of java api during this period.
 * -------------------
 * -------------------
 * Alternatives
 * -------------------
 * -------------------
 * for this project i used csv files to store data for application restart in order to maintain records correctly
 * alternatively i could have stored within a text file or not at all, the assignment does not currently use instance/member variables
 * as i did not see them being relevant to the desired result of the application however i have left notes where they should be initated
 * , the application uses java.time api and could alternatively of used the simpledateformat api.
 * ---------------------
 *---------------------- 
 *Note: upon reaching errors there is an error message which proceeds with a timer for returning to
 *the main menu and this time may vary depending on importance of errors.
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;

public class Member
{
   // define a scanner for console input
   // defining it here helps save repetition
   private Scanner input = new Scanner(System.in);

   public static void main(String[] args)
   {
      // initialize constructor
      new Member();

   }

   public Member()
   {
      // instance variables usually declared here however
      // given the nature of application instance variables are not needed in this
      // case

      // use constructor to initalize menu
      tlcMainMenu();
   }

   // this method is our main menu and allows us to navigate with ease
   private void tlcMainMenu()
   {
      updateStockList();
      updateMemberInfo();

      String menuOption;

      System.out
               .println("***** Casey's Educational Toy Library Record System *****\r\n\n" +
                        "Enter a leter below for required menu: \n" +
                        "A. New Member Registration\r\n" +
                        "B. Borrow a Toy\r\n" +
                        "C. Return a Toy\r\n" +
                        "D. View Library Docket\r\n" +
                        "X. Exit the program");

      menuOption = this.input.nextLine();

      // switch is a simple way to control the menu
      // alternatively if statement will fit this purpose
      switch (menuOption)
      {
         case "a":
            newMem();

            break;
         case "b":
            borrowToy();

            break;
         case "c":
            returnToy();

            break;
         case "d":
            viewDocket();

            break;
         case "x":

            System.exit(0);
            break;

         default:
            System.out.println("Incorrect menu Item\n");

            wsleep(3000);

            tlcMainMenu();

      }

   }

   // this method updates our stocklist to see whats in stock
   // this method is important since this allows us to keep stock updated
   // when closing or opening our program
   private void updateStockList()
   {
      BufferedReader inFileStockList = null;

      // filereader control handled here
      try
      {

         inFileStockList =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/stockList.csv"));

         String currLine = inFileStockList.readLine();
         // find end of file
         while (currLine != null)
         {

            currLine = inFileStockList.readLine();
         }

         inFileStockList.close();

      }
      // generate file if we do not have one
      catch (Exception e)
      {
         File f = new File("stockList.csv");

         try
         {
            f.createNewFile();
         }
         catch (IOException e1)
         {

            e1.printStackTrace();
         }
      }

   }

   // method for our thread.sleep to reduce code Repetition
   private void wsleep(int sleepTime)
   {
      try
      {
         Thread.sleep(sleepTime);
      }
      // handle errors created by printing out our stack trace
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }

   }

   // this method record member information into a csv
   // and allows us to load it upon application restart
   private void updateMemberInfo()
   {

      BufferedReader inFileMemDetails = null;

      // handle buffered reader usage
      try
      {

         inFileMemDetails =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/memberDetails.csv"));

         String currLine = inFileMemDetails.readLine();
         // find end of file
         while (currLine != null)
         {

            currLine = inFileMemDetails.readLine();
         }

         inFileMemDetails.close();

      }
      // create a new file if above failed
      catch (Exception e)
      {
         File f = new File("memberDetails.csv");

         try
         {
            f.createNewFile();
         }
         catch (IOException e1)
         {

            e1.printStackTrace();
         }
         System.out.println("No memberDetails.csv found, Creating new file...");
      }

      return;

   }

   // this method is used to generate a member id and record it based
   // upon the .csv list we have generated previously
   private int generateMemId()
   {

      BufferedReader inFileMemDetails = null;

      int lineNum = 0;

      // handle bufferedReader here
      try
      {

         inFileMemDetails =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/memberDetails.csv"));

         String currLine = inFileMemDetails.readLine();
         // find end of file
         while (currLine != null)
         {
            lineNum++;

            currLine = inFileMemDetails.readLine();
         }

         inFileMemDetails.close();

      }
      catch (Exception e)
      {
         System.out.println("Error reading file");

      }

      return lineNum;

   }

   // method for creating a new member and registering their details
   private void newMem()
   {
      int MemId;
      String MemName;
      String MemDob;
      System.out.println("Enter new members name:\n");
      MemName = this.input.nextLine();
      System.out.println("Enter new members date of birth:(dd/mm/yyyy)\n");
      MemDob = this.input.nextLine();
      MemId = generateMemId();
      System.out.println("Your member id is " + MemId);

      saveMemDetails(MemId, MemName, MemDob);
      tlcMainMenu();
   }

   // this method is used to save member details into the .csv file
   private void saveMemDetails(int MemId, String MemName, String MemDob)
   {

      BufferedWriter outFileMemDetails;

      // handle buffered writer here
      try
      {

         outFileMemDetails =
                  new BufferedWriter(new FileWriter(System.getProperty("user.dir") +
                                                    "/memberDetails.csv", true));

         outFileMemDetails.write(
                                 MemId + "," + MemName + "," + MemDob + "\n");

         outFileMemDetails.close();

      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
      }

      return;

   }

   // method used to retrieve the members age from csv file
   private String getMemAge(String memId)
   {

      String[] tempDetails = null;
      String[] tempCheck;
      BufferedReader inFileMemDetails = null;

      // handle bufferedReader here
      try
      {

         inFileMemDetails =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/memberDetails.csv"));

         String currLine = inFileMemDetails.readLine();
         while (currLine != null)
         {

            tempCheck = currLine.split(",");
            if (tempCheck[0].contains(memId))
            {

               tempDetails = tempCheck;

            }

            currLine = inFileMemDetails.readLine();

         }

         inFileMemDetails.close();

      }

      catch (Exception e)
      {
         System.out.println("Error reading file for ageCheck");

      }

      return tempDetails[2];

   }

   // used to set a boolean if member makes age for toy
   private boolean ageCheck(String memId, int minYears)
   {
      boolean isOldEnough = false;
      String tempAge = getMemAge(memId);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate now = LocalDate.now();
      LocalDate birthday = LocalDate.parse(tempAge, formatter);

      System.out.println(tempAge);

      long years = birthday.until(now, ChronoUnit.YEARS);
      // set boolean dependant on years against minyears of toy
      if (years > minYears)
      {
         isOldEnough = true;
      }
      else
      {
         isOldEnough = false;
      }

      return isOldEnough;

   }

   // this method is designed to handle borrowing toys
   private void borrowToy()
   {
      ToyInventory ti = new ToyInventory("Casey's Educational Toy Library");
      String tempToyId;
      String memId;
      int docketId;
      char tempToyStatus;
      String confirmation;

      System.out.println("Enter Member Id");
      memId = this.input.next();
      System.out.println("Enter toy ID");
      tempToyId = this.input.next();
      String tempToyDesc = ti.getDescription(tempToyId);
      String tempToyName = ti.getName(tempToyId);
      int minYears = ti.getMinYears(tempToyId);
      double tempDailyRate = ti.getDaily_rate(tempToyId);

      System.out.println("Toy Name: " + tempToyName);
      System.out.println("Description: " + tempToyDesc);
      System.out.println("\nAdd " + tempToyName +
                         " to member ID number: " + memId +
                         "\nplease note this toy has a minimum age of " + minYears +
                         "\nthis Toy has a Daily Rate of " + tempDailyRate +
                         " (Type Y or N)");
      confirmation = this.input.next();
      // provide confirmation for capitols and lowercase
      if (confirmation.equals("y") || confirmation.equals("yes") ||
          confirmation.equals("Y") || confirmation.equals("Yes"))
      {

         System.out.println("current min years is: " + minYears);
         boolean canBorrow = ageCheck(memId, minYears);
         tempToyStatus = ti.getStatus(tempToyId);
         System.out.println("Toy Status is: " + tempToyStatus);
         if (canBorrow == false)
         {
            System.out
                     .println("Member does not meet age requirement...Returning to main menu....");
            wsleep(5000);
            tlcMainMenu();
            return;

         }
         // check if toy available
         else if (tempToyStatus == 'A')
         {
            System.out.println("Toy is Available to be borrowed!");

            ti.setToyStatus(tempToyId, 'o');
            docketId = createDocket(memId, tempToyId);
            System.out.println("Your docket id is: " + docketId);
            writeDocket(docketId, memId, tempToyName, tempToyId, tempToyDesc,
                        tempDailyRate);
            wsleep(5000);
            tlcMainMenu();
            return;
         }

         else
         {
            System.out
                     .println("Toy Currently Unavailable..Returning to main menu...\n");
            wsleep(5000);
            tlcMainMenu();
            return;
         }

      }
      else
      {

         tlcMainMenu();
         return;

      }

   }

   // method is used to write contents to docket after created
   private void writeDocket(int docketId, String memId, String tempToyName,
                            String tempToyId, String tempToyDesc,
                            double tempDailyRate)
   {
      BufferedWriter outFileMemDetails;
      BufferedReader inFileMemDetails;
      // handle buffered writer here
      try
      {

         outFileMemDetails =
                  new BufferedWriter(new FileWriter(System.getProperty("user.dir") +
                                                    "/DocketNum_" + docketId +
                                                    ".csv", true));

         outFileMemDetails.write(
                                 memId + "," + LocalDate.now() + "\n");
         inFileMemDetails =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/DocketNum_" + docketId +
                                                    ".csv"));

         outFileMemDetails.write(
                                 tempToyName + "," + tempToyId + "," + tempToyDesc +
                                 "," + tempDailyRate + "\n");

         outFileMemDetails.close();
         inFileMemDetails.close();

      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
      }

      return;

   }

   // method is used to create a docket with a random docketnumber
   private int createDocket(String memID, String tempToyId)
   {
      Random randPick = new Random();
      int tempDocNumber = randPick.nextInt(Integer.MAX_VALUE);
      String tempName = "DocketNum_" + tempDocNumber;
      File f = new File(tempName + ".csv");

      try
      {
         f.createNewFile();
      }
      catch (IOException e1)
      {

         e1.printStackTrace();
      }
      return tempDocNumber;
   }

   // method used for returning toys
   private void returnToy()
   {
      ToyInventory ti = new ToyInventory("Casey's Educational Toy Library");
      String tempToyId;
      int docketId;
      String confirmation;

      System.out.println("Enter docket ID: ");
      docketId = this.input.nextInt();
      System.out.println("Enter toy ID");
      tempToyId = this.input.next();

      String tempToyDesc = ti.getDescription(tempToyId);
      String tempToyName = ti.getName(tempToyId);
      System.out.println("Toy Name: " + tempToyName);
      System.out.println("Description: " + tempToyDesc);
      System.out.println("return " + tempToyName + "From docket number " + docketId +
                         " (Type Y or N)");
      confirmation = this.input.next();
      if (confirmation.equals("y") || confirmation.equals("yes"))
      {

         ti.setToyStatus(tempToyId, 'A');
      }
      else
      {
         tlcMainMenu();
         return;

      }
   }

   // this method is used for previewing created dockets based on the docketId
   // this method parses params into the library docket class
   private void viewDocket()
   {
      int docketId;
      System.out.println("enter docket Id:");
      docketId = this.input.nextInt();
      new LibraryDocket(docketId);
   }

}
