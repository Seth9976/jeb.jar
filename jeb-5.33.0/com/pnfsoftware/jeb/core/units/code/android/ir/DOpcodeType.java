package com.pnfsoftware.jeb.core.units.code.android.ir;

public enum DOpcodeType {
   IR_NOP,
   IR_ASSIGN,
   IR_INVOKE,
   IR_JUMP,
   IR_JCOND,
   IR_SWITCH,
   IR_RETURN,
   IR_THROW,
   IR_STORE_EXCEPTION,
   IR_MONITOR_ENTER,
   IR_MONITOR_EXIT;

   public final boolean isAnyOf(DOpcodeType... var1) {
      for (DOpcodeType var5 : var1) {
         if (var5 == this) {
            return true;
         }
      }

      return false;
   }

   public final int indexOf(DOpcodeType... var1) {
      int var2 = 0;

      for (DOpcodeType var6 : var1) {
         if (var6 == this) {
            return var2;
         }

         var2++;
      }

      return -1;
   }
}
