package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class PY {
   private static final ILogger gO = GlobalLog.getLogger(PY.class);
   public static final String q = "Rich header";
   IVirtualMemory RF;
   long xK;
   List Dw = new ArrayList();
   int Uv = 0;
   int oW;

   public PY(IVirtualMemory var1, long var2) {
      this.RF = var1;
      this.xK = var2;
   }

   public boolean q() throws MemoryException {
      int var1 = 0;
      int var2 = this.RF.readLEInt(this.xK + 60L);
      if (var2 > 4096) {
         return false;
      } else {
         while (var2 >= 64) {
            if (this.RF.readLEInt(this.xK + var2 - 4L) == 1751345490) {
               var1 = var2 - 4;
               break;
            }

            var2--;
         }

         if (var1 == 0) {
            return false;
         } else {
            this.oW = this.RF.readLEInt(this.xK + var2);
            var2 -= 8;

            int var3;
            for (var3 = 0; var2 >= 64; var2 -= 4) {
               if ((this.RF.readLEInt(this.xK + var2) ^ this.oW) == 1399742788) {
                  var3 = var2;
                  break;
               }
            }

            if (var3 == 0) {
               return false;
            } else {
               var2 = var3 + 4;

               for (int var4 = 0; var4 < 3; var4++) {
                  if ((this.RF.readLEInt(this.xK + var2) ^ this.oW) != 0) {
                     return false;
                  }

                  var2 += 4;
               }

               while (var2 < var1) {
                  PY.eo var7 = new PY.eo(this.RF.readLEInt(this.xK + var2) ^ this.oW, this.RF.readLEInt(this.xK + var2 + 4L) ^ this.oW);
                  this.Dw.add(var7);
                  var2 += 8;
               }

               if (this.Dw.size() == 0) {
                  return false;
               } else {
                  this.Uv = this.q(var3);
                  return true;
               }
            }
         }
      }
   }

   private int q(int var1) throws MemoryException {
      int var2 = var1;
      int var3 = 0;

      while (var3 < var1) {
         if (var3 >= 60 && var3 < 64) {
            var3++;
         } else {
            var2 += Integer.rotateLeft(this.RF.readByte(this.xK + var3) & 255, var3);
            var3++;
         }
      }

      for (PY.eo var5 : this.Dw) {
         var2 += Integer.rotateLeft(var5.q, var5.Dw);
      }

      return var2;
   }

   private static String RF(int var0) {
      if (var0 == 1) {
         return "Visual Studio";
      } else if (var0 >= 253 && var0 < 271) {
         return "Visual Studio 2015";
      } else if (var0 >= 217 && var0 < 253) {
         return "Visual Studio 2013";
      } else if (var0 >= 199 && var0 < 217) {
         return "Visual Studio 2012";
      } else if (var0 >= 152 && var0 < 199) {
         return "Visual Studio 2010";
      } else if (var0 >= 131 && var0 < 152) {
         return "Visual Studio 2008";
      } else if (var0 >= 109 && var0 < 131) {
         return "Visual Studio 2005";
      } else {
         return var0 >= 90 && var0 < 109 ? "Visual Studio 2003" : S.L("unknown compiler");
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(S.L("Linked files according to the Rich header:"));
      var1.append(Strings.LINESEP);
      var1.append("---------------------------------------------------");

      for (PY.eo var3 : this.Dw) {
         var1.append(Strings.LINESEP);
         var1.append(var3.toString());
      }

      var1.append(Strings.LINESEP);
      var1.append("---------------------------------------------------");
      var1.append(Strings.LINESEP);
      if (this.oW == this.Uv) {
         Strings.ff(var1, S.L("Checksum is CORRECT (%X)"), this.Uv);
      } else {
         Strings.ff(var1, S.L("Checksum is WRONG (read=%X, computed=%X)"), this.oW, this.Uv);
      }

      return var1.toString();
   }

   private static class eo {
      final int q;
      final int RF;
      final int xK;
      final int Dw;

      public eo(int var1, int var2) {
         this.q = var1;
         this.RF = (var1 & -65536) >> 16;
         this.xK = var1 & 65535;
         this.Dw = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("- @comp.id=%08x [product id=0x%04x, compiler version=%6s] - count=%3d --> %s", this.q, this.RF, this.xK, this.Dw, PY.RF(this.RF));
      }
   }
}
