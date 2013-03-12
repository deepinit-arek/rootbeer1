/* 
 * Copyright 2012 Phil Pratt-Szeliga and other contributors
 * http://chirrup.org/
 * 
 * See the file LICENSE for copying permission.
 */

package edu.syr.pcpratts.rootbeer.testcases.rootbeertest.serialization;

import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.test.TestSerialization;
import java.util.ArrayList;
import java.util.List;

public class LinkedHashSetTest implements TestSerialization {

  public List<Kernel> create() {
    List<Kernel> ret = new ArrayList<Kernel>();
    for(int i = 0; i < 5; ++i){
      ret.add(new LinkedHashSetRunOnGpu());
    }
    return ret;
  }

  public boolean compare(Kernel original, Kernel from_heap) {
    LinkedHashSetRunOnGpu lhs = (LinkedHashSetRunOnGpu) original;
    LinkedHashSetRunOnGpu rhs = (LinkedHashSetRunOnGpu) from_heap;
    return lhs.compare(rhs);
  }
  
}