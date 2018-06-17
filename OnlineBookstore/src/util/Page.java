package  util;

import java.util.Collection;

//Paging tools class
public class Page {

	private int currentPageNumber = 1;  

 
	private int totalPage;  

	private long totalNumber;  

	private int itemInPage; // the first item in page  

	private int itemsPerPage;  

	private boolean next; // next page
	private boolean previous; // previous page

	private Collection<?> list;  

	private Object conditonObject;  

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public void setItemInPage(int itemInPage) {
		this.itemInPage = itemInPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public void setConditonObject(Object conditonObject) {
		this.conditonObject = conditonObject;
	}

	public Object getConditonObject() {
		return conditonObject;
	}
	public Page(){
		
	}
	public Page(int totalNumber, int itemPerPage) {
		this.totalNumber = totalNumber;
		this.itemsPerPage = itemPerPage;
		this.totalPage = (totalNumber + itemPerPage - 1) / itemPerPage;
		// if (totalNumber == 0)
		// this.currentPageNumber = 0;
		flush();
	}
//Refresh the page attributes: refresh prev and next status
	private void flush() {
		this.setNext();
		this.setPrevious();
		this.itemInPage = (currentPageNumber - 1) * itemsPerPage;
	}

	private void setNext() {

		if (this.currentPageNumber >= this.totalPage) {
			this.next = false;
		} else {
			this.next = true;
		}
	}

	private void setPrevious() {
		if (currentPageNumber == 1 || currentPageNumber == 0) {
			this.previous = false;
		} else {
			this.previous = true;
		}
	}

	 
	public long getTotalNumber() {
		return totalNumber;
	}


	public int getItemInPage() {
		return itemInPage;
	}



	public int getItemsPerPage() {
		return itemsPerPage;
	}




	public boolean isNext() {
		return next;
	}


	public boolean isPrevious() {
		return previous;
	}

	
	@SuppressWarnings("rawtypes")
	public Collection getList() {
		return list;
	}

	
	@SuppressWarnings("rawtypes")
	public void setList(Collection list) {
		this.list = list;
	}

	public int getTotalPage() {
		this.totalPage = (int) ((totalNumber + this.itemsPerPage - 1) / this.itemsPerPage);
		return totalPage;
	}


	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	 
	public void setCurrentPageNumber(int currentPageNumber) {
 
		if (currentPageNumber <= 0) {
			this.currentPageNumber = 1;
			flush();
			return;
		}
 		if (currentPageNumber >= totalPage) {
			if (totalPage > 0) {
				this.currentPageNumber = this.totalPage;
				flush();
				return;
			} else {
				this.currentPageNumber = 1;
			}
		}

		this.currentPageNumber = currentPageNumber;
		flush();
	}

}
