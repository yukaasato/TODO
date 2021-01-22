package com.todo.base.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.base.entity.taskEntity;


@Repository
public class taskContentAccessDao {
	//�ϐ�em�@�̒�`
	//�G���e�B�e�B�}�l�[�W���[�̕ϐ�����t����
	@Autowired
	EntityManager em;//Entity Control
	
	//SQL�ł���INSERT ��
	public void taskCntInsert(taskEntity taskEntity) {
		//�������Ƀ��[�U�����͂�����񂪓����Ă���
		em.persist(taskEntity);
	}
	
	/*
	 * Native Query SQL�𕶎���ŋL�q���A���̂܂�DB�ɓn�����@
	 * �����F�V�������Ƃ��o����K�v���Ȃ����ƁAOracle�֐��̂悤��DB�ˑ��̃N�G�����������邱�ƁB
	 * �Z���FJPA�̈Ӌ`�ł���f�[�^�i�������u���������C�ɂ��Ȃ��ėǂ��A�Ƃ������_���K���������Ă邱�ƁBDB��Oracle����MySQL�ɕς��悤�i
	 * �\�Z�I�ȗ��R�Łj�A�Ƃ��ɂȂ������ɁA���ɂ܂��i�H���I�ȗ��R�Łj�B
	 */
	public  List<taskEntity>  searchAll() {
		
		List<taskEntity> taskEntity = (List<taskEntity>)em.createNativeQuery("select * from todo").getResultList();
		
		return  taskEntity;
	
	}
	
	
	//�ꗗ�\��
	public List<taskEntity> getAllTask(){
		
		
		StringBuilder findAll = new StringBuilder();
		
		findAll.append("SELECT TODO_ID,"
				+ "TODO_TITLE,"
				+ "CAST(TODO_DATE AS DATE) as TODO_DATE,"
				+ "TODO_PLACE,"
				+ "COMPLETE_FLAG,"
				+ "DELETE_FLAG,"
				+ "TODO_TIME"
				+ " FROM TODO");
		
		List<taskEntity> resultList = (List<taskEntity>)em.createNativeQuery(findAll.toString(), taskEntity.class).getResultList();
		return resultList;
	}
	
	//�ꌏ�擾
	public taskEntity selectOneTask(int id) {
		StringBuilder findTask = new StringBuilder();
		
		findTask.append("SELECT * FROM TODO WHERE TODO_ID =?1");
		taskEntity task =(taskEntity)em.createNativeQuery(findTask.toString(),taskEntity.class).
				setParameter(1,id).getSingleResult();
		return task;
	}
	
	//�ꌏ�ڍ׍X�V
	public void updateTask(taskEntity taskEntity) {

		StringBuilder updateTask = new StringBuilder();
		
		updateTask.append("UPDATE TODO SET "
				+ "todo_title = ?1,"
				+ "todo_date  = ?2,"
				+ "todo_place = ?3,"
				+ "todo_time = ?4 "
				+ "WHERE "
				+ "todo_id = ?5");
		
		em.createNativeQuery(updateTask.toString(),taskEntity.class) //�����ŗ�����
		.setParameter(1, taskEntity.getTodo_title())
		.setParameter(2, taskEntity.getTodo_date())
		.setParameter(3, taskEntity.getTodo_place())
		.setParameter(4, taskEntity.getTodo_time())
		.setParameter(5, taskEntity.getTodo_id())
		.executeUpdate();

	}

}