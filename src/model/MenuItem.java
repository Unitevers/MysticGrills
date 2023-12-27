package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItem {

	private int MenuItemID;
	private String MenuItemName;
	private String MenuItemDescription;
	private int MenuItemPrice;
	
	public MenuItem(int MenuItemID, String MenuItemName, String MenuItemDescription, int MenuItemPrice) {
		super();
		this.MenuItemID = MenuItemID;
		this.MenuItemName = MenuItemName;
		this.MenuItemDescription = MenuItemDescription;
		this.MenuItemPrice = MenuItemPrice;
	}
	
	public static ArrayList<MenuItem> getAllMenuItems(){
		ArrayList<MenuItem> menus = new ArrayList<MenuItem>();
		Connect con = Connect.getInstance();
		String query = "SELECT * FROM menuitems";
		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				Integer id = con.rs.getInt("MenuItemID");
				String name = con.rs.getString("MenuItemName");
				String description = con.rs.getString("MenuItemDescription");
				Integer price = con.rs.getInt("MenuItemPrice");
				menus.add(new MenuItem(id, name, description, price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menus;
	}
	
	public static void createMenuItem(String name, String description, Integer price) {
		String query = 
		String.format( "INSERT INTO menuitems(MenuItemName, MenuItemDescription, MenuItemPrice) VALUES(\'%s\', \'%s\', \'%d\')",
		name, description, price);	
		Connect con = Connect.getInstance();
		con.execUpdate(query);
		
	}
	
	public static void updateMenuItem(Integer id, String name, String description, Integer price) {
		String query = 
		String.format("UPDATE menuitems SET MenuItemName = \'%s\', MenuItemDescription = \'%s\', MenuItemPrice = \'%d\' WHERE MenuItemID = \'%d\'", 
		name, description, price, id);
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	public static void deleteMenuItem(Integer id) {
		String query = 
		String.format("DELETE FROM menuitems WHERE MenuItemID = \'%d\'", 
		id);
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	public static MenuItem getMenuItemById(int id) {
		String query = String.format("SELECT * FROM menuitems WHERE `MenuItemID`= '%d'", id);
		Connect con = Connect.getInstance();
		con.rs = con.execQuery(query);
		
		try {
			if(con.rs.next()) {
				String name = con.rs.getString("MenuItemName");
				String description = con.rs.getString("MenuItemDescription");
				Integer price = con.rs.getInt("MenuItemPrice");
				return new MenuItem(id, name, description, price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int getMenuItemID() {
		return MenuItemID;
	}
	public void setMenuItemID(int MenuItemID) {
		this.MenuItemID = MenuItemID;
	}
	public String getMenuItemName() {
		return MenuItemName;
	}
	public void setMenuItemName(String MenuItemName) {
		this.MenuItemName = MenuItemName;
	}
	public String getMenuItemDescription() {
		return MenuItemDescription;
	}
	public void setMenuItemDescription(String MenuItemDescription) {
		this.MenuItemDescription = MenuItemDescription;
	}
	public Integer getMenuItemPrice() {
		return MenuItemPrice;
	}
	public void setMenuItemPrice(Integer MenuItemPrice) {
		this.MenuItemPrice = MenuItemPrice;
	}

}
