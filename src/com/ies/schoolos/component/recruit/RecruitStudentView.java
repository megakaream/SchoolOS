package com.ies.schoolos.component.recruit;

import java.util.ArrayList;
import java.util.Date;

import com.ies.schoolos.container.Container;
import com.ies.schoolos.report.RecruitStudentReport;
import com.ies.schoolos.schema.RecruitStudentFamilySchema;
import com.ies.schoolos.schema.RecruitStudentSchema;
import com.ies.schoolos.schema.SessionSchema;
import com.ies.schoolos.schema.view.StatRecruitStudentCodeSchema;
import com.ies.schoolos.type.AliveStatus;
import com.ies.schoolos.type.Blood;
import com.ies.schoolos.type.City;
import com.ies.schoolos.type.ClassRange;
import com.ies.schoolos.type.District;
import com.ies.schoolos.type.FamilyStatus;
import com.ies.schoolos.type.Gender;
import com.ies.schoolos.type.GuardianRelation;
import com.ies.schoolos.type.Nationality;
import com.ies.schoolos.type.Occupation;
import com.ies.schoolos.type.Parents;
import com.ies.schoolos.type.PeopleIdType;
import com.ies.schoolos.type.Postcode;
import com.ies.schoolos.type.Prename;
import com.ies.schoolos.type.Province;
import com.ies.schoolos.type.Race;
import com.ies.schoolos.type.Religion;
import com.ies.schoolos.utility.Notification;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate.RowIdChangeEvent;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate.RowIdChangeListener;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

public class RecruitStudentView extends TabSheet {
	private static final long serialVersionUID = 1L;
	
	private boolean editMode = false;
	private boolean isNewGuardian = false;
	
	private final Container container = Container.getInstance();
	
	private ArrayList<Object> rowIds = new ArrayList<Object>();
	
	private Object fatherId;
	private Object motherId;
	private Object guardianId;
	private Object studentId;
		
	private FieldGroup studentBinder;
	private FieldGroup fatherBinder;
	private FieldGroup motherBinder;
	private FieldGroup guardianBinder;

	private Item studentItem;
	private Item fatherItem;
	private Item motherItem;
	private Item guardianItem;
		
	//LAYOUT
	private FormLayout generalForm;
	private ComboBox classRange;
	private OptionGroup peopleIdType;
	private TextField peopleId;
	private ComboBox prename;
	private TextField firstname;
	private TextField lastname;
	private TextField firstnameNd;
	private TextField lastnameNd;
	private TextField nickname;
	private OptionGroup gender;
	private ComboBox religion;
	private ComboBox race;
	private ComboBox nationality;
	private PopupDateField birthDate;
	private ComboBox blood;
	private TextField height;
	private TextField weight;
	private TextField congenitalDisease;
	private TextField interested;
	private TextField siblingQty;
	private TextField siblingSequence;
	private TextField siblingInSchoolQty;
	private Button graduatedNext;

	private FormLayout graduatedForm;
	private TextField graduatedSchool;
	private ComboBox graduatedSchoolProvinceId;
	private TextField graduatedGpa;
	private TextField graduatedYear;
	private Button generalBack;
	private Button addressNext;
	
	private FormLayout addressForm;
	private TextField tel;
	private TextField mobile;
	private TextField email;
	private TextArea currentAddress;
	private ComboBox currentCity;
	private ComboBox currentDistrict;
	private ComboBox currentProvinceId;
	private ComboBox currentPostcode;
	private Button graduatedBack;
	private Button fatherNext;
	
	private FormLayout fatherForm;
	private OptionGroup fPeopleIdType;
	private TextField fPeopleid;
	private ComboBox fPrename;
	private TextField fFirstname;
	private TextField fLastname;
	private TextField fFirstnameNd;
	private TextField fLastnameNd;
	private OptionGroup fGender;
	private ComboBox fReligion;
	private ComboBox fRace;
	private ComboBox fNationality;
	private PopupDateField fBirthDate;
	private TextField fTel;
	private TextField fMobile;
	private TextField fEmail;
	private TextField fSalary;
	private ComboBox fAliveStatus;
	private ComboBox fOccupation;
	private TextArea fJobAddress;
	private TextArea fCurrentAddress;
	private ComboBox fCurrentCity;
	private ComboBox fCurrentDistrict;
	private ComboBox fCurrentProvinceId;
	private ComboBox fCurrentPostcode;
	private Button addressBack;
	private Button motherNext;
	
	private FormLayout motherForm;
	private OptionGroup mPeopleIdType;
	private TextField mPeopleid;
	private ComboBox mPrename;
	private TextField mFirstname;
	private TextField mLastname;
	private TextField mFirstnameNd;
	private TextField mLastnameNd;
	private OptionGroup mGender;
	private ComboBox mReligion;
	private ComboBox mRace;
	private ComboBox mNationality;
	private PopupDateField mBirthDate;	
	private TextField mTel;
	private TextField mMobile;
	private TextField mEmail;
	private TextField mSalary;
	private ComboBox mAliveStatus;
	private ComboBox mOccupation;
	private TextArea mJobAddress;
	private TextArea mCurrentAddress;
	private ComboBox mCurrentCity;
	private ComboBox mCurrentDistrict;
	private ComboBox mCurrentProvinceId;
	private ComboBox mCurrentPostcode;
	private ComboBox familyStatus;
	private Button fatherBack;
	private Button guardianNext;
	
	private FormLayout guardianForm;
	private ComboBox gParents;
	private OptionGroup gPeopleIdType;
	private TextField gPeopleid;
	private ComboBox gPrename;
	private TextField gFirstname;
	private TextField gLastname;
	private TextField gFirstnameNd;
	private TextField gLastnameNd;
	private OptionGroup gGender;
	private ComboBox gReligion;
	private ComboBox gRace;
	private ComboBox gNationality;
	private PopupDateField gBirthDate;	
	private TextField gTel;
	private TextField gMobile;
	private TextField gEmail;
	private TextField gSalary;
	private ComboBox gAliveStatus;
	private ComboBox gOccupation;
	private TextArea gJobAddress;
	private TextArea gCurrentAddress;
	private ComboBox gCurrentCity;
	private ComboBox gCurrentDistrict;
	private ComboBox gCurrentProvinceId;
	private ComboBox gCurrentPostcode;
	private ComboBox guardianRelation;
	private Button motherBack;
	private Button finish;
	private Button print;
	
	public RecruitStudentView() {
		buildMainLayout();
		initFieldGroup();
	}
	
	public RecruitStudentView(Object studentId) {
		editMode = true;
		this.studentId = studentId;

		buildMainLayout();
		initFieldGroup();
	}
	
	private void buildMainLayout()  {
		setWidth("100%");
		setHeight("100%");
		generalInfoLayout();
		graduatedForm();
		addressForm();
		fatherForm();
		motherForm();
		guardianForm();
	}
	
