package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bfj extends bex {
   long rL;
   long eJ;
   long YN;
   List Rv = new ArrayList();
   List zx = new ArrayList();
   int ZT;
   int Ri;

   public bfj(byte[] var1, int var2, byte[] var3, int var4, bfj var5) {
      super(2130, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bfg.WX.q();
      this.Xo = bfg.Rc.q();
      if (var5 == null) {
         this.q(this.q(0));
         Assert.a(this.jq == 1);
         this.cC();
         Assert.a(this.If == this.jq - this.gP.RF);
      } else {
         this.mI = var5.mI;
         this.jq = var5.jq;
      }

      bfh var6 = new bfh(this);
      Object[] var10000 = new Object[]{this.oW.position()};

      for (int var7 = 0; var7 < this.rL; var7++) {
         int var8 = this.oW();
         var10000 = new Object[]{var8, bfg.q(var8), this.oW.position(), this.jq};
         bfh.oL var9 = var6.q(var8, true);
         var9.q();
         this.zx.add(var9);
         this.Rr.add(var8);
      }

      var10000 = new Object[]{this.oW.position()};
      var10000 = new Object[]{this.oW.position()};

      for (int var10 = 0; var10 < this.eJ; var10++) {
         int var12 = this.oW();
         var10000 = new Object[]{var12, bfg.q(var12), this.oW.position(), this.jq};
         bfh.oL var14 = var6.q(var12, false);
         var14.q();
         this.zx.add(var14);
         this.Rr.add(var12);
      }

      var10000 = new Object[]{this.oW.position()};

      for (bfh.oL var13 : this.zx) {
         var10000 = new Object[]{this.oW.position(), var13};
         var13.RF();
      }

      var10000 = new Object[]{this.oW.position()};
      this.Gf();
      var10000 = new Object[]{this.oW.position()};
      Assert.a(this.Dz == this.jq - this.gP.RF);
   }

   @Override
   protected boolean RF() {
      return this.lm == bfj.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bfg.MT.q(), "Null"));
      this.q(this.q(bfg.MT.q(), "Sentinel"));
      this.q(this.q(bfg.MT.q(), "TransitionSentinel"));
      this.q(this.q(bfg.Yw.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bfg.Yw.q(), "ZeroArray"));
      this.q(this.q(bfg.Rv.q(), "DynamicType"));
      this.q(this.q(bfg.Rv.q(), "VoidType"));
      this.q(this.q(bfg.eJ.q(), "EmptyTypeArguments"));
      this.q(this.q(bfg.Yp.q(), "True"));
      this.q(this.q(bfg.Yp.q(), "False"));
      this.q(this.q(bfg.Yw.q(), "ExtractorParameterTypes"));
      this.q(this.q(bfg.Yw.q(), "ExtractorParameterNames"));
      this.q(this.q(bfg.cC.q(), "EmptyContextScope"));
      this.q(this.q(bfg.Me.q(), "EmptyObjectPool"));
      this.q(this.q(bfg.xW.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bfg.PV.q(), "EmptyPCDescriptors"));
      this.q(this.q(bfg.KT.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bfg.Gf.q(), "EmptyExceptionHandlers"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bfg.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bfg.Yw.q(), "CachedICDataArrays")));
      this.q(this.q(bfg.Yw.q(), "CachedArray"));

      for (int var1 = bfg.Uv.q(); var1 < bfg.IN.q(); var1++) {
         if (var1 != bfg.TX.q() && var1 != bfg.If.q()) {
            this.q(this.q(bfg.Uv.q(), "ClassStub"));
         }
      }

      this.q(this.q(bfg.Uv.q(), "Dynamic"));
      this.q(this.q(bfg.Uv.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bfg.io.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bfg.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bfg var5 : bfg.values()) {
         var1.put(var5.q(), var5.toString());
      }

      return var1;
   }

   static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO;

      int q() {
         return this.ordinal();
      }

      static bfj.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : gO;
      }
   }
}
