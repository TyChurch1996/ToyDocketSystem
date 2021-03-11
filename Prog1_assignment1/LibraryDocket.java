import java.io.BufferedReader;
import java.io.FileReader;

public class LibraryDocket
{

   public static void main(String[] args)
   {

      new LibraryDocket(0);

   }

   // accessor will simply control the order our methods run
   public LibraryDocket(int docketId)
   {

      PrintDocket(docketId);

   }

   // This method will Fetch our product values
   public void getValues()
   {
      ToyInventory toyList = new ToyInventory("");
      String[] TempListSize = toyList.getAllToysIDs();
      System.out.println(TempListSize.length);
      int ListSize = TempListSize.length;
      int[] Toyid = new int[ListSize];

   }

   // this method will be the inital docket information for console
   public void PrintDocket(int docketId)
   {
      // placeholder values
      int DocketId = docketId;
      String LibName = "Casey's Educational Toy Library";
      String Date = getRecordedDate(docketId);

      // using String as purpose is for receipt
      String MemId = getMemId(docketId);
      String MemName = getMemName(MemId);
      String MemDob = getMemDob();
      String docketOutput =
               "***** Casey's Educational Toy Library Record System *****\n" +
                            "Library Details: \n" +
                            "DocketId: %-20d \n" +
                            "Library Name: %-20s \n" +
                            "Date: %-20s\n" +
                            "\nMember Details: \n" +
                            "MemId: %-20s \n" +
                            "Name: %-20s \n" +
                            "MemDob: %-20s \n\n" +
                            "****List of Toys Borrowed****\n" +
                            "--------------------------------------------------------------------------------------------\n" +
                            "ToyID     Toy Name                 Toy Descriptions                             Status   \n";
      String borrowedToys = "132";
      docketOutput = docketOutput + borrowedToys;
      System.out.printf(docketOutput, DocketId, LibName, Date, MemId, MemName,
                        MemDob);

   }

   // method incomplete due to time constraint
   // method will return members dob similar to getMemId but different file
   private String getMemDob()
   {
      // TODO Auto-generated method stub
      return null;
   }

   // method incomplete due to time constraint
   // method will return members dob similar to getMemName but different file
   private String getMemName(String memId)
   {
      // TODO Auto-generated method stub
      return null;
   }

   // this method fetches the memberId associated with the docket
   private String getMemId(int docketId)
   {
      BufferedReader inFileMemDetails = null;

      String[] tempMemId = null;
      // handle bufferedReader here
      try
      {

         inFileMemDetails =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/DocketNum_" + docketId +
                                                    ".csv"));

         String currLine = inFileMemDetails.readLine();
         {

            tempMemId = currLine.split(",");

            currLine = inFileMemDetails.readLine();

         }

         inFileMemDetails.close();

      }

      catch (Exception e)
      {
         System.out.println("Error reading file");

      }

      return tempMemId[0];
   }

   // this method retrieve the date loan was recorded from docket
   private String getRecordedDate(int docketId)
   {
      BufferedReader inFileMemDetails = null;

      String[] tempDate = null;
      // handle bufferedReader here
      try
      {

         inFileMemDetails =
                  new BufferedReader(new FileReader(System.getProperty("user.dir") +
                                                    "/DocketNum_" + docketId +
                                                    ".csv"));

         String currLine = inFileMemDetails.readLine();
         {

            tempDate = currLine.split(",");

            currLine = inFileMemDetails.readLine();

         }

         inFileMemDetails.close();

      }

      catch (Exception e)
      {
         System.out.println("Error reading file");

      }

      return tempDate[1];
   }

}
