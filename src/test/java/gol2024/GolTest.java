package gol2024;

import static gol2024.Coordinate.coordinate;
import static gol2024.State.ALIVE;
import static gol2024.State.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import gol2024.Board.BoardBuilder;

class GolTest {

	BoardBuilder builder = new BoardBuilder();

	@Test
	void boadWithoutLife() {
		assertThat(board().stateAt(coordinate(0, 0))).isEqualTo(DEAD);
	}

	@Test
	void boadWithLife() {
		builder = builder.withLifeAt(coordinate(0, 0));
		assertThat(board().stateAt(coordinate(0, 0))).isEqualTo(ALIVE);
	}

	@Test
	void cellDiesDueToUnderpopulation() {
		builder = builder.withLifeAt(coordinate(0, 0));
		assertThat(board().tick().stateAt(coordinate(0, 0))).isEqualTo(DEAD);
	}

	@Test
	void cellSurvives() {
		builder = builder //
				.withLifeAt(coordinate(0, 0)) //
				.withLifeAt(coordinate(1, 0)) //
				.withLifeAt(coordinate(0, 1));
		assertThat(board().tick().stateAt(coordinate(1, 1))).isEqualTo(ALIVE);
	}

	@Test
	void newLifeIsBorn() {
		builder = builder //
				.withLifeAt(coordinate(0, 0)) //
				.withLifeAt(coordinate(1, 0)) //
				.withLifeAt(coordinate(2, 0));
		assertThat(board().tick().stateAt(coordinate(1, 1))).isEqualTo(ALIVE);
	}

	Board board() {
		return builder.build();
	}

}
