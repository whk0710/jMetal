//  UniformMutationTest.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//
//  Copyright (c) 2014 Antonio J. Nebro
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.test.operator.mutation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal45.core.Problem;
import org.uma.jmetal45.core.Solution;
import org.uma.jmetal45.operator.mutation.UniformMutation;
import org.uma.jmetal45.problem.multiobjective.Kursawe;
import org.uma.jmetal45.util.JMetalException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Antonio J. Nebro on 10/07/14.
 */
public class UniformMutationTest {
  UniformMutation mutation ;
  Problem problem ;

  static final double DELTA = 0.0000000000001 ;

  @Before
  public void setUp() throws JMetalException {
    problem = new Kursawe("Real", 3) ;

    mutation = new UniformMutation.Builder(0.5, 1.0/problem.getNumberOfVariables())
      .build() ;
  }

  @After
  public void tearDown() throws Exception {
    mutation = null ;
    problem = null ;
  }

  @Test
  public void defaultParametersTest() {
    assertEquals(0.5, mutation.getPerturbation(), DELTA) ;
    assertEquals(1.0/problem.getNumberOfVariables(), mutation.getMutationProbability(), DELTA) ;
  }

  @Test (expected = JMetalException.class)
  public void wrongParameterClassTest() {
    mutation.execute(new Integer(4)) ;
  }

  @Test (expected = JMetalException.class)
  public void nullParameterTest() {
    mutation.execute(null) ;
  }

  @Test
  public void operatorExecutionTest() throws ClassNotFoundException {
    mutation = new UniformMutation.Builder(0.5, 1.0/problem.getNumberOfVariables())
      .build() ;

    Object result = mutation.execute(new Solution(problem));
    assertNotNull(result);
  }
}