	/*สร้าง Layout สำหรับข้อมูลทั่วไปนักเรียน*/
	private void generalInfoLayout()  {
		generalForm = new FormLayout();
		generalForm.setSizeUndefined();
		generalForm.setMargin(true);
		addTab(generalForm,"ข้อมูลทั่วไป", FontAwesome.CHILD);
		
		classRange = new ComboBox("ระดับชั้นที่สมัคร",new ClassRange());
		classRange.setInputPrompt("กรุณาเลือก");
		classRange.setItemCaptionPropertyId("name");
		classRange.setImmediate(true);
		classRange.setNullSelectionAllowed(false);
		classRange.setRequired(true);
		classRange.setWidth("-1px");
		classRange.setHeight("-1px");
		classRange.setFilteringMode(FilteringMode.CONTAINS);
		generalForm.addComponent(classRange);
		
		peopleIdType = new OptionGroup("ประเภทบัตร",new PeopleIdType());
		peopleIdType.setItemCaptionPropertyId("name");
		peopleIdType.setImmediate(true);
		peopleIdType.setRequired(true);
		peopleIdType.setNullSelectionAllowed(false);
		peopleIdType.setWidth("-1px");
		peopleIdType.setHeight("-1px");
		generalForm.addComponent(peopleIdType);
		
		peopleId = new TextField("หมายเลขประชาชน");
		peopleId.setInputPrompt("หมายเลขประชาชน");
		peopleId.setImmediate(false);
		peopleId.setRequired(true);
		peopleId.setWidth("-1px");
		peopleId.setHeight("-1px");
		peopleId.setNullRepresentation("");
		peopleId.addValidator(new StringLengthValidator("ข้อมูลไม่ถูกต้อง", 13, 20, false));
		generalForm.addComponent(peopleId);
		
		prename = new ComboBox("ชื่อต้น",new Prename());
		prename.setInputPrompt("กรุณาเลือก");
		prename.setItemCaptionPropertyId("name");
		prename.setImmediate(true);
        prename.setNullSelectionAllowed(false);
        prename.setRequired(true);
		prename.setWidth("-1px");
		prename.setHeight("-1px");
		prename.setFilteringMode(FilteringMode.CONTAINS);
		generalForm.addComponent(prename);
		
		firstname = new TextField("ชื่อ");
		firstname.setInputPrompt("ชื่อ");
		firstname.setImmediate(false);
		firstname.setRequired(true);
		firstname.setWidth("-1px");
		firstname.setHeight("-1px");
		firstname.setNullRepresentation("");
		generalForm.addComponent(firstname);
		
		lastname = new TextField("สกุล");
		lastname.setInputPrompt("สกุล");
		lastname.setImmediate(false);
		lastname.setRequired(true);
		lastname.setWidth("-1px");
		lastname.setHeight("-1px");
		lastname.setNullRepresentation("");
		generalForm.addComponent(lastname);

		firstnameNd = new TextField("ชื่ออังกฤษ");
		firstnameNd.setInputPrompt("ชื่ออังกฤษ");
		firstnameNd.setImmediate(false);
		firstnameNd.setRequired(true);
		firstnameNd.setWidth("-1px");
		firstnameNd.setHeight("-1px");
		firstnameNd.setNullRepresentation("");
		generalForm.addComponent(firstnameNd);
		
		lastnameNd = new TextField("สกุลอังกฤษ");
		lastnameNd.setInputPrompt("สกุลอังกฤษ");
		lastnameNd.setImmediate(false);
		lastnameNd.setRequired(true);
		lastnameNd.setWidth("-1px");
		lastnameNd.setHeight("-1px");
		lastnameNd.setNullRepresentation("");
		generalForm.addComponent(lastnameNd);
		
		nickname = new TextField("ชื่อเล่น");
		nickname.setInputPrompt("ชื่อเล่น");
		nickname.setImmediate(false);
		nickname.setWidth("-1px");
		nickname.setHeight("-1px");
		nickname.setNullRepresentation("");
		generalForm.addComponent(nickname);
		
		gender = new OptionGroup("เพศ",new Gender());
		gender.setItemCaptionPropertyId("name");
		gender.setImmediate(true);
		gender.setNullSelectionAllowed(false);
		gender.setRequired(true);
		gender.setWidth("-1px");
		gender.setHeight("-1px");
		generalForm.addComponent(gender);
		
		religion = new ComboBox("ศาสนา",new Religion());
		religion.setInputPrompt("กรุณาเลือก");
		religion.setItemCaptionPropertyId("name");
		religion.setImmediate(true);
		religion.setNullSelectionAllowed(false);
		religion.setRequired(true);
		religion.setWidth("-1px");
		religion.setHeight("-1px");
		religion.setFilteringMode(FilteringMode.CONTAINS);
		generalForm.addComponent(religion);
		
		race = new ComboBox("เชื้อชาติ",new Race());
		race.setInputPrompt("กรุณาเลือก");
		race.setItemCaptionPropertyId("name");
		race.setImmediate(true);
		race.setNullSelectionAllowed(false);
		race.setRequired(true);
		race.setWidth("-1px");
		race.setHeight("-1px");
		race.setFilteringMode(FilteringMode.CONTAINS);
		generalForm.addComponent(race);
		
		nationality = new ComboBox("สัญชาติ",new Nationality());
		nationality.setInputPrompt("กรุณาเลือก");
		nationality.setItemCaptionPropertyId("name");
		nationality.setImmediate(true);
		nationality.setNullSelectionAllowed(false);
		nationality.setRequired(true);
		nationality.setWidth("-1px");
		nationality.setHeight("-1px");
		nationality.setFilteringMode(FilteringMode.CONTAINS);
		generalForm.addComponent(nationality);
		
		birthDate = new PopupDateField("วัน เดือน ปี เกิด");
		birthDate.setInputPrompt("วว/ดด/ปปปป(คศ)");
		birthDate.setImmediate(false);
		birthDate.setRequired(true);
		birthDate.setWidth("-1px");
		birthDate.setHeight("-1px");
		generalForm.addComponent(birthDate);
		
		blood = new ComboBox("หมู่เลือด",new Blood());
		blood.setInputPrompt("กรุณาเลือก");
		blood.setItemCaptionPropertyId("name");
		blood.setImmediate(true);
		blood.setNullSelectionAllowed(false);
		blood.setRequired(true);
		blood.setWidth("-1px");
		blood.setHeight("-1px");
		blood.setFilteringMode(FilteringMode.CONTAINS);
		generalForm.addComponent(blood);
		
		height = new TextField("ส่วนสูง");
		height.setInputPrompt("ส่วนสูง");
		height.setImmediate(false);
		height.setWidth("-1px");
		height.setHeight("-1px");
		//height.addValidator(new DoubleRangeValidator("ข้อมูลไม่ถูกต้อง", 30.00, 250.00));
		height.setNullRepresentation("");
		generalForm.addComponent(height);
		
		weight = new TextField("น้ำหนัก");
		weight.setInputPrompt("น้ำหนัก");
		weight.setImmediate(false);
		weight.setWidth("-1px");
		weight.setHeight("-1px");
		//weight.addValidator(new DoubleRangeValidator("ข้อมูลไม่ถูกต้อง", 30.00, 150.00));
		weight.setNullRepresentation("");
		generalForm.addComponent(weight);
		
		congenitalDisease = new TextField("โรคประจำตัว");
		congenitalDisease.setInputPrompt("โรคประจำตัว");
		congenitalDisease.setImmediate(false);
		congenitalDisease.setWidth("-1px");
		congenitalDisease.setHeight("-1px");
		congenitalDisease.setNullRepresentation("");
		generalForm.addComponent(congenitalDisease);
		
		interested = new TextField("งานอดิเรก");
		interested.setInputPrompt("งานอดิเรก");
		interested.setImmediate(false);
		interested.setWidth("-1px");
		interested.setHeight("-1px");
		interested.setNullRepresentation("");
		generalForm.addComponent(interested);
		
		siblingQty = new TextField("จำนวนพี่น้อง");
		siblingQty.setInputPrompt("จำนวน");
		siblingQty.setImmediate(false);
		siblingQty.setRequired(true);
		siblingQty.setWidth("-1px");
		siblingQty.setHeight("-1px");
		siblingQty.setNullRepresentation("");
		//siblingQty.addValidator(new IntegerRangeValidator("ข้อมูลไม่ถูกต้อง", 0, 20));
		generalForm.addComponent(siblingQty);
		
		siblingSequence = new TextField("ลำดับพี่น้อง");
		siblingSequence.setInputPrompt("ลำดับที่");
		siblingSequence.setImmediate(false);
		siblingSequence.setRequired(true);
		siblingSequence.setWidth("-1px");
		siblingSequence.setHeight("-1px");
		siblingSequence.setNullRepresentation("");
		//siblingSequence.addValidator(new IntegerRangeValidator("ข้อมูลไม่ถูกต้อง", 0, 20));
		generalForm.addComponent(siblingSequence);
		
		siblingInSchoolQty = new TextField("จำนวนพี่น้องที่ศึกษา");
		siblingInSchoolQty.setInputPrompt("จำนวน");
		siblingInSchoolQty.setImmediate(false);
		siblingInSchoolQty.setRequired(true);
		siblingInSchoolQty.setWidth("-1px");
		siblingInSchoolQty.setHeight("-1px");
		siblingInSchoolQty.setNullRepresentation("");
		//siblingInSchoolQty.addValidator(new IntegerRangeValidator("ข้อมูลไม่ถูกต้อง", 0, 20));
		generalForm.addComponent(siblingInSchoolQty);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.setWidth("100%");
		generalForm.addComponent(buttonLayout);
		
		graduatedNext = new Button(FontAwesome.ARROW_RIGHT);
		graduatedNext.setWidth("100%");
		graduatedNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(graduatedForm);
			}
		});
		buttonLayout.addComponent(graduatedNext);	
	}
	
	/*สร้าง Layout สำหรับประวัติการศึกษาของนักเรียน*/
	private void graduatedForm(){
		graduatedForm = new FormLayout();
		graduatedForm.setSizeUndefined();
		graduatedForm.setMargin(true);
		addTab(graduatedForm,"ข้อมูลการศึกษา", FontAwesome.GRADUATION_CAP);
		
		graduatedSchool = new TextField("โรงเรียนที่จบ");
		graduatedSchool.setInputPrompt("ชื่อโรงเรียน");
		graduatedSchool.setImmediate(false);
		graduatedSchool.setRequired(true);
		graduatedSchool.setWidth("-1px");
		graduatedSchool.setHeight("-1px");
		graduatedSchool.setNullRepresentation("");
		graduatedForm.addComponent(graduatedSchool);

		graduatedSchoolProvinceId = new ComboBox("จังหวัด",new Province());
		graduatedSchoolProvinceId.setInputPrompt("กรุณาเลือก");
		graduatedSchoolProvinceId.setItemCaptionPropertyId("name");
		graduatedSchoolProvinceId.setImmediate(true);
		graduatedSchoolProvinceId.setNullSelectionAllowed(false);
		graduatedSchoolProvinceId.setRequired(true);
		graduatedSchoolProvinceId.setWidth("-1px");
		graduatedSchoolProvinceId.setHeight("-1px");
		graduatedSchoolProvinceId.setFilteringMode(FilteringMode.CONTAINS);
		graduatedForm.addComponent(graduatedSchoolProvinceId);

		graduatedGpa = new TextField("ผลการเรียนเฉลี่ย");
		graduatedGpa.setInputPrompt("ผลการเรียน");
		graduatedGpa.setImmediate(false);
		graduatedGpa.setRequired(true);
		graduatedGpa.setWidth("-1px");
		graduatedGpa.setHeight("-1px");
		graduatedGpa.setNullRepresentation("");
		//graduatedGpa.addValidator(new DoubleRangeValidator("ข้อมูลไม่ถูกต้อง", 0.0, 4.0));
		graduatedForm.addComponent(graduatedGpa);

		graduatedYear = new TextField("ปีที่จบ");
		graduatedYear.setInputPrompt("ปีที่จบ");
		graduatedYear.setImmediate(false);
		graduatedYear.setRequired(true);
		graduatedYear.setWidth("-1px");
		graduatedYear.setHeight("-1px");
		graduatedYear.setNullRepresentation("");
		//graduatedYear.addValidator(new IntegerRangeValidator("ข้อมูลไม่ถูกต้อง", 1900, 2600));
		graduatedForm.addComponent(graduatedYear);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth("100%");
		buttonLayout.setSpacing(true);
		graduatedForm.addComponent(buttonLayout);
		
		generalBack = new Button(FontAwesome.ARROW_LEFT);
		generalBack.setWidth("100%");
		generalBack.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(generalForm);
			}
		});
		buttonLayout.addComponents(generalBack);
		
		addressNext = new Button(FontAwesome.ARROW_RIGHT);
		addressNext.setWidth("100%");
		addressNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(addressForm);
			}
		});
		buttonLayout.addComponent(addressNext);
		
	}
	
	/*สร้าง Layout สำหรับที่อยู่ปัจจุบันของนักเรียน*/
	private void addressForm(){
		addressForm = new FormLayout();
		addressForm.setSizeUndefined();
		addressForm.setMargin(true);
		addTab(addressForm,"ข้อมูลติดต่อ", FontAwesome.BOOK);
		
		tel = new TextField("เบอร์โทร");
		tel.setInputPrompt("เบอร์โทร");
		tel.setImmediate(false);
		tel.setWidth("-1px");
		tel.setHeight("-1px");
		tel.setNullRepresentation("");
		addressForm.addComponent(tel);
		
		mobile = new TextField("มือถือ");
		mobile.setInputPrompt("มือถือ");
		mobile.setImmediate(false);
		mobile.setRequired(true);
		mobile.setWidth("-1px");
		mobile.setHeight("-1px");
		mobile.setNullRepresentation("");
		addressForm.addComponent(mobile);
		
		email = new TextField("อีเมลล์");
		email.setInputPrompt("อีเมลล์");
		email.setImmediate(false);
		email.setRequired(true);
		email.setWidth("-1px");
		email.setHeight("-1px");
		email.addValidator(new EmailValidator("ข้อมูลไม่ถูกต้อง"));
		email.setNullRepresentation("");
		addressForm.addComponent(email);
		
		currentAddress = new TextArea("ที่อยู่ปัจจุบัน");
		currentAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		currentAddress.setImmediate(false);
		currentAddress.setRequired(true);
		currentAddress.setWidth("-1px");
		currentAddress.setHeight("-1px");
		currentAddress.setNullRepresentation("");
		addressForm.addComponent(currentAddress);
		
		currentProvinceId = new ComboBox("จังหวัด",new Province());
		currentProvinceId.setInputPrompt("กรุณาเลือก");
		currentProvinceId.setItemCaptionPropertyId("name");
		currentProvinceId.setImmediate(true);
		currentProvinceId.setNullSelectionAllowed(false);
		currentProvinceId.setRequired(true);
		currentProvinceId.setWidth("-1px");
		currentProvinceId.setHeight("-1px");
		currentProvinceId.setFilteringMode(FilteringMode.CONTAINS);
		currentProvinceId.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null)
					currentDistrict.setContainerDataSource(new District(Integer.parseInt(event.getProperty().getValue().toString())));
			}
		});
		addressForm.addComponent(currentProvinceId);
		
		currentDistrict = new ComboBox("อำเภอ");
		currentDistrict.setInputPrompt("กรุณาเลือก");
		currentDistrict.setItemCaptionPropertyId("name");
		currentDistrict.setImmediate(true);
		currentDistrict.setNullSelectionAllowed(false);
		currentDistrict.setRequired(true);
		currentDistrict.setWidth("-1px");
		currentDistrict.setHeight("-1px");
		currentDistrict.setFilteringMode(FilteringMode.CONTAINS);
		currentDistrict.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null){
					currentCity.setContainerDataSource(new City(Integer.parseInt(event.getProperty().getValue().toString())));
					currentPostcode.setContainerDataSource(new Postcode(Integer.parseInt(event.getProperty().getValue().toString())));
				}
			}
		});
		addressForm.addComponent(currentDistrict);
		
		currentCity = new ComboBox("ตำบล");
		currentCity.setInputPrompt("กรุณาเลือก");
		currentCity.setItemCaptionPropertyId("name");
		currentCity.setImmediate(true);
		currentCity.setNullSelectionAllowed(false);
		currentCity.setRequired(true);
		currentCity.setWidth("-1px");
		currentCity.setHeight("-1px");
		currentCity.setFilteringMode(FilteringMode.CONTAINS);
		addressForm.addComponent(currentCity);
		
		currentPostcode = new ComboBox("รหัสไปรษณีย์");
		currentPostcode.setInputPrompt("กรุณาเลือก");
		currentPostcode.setItemCaptionPropertyId("name");
		currentPostcode.setImmediate(true);
		currentPostcode.setNullSelectionAllowed(false);
		currentPostcode.setRequired(true);
		currentPostcode.setWidth("-1px");
		currentPostcode.setHeight("-1px");
		currentPostcode.setFilteringMode(FilteringMode.CONTAINS);
		addressForm.addComponent(currentPostcode);
		

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.setWidth("100%");
		addressForm.addComponent(buttonLayout);
		
		graduatedBack = new Button(FontAwesome.ARROW_LEFT);
		graduatedBack.setWidth("100%");
		graduatedBack.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(graduatedForm);
			}
		});
		buttonLayout.addComponents(graduatedBack);
		
		fatherNext = new Button(FontAwesome.ARROW_RIGHT);
		fatherNext.setWidth("100%");
		fatherNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(fatherForm);
			}
		});
		
		buttonLayout.addComponents(fatherNext);
	}
	
	/*สร้าง Layout สำหรับบิดา*/
	private void fatherForm(){
		fatherForm = new FormLayout();
		fatherForm.setSizeUndefined();
		fatherForm.setMargin(true);
		addTab(fatherForm,"ข้อมูลบิดา", FontAwesome.MALE);
		
		fPeopleIdType = new OptionGroup("ประเภทบัตร",new PeopleIdType());
		fPeopleIdType.setItemCaptionPropertyId("name");
		fPeopleIdType.setImmediate(true);
		fPeopleIdType.setNullSelectionAllowed(false);
		fPeopleIdType.setRequired(true);
		fPeopleIdType.setWidth("-1px");
		fPeopleIdType.setHeight("-1px");
		fatherForm.addComponent(fPeopleIdType);
		
		fPeopleid = new TextField("หมายเลขประชาชน");
		fPeopleid.setInputPrompt("หมายเลขประชาชน");
		fPeopleid.setImmediate(false);
		fPeopleid.setRequired(true);
		fPeopleid.setWidth("-1px");
		fPeopleid.setHeight("-1px");
		fPeopleid.setNullRepresentation("");
		fPeopleid.addValidator(new StringLengthValidator("ข้อมูลไม่ถูกต้อง", 13, 20, false));
		fatherForm.addComponent(fPeopleid);
		
		fPrename = new ComboBox("ชื่อต้น",new Prename());
		fPrename.setInputPrompt("กรุณาเลือก");
		fPrename.setValue("ชาย");
		fPrename.setItemCaptionPropertyId("name");
		fPrename.setImmediate(true);
		fPrename.setNullSelectionAllowed(false);
		fPrename.setRequired(true);
		fPrename.setWidth("-1px");
		fPrename.setHeight("-1px");
		fPrename.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fPrename);
		
		fFirstname = new TextField("ชื่อ");
		fFirstname.setInputPrompt("ชื่อ");
		fFirstname.setImmediate(false);
		fFirstname.setRequired(true);
		fFirstname.setWidth("-1px");
		fFirstname.setHeight("-1px");
		fFirstname.setNullRepresentation("");
		fatherForm.addComponent(fFirstname);
		
		fLastname = new TextField("สกุล");
		fLastname.setInputPrompt("สกุล");
		fLastname.setImmediate(false);
		fLastname.setRequired(true);
		fLastname.setWidth("-1px");
		fLastname.setHeight("-1px");
		fLastname.setNullRepresentation("");
		fatherForm.addComponent(fLastname);

		fFirstnameNd = new TextField("ชื่ออังกฤษ");
		fFirstnameNd.setInputPrompt("ชื่ออังกฤษ");
		fFirstnameNd.setImmediate(false);
		fFirstnameNd.setWidth("-1px");
		fFirstnameNd.setHeight("-1px");
		fFirstnameNd.setNullRepresentation("");
		fatherForm.addComponent(fFirstnameNd);
		
		fLastnameNd = new TextField("สกุลอังกฤษ");
		fLastnameNd.setInputPrompt("สกุลอังกฤษ");
		fLastnameNd.setImmediate(false);
		fLastnameNd.setWidth("-1px");
		fLastnameNd.setHeight("-1px");
		fLastnameNd.setNullRepresentation("");
		fatherForm.addComponent(fLastnameNd);
			
		fGender = new OptionGroup("เพศ",new Gender());
		fGender.setItemCaptionPropertyId("name");
		fGender.setImmediate(true);
		fGender.setNullSelectionAllowed(false);
		fGender.setRequired(true);
		fGender.setWidth("-1px");
		fGender.setHeight("-1px");
		fatherForm.addComponent(fGender);
		
		fReligion = new ComboBox("ศาสนา",new Religion());
		fReligion.setInputPrompt("กรุณาเลือก");
		fReligion.setItemCaptionPropertyId("name");
		fReligion.setImmediate(true);
		fReligion.setNullSelectionAllowed(false);
		fReligion.setRequired(true);
		fReligion.setWidth("-1px");
		fReligion.setHeight("-1px");
		fReligion.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fReligion);
		
		fRace = new ComboBox("เชื้อชาติ",new Race());
		fRace.setInputPrompt("กรุณาเลือก");
		fRace.setItemCaptionPropertyId("name");
		fRace.setImmediate(true);
		fRace.setNullSelectionAllowed(false);
		fRace.setRequired(true);
		fRace.setWidth("-1px");
		fRace.setHeight("-1px");
		fRace.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fRace);
		
		fNationality = new ComboBox("สัญชาติ",new Nationality());
		fNationality.setInputPrompt("กรุณาเลือก");
		fNationality.setItemCaptionPropertyId("name");
		fNationality.setImmediate(true);
		fNationality.setNullSelectionAllowed(false);
		fNationality.setRequired(true);
		fNationality.setWidth("-1px");
		fNationality.setHeight("-1px");
		fNationality.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fNationality);
		
		fBirthDate = new PopupDateField("วัน เดือน ปี เกิด");
		fBirthDate.setInputPrompt("วว/ดด/ปปปป(คศ)");
		fBirthDate.setImmediate(false);
		fBirthDate.setWidth("-1px");
		fBirthDate.setHeight("-1px");
		fatherForm.addComponent(fBirthDate);
		
		fTel = new TextField("เบอร์โทร");
		fTel.setInputPrompt("เบอร์โทร");
		fTel.setImmediate(false);
		fTel.setWidth("-1px");
		fTel.setHeight("-1px");
		fTel.setNullRepresentation("");
		fatherForm.addComponent(fTel);
		
		fMobile = new TextField("มือถือ");
		fMobile.setInputPrompt("มือถือ");
		fMobile.setImmediate(false);
		fMobile.setRequired(true);
		fMobile.setWidth("-1px");
		fMobile.setHeight("-1px");
		fMobile.setNullRepresentation("");
		fatherForm.addComponent(fMobile);
		
		fEmail = new TextField("อีเมลล์");
		fEmail.setInputPrompt("อีเมลล์");
		fEmail.setImmediate(false);
		fEmail.setWidth("-1px");
		fEmail.setHeight("-1px");
		fEmail.setNullRepresentation("");
		fEmail.addValidator(new EmailValidator("ข้อมูลไม่ถูกต้อง"));
		fatherForm.addComponent(fEmail);
		
		fSalary = new TextField("รายได้");
		fSalary.setInputPrompt("รายได้");
		fSalary.setImmediate(false);
		fSalary.setWidth("-1px");
		fSalary.setHeight("-1px");
		fSalary.setNullRepresentation("");
		//fSalary.addValidator(new DoubleRangeValidator("ข้อมูลไม่ถูกต้อง", 0.00, null));
		fatherForm.addComponent(fSalary);
		
		fAliveStatus = new ComboBox("สถานภาพ",new AliveStatus());
		fAliveStatus.setInputPrompt("กรุณาเลือก");
		fAliveStatus.setItemCaptionPropertyId("name");
		fAliveStatus.setImmediate(true);
		fAliveStatus.setNullSelectionAllowed(false);
		fAliveStatus.setRequired(true);
		fAliveStatus.setWidth("-1px");
		fAliveStatus.setHeight("-1px");
		fAliveStatus.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fAliveStatus);
		
		fOccupation = new ComboBox("อาชีพ",new Occupation());
		fOccupation.setInputPrompt("กรุณาเลือก");
		fOccupation.setItemCaptionPropertyId("name");
		fOccupation.setImmediate(true);
		fOccupation.setNullSelectionAllowed(false);
		fOccupation.setRequired(true);
		fOccupation.setWidth("-1px");
		fOccupation.setHeight("-1px");
		fOccupation.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fOccupation);
		
		fJobAddress = new TextArea("สถานที่ทำงาน");
		fJobAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		fJobAddress.setImmediate(false);
		fJobAddress.setWidth("-1px");
		fJobAddress.setHeight("-1px");
		fJobAddress.setNullRepresentation("");
		fatherForm.addComponent(fJobAddress);
		
		fCurrentAddress = new TextArea("ที่อยู่ปัจจุบัน");
		fCurrentAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		fCurrentAddress.setImmediate(false);
		fCurrentAddress.setRequired(true);
		fCurrentAddress.setWidth("-1px");
		fCurrentAddress.setHeight("-1px");
		fCurrentAddress.setNullRepresentation("");
		fatherForm.addComponent(fCurrentAddress);
		
		fCurrentProvinceId = new ComboBox("จังหวัด",new Province());
		fCurrentProvinceId.setInputPrompt("กรุณาเลือก");
		fCurrentProvinceId.setItemCaptionPropertyId("name");
		fCurrentProvinceId.setImmediate(true);
		fCurrentProvinceId.setNullSelectionAllowed(false);
		fCurrentProvinceId.setRequired(true);
		fCurrentProvinceId.setWidth("-1px");
		fCurrentProvinceId.setHeight("-1px");
		fCurrentProvinceId.setFilteringMode(FilteringMode.CONTAINS);
		fCurrentProvinceId.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null)
					fCurrentDistrict.setContainerDataSource(new District(Integer.parseInt(event.getProperty().getValue().toString())));
			}
		});
		fatherForm.addComponent(fCurrentProvinceId);
		
		fCurrentDistrict = new ComboBox("อำเภอ");
		fCurrentDistrict.setInputPrompt("กรุณาเลือก");
		fCurrentDistrict.setItemCaptionPropertyId("name");
		fCurrentDistrict.setImmediate(true);
		fCurrentDistrict.setNullSelectionAllowed(false);
		fCurrentDistrict.setRequired(true);
		fCurrentDistrict.setWidth("-1px");
		fCurrentDistrict.setHeight("-1px");
		fCurrentDistrict.setFilteringMode(FilteringMode.CONTAINS);
		fCurrentDistrict.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null){
					fCurrentCity.setContainerDataSource(new City(Integer.parseInt(event.getProperty().getValue().toString())));
					fCurrentPostcode.setContainerDataSource(new Postcode(Integer.parseInt(event.getProperty().getValue().toString())));
				}
			}
		});
		fatherForm.addComponent(fCurrentDistrict);
		
		fCurrentCity = new ComboBox("ตำบล");
		fCurrentCity.setInputPrompt("กรุณาเลือก");
		fCurrentCity.setItemCaptionPropertyId("name");
		fCurrentCity.setImmediate(true);
		fCurrentCity.setNullSelectionAllowed(false);
		fCurrentCity.setRequired(true);
		fCurrentCity.setWidth("-1px");
		fCurrentCity.setHeight("-1px");
		fCurrentCity.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fCurrentCity);
		
		fCurrentPostcode = new ComboBox("รหัสไปรษณีย์");
		fCurrentPostcode.setInputPrompt("กรุณาเลือก");
		fCurrentPostcode.setItemCaptionPropertyId("name");
		fCurrentPostcode.setImmediate(true);
		fCurrentPostcode.setNullSelectionAllowed(false);
		fCurrentPostcode.setRequired(true);
		fCurrentPostcode.setWidth("-1px");
		fCurrentPostcode.setHeight("-1px");
		fCurrentPostcode.setFilteringMode(FilteringMode.CONTAINS);
		fatherForm.addComponent(fCurrentPostcode);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.setWidth("100%");
		fatherForm.addComponent(buttonLayout);
		
		addressBack = new Button(FontAwesome.ARROW_LEFT);
		addressBack.setWidth("100%");
		addressBack.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(addressForm);
			}
		});
		buttonLayout.addComponents(addressBack);
		
		motherNext = new Button(FontAwesome.ARROW_RIGHT);
		motherNext.setWidth("100%");
		motherNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(motherForm);
			}
		});
		buttonLayout.addComponents(motherNext);
	}
	
	/*สร้าง Layout สำหรับมารดา*/
	private void motherForm(){
		motherForm = new FormLayout();
		motherForm.setSizeUndefined();
		motherForm.setMargin(true);
		addTab(motherForm,"ข้อมูลมารดา", FontAwesome.FEMALE);
		
		mPeopleIdType = new OptionGroup("ประเภทบัตร",new PeopleIdType());
		mPeopleIdType.setItemCaptionPropertyId("name");
		mPeopleIdType.setImmediate(true);
		mPeopleIdType.setNullSelectionAllowed(false);
		mPeopleIdType.setRequired(true);
		mPeopleIdType.setWidth("-1px");
		mPeopleIdType.setHeight("-1px");
		motherForm.addComponent(mPeopleIdType);
		
		mPeopleid = new TextField("หมายเลขประชาชน");
		mPeopleid.setInputPrompt("หมายเลขประชาชน");
		mPeopleid.setImmediate(false);
		mPeopleid.setRequired(true);
		mPeopleid.setWidth("-1px");
		mPeopleid.setHeight("-1px");
		mPeopleid.setNullRepresentation("");
		mPeopleid.addValidator(new StringLengthValidator("ข้อมูลไม่ถูกต้อง", 13, 20, false));
		motherForm.addComponent(mPeopleid);
		
		mPrename = new ComboBox("ชื่อต้น",new Prename());
		mPrename.setInputPrompt("กรุณาเลือก");
		mPrename.setItemCaptionPropertyId("name");
		mPrename.setImmediate(true);
		mPrename.setNullSelectionAllowed(false);
		mPrename.setRequired(true);
		mPrename.setWidth("-1px");
		mPrename.setHeight("-1px");
		mPrename.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mPrename);
		
		mFirstname = new TextField("ชื่อ");
		mFirstname.setInputPrompt("ชื่อ");
		mFirstname.setImmediate(false);
		mFirstname.setRequired(true);
		mFirstname.setWidth("-1px");
		mFirstname.setHeight("-1px");
		mFirstname.setNullRepresentation("");
		motherForm.addComponent(mFirstname);
		
		mLastname = new TextField("สกุล");
		mLastname.setInputPrompt("สกุล");
		mLastname.setImmediate(false);
		mLastname.setRequired(true);
		mLastname.setWidth("-1px");
		mLastname.setHeight("-1px");
		mLastname.setNullRepresentation("");
		motherForm.addComponent(mLastname);

		mFirstnameNd = new TextField("ชื่ออังกฤษ");
		mFirstnameNd.setInputPrompt("ชื่ออังกฤษ");
		mFirstnameNd.setImmediate(false);
		mFirstnameNd.setWidth("-1px");
		mFirstnameNd.setHeight("-1px");
		mFirstnameNd.setNullRepresentation("");
		motherForm.addComponent(mFirstnameNd);
		
		mLastnameNd = new TextField("สกุลอังกฤษ");
		mLastnameNd.setInputPrompt("สกุลอังกฤษ");
		mLastnameNd.setImmediate(false);
		mLastnameNd.setWidth("-1px");
		mLastnameNd.setHeight("-1px");
		mLastnameNd.setNullRepresentation("");
		motherForm.addComponent(mLastnameNd);
			
		mGender = new OptionGroup("เพศ",new Gender());
		mGender.setItemCaptionPropertyId("name");
		mGender.setImmediate(true);
		mGender.setNullSelectionAllowed(false);
		mGender.setRequired(true);
		mGender.setWidth("-1px");
		mGender.setHeight("-1px");
		motherForm.addComponent(mGender);
		
		mReligion = new ComboBox("ศาสนา",new Religion());
		mReligion.setInputPrompt("กรุณาเลือก");
		mReligion.setItemCaptionPropertyId("name");
		mReligion.setImmediate(true);
		mReligion.setNullSelectionAllowed(false);
		mReligion.setRequired(true);
		mReligion.setWidth("-1px");
		mReligion.setHeight("-1px");
		mReligion.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mReligion);
		
		mRace = new ComboBox("เชื้อชาติ",new Race());
		mRace.setInputPrompt("กรุณาเลือก");
		mRace.setItemCaptionPropertyId("name");
		mRace.setImmediate(true);
		mRace.setNullSelectionAllowed(false);
		mRace.setRequired(true);
		mRace.setWidth("-1px");
		mRace.setHeight("-1px");
		mRace.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mRace);
		
		mNationality = new ComboBox("สัญชาติ",new Nationality());
		mNationality.setInputPrompt("กรุณาเลือก");
		mNationality.setItemCaptionPropertyId("name");
		mNationality.setImmediate(true);
		mNationality.setNullSelectionAllowed(false);
		mNationality.setRequired(true);
		mNationality.setWidth("-1px");
		mNationality.setHeight("-1px");
		mNationality.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mNationality);

		mBirthDate = new PopupDateField("วัน เดือน ปี เกิด");
		mBirthDate.setInputPrompt("วว/ดด/ปปปป(คศ)");
		mBirthDate.setImmediate(false);
		mBirthDate.setWidth("-1px");
		mBirthDate.setHeight("-1px");
		motherForm.addComponent(mBirthDate);
		
		mTel = new TextField("เบอร์โทร");
		mTel.setInputPrompt("เบอร์โทร");
		mTel.setImmediate(false);
		mTel.setWidth("-1px");
		mTel.setHeight("-1px");
		mTel.setNullRepresentation("");
		motherForm.addComponent(mTel);
		
		mMobile = new TextField("มือถือ");
		mMobile.setInputPrompt("มือถือ");
		mMobile.setImmediate(false);
		mMobile.setRequired(true);
		mMobile.setWidth("-1px");
		mMobile.setHeight("-1px");
		mMobile.setNullRepresentation("");
		motherForm.addComponent(mMobile);
		
		mEmail = new TextField("อีเมลล์");
		mEmail.setInputPrompt("อีเมลล์");
		mEmail.setImmediate(false);
		mEmail.setWidth("-1px");
		mEmail.setHeight("-1px");
		mEmail.setNullRepresentation("");
		mEmail.addValidator(new EmailValidator("ข้อมูลไม่ถูกต้อง"));
		motherForm.addComponent(mEmail);
		
		mSalary = new TextField("รายได้");
		mSalary.setInputPrompt("รายได้");
		mSalary.setImmediate(false);
		mSalary.setWidth("-1px");
		mSalary.setHeight("-1px");
		mSalary.setNullRepresentation("");
		//mSalary.addValidator(new DoubleRangeValidator("ข้อมูลไม่ถูกต้อง", 0.00, null));
		motherForm.addComponent(mSalary);
		
		mAliveStatus = new ComboBox("สถานภาพ",new AliveStatus());
		mAliveStatus.setInputPrompt("กรุณาเลือก");
		mAliveStatus.setItemCaptionPropertyId("name");
		mAliveStatus.setImmediate(true);
		mAliveStatus.setNullSelectionAllowed(false);
		mAliveStatus.setRequired(true);
		mAliveStatus.setWidth("-1px");
		mAliveStatus.setHeight("-1px");
		mAliveStatus.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mAliveStatus);
		
		mOccupation = new ComboBox("อาชีพ",new Occupation());
		mOccupation.setInputPrompt("กรุณาเลือก");
		mOccupation.setItemCaptionPropertyId("name");
		mOccupation.setImmediate(true);
		mOccupation.setNullSelectionAllowed(false);
		mOccupation.setRequired(true);
		mOccupation.setWidth("-1px");
		mOccupation.setHeight("-1px");
		mOccupation.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mOccupation);
		
		mJobAddress = new TextArea("สถานที่ทำงาน");
		mJobAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		mJobAddress.setImmediate(false);
		mJobAddress.setWidth("-1px");
		mJobAddress.setHeight("-1px");
		mJobAddress.setNullRepresentation("");
		motherForm.addComponent(mJobAddress);
		
		mCurrentAddress = new TextArea("ที่อยู่ปัจจุบัน");
		mCurrentAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		mCurrentAddress.setImmediate(false);
		mCurrentAddress.setRequired(true);
		mCurrentAddress.setWidth("-1px");
		mCurrentAddress.setHeight("-1px");
		mCurrentAddress.setNullRepresentation("");
		motherForm.addComponent(mCurrentAddress);
		
		mCurrentProvinceId = new ComboBox("จังหวัด",new Province());
		mCurrentProvinceId.setInputPrompt("กรุณาเลือก");
		mCurrentProvinceId.setItemCaptionPropertyId("name");
		mCurrentProvinceId.setImmediate(true);
		mCurrentProvinceId.setNullSelectionAllowed(false);
		mCurrentProvinceId.setRequired(true);
		mCurrentProvinceId.setWidth("-1px");
		mCurrentProvinceId.setHeight("-1px");
		mCurrentProvinceId.setFilteringMode(FilteringMode.CONTAINS);
		mCurrentProvinceId.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null)
					mCurrentDistrict.setContainerDataSource(new District(Integer.parseInt(event.getProperty().getValue().toString())));
			}
		});
		motherForm.addComponent(mCurrentProvinceId);
		
		mCurrentDistrict = new ComboBox("อำเภอ");
		mCurrentDistrict.setInputPrompt("กรุณาเลือก");
		mCurrentDistrict.setItemCaptionPropertyId("name");
		mCurrentDistrict.setImmediate(true);
		mCurrentDistrict.setNullSelectionAllowed(false);
		mCurrentDistrict.setRequired(true);
		mCurrentDistrict.setWidth("-1px");
		mCurrentDistrict.setHeight("-1px");
		mCurrentDistrict.setFilteringMode(FilteringMode.CONTAINS);
		mCurrentDistrict.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null){
					mCurrentCity.setContainerDataSource(new City(Integer.parseInt(event.getProperty().getValue().toString())));
					mCurrentPostcode.setContainerDataSource(new Postcode(Integer.parseInt(event.getProperty().getValue().toString())));
				}
			}
		});
		motherForm.addComponent(mCurrentDistrict);
		
		mCurrentCity = new ComboBox("ตำบล");
		mCurrentCity.setInputPrompt("กรุณาเลือก");
		mCurrentCity.setItemCaptionPropertyId("name");
		mCurrentCity.setImmediate(true);
		mCurrentCity.setNullSelectionAllowed(false);
		mCurrentCity.setRequired(true);
		mCurrentCity.setWidth("-1px");
		mCurrentCity.setHeight("-1px");
		mCurrentCity.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mCurrentCity);
		
		mCurrentPostcode = new ComboBox("รหัสไปรษณีย์");
		mCurrentPostcode.setInputPrompt("กรุณาเลือก");
		mCurrentPostcode.setItemCaptionPropertyId("name");
		mCurrentPostcode.setImmediate(true);
		mCurrentPostcode.setNullSelectionAllowed(false);
		mCurrentPostcode.setRequired(true);
		mCurrentPostcode.setWidth("-1px");
		mCurrentPostcode.setHeight("-1px");
		mCurrentPostcode.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(mCurrentPostcode);
		
		familyStatus = new ComboBox("สถานะครอบครัว",new FamilyStatus());
		familyStatus.setInputPrompt("กรุณาเลือก");
		familyStatus.setItemCaptionPropertyId("name");
		familyStatus.setImmediate(true);
		familyStatus.setNullSelectionAllowed(false);
		familyStatus.setRequired(true);
		familyStatus.setWidth("-1px");
		familyStatus.setHeight("-1px");
		familyStatus.setFilteringMode(FilteringMode.CONTAINS);
		motherForm.addComponent(familyStatus);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.setWidth("100%");
		motherForm.addComponent(buttonLayout);
		
		fatherBack = new Button(FontAwesome.ARROW_LEFT);
		fatherBack.setWidth("100%");
		fatherBack.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(fatherForm);
			}
		});
		buttonLayout.addComponents(fatherBack);
		
		guardianNext = new Button(FontAwesome.ARROW_RIGHT);
		guardianNext.setWidth("100%");
		guardianNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(guardianForm);
			}
		});
		buttonLayout.addComponents(guardianNext);
	}
	
	/*สร้าง Layout สำหรับผู้ปกครอง*/
	private void guardianForm(){
		guardianForm = new FormLayout();
		guardianForm.setSizeUndefined();
		guardianForm.setMargin(true);
		addTab(guardianForm,"ข้อมูลผู้ปกครอง", FontAwesome.USER);
		
		gParents = new ComboBox("ผู้ปกครอง",new Parents());
		gParents.setInputPrompt("กรุณาเลือก");
		gParents.setItemCaptionPropertyId("name");
		gParents.setImmediate(true);
		gParents.setNullSelectionAllowed(false);
		gParents.setRequired(true);
		gParents.setWidth("-1px");
		gParents.setHeight("-1px");
		gParents.setFilteringMode(FilteringMode.CONTAINS);
		gParents.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null){
					enableGuardianBinder();
					
					/*กำหนดข้อมูลตามความสัมพันธ์ของผู้ปกครอง
					 * กรณี เลือกเป็น "บิดา (0)" ข้อมูลบิดาจะถูกนำมาตั้งค่าภายในฟอร์ม
					 * กรณี เลือกเป็น "มารดา (1)" ข้อมูลมารดาจะถูกนำมาตั้งค่าภายในฟอร์ม
					 * กรณี เลือกเป็น "อื่น ๆ (2)" ข้อมูลผู้ปกครองจะถูกนำมาตั้งค่าภายในฟอร์ม
					 * */
					String parents = event.getProperty().getValue().toString();
					if(parents.equals("0")){
						/* ตรวจสอบสถานะการแก้ไข 
						 *  กรณี "สถานะแก้ไข" จะนำข้อมูลบิดามากรอกในฟอร์ม พร้อม primary key
						 *  กรณี "สถานะเพิ่ม"  จะนำข้อมูลบิดามากรอกในฟอร์ม ปราศจาก primary key
						 * ปิดการกรอกข้อมูลของผู้ปกครองไม่ให้มีการแก้ไข
						 * */
						if(editMode){
							guardianBinder.setItemDataSource(fatherBinder.getItemDataSource());
						}else{
							initGuardianData(fatherBinder);
						}					
						disableGuardianBinder();
					}else if(parents.equals("1")){
						/* ตรวจสอบสถานะการแก้ไข 
						 *  กรณี "สถานะแก้ไข" จะนำข้อมูลมารดามากรอกในฟอร์ม พร้อม primary key
						 *  กรณี "สถานะเพิ่ม"  จะนำข้อมูลมารดามากรอกในฟอร์ม ปราศจาก primary key
						 * ปิดการกรอกข้อมูลของผู้ปกครองไม่ให้มีการแก้ไข
						 * */
						if(editMode){
							guardianBinder.setItemDataSource(motherBinder.getItemDataSource());
						}else{
							initGuardianData(motherBinder);
						}
						disableGuardianBinder();
					}else if(parents.equals("2")){
						/* ตรวจสอบสถานะการแก้ไข
						 *  กรณี "สถานะแก้ไข" จะแสดงถึงการแก้ไขมาจาก ข้อมูลเดิมเป็นบิดา มารดา หรือ อื่น ๆ 
						 * */
						if(editMode){
							/*
							 * ตรวจสอบ guardianId เดิมว่า ใครเป็นผู้ปกครอง
							 *  กรณีเดิมเป็นบิดา หรือ มาดา แสดงถึงการเพิ่มข้อมูลใหม่ ก็จะทำการล้างข้อมูลในฟอร์ม
							 *  กรณีเดิ่มเป็นอื่น ๆ ก็จะทำการคืนค่าเดิมกลับมา
							 * */
							if(guardianId.equals(fatherId) || guardianId.equals(motherId)){
								//เพิ่มข้อมูล ผู้ปกครองใหม่
								isNewGuardian = true;
								SQLContainer gSqlCon = container.getRecruitFamilyContainer();
								Object tmpGItem = gSqlCon.addItem();
								guardianItem = gSqlCon.getItem(tmpGItem);
								
								guardianBinder.setItemDataSource(guardianItem);
								guardianRelation.setValue(null);

							}else{
								isNewGuardian = false;
								initGuardianFieldGroup();
							}
						}else{
							isNewGuardian = true;
							SQLContainer gSqlCon = container.getRecruitFamilyContainer();
							Object tmpGItem = gSqlCon.addItem();
							guardianItem = gSqlCon.getItem(tmpGItem);
							guardianRelation.setValue(null);
							initGuardianFieldGroup();
						}
					}
					
				}
			}
		});
		guardianForm.addComponent(gParents);
		
		gPeopleIdType = new OptionGroup("ประเภทบัตร",new PeopleIdType());
		gPeopleIdType.setItemCaptionPropertyId("name");
		gPeopleIdType.setImmediate(true);
		gPeopleIdType.setNullSelectionAllowed(false);
		gPeopleIdType.setRequired(true);
		gPeopleIdType.setWidth("-1px");
		gPeopleIdType.setHeight("-1px");
		guardianForm.addComponent(gPeopleIdType);
		
		gPeopleid = new TextField("หมายเลขประชาชน");
		gPeopleid.setInputPrompt("หมายเลขประชาชน");
		gPeopleid.setImmediate(false);
		gPeopleid.setRequired(true);
		gPeopleid.setNullRepresentation("");
		gPeopleid.setWidth("-1px");
		gPeopleid.setHeight("-1px");
		gPeopleid.setNullRepresentation("");
		gPeopleid.addValidator(new StringLengthValidator("ข้อมูลไม่ถูกต้อง", 13, 20, false));
		guardianForm.addComponent(gPeopleid);
		
		gPrename = new ComboBox("ชื่อต้น",new Prename());
		gPrename.setInputPrompt("กรุณาเลือก");
		gPrename.setItemCaptionPropertyId("name");
		gPrename.setImmediate(true);
		gPrename.setNullSelectionAllowed(false);
		gPrename.setRequired(true);
		gPrename.setWidth("-1px");
		gPrename.setHeight("-1px");
		gPrename.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gPrename);
		
		gFirstname = new TextField("ชื่อ");
		gFirstname.setInputPrompt("ชื่อ");
		gFirstname.setImmediate(false);
		gFirstname.setRequired(true);
		gFirstname.setWidth("-1px");
		gFirstname.setHeight("-1px");
		gFirstname.setNullRepresentation("");
		guardianForm.addComponent(gFirstname);
		
		gLastname = new TextField("สกุล");
		gLastname.setInputPrompt("สกุล");
		gLastname.setImmediate(false);
		gLastname.setRequired(true);
		gLastname.setWidth("-1px");
		gLastname.setHeight("-1px");
		gLastname.setNullRepresentation("");
		guardianForm.addComponent(gLastname);

		gFirstnameNd = new TextField("ชื่ออังกฤษ");
		gFirstnameNd.setInputPrompt("ชื่ออังกฤษ");
		gFirstnameNd.setImmediate(false);
		gFirstnameNd.setWidth("-1px");
		gFirstnameNd.setHeight("-1px");
		gFirstnameNd.setNullRepresentation("");
		guardianForm.addComponent(gFirstnameNd);
		
		gLastnameNd = new TextField("สกุลอังกฤษ");
		gLastnameNd.setInputPrompt("สกุลอังกฤษ");
		gLastnameNd.setImmediate(false);
		gLastnameNd.setWidth("-1px");
		gLastnameNd.setHeight("-1px");
		gLastnameNd.setNullRepresentation("");
		guardianForm.addComponent(gLastnameNd);
			
		gGender = new OptionGroup("เพศ",new Gender());
		gGender.setItemCaptionPropertyId("name");
		gGender.setImmediate(true);
		gGender.setNullSelectionAllowed(false);
		gGender.setRequired(true);
		gGender.setWidth("-1px");
		gGender.setHeight("-1px");
		guardianForm.addComponent(gGender);
		
		gReligion = new ComboBox("ศาสนา",new Religion());
		gReligion.setInputPrompt("กรุณาเลือก");
		gReligion.setItemCaptionPropertyId("name");
		gReligion.setImmediate(true);
		gReligion.setNullSelectionAllowed(false);
		gReligion.setRequired(true);
		gReligion.setWidth("-1px");
		gReligion.setHeight("-1px");
		gReligion.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gReligion);
		
		gRace = new ComboBox("เชื้อชาติ",new Race());
		gRace.setInputPrompt("กรุณาเลือก");
		gRace.setItemCaptionPropertyId("name");
		gRace.setImmediate(true);
		gRace.setNullSelectionAllowed(false);
		gRace.setRequired(true);
		gRace.setWidth("-1px");
		gRace.setHeight("-1px");
		gRace.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gRace);
		
		gNationality = new ComboBox("สัญชาติ",new Nationality());
		gNationality.setInputPrompt("กรุณาเลือก");
		gNationality.setItemCaptionPropertyId("name");
		gNationality.setImmediate(true);
		gNationality.setNullSelectionAllowed(false);
		gNationality.setRequired(true);
		gNationality.setWidth("-1px");
		gNationality.setHeight("-1px");
		gNationality.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gNationality);
		
		gBirthDate = new PopupDateField("วัน เดือน ปี เกิด");
		gBirthDate.setInputPrompt("วว/ดด/ปปปป(คศ)");
		gBirthDate.setImmediate(false);
		gBirthDate.setWidth("-1px");
		gBirthDate.setHeight("-1px");
		guardianForm.addComponent(gBirthDate);
		
		gTel = new TextField("เบอร์โทร");
		gTel.setInputPrompt("เบอร์โทร");
		gTel.setImmediate(false);
		gTel.setWidth("-1px");
		gTel.setHeight("-1px");
		gTel.setNullRepresentation("");
		guardianForm.addComponent(gTel);
		
		gMobile = new TextField("มือถือ");
		gMobile.setInputPrompt("มือถือ");
		gMobile.setImmediate(false);
		gMobile.setRequired(true);
		gMobile.setWidth("-1px");
		gMobile.setHeight("-1px");
		gMobile.setNullRepresentation("");
		guardianForm.addComponent(gMobile);
		
		gEmail = new TextField("อีเมลล์");
		gEmail.setInputPrompt("อีเมลล์");
		gEmail.setImmediate(false);
		gEmail.setWidth("-1px");
		gEmail.setHeight("-1px");
		gEmail.setNullRepresentation("");
		gEmail.addValidator(new EmailValidator("ข้อมูลไม่ถูกต้อง"));
		guardianForm.addComponent(gEmail);
		
		gSalary = new TextField("รายได้");
		gSalary.setInputPrompt("รายได้");
		gSalary.setImmediate(false);
		gSalary.setWidth("-1px");
		gSalary.setHeight("-1px");
		gSalary.setNullRepresentation("");
		//gSalary.addValidator(new DoubleRangeValidator("ข้อมูลไม่ถูกต้อง", 0.00, null));
		guardianForm.addComponent(gSalary);
		
		gAliveStatus = new ComboBox("สถานภาพ",new AliveStatus());
		gAliveStatus.setInputPrompt("กรุณาเลือก");
		gAliveStatus.setItemCaptionPropertyId("name");
		gAliveStatus.setImmediate(true);
		gAliveStatus.setNullSelectionAllowed(false);
		gAliveStatus.setRequired(true);
		gAliveStatus.setWidth("-1px");
		gAliveStatus.setHeight("-1px");
		gAliveStatus.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gAliveStatus);
		
		gOccupation = new ComboBox("อาชีพ",new Occupation());
		gOccupation.setInputPrompt("กรุณาเลือก");
		gOccupation.setItemCaptionPropertyId("name");
		gOccupation.setImmediate(true);
		gOccupation.setNullSelectionAllowed(false);
		gOccupation.setRequired(true);
		gOccupation.setWidth("-1px");
		gOccupation.setHeight("-1px");
		gOccupation.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gOccupation);
		
		gJobAddress = new TextArea("สถานที่ทำงาน");
		gJobAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		gJobAddress.setImmediate(false);
		gJobAddress.setWidth("-1px");
		gJobAddress.setHeight("-1px");
		gJobAddress.setNullRepresentation("");
		guardianForm.addComponent(gJobAddress);
		
		gCurrentAddress = new TextArea("ที่อยู่ปัจจุบัน");
		gCurrentAddress.setInputPrompt("บ้านเลขที่ ซอย ถนน");
		gCurrentAddress.setImmediate(false);
		gCurrentAddress.setRequired(true);
		gCurrentAddress.setWidth("-1px");
		gCurrentAddress.setHeight("-1px");
		gCurrentAddress.setNullRepresentation("");
		guardianForm.addComponent(gCurrentAddress);
		
		gCurrentProvinceId = new ComboBox("จังหวัด",new Province());
		gCurrentProvinceId.setInputPrompt("กรุณาเลือก");
		gCurrentProvinceId.setItemCaptionPropertyId("name");
		gCurrentProvinceId.setImmediate(true);
		gCurrentProvinceId.setNullSelectionAllowed(false);
		gCurrentProvinceId.setRequired(true);
		gCurrentProvinceId.setWidth("-1px");
		gCurrentProvinceId.setHeight("-1px");
		gCurrentProvinceId.setFilteringMode(FilteringMode.CONTAINS);
		gCurrentProvinceId.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null)
					gCurrentDistrict.setContainerDataSource(new District(Integer.parseInt(event.getProperty().getValue().toString())));
			}
		});
		guardianForm.addComponent(gCurrentProvinceId);
		
		gCurrentDistrict = new ComboBox("อำเภอ",new Blood());
		gCurrentDistrict.setInputPrompt("กรุณาเลือก");
		gCurrentDistrict.setItemCaptionPropertyId("name");
		gCurrentDistrict.setImmediate(true);
		gCurrentDistrict.setNullSelectionAllowed(false);
		gCurrentDistrict.setRequired(true);
		gCurrentDistrict.setWidth("-1px");
		gCurrentDistrict.setHeight("-1px");
		gCurrentDistrict.setFilteringMode(FilteringMode.CONTAINS);
		gCurrentDistrict.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null){
					gCurrentCity.setContainerDataSource(new City(Integer.parseInt(event.getProperty().getValue().toString())));
					gCurrentPostcode.setContainerDataSource(new Postcode(Integer.parseInt(event.getProperty().getValue().toString())));
				}
			}
		});
		guardianForm.addComponent(gCurrentDistrict);
		
		gCurrentCity = new ComboBox("ตำบล");
		gCurrentCity.setInputPrompt("กรุณาเลือก");
		gCurrentCity.setItemCaptionPropertyId("name");
		gCurrentCity.setImmediate(true);
		gCurrentCity.setNullSelectionAllowed(false);
		gCurrentCity.setRequired(true);
		gCurrentCity.setWidth("-1px");
		gCurrentCity.setHeight("-1px");
		gCurrentCity.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gCurrentCity);
		
		gCurrentPostcode = new ComboBox("รหัสไปรษณีย์");
		gCurrentPostcode.setInputPrompt("กรุณาเลือก");
		gCurrentPostcode.setItemCaptionPropertyId("name");
		gCurrentPostcode.setImmediate(true);
		gCurrentPostcode.setNullSelectionAllowed(false);
		gCurrentPostcode.setRequired(true);
		gCurrentPostcode.setWidth("-1px");
		gCurrentPostcode.setHeight("-1px");
		gCurrentPostcode.setFilteringMode(FilteringMode.CONTAINS);
		guardianForm.addComponent(gCurrentPostcode);
		
		guardianRelation = new ComboBox("ความสัมพันธ์ผู้ปกครอง",new GuardianRelation());
		guardianRelation.setInputPrompt("กรุณาเลือก");
		guardianRelation.setItemCaptionPropertyId("name");
		guardianRelation.setImmediate(true);
		guardianRelation.setNullSelectionAllowed(false);
		guardianRelation.setRequired(true);
		guardianRelation.setWidth("-1px");
		guardianRelation.setHeight("-1px");
		guardianRelation.setFilteringMode(FilteringMode.CONTAINS);
		guardianRelation.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() != null &&  studentItem != null)
					guardianRelation.setValue(Integer.parseInt(event.getProperty().getValue().toString()));
			}
		});
		guardianForm.addComponent(guardianRelation);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.setWidth("100%");
		guardianForm.addComponent(buttonLayout);
		
		motherBack = new Button(FontAwesome.ARROW_LEFT);
		motherBack.setWidth("100%");
		motherBack.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedTab(motherForm);
			}
		});
		buttonLayout.addComponents(motherBack);
		
		finish = new Button("ตกลง",FontAwesome.SAVE);
		finish.setWidth("100%");
		finish.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "deprecation" })
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if(fatherBinder.isValid() &&
							motherBinder.isValid() &&
							guardianBinder.isValid() &&
							studentBinder.isValid()){
						
						try {
							/*บันทึก ข้อมูล บิดา มารดา ผู้ปกครอง*/
							fatherBinder.commit();
							motherBinder.commit();
							if(isNewGuardian){
								guardianBinder.commit();
							}
							initContainerRowIdChanged();
							container.getRecruitFamilyContainer().commit();

							/* ตรวจสอบความสัมพันธ์ของผู้ปกครอง
							 *  กรณีเป็นบิดา gParents = 0 แปลว่าบันทึกซ้ำ ให้ลบออก
							 *  กรณีเป็นมารดา gParents = 1 แปลว่าบันทึกซ้ำ ให้ลบออก
							 * */
							if(gParents.getValue().toString().equals("0")){
								guardianId = fatherId;
							}else if(gParents.getValue().toString().equals("1")){
								guardianId = motherId;
							}
							
							/* กำหนดรหัสนักเรียน อยู่ในรูปแบบของ 581001 
							 * โดย 58 แสดงถึงปี พศ
							 *   1 แสดงถึงรหัสช่วงชั้น
							 *   001 แสดงถึงลำดับ
							 * */							
							SQLContainer freeFormContainer = container.getFreeFormContainer("SELECT * FROM stat_recruit_student_code WHERE school_id= "+
									UI.getCurrent().getSession().getAttribute(SessionSchema.SCHOOL_ID), StatRecruitStudentCodeSchema.MAX_CODE);
							int maxCode = 0;
							for(Object object:freeFormContainer.getItemIds())
								maxCode = Integer.parseInt(object.toString())+1;
							freeFormContainer.removeAllContainerFilters();
							
							String recruitCode = "";
							if(maxCode == 0){
								maxCode = new Date().getYear()+2443;
								recruitCode = Integer.toString(maxCode).substring(2)+studentBinder.getField(RecruitStudentSchema.CLASS_RANGE).getValue().toString()+"001";
							}else{
								recruitCode = Integer.toString(maxCode+1);
							}
							
							studentItem.getItemProperty(RecruitStudentSchema.FATHER_ID).setValue(Integer.parseInt(fatherId.toString()));
							studentItem.getItemProperty(RecruitStudentSchema.MOTHER_ID).setValue(Integer.parseInt(motherId.toString()));
							studentItem.getItemProperty(RecruitStudentSchema.GUARDIAN_ID).setValue(Integer.parseInt(guardianId.toString()));
							studentItem.getItemProperty(RecruitStudentSchema.REGISTER_DATE).setValue(new Date());
							studentItem.getItemProperty(RecruitStudentSchema.RECRUIT_CODE).setValue(recruitCode);
							studentItem.getItemProperty(RecruitStudentSchema.SCHOOL_ID).setValue(UI.getCurrent().getSession().getAttribute(SessionSchema.SCHOOL_ID));
							
							
							studentBinder.commit();
							container.getRecruitStudentContainer().commit();
							
							print.setVisible(true);
							new RecruitStudentReport(Integer.parseInt(studentId.toString()));	
							
							Notification.show("บันทึกสำเร็จ", Type.HUMANIZED_MESSAGE);
						} catch (Exception e) {
							Notification.show("บันทึกข้อมูลไม่สำเร็จ", Type.WARNING_MESSAGE);
							e.printStackTrace();
						}
					}else{
						Notification.show("กรุณาตรวจสอบข้อมูลให้ครบถ้วน", Type.WARNING_MESSAGE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		buttonLayout.addComponents(finish);
		
		print = new Button("พิมพ์ใบสมัคร",FontAwesome.PRINT);
		print.setVisible(false);
		print.setWidth("100%");
		buttonLayout.addComponents(print);
	}
	
	/*กำหนดค่าเริ่มต้นภายในฟอร์ม นักเรียน บิดา มารดา*/
	private void initFieldGroup(){		
		/*ตรวจสอบสถานะการจัดการข้อมูล
		 * กรณี "แก้ไข" editMode = true 
		 * กรณี "เพิ่ม" editMode = false  
		 * */
		if(editMode){
			SQLContainer sSqlCon = container.getRecruitStudentContainer();
			SQLContainer fSqlCon = container.getRecruitFamilyContainer();
			
			sSqlCon.getItem(new RowId(studentId));

			studentItem = sSqlCon.getItem(new RowId(studentId));
			fatherId = studentItem.getItemProperty(RecruitStudentSchema.FATHER_ID).getValue();
			motherId = studentItem.getItemProperty(RecruitStudentSchema.MOTHER_ID).getValue();
			guardianId = studentItem.getItemProperty(RecruitStudentSchema.GUARDIAN_ID).getValue();
			
			fatherItem = fSqlCon.getItem(new RowId(fatherId));
			motherItem = fSqlCon.getItem(new RowId(motherId));
			guardianItem = fSqlCon.getItem(new RowId(guardianId));	

		}else{
			SQLContainer sSqlCon = container.getRecruitStudentContainer();
			SQLContainer fSqlCon = container.getRecruitFamilyContainer();
			SQLContainer mSqlCon = container.getRecruitFamilyContainer();
			
			//เพิ่มข้อมูล นักเรียน
			Object tmpSItem = sSqlCon.addItem();
			studentItem = sSqlCon.getItem(tmpSItem);
			
			//เพิ่มข้อมูล นักเรียน
			Object tmpFItem = fSqlCon.addItem();
			fatherItem = fSqlCon.getItem(tmpFItem);
			
			//เพิ่มข้อมูล นักเรียน
			Object tmpMItem = mSqlCon.addItem();
			motherItem = mSqlCon.getItem(tmpMItem);
			
			/*สำหรับผู้ปกครองจะไม่เพิ่มในนี้ เพื่อป้องกันไม่ให้สร้างข้อมูลซ้ำกันระหว่าง พ่อ แม่่ ที่เลือกเป็นผู้ปกครอง 
			 *  เพิ่มกรณี เลือกผู้ปกครองเป็น อื่น ๆ
			 *  */
		}
		
		studentBinder = new FieldGroup(studentItem);
		studentBinder.setBuffered(true);
		studentBinder.bind(classRange, RecruitStudentSchema.CLASS_RANGE);
		studentBinder.bind(peopleIdType, RecruitStudentSchema.PEOPLE_ID_TYPE);
		studentBinder.bind(peopleId, RecruitStudentSchema.PEOPLE_ID);
		studentBinder.bind(prename, RecruitStudentSchema.PRENAME);
		studentBinder.bind(firstname, RecruitStudentSchema.FIRSTNAME);
		studentBinder.bind(lastname, RecruitStudentSchema.LASTNAME);
		studentBinder.bind(firstnameNd, RecruitStudentSchema.FIRSTNAME_ND);
		studentBinder.bind(lastnameNd, RecruitStudentSchema.LASTNAME_ND);
		studentBinder.bind(nickname, RecruitStudentSchema.NICKNAME);
		studentBinder.bind(gender, RecruitStudentSchema.GENDER);
		studentBinder.bind(religion, RecruitStudentSchema.RELIGION);
		studentBinder.bind(race, RecruitStudentSchema.RACE);
		studentBinder.bind(nationality, RecruitStudentSchema.NATIONALITY);
		studentBinder.bind(birthDate, RecruitStudentSchema.BIRTH_DATE);
		studentBinder.bind(blood, RecruitStudentSchema.BLOOD);
		studentBinder.bind(height, RecruitStudentSchema.HEIGHT);
		studentBinder.bind(weight, RecruitStudentSchema.WEIGHT);
		studentBinder.bind(congenitalDisease, RecruitStudentSchema.CONGENITAL_DISEASE);
		studentBinder.bind(interested, RecruitStudentSchema.INTERESTED);
		studentBinder.bind(siblingQty, RecruitStudentSchema.SIBLING_QTY);
		studentBinder.bind(siblingSequence, RecruitStudentSchema.SIBLING_SEQUENCE);
		studentBinder.bind(siblingInSchoolQty, RecruitStudentSchema.SIBLING_INSCHOOL_QTY);
		studentBinder.bind(graduatedSchool, RecruitStudentSchema.GRADUATED_SCHOOL);
		studentBinder.bind(graduatedSchoolProvinceId, RecruitStudentSchema.GRADUATED_SCHOOL_PROVINCE_ID);
		studentBinder.bind(graduatedGpa, RecruitStudentSchema.GRADUATED_GPA);
		studentBinder.bind(graduatedYear, RecruitStudentSchema.GRADUATED_YEAR);
		studentBinder.bind(tel, RecruitStudentSchema.TEL);
		studentBinder.bind(mobile, RecruitStudentSchema.MOBILE);
		studentBinder.bind(email, RecruitStudentSchema.EMAIL);
		studentBinder.bind(currentAddress, RecruitStudentSchema.CURRENT_ADDRESS);
		studentBinder.bind(currentProvinceId, RecruitStudentSchema.CURRENT_PROVINCE_ID);
		studentBinder.bind(currentDistrict, RecruitStudentSchema.CURRENT_DISTRICT_ID);
		studentBinder.bind(currentCity, RecruitStudentSchema.CURRENT_CITY_ID);
		studentBinder.bind(currentPostcode, RecruitStudentSchema.CURRENT_POSTCODE_ID);
		studentBinder.bind(familyStatus, RecruitStudentSchema.FAMILY_STATUS);
		studentBinder.bind(guardianRelation, RecruitStudentSchema.GUARDIAN_RELATION);
		
		fatherBinder = new FieldGroup(fatherItem);
		fatherBinder.setBuffered(true);
		fatherBinder.bind(fPeopleIdType, RecruitStudentFamilySchema.PEOPLE_ID_TYPE);
		fatherBinder.bind(fPeopleid, RecruitStudentFamilySchema.PEOPLE_ID);
		fatherBinder.bind(fPrename, RecruitStudentFamilySchema.PRENAME);
		fatherBinder.bind(fFirstname, RecruitStudentFamilySchema.FIRSTNAME);
		fatherBinder.bind(fLastname, RecruitStudentFamilySchema.LASTNAME);
		fatherBinder.bind(fFirstnameNd, RecruitStudentFamilySchema.FIRSTNAME_ND);
		fatherBinder.bind(fLastnameNd, RecruitStudentFamilySchema.LASTNAME_ND);
		fatherBinder.bind(fGender, RecruitStudentFamilySchema.GENDER);
		fatherBinder.bind(fReligion, RecruitStudentFamilySchema.RELIGION);
		fatherBinder.bind(fRace, RecruitStudentFamilySchema.RACE);
		fatherBinder.bind(fNationality, RecruitStudentFamilySchema.NATIONALITY);
		fatherBinder.bind(fBirthDate, RecruitStudentFamilySchema.BIRTH_DATE);
		fatherBinder.bind(fTel, RecruitStudentFamilySchema.TEL);
		fatherBinder.bind(fMobile, RecruitStudentFamilySchema.MOBILE);
		fatherBinder.bind(fEmail, RecruitStudentFamilySchema.EMAIL);
		fatherBinder.bind(fSalary, RecruitStudentFamilySchema.SALARY);
		fatherBinder.bind(fAliveStatus, RecruitStudentFamilySchema.ALIVE_STATUS);
		fatherBinder.bind(fOccupation, RecruitStudentFamilySchema.OCCUPATION);
		fatherBinder.bind(fJobAddress, RecruitStudentFamilySchema.JOB_ADDRESS);
		fatherBinder.bind(fCurrentAddress, RecruitStudentFamilySchema.CURRENT_ADDRESS);
		fatherBinder.bind(fCurrentProvinceId, RecruitStudentFamilySchema.CURRENT_PROVINCE_ID);
		fatherBinder.bind(fCurrentDistrict, RecruitStudentFamilySchema.CURRENT_DISTRICT_ID);
		fatherBinder.bind(fCurrentCity, RecruitStudentFamilySchema.CURRENT_CITY_ID);
		fatherBinder.bind(fCurrentPostcode, RecruitStudentFamilySchema.CURRENT_POSTCODE_ID);
		
		motherBinder = new FieldGroup(motherItem);
		motherBinder.setBuffered(true);
		motherBinder.bind(mPeopleIdType, RecruitStudentFamilySchema.PEOPLE_ID_TYPE);
		motherBinder.bind(mPeopleid, RecruitStudentFamilySchema.PEOPLE_ID);
		motherBinder.bind(mPrename, RecruitStudentFamilySchema.PRENAME);
		motherBinder.bind(mFirstname, RecruitStudentFamilySchema.FIRSTNAME);
		motherBinder.bind(mLastname, RecruitStudentFamilySchema.LASTNAME);
		motherBinder.bind(mFirstnameNd, RecruitStudentFamilySchema.FIRSTNAME_ND);
		motherBinder.bind(mLastnameNd, RecruitStudentFamilySchema.LASTNAME_ND);
		motherBinder.bind(mGender, RecruitStudentFamilySchema.GENDER);
		motherBinder.bind(mReligion, RecruitStudentFamilySchema.RELIGION);
		motherBinder.bind(mRace, RecruitStudentFamilySchema.RACE);
		motherBinder.bind(mNationality, RecruitStudentFamilySchema.NATIONALITY);
		motherBinder.bind(mBirthDate, RecruitStudentFamilySchema.BIRTH_DATE);
		motherBinder.bind(mTel, RecruitStudentFamilySchema.TEL);
		motherBinder.bind(mMobile, RecruitStudentFamilySchema.MOBILE);
		motherBinder.bind(mEmail, RecruitStudentFamilySchema.EMAIL);
		motherBinder.bind(mSalary, RecruitStudentFamilySchema.SALARY);
		motherBinder.bind(mAliveStatus, RecruitStudentFamilySchema.ALIVE_STATUS);
		motherBinder.bind(mOccupation, RecruitStudentFamilySchema.OCCUPATION);
		motherBinder.bind(mJobAddress, RecruitStudentFamilySchema.JOB_ADDRESS);
		motherBinder.bind(mCurrentAddress, RecruitStudentFamilySchema.CURRENT_ADDRESS);
		motherBinder.bind(mCurrentProvinceId, RecruitStudentFamilySchema.CURRENT_PROVINCE_ID);
		motherBinder.bind(mCurrentDistrict, RecruitStudentFamilySchema.CURRENT_DISTRICT_ID);
		motherBinder.bind(mCurrentCity, RecruitStudentFamilySchema.CURRENT_CITY_ID);
		motherBinder.bind(mCurrentPostcode, RecruitStudentFamilySchema.CURRENT_POSTCODE_ID);

		initGuardianFieldGroup();
		whoisGuardian();
		testData();
	}
	
	/*กำหนดค่าเริ่มต้นภายในฟอร์ม ผู้ปกครอง*/
	private void initGuardianFieldGroup(){	
		guardianBinder = new FieldGroup(guardianItem);
		guardianBinder.setBuffered(true);
		guardianBinder.bind(gPeopleIdType, RecruitStudentFamilySchema.PEOPLE_ID_TYPE);
		guardianBinder.bind(gPeopleid, RecruitStudentFamilySchema.PEOPLE_ID);
		guardianBinder.bind(gPrename, RecruitStudentFamilySchema.PRENAME);
		guardianBinder.bind(gFirstname, RecruitStudentFamilySchema.FIRSTNAME);
		guardianBinder.bind(gLastname, RecruitStudentFamilySchema.LASTNAME);
		guardianBinder.bind(gFirstnameNd, RecruitStudentFamilySchema.FIRSTNAME_ND);
		guardianBinder.bind(gLastnameNd, RecruitStudentFamilySchema.LASTNAME_ND);
		guardianBinder.bind(gGender, RecruitStudentFamilySchema.GENDER);
		guardianBinder.bind(gReligion, RecruitStudentFamilySchema.RELIGION);
		guardianBinder.bind(gRace, RecruitStudentFamilySchema.RACE);
		guardianBinder.bind(gNationality, RecruitStudentFamilySchema.NATIONALITY);
		guardianBinder.bind(gBirthDate, RecruitStudentFamilySchema.BIRTH_DATE);
		guardianBinder.bind(gTel, RecruitStudentFamilySchema.TEL);
		guardianBinder.bind(gMobile, RecruitStudentFamilySchema.MOBILE);
		guardianBinder.bind(gEmail, RecruitStudentFamilySchema.EMAIL);
		guardianBinder.bind(gSalary, RecruitStudentFamilySchema.SALARY);
		guardianBinder.bind(gAliveStatus, RecruitStudentFamilySchema.ALIVE_STATUS);
		guardianBinder.bind(gOccupation, RecruitStudentFamilySchema.OCCUPATION);
		guardianBinder.bind(gJobAddress, RecruitStudentFamilySchema.JOB_ADDRESS);
		guardianBinder.bind(gCurrentAddress, RecruitStudentFamilySchema.CURRENT_ADDRESS);
		guardianBinder.bind(gCurrentProvinceId, RecruitStudentFamilySchema.CURRENT_PROVINCE_ID);
		guardianBinder.bind(gCurrentDistrict, RecruitStudentFamilySchema.CURRENT_DISTRICT_ID);
		guardianBinder.bind(gCurrentCity, RecruitStudentFamilySchema.CURRENT_CITY_ID);
		guardianBinder.bind(gCurrentPostcode, RecruitStudentFamilySchema.CURRENT_POSTCODE_ID);

	}
	
	/*กำหนดค่าในฟอร์มผู้ปกครอง
	 * กรณี เลือกเป็น บิดา มารดา
	 * */
	private void initGuardianData(FieldGroup binder){	
		PropertysetItem item = new PropertysetItem();		
		for(Field<?> field:binder.getFields()){
			if(binder.getField(binder.getPropertyId(field)).getValue() != null)
				item.addItemProperty(binder.getPropertyId(field), new ObjectProperty<Object>(binder.getField(binder.getPropertyId(field)).getValue()));
			else{
				item.addItemProperty(binder.getPropertyId(field), new ObjectProperty<Object>(""));
			}
		}
		guardianBinder.setItemDataSource(item);
	}
	
	/*ตรวจสอบสถานะผู้ปกครอง
	 * หากความสัมพันธ์ เป็น บิดา หรือ มารดา ผู้ปกครองจะถูกเลือกเป็น บิดา/มารดา
	 * หากเป็นอื่น ๆ  ผู้ปกครองจะถูกเลือกเป็น อื่น ๆ 
	 * */
	private void whoisGuardian(){
		if(editMode){
			if(studentItem != null){
				int gRelation = Integer.parseInt(studentItem.getItemProperty(RecruitStudentSchema.GUARDIAN_RELATION).getValue().toString());
				
				guardianId =  Integer.parseInt(studentItem.getItemProperty(RecruitStudentSchema.GUARDIAN_ID).getValue().toString());
				fatherId = Integer.parseInt(studentItem.getItemProperty(RecruitStudentSchema.FATHER_ID).getValue().toString());
				motherId = Integer.parseInt(studentItem.getItemProperty(RecruitStudentSchema.MOTHER_ID).getValue().toString()); 
				
				//Guardian relation (father or mother)
				/*ตรวจความสัมพันธ์ของผู้ปกครอง
				 *  กรณี เป็น "บิดา/มารดา" gRelation == 0 
				 *  กรณี เป็น "อื่น ๆ" 
				 * */				
				if(gRelation == 0){
					//Select who is guardian (father,mother)
					if(guardianId.equals(fatherId)){
						gParents.setValue(0);
					}else if(guardianId.equals(motherId)){
						gParents.setValue(1);
					}
				}else{
					//Select who is guardian (other)
					gParents.setValue(2);
				}
			}
		}
	}
	
	/*อนุญาติแก้ไขฟอร์ม ผู้ปกครอง
	 * กรณี เลือกผู้ปกครองเป็นอื่น ๆ 
	 * */
	private void enableGuardianBinder(){
		guardianBinder.setEnabled(true);
		guardianBinder.setReadOnly(false);
		guardianRelation.setEnabled(true);
		guardianRelation.setReadOnly(false);
	}
	
	/*ปิดการแก้ไขฟอร์ม ผู้ปกครอง
	 * กรณี เลือกผู้ปกครองเป็น บิดา มารดา
	 * */
	private void disableGuardianBinder(){
		guardianRelation.setValue(0);
		guardianBinder.setEnabled(false);
		guardianBinder.setReadOnly(true);
		guardianRelation.setEnabled(false);
		guardianRelation.setReadOnly(true);
	}
	
	/*กำนด RowIdChangeListener ของ  Container หากมีการ commit*/
	private void initContainerRowIdChanged(){
		container.getRecruitFamilyContainer().addRowIdChangeListener(new RowIdChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void rowIdChange(RowIdChangeEvent arg0) {
				rowIds.add(arg0.getNewRowId());
				if(isNewGuardian && editMode){
					guardianId = arg0.getNewRowId();	
				}else{
					if(rowIds.size() == 1)
						fatherId = arg0.getNewRowId();
					else if(rowIds.size() == 2)
						motherId = arg0.getNewRowId();
					else if(rowIds.size() == 3)
						guardianId = arg0.getNewRowId();	
				}
			}
		});
		
		container.getRecruitStudentContainer().addRowIdChangeListener(new RowIdChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void rowIdChange(RowIdChangeEvent arg0) {
				studentId = arg0.getNewRowId();
			}
		});
	}
	
	/*กรณีทดสอบ ของการเพิ่มข้อมูล*/
	public void testData(){
		classRange.setValue(0);
		peopleIdType.setValue(0);
		peopleId.setValue("1959900163320");
		prename.setValue(0);
		firstname.setValue("sfasf");
		lastname.setValue("asdfdasf");
		firstnameNd.setValue("asdfdasf");
		lastnameNd.setValue("asdfdasf");
		nickname.setValue("asdfdasf");
		gender.setValue(0);
		religion.setValue(0);
		race.setValue(0);
		nationality.setValue(0);
		birthDate.setValue(new Date());
		blood.setValue(0);
		height.setValue("0");
		weight.setValue("0");
		congenitalDisease.setValue("");
		interested.setValue("");
		siblingQty.setValue("0");
		siblingSequence.setValue("0");
		siblingInSchoolQty.setValue("0");
		graduatedSchool.setValue("asdfdasf");
		graduatedSchoolProvinceId.setValue(1);
		graduatedGpa.setValue("2.5");
		graduatedYear.setValue("2554");
		tel.setValue("0897375348");
		mobile.setValue("0897375348");
		email.setValue("asdfdasf@dsrgf.com");
		currentAddress.setValue("aasdfadsf");
		currentProvinceId.setValue(8);
		currentDistrict.setValue(109);
		currentCity.setValue(860);
		currentPostcode.setValue(119);

		fPeopleIdType.setValue(0);
		fPeopleid.setValue("1959900163320");
		fPrename.setValue(0);
		fFirstname.setValue("asfadsf");
		fLastname.setValue("asdfdasf");
		fFirstnameNd.setValue("asdfadsf");
		fLastnameNd.setValue("asdfdasf");
		fGender.setValue(0);
		fReligion.setValue(0);
		fRace.setValue(0);
		fNationality.setValue(0);
		fBirthDate.setValue(new Date());
		fTel.setValue("0732174283");
		fMobile.setValue("0897375348");
		fEmail.setValue("asdfdas@asdf.com");
		fSalary.setValue("0");
		fAliveStatus.setValue(0);
		fOccupation.setValue(0);
		fJobAddress.setValue("asfdasf");
		fCurrentAddress.setValue("asfdasf");
		fCurrentProvinceId.setValue(1);
		fCurrentDistrict.setValue(1);
		fCurrentCity.setValue(1);
		fCurrentPostcode.setValue(1);

		mPeopleIdType.setValue(0);
		mPeopleid.setValue("1959900163320");
		mPrename.setValue(0);
		mFirstname.setValue("asfadsf");
		mLastname.setValue("asdfdasf");
		mFirstnameNd.setValue("asdfadsf");
		mLastnameNd.setValue("asdfdasf");
		mGender.setValue(0);
		mReligion.setValue(0);
		mRace.setValue(0);
		mNationality.setValue(0);
		mBirthDate.setValue(new Date());
		mTel.setValue("0732174283");
		mMobile.setValue("0897375348");
		mEmail.setValue("asdfdas@asdf.com");
		mSalary.setValue("0");
		mAliveStatus.setValue(0);
		mOccupation.setValue(0);
		mJobAddress.setValue("asfdasf");
		mCurrentAddress.setValue("asfdasf");
		mCurrentProvinceId.setValue(1);
		mCurrentDistrict.setValue(1);
		mCurrentCity.setValue(1);
		mCurrentPostcode.setValue(1);
		
		gPeopleIdType.setValue(0);
		gPeopleid.setValue("1959900163320");
		gPrename.setValue(0);
		gFirstname.setValue("asfadsf");
		gLastname.setValue("asdfdasf");
		gFirstnameNd.setValue("asdfadsf");
		gLastnameNd.setValue("asdfdasf");
		gGender.setValue(0);
		gReligion.setValue(0);
		gRace.setValue(0);
		gNationality.setValue(0);
		gBirthDate.setValue(new Date());
		gTel.setValue("0732174283");
		gMobile.setValue("0897375348");
		gEmail.setValue("asdfdas@asdf.com");
		gSalary.setValue("0");
		gAliveStatus.setValue(0);
		gOccupation.setValue(0);
		gJobAddress.setValue("asfdasf");
		gCurrentAddress.setValue("asfdasf");
		gCurrentProvinceId.setValue(1);
		gCurrentDistrict.setValue(1);
		gCurrentCity.setValue(1);
		gCurrentPostcode.setValue(1);
		
		gParents.setValue(0);
		familyStatus.setValue(0);
		guardianRelation.setValue(0);
	}
}
