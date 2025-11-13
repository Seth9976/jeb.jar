package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgf {
   private static final ILogger RF = GlobalLog.getLogger(bgf.class);
   bgh q;

   bgf(bgh var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgf.Bu q(int var1, boolean var2, boolean var3) {
      bge var4 = bge.q(var1);
      if (var1 >= bge.uz.q() || var1 == bge.Rv.q()) {
         return new bgf.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgf.qx(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case KT:
               case Gf:
               case Ef:
                  return new bgf.KZ(var2, var3, var1);
               case fn:
               case ZU:
               case iu:
                  if (var3) {
                     return new bgf.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case KT:
               Assert.a(!var2);
               return new bgf.zJ();
            case Gf:
               Assert.a(!var2);
               return new bgf.iZ();
            case Ef:
               Assert.a(!var2);
               return new bgf.tw();
            case fn:
            case ZU:
            default:
               return null;
            case iu:
               return new bgf.LR(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bgf.CU();
            case gP:
               return new bgf.ct();
            case ZT:
               return new bgf.ME(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bgf.vX();
            case nf:
               Assert.a(!var2);
               return new bgf.vb();
            case za:
               Assert.a(!var2);
               return new bgf.nI();
            case lm:
               Assert.a(!var2);
               return new bgf.vn();
            case zz:
               Assert.a(!var2);
               return new bgf.PY();
            case JY:
               Assert.a(!var2);
               return new bgf.eM();
            case HF:
               Assert.a(!var2);
               return new bgf.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Hk:
               Assert.a(!var2);
               return new bgf.Nt();
            case xW:
               Assert.a(!var2);
               return new bgf.CI();
            case sH:
               Assert.a(!var2);
               return new bgf.qV();
            case CE:
               Assert.a(!var2);
               return null;
            case wF:
               Assert.a(!var2);
               return null;
            case mI:
               Assert.a(!var2);
               return new bgf.FL();
            case TX:
               Assert.a(!var2);
               return null;
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return new bgf.qa();
            case Xo:
               Assert.a(!var2);
               return new bgf.Nz();
            case rL:
               Assert.a(!var2);
               return null;
            case eJ:
               Assert.a(!var2);
               return null;
            case zx:
               Assert.a(!var2);
               return null;
            case GY:
               return new bgf.SG(var2, var3);
            case Wx:
               return new bgf.oL(var2, var3);
            case CY:
               Assert.a(!var2);
               return new bgf.GA();
            case WI:
               return new bgf.Fw(var2, var3);
            case lF:
               return new bgf.ej(var2);
            case tW:
               return new bgf.Xa(var2);
            case ZA:
               return new bgf.EE(var2);
            case os:
               Assert.a(!var2);
               return new bgf.Vj();
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
               return new bgf.Uz(var2, var1);
            case jh:
               throw new RuntimeException();
            case Jf:
               return new bgf.HA(var2, var1);
            case vC:
            case of:
               return new bgf.eo(var2, var1);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgf.oM {
      @Override
      public void q() {
         this.RF(bge.xW.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgf.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bgf.this.q.oW(var6);
               if (var8 == bgf.this.q.gP.xW) {
                  var7.put("rawObj", bgf.this.q.Uv());
               } else if (var8 == bgf.this.q.gP.KT) {
                  var7.put("rawValue", bgf.this.q.za());
               } else if (var8 == bgf.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bgf.this.q.gP.sH && var8 != bgf.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bgf.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class CU extends bgf.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgf.this.q.jq;
         long var1 = bgf.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgf.this.q.q(bge.oW.q());
            var2.q("id", bgf.this.q.oW());
            bgf.this.q.q(var2);
         });
         this.RF = bgf.this.q.jq;
         this.q(bge.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgf.this.q.xK(var6);
               this.q(var8);
               int var9 = bgf.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgf.this.q.PV && !bgf.this.q.io) {
                  var8.q("kernelOffset", bgf.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgf.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgf.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgf.this.q.za());
               if (!bgf.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgf.this.q.za());
               var8.q("numNativeFields", bgf.this.q.nf());
               if (!bgf.this.q.PV) {
                  Assert.a(!bgf.this.q.io);
                  var8.q("tokenPos", bgf.this.q.gO());
                  var8.q("endTokenPos", bgf.this.q.gO());
               }

               var8.q("stateBits", bgf.this.q.nf());
               if (bgf.this.q.PV) {
                  if (var7) {
                     bgf.this.q.nf();
                  } else if (!bgf.this.q.gP.q(var9)) {
                     bgf.this.q.TX.put(var9, bgf.this.q.nf());
                  }
               }

               bgf.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgf.this.q.Uv());
         if (!bgf.this.q.Me) {
            var1.q("userName", bgf.this.q.Uv());
         }

         var1.q("functions", bgf.this.q.Uv());
         var1.q("functionsHashTable", bgf.this.q.Uv());
         var1.q("fields", bgf.this.q.Uv());
         var1.q("offsetInWordsToField", bgf.this.q.Uv());
         var1.q("interfaces", bgf.this.q.Uv());
         var1.q("script", bgf.this.q.Uv());
         var1.q("library", bgf.this.q.Uv());
         var1.q("typeParameters", bgf.this.q.Uv());
         var1.q("superType", bgf.this.q.Uv());
         var1.q("constants", bgf.this.q.Uv());
         var1.q("declarationType", bgf.this.q.Uv());
         var1.q("invocationDispatcherCache", bgf.this.q.Uv());
         if (!bgf.this.q.Me || !bgf.this.q.PV) {
            var1.q("directImplementors", bgf.this.q.Uv());
            var1.q("directSubclasses", bgf.this.q.Uv());
         }

         if (!bgf.this.q.PV) {
            var1.q("allocationStub", bgf.this.q.Uv());
            var1.q("dependentCode", bgf.this.q.Uv());
         }
      }
   }

   class EE extends bgf.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bge.ZA.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgf.this.q.za());
         }
      }
   }

   class FL extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.mI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgf.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgf.this.q.Uv());
         var1.q("argsDescriptor", bgf.this.q.Uv());
      }
   }

   class Fw extends bgf.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bge.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bgf.this.q.qa());
            var2.q("base", bgf.this.q.zz());
            var2.q("index", bgf.this.q.zz());
            long var3 = bgf.this.q.zz();
            var2.q("typeState", var3 >> (int)bgf.this.q.gP.Rr);
            var2.q("nullability", var3 & bgf.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgf.this.q.Uv());
         var1.q("hash", bgf.this.q.Uv());
         var1.q("bound", bgf.this.q.Uv());
      }
   }

   class GA extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.CY.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgf.this.q.Uv());
         var1.q("type", bgf.this.q.Uv());
      }
   }

   class HA extends bgf.oM {
      int q;

      HA(boolean var2, int var3) {
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
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgf.this.q.Uv());
         var1.q("hashMask", bgf.this.q.Uv());
         var1.q("data", bgf.this.q.Uv());
         var1.q("usedData", bgf.this.q.Uv());
         var1.q("deletedKeys", bgf.this.q.Uv());
      }
   }

   class KZ extends bgf.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgf.this.q.jq;
         long var1 = bgf.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgf.this.q.nf() << (int)bgf.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgf.this.q.q(this.q);
            var7.q("data", var6);
            bgf.this.q.q(var7);
         }

         this.gO = bgf.this.q.jq;
         if (this.q == bge.iu.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bge.fn.q() && this.q != bge.iu.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgf.this.q.gO.position(var1);
            bgf.this.q.gO.i32();
            long var2;
            if (bgf.this.q.wF) {
               bgf.this.q.gO.i32();
               var2 = bgf.this.q.gO.i64();
            } else {
               bgf.this.q.gO.i32();
               var2 = bgf.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgf.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bgf.oM {
      LR(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgf.this.q.jq;
         long var1 = bgf.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgf.this.q.nf();
            bge var6 = (var4 & 1L) != 0L ? bge.ZU : bge.fn;
            long var7 = var4 >> 1;
            bew var9 = bgf.this.q.q(var6.q());
            var9.q("length", var7);
            bgf.this.q.q(var9);
         }

         this.gO = bgf.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            long var3 = bgf.this.q.nf();
            long var5 = var3 >> 1;
            bge var7 = (var3 & 1L) != 0L ? bge.ZU : bge.fn;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bge.fn) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgf.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgf.this.q.zz();
                  var10 |= bgf.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class ME extends bgf.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bge.ZT.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgf.this.q.lm());
            var2.q("nullabity", bgf.this.q.nf());
            var2.q("instantiations", bgf.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgf.this.q.Uv());
            }
         }
      }
   }

   class Nt extends bgf.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgf.this.q.jq;
         bgf.this.q.Ri = this.oW;
         long var1 = bgf.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgf.this.q.jq;
         this.q = bgf.this.q.jq;
         var1 = bgf.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgf.this.q.jq;
      }

      private void Dw() {
         int var1 = bgf.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgf.this.q.q(bge.Hk.q());
         bgf.this.q.q(var3);
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
         bew var3 = bgf.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgf.this.q.io) {
            var3.q("objectPool", bgf.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgf.this.q.Uv(), bge.nf.toString(), bge.oW.toString(), bge.GY.toString());
         var3.q("exceptionHandlers", bgf.this.q.Uv(), bge.sH.toString());
         var3.q("pcDescriptors", bgf.this.q.Uv(), bge.KT.toString());
         var3.q("catchEntry", bgf.this.q.Uv());
         if (bgf.this.q.LK) {
            var3.q("compressedStackMaps", bgf.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgf.this.q.Uv(), bge.vC.toString());
         var3.q("codeSourceMap", bgf.this.q.Uv(), bge.Gf.toString());
         if (!bgf.this.q.PV && bgf.this.q.LK) {
            var3.q("deoptInfoArray", bgf.this.q.Uv());
            var3.q("staticCallsTargetTable", bgf.this.q.Uv());
         }

         if (!bgf.this.q.Me) {
            var3.q("returnAddressMetadata", bgf.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgf.this.q.Gf ? bgf.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgf.this.q.PV) {
            long var3 = bgf.this.q.Rv[2 * bgf.this.q.ZT];
            long var5 = bgf.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgf.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgf.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgf.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("parent", bgf.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgf.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class PY extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgf.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgf.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgf.this.q.Uv());
         var1.q("owner", bgf.this.q.Uv());
         var1.q("type", bgf.this.q.Uv());
         var1.q("initializerFunction", bgf.this.q.Uv());
      }
   }

   class SG extends bgf.oM {
      SG(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bge.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bgf.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgf.this.q.Uv());
         var1.q("arguments", bgf.this.q.Uv());
         var1.q("hash", bgf.this.q.Uv());
      }
   }

   class Uz extends bgf.oM {
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
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgf.this.q.Uv());
         var1.q("hashMask", bgf.this.q.Uv());
         var1.q("data", bgf.this.q.Uv());
         var1.q("usedData", bgf.this.q.Uv());
         var1.q("deletedKeys", bgf.this.q.Uv());
      }
   }

   class Vj extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.os.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgf.this.q.Uv());
         var1.q("length", bgf.this.q.Uv());
         var1.q("data", bgf.this.q.Uv());
      }
   }

   class Xa extends bgf.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgf.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgf.this.q.q(bge.tW.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgf.this.q.za());
            bgf.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class bK extends bgf.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgf.this.q.jq;
         long var1 = bgf.this.q.nf();
         this.RF = bgf.this.q.qa();
         this.xK = bgf.this.q.qa();
         bgf.this.q.q(var1, this.q);
         this.gO = bgf.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgf.this.q.gP.nf;
         int var2 = (int)bgf.this.q.q(this.xK * bgf.this.q.gP.gO, bgf.this.q.gP.gP);
         Long var3 = bgf.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgf.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgf.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgf.this.q.gP.gO)) {
               if (bgf.this.q.RF(var3, var6 / (int)bgf.this.q.gP.gO)) {
                  bgf.this.q.oQ();
               } else {
                  bgf.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgf.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.WI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgf.this.q.Uv());
         var1.q("flags", bgf.this.q.Uv());
         var1.q("bounds", bgf.this.q.Uv());
         var1.q("defaults", bgf.this.q.Uv());
      }
   }

   class eM extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            if (!bgf.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgf.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgf.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgf.this.q.Uv());
         if (bgf.this.q.io) {
            if (!bgf.this.q.Me) {
               var1.q("resolvedUrl", bgf.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgf.this.q.Uv());
            var1.q("resolvedUrl", bgf.this.q.Uv());
            var1.q("lineStarts", bgf.this.q.Uv());
            var1.q("constantCoverage", bgf.this.q.Uv());
            var1.q("debugPositions", bgf.this.q.Uv());
            var1.q("kernelProgramInfo", bgf.this.q.Uv());
         }
      }
   }

   class ej extends bgf.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bge.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgf.this.q.Uv());
         var1.q("functionTypeArguments", bgf.this.q.Uv());
         var1.q("delayedTypeArguments", bgf.this.q.Uv());
         var1.q("function", bgf.this.q.Uv());
         var1.q("context", bgf.this.q.Uv());
         var1.q("hash", bgf.this.q.Uv());
      }
   }

   class eo extends bgf.oM {
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
            bew var2 = bgf.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgf.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgf.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgf.oM {
      @Override
      public void q() {
         this.RF(bge.Gf.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgf.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class kY extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.Rr.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgf.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgf.this.q.Uv());
         var1.q("argsDescriptor", bgf.this.q.Uv());
         var1.q("buckets", bgf.this.q.Uv());
         var1.q("mask", bgf.this.q.Uv());
      }
   }

   class nI extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            if (bgf.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgf.this.q.Uv());
            }

            var2.q("parentFunction", bgf.this.q.Uv());
            var2.q("closure", bgf.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgf.this.q.nf());
         }
      }
   }

   class oL extends bgf.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bge.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgf.this.q.zz();
            var2.q("typeState", var3 >> (int)bgf.this.q.gP.Rr);
            var2.q("nullability", var3 & bgf.this.q.gP.EB);
            var2.q("packedParameterCounts", bgf.this.q.io());
            var2.q("packedTypeParameterCounts", bgf.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgf.this.q.Uv());
         var1.q("typeParameters", bgf.this.q.Uv());
         var1.q("resultType", bgf.this.q.Uv());
         var1.q("parameterTypes", bgf.this.q.Uv());
         var1.q("namedParameterNames", bgf.this.q.Uv());
         var1.q("hash", bgf.this.q.Uv());
      }
   }

   abstract class oM implements bgf.Bu {
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
         this.oW = bgf.this.q.jq;
         long var2 = bgf.this.q.nf();
         bgf.this.q.q(var2, var1);
         this.gO = bgf.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgf.this.q.jq;
         long var2 = bgf.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgf.this.q.q(var1);
            long var4 = bgf.this.q.nf();
            var3.q("length", var4);
            bgf.this.q.q(var3);
         });
         this.gO = bgf.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgf.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgf.this.q.nf();
            long var1 = bgf.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgf.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgf.oM {
      @Override
      public void q() {
         this.RF(bge.sH.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            long var3 = bgf.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgf.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgf.this.q.gP());
               var9.put("outerTryIndex", bgf.this.q.LK());
               var9.put("needsStacktrace", bgf.this.q.Dw());
               var9.put("hasCatchAll", bgf.this.q.Dw());
               var9.put("isGenerated", bgf.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.EB.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            var2.q("cache", bgf.this.q.Uv());
         }
      }
   }

   class qx extends bgf.oM {
      int q;

      public qx(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgf.this.q.HF(this.q);
            var2.q("data", bgf.this.q.Dw(var4));
         }
      }
   }

   class ry extends bgf.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class tl extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgf.this.q.qa());
            var2.q("numImports", bgf.this.q.HF());
            var2.q("loadState", bgf.this.q.JY());
            var2.q("flags", bgf.this.q.zz());
            if (!bgf.this.q.PV && !bgf.this.q.io) {
               var2.q("kernelOffset", bgf.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgf.this.q.Uv());
         var1.q("url", bgf.this.q.Uv());
         var1.q("privateKey", bgf.this.q.Uv());
         var1.q("dictionary", bgf.this.q.Uv());
         var1.q("metadata", bgf.this.q.Uv());
         var1.q("toplevelClass", bgf.this.q.Uv());
         var1.q("usedScripts", bgf.this.q.Uv());
         var1.q("loadingUnit", bgf.this.q.Uv());
         var1.q("imports", bgf.this.q.Uv());
         var1.q("exports", bgf.this.q.Uv());
         if (!bgf.this.q.io) {
            var1.q("dependencies", bgf.this.q.Uv());
            var1.q("kernelData", bgf.this.q.Uv());
         }
      }
   }

   class tw extends bgf.oM {
      @Override
      public void q() {
         this.RF(bge.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            long var3 = bgf.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgf.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            if (!bgf.this.q.PV && !bgf.this.q.io) {
               var2.q("libraryKernelOffset", bgf.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bgf.this.q.Uv());
         var1.q("originClass", bgf.this.q.Uv());
         var1.q("script", bgf.this.q.Uv());
         if (!bgf.this.q.io) {
            var1.q("libraryKernelData", bgf.this.q.Uv());
         }
      }
   }

   class vb extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            if (!bgf.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgf.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgf.this.q.Ri + (int)var3 - 1;
               bew var6 = bgf.this.q.xK(var5);
               if (var6.q().equals(bge.Hk.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgf.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgf.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgf.this.q.Uv());
         var1.q("owner", bgf.this.q.Uv());
         var1.q("signature", bgf.this.q.Uv(), bge.Wx.toString());
         var1.q("data", bgf.this.q.Uv());
      }
   }

   class vn extends bgf.oM {
      @Override
      public void q() {
         this.q(bge.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgf.this.q.io ? bgf.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgf.this.q.Uv());
         var1.q("cSignature", bgf.this.q.Uv());
         var1.q("callbackTarget", bgf.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgf.this.q.Uv());
      }
   }

   class zJ extends bgf.oM {
      @Override
      public void q() {
         this.RF(bge.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgf.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgf.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
