package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bbx extends bbd {
   long DQ;
   long ZN;
   long OB;
   List pF = new ArrayList();
   int Bc;
   int OI;

   public bbx(byte[] var1, int var2, byte[] var3, int var4, bbx var5) {
      super(2150, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bbu.MZ.pC();
      this.UW = bbu.x.pC();
      if (var5 == null) {
         this.pC(this.pC(0));
         Assert.a(this.mv == 1);
         this.Ab();
         Assert.a(this.Aj == this.mv - this.ys.A);
      } else {
         this.LM = var5.LM;
         this.mv = var5.mv;
      }

      bbv var6 = new bbv(this);
      Object[] var10000 = new Object[]{this.UT.position()};

      for (int var7 = 0; var7 < this.DQ; var7++) {
         long var8 = this.ys();
         int var10 = (int)(var8 >>> 1);
         boolean var11 = (var8 & 1L) == 1L;
         var10000 = new Object[]{var11, var10, bbu.pC(var10), this.UT.position(), this.mv};
         bbv.oP var12 = var6.pC(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bbu.pC(var10)));
         }

         var12.pC();
         this.pF.add(var12);
         this.Cu.add(var10);
      }

      var10000 = new Object[]{this.UT.position()};

      for (bbv.oP var14 : this.pF) {
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
      return this.gp == bbx.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bbu.JV.pC(), "Null"));
      this.pC(this.pC(bbu.JV.pC(), "Sentinel"));
      this.pC(this.pC(bbu.JV.pC(), "TransitionSentinel"));
      this.pC(this.pC(bbu.IE.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bbu.IE.pC(), "ZeroArray"));
      this.pC(this.pC(bbu.ck.pC(), "DynamicType"));
      this.pC(this.pC(bbu.ck.pC(), "VoidType"));
      this.pC(this.pC(bbu.Bf.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bbu.gj.pC(), "True"));
      this.pC(this.pC(bbu.gj.pC(), "False"));
      this.pC(this.pC(bbu.IE.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bbu.IE.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bbu.Aj.pC(), "EmptyContextScope"));
      this.pC(this.pC(bbu.Ab.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bbu.Ek.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bbu.rl.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bbu.hK.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bbu.Er.pC(), "EmptyExceptionHandlers"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bbu.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bbu.IE.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bbu.IE.pC(), "CachedArray"));

      for (int var1 = bbu.E.pC(); var1 < bbu.Bc.pC(); var1++) {
         if (var1 != bbu.cX.pC() && var1 != bbu.os.pC()) {
            this.pC(this.pC(bbu.E.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bbu.E.pC(), "Dynamic"));
      this.pC(this.pC(bbu.E.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bbu.Sc.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bbu.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bbu var5 : bbu.values()) {
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
