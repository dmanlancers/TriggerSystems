package com.wetrig.dev.wetrig.POJO;

import java.util.ArrayList;

public class Group {

	private String Name;
	private  String Image;
	private String Id;
	private ArrayList<Child> Items;

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getName() {return Name;}

	public void setName(String name) {this.Name = name;}

	public String getImage() {
		return Image;
	}

	public void setImage(String Image) {
		this.Image = Image;
	}

	public ArrayList<Child> getItems() {
		return Items;
	}

	public void setItems(ArrayList<Child> Items) {
		this.Items = Items;
	}

}