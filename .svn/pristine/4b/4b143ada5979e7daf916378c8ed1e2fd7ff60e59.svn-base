package org.simongellis.hw11;

import java.util.Set;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class EmailList {
	@Id Long id;
	Set<String> emails;
	
	public EmailList() {}
	
	public EmailList(Long id, Set<String> emails) {
		this.id = id;
		this.emails = emails;
	}
	
	public void addEmails(Set<String> emails) {
		this.emails.addAll(emails);
	}
	
	public Set<String> getEmails() {
		return emails;
	}
}
