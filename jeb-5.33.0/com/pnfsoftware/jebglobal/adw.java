package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Ser
public class adw implements ICConstantFactory {
   @SerId(1)
   private afk pC;
   @SerId(3)
   private Map A;
   @SerId(4)
   private Map kS;
   @SerId(5)
   private Map wS;
   @SerId(6)
   private Map UT;
   @SerId(7)
   private Map E;
   @SerId(8)
   private Map sY;
   @SerId(9)
   private Map ys;
   @SerId(10)
   private Map ld;
   @SerId(11)
   private Map gp;
   @SerId(12)
   private Map oT;
   @SerId(13)
   private Map fI;

   public adw(afk var1) {
      this.pC = var1;
      this.A = new HashMap();
      this.fI = new HashMap();
      this.kS = new HashMap();
      this.wS = new HashMap();
      this.UT = new HashMap();
      this.E = new HashMap();
      this.sY = new HashMap();
      this.ys = new HashMap();
      this.ld = new HashMap();
      this.gp = new HashMap();
      this.oT = new HashMap();
   }

   public aed pC(String var1, long var2) {
      Couple var4 = new Couple(var1, var2);
      aed var5 = (aed)this.A.get(var4);
      if (var5 == null) {
         var5 = new aed(var1, var2);
         this.A.put(var4, var5);
      }

      return var5;
   }

   public aed pC(String var1, long var2, Integer var4) {
      if (this.fI == null) {
         return this.pC(var1, var2);
      } else {
         aed var5 = (aed)this.fI.get(var4);
         if (var5 == null) {
            var5 = new aed(var1, var2, var4);
            this.fI.put(var4, var5);
         }

         return var5;
      }
   }

   public aec pC() {
      return this.pC(0L);
   }

   public aec pC(long var1) {
      aec var3 = (aec)this.kS.get(var1);
      if (var3 == null) {
         var3 = new aec(var1);
         this.kS.put(var1, var3);
      }

      return var3;
   }

   @Override
   public ICConstantInteger createInt(BigInteger var1, int var2) {
      if (var2 <= 32) {
         return this.A(var1.intValue());
      } else {
         return (ICConstantInteger)(var2 <= 64 ? this.A(var1.longValue()) : this.pC(var1, var2));
      }
   }

   public adz pC(int var1) {
      if (var1 >= 0 && var1 <= 255) {
         adz var2 = (adz)this.ld.get(var1);
         if (var2 == null) {
            var2 = new adz(this.pC.getChar(), var1);
            this.ld.put(var1, var2);
         }

         return var2;
      } else {
         return this.A(var1);
      }
   }

   public adz A(int var1) {
      adz var2 = (adz)this.wS.get(var1);
      if (var2 == null) {
         var2 = new adz(var1, false);
         this.wS.put(var1, var2);
      }

      return var2;
   }

   public aea A(long var1) {
      aea var3 = (aea)this.UT.get(var1);
      if (var3 == null) {
         var3 = new aea(var1, false);
         this.UT.put(var1, var3);
      }

      return var3;
   }

   public adz kS(int var1) {
      adz var2 = (adz)this.gp.get(var1);
      if (var2 == null) {
         var2 = new adz(var1, true);
         this.gp.put(var1, var2);
      }

      return var2;
   }

   public aea kS(long var1) {
      aea var3 = (aea)this.oT.get(var1);
      if (var3 == null) {
         var3 = new aea(var1, true);
         this.oT.put(var1, var3);
      }

      return var3;
   }

   public aeb pC(BigInteger var1, int var2) {
      Couple var3 = new Couple(var2, var1);
      aeb var4 = (aeb)this.E.get(var3);
      if (var4 == null) {
         var4 = new aeb(var2, var1);
         this.E.put(var3, var4);
      }

      return var4;
   }

   public adx pC(float var1) {
      adx var2 = (adx)this.sY.get(var1);
      if (var2 == null) {
         var2 = new adx(var1);
         this.sY.put(var1, var2);
      }

      return var2;
   }

   public ady pC(double var1) {
      ady var3 = (ady)this.ys.get(var1);
      if (var3 == null) {
         var3 = new ady(var1);
         this.ys.put(var1, var3);
      }

      return var3;
   }
}
