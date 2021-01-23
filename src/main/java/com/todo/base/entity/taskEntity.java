package com.todo.base.entity;
//�c��������util
import java.util.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "TODO")
public class taskEntity {
	
	    //���j�[�N�L�[
		@Id
		@Column(name = "TODO_ID")		
		@NotNull
		private int todo_id;
		
		
		@Column(name = "TODO_TITLE")
		private String todo_title;

		@Column(name = "TODO_DATE")
		private Date  todo_date;
		
		
		@Column(name = "TODO_TIME") 
		private Time todo_time;
		 
		
		@Column(name = "TODO_PLACE")
		private String todo_place;
		
		
		@Column(name = "COMPLETE_FLAG")
		private Boolean complete_flag;
		
		
		@Column(name = "DELETE_FLAG")
		private Boolean delete_flag;
		
		
	//��؂���ɕ���
		 public Date getTodo_date() { 
			 return todo_date; 
		}
		  
		  public void setTodo_date(Date todo_date) {
			  this.todo_date = todo_date; 
		}
		 

		
		
		//NotNull 
		//set���Ȃ��œ����f�[�^
		//�쐬�������Ɏ����ł���Entitiy�ɂ������C���[�W�Adao�ɂ��̂܂ܓn�����
		/*
		 * @PrePersist public void onPrePersistTime() { //�^�C���X�^���v�^ �Ƀf�[�g�^���L���X�g���Ă���
		 * setTime(new Time((new Date()).getTime())); }
		 */
		/*
		 * @PrePersist public void onPrePersistCreateTime() { //�쐬���� setCreate_time(new //PrePersist��2�񂷂�ƃG���[�ł�
		 * Time((new Date()).getTime())); }
		 */
		
		
		/*
		 * @PrePersist public void onPrePersistCompleteTime() { //��������
		 * setComplete_time(new Time((new Date()).getTime())); }
		 */
		


		public Boolean getComplete_flag() {
			return complete_flag;
		}

		public void setComplete_flag(Boolean complete_flag) {
			this.complete_flag = complete_flag;
		}

		public Boolean getDelete_flag() {
			return delete_flag;
		}

		public void setDelete_flag(Boolean delete_flag) {
			this.delete_flag = delete_flag;
		}

		public String getTodo_title() {
			return todo_title;
		}

		public void setTodo_title(String todo_title) {
			this.todo_title = todo_title;
		}



		public String getTodo_place() {
			return todo_place;
		}

		public void setTodo_place(String todo_place) {
			this.todo_place = todo_place;
		}

		public int getTodo_id() {
			return todo_id;
		}

		public void setTodo_id(int todo_id) {
			this.todo_id = todo_id;
		}

		//�m�F
		public Time getTodo_time() {
			return todo_time;
		}

		public void setTodo_time(Time todo_time) {
			this.todo_time = todo_time;
		}

		


}
