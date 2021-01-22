package com.todo.base.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.base.entity.InquiryEntity;

@Repository
public class InquiryContentAccessDao {

	//�ϐ�em�@�̒�`
	//�G���e�B�e�B�}�l�[�W���[�̕ϐ�����t����
	@Autowired
	EntityManager EntityCtl;//Entity Control
	
	//SQL�ł���INSERT ��
	public void inquiryCntInsert(InquiryEntity inquiryEntity) {
		//�������Ƀ��[�U�����͂�����񂪓����Ă���
		EntityCtl.persist(inquiryEntity);
	}
}