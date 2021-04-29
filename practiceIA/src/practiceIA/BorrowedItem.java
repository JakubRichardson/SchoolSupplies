package practiceIA;

public class BorrowedItem {
	Item item;
	BorrowObject borrowedItem;
	
	BorrowedItem(Item item, BorrowObject borrowedItem) {
		this.item = item;
		this.borrowedItem = borrowedItem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BorrowObject getBorrowedItem() {
		return borrowedItem;
	}

	public void setBorrowedItem(BorrowObject borrowedItem) {
		this.borrowedItem = borrowedItem;
	}
}
