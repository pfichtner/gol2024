package gol2024;

public record AliveNeighbours(int count) {

	public boolean isEqualTo(int compareTo) {
		return count == compareTo;
	}

	public boolean isLessThan(int compareTo) {
		return count < compareTo;
	}

	public boolean isGreaterThan(int compareTo) {
		return count > compareTo;
	}

}
