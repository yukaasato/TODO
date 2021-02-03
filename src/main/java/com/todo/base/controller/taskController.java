package com.todo.base.controller;

import java.sql.Date;
import java.sql.Time;
//import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.base.URL;
import com.todo.base.entity.taskEntity;
import com.todo.base.form.InquiryForm;
import com.todo.base.form.taskForm;
import com.todo.base.service.taskAccessService;

@Controller
//�ϐ��g���A�ėp������
//GetPost�Ɋւ�炸������o�R����AURL���������ɕ֗�
@RequestMapping(URL.TODO)
public class taskController {

	@Autowired
	taskAccessService taskAcsServ;

/*******************************************************************************/
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

	/*******************************************************************************/
	//�쐬������
	@PostMapping(URL.CREATE)							//Form�̌�ɕK���u���A�łȂ��ƃG���[�ł�
	public String complete( @Validated taskForm taskForm,BindingResult result, 
			Model model) {	
		
		if(result.hasErrors()) {
			model.addAttribute("title", "toDoList");
			
			List<taskEntity> todolists =taskAcsServ.searchAll();
			
			model.addAttribute("todolists",todolists);
			
			return URL.TODO + URL.LIST;
			}
		
		
		//�G���e�B�e�B�̃C���X�^���X�쐬
		taskEntity taskEntity = new taskEntity();

		//�G���e�B�e�B�Ƀ^�C�g����n��
		taskEntity.setTodo_title(taskForm.getTodo_title());
		
		//�t���O��0������@���f�t�H���g��Null�ɂȂ��Ă��܂�����
		taskEntity.setComplete_flag(false);
		taskEntity.setDelete_flag(false);
	
		//���t������i���́j
		//taskEntity.setTodo_date(taskEntity.getTodo_date());

		//DB�փC���T�[�g����
		taskAcsServ.addTask(taskEntity);


		model.addAttribute("title", "toDoList");


		//http://localhost:8080/todo/list
		//return URL.TODO + URL.LIST;
		return "redirect:/todo/list";

	}

	/*******************************************************************************/
	/**
	 * �ڍ׉�ʂ֑J��
	 * �f�[�^���ꌏ�擾���鏈��
	 * �f�[�^�x�[�X�ɂ�todo_id��form����󂯎�������Where�ōi�荞��
	 * */
	@GetMapping(URL.DETAILS)
	public String displayDtl(taskForm taskForm, Model model) {
		
		//�f�[�^�����
		taskEntity detailEntity =new taskEntity();
		
		//id�t�H�[������G���e�B�e�B�փZ�b�g����
		detailEntity.setTodo_id(taskForm.getTodo_id());
		
		//���܂����f�[�^�\��
		taskEntity oneTask = taskAcsServ.findTask(detailEntity.getTodo_id());

		//�^�C�g��
		model.addAttribute("title","My Task");

		//Entity��n��
		model.addAttribute("oneTask", oneTask);

		//�ڍ׉�ʂ�
		return URL.TODO + URL.DETAILS;


	}

	
	/*******************************************************************************/
	/**
	 * �A�b�v�f�[�g����
	 * @param Form �ɓ����Ă�����̂�ێ�
	 * @param model�@title��n��
	 * @return list���
	 */

