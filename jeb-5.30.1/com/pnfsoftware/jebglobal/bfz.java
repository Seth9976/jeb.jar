package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bfz extends bex {
   long rL;
   long eJ;
   long YN;
   long Rv;
   int[] zx;
   List ZT = new ArrayList();
   int Ri;
   int GY;

   public bfz(byte[] var1, int var2, byte[] var3, int var4, bfz var5) {
      super(2170, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.Rv = this.nf();
      this.EB = bfw.kv.q();
      this.Xo = bfw.CK.q();
      if (this.YN > 0L && this.Rv > 0L) {
         this.gO.position((int)this.Rv);
         this.gO.i32();
         this.gO.i32();
         int var10000 = (int)this.gO.i64() / 2;
         this.gO.i32();
         this.gO.i32();
         this.gO.i32();
         this.gO.i32();
         int var7 = (int)this.YN * 2;
         this.zx = new int[var7];

         for (int var8 = 0; var8 < var7; var8++) {
            this.zx[var8] = this.gO.i32();
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

      bfx var6 = new bfx(this);
      Object[] var17 = new Object[]{this.oW.position()};

      for (int var13 = 0; var13 < this.rL; var13++) {
         long var15 = this.za();
         int var10 = (int)(var15 >>> 1);
         boolean var11 = (var15 & 1L) == 1L;
         var17 = new Object[]{var11, var10, bfw.q(var10), this.oW.position(), this.jq};
         bfx.Bu var12 = var6.q(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bfw.q(var10)));
         }

         var12.q();
         this.ZT.add(var12);
         this.Rr.add(var10);
      }

      var17 = new Object[]{this.oW.position()};

      for (bfx.Bu var16 : this.ZT) {
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
      return this.lm == bfz.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bfw.zm.q(), "Null"));
      this.q(this.q(bfw.zm.q(), "Sentinel"));
      this.q(this.q(bfw.zm.q(), "TransitionSentinel"));
      this.q(this.q(bfw.Qu.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bfw.Qu.q(), "ZeroArray"));
      this.q(this.q(bfw.GY.q(), "DynamicType"));
      this.q(this.q(bfw.GY.q(), "VoidType"));
      this.q(this.q(bfw.ZT.q(), "EmptyTypeArguments"));
      this.q(this.q(bfw.ZA.q(), "True"));
      this.q(this.q(bfw.ZA.q(), "False"));
      this.q(this.q(bfw.Qu.q(), "ExtractorParameterTypes"));
      this.q(this.q(bfw.Qu.q(), "ExtractorParameterNames"));
      this.q(this.q(bfw.wF.q(), "EmptyContextScope"));
      this.q(this.q(bfw.xW.q(), "EmptyObjectPool"));
      this.q(this.q(bfw.Ef.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bfw.KT.q(), "EmptyPCDescriptors"));
      this.q(this.q(bfw.cC.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bfw.sH.q(), "EmptyExceptionHandlers"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bfw.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bfw.Qu.q(), "CachedICDataArrays")));
      this.q(this.q(bfw.Qu.q(), "CachedArray"));

      for (int var1 = bfw.oW.q(); var1 < bfw.Rv.q(); var1++) {
         if (var1 != bfw.Bu.q() && var1 != bfw.ui.q()) {
            this.q(this.q(bfw.oW.q(), "ClassStub"));
         }
      }

      this.q(this.q(bfw.oW.q(), "Dynamic"));
      this.q(this.q(bfw.oW.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bfw.Hk.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bfw.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bfw var5 : bfw.values()) {
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

      static bfz.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
