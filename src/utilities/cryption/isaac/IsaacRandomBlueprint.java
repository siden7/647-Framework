package utilities.cryption.isaac;

/**
 * Represents a ISAAC key pair, for both input and output.
 * 
 * @author `Discardedx2
 */
public final class IsaacRandomBlueprint {

	/**
	 * The input cipher.
	 */
	private IsaacRandom input;

	/**
	 * The output cipher.
	 */
	private IsaacRandom output;

	/**
	 * Constructs a new {@code ISAACPair} {@code Object}.
	 * 
	 * @param input The input cipher.
	 * @param output The output cipher.
	 */
	public IsaacRandomBlueprint(IsaacRandom input, IsaacRandom output) {
		this.input = input;
		this.output = output;
	}

	/**
	 * Gets the input cipher.
	 * 
	 * @return The input cipher.
	 */
	public IsaacRandom getInput() {
		return input;
	}

	/**
	 * Gets the output cipher.
	 * 
	 * @return The output cipher.
	 */
	public IsaacRandom getOutput() {
		return output;
	}

}