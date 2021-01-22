package com.todo.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todo.base.entity.InquiryEntity;
import com.todo.base.form.InquiryForm;
import com.todo.base.service.InquriyAccessService;


@Controller
public class InquiryController {

	@Autowired
	InquriyAccessService inquriyAcsServ;
	
	/**
	 * /from��Access�������̏���
	 * @param inauriyForm Fimport com.tutorial.base.entity.InquiryEntity;orm�̏�����Ԃ�n���Ă���
	 * @param model title��n��
	 * @return form���
	 */
	
	@GetMapping("inquiry/form")
	public String form(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "���₢���킹");
		
		//model.addAttribute("form", inquiryForm);
		return "inquiry/form";
	}
	
	
	/**
	 * /form�ɖ߂����Ƃ��̏���
	 * @param inquiryForm Form�ɓ����Ă�����̂�ێ�
	 * @param model�@title��n��
	 * @return form���
	 */
	@PostMapping("inquiry/form")
	public String backForm(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "���₢���킹");
		//model.addAttribute("form", inquiryForm);
		return "inquiry/form";
	}
	/**
	 * /confirm�Ƀf�[�^��POST���\�b�h�ő��M���ꂽ���̏���
	 * @param inquiryForm Form�̓��e���`�F�b�N����
	 * @param result �`�F�b�N���ʂɃG���[�����邩�̔��������
	 * @param model title��n��
	 * @return
	 */
	@PostMapping("inquiry/confirm")
	public String confirm(@Validated InquiryForm inquiryForm,
							BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "���₢���킹���e�G���[");
			return "inquiry/form";
		}
		model.addAttribute("title", "���₢���킹���e�m�F");
		//model.addAttribute("inquiryForm", inquiryForm); \\�Ȃ��Ă���
			
	
		return "inquiry/confirm";
	
	}
	
	@PostMapping("inquiry/complete")
	public String complete( @Validated InquiryForm inquiryForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
			
		// inquiryEntity���C���X�^���X���A�l�����Ă���
		
				InquiryEntity inquiryEntity = new InquiryEntity();
				
				inquiryEntity.setInquiryType(inquiryForm.getInquiryType());
				inquiryEntity.setInquiryInfo(inquiryForm.getInquiryInfo());
				inquiryEntity.setInquiryUser(inquiryForm.getInquiryUser());
				inquiryEntity.setInquiryUserMail(inquiryForm.getInquiryUserMail());
				
				// DB�ւ̓o�^����
				inquriyAcsServ.inquiryInsert(inquiryEntity);
				
				//���C�Ɠ����ɂ���ꍇ��2�s���R�����g�A�E�g���Ă�
				//redirectAttributes.addFlashAttribute("complete", "���M���������܂���");
				//redirect��get�̈����炵��
				//return "redirect:/inquiry/form";
				
				model.addAttribute("title", "����������");
				return "inquiry/complete";
				
	}


	
	

}
