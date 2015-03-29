package com.ies.schoolos.component.recruit;

import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.haijian.ExcelExporter;

import com.ies.schoolos.component.ui.ContentPage;
import com.ies.schoolos.container.Container;
import com.ies.schoolos.filter.TableFilterDecorator;
import com.ies.schoolos.filter.TableFilterGenerator;
import com.ies.schoolos.report.excel.RecruitStudentConfirmedToExcel;
import com.ies.schoolos.schema.RecruitStudentSchema;
import com.ies.schoolos.schema.SchoolSchema;
import com.ies.schoolos.schema.SessionSchema;
import com.ies.schoolos.type.ClassRange;
import com.ies.schoolos.type.Prename;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomTable.Align;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class RecruitToStudentView extends ContentPage {
	private static final long serialVersionUID = 1L;

	private int confirmQty = 0;
	private int unconfirmQty = 0;
	private SQLContainer rsContainer = Container.getInstance().getRecruitStudentContainer();

	private HorizontalLayout toolbar;
	private Button confirm;	
	private FilterTable  table;
	
	public RecruitToStudentView() {
		super("กำหนดรหัสนักเรียน");
		buildMainLayout();
	}
	
	private void buildMainLayout(){
		/* Toolbar */
		toolbar = new HorizontalLayout();
		toolbar.setSpacing(true);
		addComponent(toolbar);
		
		confirm = new Button("กำหนดรหัสอัตโนมัติ         ", FontAwesome.CHECK);
		confirm.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(UI.getCurrent(), "ยืนยันรหัสนักเรียน", "คุณต้องการกำหนดรหัสนักเรียนตามที่ตั้งค่าใว้ ในเมนูตั้งค่า ใช่หรือไม่?", "ตกลง", "ยกเลิก", 
						 new ConfirmDialog.Listener() {
					private static final long serialVersionUID = 1L;
					public void onClose(ConfirmDialog dialog) {
		                if (dialog.isConfirmed()) {
		                	
		                }
		            }
		        });
				
			}
		});
		toolbar.addComponent(confirm);
		
		ExcelExporter excelExporter = new ExcelExporter(new RecruitStudentConfirmedToExcel());
		excelExporter.setIcon(FontAwesome.FILE_EXCEL_O);
		excelExporter.setCaption("ส่งออกไฟล์ Excel");
		toolbar.addComponent(excelExporter);
		
		/* Content */
		table = new FilterTable();
		table.setSizeFull();
		table.setSelectable(true);
		table.setFooterVisible(true);        
        
        table.addContainerProperty(RecruitStudentSchema.RECRUIT_CODE, String.class, null);
		table.addContainerProperty(RecruitStudentSchema.CLASS_RANGE, String.class, null);
		table.addContainerProperty(RecruitStudentSchema.PRENAME, String.class, null);
		table.addContainerProperty(RecruitStudentSchema.FIRSTNAME, String.class, null);
		table.addContainerProperty(RecruitStudentSchema.LASTNAME, String.class, null);

		table.setFilterDecorator(new TableFilterDecorator());
		table.setFilterGenerator(new TableFilterGenerator());
        table.setFilterBarVisible(true);
        
		table.setColumnAlignment((Object)RecruitStudentSchema.RECRUIT_CODE,Align.CENTER);
		table.setColumnAlignment(RecruitStudentSchema.CLASS_RANGE,Align.CENTER);
		table.setColumnAlignment(RecruitStudentSchema.PRENAME,Align.CENTER);
		table.setColumnAlignment(RecruitStudentSchema.FIRSTNAME,Align.CENTER);
		table.setColumnAlignment(RecruitStudentSchema.LASTNAME,Align.CENTER);

		table.setColumnHeader(RecruitStudentSchema.RECRUIT_CODE, "หมายเลขสมัคร");
		table.setColumnHeader(RecruitStudentSchema.CLASS_RANGE,"ช่วงชั้น");
		table.setColumnHeader(RecruitStudentSchema.PRENAME, "ชื่อต้น");
		table.setColumnHeader(RecruitStudentSchema.FIRSTNAME, "ชื่อ");
		table.setColumnHeader(RecruitStudentSchema.LASTNAME, "สกุล");
		table.setVisibleColumns(
				RecruitStudentSchema.RECRUIT_CODE, 
				RecruitStudentSchema.CLASS_RANGE,
				RecruitStudentSchema.PRENAME,
				RecruitStudentSchema.FIRSTNAME, 
				RecruitStudentSchema.LASTNAME);
		
		addComponent(table);
        setExpandRatio(table, 1);

		setTableData();
		setFooterData();

	}
	
	/*สร้าง Layout ของข้อมูลเพื่อนำไปใส่ในตาราง*/
	private void setTableData(){
		/* ดึงจำนวนนักเรียนที่ไม่ยืนยันตัว */
		rsContainer.addContainerFilter(new And(
				new Equal(RecruitStudentSchema.SCHOOL_ID, UI.getCurrent().getSession().getAttribute(SessionSchema.SCHOOL_ID)),
				new Equal(RecruitStudentSchema.IS_CONFIRM, false)));
		unconfirmQty = rsContainer.size();
		rsContainer.removeAllContainerFilters();
		/* ดึงจำนวนนักเรียนที่ยืนยันตัว */ 
		rsContainer.addContainerFilter(new And(
				new Equal(SchoolSchema.SCHOOL_ID,UI.getCurrent().getSession().getAttribute(SessionSchema.SCHOOL_ID)),
				new Equal(RecruitStudentSchema.IS_CONFIRM, true)));
		confirmQty = rsContainer.size();
		
		for(final Object itemId:rsContainer.getItemIds()){
			/* ลบ WHERE ออกจาก Query เพื่อป้องกันการค้างของคำสั่่งจากการทำงานอื่นที่เรียกตัวแปรไปใช้ */
			rsContainer.removeAllContainerFilters();
			
			final Item studentItem = rsContainer.getItem(itemId);
			addDataItem(studentItem, itemId);
		}
		/* ลบ WHERE ออกจาก Query เพื่อป้องกันการค้างของคำสั่่งจากการทำงานอื่นที่เรียกตัวแปรไปใช้ */
		rsContainer.removeAllContainerFilters();
	}
	
	/*นำ Layout มาใส่ในแต่ละแถวของตาราง*/
	private void addDataItem(Item item,Object itemId){
		table.addItem(new Object[] {
				item.getItemProperty(RecruitStudentSchema.RECRUIT_CODE).getValue(), 
				ClassRange.getNameTh(Integer.parseInt(item.getItemProperty(RecruitStudentSchema.CLASS_RANGE).getValue().toString())),
				Prename.getNameTh(Integer.parseInt(item.getItemProperty(RecruitStudentSchema.PRENAME).getValue().toString())), 
				item.getItemProperty(RecruitStudentSchema.FIRSTNAME).getValue(), 
				item.getItemProperty(RecruitStudentSchema.LASTNAME).getValue()
		},itemId);
	}
	/*นำจำนวนที่นับ มาใส่ค่าในส่วนท้ายตาราง*/
	private void setFooterData(){
		table.setColumnFooter(RecruitStudentSchema.RECRUIT_CODE, "ทั้งหมด: "+ (confirmQty+unconfirmQty) + " คน</br>"
				+ "จำนวนยืนยันตัว: " + confirmQty + " คน</br>"
				+ "จำนวนไม่่ยืนยันตัว: " + unconfirmQty +" คน");
	}
}
