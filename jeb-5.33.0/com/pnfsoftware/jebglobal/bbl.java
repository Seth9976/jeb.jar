package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bbl extends bbd {
   long DQ;
   long ZN;
   long OB;
   List pF = new ArrayList();
   List Bc = new ArrayList();
   int OI;
   int Bf;

   public bbl(byte[] var1, int var2, byte[] var3, int var4, bbl var5) {
      super(2120, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bbi.qG.pC();
      this.UW = bbi.uw.pC();
      if (var5 == null) {
         this.pC(this.pC(0));
         Assert.a(this.mv == 1);
         this.Ab();
         Assert.a(this.Aj == this.mv - this.ys.A);
      } else {
         this.LM = var5.LM;
         this.mv = var5.mv;
      }

      bbj var6 = new bbj(this);
      Object[] var10000 = new Object[]{this.UT.position()};

      for (int var7 = 0; var7 < this.DQ; var7++) {
         int var8 = (int)this.ys();
         var10000 = new Object[]{bbi.pC(var8), this.UT.position(), this.mv};
         bbj.HE var9 = var6.pC(var8, true);
         var9.pC();
         this.Bc.add(var9);
         this.Cu.add(var8);
      }

      var10000 = new Object[]{this.UT.position()};
      var10000 = new Object[]{this.UT.position()};

      for (int var10 = 0; var10 < this.ZN; var10++) {
         int var12 = (int)this.ys();
         var10000 = new Object[]{bbi.pC(var12), this.UT.position(), this.mv};
         bbj.HE var14 = var6.pC(var12, false);
         var14.pC();
         this.Bc.add(var14);
         this.Cu.add(var12);
      }

      var10000 = new Object[]{this.UT.position()};

      for (bbj.HE var13 : this.Bc) {
         var10000 = new Object[]{this.UT.position(), var13};
         var13.A();
      }

      var10000 = new Object[]{this.UT.position()};
      this.eP();
      var10000 = new Object[]{this.UT.position()};
      Assert.a(this.EX == this.mv - this.ys.A);
   }

   @Override
   protected boolean pC() {
      return this.gp == bbl.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bbi.hM.pC(), "Null"));
      this.pC(this.pC(bbi.hM.pC(), "Sentinel"));
      this.pC(this.pC(bbi.hM.pC(), "TransitionSentinel"));
      this.pC(this.pC(bbi.Um.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bbi.Um.pC(), "ZeroArray"));
      this.pC(this.pC(bbi.pF.pC(), "DynamicType"));
      this.pC(this.pC(bbi.pF.pC(), "VoidType"));
      this.pC(this.pC(bbi.ZN.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bbi.Kq.pC(), "True"));
      this.pC(this.pC(bbi.Kq.pC(), "False"));
      this.pC(this.pC(bbi.Um.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bbi.Um.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bbi.Ek.pC(), "EmptyContextScope"));
      this.pC(this.pC(bbi.Sc.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bbi.UO.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bbi.ah.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bbi.Ab.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bbi.rl.pC(), "EmptyExceptionHandlers"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bbi.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bbi.Um.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bbi.Um.pC(), "CachedArray"));

      for (int var1 = bbi.UT.pC(); var1 < bbi.cX.pC(); var1++) {
         if (var1 != bbi.os.pC() && var1 != bbi.Aj.pC()) {
            this.pC(this.pC(bbi.UT.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bbi.UT.pC(), "Dynamic"));
      this.pC(this.pC(bbi.UT.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bbi.vP.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bbi.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bbi var5 : bbi.values()) {
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
      E,
      sY;

      int pC() {
         return this.ordinal();
      }
   }
}
