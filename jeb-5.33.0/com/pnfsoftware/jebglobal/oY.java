package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;

public class oY extends JebException {
   private static final long serialVersionUID = 1L;
   private int pC;
   private int A;

   public oY(int var1, short var2) {
      super(Strings.ff("JDWP request ID %d returned error code %d %s", var1, var2, pC(var2)));
      this.pC = var1;
      this.A = var2;
   }

   public int pC() {
      return this.A;
   }

   private static String pC(short var0) {
      String var1 = A(var0);
      return var1 != null ? Strings.ff("(%s)", var1) : "";
   }

   private static String A(short var0) {
      switch (var0) {
         case 10:
            return "INVALID_THREAD";
         case 13:
            return "THREAD_NOT_SUSPENDED";
         case 14:
            return "THREAD_SUSPENDED";
         case 20:
            return "INVALID_OBJECT";
         case 21:
            return "INVALID_CLASS";
         case 22:
            return "CLASS_NOT_PREPARED";
         case 23:
            return "INVALID_METHODID";
         case 24:
            return "INVALID_LOCATION";
         case 25:
            return "INVALID_FIELDID";
         case 34:
            return "TYPE_MISMATCH";
         case 35:
            return "INVALID_SLOT";
         case 41:
            return "NOT_FOUND";
         case 99:
            return "NOT_IMPLEMENTED";
         case 100:
            return "NULL_POINTER";
         case 103:
            return "ILLEGAL_ARGUMENT";
         case 110:
            return "OUT_OF_MEMORY";
         case 111:
            return "ACCESS_DENIED";
         case 112:
            return "VM_DEAD";
         case 113:
            return "INTERNAL";
         default:
            return null;
      }
   }
}
