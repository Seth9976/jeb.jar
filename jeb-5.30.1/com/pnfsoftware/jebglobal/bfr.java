package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bfr extends bex {
   long rL;
   long eJ;
   long YN;
   List Rv = new ArrayList();
   int zx;
   int ZT;

   public bfr(byte[] var1, int var2, byte[] var3, int var4, bfr var5) {
      super(2150, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bfo.cy.q();
      this.Xo = bfo.cO.q();
      if (var5 == null) {
         this.q(this.q(0));
         Assert.a(this.jq == 1);
         this.cC();
         Assert.a(this.If == this.jq - this.gP.RF);
      } else {
         this.mI = var5.mI;
         this.jq = var5.jq;
      }

      bfp var6 = new bfp(this);
      Object[] var10000 = new Object[]{this.oW.position()};

      for (int var7 = 0; var7 < this.rL; var7++) {
         long var8 = this.za();
         int var10 = (int)(var8 >>> 1);
         boolean var11 = (var8 & 1L) == 1L;
         var10000 = new Object[]{var11, var10, bfo.q(var10), this.oW.position(), this.jq};
         bfp.Bu var12 = var6.q(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bfo.q(var10)));
         }

         var12.q();
         this.Rv.add(var12);
         this.Rr.add(var10);
      }

      var10000 = new Object[]{this.oW.position()};

      for (bfp.Bu var14 : this.Rv) {
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
      return this.lm == bfr.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bfo.pe.q(), "Null"));
      this.q(this.q(bfo.pe.q(), "Sentinel"));
      this.q(this.q(bfo.pe.q(), "TransitionSentinel"));
      this.q(this.q(bfo.cY.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bfo.cY.q(), "ZeroArray"));
      this.q(this.q(bfo.GY.q(), "DynamicType"));
      this.q(this.q(bfo.GY.q(), "VoidType"));
      this.q(this.q(bfo.ZT.q(), "EmptyTypeArguments"));
      this.q(this.q(bfo.nq.q(), "True"));
      this.q(this.q(bfo.nq.q(), "False"));
      this.q(this.q(bfo.cY.q(), "ExtractorParameterTypes"));
      this.q(this.q(bfo.cY.q(), "ExtractorParameterNames"));
      this.q(this.q(bfo.wF.q(), "EmptyContextScope"));
      this.q(this.q(bfo.xW.q(), "EmptyObjectPool"));
      this.q(this.q(bfo.Ef.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bfo.KT.q(), "EmptyPCDescriptors"));
      this.q(this.q(bfo.cC.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bfo.sH.q(), "EmptyExceptionHandlers"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bfo.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bfo.cY.q(), "CachedICDataArrays")));
      this.q(this.q(bfo.cY.q(), "CachedArray"));

      for (int var1 = bfo.oW.q(); var1 < bfo.Rv.q(); var1++) {
         if (var1 != bfo.Bu.q() && var1 != bfo.ui.q()) {
            this.q(this.q(bfo.oW.q(), "ClassStub"));
         }
      }

      this.q(this.q(bfo.oW.q(), "Dynamic"));
      this.q(this.q(bfo.oW.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bfo.Hk.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bfo.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bfo var5 : bfo.values()) {
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

      static bfr.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
