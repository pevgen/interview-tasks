package interview.tasks.deal;

import lombok.Value;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@Value
public class Deal {
	private static final Random RANDOM = new Random(1_000_000);

	String name;
	long value;
	String buyer;
	String seller;

	static Deal generate() {
		return new Deal(RandomStringUtils.randomAlphanumeric(10),
				RANDOM.nextLong(),
				RandomStringUtils.randomAlphabetic(5),
				RandomStringUtils.randomAlphabetic(5));
	}
}
