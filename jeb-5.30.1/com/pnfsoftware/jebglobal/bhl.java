package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bhl {
   private static final ILogger RF = GlobalLog.getLogger(bhl.class);
   bhn q;

   bhl(bhn var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bhl.Bu q(int var1, boolean var2, boolean var3) {
      bhk var4 = bhk.q(var1);
      if (var1 >= bhk.JF.q() || var1 == bhk.ZT.q()) {
         return new bhl.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bhl.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (bhm.q[var4.ordinal()]) {
               case 1:
               case 2:
               case 3:
                  return new bhl.KZ(var2, var3, var1);
               case 4:
               case 5:
               case 6:
                  if (var3) {
                     return new bhl.KZ(var2, var3, var1);
                  }
            }
         }

         switch (bhm.q[var4.ordinal()]) {
            case 1:
               Assert.a(!var2);
               return new bhl.zJ();
            case 2:
               Assert.a(!var2);
               return new bhl.iZ();
            case 3:
               Assert.a(!var2);
               return new bhl.tw();
            case 4:
            case 5:
            default:
               return null;
            case 6:
               return new bhl.ME(var2, var3 && this.q.Uv != null);
            case 7:
               Assert.a(!var2);
               return new bhl.CU();
            case 8:
               return new bhl.qx();
            case 9:
               return new bhl.Fw(var2, var3);
            case 10:
               Assert.a(!var2);
               return new bhl.vX();
            case 11:
               Assert.a(!var2);
               return new bhl.vb();
            case 12:
               Assert.a(!var2);
               return new bhl.nI();
            case 13:
               Assert.a(!var2);
               return new bhl.vn();
            case 14:
               Assert.a(!var2);
               return new bhl.PY();
            case 15:
               Assert.a(!var2);
               return new bhl.LR();
            case 16:
               Assert.a(!var2);
               return new bhl.tl();
            case 17:
               Assert.a(!var2);
               throw new ToDoException();
            case 18:
               Assert.a(!var2);
               return new bhl.Nt();
            case 19:
               Assert.a(!var2);
               return new bhl.CI();
            case 20:
               Assert.a(!var2);
               return new bhl.qV();
            case 21:
               Assert.a(!var2);
               return null;
            case 22:
               Assert.a(!var2);
               return null;
            case 23:
               Assert.a(!var2);
               return new bhl.ry();
            case 24:
               Assert.a(!var2);
               return null;
            case 25:
               Assert.a(!var2);
               return null;
            case 26:
               Assert.a(!var2);
               return new bhl.SG();
            case 27:
               Assert.a(!var2);
               return new bhl.Nz();
            case 28:
               Assert.a(!var2);
               return null;
            case 29:
               Assert.a(!var2);
               return null;
            case 30:
               Assert.a(!var2);
               return null;
            case 31:
               return new bhl.ct(var2, var3);
            case 32:
               return new bhl.oL(var2, var3);
            case 33:
               return new bhl.GA(var2, var3);
            case 34:
               return new bhl.ej(var2);
            case 35:
               return new bhl.Xa(var2);
            case 36:
               return new bhl.EE(var2);
            case 37:
            case 38:
            case 39:
               return null;
            case 40:
               Assert.a(!var2);
               return new bhl.Vj();
            case 41:
               Assert.a(!var2);
               return null;
            case 42:
               Assert.a(!var2);
               return null;
            case 43:
               Assert.a(!var2);
               return null;
            case 44:
               throw new RuntimeException();
            case 45:
               return new bhl.Uz(var2, var1);
            case 46:
               throw new RuntimeException();
            case 47:
               return new bhl.qa(var2, var1);
            case 48:
            case 49:
               return new bhl.eo(var2, var1);
            case 50:
               return new bhl.Zu(var2, var1);
            case 51:
               return new bhl.HA(var2, var3);
            case 52:
               return new bhl.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bhl.oM {
      @Override
      public void q() {
         this.RF(bhk.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bhl.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bhl.this.q.LK(var6);
               Assert.a(var8 != bhl.this.q.gP.mI);
               if (var8 == bhl.this.q.gP.Dz) {
                  int var9 = bhl.this.q.oW(var6);
                  if (var9 == bhl.this.q.gP.xW) {
                     long var10 = bhl.this.q.Uv();
                     var7.put("rawObj", var10);
                  } else if (var9 == bhl.this.q.gP.KT) {
                     long var12 = bhl.this.q.za();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bhl.this.q.gP.Gf) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bhl.this.q.gP.jq && var8 != bhl.this.q.gP.ui) {
                  if (var8 != bhl.this.q.gP.TX) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class CU extends bhl.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bhl.this.q.jq;
         long var1 = bhl.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bhl.this.q.q(bhk.oW.q());
            var2.q("id", bhl.this.q.oW());
            bhl.this.q.q(var2);
         });
         this.RF = bhl.this.q.jq;
         this.q(bhk.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bhl.this.q.xK(var6);
               this.q(var8);
               int var9 = bhl.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bhl.this.q.PV && !bhl.this.q.io) {
                  var8.q("kernelOffset", bhl.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bhl.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bhl.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bhl.this.q.za());
               if (!bhl.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bhl.this.q.za());
               var8.q("numNativeFields", bhl.this.q.nf());
               if (!bhl.this.q.PV) {
                  Assert.a(!bhl.this.q.io);
                  var8.q("tokenPos", bhl.this.q.gO());
                  var8.q("endTokenPos", bhl.this.q.gO());
               }

               var8.q("stateBits", bhl.this.q.nf());
               if (bhl.this.q.PV) {
                  if (var7) {
                     bhl.this.q.nf();
                  } else if (!bhl.this.q.gP.q(var9)) {
                     bhl.this.q.TX.put(var9, bhl.this.q.nf());
                  }
               }

               bhl.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhl.this.q.Uv());
         if (!bhl.this.q.Me) {
            var1.q("userName", bhl.this.q.Uv());
         }

         var1.q("functions", bhl.this.q.Uv());
         var1.q("functionsHashTable", bhl.this.q.Uv());
         var1.q("fields", bhl.this.q.Uv());
         var1.q("offsetInWordsToField", bhl.this.q.Uv());
         var1.q("interfaces", bhl.this.q.Uv());
         var1.q("script", bhl.this.q.Uv());
         var1.q("library", bhl.this.q.Uv());
         var1.q("typeParameters", bhl.this.q.Uv());
         var1.q("superType", bhl.this.q.Uv());
         var1.q("constants", bhl.this.q.Uv());
         var1.q("declarationType", bhl.this.q.Uv());
         var1.q("invocationDispatcherCache", bhl.this.q.Uv());
         if (!bhl.this.q.Me || !bhl.this.q.PV) {
            var1.q("directImplementors", bhl.this.q.Uv());
            var1.q("directSubclasses", bhl.this.q.Uv());
         }

         if (!bhl.this.q.PV) {
            var1.q("allocationStub", bhl.this.q.Uv());
            var1.q("dependentCode", bhl.this.q.Uv());
         }
      }
   }

   class EE extends bhl.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bhk.Ov.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bhl.this.q.PV());
         }
      }
   }

   class FL extends bhl.oM {
      int q;

      public FL(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bhl.this.q.HF(this.q);
            var2.q("data", bhl.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bhl.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bhk.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bhl.this.q.qa());
            var2.q("nullabity", bhl.this.q.nf());
            var2.q("instantiations", bhl.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bhl.this.q.Uv());
            }
         }
      }
   }

   class GA extends bhl.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhk.Tq.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bhl.this.q.HF());
            var2.q("index", bhl.this.q.HF());
            long var3 = bhl.this.q.zz();
            var2.q("typeState", var3 >> (int)bhl.this.q.gP.Rr);
            var2.q("nullability", var3 & bhl.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhl.this.q.Uv());
         var1.q("hash", bhl.this.q.Uv());
         var1.q("owner", bhl.this.q.Uv());
      }
   }

   class HA extends bhl.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhk.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bhl.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bhl.this.q.Uv());
         var1.q("hash", bhl.this.q.Uv());
         var1.q("shape", bhl.this.q.Uv());
         var1.q("fieldTypes", bhl.this.q.Uv());
      }
   }

   class KZ extends bhl.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bhl.this.q.jq;
         long var1 = bhl.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bhl.this.q.nf() << (int)bhl.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bhl.this.q.q(this.q);
            var7.q("data", var6);
            bhl.this.q.q(var7);
         }

         this.gO = bhl.this.q.jq;
         if (this.q == bhk.fn.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bhk.ZU.q() && this.q != bhk.fn.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bhl.this.q.gO.position(var1);
            bhl.this.q.gO.i32();
            long var2;
            if (bhl.this.q.wF) {
               bhl.this.q.gO.i32();
               var2 = bhl.this.q.gO.i64();
            } else {
               bhl.this.q.gO.i32();
               var2 = bhl.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bhl.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            if (!bhl.this.q.PV) {
               var2.q("flagsAndMaxPosition", bhl.this.q.qa());
            }

            var2.q("kernelScriptIndex", bhl.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bhl.this.q.Uv());
         if (bhl.this.q.io) {
            if (!bhl.this.q.Me) {
               var1.q("resolvedUrl", bhl.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bhl.this.q.Uv());
            var1.q("resolvedUrl", bhl.this.q.Uv());
            var1.q("lineStarts", bhl.this.q.Uv());
            var1.q("constantCoverage", bhl.this.q.Uv());
            var1.q("debugPositions", bhl.this.q.Uv());
            var1.q("kernelProgramInfo", bhl.this.q.Uv());
         }
      }
   }

   class ME extends bhl.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bhl.this.q.jq;
         long var1 = bhl.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bhl.this.q.nf();
            bhk var6 = (var4 & 1L) != 0L ? bhk.Sz : bhk.ZU;
            long var7 = var4 >> 1;
            bew var9 = bhl.this.q.q(var6.q());
            var9.q("length", var7);
            bhl.this.q.q(var9);
         }

         this.gO = bhl.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            long var3 = bhl.this.q.nf();
            long var5 = var3 >> 1;
            bhk var7 = (var3 & 1L) != 0L ? bhk.Sz : bhk.ZU;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bhk.ZU) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bhl.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bhl.this.q.zz();
                  var10 |= bhl.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bhl.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bhl.this.q.jq;
         bhl.this.q.Ri = this.oW;
         long var1 = bhl.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bhl.this.q.jq;
         this.q = bhl.this.q.jq;
         var1 = bhl.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bhl.this.q.jq;
      }

      private void Dw() {
         int var1 = bhl.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bhl.this.q.q(bhk.Me.q());
         bhl.this.q.q(var3);
         var3.q("stateBits", var1);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            this.q(var1, false);
         }

         for (int var2 = this.q; var2 < this.RF; var2++) {
            this.q(var2, true);
         }
      }

      void q(int var1, boolean var2) {
         bew var3 = bhl.this.q.xK(var1);
         this.q(var3, var2);
         if (!bhl.this.q.io) {
            var3.q("objectPool", bhl.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bhl.this.q.Uv(), bhk.nf.toString(), bhk.oW.toString(), bhk.AB.toString());
         var3.q("exceptionHandlers", bhl.this.q.Uv(), bhk.wF.toString());
         var3.q("pcDescriptors", bhl.this.q.Uv(), bhk.Ef.toString());
         var3.q("catchEntry", bhl.this.q.Uv());
         if (bhl.this.q.LK) {
            var3.q("compressedStackMaps", bhl.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bhl.this.q.Uv(), bhk.of.toString());
         var3.q("codeSourceMap", bhl.this.q.Uv(), bhk.cC.toString());
         if (!bhl.this.q.PV && bhl.this.q.LK) {
            var3.q("deoptInfoArray", bhl.this.q.Uv());
            var3.q("staticCallsTargetTable", bhl.this.q.Uv());
         }

         if (!bhl.this.q.Me) {
            var3.q("returnAddressMetadata", bhl.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bhl.this.q.Gf ? bhl.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bhl.this.q.PV) {
            long var3 = bhl.this.q.Rv[2 * bhl.this.q.ZT];
            long var5 = bhl.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bhl.this.q.gP.oQ : 0L;
            long var12 = var9 ? bhl.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bhl.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.IN.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("parent", bhl.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("instructionsImage", null);
            int var3 = 0 | bhl.this.q.qa() << 2;
            var2.q("packedFields", var3);
         }
      }
   }

   class PY extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bhl.this.q.gP());
            var2.q("hostOffsetOrFieldId", bhl.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bhl.this.q.Uv());
         var1.q("owner", bhl.this.q.Uv());
         var1.q("type", bhl.this.q.Uv());
         var1.q("initializerFunction", bhl.this.q.Uv());
      }
   }

   class SG extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("cache", bhl.this.q.Uv());
            var2.q("numInputs", bhl.this.q.io());
            var2.q("numOccupied", bhl.this.q.io());
         }
      }
   }

   class Uz extends bhl.oM {
      int q;

      Uz(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.q(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhl.this.q.Uv());
         var1.q("hashMask", bhl.this.q.Uv());
         var1.q("data", bhl.this.q.Uv());
         var1.q("usedData", bhl.this.q.Uv());
         var1.q("deletedKeys", bhl.this.q.Uv());
      }
   }

   class Vj extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.iu.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bhl.this.q.Uv());
         var1.q("length", bhl.this.q.Uv());
         var1.q("data", bhl.this.q.Uv());
      }
   }

   class Xa extends bhl.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bhl.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bhl.this.q.q(bhk.ZA.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bhl.this.q.Hk());
            bhl.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bhl.oM {
      int q;

      Zu(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhl.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bhl.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bhl.this.q.jq;
         long var1 = bhl.this.q.nf();
         this.RF = bhl.this.q.qa();
         this.xK = bhl.this.q.qa();
         bhl.this.q.q(var1, this.q);
         this.gO = bhl.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bhl.this.q.gP.nf;
         int var2 = (int)bhl.this.q.q(this.xK * bhl.this.q.gP.gO, bhl.this.q.gP.gP);
         Long var3 = bhl.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bhl.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bhl.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bhl.this.q.gP.gO)) {
               if (bhl.this.q.RF(var3, var6 / (int)bhl.this.q.gP.gO)) {
                  bhl.this.q.oQ();
               } else {
                  bhl.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bhl.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bhl.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhk.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bhl.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhl.this.q.Uv());
         var1.q("hash", bhl.this.q.Uv());
         var1.q("arguments", bhl.this.q.Uv());
      }
   }

   class eM extends bhl.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bhk.fQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bhl.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bhl.this.q.Uv();
            }
         }
      }
   }

   class ej extends bhl.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bhk.nq.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bhl.this.q.Uv());
         var1.q("functionTypeArguments", bhl.this.q.Uv());
         var1.q("delayedTypeArguments", bhl.this.q.Uv());
         var1.q("function", bhl.this.q.Uv());
         var1.q("context", bhl.this.q.Uv());
         var1.q("hash", bhl.this.q.Uv());
      }
   }

   class eo extends bhl.oM {
      int q;

      eo(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bhl.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhl.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bhl.oM {
      @Override
      public void q() {
         this.RF(bhk.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhl.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bhl.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.Xo.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bhl.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhl.this.q.Uv());
         var1.q("argsDescriptor", bhl.this.q.Uv());
         var1.q("buckets", bhl.this.q.Uv());
         var1.q("mask", bhl.this.q.Uv());
      }
   }

   class nI extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            if (bhl.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bhl.this.q.Uv());
            }

            var2.q("parentFunction", bhl.this.q.Uv());
            var2.q("closure", bhl.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bhl.this.q.nf());
         }
      }
   }

   class oL extends bhl.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhk.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bhl.this.q.zz();
            var2.q("typeState", var3 >> (int)bhl.this.q.gP.Rr);
            var2.q("nullability", var3 & bhl.this.q.gP.EB);
            var2.q("packedParameterCounts", bhl.this.q.io());
            var2.q("packedTypeParameterCounts", bhl.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhl.this.q.Uv());
         var1.q("hash", bhl.this.q.Uv());
         var1.q("typeParameters", bhl.this.q.Uv());
         var1.q("resultType", bhl.this.q.Uv());
         var1.q("parameterTypes", bhl.this.q.Uv());
         var1.q("namedParameterNames", bhl.this.q.Uv());
      }
   }

   abstract class oM implements bhl.Bu {
      boolean Dw;
      boolean Uv;
      int oW;
      int gO;

      @Override
      public void q() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void RF() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void q(int var1) {
         this.oW = bhl.this.q.jq;
         long var2 = bhl.this.q.nf();
         bhl.this.q.q(var2, var1);
         this.gO = bhl.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bhl.this.q.jq;
         long var2 = bhl.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bhl.this.q.q(var1);
            long var4 = bhl.this.q.nf();
            var3.q("length", var4);
            bhl.this.q.q(var3);
         });
         this.gO = bhl.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bhl.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bhl.this.q.nf();
            long var1 = bhl.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bhl.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bhl.oM {
      @Override
      public void q() {
         this.RF(bhk.wF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            long var3 = bhl.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bhl.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bhl.this.q.gP());
               var9.put("outerTryIndex", bhl.this.q.LK());
               var9.put("needsStacktrace", bhl.this.q.Dw());
               var9.put("hasCatchAll", bhl.this.q.Dw());
               var9.put("isGenerated", bhl.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bhl.oM {
      int q;

      qa(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.q(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhl.this.q.Uv());
         var1.q("hashMask", bhl.this.q.Uv());
         var1.q("data", bhl.this.q.Uv());
         var1.q("usedData", bhl.this.q.Uv());
         var1.q("deletedKeys", bhl.this.q.Uv());
      }
   }

   class qx extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.Tq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bhl.this.q.Uv());
         var1.q("flags", bhl.this.q.Uv());
         var1.q("bounds", bhl.this.q.Uv());
         var1.q("defaults", bhl.this.q.Uv());
      }
   }

   class ry extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.ui.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bhl.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhl.this.q.Uv());
         var1.q("argsDescriptor", bhl.this.q.Uv());
      }
   }

   class tl extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bhl.this.q.qa());
            var2.q("numImports", bhl.this.q.HF());
            var2.q("loadState", bhl.this.q.JY());
            var2.q("flags", bhl.this.q.zz());
            if (!bhl.this.q.PV && !bhl.this.q.io) {
               var2.q("kernelOffset", bhl.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhl.this.q.Uv());
         var1.q("url", bhl.this.q.Uv());
         var1.q("privateKey", bhl.this.q.Uv());
         var1.q("dictionary", bhl.this.q.Uv());
         var1.q("metadata", bhl.this.q.Uv());
         var1.q("toplevelClass", bhl.this.q.Uv());
         var1.q("usedScripts", bhl.this.q.Uv());
         var1.q("loadingUnit", bhl.this.q.Uv());
         var1.q("imports", bhl.this.q.Uv());
         var1.q("exports", bhl.this.q.Uv());
         if (!bhl.this.q.io) {
            var1.q("dependencies", bhl.this.q.Uv());
            var1.q("kernelProgramInfo", bhl.this.q.Uv());
         }
      }
   }

   class tw extends bhl.oM {
      @Override
      public void q() {
         this.RF(bhk.sH.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            long var3 = bhl.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bhl.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            if (!bhl.this.q.PV && !bhl.this.q.io) {
               var2.q("libraryKernelOffset", bhl.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bhl.this.q.Uv());
         var1.q("script", bhl.this.q.Uv());
         if (!bhl.this.q.io) {
            var1.q("libraryKernelData", bhl.this.q.Uv());
         }
      }
   }

   class vb extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            if (!bhl.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bhl.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bhl.this.q.Ri + (int)var3 - 1;
               bew var6 = bhl.this.q.xK(var5);
               if (var6.q().equals(bhk.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bhl.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bhl.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bhl.this.q.Uv());
         var1.q("owner", bhl.this.q.Uv());
         var1.q("signature", bhl.this.q.Uv(), bhk.CY.toString());
         var1.q("data", bhl.this.q.Uv());
      }
   }

   class vn extends bhl.oM {
      @Override
      public void q() {
         this.q(bhk.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bhl.this.q.nf());
            var2.q("callbackKind", bhl.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bhl.this.q.Uv());
         var1.q("cSignature", bhl.this.q.Uv());
         var1.q("callbackTarget", bhl.this.q.Uv());
         var1.q("callbackExceptionalReturn", bhl.this.q.Uv());
      }
   }

   class zJ extends bhl.oM {
      @Override
      public void q() {
         this.RF(bhk.Ef.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhl.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhl.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
