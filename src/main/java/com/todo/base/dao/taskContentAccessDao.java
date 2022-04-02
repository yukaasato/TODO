package com.todo.base.dao;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.todo.base.entity.taskEntity;

/**
 * �N���X�̐���
 * Dao�N���X
 * Entitiy�ɃZ�b�g����Ă���l�����Ƃ�
 * SQL�����s���f�[�^��
 * �擾�A�X�V�A�폜���s��
 * 
 * */

@Repository
public class taskContentAccessDao {

	
	@Autowired
	EntityManager em;
	
	/**
	 * ���\�b�h�FSQL�֗��ǉ����� 
	 * �f�[�^�Ftodo_titiel��o�^����
	 * �����FtaskEntitiy(���[�U�����͂����^�X�N�̃^�C�g��)
	 */
	
	public void addTask(taskEntity title) {
		
		//DB�փG���e�B�e�B�̃f�[�^��o�^����
		em.persist(title);
		
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
		
		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder selectAll = new StringBuilder();
		
		//�S�f�[�^�擾�@���[�U�r���e�B���l���Ăɓ����̕��בւ��������ɍs��
		selectAll.append("SELECT TODO_ID,"
				+ "TODO_TITLE,"
				+ "CAST(TODO_DATE AS DATE) as TODO_DATE," //datetime�^����Date�^�֕ϊ�
				+ "TODO_PLACE,"
				+ "COMPLETE_FLAG,"
				+ "DELETE_FLAG,"
				+ "TODO_TIME"
				+ " FROM TODO"
				+ " ORDER BY CASE WHEN TODO_DATE IS NULL THEN '9999-99-99 00:00:00' END, TODO_DATE ASC,TODO_TIME ASC"); //���t>���ԁ@NULL�͌��ɗ���悤��
		
		//SQL�@��
		//SELECT * FROM todo Order by case when Todo_date is NULL then '9999-99-99 00:00:00' end, TODO_DATE ASC,TODO_TIME ASC;
		
		//Entity��getResultList()�ŕԂ��Ă����f�[�^������āA���X�g�^�֕ϊ�����resultList�֊i�[
		//em�̃��\�b�hgetResultList();�^���Ȃ����ĕԂ��@���O�̂Ȃ���
		
		List<taskEntity> resultList =
				(List<taskEntity>)em.createNativeQuery(selectAll.toString(), taskEntity.class).getResultList();
		
		//�S�f�[�^���T�[�r�X�N���X�֕Ԃ�<�z�[����ʂ̃f�[�^>
		return resultList;
	}
	
	//�ꌏ�擾
	public taskEntity selectOneTask(int id) {
		
		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder selectOne = new StringBuilder();
		
		//1���̃f�[�^�擾�@?1������java�̕ϐ��������񉻂��Ȃ����߂ɃZ�b�g�p�����[�^�[���g��
		selectOne.append("SELECT * FROM TODO WHERE TODO_ID =?1");
		
		//Entity��getSingleResult()�ŕԂ��Ă����f�[�^������āA�G���e�B�e�B�փL���X�g���āAoneTask�֊i�[
		taskEntity oneTask =(taskEntity)em.createNativeQuery(selectOne.toString(),taskEntity.class).setParameter(1,id).getSingleResult();
		
		//1���̃f�[�^���T�[�r�X�N���X�֕Ԃ�<�ڍ׉�ʂ̃f�[�^>
		return oneTask;
	}
	
	//�ꌏ�ڍ׍X�V
	public void updateTask(taskEntity taskEntity) {

		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder updateTask = new StringBuilder();
		
		//1���̃f�[�^�X�V�@?1~5�̕�����java�̕ϐ��������񉻂��Ȃ����߂ɃZ�b�g�p�����[�^�[���g��
		updateTask.append("UPDATE TODO SET "
				+ "todo_title = ?1,"
				+ "todo_date  = ?2,"
				+ "todo_place = ?3,"
				+ "todo_time = ?4 "
				+ "WHERE "
				+ "todo_id = ?5");
		
		//SQL��?1~5�̓���l��taskEntitiy����擾���A�r�p�k�����X�V����
		em.createNativeQuery(updateTask.toString(),taskEntity.class) 
		.setParameter(1, taskEntity.getTodo_title())
		.setParameter(2, taskEntity.getTodo_date())
		.setParameter(3, taskEntity.getTodo_place())
		.setParameter(4, taskEntity.getTodo_time())
		.setParameter(5, taskEntity.getTodo_id())
		
		//update���I��������
		.executeUpdate();
	}
	
	public void deleteTask(int id) {
		
		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder changeDltFlag = new StringBuilder();
				
		//1���̃f�[�^�擾�@?1������java�̕ϐ��������񉻂��Ȃ����߂ɃZ�b�g�p�����[�^�[���g��
		changeDltFlag.append("UPDATE TODO SET DELETE_FLAG = 1  WHERE TODO_ID =?1");

		//Entity��getSingleResult()�ŕԂ��Ă����f�[�^������āA�G���e�B�e�B�փL���X�g���āAoneTask�֊i�[
		em.createNativeQuery(changeDltFlag.toString(),taskEntity.class).setParameter(1,id)
		
		.executeUpdate();
				
	}
	
	public void completeTask(int id) {
		
		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder changeCmpFlag = new StringBuilder();
				
		//1���̃f�[�^�擾�@?1������java�̕ϐ��������񉻂��Ȃ����߂ɃZ�b�g�p�����[�^�[���g��
		changeCmpFlag.append("UPDATE TODO SET COMPLETE_FLAG = 1  WHERE TODO_ID =?1");

		//Entity��getSingleResult()�ŕԂ��Ă����f�[�^������āA�G���e�B�e�B�փL���X�g���āAoneTask�֊i�[
		em.createNativeQuery(changeCmpFlag.toString(),taskEntity.class).setParameter(1,id)
		
		.executeUpdate();
				
	}
	public void clearTask(int id) {
		
		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder changeCmpFlag = new StringBuilder();
				
		//1���̃f�[�^�擾�@?1������java�̕ϐ��������񉻂��Ȃ����߂ɃZ�b�g�p�����[�^�[���g��
		changeCmpFlag.append("UPDATE TODO SET COMPLETE_FLAG = 0  WHERE TODO_ID =?1");

		//Entity��getSingleResult()�ŕԂ��Ă����f�[�^������āA�G���e�B�e�B�փL���X�g���āAoneTask�֊i�[
		em.createNativeQuery(changeCmpFlag.toString(),taskEntity.class).setParameter(1,id)
		
		.executeUpdate();
				
	}
	

	public void taskAllDelete() {
	
		//������̒ǉ����s���C���X�^���X�𐶐��ASQL������
		StringBuilder changeCmpFlag = new StringBuilder();
				
		//1���̃f�[�^�擾�@?1������java�̕ϐ��������񉻂��Ȃ����߂ɃZ�b�g�p�����[�^�[���g��
		changeCmpFlag.append("UPDATE TODO SET COMPLETE_FLAG = 1 WHERE DELETE_FLAG = =0");

		//Entity��getSingleResult()�ŕԂ��Ă����f�[�^������āA�G���e�B�e�B�փL���X�g���āAoneTask�֊i�[
		em.createNativeQuery(changeCmpFlag.toString(),taskEntity.class)
			
		.executeUpdate();
		
				
	}
	

}