package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bcr extends bbd {
   long DQ;
   long ZN;
   long OB;
   int[] pF;
   List Bc = new ArrayList();
   int OI;
   int Bf;

   public bcr(byte[] var1, int var2, byte[] var3, int var4, bcr var5) {
      super(3000, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bco.wF.pC();
      this.UW = bco.np.pC();
      if (this.ZN > 0L && this.OB > 0L) {
         this.E.position((int)this.OB);
         this.E.i32();
         this.E.i32();
         int var10000 = (int)this.E.i64() / 2;
         this.E.i32();
         this.E.i32();
         this.E.i32();
         this.E.i32();
         int var7 = (int)this.ZN * 2;
         this.pF = new int[var7];

         for (int var8 = 0; var8 < var7; var8++) {
            this.pF[var8] = this.E.i32();
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

      bcp var6 = new bcp(this);
      Object[] var17 = new Object[]{this.UT.position()};

      for (int var13 = 0; var13 < this.DQ; var13++) {
         long var15 = this.ys();
         int var10 = (int)(var15 >>> 1);
         boolean var11 = (var15 & 1L) == 1L;
         var17 = new Object[]{var11, var10, bco.pC(var10), this.UT.position(), this.mv};
         bcp.oP var12 = var6.pC(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bco.pC(var10)));
         }

         var12.pC();
         this.Bc.add(var12);
         this.Cu.add(var10);
      }

      var17 = new Object[]{this.UT.position()};

      for (bcp.oP var16 : this.Bc) {
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
      return this.gp == bcr.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bco.gR.pC(), "Null"));
      this.pC(this.pC(bco.gR.pC(), "Sentinel"));
      this.pC(this.pC(bco.gR.pC(), "TransitionSentinel"));
      this.pC(this.pC(bco.gR.pC(), "OptimizedOut"));
      this.pC(this.pC(bco.Xh.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bco.Xh.pC(), "ZeroArray"));
      this.pC(this.pC(bco.RW.pC(), "DynamicType"));
      this.pC(this.pC(bco.RW.pC(), "VoidType"));
      this.pC(this.pC(bco.Pe.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bco.KV.pC(), "True"));
      this.pC(this.pC(bco.KV.pC(), "False"));
      this.pC(this.pC(bco.Xh.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bco.Xh.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bco.EX.pC(), "EmptyContextScope"));
      this.pC(this.pC(bco.rl.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bco.hK.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bco.z.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bco.Er.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bco.FE.pC(), "EmptyExceptionHandlers"));
      this.pC(this.pC(bco.FE.pC(), "EmptyExceptionHandlersAsync"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bco.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bco.Xh.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bco.Xh.pC(), "CachedArray"));

      for (int var1 = bco.E.pC(); var1 < bco.OI.pC(); var1++) {
         if (var1 != bco.DQ.pC() && var1 != bco.Cu.pC()) {
            this.pC(this.pC(bco.E.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bco.E.pC(), "Dynamic"));
      this.pC(this.pC(bco.E.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bco.ah.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bco.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bco var5 : bco.values()) {
         var1.put(var5.pC(), var5.toString());
      }

      return var1;
   }

   @Override
   public long kS() {
      long var1 = 0L;

      for (int var3 = 0; var3 < 4; var3++) {
         byte var4 = this.UT.get();
         var1 = var4 + (var1 << 7);
         if (var4 < 0) {
            break;
         }
      }

      long var5 = var1 + 128L;
      this.pC(var5);
      return var5;
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
