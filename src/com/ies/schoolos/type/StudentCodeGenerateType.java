package com.ies.schoolos.type;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class StudentCodeGenerateType  extends IndexedContainer{

	private static final long serialVersionUID = 1L;
	
	private static String[] aliveStatuses = {"อัตโนมัติ","กำหนดเอง"};

	public StudentCodeGenerateType() {
		initContainer();
	}
 
	@SuppressWarnings("unchecked")
	private void initContainer(){
	   addContainerProperty("name", String.class,null);
	   for (int i = 0; i < aliveStatuses.length; i++) {
	        Item item = addItem(i);
	        item.getItemProperty("name").setValue(aliveStatuses[i]);
	   }
	}
	
	public static String getNameTh(int index){
		return aliveStatuses[index];
	}

}
