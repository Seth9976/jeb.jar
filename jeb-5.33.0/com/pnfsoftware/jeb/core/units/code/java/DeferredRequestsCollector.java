package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DeferredRequestsCollector {
   Set deferredClassDecompilations = new TreeSet();
   Set deferredFieldDecompilations = new TreeSet();
   Set deferredMethodDecompilations = new TreeSet();

   public void clear() {
      this.pullRequestedClassDecompilations();
      this.pullRequestedFieldDecompilations();
      this.pullRequestedMethodDecompilations();
   }

   public boolean isEmpty() {
      return this.deferredClassDecompilations.isEmpty() && this.deferredFieldDecompilations.isEmpty() && this.deferredMethodDecompilations.isEmpty();
   }

   public void request(String var1) {
      if (var1 != null) {
         if (var1.contains("->")) {
            if (var1.contains("(")) {
               JvmMethodSig.parse(var1);
               this.deferredMethodDecompilations.add(var1);
            } else {
               if (!var1.contains(":")) {
                  throw new IllegalArgumentException();
               }

               JvmFieldSig.parse(var1);
               this.deferredFieldDecompilations.add(var1);
            }
         } else {
            this.deferredClassDecompilations.add(var1);
         }
      }
   }

   public List pullRequestedClassDecompilations() {
      ArrayList var1 = new ArrayList(this.deferredClassDecompilations);
      this.deferredClassDecompilations.clear();
      return var1;
   }

   public List pullRequestedFieldDecompilations() {
      ArrayList var1 = new ArrayList(this.deferredFieldDecompilations);
      this.deferredFieldDecompilations.clear();
      return var1;
   }

   public List pullRequestedMethodDecompilations() {
      ArrayList var1 = new ArrayList(this.deferredMethodDecompilations);
      this.deferredMethodDecompilations.clear();
      return var1;
   }

   @Override
   public String toString() {
      return this.deferredClassDecompilations + ", " + this.deferredMethodDecompilations + ", " + this.deferredFieldDecompilations;
   }
}
