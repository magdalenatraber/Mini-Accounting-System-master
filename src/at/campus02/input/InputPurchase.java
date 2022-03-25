package at.campus02.input;

import at.campus02.model.Item;
import at.campus02.model.Purchase;
import at.campus02.model.Supplier;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class InputPurchase {
  private final Scanner scanner;
    private final  Map<Integer,Purchase> purchaseMap;
    private final Map<String,Supplier> supplierMap;
    private final InputHelper inputHelper;

    public InputPurchase(Scanner scanner, Map<Integer, Purchase> purchaseMap, Map<String, Supplier> supplierMap, InputHelper inputHelper) {
        this.scanner = scanner;
        this.purchaseMap = purchaseMap;
        this.supplierMap = supplierMap;
        this.inputHelper = inputHelper;
    }

    public  Purchase addPurchase() throws Exception {
      System.out.println("*** Add at.campus02.model.Purchase ***");
      System.out.println("Enter at.campus02.model.Purchase Number: ");
      // purchase number



      String number = inputHelper.readString();
        int purchaseNo = inputHelper.readInt(1,999);
      //If input empty



      // purchase number

      // TRN no.
      System.out.println("Enter TRN No.");
      int trn_number = inputHelper.readInt(100000,999999);
      //TRN no.

      // Date
      System.out.println("*Date*");
      System.out.println("Enter Day: ");
      Date currentDate = new Date();

      int day = inputHelper.readInt(1,31);

      System.out.println("Enter Month: ");
      int month = inputHelper.readInt(1,12);

      System.out.println("Enter Year: ");
      int year = inputHelper.readInt(1900,2200);

      // Date
      Date purchaseDate = new Date(year, month-1, day);

      // at.campus02.model.Supplier ID
      System.out.println("Enter at.campus02.model.Supplier ID");
      scanner.nextLine();
      String id = inputHelper.readString();

      //If supplier field is empty


      // at.campus02.model.Item No
      System.out.println("Enter at.campus02.model.Item No : ");
      //sc.nextLine();

      //If input empty

      int itemno = inputHelper.readInt(1,Integer.MAX_VALUE);

      //at.campus02.model.Item quanitity
      System.out.println("Enter at.campus02.model.Item quantity : ");
      int quantity = inputHelper.readInt(1,Integer.MAX_VALUE);

      // at.campus02.model.Item object creation
      Item itemObject = new Item(itemno, quantity);

      //Payment mode
      System.out.println("Enter Payment Mode : ");
      String mode = inputHelper.readString();

      if (!(mode.equals("card") || mode.equals("cheque") || mode.equals("bank transfer"))) {
        throw new Exception("Unsuccessful. Mode of payment should be either of card / cheque / bank transfer");
      }

      //Payment Due Date
      System.out.println("*Payment Due Date*");
      System.out.println("Enter Day: ");
      String temp_due_day = inputHelper.readString();

      int due_day = Integer.parseInt(temp_due_day);

      System.out.println("Enter Month: ");
      String temp_due_month = inputHelper.readString();

      int due_month = Integer.parseInt(temp_due_month);

      System.out.println("Enter Year: ");
      String temp_due_year = inputHelper.readString();

      int due_year = Integer.parseInt(temp_due_year);

      Date purchaseDueDate = new Date(due_year, due_month, due_day);
      if (purchaseDueDate.before(purchaseDate)) {
        throw new Exception("Unsuccessful. at.campus02.model.Purchase date should be before the Payment Due Date.");
      }

      //total cost
      System.out.println("Enter total cost : ");
      scanner.nextLine();
      String tempcost = inputHelper.readString();


      double cost = Double.parseDouble(tempcost);

      // vat amount
      double vat = 0.05 * cost;

      // newPurchase Creation
      Purchase newPurchase = new Purchase(purchaseNo, trn_number, purchaseDate,
              supplierMap.get(id), itemObject, mode, purchaseDueDate, cost, vat);
      return newPurchase;
    }

  public  Purchase removePurchase() {
      System.out.println("*** Remove at.campus02.model.Purchase ***");
      System.out.println("Enter at.campus02.model.Purchase Number");

      scanner.nextLine();
      String number = inputHelper.readString();
      //If input empty


      try {
          int purchaseNo = Integer.parseInt(number);

          //Wrong at.campus02.model.Purchase Number format (should be a 3-digit number
          if (purchaseNo > 999) {

              return null;
          }

          if (purchaseMap.containsKey(purchaseNo)) {
              System.out.println("at.campus02.model.Purchase order No " + purchaseNo + " Deleted\n");
              return purchaseMap.get(purchaseNo);
          } else {
              System.out.println("Unsuccessful. Invalid purchase Number");
          }
      }
      //If String given for at.campus02.model.Purchase Number
      catch (NumberFormatException e) {
        System.out.println("Unsuccessful. Invalid purchase Number Format");
      }

      return null;
    }

  public void viewPurchase() {
      System.out.println("*** View at.campus02.model.Purchase ***");
      System.out.println("Enter at.campus02.model.Purchase Number");

      scanner.nextLine();
      String number = inputHelper.readString();

      //If input empty


      try {
          int temp = Integer.parseInt(number);

          //Wrong at.campus02.model.Purchase Number format (should be a 3-digit number
          if (temp > 999) {
              System.out.println("Unsuccessful. Invalid purchase Number");
              return;
          }

          if (purchaseMap.containsKey(temp)) {
              System.out.println("at.campus02.model.Purchase Information");
              System.out.println(purchaseMap.get(temp));
          } else {
              System.out.println("Unsuccessful. at.campus02.model.Purchase order does not exist");
          }
      }
      //If String given for at.campus02.model.Purchase Number
      catch (NumberFormatException e) {
        System.out.println("Unsuccessful. Invalid purchase Number Format");
      }
    } //Function View at.campus02.model.Purchase End
}
