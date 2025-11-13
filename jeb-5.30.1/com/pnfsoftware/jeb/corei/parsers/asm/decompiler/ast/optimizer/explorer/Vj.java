package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Vj {
   SortedMap q = new TreeMap();

   public void q(int var1, Class var2) {
      Object var3 = (List)this.q.get(var1);
      if (var3 == null) {
         var3 = new ArrayList();
         this.q.put(var1, var3);
      }

      var3.add(var2);
   }

   public boolean q() {
      return this.q.isEmpty();
   }

   public int RF() {
      return (Integer)this.q.lastKey();
   }

   public int xK() {
      return (Integer)this.q.firstKey();
   }

   public List q(int var1) {
      return (List)this.q.get(var1);
   }

   @Override
   public String toString() {
      return "OptimizationPipeline [pipeline=" + this.q + "]";
   }
}
