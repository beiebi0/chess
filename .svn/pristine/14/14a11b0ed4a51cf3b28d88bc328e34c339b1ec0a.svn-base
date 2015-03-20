package org.ashishmanral.hw11;

import com.google.gwt.core.client.GWT;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactField;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;


public class PopulateContacts {
	
	final PhoneGap phoneGap = GWT.create(PhoneGap.class);
	private final SuggestionBox suggestionBox;
	
	public PopulateContacts(){
		this.suggestionBox = new SuggestionBox();
	}
	
	public void populateContacts(){
		LightArray<String> fields = CollectionFactory.<String> constructArray();
		fields.push("name"); //search in name 
		fields.push("emails"); //search in emails
		
		ContactFindOptions findOptions = new ContactFindOptions("", true);

		phoneGap.getContacts().find(fields, new ContactFindCallback() {

		        @Override
		        public void onSuccess(LightArray<Contact> contacts) {
		                for(int i=0;i<contacts.length();i++){
		                	DemoContact contact = new DemoContact(contacts.get(i).getName().getFormatted(), contacts.get(i).getEmails());
		                	LightArray<ContactField> emails = contact.getEmails();
		                	for(int j=0;j<emails.length();j++){
		                		if(emails.get(j).getValue().split("@")[1].equals("gmail.com"))
		                		suggestionBox.populateSuggestionBox(emails.get(j).getValue());
		                	}
		                }
		                suggestionBox.displayBox();
		        }

		        @Override
		        public void onFailure(ContactError error) {
		        }

		}, findOptions);
	}
	
}
