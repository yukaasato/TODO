package com.todo.base.form;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

//3 SpringBooot�p�ɃR���|�[�l���g�̖ڈ������
@Component
public class reviewForm {

	
	//�ϐ�������
		//�f�[�^�x�[�X�ł����ԍ��������ō~���Ă�������
		//�C���N�������g
		@Id
		//��
		@Column(name = "REVIEW_ID")
		@NotNull
		private int review_id;


		//���[�U�[�����͂�����̂��󂯎�锠
		@NotNull
		@Size(min = 1, max = 60, message = "Title�͕K�{")
		@Column(name = "REVIEW_TITLE") 
		private String review_title;


		public int getReview_id() {
			return review_id;
		}


		public void setReview_id(int review_id) {
			this.review_id = review_id;
		}


		public String getReview_title() {
			return review_title;
		}


		public void setReview_title(String review_title) {
			this.review_title = review_title;
		}


		

	//getter��setter���쐬����
	
		//4.�e�[�u�������
		/*
		 * CREATE TABLE REVIEW //�e�[�u������REVIEW
		 *  (				//�J�������`���Ă���
		 *  REWIEW_ID INT(7) PRIMARY KEY AUTO_INCREMENT,//�v���C�}���[�L�[�A������++���Ă���
		 *  REVIEW_TITLE VARCHAR(30) NOT NULL//�^�C�g���K�{�A������30,CHAR�Ƃ̈Ⴂ�����x�}����
		 *  );
		 *  CREATE TABLE REVIEW (REWIEW_ID INT(7) PRIMARY KEY AUTO_INCREMENT,REVIEW_TITLE VARCHAR(30));
		 */
		
	//5.HTML������
	
}
