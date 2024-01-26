package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.PromotionPage;

public class PromotionSteps extends DriverBase {
	PromotionPage promotionPage;
	ActionManager action = new ActionManager();

	public PromotionSteps(PromotionPage promotionPage) {
		this.promotionPage=promotionPage;
	}



}
