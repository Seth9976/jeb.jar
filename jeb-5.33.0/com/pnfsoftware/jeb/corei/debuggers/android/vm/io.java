package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueArray;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.oY;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.wX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class io extends ValueArray {
   private static final ILogger pC = GlobalLog.getLogger(io.class);
   Tb UT;

   public io(Tb var1, long var2, long var4) {
      super(var2, var4, "array");
      this.UT = var1;
   }

   @Override
   public long getRefTypeId() {
      if (this.refTypeId == 0L && this.getObjectId() != 0L) {
         try {
            bA var1 = (bA)this.UT.UT();
            this.refTypeId = var1.fI().pC(this.getObjectId());
         } catch (wX | IOException var2) {
            pC.catching(var2);
         }
      }

      return this.refTypeId;
   }

   @Override
   public String getTypeName() {
      String var1 = this.pC();
      return var1 != null ? var1 : super.getTypeName();
   }

   public String pC() {
      if (this.getObjectId() == 0L) {
         return null;
      } else {
         try {
            if (!this.UT.pC()) {
               return null;
            } else {
               bA var1 = (bA)this.UT.UT();
               long var2 = this.getRefTypeId();
               return var1.oT().kS(var2);
            }
         } catch (wX | IOException var4) {
            pC.catching(var4);
            return null;
         }
      }
   }

   @Override
   public int getLength() {
      long var1 = this.getObjectId();
      if (var1 == 0L) {
         return -1;
      } else {
         try {
            if (!this.UT.pC()) {
               return -1;
            } else {
               bA var3 = (bA)this.UT.UT();
               return var3.gp().NS(var1);
            }
         } catch (IOException | oY var4) {
            pC.catching(var4);
            return -1;
         }
      }
   }

   @Override
   public List getValue() {
      return this.getElements(0, -1);
   }

   @Override
   public boolean hasChildren() {
      long var1 = this.getObjectId();
      if (var1 == 0L) {
         return false;
      } else if (!this.UT.pC()) {
         return false;
      } else {
         try {
            bA var3 = (bA)this.UT.UT();
            int var4 = var3.gp().NS(var1);
            return var4 > 0;
         } catch (oY | IOException var5) {
            pC.catching(var5);
            return false;
         }
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   public List getElements(int var1, int var2) {
      long var3 = this.getObjectId();
      if (var3 == 0L) {
         return null;
      } else {
         ArrayList var5 = new ArrayList();

         try {
            if (!this.UT.pC()) {
               return null;
            }
         } catch (oY | wX | IOException var19) {
            pC.catching(var19);
            return null;
         }

         bA var6;
         String var9;
         int var10;
         try {
            var6 = (bA)this.UT.UT();
            long var7 = this.getRefTypeId();
            var9 = var6.oT().kS(var7);
            Object[] var10000 = new Object[]{var3, var3, var9};
            var10 = var6.gp().NS(var3);
            if (var1 < 0 || var1 >= var10) {
               return null;
            }
         } catch (oY | wX | IOException var22) {
            pC.catching(var22);
            return null;
         }

         if (var2 < 0) {
            var2 = var10 - var1;
         } else if (var1 + var2 > var10) {
            return null;
         }

         String var11;
         try {
            var11 = var9;
            if (var9 == null || var9.length() < 2 || var9.charAt(0) != '[') {
               pC.error("Expected an array object");
               return null;
            }
         } catch (oY | wX | IOException var21) {
            pC.catching(var21);
            return null;
         }

         try {
            rG[] var12 = var6.gp().A(var3, var1, var2);
            if (var12 != null) {
               int var13 = var1;
               String var14 = var11.substring(1);

               for (rG var18 : var12) {
                  var5.add(oP.pC(var13 + "", 0, this.UT, new Kr.Av(var3, var13), var18, var14));
                  var13++;
               }
            }

            return var5;
         } catch (oY | wX | IOException var20) {
            pC.catching(var20);
            return null;
         }
      }
   }

   @Override
   public String format() {
      if (this.getObjectId() == 0L) {
         return "null";
      } else {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "array@%d (type=%s)\n", this.getObjectId(), this.getTypeName());
         var1.append("[");
         List var2 = this.getValue();
         if (var2 != null) {
            int var3 = 0;

            for (IDebuggerVariable var5 : var2) {
               if (var3 > 0) {
                  var1.append(", ");
                  if (var3 % 100 == 0) {
                     var1.append("\n  ");
                  }
               }

               var1.append(var5.getTypedValue().toString());
               var3++;
            }
         }

         var1.append("]");
         return var1.toString();
      }
   }

   @Override
   public String toString() {
      return this.getObjectId() == 0L ? "null" : Strings.ff("array@%d", this.getObjectId());
   }
}
