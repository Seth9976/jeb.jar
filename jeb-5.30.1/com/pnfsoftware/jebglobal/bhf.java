package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bhf extends bex {
   long rL;
   long eJ;
   long YN;
   int[] Rv;
   List zx = new ArrayList();
   int ZT;
   int Ri;

   public bhf(byte[] var1, int var2, byte[] var3, int var4, bhf var5) {
      super(3050, var1, var2, var3, var4, var5);
      this.If = this.nf();
      this.Dz = this.nf();
      this.rL = this.nf();
      this.eJ = this.nf();
      this.YN = this.nf();
      this.EB = bhc.oS.q();
      this.Xo = bhc.o.q();
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

      bhd var6 = new bhd(this);
      Object[] var16 = new Object[]{this.oW.position()};

      for (int var12 = 0; var12 < this.rL; var12++) {
         int var14 = this.lm();
         int var9 = var14 >> 12 & 1048575;
         boolean var10 = (var14 >> 1 & 1) != 0;
         var16 = new Object[]{var10, var9, bhc.q(var9), this.oW.position(), this.jq};
         bhd.Bu var11 = var6.q(var9, var10, true);
         if (var11 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var9, bhc.q(var9)));
         }

         var11.q();
         this.zx.add(var11);
         this.Rr.add(var9);
      }

      var16 = new Object[]{this.oW.position()};

      for (bhd.Bu var15 : this.zx) {
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
      return this.lm == bhf.eo.Dw.q();
   }

   void cC() {
      this.q(this.q(bhc.Qt.q(), "Null"));
      this.q(this.q(bhc.Qt.q(), "Sentinel"));
      this.q(this.q(bhc.Qt.q(), "OptimizedOut"));
      this.q(this.q(bhc.vC.q(), "EmptyArray", new Object[0]));
      this.q(this.q(bhc.vC.q(), "ZeroArray"));
      this.q(this.q(bhc.vC.q(), "ZeroArray2"));
      this.q(this.q(bhc.Wx.q(), "DynamicType"));
      this.q(this.q(bhc.Wx.q(), "VoidType"));
      this.q(this.q(bhc.Ri.q(), "EmptyTypeArguments"));
      this.q(this.q(bhc.Ov.q(), "True"));
      this.q(this.q(bhc.Ov.q(), "False"));
      this.q(this.q(bhc.vC.q(), "ExtractorParameterTypes"));
      this.q(this.q(bhc.vC.q(), "ExtractorParameterNames"));
      this.q(this.q(bhc.If.q(), "EmptyContextScope"));
      this.q(this.q(bhc.KT.q(), "EmptyObjectPool"));
      this.q(this.q(bhc.cC.q(), "EmptyCompressedStackMaps"));
      this.q(this.q(bhc.Gf.q(), "EmptyPCDescriptors"));
      this.q(this.q(bhc.sH.q(), "EmptyLocalVarDescriptors"));
      this.q(this.q(bhc.CE.q(), "EmptyExceptionHandlers"));
      this.q(this.q(bhc.CE.q(), "EmptyExceptionHandlersAsync"));
      Longs.range(this.gP.io).forEach(var1x -> this.q(this.q(bhc.q.q(), "CachedArgsDescriptors")));
      Longs.range(this.gP.qa).forEach(var1x -> this.q(this.q(bhc.vC.q(), "CachedICDataArrays")));

      for (int var1 = bhc.oW.q(); var1 < bhc.zx.q(); var1++) {
         if (var1 != bhc.IN.q() && var1 != bhc.TX.q()) {
            this.q(this.q(bhc.oW.q(), "ClassStub"));
         }
      }

      this.q(this.q(bhc.oW.q(), "Dynamic"));
      this.q(this.q(bhc.oW.q(), "Void"));
      if (!this.KT) {
         Longs.range(this.gP.Hk).forEach(var1x -> this.q(this.q(bhc.Me.q(), "StubCode")));
      }
   }

   @Override
   public String nf(int var1) {
      return bhc.q(var1).toString();
   }

   @Override
   public Map KT() {
      TreeMap var1 = new TreeMap();

      for (bhc var5 : bhc.values()) {
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

      static bhf.eo q(long var0) {
         return var0 >= 0L && var0 < values().length ? values()[(int)var0] : oW;
      }
   }
}
