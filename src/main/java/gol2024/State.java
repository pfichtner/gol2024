package gol2024;

public enum State {

	ALIVE {
		@Override
		boolean isAlive() {
			return true;
		}

		@Override
		State transform(int aliveNeighbours) {
			return aliveNeighbours == 2 || aliveNeighbours == 3 ? ALIVE : DEAD;
		}

	},
	DEAD {
		@Override
		boolean isAlive() {
			return false;
		}

		@Override
		State transform(int aliveNeighbours) {
			return aliveNeighbours == 3 ? ALIVE : DEAD;
		}
	};

	abstract boolean isAlive();

	abstract State transform(int aliveNeighbours);

}
