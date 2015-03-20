package org.mengyanhuang.hw11;

import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;


public class Contacts {

	private final PhoneGap phoneGap;
	phoneGap = GWT.create(PhoneGap.class);
	
	LightArray<String> fields = CollectionFactory.<String> constructArray();
	fields.push("displayed"); //search in displayname 
	fields.push("name"); //search in name as well
	ContactFindOptions findOptions = new ContactFindOptions("<string to search>", true);

	phoneGap.getContacts().find(fields, new ContactFindCallback() {

	        @Override
	        public void onSuccess(LightArray<Contact> contacts) {
	                //read contacts here..
	        }

	        @Override
	        public void onFailure(ContactError error) {
	                //something went wrong, doh!

	        }
	}, findOptions);
	
}
