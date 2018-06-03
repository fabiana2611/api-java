package br.com.delivery.challenge.model.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum TypeStatus {

	PLACED (0, "Placed" ), 
	INBOUND (1, "Inbound"), 
	DELIVERED (2, "Delivered"), 
	CANCELED (3, "Canceled"), 
	REJECTED (4, "Rejected");
	
	int code;
	String description;
	
	private TypeStatus (int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static TypeStatus getByCode(Integer code) {
		
		Stream<TypeStatus> values = Arrays.stream(TypeStatus.values());
		
		Predicate<TypeStatus> predicate = x -> x.getCode() == code;
		
		Optional<TypeStatus> result = values.filter(predicate).findFirst();
		
		return result.get();
		
	}
 
}
