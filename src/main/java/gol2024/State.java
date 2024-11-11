package gol2024;

public enum State {

	ALIVE {

		enum Population {

			UNDERPOPULATION, SURVIVAL, OVERCROWDING;

			static Population of(AliveNeighbours aliveNeighbours) {
				if (aliveNeighbours.isLessThan(2)) {
					return UNDERPOPULATION;
				} else if (aliveNeighbours.isEqualTo(2) || aliveNeighbours.isEqualTo(3)) {
					return SURVIVAL;
				} else if (aliveNeighbours.isGreaterThan(3)) {
					return OVERCROWDING;
				}
				throw new IllegalStateException("Unhandled case of aliveNeighbours " + aliveNeighbours);
			}

		}

		@Override
		boolean isAlive() {
			return true;
		}

		@Override
		State transform(AliveNeighbours aliveNeighbours) {
			return switch (Population.of(aliveNeighbours)) {
			case UNDERPOPULATION -> DEAD;
			case SURVIVAL -> ALIVE;
			case OVERCROWDING -> DEAD;
			};
		}

	},
	DEAD {

		enum Population {

			REPRODUCTION, INERTIA;

			static Population of(AliveNeighbours aliveNeighbours) {
				if (aliveNeighbours.isEqualTo(3)) {
					return REPRODUCTION;
				}
				return INERTIA;
			}

		}

		@Override
		boolean isAlive() {
			return false;
		}

		@Override
		State transform(AliveNeighbours aliveNeighbours) {
			return switch (Population.of(aliveNeighbours)) {
			case REPRODUCTION -> ALIVE;
			default -> DEAD;
			};
		}
	};

	abstract boolean isAlive();

	abstract State transform(AliveNeighbours aliveNeighbours);

}
