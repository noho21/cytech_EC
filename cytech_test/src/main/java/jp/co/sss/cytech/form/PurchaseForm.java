package jp.co.sss.cytech.form;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jp.co.sss.cytech.enums.PayMethod;
import jp.co.sss.cytech.enums.ReceivePlace;

public class PurchaseForm {
	
	@NotNull(message = "住所を選択してください")
	private ReceivePlace receivePlace; // REGISTERED / NEW

	private String registeredAddress;
	private String registeredBuilding;
	 
	@AssertTrue(message = "住所を正しく入力してください")
	public boolean isValidAddress() {
		if (receivePlace == null) {
	        return true;
	    }
		if (receivePlace == ReceivePlace.REGISTERED) {
			return registeredAddress != null && !registeredAddress.isBlank();
		} else if (receivePlace == ReceivePlace.NEW) {
			return newAddress != null && !newAddress.isBlank();
	    }
	    return false;
	}

	private String newAddress;
	private String newBuilding;

	@NotNull(message = "お支払い方法を選択してください")
	private PayMethod payMethod;
	
	private String cardNumber1;
    private String cardNumber2;
    
    private String cardExpiry1;
    private String cardExpiry2;
    
    @AssertTrue(message = "カード番号を入力してください")
    public boolean isValidCard() {
    	if (payMethod == null) {
            return true;
        }
        if (payMethod == PayMethod.CARD1) {
            return cardNumber1 != null && !cardNumber1.isBlank();
        }
        if (payMethod == PayMethod.CARD2) {
            return cardNumber2 != null && !cardNumber2.isBlank();
        }
        return true;
    }
    
    @AssertTrue(message = "カード番号は15桁の数字で入力してください")
    public boolean isCardFormatValid() {
        if (payMethod == null) return true;

        String number =
            payMethod == PayMethod.CARD1 ? cardNumber1 : cardNumber2;

        if (number == null || number.isBlank()) return true;

        return number.matches("\\d{15}");
    }
    
    @AssertTrue(message = "有効期限を入力してください")
    public boolean isValidExpiry() {
    	if (payMethod == null) {
            return true;
        }
        if (payMethod == PayMethod.CARD1) {
            return cardExpiry1 != null && !cardExpiry1.isBlank();
        }
        if (payMethod == PayMethod.CARD2) {
            return cardExpiry2 != null && !cardExpiry2.isBlank();
        }
        return true;
    }
    
    @AssertTrue(message = "有効期限は現在以降の MM/YY 形式で入力してください")
    public boolean isExpiryFormatValid() {
        if (payMethod == null) return true;

        String expiry =
            payMethod == PayMethod.CARD1 ? cardExpiry1 : cardExpiry2;

        if (expiry == null || expiry.isBlank()) return true;
        
        if (!expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return false;
        }

        String[] parts = expiry.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]); // YY

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear() % 100; // YY
        int currentMonth = now.getMonthValue();

        if (year < currentYear) return false;
        if (year == currentYear && month < currentMonth) return false;

        return true;
    }

	 
	public ReceivePlace getReceivePlace() {
		return receivePlace;
	}

	public void setReceivePlace(ReceivePlace receivePlace) {
		this.receivePlace = receivePlace;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getRegisteredBuilding() {
		return registeredBuilding;
	}

	public void setRegisteredBuilding(String registeredBuilding) {
		this.registeredBuilding = registeredBuilding;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public String getNewBuilding() {
		return newBuilding;
	}

	public void setNewBuilding(String newBuilding) {
		this.newBuilding = newBuilding;
	}

	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}

	public String getCardNumber1() {
		return cardNumber1;
	}

	public void setCardNumber1(String cardNumber1) {
		this.cardNumber1 = cardNumber1;
	}

	public String getCardNumber2() {
		return cardNumber2;
	}

	public void setCardNumber2(String cardNumber2) {
		this.cardNumber2 = cardNumber2;
	}

	public String getCardExpiry1() {
		return cardExpiry1;
	}

	public void setCardExpiry1(String cardExpiry1) {
		this.cardExpiry1 = cardExpiry1;
	}

	public String getCardExpiry2() {
		return cardExpiry2;
	}

	public void setCardExpiry2(String cardExpiry2) {
		this.cardExpiry2 = cardExpiry2;
	}
	 
}
