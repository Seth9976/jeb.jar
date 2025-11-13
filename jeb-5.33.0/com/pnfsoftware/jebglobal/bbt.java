package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bbt extends bbd {
   long DQ;
   long ZN;
   long OB;
   List pF = new ArrayList();
   int Bc;
   int OI;

   public bbt(byte[] var1, int var2, byte[] var3, int var4, bbt var5) {
      super(2140, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bbq.ve.pC();
      this.UW = bbq.OA.pC();
      if (var5 == null) {
         this.pC(this.pC(0));
         Assert.a(this.mv == 1);
         this.Ab();
         Assert.a(this.Aj == this.mv - this.ys.A);
      } else {
         this.LM = var5.LM;
         this.mv = var5.mv;
      }

      bbr var6 = new bbr(this);
      Object[] var10000 = new Object[]{this.UT.position()};

      for (int var7 = 0; var7 < this.DQ; var7++) {
         long var8 = this.ys();
         int var10 = (int)(var8 >>> 1);
         boolean var11 = (var8 & 1L) == 1L;
         var10000 = new Object[]{var11, var10, bbq.pC(var10), this.UT.position(), this.mv};
         bbr.HE var12 = var6.pC(var10, var11);
         var12.pC();
         this.pF.add(var12);
         this.Cu.add(var10);
      }

      var10000 = new Object[]{this.UT.position()};

      for (bbr.HE var14 : this.pF) {
         var10000 = new Object[]{this.UT.position(), var14};
         var14.A();
      }

      var10000 = new Object[]{this.UT.position()};
      this.eP();
      var10000 = new Object[]{this.UT.position()};
      Assert.a(this.EX == this.mv - this.ys.A);
   }

   @Override
   protected boolean pC() {
      return this.gp == bbt.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bbq.x.pC(), "Null"));
      this.pC(this.pC(bbq.x.pC(), "Sentinel"));
      this.pC(this.pC(bbq.x.pC(), "TransitionSentinel"));
      this.pC(this.pC(bbq.Gm.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bbq.Gm.pC(), "ZeroArray"));
      this.pC(this.pC(bbq.Pe.pC(), "DynamicType"));
      this.pC(this.pC(bbq.Pe.pC(), "VoidType"));
      this.pC(this.pC(bbq.OI.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bbq.pg.pC(), "True"));
      this.pC(this.pC(bbq.pg.pC(), "False"));
      this.pC(this.pC(bbq.Gm.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bbq.Gm.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bbq.FE.pC(), "EmptyContextScope"));
      this.pC(this.pC(bbq.UO.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bbq.z.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bbq.Ab.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bbq.Ek.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bbq.hK.pC(), "EmptyExceptionHandlers"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bbq.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bbq.Gm.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bbq.Gm.pC(), "CachedArray"));

      for (int var1 = bbq.UT.pC(); var1 < bbq.pF.pC(); var1++) {
         if (var1 != bbq.PR.pC() && var1 != bbq.sO.pC()) {
            this.pC(this.pC(bbq.UT.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bbq.UT.pC(), "Dynamic"));
      this.pC(this.pC(bbq.UT.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bbq.ED.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bbq.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bbq var5 : bbq.values()) {
         var1.put(var5.pC(), var5.toString());
      }

      return var1;
   }

   static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E;

      int pC() {
         return this.ordinal();
      }
   }
}
