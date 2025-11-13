package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class RC {
   private static final ILogger E = GlobalLog.getLogger(RC.class);
   IVirtualMemory pC;
   long A;
   List kS = new ArrayList();
   int wS = 0;
   int UT;

   public RC(IVirtualMemory var1, long var2) {
      this.pC = var1;
      this.A = var2;
   }

   public boolean pC() throws MemoryException {
      int var1 = 0;
      int var2 = this.pC.readLEInt(this.A + 60L);
      if (var2 > 4096) {
         return false;
      } else {
         while (var2 >= 64) {
            if (this.pC.readLEInt(this.A + var2 - 4L) == 1751345490) {
               var1 = var2 - 4;
               break;
            }

            var2--;
         }

         if (var1 == 0) {
            return false;
         } else {
            this.UT = this.pC.readLEInt(this.A + var2);
            var2 -= 8;

            int var3;
            for (var3 = 0; var2 >= 64; var2 -= 4) {
               if ((this.pC.readLEInt(this.A + var2) ^ this.UT) == 1399742788) {
                  var3 = var2;
                  break;
               }
            }

            if (var3 == 0) {
               return false;
            } else {
               var2 = var3 + 4;

               for (int var4 = 0; var4 < 3; var4++) {
                  if ((this.pC.readLEInt(this.A + var2) ^ this.UT) != 0) {
                     return false;
                  }

                  var2 += 4;
               }

               while (var2 < var1) {
                  RC.Av var7 = new RC.Av(this.pC.readLEInt(this.A + var2) ^ this.UT, this.pC.readLEInt(this.A + var2 + 4L) ^ this.UT);
                  this.kS.add(var7);
                  var2 += 8;
               }

               if (this.kS.size() == 0) {
                  return false;
               } else {
                  this.wS = this.pC(var3);
                  return true;
               }
            }
         }
      }
   }

   private int pC(int var1) throws MemoryException {
      int var2 = var1;
      int var3 = 0;

      while (var3 < var1) {
         if (var3 >= 60 && var3 < 64) {
            var3++;
         } else {
            var2 += Integer.rotateLeft(this.pC.readByte(this.A + var3) & 255, var3);
            var3++;
         }
      }

      for (RC.Av var5 : this.kS) {
         var2 += Integer.rotateLeft(var5.pC, var5.wS);
      }

      return var2;
   }

   private static String A(int var0) {
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

      for (RC.Av var3 : this.kS) {
         var1.append(Strings.LINESEP);
         var1.append(var3.toString());
      }

      var1.append(Strings.LINESEP);
      var1.append("---------------------------------------------------");
      var1.append(Strings.LINESEP);
      if (this.UT == this.wS) {
         Strings.ff(var1, S.L("Checksum is CORRECT (%X)"), this.wS);
      } else {
         Strings.ff(var1, S.L("Checksum is WRONG (read=%X, computed=%X)"), this.UT, this.wS);
      }

      return var1.toString();
   }

   private static class Av {
      final int pC;
      final int A;
      final int kS;
      final int wS;

      public Av(int var1, int var2) {
         this.pC = var1;
         this.A = (var1 & -65536) >> 16;
         this.kS = var1 & 65535;
         this.wS = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("- @comp.id=%08x [product id=0x%04x, compiler version=%6s] - count=%3d --> %s", this.pC, this.A, this.kS, this.wS, RC.A(this.A));
      }
   }
}
