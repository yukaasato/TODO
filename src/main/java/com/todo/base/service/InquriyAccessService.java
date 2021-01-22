package com.todo.base.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.base.dao.InquiryContentAccessDao;
import com.todo.base.entity.InquiryEntity;

//�r�W�l�X���W�b�N�@�������iApp���������邩
@Service
//DB�ւ̏����𐧌䂷����́A��A�̂Ȃ���@�o�^�ɕK�v�Ȃ̂ŁA�T�[�r�X�N���X�ɂ͂��Ă����Ă�
@Transactional
public class InquriyAccessService {

	//�I�[�g���C���[�h�����Ă�����ƁA1�̃C���X�^���X���쐬���Ă����
	//�ǂ��ŏ��������Ă����ƁA�ŏ�
	@Autowired
	InquiryContentAccessDao inquiryAcsDao;

	
	//���\�b�h�̒���Dao���Ăяo���Ă�����A�Ăяo���^���߂�ǂ�����
	//�������̓G���e�B�e�B
	public void inquiryInsert(InquiryEntity inquiryEntity) {
		
		inquiryAcsDao.inquiryCntInsert(inquiryEntity);
	}
}

/*
 * Dao����
 * Insert�̃��\�b�h����A������Entitiy
 * ���̏�����Dao�̒���insert���Ăяo��
 *
 * 
 * 
 * */
