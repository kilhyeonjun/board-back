package com.board.back.util;

import com.board.back.vo.BoardVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PagingUtil {
	Integer currentPageNum;         // ���� ������ ��ȣ

	Integer objectCountTotal;       // ��ü �� ��
	Integer objectCountPerPage;     // �� ȭ�鿡 ����� ������Ʈ ��
	Integer objectStartNum;         // �� ȭ�鿡 ǥ�õǴ� ������Ʈ�� ����
	Integer objectEndNum;           // �� ȭ�鿡 ǥ�õǴ� ������Ʈ�� ������

	Integer pageNumCountTotal;      // ��ü ������ ��
	Integer pageNumCountPerPage;    // �� ȭ�鿡 ����� ������ ��ȣ ��
	Integer pageNumStart;           // �� ȭ�鿡 ��µǴ� ������ ��ȣ�� ����
	Integer pageNumEnd;             // �� ȭ�鿡 ��µǴ� ������ ��ȣ�� ������

	Boolean isPrev;                 // ���� ������ ǥ�� ����
	Boolean isNext;                 // ���� ������ ǥ�ÿ���
	
	/**
	 * ������ 1; 
	 * 1) ���� ������ ��ȣ : 1, �� ȭ�鿡 ����� ������Ʈ ��: 10, �� ȭ�鿡 ����� ������ ��ȣ �� : 10���� �⺻�����Ѵ�.
	 * 2) setObjectStartAndEnd()�� ȣ���Ͽ� �� ȭ�鿡 ǥ�õǴ� ������Ʈ�� ���۰� �������� �����Ѵ�.
	 * 
	 */
	public PagingUtil() {
		this.currentPageNum = 1;
		this.objectCountPerPage = 10;
		this.pageNumCountPerPage = 10;

		setObjectStartAndEnd();
	}
	
	
	/**
	 * ������ 2;
	 * 1) '���� ������ ��ȣ'�� ������ �޴´�. �� ȭ�鿡 ����� ������Ʈ ��: 10, �� ȭ�鿡 ����� ������ ��ȣ �� : 10���� �⺻�����Ѵ�.
	 * 2) setObjectStartAndEnd()�� ȣ���Ͽ� �� ȭ�鿡 ǥ�õǴ� ������Ʈ�� ���۰� �������� �����Ѵ�.
	 * 
	 * @param currentPageNum
	 */
	public PagingUtil(int currentPageNum) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		this.objectCountPerPage = 10;
		this.pageNumCountPerPage = 10;

		setObjectStartAndEnd();
	}
	
	/**
	 * ������ 3;
	 * 1) '���� ������ ��ȣ','�� ȭ�鿡 ����� ������Ʈ ��','�� ȭ�鿡 ����� ������ ��ȣ ��'�� ������ �޴´�.
	 * 2) setObjectStartAndEnd()�� ȣ���Ͽ� �� ȭ�鿡 ǥ�õǴ� ������Ʈ�� ���۰� �������� �����Ѵ�.
	 * 
	 * @param currentPageNum
	 * @param objectCountPerPage
	 * @param pageNumCountPerPage
	 */
	public PagingUtil(int currentPageNum, int objectCountPerPage, int pageNumCountPerPage) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		this.objectCountPerPage = (0 < objectCountPerPage) ? objectCountPerPage : 10 ;
		this.pageNumCountPerPage = (0 < pageNumCountPerPage) ? pageNumCountPerPage : 10 ;

		setObjectStartAndEnd();
	}

	
	/**
	 * �� ȭ�鿡 ǥ�õǴ� ������Ʈ�� ���۰� �������� �����Ѵ�.
	 * 
	 */
	public void setObjectStartAndEnd() {
		this.objectEndNum = currentPageNum * objectCountPerPage;
		this.objectStartNum = (objectEndNum - 1) - (objectCountPerPage - 1);

	}

	public boolean setCalcForPaging(Integer objectCountTotal) {
		if (objectCountTotal == null) {
			return false;
		}
		
		try {
			
			this.objectCountTotal = objectCountTotal;
			this.pageNumCountTotal = (int) Math.ceil((double)objectCountTotal / objectCountPerPage);
			
			int tmpPageNumStart = ((int) Math.ceil(currentPageNum / pageNumCountPerPage) 
					* pageNumCountPerPage);
			int tmpPageNumEnd = 0;
					
			if (tmpPageNumStart == 0) {
				this.pageNumStart = 1;
				tmpPageNumEnd = tmpPageNumStart + pageNumCountPerPage;		
			} else if (tmpPageNumStart == currentPageNum) {
				this.pageNumStart = tmpPageNumStart - (pageNumCountPerPage - 1);
				tmpPageNumEnd = currentPageNum;
			} else {
				this.pageNumStart = tmpPageNumStart + 1;
				tmpPageNumEnd = pageNumStart + pageNumCountPerPage;
			}
			
			
			this.pageNumEnd = (pageNumCountTotal < tmpPageNumEnd) ? pageNumCountTotal : tmpPageNumEnd;
			
			this.isPrev = (currentPageNum > pageNumCountPerPage) ? true : false;
			this.isNext = (pageNumEnd < pageNumCountTotal || (pageNumStart < pageNumEnd && currentPageNum < pageNumCountTotal)  ) ? true : false;
			
			this.objectEndNum = (objectCountTotal < objectEndNum) ? objectCountTotal : objectEndNum;
		    return true;
		    
		} catch (Exception e) {e.printStackTrace(); return false;}
		
	}
	
	
	public boolean setCalcForPaging() {
		return setCalcForPaging(this.objectCountTotal);
	}
}
