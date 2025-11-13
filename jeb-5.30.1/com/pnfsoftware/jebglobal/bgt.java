package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bgt extends bex {
   long rL;
   long eJ;
   long YN;
   int[] Rv;
   List zx = new ArrayList();
   int ZT;
   int Ri;

   public bgt(byte[] var1, int var2, byte[] var3, int var4, bgt var5) {
      super(3020, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bgq.Al.q();
      this.Xo = bgq.tX.q();
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

      bgr var6 = new bgr(this);
      Object[] var17 = new Object[]{this.oW.position()};

      for (int var13 = 0; var13 < this.rL; var13++) {
         long var15 = this.za();
         int var10 = (int)(var15 >>> 1);
         boolean var11 = (var15 & 1L) == 1L;
         var17 = new Object[]{var11, var10, bgq.q(var10), this.oW.position(), this.jq};
         bgr.Bu var12 = var6.q(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bgq.q(var10)));
         }

         var12.q();
         this.zx.add(var12);
         this.Rr.add(var10);
      }

      var17 = new Object[]{this.oW.position()};

      for (bgr.Bu var16 : this.zx) {
         var17 = new Object[]{this.oW.position(), var16};
         var16.RF();
      }

      var17 = new Object[]{this.oW.position()};
      this.Gf();
      var17 = new Object[]{this.oW.position()};
      Assert.a(this.Dz == this.jq - this.gP.RF);
   }

   @Override
   protected boolean RF() {
      return this.lm == bgt.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bgq.Ub.q(), "Null"));
      this.q(this.q(bgq.Ub.q(), "Sentinel"));
      this.q(this.q(bgq.Ub.q(), "TransitionSentinel"));
      this.q(this.q(bgq.Ub.q(), "OptimizedOut"));
      this.q(this.q(bgq.vC.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bgq.vC.q(), "ZeroArray"));
      this.q(this.q(bgq.vC.q(), "ZeroArray2"));
      this.q(this.q(bgq.Wx.q(), "DynamicType"));
      this.q(this.q(bgq.Wx.q(), "VoidType"));
      this.q(this.q(bgq.Ri.q(), "EmptyTypeArguments"));
      this.q(this.q(bgq.Ov.q(), "True"));
      this.q(this.q(bgq.Ov.q(), "False"));
      this.q(this.q(bgq.vC.q(), "ExtractorParameterTypes"));
      this.q(this.q(bgq.vC.q(), "ExtractorParameterNames"));
      this.q(this.q(bgq.If.q(), "EmptyContextScope"));
      this.q(this.q(bgq.KT.q(), "EmptyObjectPool"));
      this.q(this.q(bgq.cC.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bgq.Gf.q(), "EmptyPCDescriptors"));
      this.q(this.q(bgq.sH.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bgq.CE.q(), "EmptyExceptionHandlers"));
      this.q(this.q(bgq.CE.q(), "EmptyExceptionHandlersAsync"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bgq.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bgq.vC.q(), "CachedICDataArrays")));

      for (int var1 = bgq.oW.q(); var1 < bgq.zx.q(); var1++) {
         if (var1 != bgq.IN.q() && var1 != bgq.TX.q()) {
            this.q(this.q(bgq.oW.q(), "ClassStub"));
         }
      }

      this.q(this.q(bgq.oW.q(), "Dynamic"));
      this.q(this.q(bgq.oW.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bgq.Me.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bgq.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bgq var5 : bgq.values()) {
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

      static bgt.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
