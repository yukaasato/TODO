package com.todo.base.service;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.base.dao.taskContentAccessDao;
import com.todo.base.entity.taskEntity;

@Service
@Transactional

public class taskAccessService {

	@Autowired
	taskContentAccessDao taskAcsDao;

	//Task�쐬
	public void taskInsert(taskEntity taskEntity) {

		taskAcsDao.taskCntInsert(taskEntity);
	}


	//Task�ꗗ�\��
	public List<taskEntity>  searchAll() {

		//List<taskEntity> taskEntity=taskAcsDao.searchAll();
		return taskAcsDao.getAllTask();
		

	}		

	//Task�ꌏ�\��
	public taskEntity findTask(int id) {
		taskEntity task = taskAcsDao.selectOneTask(id);
		return task;
	}
	
	//Task�X�V
	public void taskUpdatek(taskEntity updateEntity) {
		taskAcsDao.updateTask(updateEntity);
		
	}

}

