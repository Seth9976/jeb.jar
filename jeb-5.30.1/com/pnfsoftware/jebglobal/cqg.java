package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.format.Strings;
import java.nio.charset.Charset;

public class cqg {
   IVirtualMemory q;
   long RF;
   long xK;
   cqf Dw = new cqf();
   int Uv;
   int oW;
   int gO;

   public cqg(IVirtualMemory var1, long var2, long var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var4;
   }

   public cqf q() {
      return this.Dw;
   }

   public boolean RF() {
      return this.Uv != 0;
   }

   public boolean xK() {
      return this.oW != 0;
   }

   public int Dw() {
      return this.oW;
   }

   public int Uv() {
      return this.gO;
   }

   public void oW() throws Exception {
      this.q(0, 0, this.Dw, false);
   }

   void q(int var1, int var2, cqf var3, boolean var4) throws Exception {
      this.Uv++;
      if (var1 > this.gO) {
         this.gO = var1;
      }

      long var5 = this.RF + var2;
      if (var4) {
         int var14 = this.q.readLEInt(var5);
         int var15 = this.q.readLEInt(var5 + 4L);
         int var16 = this.q.readLEInt(var5 + 8L);
         var3.q(new cqe(var3, var14, var15, var16));
         this.oW++;
      } else {
         int var7 = this.q.readLEShort(var5 + 12L) & '\uffff';
         int var8 = this.q.readLEShort(var5 + 14L) & '\uffff';
         var5 += 16L;

         for (int var9 = 0; var9 < var7 + var8; var9++) {
            int var10 = this.q.readLEInt(var5);
            int var11 = this.q.readLEInt(var5 + 4L);
            cqf var12;
            if ((var10 & -2147483648) != 0) {
               var12 = new cqf(this.q(var10 & 2147483647), var3);
            } else {
               var12 = new cqf(var10, var3);
            }

            if ((var11 & -2147483648) != 0) {
               var11 &= Integer.MAX_VALUE;
               this.q(var1 + 1, var11, var12, false);
            } else {
               this.q(var1 + 1, var11, var12, true);
            }

            var5 += 8L;
         }
      }
   }

   public String q(int var1) throws MemoryException {
      int var2 = this.q.readLEShort(this.RF + var1) & '\uffff';
      byte[] var3 = new byte[var2 * 2];
      if (this.q.read(this.RF + var1 + 2L, var3.length, var3, 0) != var3.length) {
         throw new MemoryException();
      } else {
         return new String(var3, Charset.forName("UTF-16LE"));
      }
   }

   public static String RF(int var0) {
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

   public String q(cqf var1) {
      String var2 = var1.xK();
      if (var2 == null) {
         int var3 = var1.RF();
         if (var1.Uv() == this.Dw) {
            var2 = RF(var3);
         }

         if (var2 == null) {
            var2 = var1.RF() + "";
         }
      }

      return var2;
   }

   public String RF(cqf var1) {
      String var2 = "";

      do {
         String var3 = this.q(var1);
         var2 = var3 + " " + var2;
         var1 = var1.Uv();
      } while (var1 != null && !var1.q());

      return var2.trim();
   }

   @Override
   public String toString() {
      return Strings.ff("Resources:%d,Nodes:%d,Depth:%d", this.Dw(), this.Uv, this.Uv());
   }
}
