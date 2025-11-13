package com.pnfsoftware.jeb.core.units.code.asm.type;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class ClassVtablePaths {
   Object base;
   INodeFeatureExtractor fx;
   List out = new ArrayList();
   Map processed = new IdentityHashMap();
   private boolean valid;

   public ClassVtablePaths(Object var1, INodeFeatureExtractor var2) {
      this.base = var1;
      this.fx = var2;
   }

   public List determinePartial() {
      this.out.clear();
      this.processed.clear();
      this.determinePartial(this.base, new ArrayList());
      return this.out;
   }

   private void determinePartial(Object var1, List var2) {
      var2.add(var1);
      this.processed.put(var1, null);

      for (Object var4 : this.fx.getParents(var1)) {
         if (!this.processed.containsKey(var4)) {
            this.determinePartial(var4, var2);
         }
      }

      if (!var2.isEmpty()) {
         this.out.add(new ArrayList(var2));
         var2.clear();
      }
   }

   public List determineFull() {
      this.out.clear();
      this.processed.clear();
      this.determineFull(this.base, new ArrayList());
      return this.out;
   }

   private void determineFull(Object var1, List var2) {
      var2.add(var1);
      this.valid = true;

      for (Object var4 : this.fx.getParents(var1)) {
         if (!this.processed.containsKey(var4)) {
            this.determineFull(var4, var2);
         }
      }

      if (this.valid) {
         this.out.add(new ArrayList(var2));
         this.valid = false;
      }

      var2.remove(var2.size() - 1);
      this.processed.put(var1, null);
   }
}
