package com.todo.base.entity;


/**
 * 
 * ���₢���킹���Entity
 * ���ۂ�DB�Ɠ�����������
 * @author suzuki
 *
 */


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
//�G���e�B�e�B�͒l���i�[��Z�b�g����΂���
//���Ƃ�InquiryForm.java�Ɠ���


//�G���e�B�e�B form�̈Ⴂ
@Entity
//�e�[�u������inauiry SQL�͑啶����������ʂȂ��@DB�ƕR�Â�
@Table(name = "INQUIRY")

public class InquiryEntity {
	
	//���j�[�N�L�[
	@Id
	//DB�̗񖼂�name�ɂ͂����Ă���@DB�ƕR�Â�
	@Column(name = "INQUIRY_ID")
	//�����SQL�̃e�[�u���ƈꏏ�ɂ���A�f�[�^�x�[�X�̎��Ԃƈꏏ�ɂ���
	@NotNull
	//������set�����
	private int inquiryId;
	
	@Column(name = "INQUIRY_TYPE")
	@NotNull
	private int inquiryType;

	@Column(name = "INQUIRY_USER")
	@NotNull
	private String inquiryUser;

	@Column(name = "INQUIRY_USER_MAIL")
	@NotNull
	private String inquiryUserMail;

	@Column(name = "INQUIRY_INFO")
	@NotNull
	private String inquiryInfo;

	@Column(name = "INQUIRY_TIME")
	@NotNull
	private Timestamp inquiryTime;
	public int getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}

	public int getInquiryType() {
		return inquiryType;
	}

	public void setInquiryType(int inquiryType) {
		this.inquiryType = inquiryType;
	}

	public String getInquiryUser() {
		return inquiryUser;
	}

	public void setInquiryUser(String inquiryUser) {
		this.inquiryUser = inquiryUser;
	}


	public String getInquiryUserMail() {
		return inquiryUserMail;
	}

	public void setInquiryUserMail(String inquiryUserMail) {
		this.inquiryUserMail = inquiryUserMail;
	}

	public String getInquiryInfo() {
		return inquiryInfo;
	}

	public void setInquiryInfo(String inquiryInfo) {
		this.inquiryInfo = inquiryInfo;
	}
	
	public Timestamp getInquiryTime() {
		return inquiryTime;
	}

	public void setInquiryTime(Timestamp inquiryTime) {
		this.inquiryTime = inquiryTime;
	}
	
	//NotNull 
	//���O�ɓ����f�[�^
	@PrePersist
	public void onPrePersist() {						//�^�C���X�^���v�^�@�Ƀf�[�g�^���L���X�g���Ă���
		setInquiryTime(new Timestamp((new Date()).getTime()));
	}
	

}
