package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductServices {


	public static  Connection getConnection() {
		Connection con=null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/Productdb","root","Kdilli123.");
		} 
		catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}


	public void addproduct() throws SQLException {

		Connection connection=ProductServices.getConnection();
		String dynamicQuery="insert into Product values(?,?,?,?)";
		PreparedStatement pst=connection.prepareStatement(dynamicQuery);
		Scanner sc=new Scanner(System.in);


		System.out.println("Enter the ProductId");
		pst.setInt(1,sc.nextInt());
		System.out.println("Enter the ProductName");

		pst.setString(2,sc.next());
		System.out.println("Enter the ProductBrand");
		pst.setString(3,sc.next());
		System.out.println("Enter the ProductPrice");
		pst.setInt(4,sc.nextInt());

		pst.executeUpdate();
		pst.close();
		connection.close();
		System.out.println("insterded successfully");


	}


	public static void getProductById(int id) {

		Connection con=getConnection();

		String sql="select *  from productdb.product  where productId=?";

		try {

			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet res= ps.executeQuery();

			while(res.next()) {
				int Id=res.getInt("productId");
				String name=res.getString("productName");
				String brand=res.getString("productBrand");
				int price=res.getInt("productPrice");


				System.out.println(Id+" "+name+ " "+brand+" "+price);
			}
			ps.close();
			con.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public  static void  getProductByName(String name) {


		Connection con=getConnection();

		String sql="select *  from product  where productName=?";

		try {

			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet res= ps.executeQuery();

			while(res.next()) {

				int Id=res.getInt("productId");
				String nam=res.getString("productName");
				String brand=res.getString("productBrand");
				int price=res.getInt("productPrice");


				System.out.println(Id+" "+nam+ " "+brand+" "+price);
			}
			ps.close();
			con.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}




	}

	public static  void getProductsBetweenPrice(int price1,int price2) {

		Connection con=getConnection();

		String sqlQuery="select * from product where productPrice between ? and ?";

		try {
			PreparedStatement ps=con.prepareStatement(sqlQuery);

			ps.setInt(1, price1);
			ps.setInt(2, price2);

			ResultSet res=ps.executeQuery();

			while(res.next()) {
				int Id=res.getInt("productId");
				String nam=res.getString("productName");
				String brand=res.getString("productBrand");
				int price=res.getInt("productPrice");


				System.out.println(Id+" "+nam+ " "+brand+" "+price);


			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}




	}public static void getAllProducts() {

		Connection con=getConnection();

		String sql="select *  from product";

		try {

			PreparedStatement ps= con.prepareStatement(sql);


			ResultSet res= ps.executeQuery();

			while(res.next()) {

				int Id=res.getInt("productId");
				String nam=res.getString("productName");
				String brand=res.getString("productBrand");
				int price=res.getInt("productPrice");


				System.out.println(Id+" "+nam+ " "+brand+" "+price);
			}


			ps.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}




	}


	public static void updateProductPriceById(int id ,int price) {

		String sql="update product set productPrice=? where productId=?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","Kdilli123.");
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, id);

			int res=ps.executeUpdate();

			if(res==1) {
				System.out.println("updated sucessfully");

			}
			else {

				System.out.println("updation failed");
			}


			ps.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void deleteProductById(int id) {

		String sql=" delete  from product where productId=?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","Kdilli123.");

			PreparedStatement ps= con.prepareStatement(sql);

			ps.setInt(1, id);

			int res=ps.executeUpdate();

			if(res==1) {
				System.out.println("deleted sucessfully");

			}
			else {

				System.out.println("deleation failed");
			}


			ps.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

public static void deleteProductByName(String name) {

		String sql=" delete  from product where productName=?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","Kdilli123.");

			PreparedStatement ps= con.prepareStatement(sql);

			ps.setString(1, name);

			int res=ps.executeUpdate();

			if(res==1) {
				System.out.println("deleted sucessfully");

			}
			else {

				System.out.println("deleation failed");
			}


			ps.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

}
