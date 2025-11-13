package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bhh {
   private static final ILogger RF = GlobalLog.getLogger(bhh.class);
   bhj q;

   bhh(bhj var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bhh.Bu q(int var1, boolean var2, boolean var3) {
      bhg var4 = bhg.q(var1);
      if (var1 >= bhg.JF.q() || var1 == bhg.ZT.q()) {
         return new bhh.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bhh.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Ef:
               case cC:
               case sH:
                  return new bhh.KZ(var2, var3, var1);
               case ZU:
               case Sz:
               case fn:
                  if (var3) {
                     return new bhh.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Ef:
               Assert.a(!var2);
               return new bhh.zJ();
            case cC:
               Assert.a(!var2);
               return new bhh.iZ();
            case sH:
               Assert.a(!var2);
               return new bhh.tw();
            case ZU:
            case Sz:
            default:
               return null;
            case fn:
               return new bhh.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bhh.CU();
            case gP:
               return new bhh.qx();
            case GY:
               return new bhh.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bhh.vX();
            case nf:
               Assert.a(!var2);
               return new bhh.vb();
            case za:
               Assert.a(!var2);
               return new bhh.nI();
            case lm:
               Assert.a(!var2);
               return new bhh.vn();
            case zz:
               Assert.a(!var2);
               return new bhh.PY();
            case JY:
               Assert.a(!var2);
               return new bhh.LR();
            case HF:
               Assert.a(!var2);
               return new bhh.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bhh.Nt();
            case Gf:
               Assert.a(!var2);
               return new bhh.CI();
            case wF:
               Assert.a(!var2);
               return new bhh.qV();
            case If:
               Assert.a(!var2);
               return null;
            case Dz:
               Assert.a(!var2);
               return null;
            case ui:
               Assert.a(!var2);
               return new bhh.ry();
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return null;
            case Bu:
               Assert.a(!var2);
               return new bhh.SG();
            case IN:
               Assert.a(!var2);
               return new bhh.Nz();
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
               return new bhh.ct(var2, var3);
            case CY:
               return new bhh.oL(var2, var3);
            case Tq:
               return new bhh.GA(var2, var3);
            case nq:
               return new bhh.ej(var2);
            case ZA:
               return new bhh.Xa(var2);
            case Ov:
               return new bhh.EE(var2);
            case LL:
            case nv:
            case PQ:
               return null;
            case iu:
               Assert.a(!var2);
               return new bhh.Vj();
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
               return new bhh.Uz(var2, var1);
            case Jf:
               throw new RuntimeException();
            case vC:
               return new bhh.qa(var2, var1);
            case of:
            case os:
               return new bhh.eo(var2, var1);
            case Hk:
               return new bhh.Zu(var2, var1);
            case WI:
               return new bhh.HA(var2, var3);
            case fQ:
               return new bhh.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bhh.oM {
      @Override
      public void q() {
         this.RF(bhg.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bhh.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bhh.this.q.LK(var6);
               Assert.a(var8 != bhh.this.q.gP.mI);
               if (var8 == bhh.this.q.gP.Dz) {
                  int var9 = bhh.this.q.oW(var6);
                  if (var9 == bhh.this.q.gP.xW) {
                     long var10 = bhh.this.q.Uv();
                     var7.put("rawObj", var10);
                  } else if (var9 == bhh.this.q.gP.KT) {
                     long var12 = bhh.this.q.za();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bhh.this.q.gP.Gf) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bhh.this.q.gP.jq && var8 != bhh.this.q.gP.ui) {
                  if (var8 != bhh.this.q.gP.TX) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class CU extends bhh.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bhh.this.q.jq;
         long var1 = bhh.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bhh.this.q.q(bhg.oW.q());
            var2.q("id", bhh.this.q.oW());
            bhh.this.q.q(var2);
         });
         this.RF = bhh.this.q.jq;
         this.q(bhg.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bhh.this.q.xK(var6);
               this.q(var8);
               int var9 = bhh.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bhh.this.q.PV && !bhh.this.q.io) {
                  var8.q("kernelOffset", bhh.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bhh.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bhh.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bhh.this.q.za());
               if (!bhh.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bhh.this.q.za());
               var8.q("numNativeFields", bhh.this.q.nf());
               if (!bhh.this.q.PV) {
                  Assert.a(!bhh.this.q.io);
                  var8.q("tokenPos", bhh.this.q.gO());
                  var8.q("endTokenPos", bhh.this.q.gO());
               }

               var8.q("stateBits", bhh.this.q.nf());
               if (bhh.this.q.PV) {
                  if (var7) {
                     bhh.this.q.nf();
                  } else if (!bhh.this.q.gP.q(var9)) {
                     bhh.this.q.TX.put(var9, bhh.this.q.nf());
                  }
               }

               bhh.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhh.this.q.Uv());
         if (!bhh.this.q.Me) {
            var1.q("userName", bhh.this.q.Uv());
         }

         var1.q("functions", bhh.this.q.Uv());
         var1.q("functionsHashTable", bhh.this.q.Uv());
         var1.q("fields", bhh.this.q.Uv());
         var1.q("offsetInWordsToField", bhh.this.q.Uv());
         var1.q("interfaces", bhh.this.q.Uv());
         var1.q("script", bhh.this.q.Uv());
         var1.q("library", bhh.this.q.Uv());
         var1.q("typeParameters", bhh.this.q.Uv());
         var1.q("superType", bhh.this.q.Uv());
         var1.q("constants", bhh.this.q.Uv());
         var1.q("declarationType", bhh.this.q.Uv());
         var1.q("invocationDispatcherCache", bhh.this.q.Uv());
         if (!bhh.this.q.Me || !bhh.this.q.PV) {
            var1.q("directImplementors", bhh.this.q.Uv());
            var1.q("directSubclasses", bhh.this.q.Uv());
         }

         if (!bhh.this.q.PV) {
            var1.q("allocationStub", bhh.this.q.Uv());
            var1.q("dependentCode", bhh.this.q.Uv());
         }
      }
   }

   class EE extends bhh.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bhg.Ov.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bhh.this.q.PV());
         }
      }
   }

   class FL extends bhh.oM {
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
            bew var2 = bhh.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bhh.this.q.HF(this.q);
            var2.q("data", bhh.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bhh.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bhg.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bhh.this.q.qa());
            var2.q("nullabity", bhh.this.q.nf());
            var2.q("instantiations", bhh.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bhh.this.q.Uv());
            }
         }
      }
   }

   class GA extends bhh.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhg.Tq.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bhh.this.q.HF());
            var2.q("index", bhh.this.q.HF());
            long var3 = bhh.this.q.zz();
            var2.q("typeState", var3 >> (int)bhh.this.q.gP.Rr);
            var2.q("nullability", var3 & bhh.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhh.this.q.Uv());
         var1.q("hash", bhh.this.q.Uv());
         var1.q("owner", bhh.this.q.Uv());
      }
   }

   class HA extends bhh.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhg.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bhh.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bhh.this.q.Uv());
         var1.q("hash", bhh.this.q.Uv());
         var1.q("shape", bhh.this.q.Uv());
         var1.q("fieldTypes", bhh.this.q.Uv());
      }
   }

   class KZ extends bhh.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bhh.this.q.jq;
         long var1 = bhh.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bhh.this.q.nf() << (int)bhh.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bhh.this.q.q(this.q);
            var7.q("data", var6);
            bhh.this.q.q(var7);
         }

         this.gO = bhh.this.q.jq;
         if (this.q == bhg.fn.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bhg.ZU.q() && this.q != bhg.fn.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bhh.this.q.gO.position(var1);
            bhh.this.q.gO.i32();
            long var2;
            if (bhh.this.q.wF) {
               bhh.this.q.gO.i32();
               var2 = bhh.this.q.gO.i64();
            } else {
               bhh.this.q.gO.i32();
               var2 = bhh.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bhh.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            if (!bhh.this.q.PV) {
               var2.q("flagsAndMaxPosition", bhh.this.q.qa());
            }

            var2.q("kernelScriptIndex", bhh.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bhh.this.q.Uv());
         if (bhh.this.q.io) {
            if (!bhh.this.q.Me) {
               var1.q("resolvedUrl", bhh.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bhh.this.q.Uv());
            var1.q("resolvedUrl", bhh.this.q.Uv());
            var1.q("lineStarts", bhh.this.q.Uv());
            var1.q("constantCoverage", bhh.this.q.Uv());
            var1.q("debugPositions", bhh.this.q.Uv());
            var1.q("kernelProgramInfo", bhh.this.q.Uv());
         }
      }
   }

   class ME extends bhh.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bhh.this.q.jq;
         long var1 = bhh.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bhh.this.q.nf();
            bhg var6 = (var4 & 1L) != 0L ? bhg.Sz : bhg.ZU;
            long var7 = var4 >> 1;
            bew var9 = bhh.this.q.q(var6.q());
            var9.q("length", var7);
            bhh.this.q.q(var9);
         }

         this.gO = bhh.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            long var3 = bhh.this.q.nf();
            long var5 = var3 >> 1;
            bhg var7 = (var3 & 1L) != 0L ? bhg.Sz : bhg.ZU;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bhg.ZU) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bhh.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bhh.this.q.zz();
                  var10 |= bhh.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bhh.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bhh.this.q.jq;
         bhh.this.q.Ri = this.oW;
         long var1 = bhh.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bhh.this.q.jq;
         this.q = bhh.this.q.jq;
         var1 = bhh.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bhh.this.q.jq;
      }

      private void Dw() {
         int var1 = bhh.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bhh.this.q.q(bhg.Me.q());
         bhh.this.q.q(var3);
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
         bew var3 = bhh.this.q.xK(var1);
         this.q(var3, var2);
         if (!bhh.this.q.io) {
            var3.q("objectPool", bhh.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bhh.this.q.Uv(), bhg.nf.toString(), bhg.oW.toString(), bhg.AB.toString());
         var3.q("exceptionHandlers", bhh.this.q.Uv(), bhg.wF.toString());
         var3.q("pcDescriptors", bhh.this.q.Uv(), bhg.Ef.toString());
         var3.q("catchEntry", bhh.this.q.Uv());
         if (bhh.this.q.LK) {
            var3.q("compressedStackMaps", bhh.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bhh.this.q.Uv(), bhg.of.toString());
         var3.q("codeSourceMap", bhh.this.q.Uv(), bhg.cC.toString());
         if (!bhh.this.q.PV && bhh.this.q.LK) {
            var3.q("deoptInfoArray", bhh.this.q.Uv());
            var3.q("staticCallsTargetTable", bhh.this.q.Uv());
         }

         if (!bhh.this.q.Me) {
            var3.q("returnAddressMetadata", bhh.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bhh.this.q.Gf ? bhh.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bhh.this.q.PV) {
            long var3 = bhh.this.q.Rv[2 * bhh.this.q.ZT];
            long var5 = bhh.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bhh.this.q.gP.oQ : 0L;
            long var12 = var9 ? bhh.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bhh.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.IN.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("parent", bhh.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("instructionsImage", null);
            int var3 = 0 | bhh.this.q.qa() << 2;
            var2.q("packedFields", var3);
         }
      }
   }

   class PY extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bhh.this.q.gP());
            var2.q("hostOffsetOrFieldId", bhh.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bhh.this.q.Uv());
         var1.q("owner", bhh.this.q.Uv());
         var1.q("type", bhh.this.q.Uv());
         var1.q("initializerFunction", bhh.this.q.Uv());
      }
   }

   class SG extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("cache", bhh.this.q.Uv());
            var2.q("numInputs", bhh.this.q.io());
            var2.q("numOccupied", bhh.this.q.io());
         }
      }
   }

   class Uz extends bhh.oM {
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
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhh.this.q.Uv());
         var1.q("hashMask", bhh.this.q.Uv());
         var1.q("data", bhh.this.q.Uv());
         var1.q("usedData", bhh.this.q.Uv());
         var1.q("deletedKeys", bhh.this.q.Uv());
      }
   }

   class Vj extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.iu.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bhh.this.q.Uv());
         var1.q("length", bhh.this.q.Uv());
         var1.q("data", bhh.this.q.Uv());
      }
   }

   class Xa extends bhh.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bhh.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bhh.this.q.q(bhg.ZA.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bhh.this.q.Hk());
            bhh.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bhh.oM {
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
            bew var2 = bhh.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhh.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bhh.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bhh.this.q.jq;
         long var1 = bhh.this.q.nf();
         this.RF = bhh.this.q.qa();
         this.xK = bhh.this.q.qa();
         bhh.this.q.q(var1, this.q);
         this.gO = bhh.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bhh.this.q.gP.nf;
         int var2 = (int)bhh.this.q.q(this.xK * bhh.this.q.gP.gO, bhh.this.q.gP.gP);
         Long var3 = bhh.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bhh.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bhh.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bhh.this.q.gP.gO)) {
               if (bhh.this.q.RF(var3, var6 / (int)bhh.this.q.gP.gO)) {
                  bhh.this.q.oQ();
               } else {
                  bhh.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bhh.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bhh.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhg.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bhh.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhh.this.q.Uv());
         var1.q("hash", bhh.this.q.Uv());
         var1.q("arguments", bhh.this.q.Uv());
      }
   }

   class eM extends bhh.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bhg.fQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bhh.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bhh.this.q.Uv();
            }
         }
      }
   }

   class ej extends bhh.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bhg.nq.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bhh.this.q.Uv());
         var1.q("functionTypeArguments", bhh.this.q.Uv());
         var1.q("delayedTypeArguments", bhh.this.q.Uv());
         var1.q("function", bhh.this.q.Uv());
         var1.q("context", bhh.this.q.Uv());
         var1.q("hash", bhh.this.q.Uv());
      }
   }

   class eo extends bhh.oM {
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
            bew var2 = bhh.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bhh.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhh.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bhh.oM {
      @Override
      public void q() {
         this.RF(bhg.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhh.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bhh.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.Xo.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bhh.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhh.this.q.Uv());
         var1.q("argsDescriptor", bhh.this.q.Uv());
         var1.q("buckets", bhh.this.q.Uv());
         var1.q("mask", bhh.this.q.Uv());
      }
   }

   class nI extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            if (bhh.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bhh.this.q.Uv());
            }

            var2.q("parentFunction", bhh.this.q.Uv());
            var2.q("closure", bhh.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bhh.this.q.nf());
         }
      }
   }

   class oL extends bhh.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhg.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bhh.this.q.zz();
            var2.q("typeState", var3 >> (int)bhh.this.q.gP.Rr);
            var2.q("nullability", var3 & bhh.this.q.gP.EB);
            var2.q("packedParameterCounts", bhh.this.q.io());
            var2.q("packedTypeParameterCounts", bhh.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhh.this.q.Uv());
         var1.q("hash", bhh.this.q.Uv());
         var1.q("typeParameters", bhh.this.q.Uv());
         var1.q("resultType", bhh.this.q.Uv());
         var1.q("parameterTypes", bhh.this.q.Uv());
         var1.q("namedParameterNames", bhh.this.q.Uv());
      }
   }

   abstract class oM implements bhh.Bu {
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
         this.oW = bhh.this.q.jq;
         long var2 = bhh.this.q.nf();
         bhh.this.q.q(var2, var1);
         this.gO = bhh.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bhh.this.q.jq;
         long var2 = bhh.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bhh.this.q.q(var1);
            long var4 = bhh.this.q.nf();
            var3.q("length", var4);
            bhh.this.q.q(var3);
         });
         this.gO = bhh.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bhh.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bhh.this.q.nf();
            long var1 = bhh.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bhh.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bhh.oM {
      @Override
      public void q() {
         this.RF(bhg.wF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            long var3 = bhh.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bhh.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bhh.this.q.gP());
               var9.put("outerTryIndex", bhh.this.q.LK());
               var9.put("needsStacktrace", bhh.this.q.Dw());
               var9.put("hasCatchAll", bhh.this.q.Dw());
               var9.put("isGenerated", bhh.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bhh.oM {
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
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhh.this.q.Uv());
         var1.q("hashMask", bhh.this.q.Uv());
         var1.q("data", bhh.this.q.Uv());
         var1.q("usedData", bhh.this.q.Uv());
         var1.q("deletedKeys", bhh.this.q.Uv());
      }
   }

   class qx extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.Tq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bhh.this.q.Uv());
         var1.q("flags", bhh.this.q.Uv());
         var1.q("bounds", bhh.this.q.Uv());
         var1.q("defaults", bhh.this.q.Uv());
      }
   }

   class ry extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.ui.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bhh.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhh.this.q.Uv());
         var1.q("argsDescriptor", bhh.this.q.Uv());
      }
   }

   class tl extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bhh.this.q.qa());
            var2.q("numImports", bhh.this.q.HF());
            var2.q("loadState", bhh.this.q.JY());
            var2.q("flags", bhh.this.q.zz());
            if (!bhh.this.q.PV && !bhh.this.q.io) {
               var2.q("kernelOffset", bhh.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhh.this.q.Uv());
         var1.q("url", bhh.this.q.Uv());
         var1.q("privateKey", bhh.this.q.Uv());
         var1.q("dictionary", bhh.this.q.Uv());
         var1.q("metadata", bhh.this.q.Uv());
         var1.q("toplevelClass", bhh.this.q.Uv());
         var1.q("usedScripts", bhh.this.q.Uv());
         var1.q("loadingUnit", bhh.this.q.Uv());
         var1.q("imports", bhh.this.q.Uv());
         var1.q("exports", bhh.this.q.Uv());
         if (!bhh.this.q.io) {
            var1.q("dependencies", bhh.this.q.Uv());
            var1.q("kernelProgramInfo", bhh.this.q.Uv());
         }
      }
   }

   class tw extends bhh.oM {
      @Override
      public void q() {
         this.RF(bhg.sH.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            long var3 = bhh.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bhh.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            if (!bhh.this.q.PV && !bhh.this.q.io) {
               var2.q("libraryKernelOffset", bhh.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bhh.this.q.Uv());
         var1.q("script", bhh.this.q.Uv());
         if (!bhh.this.q.io) {
            var1.q("libraryKernelData", bhh.this.q.Uv());
         }
      }
   }

   class vb extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            if (!bhh.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bhh.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bhh.this.q.Ri + (int)var3 - 1;
               bew var6 = bhh.this.q.xK(var5);
               if (var6.q().equals(bhg.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bhh.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bhh.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bhh.this.q.Uv());
         var1.q("owner", bhh.this.q.Uv());
         var1.q("signature", bhh.this.q.Uv(), bhg.CY.toString());
         var1.q("data", bhh.this.q.Uv());
      }
   }

   class vn extends bhh.oM {
      @Override
      public void q() {
         this.q(bhg.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bhh.this.q.nf());
            var2.q("callbackKind", bhh.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bhh.this.q.Uv());
         var1.q("cSignature", bhh.this.q.Uv());
         var1.q("callbackTarget", bhh.this.q.Uv());
         var1.q("callbackExceptionalReturn", bhh.this.q.Uv());
      }
   }

   class zJ extends bhh.oM {
      @Override
      public void q() {
         this.RF(bhg.Ef.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhh.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhh.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
