package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bfb extends bex {
   long rL;
   long eJ;
   List YN = new ArrayList();
   int Rv;
   int zx;

   public bfb(byte[] var1, int var2, byte[] var3, int var4, bfb var5) {
      super(2100, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.EB = bey.Cl.q();
      this.Xo = bey.pe.q();
      if (var5 == null) {
         this.q(this.q(0));
         Assert.a(this.jq == 1);
         this.cC();
         Assert.a(this.If == this.jq - this.gP.RF);
      } else {
         this.mI = var5.mI;
         this.jq = var5.jq;
      }

      bez var6 = new bez(this);
      Object[] var10000 = new Object[]{this.oW.position()};

      for (int var7 = 0; var7 < this.rL; var7++) {
         int var8 = (int)this.za();
         var10000 = new Object[]{bey.q(var8), this.oW.position(), this.jq};
         bez.PY var9 = var6.q(var8);
         var9.q();
         this.YN.add(var9);
         this.Rr.add(var8);
      }

      var10000 = new Object[]{this.oW.position()};

      for (bez.PY var11 : this.YN) {
         var10000 = new Object[]{this.oW.position(), var11};
         var11.RF();
      }

      var10000 = new Object[]{this.oW.position()};
      this.Gf();
      var10000 = new Object[]{this.oW.position()};
      Assert.a(this.Dz == this.jq - this.gP.RF);
   }

   @Override
   protected boolean RF() {
      return this.lm == bfb.eo.xK.q();
   }

   void cC() {
      this.q(this.q(bey.CK.q(), "Null"));
      this.q(this.q(bey.CK.q(), "Sentinel"));
      this.q(this.q(bey.CK.q(), "TransitionSentinel"));
      this.q(this.q(bey.YA.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bey.YA.q(), "ZeroArray"));
      this.q(this.q(bey.Ri.q(), "DynamicType"));
      this.q(this.q(bey.Ri.q(), "VoidType"));
      this.q(this.q(bey.zx.q(), "EmptyTypeArguments"));
      this.q(this.q(bey.nY.q(), "True"));
      this.q(this.q(bey.nY.q(), "False"));
      this.q(this.q(bey.YA.q(), "ExtractorParameterTypes"));
      this.q(this.q(bey.YA.q(), "ExtractorParameterNames"));
      this.q(this.q(bey.CE.q(), "EmptyContextScope"));
      this.q(this.q(bey.xW.q(), "EmptyPCDescriptors"));
      this.q(this.q(bey.Ef.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bey.cC.q(), "EmptyExceptionHandlers"));
      this.q(this.q(bey.Hk.q(), "ImplicitGetterBytecode"));
      this.q(this.q(bey.Hk.q(), "ImplicitSetterBytecode"));
      this.q(this.q(bey.Hk.q(), "ImplicitStaticGetterBytecode"));
      this.q(this.q(bey.Hk.q(), "MethodExtractorBytecode"));
      this.q(this.q(bey.Hk.q(), "InvokeClosureBytecode"));
      this.q(this.q(bey.Hk.q(), "InvokeFieldBytecode"));
      this.q(this.q(bey.Hk.q(), "NsmDispatcherBytecode"));
      this.q(this.q(bey.Hk.q(), "DynamicInvocationForwarderBytecode"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bey.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bey.YA.q(), "CachedICDataArrays")));
      this.q(this.q(bey.YA.q(), "CachedArray"));

      for (int var1 = bey.Uv.q(); var1 < bey.YN.q(); var1++) {
         if (var1 != bey.Xo.q() && var1 != bey.jq.q()) {
            this.q(this.q(bey.Uv.q(), "ClassStub"));
         }
      }

      this.q(this.q(bey.Uv.q(), "Dynamic"));
      this.q(this.q(bey.Uv.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bey.qa.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bey.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bey var5 : bey.values()) {
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

      static bfb.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
