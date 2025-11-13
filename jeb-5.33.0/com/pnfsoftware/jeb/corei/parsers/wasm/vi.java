package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.chm;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

class vi {
   private static final ILogger kS = GlobalLog.getLogger(vi.class);
   byte[] pC;
   int A;

   vi(byte[] var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("@%Xh", this.A);
   }

   public int pC() {
      return this.pC.length - this.A;
   }

   int A() {
      int var1 = this.pC[this.A] & 255;
      this.A++;
      return var1;
   }

   int kS() {
      uX.Av var1 = uX.E(this.pC, this.A);
      this.A = this.A + var1.A;
      return (int)var1.pC;
   }

   int wS() {
      int var1 = this.A();
      if (!Tb.E(var1)) {
         throw new RuntimeException(Strings.ff("Expected a value type, got 0x%X", var1));
      } else {
         return var1;
      }
   }

   int UT() {
      int var1 = this.A();
      if (!Tb.UT(var1)) {
         throw new RuntimeException(Strings.ff("Expected a referece type, got 0x%X", var1));
      } else {
         return var1;
      }
   }

   DH E() {
      int var1 = this.A();
      if (var1 != 96) {
         throw new RuntimeException(Strings.ff("Expected the function byte (0x60), got 0x%X", var1));
      } else {
         int var2 = this.kS();
         int[] var3 = new int[var2];

         for (int var4 = 0; var4 < var2; var4++) {
            int var5 = this.wS();
            var3[var4] = var5;
         }

         int var8 = this.kS();
         int[] var9 = new int[var8];

         for (int var6 = 0; var6 < var8; var6++) {
            int var7 = this.wS();
            var9[var6] = var7;
         }

         if (var8 != 0 && var8 != 1) {
            throw new chm("Multiple return types are not supported");
         } else {
            Integer var10 = var8 == 0 ? null : var9[0];
            return new DH(var10, var3);
         }
      }
   }

   String sY() {
      int var1 = this.kS();
      String var2 = new String(this.pC, this.A, var1, Charset.forName("UTF-8"));
      this.A += var1;
      return var2;
   }

   KD pC(List var1) {
      String var2 = this.sY();
      String var3 = this.sY();
      int var4 = this.ys();
      switch (var4) {
         case 0:
            int var8 = this.kS();
            return new yt(var2, var3, (DH)var1.get(var8));
         case 1:
            Mh var7 = this.fI();
            return new HE(var2, var3, var7);
         case 2:
            oP var6 = this.WR();
            return new sy(var2, var3, var6);
         case 3:
            zp var5 = this.oT();
            return new RC(var2, var3, var5);
         default:
            throw new RuntimeException(Strings.ff("Unsupported import descriptor: 0x%X", var4));
      }
   }

   int ys() {
      int var1 = this.A();
      if (var1 >= 0 && var1 <= 3) {
         return var1;
      } else {
         throw new RuntimeException("Unsupported external_kind value: " + var1);
      }
   }

   p ld() {
      int var1 = this.A();
      int var2 = this.kS();
      Integer var3 = null;
      if ((var1 & 1) != 0) {
         var3 = this.kS();
      }

      boolean var4 = false;
      if ((var1 & 2) != 0) {
         var4 = true;
      }

      return new p(var2, var3, var4);
   }

   List pC(int var1, boolean var2) {
      gJ var3 = new gJ(null);

      try {
         ArrayList var4 = new ArrayList();

         while (this.A < var1) {
            m var5 = (m)var3.parseAt(this.pC, this.A, var1);
            this.A = this.A + var5.getSize();
            var4.add(var5);
            if (var2 && var5.wS()) {
               break;
            }
         }

         if (this.A > var1) {
            throw new RuntimeException();
         } else if (var4.isEmpty()) {
            throw new RuntimeException();
         } else if (!((m)var4.get(var4.size() - 1)).wS()) {
            throw new RuntimeException();
         } else {
            return var4;
         }
      } catch (ProcessorException var6) {
         throw new RuntimeException(var6);
      }
   }

   qt gp() {
      List var1 = this.pC(this.pC.length, true);
      if (!var1.isEmpty() && ((m)var1.get(var1.size() - 1)).wS()) {
         var1.remove(var1.size() - 1);
         return new qt(var1);
      } else {
         throw new NullPointerException("Unsupported expression format, must end with END (0x0B)");
      }
   }

   zp oT() {
      int var1 = this.wS();
      boolean var2 = (this.A() & 1) == 1;
      return new zp(var1, var2);
   }

   Mh fI() {
      int var1 = this.UT();
      p var2 = this.ld();
      return new Mh(var1, var2);
   }

   oP WR() {
      return new oP(this.ld());
   }

   rQ NS() {
      zp var1 = this.oT();
      qt var2 = this.gp();
      return new rQ(var1, var2);
   }

   Ws vP() {
      String var1 = this.sY();
      int var2 = this.ys();
      int var3 = this.kS();
      return new Ws(var1, var2, var3);
   }

   K xC() {
      int var1 = this.A();
      if (var1 > 7) {
         throw new RuntimeException(Strings.ff("Unsupported element segment with header 0x%X", var1));
      } else if (var1 != 0) {
         throw new chm("Unsupported element segment");
      } else {
         byte var2 = 0;
         qt var3 = this.gp();
         int var4 = this.kS();
         ArrayList var5 = new ArrayList(var4);

         for (int var6 = 0; var6 < var4; var6++) {
            int var7 = this.kS();
            var5.add(var7);
         }

         return new K(var2, var3, var5);
      }
   }

   bO pC(gJ var1, boolean var2) {
      int var3 = this.kS();
      int var4 = this.A;
      int var5 = this.kS();
      ArrayList var6 = new ArrayList();

      for (int var7 = 0; var7 < var5; var7++) {
         int var8 = this.kS();
         int var9 = this.wS();

         while (var8-- > 0) {
            var6.add(var9);
         }
      }

      int var11 = this.A;
      int var12 = var4 + var3;

      try {
         List var13 = var1.pC(var11, var12, var2);
         this.A = var12;
         return new bO(var6, var11, var12, var13);
      } catch (ProcessorException var10) {
         kS.error("wasm parsing error: method body will be empty: %s", var10.getMessage());
         JebCoreService.notifySilentExceptionToClient(var10);
         this.A = var12;
         return new bO(var6, var11, var12, new ArrayList());
      }
   }

   Sv ED() {
      int var1 = this.A();
      if (var1 > 2) {
         throw new RuntimeException(Strings.ff("Unsupported data segment, flags 0x%X", var1));
      } else if (var1 == 0) {
         qt var7 = this.gp();
         int var9 = this.kS();
         int var10 = this.A;
         this.A += var9;
         return new Sv(0, var7, var10, var9);
      } else if (var1 == 1) {
         int var6 = this.kS();
         int var8 = this.A;
         this.A += var6;
         return new Sv(0, null, var8, var6);
      } else if (var1 == 2) {
         int var2 = this.kS();
         qt var3 = this.gp();
         int var4 = this.kS();
         int var5 = this.A;
         this.A += var4;
         return new Sv(var2, var3, var5, var4);
      } else {
         throw new RuntimeException();
      }
   }
}
