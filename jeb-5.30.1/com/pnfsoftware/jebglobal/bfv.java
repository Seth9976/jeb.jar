package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bfv extends bex {
   long rL;
   long eJ;
   long YN;
   long Rv;
   int[] zx;
   List ZT = new ArrayList();
   int Ri;
   int GY;

   public bfv(byte[] var1, int var2, byte[] var3, int var4, bfv var5) {
      super(2160, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.Rv = this.nf();
      this.EB = bfs.cy.q();
      this.Xo = bfs.cO.q();
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

      bft var6 = new bft(this);
      Object[] var17 = new Object[]{this.oW.position()};

      for (int var13 = 0; var13 < this.rL; var13++) {
         long var15 = this.za();
         int var10 = (int)(var15 >>> 1);
         boolean var11 = (var15 & 1L) == 1L;
         var17 = new Object[]{var11, var10, bfs.q(var10), this.oW.position(), this.jq};
         bft.Bu var12 = var6.q(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bfs.q(var10)));
         }

         var12.q();
         this.ZT.add(var12);
         this.Rr.add(var10);
      }

      var17 = new Object[]{this.oW.position()};

      for (bft.Bu var16 : this.ZT) {
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
      return this.lm == bfv.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bfs.pe.q(), "Null"));
      this.q(this.q(bfs.pe.q(), "Sentinel"));
      this.q(this.q(bfs.pe.q(), "TransitionSentinel"));
      this.q(this.q(bfs.cY.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bfs.cY.q(), "ZeroArray"));
      this.q(this.q(bfs.GY.q(), "DynamicType"));
      this.q(this.q(bfs.GY.q(), "VoidType"));
      this.q(this.q(bfs.ZT.q(), "EmptyTypeArguments"));
      this.q(this.q(bfs.nq.q(), "True"));
      this.q(this.q(bfs.nq.q(), "False"));
      this.q(this.q(bfs.cY.q(), "ExtractorParameterTypes"));
      this.q(this.q(bfs.cY.q(), "ExtractorParameterNames"));
      this.q(this.q(bfs.wF.q(), "EmptyContextScope"));
      this.q(this.q(bfs.xW.q(), "EmptyObjectPool"));
      this.q(this.q(bfs.Ef.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bfs.KT.q(), "EmptyPCDescriptors"));
      this.q(this.q(bfs.cC.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bfs.sH.q(), "EmptyExceptionHandlers"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bfs.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bfs.cY.q(), "CachedICDataArrays")));
      this.q(this.q(bfs.cY.q(), "CachedArray"));

      for (int var1 = bfs.oW.q(); var1 < bfs.Rv.q(); var1++) {
         if (var1 != bfs.Bu.q() && var1 != bfs.ui.q()) {
            this.q(this.q(bfs.oW.q(), "ClassStub"));
         }
      }

      this.q(this.q(bfs.oW.q(), "Dynamic"));
      this.q(this.q(bfs.oW.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bfs.Hk.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bfs.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bfs var5 : bfs.values()) {
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

      static bfv.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
