/* Nick Nichols - Reportable.java
 *
 * Allows custom FitnessFunctions to handle reporting on
 * their final results
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public interface Reportable{

	public double evaluate( IChromosome target );

	public void report( IChromosome target );

}