	@PostMapping(URL.LIST)
	public String taskUpdate(@Validated taskForm taskForm,BindingResult result, Model model) {

		if(result.hasErrors()) {
			
			taskEntity detailEntity =new taskEntity();
			
			//id�t�H�[������G���e�B�e�B�փZ�b�g����
			detailEntity.setTodo_id(taskForm.getTodo_id());
			
			//���܂����f�[�^�\��
			taskEntity oneTask = taskAcsServ.findTask(detailEntity.getTodo_id());

			//�^�C�g��
			model.addAttribute("title","My Task");

			//Entity��n��
			model.addAttribute("oneTask", oneTask);

			//�ڍ׉�ʂ�
			return URL.TODO + URL.DETAILS;

		}

		//update���邽��
		//�G���e�B�e�B�̃C���X�^���X�쐬
		taskEntity updateEntity = new taskEntity();

		//id��Form����G���e�B�e�B�֓n��
		updateEntity.setTodo_id(taskForm.getTodo_id());

		//�^�C�g����Form����G���e�B�e�B�֓n��
		updateEntity.setTodo_title(taskForm.getTodo_title());

		//�ꏊ��Form����G���e�B�e�B�֓n��
		updateEntity.setTodo_place(taskForm.getTodo_place());

		
		//���t��Form����G���e�B�e�B�֓n��
		//���͂���Ă�����
		if(taskForm.getTodo_date().length() == 10 ) {
			
			//Form��String�^����Date�^�ɕϊ����A�G���e�B�e�B�֓n��
			updateEntity.setTodo_date(Date.valueOf(taskForm.getTodo_date()));
			
		}


		//���Ԃ�Form����G���e�B�e�B�֓n��
		//���͂���Ă�����i����j
		if(taskForm.getTodo_time().length() == 5 ) {
			
			//�菇1.�@������java��SQL�ň�v������
			//������̘A���ׂ̈ɃC���X�^���X����������
			StringBuilder buf = new StringBuilder();
			
			//hh:mm�@�ɓ����鎞�Ԃ� buf �ɓ����
			buf.append(taskForm.getTodo_time());	
			
			//:ss�@�ɂ����镶����@00�� buf �ɓ����
			buf.append(":00");
			
			
			//�菇2.�@�^��java��SQL�ň�v������
			//hh:mm:ss�̃X�g�����O���^�C���^�ϊ�
			updateEntity.setTodo_time(Time.valueOf((buf.toString())));
		}
		
		//���ɓ��͂���Ă�����
		if(taskForm.getTodo_time().length() == 9 ) {
			
			//hh:mm:ss�̃X�g�����O���^�C���^�ϊ�
			updateEntity.setTodo_time(Time.valueOf(taskForm.getTodo_time()));	
		}
		
		//�󂾂�����""�łƂ�Ă���
		//��U�������Ȃ��ł����A�c�a�ɂ�NULL�œo�^�����
		
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
	
	/*******************************************************************************/
	@PostMapping(URL.DELETE)
	public String taskDelete (taskForm taskForm, Model model) {

		//�폜�p�̃f�[�^�����
		taskEntity deleteEntity =new taskEntity();
		
		//Form����id���G���e�B�e�B�փZ�b�g����
		deleteEntity.setTodo_id(taskForm.getTodo_id());
		
		//�A�N�Z�X�N���X���g���č폜����=�t���O��؂�ւ���
		taskAcsServ.taskDelete(deleteEntity.getTodo_id()); 
		
		//�f�[�^��ǂݍ���
		
		//�S�f�[�^���擾���Ă���
		//DB����f�[�^�T���Ă���
		List<taskEntity> todolists =taskAcsServ.searchAll();

		//�S�f�[�^�̃��X�g��HTML���ֈ����n��
		model.addAttribute("todolists",todolists);

		//�^�C�g���ǉ�
		model.addAttribute("title", "toDoList");
		
		
		return URL.TODO + URL.LIST;
	}
	
	/*******************************************************************************/
	@PostMapping(URL.COMPLETE)
	public String taskComplete(taskForm taskForm, Model model) {

		//�����p�̃f�[�^�����
		taskEntity completeEntity =new taskEntity();
		
		//Form����id���G���e�B�e�B�փZ�b�g����
		completeEntity.setTodo_id(taskForm.getTodo_id());
		if(taskForm.getComplete_flag() ==1){
			completeEntity.setComplete_flag(true);
		}else if(taskForm.getComplete_flag() ==0){
			completeEntity.setComplete_flag(false);
		}
		
		System.out.println(completeEntity.getComplete_flag());
		if(completeEntity.getComplete_flag() == true) {
			taskAcsServ.taskClear(completeEntity.getTodo_id()); 
		}
		
		if(completeEntity.getComplete_flag() == false) {
			//�A�N�Z�X�N���X���g���č폜����=�t���O��؂�ւ���
			taskAcsServ.taskComplete(completeEntity.getTodo_id()); 	}
	
		
		//�f�[�^��ǂݍ���
		
		//�S�f�[�^���擾���Ă���
		//DB����f�[�^�T���Ă���
		List<taskEntity> todolists =taskAcsServ.searchAll();

		//�S�f�[�^�̃��X�g��HTML���ֈ����n��
		model.addAttribute("todolists",todolists);

		//�^�C�g���ǉ�
		model.addAttribute("title", "toDoList");
		
		
		return URL.TODO + URL.LIST;
	}
	
	
	
	
	/*
	 * @PostMapping(URL.ALLDELETE) public String taskAllDelete (taskForm taskForm,
	 * Model model) {
	 * 
	 * 
	 * //�A�N�Z�X�N���X���g���Ăč폜����=�t���O��؂�ւ��� taskAcsServ.taskAllDelete();
	 * 
	 * //�f�[�^��ǂݍ���
	 * 
	 * //�S�f�[�^���擾���Ă��� //DB����f�[�^�T���Ă��� List<taskEntity> todolists
	 * =taskAcsServ.searchAll();
	 * 
	 * //�S�f�[�^�̃��X�g��HTML���ֈ����n�� model.addAttribute("todolists",todolists);
	 * 
	 * //�^�C�g���ǉ� model.addAttribute("title", "toDoList");
	 * 
	 * 
	 * return "redirect:/todo/list"; }
	 */
		//���Ԃ������
		/*���*/
		/*
		 * updateEntity.setTodo_time(Time.valueOf((taskForm.getTodo_time())));
		 * �ɂ���ƌ������Ⴄ���߃G���[�ɂȂ�i HTML(type="time(11:22)"�� Java hh:mm �� SQL: hh:mm:ss hh:mm �̕�����j
		 * 
		 * �ŁA:00������ ��SQL �ɓ������悤�ɉ��H �B
		 * �����āA�^��ϊ��i�^���Ⴄ��SQL�ŃG���[���N����̂Łj
		 * ����̂�Time.valeOf(String s)���g�� ��import java.sql.Time;
		 */
		
		/*
		 * ���I�ɂ�₱�����������R��
		 *  ������̏����ƌ^ 2��
		 *  ��v�����Ȃ��Ƃ����Ȃ�����B
		 *   �����F�����StringBuilder 
		 *   �^ �FTime.valeOf(Strings)
		 *   
		 *   
		 *   ����HTMIL��input type="time"�@���m��Ȃ���������(hh:mm)
		 *   SQL���̂s�h�l�d�^��hh:mm:ss�̏������m��Ȃ�����
		 *   
		 *   ���l�܂�����
		 *   HTMI SQL�̏����@���܂��m�F
		 *   ���������v������
		 *   valueOf���\�b�h�g��
		 *   ��ok
		 */
		
		
		/*�Q�l�T�C�g*/
		/*https://dodododo.jp/java/javase_6_docs_ja/api/java/sql/Time.html#valueOf(java.lang.String)*/
		/**
		 * �����Fyyyy/MM/dd
		 * �߂�l�@�Fyyyy/MM/dd 
		 * ������̓��t��java.sql.Date�ɕϊ�����
		 * java.util.Date�ł͂Ȃ��B
		 * sql�p�b�P�[�W��Date�̓f�[�^�x�[�X�̃f�[�^�^��date�ƌ݊���������܂�
		 *  �f�[�^�x�[�X�̓��t��Java�ŏ����������ꍇ�Ɏg�p���܂��B
		 */
		/**
		 * ����: s - hh:mm:ss�`���̎���
		 * �߂�l: �Ή�����Time�I�u�W�F�N�g
		 * valueOf public static Time valueOf(String s) 
		 * JDBC���ԃG�X�P�[�v�`�����̕������Time�l�ɕϊ����܂��B
		 *  
		 */

	

	





}
