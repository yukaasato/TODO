package com.todo.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.todo.base.form.reviewForm;

//1.�R���g���[���[�̖ڈ������
@Controller
public class dataInsert {

	
	//2.�z�[����ʂ�Ԃ����\�b�h�����
	
	//URL
	@GetMapping("review/insert")
	//Form�N���X��Model�̃C���X�^���X�𐶐����āA��̂܂�HTML�ɓn��
	public String form(reviewForm insertForm, Model model) {
		
		System.out.println("aaa");
		//HTML�̃^�C�g���i�ϐ��j�Ƀf�[�^�o�^�̕���������
		model.addAttribute("title", "�f�[�^�o�^");
		
		//HTML��Ԃ�
		return "/review/insert";
	}
	
}
//3.FORM�����
