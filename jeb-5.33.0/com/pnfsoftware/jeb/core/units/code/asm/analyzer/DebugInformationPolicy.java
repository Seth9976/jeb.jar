package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DebugInformationPolicy {
   public static final int USAGE_NONE = 0;
   public static final int USAGE_INTERNAL = 1;
   public static final int USAGE_ALL = 2;
   public static final int RETRIEVAL_NONE = 0;
   public static final int RETRIEVAL_LOCAL = 1;
   public static final int RETRIEVAL_ALL = 2;
   @SerId(1)
   private int usage;
   @SerId(2)
   private int retrieval;

   public DebugInformationPolicy(int var1, int var2) {
      this.usage = var1;
      this.retrieval = var2;
   }

   public int getUsage() {
      return this.usage;
   }

   public int getRetrieval() {
      return this.retrieval;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.retrieval;
      return 31 * var1 + this.usage;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         DebugInformationPolicy var2 = (DebugInformationPolicy)var1;
         return this.retrieval != var2.retrieval ? false : this.usage == var2.usage;
      }
   }

   @Override
   public String toString() {
      return "DebugInformationPolicy [usage=" + this.usage + ", retrieval=" + this.retrieval + "]";
   }
}
