package com.ies.schoolos.component.recruit;

import java.util.Collection;
import java.util.HashSet;

import com.ies.schoolos.container.Container;
import com.ies.schoolos.schema.RecruitStudentSchema;
import com.ies.schoolos.schema.SchoolSchema;
import com.ies.schoolos.schema.SessionSchema;
import com.ies.schoolos.type.Prename;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;

public class RecruitStudentExam extends TwinColSelect {
	private static final long serialVersionUID = 1L;

	/* จำนวนปัจจุบันของนักเรียนที่ถูกเลือก */
	int presentSelectSize = 0;
	/* จำนวนปัจจุบันของนักเรียนที่ถูกเลือกใหม่ เช่น เพิ่ม ลด */
	int newSelectSize = 0;
	
	private SQLContainer sContainer = Container.getInstance().getRecruitStudentContainer();
	private Collection<Object> rightData = new HashSet<Object>();
	
	public RecruitStudentExam() {
		buildMainLayout();
	}
	
	private void buildMainLayout(){
		setSizeFull();
        setNullSelectionAllowed(true);
        setMultiSelect(true);
        setImmediate(true);
        setLeftColumnCaption("ผู้สมัครสอบ");
        setRightColumnCaption("ห้องสอบ");

        addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				/* ข้อมูลของตารางทางขวาเก็บอยู่ในรูปของ HashMap<RowId> เช่น [60,61,62] แสดงถึงข้อมูลที่เลือกมี 3 ค่าจาก primary key */
				Collection<Object> selectData = (Collection<Object>)event.getProperty().getValue();
				/* จำนวนนักเรียนที่เหลือใหม่จากการ เพิ่ม ลด */
				newSelectSize = selectData.size();
				
				/* ตรวจสอบสถานะว่าเป็นการเพิ่ม หรือ ลด
				 *  กรณี ขนาดของเดิม มีค่าน้อยกว่าขนาดของใหม่ แสดงว่า เป็นการเพิ่มข้อมูลใหม่
				 *  กรณี ขนาดของข้อมูล มีค่ามากกว่าขนาดของใหม่ แสดงว่าเป็นการลบข้อมูล
				 *  */
				if(presentSelectSize < newSelectSize){
					rightData.clear();
					rightData.addAll(selectData);
				}else if(presentSelectSize > newSelectSize){
					removeData(selectData);
				}
				presentSelectSize = newSelectSize;	
			}
        });
        setData();
        selectData();
	}
	
	private void setData(){
		sContainer.addContainerFilter(new Equal(SchoolSchema.SCHOOL_ID,
				UI.getCurrent().getSession().getAttribute(SessionSchema.SCHOOL_ID)));
		setRows(sContainer.size());
		
		for(final Object itemId:sContainer.getItemIds()){
			Item item = sContainer.getItem(itemId);
			String caption = Prename.getNameTh(Integer.parseInt(item.getItemProperty(RecruitStudentSchema.PRENAME).getValue().toString())) + " " +
					item.getItemProperty(RecruitStudentSchema.FIRSTNAME).getValue().toString() + " " +
					item.getItemProperty(RecruitStudentSchema.LASTNAME).getValue().toString();
			 addItem(itemId);
	         setItemCaption(itemId, caption);		 
		}
		/* ลบ WHERE ออกจาก Query เพื่อป้องกันการค้างของคำสั่่งจากการทำงานอื่นที่เรียกตัวแปรไปใช้ */
		sContainer.removeAllContainerFilters();
	}
	
	/* จำนวนนักเรียนที่ถูกเลือก */
	private void selectData(){
		rightData.add(new RowId(64));
		setValue(rightData);
	}
	
	/* ลบข้อมูลที่นำออกจากข้อมูลที่ถูกเลือก */
	private void removeData(Collection<Object> remainData){
		Collection<Object> rightTemp = new HashSet<Object>();
		rightTemp.addAll(rightData);
		/* ตรวจสอบว่า ข้อมูบที่ถูกนำออกคือ pk อะไร เพื่อทำการลบจาก ฐานข้อมูล */
		for (Object itemId: rightData) {
			/* หากค่า pk ในชุดนักเรียนเดิม ไม่พบใน ชุดนักเรียนใหม่ที่ลบ แสดงถึง pk นั้นได้ถูกเลือกให้ลบ
			 * เช่น เดิ่ม มี [60,61] ใหม่ เป็น [60]
			 * แสดงว่า 61 ไปหาใน ใหม่ จะไม่พอ แสดงว่า 61 คือค่าที่ถูกลบ
			 *  */
			if(!remainData.contains(itemId)){
				rightTemp.remove(itemId);
			}
		}
		rightData.clear();
		rightData.addAll(rightTemp);
	}
}
