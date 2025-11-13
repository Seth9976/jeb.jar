package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bhp {
   private static final ILogger RF = GlobalLog.getLogger(bhp.class);
   bhr q;

   bhp(bhr var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bhp.Bu q(int var1, boolean var2, boolean var3) {
      bho var4 = bho.q(var1);
      if (var1 >= bho.JF.q() || var1 == bho.ZT.q()) {
         return new bhp.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bhp.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Ef:
               case cC:
               case sH:
                  return new bhp.KZ(var2, var3, var1);
               case ZU:
               case Sz:
               case fn:
                  if (var3) {
                     return new bhp.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Ef:
               Assert.a(!var2);
               return new bhp.zJ();
            case cC:
               Assert.a(!var2);
               return new bhp.iZ();
            case sH:
               Assert.a(!var2);
               return new bhp.tw();
            case ZU:
            case Sz:
            default:
               return null;
            case fn:
               return new bhp.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bhp.CU();
            case gP:
               return new bhp.qx();
            case GY:
               return new bhp.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bhp.vX();
            case nf:
               Assert.a(!var2);
               return new bhp.vb();
            case za:
               Assert.a(!var2);
               return new bhp.nI();
            case lm:
               Assert.a(!var2);
               return new bhp.vn();
            case zz:
               Assert.a(!var2);
               return new bhp.PY();
            case JY:
               Assert.a(!var2);
               return new bhp.LR();
            case HF:
               Assert.a(!var2);
               return new bhp.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bhp.Nt();
            case Gf:
               Assert.a(!var2);
               return new bhp.CI();
            case wF:
               Assert.a(!var2);
               return new bhp.qV();
            case If:
               Assert.a(!var2);
               return null;
            case Dz:
               Assert.a(!var2);
               return null;
            case ui:
               Assert.a(!var2);
               return new bhp.ry();
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return null;
            case Bu:
               Assert.a(!var2);
               return new bhp.SG();
            case IN:
               Assert.a(!var2);
               return new bhp.Nz();
            case YN:
               Assert.a(!var2);
               return null;
            case Rv:
               Assert.a(!var2);
               return null;
            case Ri:
               Assert.a(!var2);
               return null;
            case AB:
               return new bhp.ct(var2, var3);
            case CY:
               return new bhp.oL(var2, var3);
            case Tq:
               return new bhp.GA(var2, var3);
            case nq:
               return new bhp.ej(var2);
            case ZA:
               return new bhp.Xa(var2);
            case Ov:
               return new bhp.EE(var2);
            case LL:
            case nv:
            case PQ:
               return null;
            case iu:
               Assert.a(!var2);
               return new bhp.Vj();
            case qR:
               Assert.a(!var2);
               return null;
            case fw:
               Assert.a(!var2);
               return null;
            case Wp:
               Assert.a(!var2);
               return null;
            case Qu:
               throw new RuntimeException();
            case jh:
               return new bhp.Uz(var2, var1);
            case Jf:
               throw new RuntimeException();
            case vC:
               return new bhp.qa(var2, var1);
            case of:
            case os:
               return new bhp.eo(var2, var1);
            case Hk:
               return new bhp.Zu(var2, var1);
            case WI:
               return new bhp.HA(var2, var3);
            case fQ:
               return new bhp.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bhp.oM {
      @Override
      public void q() {
         this.RF(bho.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bhp.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bhp.this.q.LK(var6);
               Assert.a(var8 != bhp.this.q.gP.mI);
               if (var8 == bhp.this.q.gP.Dz) {
                  int var9 = bhp.this.q.oW(var6);
                  if (var9 == bhp.this.q.gP.xW) {
                     long var10 = bhp.this.q.Uv();
                     var7.put("rawObj", var10);
                  } else if (var9 == bhp.this.q.gP.KT) {
                     long var12 = bhp.this.q.za();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bhp.this.q.gP.Gf) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bhp.this.q.gP.jq && var8 != bhp.this.q.gP.ui) {
                  if (var8 != bhp.this.q.gP.TX) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class CU extends bhp.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bhp.this.q.jq;
         long var1 = bhp.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bhp.this.q.q(bho.oW.q());
            var2.q("id", bhp.this.q.oW());
            bhp.this.q.q(var2);
         });
         this.RF = bhp.this.q.jq;
         this.q(bho.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bhp.this.q.xK(var6);
               this.q(var8);
               int var9 = bhp.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bhp.this.q.PV && !bhp.this.q.io) {
                  var8.q("kernelOffset", bhp.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bhp.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bhp.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bhp.this.q.za());
               if (!bhp.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bhp.this.q.za());
               var8.q("numNativeFields", bhp.this.q.nf());
               if (!bhp.this.q.PV) {
                  Assert.a(!bhp.this.q.io);
                  var8.q("tokenPos", bhp.this.q.gO());
                  var8.q("endTokenPos", bhp.this.q.gO());
               }

               var8.q("stateBits", bhp.this.q.nf());
               if (bhp.this.q.PV) {
                  if (var7) {
                     bhp.this.q.nf();
                  } else if (!bhp.this.q.gP.q(var9)) {
                     bhp.this.q.TX.put(var9, bhp.this.q.nf());
                  }
               }

               bhp.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhp.this.q.Uv());
         if (!bhp.this.q.Me) {
            var1.q("userName", bhp.this.q.Uv());
         }

         var1.q("functions", bhp.this.q.Uv());
         var1.q("functionsHashTable", bhp.this.q.Uv());
         var1.q("fields", bhp.this.q.Uv());
         var1.q("offsetInWordsToField", bhp.this.q.Uv());
         var1.q("interfaces", bhp.this.q.Uv());
         var1.q("script", bhp.this.q.Uv());
         var1.q("library", bhp.this.q.Uv());
         var1.q("typeParameters", bhp.this.q.Uv());
         var1.q("superType", bhp.this.q.Uv());
         var1.q("constants", bhp.this.q.Uv());
         var1.q("declarationType", bhp.this.q.Uv());
         var1.q("invocationDispatcherCache", bhp.this.q.Uv());
         if (!bhp.this.q.Me || !bhp.this.q.PV) {
            var1.q("directImplementors", bhp.this.q.Uv());
            var1.q("directSubclasses", bhp.this.q.Uv());
         }

         if (!bhp.this.q.PV) {
            var1.q("allocationStub", bhp.this.q.Uv());
            var1.q("dependentCode", bhp.this.q.Uv());
         }
      }
   }

   class EE extends bhp.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bho.Ov.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bhp.this.q.PV());
         }
      }
   }

   class FL extends bhp.oM {
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
            bew var2 = bhp.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bhp.this.q.HF(this.q);
            var2.q("data", bhp.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bhp.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bho.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bhp.this.q.qa());
            var2.q("nullabity", bhp.this.q.nf());
            var2.q("instantiations", bhp.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bhp.this.q.Uv());
            }
         }
      }
   }

   class GA extends bhp.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bho.Tq.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bhp.this.q.HF());
            var2.q("index", bhp.this.q.HF());
            long var3 = bhp.this.q.zz();
            var2.q("typeState", var3 >> (int)bhp.this.q.gP.Rr);
            var2.q("nullability", var3 & bhp.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhp.this.q.Uv());
         var1.q("hash", bhp.this.q.Uv());
         var1.q("owner", bhp.this.q.Uv());
      }
   }

   class HA extends bhp.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bho.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bhp.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bhp.this.q.Uv());
         var1.q("hash", bhp.this.q.Uv());
         var1.q("shape", bhp.this.q.Uv());
         var1.q("fieldTypes", bhp.this.q.Uv());
      }
   }

   class KZ extends bhp.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bhp.this.q.jq;
         long var1 = bhp.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bhp.this.q.nf() << (int)bhp.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bhp.this.q.q(this.q);
            var7.q("data", var6);
            bhp.this.q.q(var7);
         }

         this.gO = bhp.this.q.jq;
         if (this.q == bho.fn.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bho.ZU.q() && this.q != bho.fn.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bhp.this.q.gO.position(var1);
            bhp.this.q.gO.i32();
            long var2;
            if (bhp.this.q.wF) {
               bhp.this.q.gO.i32();
               var2 = bhp.this.q.gO.i64();
            } else {
               bhp.this.q.gO.i32();
               var2 = bhp.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bhp.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            if (!bhp.this.q.PV) {
               var2.q("flagsAndMaxPosition", bhp.this.q.qa());
            }

            var2.q("kernelScriptIndex", bhp.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bhp.this.q.Uv());
         if (bhp.this.q.io) {
            if (!bhp.this.q.Me) {
               var1.q("resolvedUrl", bhp.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bhp.this.q.Uv());
            var1.q("resolvedUrl", bhp.this.q.Uv());
            var1.q("lineStarts", bhp.this.q.Uv());
            var1.q("constantCoverage", bhp.this.q.Uv());
            var1.q("debugPositions", bhp.this.q.Uv());
            var1.q("kernelProgramInfo", bhp.this.q.Uv());
         }
      }
   }

   class ME extends bhp.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bhp.this.q.jq;
         long var1 = bhp.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bhp.this.q.nf();
            bho var6 = (var4 & 1L) != 0L ? bho.Sz : bho.ZU;
            long var7 = var4 >> 1;
            bew var9 = bhp.this.q.q(var6.q());
            var9.q("length", var7);
            bhp.this.q.q(var9);
         }

         this.gO = bhp.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            long var3 = bhp.this.q.nf();
            long var5 = var3 >> 1;
            bho var7 = (var3 & 1L) != 0L ? bho.Sz : bho.ZU;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bho.ZU) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bhp.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bhp.this.q.zz();
                  var10 |= bhp.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bhp.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bhp.this.q.jq;
         bhp.this.q.Ri = this.oW;
         long var1 = bhp.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bhp.this.q.jq;
         this.q = bhp.this.q.jq;
         var1 = bhp.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bhp.this.q.jq;
      }

      private void Dw() {
         int var1 = bhp.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bhp.this.q.q(bho.Me.q());
         bhp.this.q.q(var3);
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
         bew var3 = bhp.this.q.xK(var1);
         this.q(var3, var2);
         if (!bhp.this.q.io) {
            var3.q("objectPool", bhp.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bhp.this.q.Uv(), bho.nf.toString(), bho.oW.toString(), bho.AB.toString());
         var3.q("exceptionHandlers", bhp.this.q.Uv(), bho.wF.toString());
         var3.q("pcDescriptors", bhp.this.q.Uv(), bho.Ef.toString());
         var3.q("catchEntry", bhp.this.q.Uv());
         if (bhp.this.q.LK) {
            var3.q("compressedStackMaps", bhp.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bhp.this.q.Uv(), bho.of.toString());
         var3.q("codeSourceMap", bhp.this.q.Uv(), bho.cC.toString());
         if (!bhp.this.q.PV && bhp.this.q.LK) {
            var3.q("deoptInfoArray", bhp.this.q.Uv());
            var3.q("staticCallsTargetTable", bhp.this.q.Uv());
         }

         if (!bhp.this.q.Me) {
            var3.q("returnAddressMetadata", bhp.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bhp.this.q.Gf ? bhp.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bhp.this.q.PV) {
            long var3 = bhp.this.q.Rv[2 * bhp.this.q.ZT];
            long var5 = bhp.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bhp.this.q.gP.oQ : 0L;
            long var12 = var9 ? bhp.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bhp.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.IN.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("parent", bhp.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("instructionsImage", null);
            int var3 = 0 | bhp.this.q.qa() << 2;
            var2.q("packedFields", var3);
         }
      }
   }

   class PY extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bhp.this.q.gP());
            var2.q("hostOffsetOrFieldId", bhp.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bhp.this.q.Uv());
         var1.q("owner", bhp.this.q.Uv());
         var1.q("type", bhp.this.q.Uv());
         var1.q("initializerFunction", bhp.this.q.Uv());
      }
   }

   class SG extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("cache", bhp.this.q.Uv());
            var2.q("numInputs", bhp.this.q.io());
            var2.q("numOccupied", bhp.this.q.io());
         }
      }
   }

   class Uz extends bhp.oM {
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
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhp.this.q.Uv());
         var1.q("hashMask", bhp.this.q.Uv());
         var1.q("data", bhp.this.q.Uv());
         var1.q("usedData", bhp.this.q.Uv());
         var1.q("deletedKeys", bhp.this.q.Uv());
      }
   }

   class Vj extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.iu.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bhp.this.q.Uv());
         var1.q("length", bhp.this.q.Uv());
         var1.q("data", bhp.this.q.Uv());
      }
   }

   class Xa extends bhp.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bhp.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bhp.this.q.q(bho.ZA.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bhp.this.q.Hk());
            bhp.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bhp.oM {
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
            bew var2 = bhp.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhp.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bhp.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bhp.this.q.jq;
         long var1 = bhp.this.q.nf();
         this.RF = bhp.this.q.qa();
         this.xK = bhp.this.q.qa();
         bhp.this.q.q(var1, this.q);
         this.gO = bhp.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bhp.this.q.gP.nf;
         int var2 = (int)bhp.this.q.q(this.xK * bhp.this.q.gP.gO, bhp.this.q.gP.gP);
         Long var3 = bhp.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bhp.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bhp.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bhp.this.q.gP.gO)) {
               if (bhp.this.q.RF(var3, var6 / (int)bhp.this.q.gP.gO)) {
                  bhp.this.q.oQ();
               } else {
                  bhp.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bhp.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bhp.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bho.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bhp.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhp.this.q.Uv());
         var1.q("hash", bhp.this.q.Uv());
         var1.q("arguments", bhp.this.q.Uv());
      }
   }

   class eM extends bhp.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bho.fQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bhp.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bhp.this.q.Uv();
            }
         }
      }
   }

   class ej extends bhp.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bho.nq.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bhp.this.q.Uv());
         var1.q("functionTypeArguments", bhp.this.q.Uv());
         var1.q("delayedTypeArguments", bhp.this.q.Uv());
         var1.q("function", bhp.this.q.Uv());
         var1.q("context", bhp.this.q.Uv());
         var1.q("hash", bhp.this.q.Uv());
      }
   }

   class eo extends bhp.oM {
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
            bew var2 = bhp.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bhp.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhp.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bhp.oM {
      @Override
      public void q() {
         this.RF(bho.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhp.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bhp.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.Xo.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bhp.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhp.this.q.Uv());
         var1.q("argsDescriptor", bhp.this.q.Uv());
         var1.q("buckets", bhp.this.q.Uv());
         var1.q("mask", bhp.this.q.Uv());
      }
   }

   class nI extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            if (bhp.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bhp.this.q.Uv());
            }

            var2.q("parentFunction", bhp.this.q.Uv());
            var2.q("closure", bhp.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bhp.this.q.nf());
         }
      }
   }

   class oL extends bhp.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bho.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bhp.this.q.zz();
            var2.q("typeState", var3 >> (int)bhp.this.q.gP.Rr);
            var2.q("nullability", var3 & bhp.this.q.gP.EB);
            var2.q("packedParameterCounts", bhp.this.q.io());
            var2.q("packedTypeParameterCounts", bhp.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhp.this.q.Uv());
         var1.q("hash", bhp.this.q.Uv());
         var1.q("typeParameters", bhp.this.q.Uv());
         var1.q("resultType", bhp.this.q.Uv());
         var1.q("parameterTypes", bhp.this.q.Uv());
         var1.q("namedParameterNames", bhp.this.q.Uv());
      }
   }

   abstract class oM implements bhp.Bu {
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
         this.oW = bhp.this.q.jq;
         long var2 = bhp.this.q.nf();
         bhp.this.q.q(var2, var1);
         this.gO = bhp.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bhp.this.q.jq;
         long var2 = bhp.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bhp.this.q.q(var1);
            long var4 = bhp.this.q.nf();
            var3.q("length", var4);
            bhp.this.q.q(var3);
         });
         this.gO = bhp.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bhp.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bhp.this.q.nf();
            long var1 = bhp.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bhp.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bhp.oM {
      @Override
      public void q() {
         this.RF(bho.wF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            long var3 = bhp.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bhp.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bhp.this.q.gP());
               var9.put("outerTryIndex", bhp.this.q.LK());
               var9.put("needsStacktrace", bhp.this.q.Dw());
               var9.put("hasCatchAll", bhp.this.q.Dw());
               var9.put("isGenerated", bhp.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bhp.oM {
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
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhp.this.q.Uv());
         var1.q("hashMask", bhp.this.q.Uv());
         var1.q("data", bhp.this.q.Uv());
         var1.q("usedData", bhp.this.q.Uv());
         var1.q("deletedKeys", bhp.this.q.Uv());
      }
   }

   class qx extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.Tq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bhp.this.q.Uv());
         var1.q("flags", bhp.this.q.Uv());
         var1.q("bounds", bhp.this.q.Uv());
         var1.q("defaults", bhp.this.q.Uv());
      }
   }

   class ry extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.ui.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bhp.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhp.this.q.Uv());
         var1.q("argsDescriptor", bhp.this.q.Uv());
      }
   }

   class tl extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bhp.this.q.qa());
            var2.q("numImports", bhp.this.q.HF());
            var2.q("loadState", bhp.this.q.JY());
            var2.q("flags", bhp.this.q.zz());
            if (!bhp.this.q.PV && !bhp.this.q.io) {
               var2.q("kernelOffset", bhp.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhp.this.q.Uv());
         var1.q("url", bhp.this.q.Uv());
         var1.q("privateKey", bhp.this.q.Uv());
         var1.q("dictionary", bhp.this.q.Uv());
         var1.q("metadata", bhp.this.q.Uv());
         var1.q("toplevelClass", bhp.this.q.Uv());
         var1.q("usedScripts", bhp.this.q.Uv());
         var1.q("loadingUnit", bhp.this.q.Uv());
         var1.q("imports", bhp.this.q.Uv());
         var1.q("exports", bhp.this.q.Uv());
         if (!bhp.this.q.io) {
            var1.q("dependencies", bhp.this.q.Uv());
            var1.q("kernelProgramInfo", bhp.this.q.Uv());
         }
      }
   }

   class tw extends bhp.oM {
      @Override
      public void q() {
         this.RF(bho.sH.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            long var3 = bhp.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bhp.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            if (!bhp.this.q.PV && !bhp.this.q.io) {
               var2.q("libraryKernelOffset", bhp.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bhp.this.q.Uv());
         var1.q("script", bhp.this.q.Uv());
         if (!bhp.this.q.io) {
            var1.q("libraryKernelData", bhp.this.q.Uv());
         }
      }
   }

   class vb extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            if (!bhp.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bhp.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bhp.this.q.Ri + (int)var3 - 1;
               bew var6 = bhp.this.q.xK(var5);
               if (var6.q().equals(bho.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bhp.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bhp.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bhp.this.q.Uv());
         var1.q("owner", bhp.this.q.Uv());
         var1.q("signature", bhp.this.q.Uv(), bho.CY.toString());
         var1.q("data", bhp.this.q.Uv());
      }
   }

   class vn extends bhp.oM {
      @Override
      public void q() {
         this.q(bho.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bhp.this.q.nf());
            var2.q("callbackKind", bhp.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bhp.this.q.Uv());
         var1.q("cSignature", bhp.this.q.Uv());
         var1.q("callbackTarget", bhp.this.q.Uv());
         var1.q("callbackExceptionalReturn", bhp.this.q.Uv());
      }
   }

   class zJ extends bhp.oM {
      @Override
      public void q() {
         this.RF(bho.Ef.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhp.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhp.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
