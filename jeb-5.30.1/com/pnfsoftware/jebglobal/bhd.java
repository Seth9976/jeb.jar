package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bhd {
   private static final ILogger RF = GlobalLog.getLogger(bhd.class);
   bhf q;

   bhd(bhf var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bhd.Bu q(int var1, boolean var2, boolean var3) {
      bhc var4 = bhc.q(var1);
      if (var1 >= bhc.yW.q() || var1 == bhc.zx.q()) {
         return new bhd.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bhd.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Gf:
               case Ef:
               case cC:
                  return new bhd.KZ(var2, var3, var1);
               case fn:
               case ZU:
               case iu:
                  if (var3) {
                     return new bhd.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Gf:
               Assert.a(!var2);
               return new bhd.zJ();
            case Ef:
               Assert.a(!var2);
               return new bhd.iZ();
            case cC:
               Assert.a(!var2);
               return new bhd.tw();
            case fn:
            case ZU:
            default:
               return null;
            case iu:
               return new bhd.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bhd.CU();
            case gP:
               return new bhd.qx();
            case Ri:
               return new bhd.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bhd.vX();
            case nf:
               Assert.a(!var2);
               return new bhd.vb();
            case za:
               Assert.a(!var2);
               return new bhd.nI();
            case lm:
               Assert.a(!var2);
               return new bhd.vn();
            case zz:
               Assert.a(!var2);
               return new bhd.PY();
            case JY:
               Assert.a(!var2);
               return new bhd.LR();
            case HF:
               Assert.a(!var2);
               return new bhd.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bhd.Nt();
            case KT:
               Assert.a(!var2);
               return new bhd.CI();
            case CE:
               Assert.a(!var2);
               return new bhd.qV();
            case wF:
               Assert.a(!var2);
               return null;
            case If:
               Assert.a(!var2);
               return null;
            case jq:
               Assert.a(!var2);
               return new bhd.ry();
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return new bhd.SG();
            case Bu:
               Assert.a(!var2);
               return new bhd.Nz();
            case eJ:
               Assert.a(!var2);
               return null;
            case YN:
               Assert.a(!var2);
               return null;
            case ZT:
               Assert.a(!var2);
               return null;
            case Wx:
               return new bhd.ct(var2, var3);
            case AB:
               return new bhd.oL(var2, var3);
            case WI:
               return new bhd.GA(var2, var3);
            case lF:
               return new bhd.ej(var2);
            case tW:
               return new bhd.Xa(var2);
            case ZA:
               return new bhd.EE(var2);
            case nv:
            case Lj:
            case LL:
               return null;
            case os:
               Assert.a(!var2);
               return new bhd.Vj();
            case IY:
               Assert.a(!var2);
               return null;
            case YA:
               Assert.a(!var2);
               return null;
            case fw:
               Assert.a(!var2);
               return null;
            case ND:
               throw new RuntimeException();
            case Qu:
               return new bhd.Uz(var2, var1);
            case jh:
               throw new RuntimeException();
            case Jf:
               return new bhd.qa(var2, var1);
            case vC:
            case of:
               return new bhd.eo(var2, var1);
            case Hk:
               return new bhd.Zu(var2, var1);
            case CY:
               return new bhd.HA(var2, var3);
            case PQ:
               return new bhd.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bhd.oM {
      @Override
      public void q() {
         this.RF(bhc.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bhd.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bhd.this.q.LK(var6);
               Assert.a(var8 != bhd.this.q.gP.mI);
               if (var8 == bhd.this.q.gP.Dz) {
                  int var9 = bhd.this.q.oW(var6);
                  if (var9 == bhd.this.q.gP.xW) {
                     long var10 = bhd.this.q.Uv();
                     var7.put("rawObj", var10);
                  } else if (var9 == bhd.this.q.gP.KT) {
                     long var12 = bhd.this.q.za();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bhd.this.q.gP.Gf) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bhd.this.q.gP.jq && var8 != bhd.this.q.gP.ui) {
                  if (var8 != bhd.this.q.gP.TX) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class CU extends bhd.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bhd.this.q.jq;
         long var1 = bhd.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bhd.this.q.q(bhc.oW.q());
            var2.q("id", bhd.this.q.oW());
            bhd.this.q.q(var2);
         });
         this.RF = bhd.this.q.jq;
         this.q(bhc.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bhd.this.q.xK(var6);
               this.q(var8);
               int var9 = bhd.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bhd.this.q.PV && !bhd.this.q.io) {
                  var8.q("kernelOffset", bhd.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bhd.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bhd.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bhd.this.q.za());
               if (!bhd.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bhd.this.q.za());
               var8.q("numNativeFields", bhd.this.q.nf());
               if (!bhd.this.q.PV) {
                  Assert.a(!bhd.this.q.io);
                  var8.q("tokenPos", bhd.this.q.gO());
                  var8.q("endTokenPos", bhd.this.q.gO());
               }

               var8.q("stateBits", bhd.this.q.nf());
               if (bhd.this.q.PV) {
                  if (var7) {
                     bhd.this.q.nf();
                  } else if (!bhd.this.q.gP.q(var9)) {
                     bhd.this.q.TX.put(var9, bhd.this.q.nf());
                  }
               }

               bhd.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhd.this.q.Uv());
         if (!bhd.this.q.Me) {
            var1.q("userName", bhd.this.q.Uv());
         }

         var1.q("functions", bhd.this.q.Uv());
         var1.q("functionsHashTable", bhd.this.q.Uv());
         var1.q("fields", bhd.this.q.Uv());
         var1.q("offsetInWordsToField", bhd.this.q.Uv());
         var1.q("interfaces", bhd.this.q.Uv());
         var1.q("script", bhd.this.q.Uv());
         var1.q("library", bhd.this.q.Uv());
         var1.q("typeParameters", bhd.this.q.Uv());
         var1.q("superType", bhd.this.q.Uv());
         var1.q("constants", bhd.this.q.Uv());
         var1.q("declarationType", bhd.this.q.Uv());
         var1.q("invocationDispatcherCache", bhd.this.q.Uv());
         if (!bhd.this.q.Me || !bhd.this.q.PV) {
            var1.q("directImplementors", bhd.this.q.Uv());
            var1.q("directSubclasses", bhd.this.q.Uv());
         }

         if (!bhd.this.q.PV) {
            var1.q("allocationStub", bhd.this.q.Uv());
            var1.q("dependentCode", bhd.this.q.Uv());
         }
      }
   }

   class EE extends bhd.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bhc.ZA.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bhd.this.q.PV());
         }
      }
   }

   class FL extends bhd.oM {
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
            bew var2 = bhd.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bhd.this.q.HF(this.q);
            var2.q("data", bhd.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bhd.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bhc.Ri.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bhd.this.q.qa());
            var2.q("nullabity", bhd.this.q.nf());
            var2.q("instantiations", bhd.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bhd.this.q.Uv());
            }
         }
      }
   }

   class GA extends bhd.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhc.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bhd.this.q.HF());
            var2.q("index", bhd.this.q.HF());
            long var3 = bhd.this.q.zz();
            var2.q("typeState", var3 >> (int)bhd.this.q.gP.Rr);
            var2.q("nullability", var3 & bhd.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhd.this.q.Uv());
         var1.q("hash", bhd.this.q.Uv());
         var1.q("owner", bhd.this.q.Uv());
      }
   }

   class HA extends bhd.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhc.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bhd.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bhd.this.q.Uv());
         var1.q("hash", bhd.this.q.Uv());
         var1.q("shape", bhd.this.q.Uv());
         var1.q("fieldTypes", bhd.this.q.Uv());
      }
   }

   class KZ extends bhd.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bhd.this.q.jq;
         long var1 = bhd.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bhd.this.q.nf() << (int)bhd.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bhd.this.q.q(this.q);
            var7.q("data", var6);
            bhd.this.q.q(var7);
         }

         this.gO = bhd.this.q.jq;
         if (this.q == bhc.iu.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bhc.fn.q() && this.q != bhc.iu.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bhd.this.q.gO.position(var1);
            bhd.this.q.gO.i32();
            long var2;
            if (bhd.this.q.wF) {
               bhd.this.q.gO.i32();
               var2 = bhd.this.q.gO.i64();
            } else {
               bhd.this.q.gO.i32();
               var2 = bhd.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bhd.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            if (!bhd.this.q.PV) {
               var2.q("flagsAndMaxPosition", bhd.this.q.qa());
            }

            var2.q("kernelScriptIndex", bhd.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bhd.this.q.Uv());
         if (bhd.this.q.io) {
            if (!bhd.this.q.Me) {
               var1.q("resolvedUrl", bhd.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bhd.this.q.Uv());
            var1.q("resolvedUrl", bhd.this.q.Uv());
            var1.q("lineStarts", bhd.this.q.Uv());
            var1.q("constantCoverage", bhd.this.q.Uv());
            var1.q("debugPositions", bhd.this.q.Uv());
            var1.q("kernelProgramInfo", bhd.this.q.Uv());
         }
      }
   }

   class ME extends bhd.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bhd.this.q.jq;
         long var1 = bhd.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bhd.this.q.nf();
            bhc var6 = (var4 & 1L) != 0L ? bhc.ZU : bhc.fn;
            long var7 = var4 >> 1;
            bew var9 = bhd.this.q.q(var6.q());
            var9.q("length", var7);
            bhd.this.q.q(var9);
         }

         this.gO = bhd.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            long var3 = bhd.this.q.nf();
            long var5 = var3 >> 1;
            bhc var7 = (var3 & 1L) != 0L ? bhc.ZU : bhc.fn;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bhc.fn) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bhd.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bhd.this.q.zz();
                  var10 |= bhd.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bhd.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bhd.this.q.jq;
         bhd.this.q.Ri = this.oW;
         long var1 = bhd.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bhd.this.q.jq;
         this.q = bhd.this.q.jq;
         var1 = bhd.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bhd.this.q.jq;
      }

      private void Dw() {
         int var1 = bhd.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bhd.this.q.q(bhc.Me.q());
         bhd.this.q.q(var3);
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
         bew var3 = bhd.this.q.xK(var1);
         this.q(var3, var2);
         if (!bhd.this.q.io) {
            var3.q("objectPool", bhd.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bhd.this.q.Uv(), bhc.nf.toString(), bhc.oW.toString(), bhc.Wx.toString());
         var3.q("exceptionHandlers", bhd.this.q.Uv(), bhc.CE.toString());
         var3.q("pcDescriptors", bhd.this.q.Uv(), bhc.Gf.toString());
         var3.q("catchEntry", bhd.this.q.Uv());
         if (bhd.this.q.LK) {
            var3.q("compressedStackMaps", bhd.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bhd.this.q.Uv(), bhc.vC.toString());
         var3.q("codeSourceMap", bhd.this.q.Uv(), bhc.Ef.toString());
         if (!bhd.this.q.PV && bhd.this.q.LK) {
            var3.q("deoptInfoArray", bhd.this.q.Uv());
            var3.q("staticCallsTargetTable", bhd.this.q.Uv());
         }

         if (!bhd.this.q.Me) {
            var3.q("returnAddressMetadata", bhd.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bhd.this.q.Gf ? bhd.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bhd.this.q.PV) {
            long var3 = bhd.this.q.Rv[2 * bhd.this.q.ZT];
            long var5 = bhd.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bhd.this.q.gP.oQ : 0L;
            long var12 = var9 ? bhd.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bhd.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("parent", bhd.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("instructionsImage", null);
            int var3 = 0 | bhd.this.q.qa() << 2;
            var2.q("packedFields", var3);
         }
      }
   }

   class PY extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bhd.this.q.gP());
            var2.q("hostOffsetOrFieldId", bhd.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bhd.this.q.Uv());
         var1.q("owner", bhd.this.q.Uv());
         var1.q("type", bhd.this.q.Uv());
         var1.q("initializerFunction", bhd.this.q.Uv());
      }
   }

   class SG extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("cache", bhd.this.q.Uv());
            var2.q("numInputs", bhd.this.q.io());
            var2.q("numOccupied", bhd.this.q.io());
         }
      }
   }

   class Uz extends bhd.oM {
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
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhd.this.q.Uv());
         var1.q("hashMask", bhd.this.q.Uv());
         var1.q("data", bhd.this.q.Uv());
         var1.q("usedData", bhd.this.q.Uv());
         var1.q("deletedKeys", bhd.this.q.Uv());
      }
   }

   class Vj extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.os.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bhd.this.q.Uv());
         var1.q("length", bhd.this.q.Uv());
         var1.q("data", bhd.this.q.Uv());
      }
   }

   class Xa extends bhd.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bhd.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bhd.this.q.q(bhc.tW.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bhd.this.q.Hk());
            bhd.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bhd.oM {
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
            bew var2 = bhd.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhd.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bhd.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bhd.this.q.jq;
         long var1 = bhd.this.q.nf();
         this.RF = bhd.this.q.qa();
         this.xK = bhd.this.q.qa();
         bhd.this.q.q(var1, this.q);
         this.gO = bhd.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bhd.this.q.gP.nf;
         int var2 = (int)bhd.this.q.q(this.xK * bhd.this.q.gP.gO, bhd.this.q.gP.gP);
         Long var3 = bhd.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bhd.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bhd.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bhd.this.q.gP.gO)) {
               if (bhd.this.q.RF(var3, var6 / (int)bhd.this.q.gP.gO)) {
                  bhd.this.q.oQ();
               } else {
                  bhd.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bhd.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bhd.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhc.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bhd.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhd.this.q.Uv());
         var1.q("hash", bhd.this.q.Uv());
         var1.q("arguments", bhd.this.q.Uv());
      }
   }

   class eM extends bhd.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bhc.PQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bhd.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bhd.this.q.Uv();
            }
         }
      }
   }

   class ej extends bhd.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bhc.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bhd.this.q.Uv());
         var1.q("functionTypeArguments", bhd.this.q.Uv());
         var1.q("delayedTypeArguments", bhd.this.q.Uv());
         var1.q("function", bhd.this.q.Uv());
         var1.q("context", bhd.this.q.Uv());
         var1.q("hash", bhd.this.q.Uv());
      }
   }

   class eo extends bhd.oM {
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
            bew var2 = bhd.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bhd.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bhd.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bhd.oM {
      @Override
      public void q() {
         this.RF(bhc.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhd.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bhd.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.EB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bhd.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhd.this.q.Uv());
         var1.q("argsDescriptor", bhd.this.q.Uv());
         var1.q("buckets", bhd.this.q.Uv());
         var1.q("mask", bhd.this.q.Uv());
      }
   }

   class nI extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            if (bhd.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bhd.this.q.Uv());
            }

            var2.q("parentFunction", bhd.this.q.Uv());
            var2.q("closure", bhd.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bhd.this.q.nf());
         }
      }
   }

   class oL extends bhd.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bhc.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bhd.this.q.zz();
            var2.q("typeState", var3 >> (int)bhd.this.q.gP.Rr);
            var2.q("nullability", var3 & bhd.this.q.gP.EB);
            var2.q("packedParameterCounts", bhd.this.q.io());
            var2.q("packedTypeParameterCounts", bhd.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bhd.this.q.Uv());
         var1.q("hash", bhd.this.q.Uv());
         var1.q("typeParameters", bhd.this.q.Uv());
         var1.q("resultType", bhd.this.q.Uv());
         var1.q("parameterTypes", bhd.this.q.Uv());
         var1.q("namedParameterNames", bhd.this.q.Uv());
      }
   }

   abstract class oM implements bhd.Bu {
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
         this.oW = bhd.this.q.jq;
         long var2 = bhd.this.q.nf();
         bhd.this.q.q(var2, var1);
         this.gO = bhd.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bhd.this.q.jq;
         long var2 = bhd.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bhd.this.q.q(var1);
            long var4 = bhd.this.q.nf();
            var3.q("length", var4);
            bhd.this.q.q(var3);
         });
         this.gO = bhd.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bhd.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bhd.this.q.nf();
            long var1 = bhd.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bhd.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bhd.oM {
      @Override
      public void q() {
         this.RF(bhc.CE.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            long var3 = bhd.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bhd.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bhd.this.q.gP());
               var9.put("outerTryIndex", bhd.this.q.LK());
               var9.put("needsStacktrace", bhd.this.q.Dw());
               var9.put("hasCatchAll", bhd.this.q.Dw());
               var9.put("isGenerated", bhd.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bhd.oM {
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
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bhd.this.q.Uv());
         var1.q("hashMask", bhd.this.q.Uv());
         var1.q("data", bhd.this.q.Uv());
         var1.q("usedData", bhd.this.q.Uv());
         var1.q("deletedKeys", bhd.this.q.Uv());
      }
   }

   class qx extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.WI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bhd.this.q.Uv());
         var1.q("flags", bhd.this.q.Uv());
         var1.q("bounds", bhd.this.q.Uv());
         var1.q("defaults", bhd.this.q.Uv());
      }
   }

   class ry extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.jq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bhd.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bhd.this.q.Uv());
         var1.q("argsDescriptor", bhd.this.q.Uv());
      }
   }

   class tl extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bhd.this.q.qa());
            var2.q("numImports", bhd.this.q.HF());
            var2.q("loadState", bhd.this.q.JY());
            var2.q("flags", bhd.this.q.zz());
            if (!bhd.this.q.PV && !bhd.this.q.io) {
               var2.q("kernelOffset", bhd.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bhd.this.q.Uv());
         var1.q("url", bhd.this.q.Uv());
         var1.q("privateKey", bhd.this.q.Uv());
         var1.q("dictionary", bhd.this.q.Uv());
         var1.q("metadata", bhd.this.q.Uv());
         var1.q("toplevelClass", bhd.this.q.Uv());
         var1.q("usedScripts", bhd.this.q.Uv());
         var1.q("loadingUnit", bhd.this.q.Uv());
         var1.q("imports", bhd.this.q.Uv());
         var1.q("exports", bhd.this.q.Uv());
         if (!bhd.this.q.io) {
            var1.q("dependencies", bhd.this.q.Uv());
            var1.q("kernelProgramInfo", bhd.this.q.Uv());
         }
      }
   }

   class tw extends bhd.oM {
      @Override
      public void q() {
         this.RF(bhc.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            long var3 = bhd.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bhd.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            if (!bhd.this.q.PV && !bhd.this.q.io) {
               var2.q("libraryKernelOffset", bhd.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bhd.this.q.Uv());
         var1.q("script", bhd.this.q.Uv());
         if (!bhd.this.q.io) {
            var1.q("libraryKernelData", bhd.this.q.Uv());
         }
      }
   }

   class vb extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            if (!bhd.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bhd.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bhd.this.q.Ri + (int)var3 - 1;
               bew var6 = bhd.this.q.xK(var5);
               if (var6.q().equals(bhc.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bhd.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bhd.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bhd.this.q.Uv());
         var1.q("owner", bhd.this.q.Uv());
         var1.q("signature", bhd.this.q.Uv(), bhc.AB.toString());
         var1.q("data", bhd.this.q.Uv());
      }
   }

   class vn extends bhd.oM {
      @Override
      public void q() {
         this.q(bhc.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bhd.this.q.nf());
            var2.q("callbackKind", bhd.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bhd.this.q.Uv());
         var1.q("cSignature", bhd.this.q.Uv());
         var1.q("callbackTarget", bhd.this.q.Uv());
         var1.q("callbackExceptionalReturn", bhd.this.q.Uv());
      }
   }

   class zJ extends bhd.oM {
      @Override
      public void q() {
         this.RF(bhc.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bhd.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bhd.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
