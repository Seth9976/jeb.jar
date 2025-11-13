package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class ETypeInfo {
   IERoutineContext ectx;
   IWildcardTypeManager etypeman;
   List list = new ArrayList();
   int unchanged;
   int changed;
   boolean allowApplyComplexTypes;

   public ETypeInfo(IERoutineContext var1) {
      this.ectx = var1;
      this.etypeman = var1.getWildcardTypeManager();
   }

   public IERoutineContext getContext() {
      return this.ectx;
   }

   public IWildcardTypeManager getWildcardTypeManager() {
      return this.etypeman;
   }

   public boolean isAllowApplyComplexTypes() {
      return this.allowApplyComplexTypes;
   }

   public void setAllowApplyComplexTypes(boolean var1) {
      this.allowApplyComplexTypes = var1;
   }

   public void recordConflict(String var1, IEGeneric var2, IWildcardType var3, IWildcardType var4) {
      ETypeInfo.Entry var5 = new ETypeInfo.Entry(var1, var2, var3, var4);
      this.list.add(var5);
   }

   public void resetCounters() {
      this.changed = 0;
      this.unchanged = 0;
   }

   public int recordChanged() {
      this.changed++;
      return this.changed;
   }

   public int getChangedCounter() {
      return this.changed;
   }

   public int recordUnchanged() {
      this.unchanged++;
      return this.unchanged;
   }

   public int getUnchangedCounter() {
      return this.unchanged;
   }

   public List getConflicts() {
      return this.list;
   }

   public int getConflictsCount() {
      return this.list.size();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (ETypeInfo.Entry var4 : this.list) {
         if (var2 >= 1) {
            var1.append("\n");
         }

         Strings.ff(var1, "- CONFLICT: %s", var4);
         var2++;
      }

      return var1.toString();
   }

   public static class Entry {
      String msg;
      IEGeneric elt;
      IWildcardType currentType;
      IWildcardType wantedType;

      public Entry(String var1, IEGeneric var2, IWildcardType var3, IWildcardType var4) {
         this.msg = var1;
         this.elt = var2;
         this.currentType = var3;
         this.wantedType = var4;
      }

      public String getMessage() {
         return this.msg;
      }

      public IEGeneric getElement() {
         return this.elt;
      }

      public IWildcardType getCurrentType() {
         return this.currentType;
      }

      public IWildcardType getWantedType() {
         return this.wantedType;
      }

      @Override
      public String toString() {
         return Strings.ff("%s: target=\"%s\", current_type=%s, wanted_type=%s", this.msg, this.elt, this.currentType, this.wantedType);
      }
   }
}
