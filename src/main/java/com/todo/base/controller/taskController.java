package com.todo.base.controller;

import java.sql.Date;
import java.sql.Time;
//import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.base.URL;
import com.todo.base.entity.taskEntity;
import com.todo.base.form.taskForm;
import com.todo.base.service.taskAccessService;

@Controller
//�ϐ��g���A�ėp������
//GetPost�Ɋւ�炸������o�R����AURL���������ɕ֗�
@RequestMapping(URL.TODO)
public class taskController {

	@Autowired
	taskAccessService taskAcsServ;


	//�z�[��
	@GetMapping(URL.LIST)
	//litForm�����
	public String displayList(Model model,taskForm taskForm) {

		//DB����f�[�^�T���Ă���
		List<taskEntity> todolists =taskAcsServ.searchAll();

		model.addAttribute("todolists",todolists);

		//�^�C�g���ǉ�
		model.addAttribute("title", "toDoList");

		//http://localhost:8080/todo/list
		return URL.TODO + URL.LIST;

	}

	//�쐬������
	@PostMapping(URL.CREATE)
	public String complete( @Validated taskForm taskForm, 
			Model model) {	

		//�G���e�B�e�B�̃C���X�^���X�쐬
		taskEntity taskEntity = new taskEntity();

		//�G���e�B�e�B�Ƀ^�C�g����n��
		taskEntity.setTodo_title(taskForm.getTodo_title());

		//���t������i���́j
		//taskEntity.setTodo_date(taskEntity.getTodo_date());

		//DB�փC���T�[�g����
		taskAcsServ.taskInsert(taskEntity);


		model.addAttribute("title", "toDoList");


		//http://localhost:8080/todo/list
		//return URL.TODO + URL.LIST;
		return "redirect:/todo/list";

	}

	/*
	 * �ڍ׉�ʂ֑J��
	 * �f�[�^���ꌏ�擾���鏈��
	 * �f�[�^�x�[�X�ɂ�todo_id��form����󂯎�������Where�ōi�荞��
	 * */
	@GetMapping(URL.DETAILS)
	public String displayDtl(taskForm taskForm, Model model) {

		//���܂����f�[�^�\��
		taskEntity oneTask = taskAcsServ.findTask(taskForm.getTodo_id());

		//�^�C�g��
		model.addAttribute("title","My Task");

		//Entity��n��
		model.addAttribute("oneTask", oneTask);

		//�ڍ׉�ʂ�
		return URL.TODO + URL.DETAILS;


	}

	/**
	 * �ڍ׉�ʂ���z�[���ɖ߂����Ƃ��̏���
	 * @param Form�ɓ����Ă�����̂�ێ�
	 * @param model�@title��n��
	 * @return list���
	 */

	@PostMapping(URL.LIST)
	public String taskUpdate(@Validated taskForm taskForm, Model model) {

		//update���邽��
		//DB�֓n��Entitiy
		taskEntity updateEntity = new taskEntity();

		//id������
		updateEntity.setTodo_id(taskForm.getTodo_id());

		//�^�C�g�����X�V�������
		updateEntity.setTodo_title(taskForm.getTodo_title());

		//�ꏊ���X�V�������
		updateEntity.setTodo_place(taskForm.getTodo_place());

		/**
		 * �p�����[�^�[�@�Fyyyy/MM/dd
		 * �߂�l�@�Fyyyy/MM/dd 
		 * ������̓��t��java.sql.Date�ɕϊ�����
		 * java.util.Date�ł͂Ȃ��B
		 * sql�p�b�P�[�W��Date�̓f�[�^�x�[�X�̃f�[�^�^��date�ƌ݊���������܂�
		 *  �f�[�^�x�[�X�̓��t��Java�ŏ����������ꍇ�Ɏg�p���܂��B
		 */
		//���t������
		updateEntity.setTodo_date(Date.valueOf(taskForm.getTodo_date()));


		//updateEntity.setTodo_time(Time.valueOf((taskForm.getTodo_time())));�������ł��Ȃ��Ĉȉ��ň�U

		/**
		 * valueOf public static Time valueOf(String s) JDBC���ԃG�X�P�[�v�`�����̕������Time�l�ɕϊ����܂��B
		 * �p�����[�^: s - hh:mm:ss�`���̎��� �߂�l: �Ή�����Time�I�u�W�F�N�g
		 */

		//�����͂�������
		if(taskForm.getTodo_time().length() < 6 ) {

			StringBuilder buf = new StringBuilder();
			//hh:mm
			buf.append(taskForm.getTodo_time());	
			//:ss
			buf.append(":00");
			//00������ɂ��āAhh:mm:ss�̃X�g�����O���^�C���^�ϊ�
			updateEntity.setTodo_time(Time.valueOf((buf.toString())));

			//���ɓ��͂���Ă�����
		}else{
			//hh:mm:ss�̃X�g�����O���^�C���^�ϊ�
			updateEntity.setTodo_time(Time.valueOf(taskForm.getTodo_time()));	

		}

		//�f�[�^���X�V����
		taskAcsServ.taskUpdatek(updateEntity); 


		//�S�f�[�^���擾���Ă���
		//DB����f�[�^�T���Ă���
		List<taskEntity> todolists =taskAcsServ.searchAll();

		model.addAttribute("todolists",todolists);

		//�^�C�g���ǉ�
		model.addAttribute("title", "toDoList");

		//http://localhost:8080/todo/list
		//return URL.TODO + URL.LIST;
		return "redirect:/todo/list";
	}






}
