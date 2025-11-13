package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bdx extends bbd {
   long DQ;
   long ZN;
   long OB;
   int[] pF;
   List Bc = new ArrayList();
   int OI;
   int Bf;

   public bdx(byte[] var1, int var2, byte[] var3, int var4, bdx var5) {
      super(3080, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bdu.fn.pC();
      this.UW = bdu.xg.pC();
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

      bdv var6 = new bdv(this);
      Object[] var16 = new Object[]{this.UT.position()};

      for (int var12 = 0; var12 < this.DQ; var12++) {
         int var14 = this.ld();
         int var9 = var14 >> 12 & 1048575;
         boolean var10 = (var14 >> 1 & 1) != 0;
         var16 = new Object[]{var10, var9, bdu.pC(var9), this.UT.position(), this.mv};
         bdv.oP var11 = var6.pC(var9, var10, true);
         if (var11 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var9, bdu.pC(var9)));
         }

         var11.pC();
         this.Bc.add(var11);
         this.Cu.add(var9);
      }

      var16 = new Object[]{this.UT.position()};

      for (bdv.oP var15 : this.Bc) {
         var16 = new Object[]{this.UT.position(), var15};
         var15.A();
      }

      var16 = new Object[]{this.UT.position()};
      this.eP();
      var16 = new Object[]{this.UT.position()};
      Assert.a(this.EX == this.mv - this.ys.A);
   }

   @Override
   protected boolean pC() {
      return this.gp == bdx.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bdu.ik.pC(), "Null"));
      this.pC(this.pC(bdu.ik.pC(), "Sentinel"));
      this.pC(this.pC(bdu.ik.pC(), "OptimizedOut"));
      this.pC(this.pC(bdu.Xh.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bdu.Xh.pC(), "ZeroArray"));
      this.pC(this.pC(bdu.Xh.pC(), "ZeroArray2"));
      this.pC(this.pC(bdu.e.pC(), "DynamicType"));
      this.pC(this.pC(bdu.e.pC(), "VoidType"));
      this.pC(this.pC(bdu.ck.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bdu.KV.pC(), "True"));
      this.pC(this.pC(bdu.KV.pC(), "False"));
      this.pC(this.pC(bdu.Xh.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bdu.Xh.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bdu.LM.pC(), "EmptyContextScope"));
      this.pC(this.pC(bdu.z.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bdu.Er.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bdu.Ek.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bdu.FE.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bdu.Aj.pC(), "EmptyExceptionHandlers"));
      this.pC(this.pC(bdu.Aj.pC(), "EmptyExceptionHandlersAsync"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bdu.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bdu.Xh.pC(), "CachedICDataArrays")));

      for (int var1 = bdu.E.pC(); var1 < bdu.Bf.pC(); var1++) {
         if (var1 != bdu.ZN.pC() && var1 != bdu.hZ.pC()) {
            this.pC(this.pC(bdu.E.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bdu.E.pC(), "Dynamic"));
      this.pC(this.pC(bdu.E.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bdu.ah.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bdu.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bdu var5 : bdu.values()) {
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

   @Override
   public int E(int var1) {
      return var1 & 15;
   }

   @Override
   public int NS(int var1) {
      return var1 >> 5 & 7;
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
