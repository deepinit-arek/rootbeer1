/* 
 * Copyright 2012 Phil Pratt-Szeliga and other contributors
 * http://chirrup.org/
 * 
 * See the file LICENSE for copying permission.
 */

package edu.syr.pcpratts.rootbeer.runtime;

import edu.syr.pcpratts.rootbeer.configuration.Configuration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Rootbeer implements IRootbeer {

  private IRootbeerInternal m_Rootbeer;
  private List<StatsRow> m_stats;
  private boolean m_ranGpu;
  private ThreadConfig m_threadConfig;
  
  public Rootbeer(){
    RootbeerFactory factory = new RootbeerFactory();
    m_Rootbeer = factory.create(this);
  }
  
  public void setThreadConfig(int block_shape_x, int grid_shape_x){
    m_threadConfig = new ThreadConfig(block_shape_x, grid_shape_x);
  }
  
  public void runAll(List<Kernel> jobs) {
    if(jobs.isEmpty()){
      m_ranGpu = false;
      return;
    }
    if(jobs.get(0) instanceof CompiledKernel == false){
      for(Kernel job : jobs){
        job.gpuMethod();
      }
      m_ranGpu = false;
    } else {
      //this must happen above Rootbeer.runAll in case exceptions are thrown
      m_ranGpu = true;
      
      m_stats = new ArrayList<StatsRow>();
      if(m_threadConfig != null){
        m_Rootbeer.setThreadConfig(m_threadConfig);
        m_threadConfig = null;
      } else {
        m_Rootbeer.clearThreadConfig();
      }
      m_Rootbeer.runAll(jobs);
    }
  }

  public boolean getRanGpu(){
    return m_ranGpu;  
  }
  
  public Iterator<Kernel> run(Iterator<Kernel> jobs) {
    return m_Rootbeer.run(jobs);
  }
  
  public void addStatsRow(StatsRow row) {
    m_stats.add(row);
  }
  
  public List<StatsRow> getStats(){
    return m_stats;
  }
}
