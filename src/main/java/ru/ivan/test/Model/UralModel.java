package ru.ivan.test.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;



@Entity (name="URAL")
public class UralModel implements Serializable{

	@Id 
	@GeneratedValue
	private long id;
	private LocalDateTime date;
	private String accountCorresp;
	private String incomingBalanse;
	private String incomingBalanseInNP;
	private String summOper;
	private Double outgoingBalance;
	private String summOperInNP;
	private String debitSumm;
	private String creditSumm;
	private String outgoingBalanceInNP;
	private String documentNumber;
	private String correspondentName;
	private String correspondentINN;
	@Column (length=1000)
	private String paymentMemo;
	private String operationMode;
	private String npp;
	private String accountPayers;
	private String accountBeneficiary;
	private String payersKPP;
	private String beneficiaryBank;
	private String bicBeneficiaryBank;
	private String createMemo;
	private String spentMemo;
	private String operType;
	private String correspondentAccountMemo;
	private String recipientKPP;
	private String codeRecipientOKTMO;
	
	public UralModel( LocalDateTime date, String accountCorresp, String incomingBalanse,
			String incomingBalanseInNP, String summOper, Double outgoingBalance, String summOperInNP, String debitSumm,
			String creditSumm, String outgoingBalanceInNP, String documentNumber, String correspondentName,
			String correspondentINN, String paymentMemo, String operationMode, String npp, String accountPayers,
			String accountBeneficiary, String payersKPP, String beneficiaryBank, String bicBeneficiaryBank,
			String createMemo, String spentMemo, String operType, String correspondentAccountMemo, String recipientKPP,
			String codeRecipientOKTMO) {
		super();
		
		this.date = date;
		this.accountCorresp = accountCorresp;
		this.incomingBalanse = incomingBalanse;
		this.incomingBalanseInNP = incomingBalanseInNP;
		this.summOper = summOper;
		this.outgoingBalance = outgoingBalance;
		this.summOperInNP = summOperInNP;
		this.debitSumm = debitSumm;
		this.creditSumm = creditSumm;
		this.outgoingBalanceInNP = outgoingBalanceInNP;
		this.documentNumber = documentNumber;
		this.correspondentName = correspondentName;
		this.correspondentINN = correspondentINN;
		this.paymentMemo = paymentMemo;
		this.operationMode = operationMode;
		this.npp = npp;
		this.accountPayers = accountPayers;
		this.accountBeneficiary = accountBeneficiary;
		this.payersKPP = payersKPP;
		this.beneficiaryBank = beneficiaryBank;
		this.bicBeneficiaryBank = bicBeneficiaryBank;
		this.createMemo = createMemo;
		this.spentMemo = spentMemo;
		this.operType = operType;
		this.correspondentAccountMemo = correspondentAccountMemo;
		this.recipientKPP = recipientKPP;
		this.codeRecipientOKTMO = codeRecipientOKTMO;
	}

	public UralModel() {
		super();
	}

	@Override
	public String toString() {
		return "UralModel [id=" + id + ", date=" + date + ", accountCorresp=" + accountCorresp + ", incomingBalanse="
				+ incomingBalanse + ", incomingBalanseInNP=" + incomingBalanseInNP + ", summOper=" + summOper
				+ ", outgoingBalance=" + outgoingBalance + ", summOperInNP=" + summOperInNP + ", debitSumm=" + debitSumm
				+ ", creditSumm=" + creditSumm + ", outgoingBalanceInNP=" + outgoingBalanceInNP + ", documentNumber="
				+ documentNumber + ", correspondentName=" + correspondentName + ", correspondentINN=" + correspondentINN
				+ ", paymentMemo=" + paymentMemo + ", operationMode=" + operationMode + ", npp=" + npp
				+ ", accountPayers=" + accountPayers + ", accountBeneficiary=" + accountBeneficiary + ", payersKPP="
				+ payersKPP + ", beneficiaryBank=" + beneficiaryBank + ", bicBeneficiaryBank=" + bicBeneficiaryBank
				+ ", createMemo=" + createMemo + ", spentMemo=" + spentMemo + ", operType=" + operType
				+ ", correspondentAccountMemo=" + correspondentAccountMemo + ", recipientKPP=" + recipientKPP
				+ ", codeRecipientOKTMO=" + codeRecipientOKTMO + "]";
	}
	
}


  
 

