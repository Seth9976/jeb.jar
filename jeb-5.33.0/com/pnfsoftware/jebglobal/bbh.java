package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bbh extends bbd {
   long DQ;
   long ZN;
   List OB = new ArrayList();
   int pF;
   int Bc;

   public bbh(byte[] var1, int var2, byte[] var3, int var4, bbh var5) {
      super(2100, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.hZ = bbe.ET.pC();
      this.UW = bbe.JV.pC();
      if (var5 == null) {
         this.pC(this.pC(0));
         Assert.a(this.mv == 1);
         this.Ab();
         Assert.a(this.Aj == this.mv - this.ys.A);
      } else {
         this.LM = var5.LM;
         this.mv = var5.mv;
      }

      bbf var6 = new bbf(this);
      Object[] var10000 = new Object[]{this.UT.position()};

      for (int var7 = 0; var7 < this.DQ; var7++) {
         int var8 = (int)this.ys();
         var10000 = new Object[]{bbe.pC(var8), this.UT.position(), this.mv};
         bbf.RC var9 = var6.pC(var8);
         var9.pC();
         this.OB.add(var9);
         this.Cu.add(var8);
      }

      var10000 = new Object[]{this.UT.position()};

      for (bbf.RC var11 : this.OB) {
         var10000 = new Object[]{this.UT.position(), var11};
         var11.A();
      }

      var10000 = new Object[]{this.UT.position()};
      this.eP();
      var10000 = new Object[]{this.UT.position()};
      Assert.a(this.EX == this.mv - this.ys.A);
   }

   @Override
   protected boolean pC() {
      return this.gp == bbh.Av.kS.pC();
   }

   void Ab() {
      this.pC(this.pC(bbe.mV.pC(), "Null"));
      this.pC(this.pC(bbe.mV.pC(), "Sentinel"));
      this.pC(this.pC(bbe.mV.pC(), "TransitionSentinel"));
      this.pC(this.pC(bbe.tH.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bbe.tH.pC(), "ZeroArray"));
      this.pC(this.pC(bbe.Pe.pC(), "DynamicType"));
      this.pC(this.pC(bbe.Pe.pC(), "VoidType"));
      this.pC(this.pC(bbe.OI.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bbe.Nq.pC(), "True"));
      this.pC(this.pC(bbe.Nq.pC(), "False"));
      this.pC(this.pC(bbe.tH.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bbe.tH.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bbe.FE.pC(), "EmptyContextScope"));
      this.pC(this.pC(bbe.Ab.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bbe.Ek.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bbe.hK.pC(), "EmptyExceptionHandlers"));
      this.pC(this.pC(bbe.Sc.pC(), "ImplicitGetterBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "ImplicitSetterBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "ImplicitStaticGetterBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "MethodExtractorBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "InvokeClosureBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "InvokeFieldBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "NsmDispatcherBytecode"));
      this.pC(this.pC(bbe.Sc.pC(), "DynamicInvocationForwarderBytecode"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bbe.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bbe.tH.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bbe.tH.pC(), "CachedArray"));

      for (int var1 = bbe.UT.pC(); var1 < bbe.pF.pC(); var1++) {
         if (var1 != bbe.PR.pC() && var1 != bbe.sO.pC()) {
            this.pC(this.pC(bbe.UT.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bbe.UT.pC(), "Dynamic"));
      this.pC(this.pC(bbe.UT.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bbe.ED.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bbe.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bbe var5 : bbe.values()) {
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
