import config.DbConfig;
import model.Region;
import util.Dbutil;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our database system");
        while (true) {
            System.out.println();
            System.out.println("1=>WORK WITH REGION  \n2=>WORK WITH DISTRICT \n3=>WORK WITH COMPANY " +
                    "\n4=>WORK WITH CATEGORY \n5=>WORK WITH PRODUCT \n6=>HISTORY \n7=>CLEAR HISTORY \n8=>EXIT");
            System.out.print("=> ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    loop:
                    while (true) {
                        System.out.println();
                        System.out.println("1=>ADD REGION \n2=>EDIT REGION \n3=>DELETE REGION \n4=>LIST OF REGIONS \n5=>WIPE ALL DATA FROM REGION\n6=>MAIN MENU");
                        System.out.print("=> ");
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                Dbutil.addRegion();
                                break;
                            case 2:
                                Dbutil.editRegion();
                                break;
                            case 3:
                                Dbutil.deleteRegion();
                                break;
                            case 4:
                                Dbutil.getRegionList();
                                break;
                            case 5:
                                Dbutil.wipeRegion();
                                break;
                            case 6:
                                break loop;


                        }

                    }
                    break;
                case 2:
                    loop:
                    while (true) {
                        System.out.println();
                        System.out.println("1=>ADD DISTRICT \n2=>EDIT DISTRICT \n3=>DELETE DISTRICT \n4=>LIST OF DISTRICTS \n5=>WIPE ALL DATA FROM DISTRICT\n6=>MAIN MENU");
                        System.out.print("=> ");
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                Dbutil.addDistrict();
                                break;
                            case 2:
                                Dbutil.editDistrict();
                                break;
                            case 3:
                                Dbutil.deleteDistrict();
                                break;
                            case 4:
                                Dbutil.getDistrictList();
                                break;
                            case 5:
                                Dbutil.wipeDistrict();
                                break;
                            case 6:
                                break loop;
                        }

                    }
                    break;
                case 3:
                    loop:
                    while (true) {
                        System.out.println();
                        System.out.println("1=>ADD COMPANY \n2=>EDIT COMPANY \n3=>DELETE COMPANY \n4=>LIST OF COMPANY \n5=>WIPE ALL DATA FROM DISTRICT\n6=>MAIN MENU");
                        System.out.print("=> ");
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                Dbutil.addCompany();
                                break;
                            case 2:
                                Dbutil.editCompany();
                                break;
                            case 3:
                                Dbutil.deleteCompany();
                                break;
                            case 4:
                                Dbutil.getCompanyList();
                                break;
                            case 5:
                                Dbutil.wipeCompany();
                                break ;
                            case 6:
                                break loop;
                        }

                    }
                    break;
                case 4:
                    loop:
                    while (true) {
                        System.out.println();
                        System.out.println("1=>ADD CATEGORY \n2=>EDIT CATEGORY \n3=>DELETE CATEGORY \n4=>LIST OF CATEGORIES \n5=>WIPE ALL DATA FROM DISTRICT\n6=>MAIN MENU");
                        System.out.print("=> ");
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                Dbutil.addCategory();
                                break;
                            case 2:
                                Dbutil.editCategory();
                                break;
                            case 3:
                                Dbutil.deleteCategory();
                                break;
                            case 4:
                                Dbutil.getCategoryList();
                                break;
                            case 5:
                                Dbutil.wipeCategory();
                                break ;
                            case 6:
                                break loop;
                        }

                    }
                    break;
                case 5:
                    loop:
                    while (true) {
                        System.out.println();
                        System.out.println("1=>ADD PRODUCT \n2=>EDIT PRODUCT \n3=>DELETE PRODUCT \n4=>LIST OF PRODUCTS \n5=>WIPE ALL DATA FROM DISTRICT\n6=>MAIN MENU");
                        System.out.print("=> ");
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                Dbutil.addProduct();
                                break;
                            case 2:
                                Dbutil.editProduct();
                                break;
                            case 3:
                                Dbutil.deleteProduct();
                                break;
                            case 4:
                                Dbutil.getProductList();
                                break;
                            case 5:
                                Dbutil.wipeProduct();
                                break ;
                            case 6:
                                break loop;

                        }
                    }
                    break;
                case 6:
                    Dbutil.getHistory();
                    break;
                case 7:
                    Dbutil.wipeHistory();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("You entered wrong option!!!!");
                    break;
            }
        }
    }
}