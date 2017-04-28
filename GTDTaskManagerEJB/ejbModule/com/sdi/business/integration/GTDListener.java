//package com.sdi.business.integration;
//
//import javax.ejb.MessageDriven;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.ejb.ActivationConfigProperty;
//
//@MessageDriven(
//		activationConfig = {
//					@ActivationConfigProperty(
//								propertyName = "destination",
//								propertyValue = "queue/GTDQueue")
//})
//public class GTDListener implements MessageListener {
//
//	@Override
//	public void onMessage(Message arg0) {
//		System.out.println("GOTCHA!!");
//
//	}
//
//}
