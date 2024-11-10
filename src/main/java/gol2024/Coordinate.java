package gol2024;

import static java.util.function.Function.identity;
import static java.util.function.Predicate.isEqual;
import static java.util.function.Predicate.not;
import static java.util.stream.IntStream.rangeClosed;

import java.util.stream.Stream;

public record Coordinate(int x, int y) {

	public static Coordinate coordinate(int x, int y) {
		return new Coordinate(x, y);
	}

	public Stream<Coordinate> neighbours() {
		return rangeClosed(y - 1, y + 1).mapToObj(y -> rangeClosed(x - 1, x + 1).mapToObj(x -> coordinate(x, y)))
				.flatMap(identity()).filter(not(isEqual(this)));
	}
}
