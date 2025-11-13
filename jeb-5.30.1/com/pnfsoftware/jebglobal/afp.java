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
public class afp implements ICConstantFactory {
   @SerId(1)
   private ahd q;
   @SerId(3)
   private Map RF;
   @SerId(4)
   private Map xK;
   @SerId(5)
   private Map Dw;
   @SerId(6)
   private Map Uv;
   @SerId(7)
   private Map oW;
   @SerId(8)
   private Map gO;
   @SerId(9)
   private Map nf;
   @SerId(10)
   private Map gP;
   @SerId(11)
   private Map za;
   @SerId(12)
   private Map lm;
   @SerId(13)
   private Map zz;

   public afp(ahd var1) {
      this.q = var1;
      this.RF = new HashMap();
      this.zz = new HashMap();
      this.xK = new HashMap();
      this.Dw = new HashMap();
      this.Uv = new HashMap();
      this.oW = new HashMap();
      this.gO = new HashMap();
      this.nf = new HashMap();
      this.gP = new HashMap();
      this.za = new HashMap();
      this.lm = new HashMap();
   }

   public afw q(String var1, long var2) {
      Couple var4 = new Couple(var1, var2);
      afw var5 = (afw)this.RF.get(var4);
      if (var5 == null) {
         var5 = new afw(var1, var2);
         this.RF.put(var4, var5);
      }

      return var5;
   }

   public afw q(String var1, long var2, Integer var4) {
      if (this.zz == null) {
         return this.q(var1, var2);
      } else {
         afw var5 = (afw)this.zz.get(var4);
         if (var5 == null) {
            var5 = new afw(var1, var2, var4);
            this.zz.put(var4, var5);
         }

         return var5;
      }
   }

   public afv q() {
      return this.q(0L);
   }

   public afv q(long var1) {
      afv var3 = (afv)this.xK.get(var1);
      if (var3 == null) {
         var3 = new afv(var1);
         this.xK.put(var1, var3);
      }

      return var3;
   }

   @Override
   public ICConstantInteger createInt(BigInteger var1, int var2) {
      if (var2 <= 32) {
         return this.RF(var1.intValue());
      } else {
         return (ICConstantInteger)(var2 <= 64 ? this.RF(var1.longValue()) : this.q(var1, var2));
      }
   }

   public afs q(int var1) {
      if (var1 >= 0 && var1 <= 255) {
         afs var2 = (afs)this.gP.get(var1);
         if (var2 == null) {
            var2 = new afs(this.q.getChar(), var1);
            this.gP.put(var1, var2);
         }

         return var2;
      } else {
         return this.RF(var1);
      }
   }

   public afs RF(int var1) {
      afs var2 = (afs)this.Dw.get(var1);
      if (var2 == null) {
         var2 = new afs(var1, false);
         this.Dw.put(var1, var2);
      }

      return var2;
   }

   public aft RF(long var1) {
      aft var3 = (aft)this.Uv.get(var1);
      if (var3 == null) {
         var3 = new aft(var1, false);
         this.Uv.put(var1, var3);
      }

      return var3;
   }

   public afs xK(int var1) {
      afs var2 = (afs)this.za.get(var1);
      if (var2 == null) {
         var2 = new afs(var1, true);
         this.za.put(var1, var2);
      }

      return var2;
   }

   public aft xK(long var1) {
      aft var3 = (aft)this.lm.get(var1);
      if (var3 == null) {
         var3 = new aft(var1, true);
         this.lm.put(var1, var3);
      }

      return var3;
   }

   public afu q(BigInteger var1, int var2) {
      Couple var3 = new Couple(var2, var1);
      afu var4 = (afu)this.oW.get(var3);
      if (var4 == null) {
         var4 = new afu(var2, var1);
         this.oW.put(var3, var4);
      }

      return var4;
   }

   public afq q(float var1) {
      afq var2 = (afq)this.gO.get(var1);
      if (var2 == null) {
         var2 = new afq(var1);
         this.gO.put(var1, var2);
      }

      return var2;
   }

   public afr q(double var1) {
      afr var3 = (afr)this.nf.get(var1);
      if (var3 == null) {
         var3 = new afr(var1);
         this.nf.put(var1, var3);
      }

      return var3;
   }
}
