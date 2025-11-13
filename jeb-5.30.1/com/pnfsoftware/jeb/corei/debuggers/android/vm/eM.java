package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueArray;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class eM extends ValueArray {
   private static final ILogger q = GlobalLog.getLogger(eM.class);
   CI Uv;

   public eM(CI var1, long var2, long var4) {
      super(var2, var4, "array");
      this.Uv = var1;
   }

   @Override
   public long getRefTypeId() {
      if (this.refTypeId == 0L && this.getObjectId() != 0L) {
         try {
            Ux var1 = (Ux)this.Uv.oW();
            this.refTypeId = var1.HF().q(this.getObjectId());
         } catch (zy | IOException var2) {
            q.catching(var2);
         }
      }

      return this.refTypeId;
   }

   @Override
   public String getTypeName() {
      String var1 = this.RF();
      return var1 != null ? var1 : super.getTypeName();
   }

   public String RF() {
      if (this.getObjectId() == 0L) {
         return null;
      } else {
         try {
            if (!this.Uv.RF()) {
               return null;
            } else {
               Ux var1 = (Ux)this.Uv.oW();
               long var2 = this.getRefTypeId();
               return var1.JY().oW(var2);
            }
         } catch (zy | IOException var4) {
            q.catching(var4);
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
            if (!this.Uv.RF()) {
               return -1;
            } else {
               Ux var3 = (Ux)this.Uv.oW();
               return var3.zz().PV(var1);
            }
         } catch (IOException | Fx var4) {
            q.catching(var4);
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
      } else if (!this.Uv.RF()) {
         return false;
      } else {
         try {
            Ux var3 = (Ux)this.Uv.oW();
            int var4 = var3.zz().PV(var1);
            return var4 > 0;
         } catch (Fx | IOException var5) {
            q.catching(var5);
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
            if (!this.Uv.RF()) {
               return null;
            }
         } catch (Fx | zy | IOException var19) {
            q.catching(var19);
            return null;
         }

         Ux var6;
         String var9;
         int var10;
         try {
            var6 = (Ux)this.Uv.oW();
            long var7 = this.getRefTypeId();
            var9 = var6.JY().oW(var7);
            Object[] var10000 = new Object[]{var3, var3, var9};
            var10 = var6.zz().PV(var3);
            if (var1 < 0 || var1 >= var10) {
               return null;
            }
         } catch (Fx | zy | IOException var22) {
            q.catching(var22);
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
               q.error("Expected an array object");
               return null;
            }
         } catch (Fx | zy | IOException var21) {
            q.catching(var21);
            return null;
         }

         try {
            ch[] var12 = var6.zz().RF(var3, var1, var2);
            if (var12 != null) {
               int var13 = var1;
               String var14 = var11.substring(1);

               for (ch var18 : var12) {
                  var5.add(Bu.q(var13 + "", 0, this.Uv, new LR.eo(var3, var13), var18, var14));
                  var13++;
               }
            }

            return var5;
         } catch (Fx | zy | IOException var20) {
            q.catching(var20);
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
