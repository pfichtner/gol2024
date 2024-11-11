package gol2024;

import static gol2024.State.ALIVE;
import static gol2024.State.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CellTest {

	@ParameterizedTest
	@ValueSource(ints = { 0, 1 })
	void underpopulate() {
		assertThat(transform(ALIVE, 0)).isEqualTo(DEAD);
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3 })
	void survived(int aliveNeighbours) {
		assertThat(transform(ALIVE, aliveNeighbours)).isEqualTo(ALIVE);
	}

	@ParameterizedTest
	@ValueSource(ints = { 4, 5, 6, 7, 8, 9 }) // should be a PBT "forAll >= 4"
	void overpopulate(int aliveNeighbours) {
		assertThat(transform(ALIVE, aliveNeighbours)).isEqualTo(DEAD);
	}

	@Test
	void newLifeIsBorn() {
		assertThat(transform(DEAD, 3)).isEqualTo(ALIVE);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 4, 5, 6, 7, 8, 9 }) // should be a PBT "forAll != 3"
	void noNewLifeIsBorn(int aliveNeighbours) {
		assertThat(transform(DEAD, aliveNeighbours)).isEqualTo(DEAD);
	}

	private State transform(State state, int aliveNeighbours) {
		return state.transform(new AliveNeighbours(aliveNeighbours));
	}

}
