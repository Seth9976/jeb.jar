package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bcb extends bbd {
   long DQ;
   long ZN;
   long OB;
   long pF;
   int[] Bc;
   List OI = new ArrayList();
   int Bf;
   int Pe;

   public bcb(byte[] var1, int var2, byte[] var3, int var4, bcb var5) {
      super(2160, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.pF = this.E();
      this.hZ = bby.MZ.pC();
      this.UW = bby.x.pC();
      if (this.OB > 0L && this.pF > 0L) {
         this.E.position((int)this.pF);
         this.E.i32();
         this.E.i32();
         int var10000 = (int)this.E.i64() / 2;
         this.E.i32();
         this.E.i32();
         this.E.i32();
         this.E.i32();
         int var7 = (int)this.OB * 2;
         this.Bc = new int[var7];

         for (int var8 = 0; var8 < var7; var8++) {
            this.Bc[var8] = this.E.i32();
         }
      }

      if (var5 == null) {
         this.pC(this.pC(0));
         Assert.a(this.mv == 1);
         this.Ab();
         Assert.a(this.Aj == this.mv - this.ys.A);
      } else {
         this.LM = var5.LM;
         this.mv = var5.mv;
      }

      bbz var6 = new bbz(this);
      Object[] var17 = new Object[]{this.UT.position()};

      for (int var13 = 0; var13 < this.DQ; var13++) {
         long var15 = this.ys();
         int var10 = (int)(var15 >>> 1);
         boolean var11 = (var15 & 1L) == 1L;
         var17 = new Object[]{var11, var10, bby.pC(var10), this.UT.position(), this.mv};
         bbz.oP var12 = var6.pC(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bby.pC(var10)));
         }

         var12.pC();
         this.OI.add(var12);
         this.Cu.add(var10);
      }

      var17 = new Object[]{this.UT.position()};

      for (bbz.oP var16 : this.OI) {
         var17 = new Object[]{this.UT.position(), var16};
         var16.A();
      }

      var17 = new Object[]{this.UT.position()};
      this.eP();
      var17 = new Object[]{this.UT.position()};
      Assert.a(this.EX == this.mv - this.ys.A);
   }

   @Override
   protected boolean pC() {
      return this.gp == bcb.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bby.JV.pC(), "Null"));
      this.pC(this.pC(bby.JV.pC(), "Sentinel"));
      this.pC(this.pC(bby.JV.pC(), "TransitionSentinel"));
      this.pC(this.pC(bby.IE.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bby.IE.pC(), "ZeroArray"));
      this.pC(this.pC(bby.ck.pC(), "DynamicType"));
      this.pC(this.pC(bby.ck.pC(), "VoidType"));
      this.pC(this.pC(bby.Bf.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bby.gj.pC(), "True"));
      this.pC(this.pC(bby.gj.pC(), "False"));
      this.pC(this.pC(bby.IE.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bby.IE.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bby.Aj.pC(), "EmptyContextScope"));
      this.pC(this.pC(bby.Ab.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bby.Ek.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bby.rl.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bby.hK.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bby.Er.pC(), "EmptyExceptionHandlers"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bby.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bby.IE.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bby.IE.pC(), "CachedArray"));

      for (int var1 = bby.E.pC(); var1 < bby.Bc.pC(); var1++) {
         if (var1 != bby.cX.pC() && var1 != bby.os.pC()) {
            this.pC(this.pC(bby.E.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bby.E.pC(), "Dynamic"));
      this.pC(this.pC(bby.E.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bby.Sc.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bby.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bby var5 : bby.values()) {
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
