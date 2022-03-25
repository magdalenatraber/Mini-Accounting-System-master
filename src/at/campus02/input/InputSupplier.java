package at.campus02.input;

import at.campus02.model.Supplier;

import java.util.Map;
import java.util.Scanner;

public class InputSupplier {
    private Scanner scanner;
    private Map<String, Supplier> supplierMap;
    private final InputHelper inputHelper;

    public InputSupplier(Scanner scanner, Map<String, Supplier> supplierMap, InputHelper inputHelper) {
        this.scanner = scanner;
        this.supplierMap = supplierMap;
        this.inputHelper = inputHelper;
    }

    public Supplier addSupplier() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the supplier details: ");
        System.out.println("Enter supplierID: ");
        String suppID = inputHelper.readString();


            if (supplierMap.containsKey(suppID)) {
                throw new RuntimeException("Error: at.campus02.model.Supplier not added, SupplierID already exists in the system.");
            }


        //If supplier field is empty


        System.out.println("Enter companyName: ");

        String companyName = inputHelper.readString();


        System.out.println("Enter Contact Number: ");
        String number = inputHelper.readString();



        if (!(number.substring(0, 2).equals("05")) || number.length() != 10) {
            throw new Exception("Error: at.campus02.model.Supplier not added, Number needs to be of the format “05XXXXXXXX” where X are numbers.");
        }

        System.out.println("Enter email: ");
        String email = inputHelper.readString();



        int atCount = 0;
        int Atindex = 0;
        boolean domainfound = false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                atCount++;
                Atindex = i;
                break;

            }

        }

        if (atCount != 1 || !(email.substring(Atindex, email.length()).contains(".")))
            throw new Exception("Error: at.campus02.model.Supplier not added, email isn’t in the correct format.The prefix appears to the left of the @ symbol. The domain appears to the right of the @ symbol");

        System.out.println("Enter tradeLicenseNo: ");
        int tradeLicenseNo = sc.nextInt();

        String texttradeLicense = Integer.toString(tradeLicenseNo);

        if (texttradeLicense.isEmpty() || texttradeLicense.equals(" ")) {
            throw new Exception("Error: at.campus02.model.Supplier not added, Trade Licence No. is left blank.");
        }

        int noOfDigitsTL = String.valueOf(tradeLicenseNo).length();

        if (noOfDigitsTL != 6)
            throw new Exception("Error: at.campus02.model.Supplier not added, Trade License number needs to be a 6 digit number.");

        System.out.println("Enter VAT registration Number: ");
        int vatRn = sc.nextInt();
        String vattext = Integer.toString(vatRn);

        if (vattext.isEmpty() || vattext.equals(" ")) {
            throw new Exception("Error: at.campus02.model.Supplier not added, VAT Registration Number is left blank");

        }

        int noOfDigitsVAT = String.valueOf(vatRn).length();

        if (noOfDigitsVAT != 7) {
            throw new Exception("Error: at.campus02.model.Supplier not added, VAT RN needs to be a 7 digit number.");
        }

        Supplier s1 = new Supplier(suppID, companyName, number, email, tradeLicenseNo, vatRn);
        supplierMap.put(s1.getSupplierId(), s1);
        System.out.println("at.campus02.model.Supplier added successfully");
        return s1;

    } //Function Add Supp End

    public Supplier deleteSupplier() {
        System.out.println("*** delete at.campus02.model.Supplier ***");
        System.out.println("Enter at.campus02.model.Supplier ID");
        scanner.nextLine();
        String id = inputHelper.readString();


        if (supplierMap.containsKey(id)) {
            System.out.println("at.campus02.model.Supplier successfully deleted");
            return supplierMap.get(id);
        } else {
            System.out.println("at.campus02.model.Supplier does not exist in the database so it isn't deleted.");
            return null;
        } //Function Delete Supp End
    }

    public void viewSupplier() {
        System.out.println("*** View at.campus02.model.Supplier ***");
        System.out.println("Enter at.campus02.model.Supplier ID");
        scanner.nextLine();
        String id = inputHelper.readString();



        if (supplierMap.containsKey(id)) {
            System.out.println("at.campus02.model.Supplier Information");
            System.out.println(supplierMap.get(id));
        } else {
            System.out.println("Read/View Unsuccessful: at.campus02.model.Supplier ID does not exist");
        }
    }//ViewSupplier End
}
