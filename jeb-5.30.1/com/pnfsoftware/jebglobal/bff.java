package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bff extends bex {
   long rL;
   long eJ;
   long YN;
   List Rv = new ArrayList();
   List zx = new ArrayList();
   int ZT;
   int Ri;

   public bff(byte[] var1, int var2, byte[] var3, int var4, bff var5) {
      super(2120, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bfc.WX.q();
      this.Xo = bfc.Rc.q();
      if (var5 == null) {
         this.q(this.q(0));
         Assert.a(this.jq == 1);
         this.cC();
         Assert.a(this.If == this.jq - this.gP.RF);
      } else {
         this.mI = var5.mI;
         this.jq = var5.jq;
      }

      bfd var6 = new bfd(this);
      Object[] var10000 = new Object[]{this.oW.position()};

      for (int var7 = 0; var7 < this.rL; var7++) {
         int var8 = (int)this.za();
         var10000 = new Object[]{bfc.q(var8), this.oW.position(), this.jq};
         bfd.oL var9 = var6.q(var8, true);
         var9.q();
         this.zx.add(var9);
         this.Rr.add(var8);
      }

      var10000 = new Object[]{this.oW.position()};
      var10000 = new Object[]{this.oW.position()};

      for (int var10 = 0; var10 < this.eJ; var10++) {
         int var12 = (int)this.za();
         var10000 = new Object[]{bfc.q(var12), this.oW.position(), this.jq};
         bfd.oL var14 = var6.q(var12, false);
         var14.q();
         this.zx.add(var14);
         this.Rr.add(var12);
      }

      var10000 = new Object[]{this.oW.position()};

      for (bfd.oL var13 : this.zx) {
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
      return this.lm == bff.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bfc.MT.q(), "Null"));
      this.q(this.q(bfc.MT.q(), "Sentinel"));
      this.q(this.q(bfc.MT.q(), "TransitionSentinel"));
      this.q(this.q(bfc.Yw.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bfc.Yw.q(), "ZeroArray"));
      this.q(this.q(bfc.YN.q(), "DynamicType"));
      this.q(this.q(bfc.YN.q(), "VoidType"));
      this.q(this.q(bfc.rL.q(), "EmptyTypeArguments"));
      this.q(this.q(bfc.Tq.q(), "True"));
      this.q(this.q(bfc.Tq.q(), "False"));
      this.q(this.q(bfc.Yw.q(), "ExtractorParameterTypes"));
      this.q(this.q(bfc.Yw.q(), "ExtractorParameterNames"));
      this.q(this.q(bfc.Ef.q(), "EmptyContextScope"));
      this.q(this.q(bfc.Hk.q(), "EmptyObjectPool"));
      this.q(this.q(bfc.oQ.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bfc.Me.q(), "EmptyPCDescriptors"));
      this.q(this.q(bfc.xW.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bfc.KT.q(), "EmptyExceptionHandlers"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bfc.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bfc.Yw.q(), "CachedICDataArrays")));
      this.q(this.q(bfc.Yw.q(), "CachedArray"));

      for (int var1 = bfc.Uv.q(); var1 < bfc.Bu.q(); var1++) {
         if (var1 != bfc.ui.q() && var1 != bfc.wF.q()) {
            this.q(this.q(bfc.Uv.q(), "ClassStub"));
         }
      }

      this.q(this.q(bfc.Uv.q(), "Dynamic"));
      this.q(this.q(bfc.Uv.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bfc.LK.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bfc.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bfc var5 : bfc.values()) {
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

      static bff.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : gO;
      }
   }
}
