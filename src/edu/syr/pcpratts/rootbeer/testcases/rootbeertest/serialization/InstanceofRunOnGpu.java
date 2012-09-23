/* 
 * Copyright 2012 Phil Pratt-Szeliga and other contributors
 * http://chirrup.org/
 * 
 * See the file LICENSE for copying permission.
 */

package edu.syr.pcpratts.rootbeer.testcases.rootbeertest.serialization;

import edu.syr.pcpratts.rootbeer.runtime.Kernel;

public class InstanceofRunOnGpu implements Kernel {
  
  int m_ret;
  
  public void gpuMethod(){
    if(this instanceof Object){
      m_ret = 1;
    } else {
      m_ret = 2;
    }
  }

  boolean compare(InstanceofRunOnGpu rhs) {
    if(m_ret != rhs.m_ret){
      System.out.println("m_ret error.");
      System.out.println("lhs: "+m_ret);
      System.out.println("rhs: "+rhs.m_ret);
      return false;
    }
    return true;
  }
}

