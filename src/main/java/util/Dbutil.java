package util;

import config.DbConfig;
import model.Region;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dbutil {
    public static void deleteRegion() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from region");
        if (!resultSet.next()) {
            System.out.println("Can not be found region!!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a region that you want to delete: ");
                String region = scanner.next();
                CallableStatement callableStatement = connection.prepareCall("{call delete_region(?,?)}");
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.setString(1, region);
                callableStatement.execute();
                String s = callableStatement.getString(2);
                System.out.println(s);
                connection.close();
                callableStatement.close();
            }catch (PSQLException e){
                System.out.println("TABLE REGION VIOLATES FOREIGN KEY CONSTRAINT ! ! !");

            }
        }
    }

    public static void editRegion() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from region");
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NOT FOUND!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the region that you want to edit: ");
            String oldRegion = scanner.next();
            System.out.print("Enter the new name of the region: ");
            String newRegion = scanner.next();
            CallableStatement callableStatement = connection.prepareCall("{call edit_region(?,?,?)}");
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.setString(1, oldRegion);
            callableStatement.setString(2, newRegion);
            callableStatement.execute();
            String message = callableStatement.getString(3);
            System.out.println(message);
            callableStatement.close();
            connection.close();


        }
    }

    public static void addRegion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the region that you want to add: ");
        String addRegion = scanner.next();
        Connection connection = DbConfig.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call add_region(?,?)}");
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.setString(1, addRegion);
        callableStatement.execute();
        String message = callableStatement.getString(2);
        System.out.println(message);
        callableStatement.close();
        connection.close();
    }

    public static void getRegionList() throws SQLException {
        String sql = "select * from region";
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NOT FOUND ! ! !");
        } else {
            while (resultSet.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("NAME: " + resultSet.getString(2));
            }
        }
    }

    public static void addDistrict() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from region");
        if (!resultSet.next()) {
            System.out.println("First you have to add region!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the name of the district that you want to add: ");
                String addDistrict = scanner.next();
                System.out.print("Enter the id of region: ");
                int regionId = scanner.nextInt();
                CallableStatement callableStatement = connection.prepareCall("{call add_district(?,?)}");
                callableStatement.setString(1, addDistrict);
                callableStatement.setInt(2, regionId);
                callableStatement.execute();
                System.out.println("Successfully added ! ! !");
                callableStatement.close();
                connection.close();


            }catch (PSQLException e){
                System.out.println("TABLE DISTRICT VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }
        }
    }

    public static void addCompany() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from district");
        if (!resultSet.next()) {
            System.out.println("First you have to add district!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the name of the company that you want to add: ");
                String addCompany = scanner.next();
                System.out.print("Enter the id of district: ");
                int districtId = scanner.nextInt();
                CallableStatement callableStatement = connection.prepareCall("{call add_company(?,?,?)}");
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.setString(1, addCompany);
                callableStatement.setInt(2, districtId);
                callableStatement.execute();
                System.out.println(callableStatement.getString(3));
                callableStatement.close();
                connection.close();

            }catch (PSQLException e){
                System.out.println("TABLE COMPANY VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }

        }
    }

    public static void addCategory() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from company");
        if (!resultSet.next()) {
            System.out.println("First you have to add company!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the name of the category that you want to add: ");
                String addCategory = scanner.next();
                System.out.print("Enter the id of company: ");
                int companyId = scanner.nextInt();
                CallableStatement callableStatement = connection.prepareCall("{call add_category(?,?,?)}");
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.setString(1, addCategory);
                callableStatement.setInt(2, companyId);
                callableStatement.execute();
                System.out.println(callableStatement.getString(3));
                callableStatement.close();
                connection.close();

            }catch (PSQLException e){
                System.out.println("TABLE CATEGORY VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }

        }
    }

    public static void addProduct() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category");
        if (!resultSet.next()) {
            System.out.println("First you have to add category!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the name of the product that you want to add: ");
                String addProduct = scanner.next();
                System.out.print("Enter the id of category: ");
                int productId = scanner.nextInt();
                CallableStatement callableStatement = connection.prepareCall("{call add_product(?,?,?)}");
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.setString(1, addProduct);
                callableStatement.setInt(2, productId);
                callableStatement.execute();
                System.out.println(callableStatement.getString(3));
                callableStatement.close();
                connection.close();
            }catch (PSQLException e){
                System.out.println("TABLE PRODUCT VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }
        }
    }
    public static void deleteDistrict() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from district");
        if (!resultSet.next()) {
            System.out.println("Can not be found district!!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a district that you want to delete: ");
                String delete = scanner.next();
                CallableStatement callableStatement = connection.prepareCall("{call delete_district(?,?)}");
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.setString(1, delete);
                callableStatement.execute();
                String s = callableStatement.getString(2);
                System.out.println(s);
                connection.close();
                callableStatement.close();

            }catch (PSQLException e){
                System.out.println("TABLE DISTRICT VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }
        }
    }

    public static void deleteCompany() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from company");
        if (!resultSet.next()) {
            System.out.println("Can not be found company!!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a company that you want to delete: ");
                String delete = scanner.next();
                CallableStatement callableStatement = connection.prepareCall("{call delete_company(?,?)}");
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.setString(1, delete);
                callableStatement.execute();
                String s = callableStatement.getString(2);
                System.out.println(s);
                connection.close();
                callableStatement.close();

            }catch (PSQLException e){
                System.out.println("TABLE COMPANY VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }
        }
    }

    public static void deleteCategory() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category");
        if (!resultSet.next()) {
            System.out.println("Can not be found category!!!!");
        } else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a category that you want to delete: ");
                String delete = scanner.next();
                CallableStatement callableStatement = connection.prepareCall("{call delete_category(?,?)}");
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.setString(1, delete);
                callableStatement.execute();
                String s = callableStatement.getString(2);
                System.out.println(s);
                connection.close();
                callableStatement.close();
            }catch (PSQLException e){
                System.out.println("TABLE CATEGORY VIOLATES FOREIGN KEY CONSTRAINT ! ! !");

            }

        }
    }

    public static void deleteProduct() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        if (!resultSet.next()) {
            System.out.println("Can not be found product!!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a product that you want to delete: ");
            String delete = scanner.next();
            CallableStatement callableStatement = connection.prepareCall("{call delete_product(?,?)}");
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.setString(1, delete);
            callableStatement.execute();
            String s = callableStatement.getString(2);
            System.out.println(s);
            connection.close();
            callableStatement.close();

        }
    }

    public static void editDistrict() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from district");
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NOT FOUND!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the old name: ");
            String editName1 = scanner.next();
            System.out.print("Enter the new name: ");
            String editName2 = scanner.next();
            System.out.print("Enter the  id of region: ");
            int id = scanner.nextInt();
            CallableStatement callableStatement = connection.prepareCall("{call edit_district(?,?,?,?)}");
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.setString(1, editName1);
            callableStatement.setString(2, editName2);
            callableStatement.setInt(3, id);
            callableStatement.execute();
            String message = callableStatement.getString(4);
            System.out.println(message);
            callableStatement.close();
            connection.close();
        }
    }

    public static void editCompany() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from company");
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NOT FOUND!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the old name: ");
            String editName1 = scanner.next();
            System.out.print("Enter the new name: ");
            String editName2 = scanner.next();
            System.out.print("Enter the  id of district: ");
            int id = scanner.nextInt();
            CallableStatement callableStatement = connection.prepareCall("{call edit_company(?,?,?,?)}");
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.setString(1, editName1);
            callableStatement.setString(2, editName2);
            callableStatement.setInt(3, id);
            callableStatement.execute();
            String message = callableStatement.getString(4);
            System.out.println(message);
            callableStatement.close();
            connection.close();

        }

    }

    public static void editCategory() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category");
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NOT FOUND!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the old name: ");
            String editName1 = scanner.next();
            System.out.print("Enter the new name: ");
            String editName2 = scanner.next();
            System.out.print("Enter the  id of company: ");
            int id = scanner.nextInt();
            CallableStatement callableStatement = connection.prepareCall("{call edit_category(?,?,?,?)}");
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.setString(1, editName1);
            callableStatement.setString(2, editName2);
            callableStatement.setInt(3, id);
            callableStatement.execute();
            String message = callableStatement.getString(4);
            System.out.println(message);
            callableStatement.close();
            connection.close();

        }

    }

    public static void editProduct() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NOT FOUND!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the old name: ");
            String editName1 = scanner.next();
            System.out.print("Enter the new name: ");
            String editName2 = scanner.next();
            System.out.print("Enter the  id of category: ");
            int id = scanner.nextInt();
            CallableStatement callableStatement = connection.prepareCall("{call edit_product(?,?,?,?)}");
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.setString(1, editName1);
            callableStatement.setString(2, editName2);
            callableStatement.setInt(3, id);
            callableStatement.execute();
            String message = callableStatement.getString(4);
            System.out.println(message);
            callableStatement.close();
            connection.close();

        }

    }

    public static void getDistrictList() throws SQLException {
        String sql = "select * from district";
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("Empty !  ! !");
        } else {
            while (resultSet.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("NAME: " + resultSet.getString(2));
                System.out.println("Region_id: " + resultSet.getString(3));

            }
        }
    }

    public static void getCompanyList() throws SQLException {
        String sql = "select * from company";
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("Empty ! ! ! ");

        } else {
            while (resultSet.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("NAME: " + resultSet.getString(2));
                System.out.println("District_id: " + resultSet.getString(3));

            }
        }
    }

    public static void getCategoryList() throws SQLException {
        String sql = "select * from category";
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("Empty ! ! ! ");
        } else {
            while (resultSet.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("NAME: " + resultSet.getString(2));
                System.out.println("Company_id: " + resultSet.getString(3));

            }
        }
    }

    public static void getProductList() throws SQLException {
        String sql = "select * from product";
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("Empty ! ! ! ");
        } else {
            while (resultSet.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("NAME: " + resultSet.getString(2));
                System.out.println("Category_id: " + resultSet.getString(3));

            }
        }
    }
    public static void wipeRegion() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from region");
        if (!resultSet.next()) {
            System.out.println("NOT FOUND!!!");
        } else {
            try {
                CallableStatement callableStatement = connection.prepareCall("{call wipe_region(?)}");
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.execute();
                String message = callableStatement.getString(1);
                System.out.println(message);
                callableStatement.close();
                connection.close();

            }catch (PSQLException e){
                System.out.println("TABLE PRODUCT VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }
        }
    }
    public static void wipeDistrict() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from district");
        if (!resultSet.next()) {
            System.out.println("NOT FOUND!!!");
        } else {
            try {
                CallableStatement callableStatement = connection.prepareCall("{call wipe_district(?)}");
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.execute();
                String message = callableStatement.getString(1);
                System.out.println(message);
                callableStatement.close();
                connection.close();

            }catch (PSQLException e){
                System.out.println("TABLE DISTRICT VIOLATES FOREIGN KEY CONSTRAINT ! ! ! ");
            }
        }
    }
    public static void wipeCompany() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from company");
        if (!resultSet.next()) {
            System.out.println("NOT FOUND!!!");
        } else {
            try {
                CallableStatement callableStatement = connection.prepareCall("{call wipe_company(?)}");
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.execute();
                String message = callableStatement.getString(1);
                System.out.println(message);
                callableStatement.close();
                connection.close();

            }catch (PSQLException e){
                System.out.println("TABLE COMPANY VIOLATES FOREIGN KEY CONSTRAINT");
            }
        }
    }
    public static void wipeCategory() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from category");
        if (!resultSet.next()) {
            System.out.println("NOT FOUND!!!");
        } else {
            try {
                CallableStatement callableStatement = connection.prepareCall("{call wipe_category(?)}");
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.execute();
                String message = callableStatement.getString(1);
                System.out.println(message);
                callableStatement.close();
                connection.close();
            }catch (PSQLException e){
                System.out.println("TABLE CATEGORY VIOLATES FOREIGN KEY CONSTRAINT ! ! !");
            }
        }

    }
  public static void wipeProduct() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        if (!resultSet.next()) {
            System.out.println("NOT FOUND!!!");
        } else {
            try {
                CallableStatement callableStatement = connection.prepareCall("{call wipe_product(?)}");
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.execute();
                String message = callableStatement.getString(1);
                System.out.println(message);
                callableStatement.close();
                connection.close();
            }catch (PSQLException e){
                System.out.println("TABLE PRODUCT VIOLATES FOREIGN KEY CONSTRAINT ! ! ! ");
            }
        }

    }
    public static void getHistory() throws SQLException {
        String sql = "select * from history";
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("NO HISTORY ! ! !");
        } else {
            while (resultSet.next()) {
                System.out.println();
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("NAME OF TABLE: " + resultSet.getString(2));
                System.out.println("OPERATION: " + resultSet.getString(3));
                System.out.println("OPERATION TIME: " + resultSet.getString(4));
                System.out.println("DATA: " + resultSet.getString(5));

            }
        }

    }
    public static void wipeHistory() throws SQLException {
        Connection connection = DbConfig.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from history");
        if (!resultSet.next()) {
            System.out.println("NOT FOUND!!!");
        } else {
            CallableStatement callableStatement = connection.prepareCall("{call wipe_history(?)}");
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.execute();
            String message = callableStatement.getString(1);
            System.out.println(message);
            callableStatement.close();
            connection.close();

        }

    }


}

