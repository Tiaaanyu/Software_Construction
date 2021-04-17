package vertex;

import static org.junit.Assert.assertTrue;

import java.io.ObjectInputStream.GetField;

public class Movie extends Vertex {
	
	
	private final String vertexType = "Movie";
	private int releaseYear = 0;
	private String shootingCountry = new String();
	private float score = 0;
	
// Abstraction function:
//  -label is the label of each vertex, so different vertex cannot have the same label;
//   label is the only symbol of the vertex.
//  -Vertex class can create a Vertex object.
//	-releaseYear is the release year of this movie.
//	-shootingCountry represents where the movie is shot.
//	-score represents the score on the IMDB of this movie.
//Representation invariant:
//  -label cannot be null.
//  -releaseYear is a four positive integer, ranging in [1900, 2018]
//  -score is a 0-10 range value, up to 2 decimal places.
//Safety from rep exposure:
//  -There's no method return the mutable.
	/**
	 * Constructor of the Movie class.
	 * @param label the label of the vertex.
	 */
	public Movie(String label) {
		super(label);
	}
	
	/**
	 * Get the type of Vertex.
	 * @return the type of the vertex.
	 */
	public String getType() {
		return vertexType;
	}
	
	/**
	 * Filling the info of the vertex.
	 */
	@Override
	public void fillVertexInfo(String[] args) {
		//System.out.println(args[0]);
		assertTrue(args.length == 3);
		releaseYear = Integer.valueOf(args[0]);
		shootingCountry = args[1];
		score = Float.parseFloat(args[2]);
	}
	
	/**
	 * Get the release year.
	 * @return releaseYear.
	 */
	public int getReleaseYear() {
		return releaseYear;
	}
	
	/**
	 * Get the shooting country.
	 * @return shootingCountry.
	 */
	public String getShootingCountry() {
		return shootingCountry;
	}
	
	/**
	 * Get the score.
	 * @return score
	 */
	public float getScore() {
		return score;
	}
	
	/**
	 * Get the label of the vertex.
	 */
	@Override
	public String getLabel() {
		return super.getLabel();
	}
	
	/**
	 * Override the method toString(), to output the vertex's info.
	 */
	@Override
	public String toString() {
		String string = new String();
		string += ("<\"" + this.getLabel() + "\", \"" + vertexType + "\", <");
		string += ("\"" + releaseYear + "\", ");
		string += ("\"" + shootingCountry + "\", ");
		string += ("\"" + score + "\">>");
		return string;
	}
	
	/**
	 * Override the method equals().
	 */
	@Override
	public boolean equals(Object that) {
		if(that == null) {
			return false;
		}
		if(this == that) {
			return true;
		}
		if(that instanceof Movie) {
			Movie other = (Movie) that;
			return other.getLabel().equals(this.getLabel()) && other.getReleaseYear() == releaseYear
					&& other.getShootingCountry().equals(shootingCountry) && other.getScore() == score;
		}
		return false;
	}
	
	/**
	 * Override the method hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;
		Float s = (Float) score;
		Integer r = (Integer) releaseYear;
		result = result * 31 + shootingCountry.hashCode();
		result = result * 31 + s.hashCode();
		result = result * 31 + r.hashCode();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
