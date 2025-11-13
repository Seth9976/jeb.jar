package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bbp extends bbd {
   long DQ;
   long ZN;
   long OB;
   List pF = new ArrayList();
   List Bc = new ArrayList();
   int OI;
   int Bf;

   public bbp(byte[] var1, int var2, byte[] var3, int var4, bbp var5) {
      super(2130, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bbm.qG.pC();
      this.UW = bbm.uw.pC();
      if (var5 == null) {
         this.pC(this.pC(0));
         Assert.a(this.mv == 1);
         this.Ab();
         Assert.a(this.Aj == this.mv - this.ys.A);
      } else {
         this.LM = var5.LM;
         this.mv = var5.mv;
      }

      bbn var6 = new bbn(this);
      Object[] var10000 = new Object[]{this.UT.position()};

      for (int var7 = 0; var7 < this.DQ; var7++) {
         int var8 = this.wS();
         var10000 = new Object[]{var8, bbm.pC(var8), this.UT.position(), this.mv};
         bbn.HE var9 = var6.pC(var8, true);
         var9.pC();
         this.Bc.add(var9);
         this.Cu.add(var8);
      }

      var10000 = new Object[]{this.UT.position()};
      var10000 = new Object[]{this.UT.position()};

      for (int var10 = 0; var10 < this.ZN; var10++) {
         int var12 = this.wS();
         var10000 = new Object[]{var12, bbm.pC(var12), this.UT.position(), this.mv};
         bbn.HE var14 = var6.pC(var12, false);
         var14.pC();
         this.Bc.add(var14);
         this.Cu.add(var12);
      }

      var10000 = new Object[]{this.UT.position()};

      for (bbn.HE var13 : this.Bc) {
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
      return this.gp == bbp.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bbm.hM.pC(), "Null"));
      this.pC(this.pC(bbm.hM.pC(), "Sentinel"));
      this.pC(this.pC(bbm.hM.pC(), "TransitionSentinel"));
      this.pC(this.pC(bbm.Um.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bbm.Um.pC(), "ZeroArray"));
      this.pC(this.pC(bbm.Bc.pC(), "DynamicType"));
      this.pC(this.pC(bbm.Bc.pC(), "VoidType"));
      this.pC(this.pC(bbm.OB.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bbm.go.pC(), "True"));
      this.pC(this.pC(bbm.go.pC(), "False"));
      this.pC(this.pC(bbm.Um.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bbm.Um.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bbm.hK.pC(), "EmptyContextScope"));
      this.pC(this.pC(bbm.ah.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bbm.Ab.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bbm.eP.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bbm.rl.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bbm.z.pC(), "EmptyExceptionHandlers"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bbm.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bbm.Um.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bbm.Um.pC(), "CachedArray"));

      for (int var1 = bbm.UT.pC(); var1 < bbm.DQ.pC(); var1++) {
         if (var1 != bbm.Cu.pC() && var1 != bbm.EX.pC()) {
            this.pC(this.pC(bbm.UT.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bbm.UT.pC(), "Dynamic"));
      this.pC(this.pC(bbm.UT.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bbm.xC.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bbm.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bbm var5 : bbm.values()) {
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
