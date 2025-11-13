package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.format.Strings;
import java.nio.charset.Charset;

public class cix {
   IVirtualMemory pC;
   long A;
   long kS;
   ciw wS = new ciw();
   int UT;
   int E;
   int sY;

   public cix(IVirtualMemory var1, long var2, long var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var4;
   }

   public ciw pC() {
      return this.wS;
   }

   public boolean A() {
      return this.UT != 0;
   }

   public boolean kS() {
      return this.E != 0;
   }

   public int wS() {
      return this.E;
   }

   public int UT() {
      return this.sY;
   }

   public void E() throws Exception {
      this.pC(0, 0, this.wS, false);
   }

   void pC(int var1, int var2, ciw var3, boolean var4) throws Exception {
      this.UT++;
      if (var1 > this.sY) {
         this.sY = var1;
      }

      long var5 = this.A + var2;
      if (var4) {
         int var14 = this.pC.readLEInt(var5);
         int var15 = this.pC.readLEInt(var5 + 4L);
         int var16 = this.pC.readLEInt(var5 + 8L);
         var3.pC(new civ(var3, var14, var15, var16));
         this.E++;
      } else {
         int var7 = this.pC.readLEShort(var5 + 12L) & '\uffff';
         int var8 = this.pC.readLEShort(var5 + 14L) & '\uffff';
         var5 += 16L;

         for (int var9 = 0; var9 < var7 + var8; var9++) {
            int var10 = this.pC.readLEInt(var5);
            int var11 = this.pC.readLEInt(var5 + 4L);
            ciw var12;
            if ((var10 & -2147483648) != 0) {
               var12 = new ciw(this.pC(var10 & 2147483647), var3);
            } else {
               var12 = new ciw(var10, var3);
            }

            if ((var11 & -2147483648) != 0) {
               var11 &= Integer.MAX_VALUE;
               this.pC(var1 + 1, var11, var12, false);
            } else {
               this.pC(var1 + 1, var11, var12, true);
            }

            var5 += 8L;
         }
      }
   }

   public String pC(int var1) throws MemoryException {
      int var2 = this.pC.readLEShort(this.A + var1) & '\uffff';
      byte[] var3 = new byte[var2 * 2];
      if (this.pC.read(this.A + var1 + 2L, var3.length, var3, 0) != var3.length) {
         throw new MemoryException();
      } else {
         return new String(var3, Charset.forName("UTF-16LE"));
      }
   }

   public static String A(int var0) {
      switch (var0) {
         case 1:
            return "CURSOR";
         case 2:
            return "BITMAP";
         case 3:
            return "ICON";
         case 4:
            return "MENU";
         case 5:
            return "DIALOG";
         case 6:
            return "STRING";
         case 7:
            return "FONTDIR";
         case 8:
            return "FONT";
         case 9:
            return "ACCELERATOR";
         case 10:
            return "RCDATA";
         case 11:
            return "MESSAGETABLE";
         case 12:
            return "GROUP_CURSOR";
         case 13:
         case 15:
         case 18:
         default:
            return null;
         case 14:
            return "GROUP_ICON";
         case 16:
            return "VERSION";
         case 17:
            return "DLGINCLUDE";
         case 19:
            return "PLUGPLAY";
         case 20:
            return "VXD";
         case 21:
            return "ANICURSOR";
         case 22:
            return "ANIICON";
         case 23:
            return "HTML";
         case 24:
            return "MANIFEST";
      }
   }

   public String pC(ciw var1) {
      String var2 = var1.kS();
      if (var2 == null) {
         int var3 = var1.A();
         if (var1.wS() == this.wS) {
            var2 = A(var3);
         }

         if (var2 == null) {
            var2 = var1.A() + "";
         }
      }

      return var2;
   }

   public String A(ciw var1) {
      String var2 = "";

      do {
         String var3 = this.pC(var1);
         var2 = var3 + " " + var2;
         var1 = var1.wS();
      } while (var1 != null && !var1.pC());

      return var2.trim();
   }

   @Override
   public String toString() {
      return Strings.ff("Resources:%d,Nodes:%d,Depth:%d", this.wS(), this.UT, this.UT());
   }
}
