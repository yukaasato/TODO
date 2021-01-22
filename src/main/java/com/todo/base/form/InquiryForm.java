package com.todo.base.form;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

/**
 * �t�H�[������̓��͒l���܂Ƃ߂�N���X
 */

@Component
public class InquiryForm {
	
	//�C���N�������g
	@Id
	//��
	@Column(name = "INQUIRY_ID")
	@NotNull
	private int inquiryId;
	
	@Column(name = "INQUIRY_TYPE")
	@NotNull
	private int inquiryType;

	@Size(min = 1, max = 60, message = "�������O����͂��Ă�������")
	@Column(name = "INQUIRY_USER")
	@NotNull
	private String inquiryUser;

	@Size(min = 1, max = 60, message = "�����[���A�h���X����͂��Ă�������")
	@Email(message = "�����[���A�h���X�̌`���œ��͂��Ă��������B")
	@Column(name = "INQUIRY_USER_MAIL")
	@NotNull
	private String inquiryUserMail;

	@Size(min = 30, max = 3000, message = "��30�����ȏ�3000�����ȉ��œ��͂��Ă��������B")
	@Column(name = "INQUIRY_INFO")
	@NotNull
	private String inquiryInfo;

	@Column(name = "INQUIRY_TIME")
	@NotNull
	private String inquiryTime;

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
	
	public String getInquiryTime() {
		return inquiryTime;
	}

	public void setInquiryTime(String inquiryTime) {
		this.inquiryTime = inquiryTime;
	}
	
}

