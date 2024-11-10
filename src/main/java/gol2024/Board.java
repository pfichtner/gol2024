package gol2024;

import static gol2024.Coordinate.coordinate;
import static gol2024.State.ALIVE;
import static gol2024.State.DEAD;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.rangeClosed;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Board {

	private final Set<Coordinate> lifeCells;

	public static class BoardBuilder {

		private final Set<Coordinate> lifeCells = new HashSet<>();

		public BoardBuilder withLifeAt(Coordinate coordinate) {
			lifeCells.add(coordinate);
			return this;
		}

		public Board build() {
			return new Board(lifeCells);
		}

	}

	private Board(Set<Coordinate> lifeCells) {
		this.lifeCells = lifeCells;
	}

	public State stateAt(Coordinate coordinate) {
		return lifeCells.contains(coordinate) ? ALIVE : DEAD;
	}

	private int aliveNeighbours(Coordinate coordinate) {
		return (int) coordinate.neighbours().map(this::stateAt).filter(State::isAlive).count();
	}

	private boolean isAliveInNextGen(Coordinate coordinate) {
		return stateAt(coordinate).transform(aliveNeighbours(coordinate)).isAlive();
	}

	public Board tick() {
		return new Board(coordinates().filter(this::isAliveInNextGen).collect(toSet()));
	}

	private Stream<Coordinate> coordinates() {
		var min = xy(minBy(Integer::compare));
		var max = xy(maxBy(Integer::compare));
		return rangeClosed(min.y() - 1, max.y() + 1)
				.mapToObj(y -> rangeClosed(min.x() - 1, max.x() + 1).mapToObj(x -> coordinate(x, y)))
				.flatMap(identity());
	}

	private Coordinate xy(Collector<Integer, ?, Optional<Integer>> collector) {
		return lifeCells.stream().collect(teeing( //
				mapping(Coordinate::x, collector), //
				mapping(Coordinate::y, collector), //
				(x, y) -> coordinate(x.orElse(0), y.orElse(0)) //
		));
	}

}
