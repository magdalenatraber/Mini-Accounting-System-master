import at.campus02.input.InputPurchase;
import at.campus02.input.InputSupplier;
import at.campus02.model.Item;
import at.campus02.model.Purchase;
import at.campus02.model.Supplier;

import java.util.*;

public class MainClass {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    Item i1 = new Item(100, 20);

    Supplier supp1 = new Supplier("supp1001", "ABC", "0502375262", "abc@abc.com", 1001, 789);
    Purchase pur1 = new Purchase(100, 2000, new Date(2002, 12, 23), supp1, i1, "Card", new Date(2013, 26, 12), 800.36, 10.45);

    Map <String, Supplier > suppliers = new HashMap<> ();
    Map <Integer, Purchase> purchases = new HashMap<> ();
    purchases.put(pur1.getPurchaseNo(), pur1);
    suppliers.put(supp1.getSupplierId(), supp1);

    System.out.println("""
		       Accounting System
		       *******Purchases*******
		       \t1. Add purchase
		       \t2. Remove purchase
		       \t3. View purchase
		       *******at.campus02.model.Supplier*******
		       \t4. Add at.campus02.model.Supplier
		       \t5. Remove at.campus02.model.Supplier
		       \t6. View at.campus02.model.Supplier
		       """);
      int choice = 0;

      InputPurchase inputPurchase = new InputPurchase(sc);
      InputSupplier inputSupplier = new InputSupplier(sc);
      while (true) {
        System.out.println("Enter your choice (1-6)");
        choice = sc.nextInt();
        switch (choice) {
        case 1: {
          try {
            Purchase p1 = inputPurchase.addPurchase(purchases, suppliers);
            purchases.put(p1.getPurchaseNo(),p1); // remove if not call by refernece
            break;
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        }

        case 2:
          int index = inputPurchase.removePurchase(purchases);
          if (index == -1) {
            //System.out.println("Unsuccessful. at.campus02.model.Purchase order does not exist");
          } else purchases.remove(index);
          break;

        case 3:
          inputPurchase.viewPurchase(purchases);
          break;

        case 4: {
          try {
            Supplier s1 = inputSupplier.addSupplier(suppliers);
            suppliers.put(s1.getSupplierId(),s1); // remove if not call by refernece
            break;
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        }

        case 5:
          int pos = inputSupplier.deleteSupplier(suppliers);
          if (pos == -1) {
            System.out.println("at.campus02.model.Supplier Doesnt Exist");
          } else suppliers.remove(pos);
          break;

        case 6:
          inputSupplier.viewSupplier(suppliers);
          break;

        default:
          System.out.println("Enter a value between 1-6");
          System.exit(0);
        } //switch end
      } //while end
    } //Main End

} //Class end
