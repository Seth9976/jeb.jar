package com.pnfsoftware.jeb.core.units.code.android;

import java.util.List;

public class ParametersInfo {
   private final int regcnt;
   private final List paramindexes;

   public ParametersInfo(int var1, List var2) {
      this.regcnt = var1;
      this.paramindexes = var2;
   }

   public int getRegisterCount() {
      return this.regcnt;
   }

   public List getParameterIndices() {
      return this.paramindexes;
   }

   public int getParameterIndex(int var1) {
      return (Integer)this.paramindexes.get(var1);
   }

   public int getParameterCount() {
      return this.paramindexes.size();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.paramindexes == null ? 0 : this.paramindexes.hashCode());
      return 31 * var1 + this.regcnt;
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
         ParametersInfo var2 = (ParametersInfo)var1;
         if (this.paramindexes == null) {
            if (var2.paramindexes != null) {
               return false;
            }
         } else if (!this.paramindexes.equals(var2.paramindexes)) {
            return false;
         }

         return this.regcnt == var2.regcnt;
      }
   }
}
