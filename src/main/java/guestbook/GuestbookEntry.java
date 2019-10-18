/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guestbook;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.Assert;

/**
 * A guestbook entry. An entity as in the Domain Driven Design context. Mapped onto the database using JPA annotations.
 *
 * @author Paul Henke
 * @author Oliver Drotbohm
 * @see https://en.wikipedia.org/wiki/Domain-driven_design#Building_blocks
 */
@Entity
class GuestbookEntry {

	private @Id @GeneratedValue Long id;
	private final String name, mail, text;
	private final LocalDateTime date;

	/**
	 * Creates a new {@link GuestbookEntry} for the given name and text.
	 *
	 * @param name must not be {@literal null} or empty
	 * @param mail must not be {@literal null} or empty
	 * @param text must not be {@literal null} or empty
	 */
	public GuestbookEntry(String name, String mail, String text) {

		Assert.hasText(name, "Name must not be null or empty!");
		Assert.hasText(mail, "Mail must not be null or empty!");
		// See https://www.freeformatter.com/java-regex-tester.html
		Assert.isTrue(mail.toLowerCase().matches("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)" +
						"*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)" +
						"*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])" +
						"|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$"),
				"Mail must have a valid format!");
		Assert.hasText(text, "Text must not be null or empty!");

		this.name = name;
		this.mail = mail;
		this.text = text;
		this.date = LocalDateTime.now();
	}

	@SuppressWarnings("unused")
	private GuestbookEntry() {
		this.name = null;
		this.mail = null;
		this.text = null;
		this.date = null;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public String getMail() {
		return mail;
	}
}