package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bfn extends bex {
   long rL;
   long eJ;
   long YN;
   List Rv = new ArrayList();
   int zx;
   int ZT;

   public bfn(byte[] var1, int var2, byte[] var3, int var4, bfn var5) {
      super(2140, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bfk.KF.q();
      this.Xo = bfk.LS.q();
      if (var5 == null) {
         this.q(this.q(0));
         Assert.a(this.jq == 1);
         this.cC();
         Assert.a(this.If == this.jq - this.gP.RF);
      } else {
         this.mI = var5.mI;
         this.jq = var5.jq;
      }

      bfl var6 = new bfl(this);
      Object[] var10000 = new Object[]{this.oW.position()};

      for (int var7 = 0; var7 < this.rL; var7++) {
         long var8 = this.za();
         int var10 = (int)(var8 >>> 1);
         boolean var11 = (var8 & 1L) == 1L;
         var10000 = new Object[]{var11, var10, bfk.q(var10), this.oW.position(), this.jq};
         bfl.oL var12 = var6.q(var10, var11);
         var12.q();
         this.Rv.add(var12);
         this.Rr.add(var10);
      }

      var10000 = new Object[]{this.oW.position()};

      for (bfl.oL var14 : this.Rv) {
         var10000 = new Object[]{this.oW.position(), var14};
         var14.RF();
      }

      var10000 = new Object[]{this.oW.position()};
      this.Gf();
      var10000 = new Object[]{this.oW.position()};
      Assert.a(this.Dz == this.jq - this.gP.RF);
   }

   @Override
   protected boolean RF() {
      return this.lm == bfn.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bfk.cO.q(), "Null"));
      this.q(this.q(bfk.cO.q(), "Sentinel"));
      this.q(this.q(bfk.cO.q(), "TransitionSentinel"));
      this.q(this.q(bfk.fw.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bfk.fw.q(), "ZeroArray"));
      this.q(this.q(bfk.Ri.q(), "DynamicType"));
      this.q(this.q(bfk.Ri.q(), "VoidType"));
      this.q(this.q(bfk.zx.q(), "EmptyTypeArguments"));
      this.q(this.q(bfk.lF.q(), "True"));
      this.q(this.q(bfk.lF.q(), "False"));
      this.q(this.q(bfk.fw.q(), "ExtractorParameterTypes"));
      this.q(this.q(bfk.fw.q(), "ExtractorParameterNames"));
      this.q(this.q(bfk.CE.q(), "EmptyContextScope"));
      this.q(this.q(bfk.oQ.q(), "EmptyObjectPool"));
      this.q(this.q(bfk.Gf.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bfk.xW.q(), "EmptyPCDescriptors"));
      this.q(this.q(bfk.Ef.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bfk.cC.q(), "EmptyExceptionHandlers"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bfk.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bfk.fw.q(), "CachedICDataArrays")));
      this.q(this.q(bfk.fw.q(), "CachedArray"));

      for (int var1 = bfk.Uv.q(); var1 < bfk.YN.q(); var1++) {
         if (var1 != bfk.Xo.q() && var1 != bfk.jq.q()) {
            this.q(this.q(bfk.Uv.q(), "ClassStub"));
         }
      }

      this.q(this.q(bfk.Uv.q(), "Dynamic"));
      this.q(this.q(bfk.Uv.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bfk.qa.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bfk.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bfk var5 : bfk.values()) {
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
      oW;

      int q() {
         return this.ordinal();
      }

      static bfn.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
