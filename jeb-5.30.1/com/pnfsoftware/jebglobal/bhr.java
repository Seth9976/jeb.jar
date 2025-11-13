package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bhr extends bex {
   long rL;
   long eJ;
   long YN;
   int[] Rv;
   List zx = new ArrayList();
   int ZT;
   int Ri;

   public bhr(byte[] var1, int var2, byte[] var3, int var4, bhr var5) {
      super(3080, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bho.FG.q();
      this.Xo = bho.gl.q();
      if (this.eJ > 0L && this.YN > 0L) {
         this.gO.position((int)this.YN);
         this.gO.i32();
         this.gO.i32();
         int var10000 = (int)this.gO.i64() / 2;
         this.gO.i32();
         this.gO.i32();
         this.gO.i32();
         this.gO.i32();
         int var7 = (int)this.eJ * 2;
         this.Rv = new int[var7];

         for (int var8 = 0; var8 < var7; var8++) {
            this.Rv[var8] = this.gO.i32();
         }
      }

      if (var5 == null) {
         this.q(this.q(0));
         Assert.a(this.jq == 1);
         this.cC();
         Assert.a(this.If == this.jq - this.gP.RF);
      } else {
         this.mI = var5.mI;
         this.jq = var5.jq;
      }

      bhp var6 = new bhp(this);
      Object[] var16 = new Object[]{this.oW.position()};

      for (int var12 = 0; var12 < this.rL; var12++) {
         int var14 = this.lm();
         int var9 = var14 >> 12 & 1048575;
         boolean var10 = (var14 >> 1 & 1) != 0;
         var16 = new Object[]{var10, var9, bho.q(var9), this.oW.position(), this.jq};
         bhp.Bu var11 = var6.q(var9, var10, true);
         if (var11 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var9, bho.q(var9)));
         }

         var11.q();
         this.zx.add(var11);
         this.Rr.add(var9);
      }

      var16 = new Object[]{this.oW.position()};

      for (bhp.Bu var15 : this.zx) {
         var16 = new Object[]{this.oW.position(), var15};
         var15.RF();
      }

      var16 = new Object[]{this.oW.position()};
      this.Gf();
      var16 = new Object[]{this.oW.position()};
      Assert.a(this.Dz == this.jq - this.gP.RF);
   }

   @Override
   protected boolean RF() {
      return this.lm == bhr.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bho.JW.q(), "Null"));
      this.q(this.q(bho.JW.q(), "Sentinel"));
      this.q(this.q(bho.JW.q(), "OptimizedOut"));
      this.q(this.q(bho.of.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bho.of.q(), "ZeroArray"));
      this.q(this.q(bho.of.q(), "ZeroArray2"));
      this.q(this.q(bho.AB.q(), "DynamicType"));
      this.q(this.q(bho.AB.q(), "VoidType"));
      this.q(this.q(bho.GY.q(), "EmptyTypeArguments"));
      this.q(this.q(bho.Lj.q(), "True"));
      this.q(this.q(bho.Lj.q(), "False"));
      this.q(this.q(bho.of.q(), "ExtractorParameterTypes"));
      this.q(this.q(bho.of.q(), "ExtractorParameterNames"));
      this.q(this.q(bho.Dz.q(), "EmptyContextScope"));
      this.q(this.q(bho.Gf.q(), "EmptyObjectPool"));
      this.q(this.q(bho.sH.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bho.Ef.q(), "EmptyPCDescriptors"));
      this.q(this.q(bho.CE.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bho.wF.q(), "EmptyExceptionHandlers"));
      this.q(this.q(bho.wF.q(), "EmptyExceptionHandlersAsync"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bho.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bho.of.q(), "CachedICDataArrays")));

      for (int var1 = bho.oW.q(); var1 < bho.ZT.q(); var1++) {
         if (var1 != bho.rL.q() && var1 != bho.Rr.q()) {
            this.q(this.q(bho.oW.q(), "ClassStub"));
         }
      }

      this.q(this.q(bho.oW.q(), "Dynamic"));
      this.q(this.q(bho.oW.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bho.Me.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bho.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bho var5 : bho.values()) {
         var1.put(var5.q(), var5.toString());
      }

      return var1;
   }

   @Override
   public long Uv() {
      long var1 = 0L;

      for (int var3 = 0; var3 < 4; var3++) {
         byte var4 = this.oW.get();
         var1 = var4 + (var1 << 7);
         if (var4 < 0) {
            break;
         }
      }

      long var5 = var1 + 128L;
      this.q(var5);
      return var5;
   }

   @Override
   public int oW(int var1) {
      return var1 & 15;
   }

   @Override
   public int gO(int var1) {
      return var1 >> 4 & 1;
   }

   @Override
   public int LK(int var1) {
      return var1 >> 5 & 7;
   }

   static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW;

      int q() {
         return this.ordinal();
      }

      static bhr.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
