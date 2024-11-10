package gol2024;

public enum State {

	ALIVE {
		@Override
		State transform(int aliveNeighbours) {
			return aliveNeighbours == 2 || aliveNeighbours == 3 ? ALIVE : DEAD;
		}
	},
	DEAD {
		@Override
		State transform(int aliveNeighbours) {
			return aliveNeighbours == 3 ? ALIVE : DEAD;
		}
	};

	abstract State transform(int aliveNeighbours);

}
