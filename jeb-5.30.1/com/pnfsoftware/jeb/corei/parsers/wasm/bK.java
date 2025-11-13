package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.coq;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

class bK {
   private static final ILogger xK = GlobalLog.getLogger(bK.class);
   byte[] q;
   int RF;

   bK(byte[] var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("@%Xh", this.RF);
   }

   public int q() {
      return this.q.length - this.RF;
   }

   int RF() {
      int var1 = this.q[this.RF] & 255;
      this.RF++;
      return var1;
   }

   int xK() {
      kY.eo var1 = kY.gP(this.q, this.RF);
      this.RF = this.RF + var1.RF;
      return (int)var1.q;
   }

   int Dw() {
      int var1 = this.RF();
      if (!Xa.oW(var1)) {
         throw new RuntimeException(Strings.ff("Expected a value type, got 0x%X", var1));
      } else {
         return var1;
      }
   }

   int Uv() {
      int var1 = this.RF();
      if (var1 != 64 && !Xa.oW(var1)) {
         throw new RuntimeException("Expected the pseudo empty block_blocka or a value type");
      } else {
         return var1;
      }
   }

   int oW() {
      int var1 = this.RF();
      if (!Xa.Uv(var1)) {
         throw new RuntimeException(Strings.ff("Expected a referece type, got 0x%X", var1));
      } else {
         return var1;
      }
   }

   iZ gO() {
      int var1 = this.RF();
      if (var1 != 96) {
         throw new RuntimeException(Strings.ff("Expected the function byte (0x60), got 0x%X", var1));
      } else {
         int var2 = this.xK();
         int[] var3 = new int[var2];

         for (int var4 = 0; var4 < var2; var4++) {
            int var5 = this.Dw();
            var3[var4] = var5;
         }

         int var8 = this.xK();
         int[] var9 = new int[var8];

         for (int var6 = 0; var6 < var8; var6++) {
            int var7 = this.Dw();
            var9[var6] = var7;
         }

         if (var8 != 0 && var8 != 1) {
            throw new coq("Multiple return types are not supported");
         } else {
            Integer var10 = var8 == 0 ? null : var9[0];
            return new iZ(var10, var3);
         }
      }
   }

   String nf() {
      int var1 = this.xK();
      String var2 = new String(this.q, this.RF, var1, Charset.forName("UTF-8"));
      this.RF += var1;
      return var2;
   }

   qV q(List var1) {
      String var2 = this.nf();
      String var3 = this.nf();
      int var4 = this.gP();
      switch (var4) {
         case 0:
            int var8 = this.xK();
            return new vn(var2, var3, (iZ)var1.get(var8));
         case 1:
            Uz var7 = this.JY();
            return new oL(var2, var3, var7);
         case 2:
            Bu var6 = this.HF();
            return new vb(var2, var3, var6);
         case 3:
            EE var5 = this.zz();
            return new PY(var2, var3, var5);
         default:
            throw new RuntimeException(Strings.ff("Unsupported import descriptor: 0x%X", var4));
      }
   }

   int gP() {
      int var1 = this.RF();
      if (var1 >= 0 && var1 <= 3) {
         return var1;
      } else {
         throw new RuntimeException("Unsupported external_kind value: " + var1);
      }
   }

   tl za() {
      int var1 = this.RF();
      int var2 = this.xK();
      Integer var3 = null;
      if ((var1 & 1) != 0) {
         var3 = this.xK();
      }

      boolean var4 = false;
      if ((var1 & 2) != 0) {
         var4 = true;
      }

      return new tl(var2, var3, var4);
   }

   List q(int var1, boolean var2) {
      vX var3 = new vX(null);

      try {
         ArrayList var4 = new ArrayList();

         while (this.RF < var1) {
            SG var5 = (SG)var3.parseAt(this.q, this.RF, var1);
            this.RF = this.RF + var5.getSize();
            var4.add(var5);
            if (var2 && var5.Dw()) {
               break;
            }
         }

         if (this.RF > var1) {
            throw new RuntimeException();
         } else if (var4.isEmpty()) {
            throw new RuntimeException();
         } else if (!((SG)var4.get(var4.size() - 1)).Dw()) {
            throw new RuntimeException();
         } else {
            return var4;
         }
      } catch (ProcessorException var6) {
         throw new RuntimeException(var6);
      }
   }

   Vj lm() {
      List var1 = this.q(this.q.length, true);
      if (!var1.isEmpty() && ((SG)var1.get(var1.size() - 1)).Dw()) {
         var1.remove(var1.size() - 1);
         return new Vj(var1);
      } else {
         throw new NullPointerException("Unsupported expression format, must end with END (0x0B)");
      }
   }

   EE zz() {
      int var1 = this.Dw();
      boolean var2 = (this.RF() & 1) == 1;
      return new EE(var1, var2);
   }

   Uz JY() {
      int var1 = this.oW();
      tl var2 = this.za();
      return new Uz(var1, var2);
   }

   Bu HF() {
      return new Bu(this.za());
   }

   tw LK() {
      EE var1 = this.zz();
      Vj var2 = this.lm();
      return new tw(var1, var2);
   }

   ej io() {
      String var1 = this.nf();
      int var2 = this.gP();
      int var3 = this.xK();
      return new ej(var1, var2, var3);
   }

   nI qa() {
      int var1 = this.RF();
      if (var1 > 7) {
         throw new RuntimeException(Strings.ff("Unsupported element segment with header 0x%X", var1));
      } else if (var1 != 0) {
         throw new coq("Unsupported element segment");
      } else {
         byte var2 = 0;
         Vj var3 = this.lm();
         int var4 = this.xK();
         ArrayList var5 = new ArrayList(var4);

         for (int var6 = 0; var6 < var4; var6++) {
            int var7 = this.xK();
            var5.add(var7);
         }

         return new nI(var2, var3, var5);
      }
   }

   oM q(vX var1, boolean var2) {
      int var3 = this.xK();
      int var4 = this.RF;
      int var5 = this.xK();
      ArrayList var6 = new ArrayList();

      for (int var7 = 0; var7 < var5; var7++) {
         int var8 = this.xK();
         int var9 = this.Dw();

         while (var8-- > 0) {
            var6.add(var9);
         }
      }

      int var11 = this.RF;
      int var12 = var4 + var3;

      try {
         List var13 = var1.q(var11, var12, var2);
         this.RF = var12;
         return new oM(var6, var11, var12, var13);
      } catch (ProcessorException var10) {
         xK.error("wasm parsing error: method body will be empty: %s", var10.getMessage());
         JebCoreService.notifySilentExceptionToClient(var10);
         this.RF = var12;
         return new oM(var6, var11, var12, new ArrayList());
      }
   }

   CU Hk() {
      int var1 = this.RF();
      if (var1 > 2) {
         throw new RuntimeException(Strings.ff("Unsupported data segment, flags 0x%X", var1));
      } else if (var1 == 0) {
         Vj var7 = this.lm();
         int var9 = this.xK();
         int var10 = this.RF;
         this.RF += var9;
         return new CU(0, var7, var10, var9);
      } else if (var1 == 1) {
         int var6 = this.xK();
         int var8 = this.RF;
         this.RF += var6;
         return new CU(0, null, var8, var6);
      } else if (var1 == 2) {
         int var2 = this.xK();
         Vj var3 = this.lm();
         int var4 = this.xK();
         int var5 = this.RF;
         this.RF += var4;
         return new CU(var2, var3, var5, var4);
      } else {
         throw new RuntimeException();
      }
   }
}
