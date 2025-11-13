package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bcn extends bbd {
   long DQ;
   long ZN;
   long OB;
   int[] pF;
   List Bc = new ArrayList();
   int OI;
   int Bf;

   public bcn(byte[] var1, int var2, byte[] var3, int var4, bcn var5) {
      super(2190, var1, var2, var3, var4, var5);
      this.Aj = this.E();
      this.EX = this.E();
      this.DQ = this.E();
      this.ZN = this.E();
      this.OB = this.E();
      this.hZ = bck.AS.pC();
      this.UW = bck.NN.pC();
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

      bcl var6 = new bcl(this);
      Object[] var17 = new Object[]{this.UT.position()};

      for (int var13 = 0; var13 < this.DQ; var13++) {
         long var15 = this.ys();
         int var10 = (int)(var15 >>> 1);
         boolean var11 = (var15 & 1L) == 1L;
         var17 = new Object[]{var11, var10, bck.pC(var10), this.UT.position(), this.mv};
         bcl.oP var12 = var6.pC(var10, var11, true);
         if (var12 == null) {
            throw new ToDoException(Strings.ff("Cluster deser. not implemented for cid=%d (%s)", var10, bck.pC(var10)));
         }

         var12.pC();
         this.Bc.add(var12);
         this.Cu.add(var10);
      }

      var17 = new Object[]{this.UT.position()};

      for (bcl.oP var16 : this.Bc) {
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
      return this.gp == bcn.Av.wS.pC();
   }

   void Ab() {
      this.pC(this.pC(bck.aK.pC(), "Null"));
      this.pC(this.pC(bck.aK.pC(), "Sentinel"));
      this.pC(this.pC(bck.aK.pC(), "TransitionSentinel"));
      this.pC(this.pC(bck.be.pC(), "EmptyArray", new Object[0]));
      this.pC(this.pC(bck.be.pC(), "ZeroArray"));
      this.pC(this.pC(bck.ck.pC(), "DynamicType"));
      this.pC(this.pC(bck.ck.pC(), "VoidType"));
      this.pC(this.pC(bck.Bf.pC(), "EmptyTypeArguments"));
      this.pC(this.pC(bck.Xs.pC(), "True"));
      this.pC(this.pC(bck.Xs.pC(), "False"));
      this.pC(this.pC(bck.be.pC(), "ExtractorParameterTypes"));
      this.pC(this.pC(bck.be.pC(), "ExtractorParameterNames"));
      this.pC(this.pC(bck.Aj.pC(), "EmptyContextScope"));
      this.pC(this.pC(bck.Ab.pC(), "EmptyObjectPool"));
      this.pC(this.pC(bck.Ek.pC(), "EmptyCompressedStackMaps"));
      this.pC(this.pC(bck.rl.pC(), "EmptyPCDescriptors"));
      this.pC(this.pC(bck.hK.pC(), "EmptyLocalVarDescriptors"));
      this.pC(this.pC(bck.Er.pC(), "EmptyExceptionHandlers"));
      this.pC(this.pC(bck.Er.pC(), "EmptyExceptionHandlersAsync"));
      Longs.range(this.ys.xC).forEach(var1x -> this.pC(this.pC(bck.pC.pC(), "CachedArgsDescriptors")));
      Longs.range(this.ys.ED).forEach(var1x -> this.pC(this.pC(bck.be.pC(), "CachedICDataArrays")));
      this.pC(this.pC(bck.be.pC(), "CachedArray"));

      for (int var1 = bck.E.pC(); var1 < bck.Bc.pC(); var1++) {
         if (var1 != bck.cX.pC() && var1 != bck.os.pC()) {
            this.pC(this.pC(bck.E.pC(), "ClassStub"));
         }
      }

      this.pC(this.pC(bck.E.pC(), "Dynamic"));
      this.pC(this.pC(bck.E.pC(), "Void"));
      if (!this.Ab) {
         Longs.range(this.ys.Sc).forEach(var1x -> this.pC(this.pC(bck.Sc.pC(), "StubCode")));
      }
   }

   @Override
   public String sY(int var1) {
      return bck.pC(var1).toString();
   }

   @Override
   public Map ah() {
      TreeMap var1 = new TreeMap();

      for (bck var5 : bck.values()) {
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
