package com.techelevator.models;

public enum AdminMessages {

	EVENT_CREATED("Event created successfully."),
	USER_CREATED("User created successfully."),
	SIGNUP_EMAIL_SENT("Signup email sent successfully."),
	EVENT_SIGNUP("You have been signed up for the selected event."),
	PREFERENCES_SUBMITTED("Your preferences have been submitted."),
	INVALID_SELECTIONS("Your choices are invalid."),
	INVALID_TIME("One of the time ranges entered below is invalid."),
	INVALID_ALREADY_REGISTERED("You have already signed up for this event."),
	STUDENT_EVENT_INVITES("Event invites sent."),
	NEW_EMPLOYER_ADDED("A new employer has signed up for this event since you began your selection. Please review your choices again."),
	ERROR("An error occurred.");
	
	
	private String value;

    AdminMessages(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}
