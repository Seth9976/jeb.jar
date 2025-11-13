package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

class bez {
   private static final ILogger RF = GlobalLog.getLogger(bez.class);
   bfb q;

   bez(bfb var1) {
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bez.PY q(int var1) {
      bey var2 = bey.q(var1);
      if (var1 >= bey.eG.q() || var1 == bey.YN.q()) {
         return new bez.vb(var1);
      } else if (this.q.za(var1)) {
         return new bez.LR(var2);
      } else {
         if (this.q.KT) {
            switch (bfa.q[var2.ordinal()]) {
               case 1:
               case 2:
               case 3:
               case 4:
                  return new bez.kY(var2);
               case 5:
                  return new bez.Nz();
            }
         }

         switch (bfa.q[var2.ordinal()]) {
            case 6:
               return new bez.CU();
            case 7:
               return new bez.Uz();
            case 8:
               return new bez.qV();
            case 9:
               return new bez.nI();
            case 10:
               return new bez.CI();
            case 11:
               return new bez.EE();
            case 12:
               return new bez.Xa();
            case 13:
               return new bez.oL();
            case 14:
               return new bez.Nt();
            case 15:
               return new bez.tl();
            case 16:
               return new bez.bK();
            case 17:
            case 18:
               return new bez.eo(var2);
            case 19:
               return new bez.tw();
            case 20:
               return new bez.qa();
            case 21:
               return new bez.Bu();
            case 22:
               return new bez.vX();
            case 23:
               return new bez.Vj();
            case 24:
               return new bez.zJ();
            case 25:
               return new bez.KZ();
            case 26:
               return new bez.HA();
            case 27:
               return new bez.eM();
            case 28:
               return new bez.ej();
            case 29:
               return new bez.iZ();
            case 30:
               return new bez.vn();
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Bu extends bez.oM {
      @Override
      public void q() {
         this.q(bey.TX);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bez.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bez.this.q.nf());
         var1.q("argsDescriptor", bez.this.q.nf());
         var1.q("buckets", bez.this.q.nf());
         var1.q("mask", bez.this.q.nf());
      }
   }

   class CI extends bez.oM {
      @Override
      public void q() {
         this.q(bey.gP);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("parentFunction", bez.this.q.nf());
         var1.q("signatureType", bez.this.q.nf());
      }
   }

   class CU extends bez.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bez.this.q.jq;
         long var1 = bez.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bez.this.q.q(bey.Uv.q());
            var2.q("id", bez.this.q.oW());
            bez.this.q.q(var2);
         });
         this.RF = bez.this.q.jq;
         this.q(bey.Uv);
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.Dw, this.Uv}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bez.this.q.xK(var6);
               this.q(var8);
               int var9 = bez.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bez.this.q.PV && !bez.this.q.io) {
                  var8.q("binaryDeclaration", bez.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bez.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bez.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bez.this.q.za());
               if (!bez.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bez.this.q.za());
               var8.q("numNativeFields", bez.this.q.nf());
               var8.q("tokenPos", bez.this.q.gO());
               var8.q("endTokenPos", bez.this.q.gO());
               var8.q("stateBits", bez.this.q.nf());
               if (bez.this.q.PV) {
                  if (var7) {
                     bez.this.q.nf();
                  } else if (!bez.this.q.gP.q(var9)) {
                     bez.this.q.TX.put(var9, bez.this.q.nf());
                  }
               }

               bez.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bez.this.q.nf());
         var1.q("userName", bez.this.q.nf());
         var1.q("functions", bez.this.q.nf());
         var1.q("functionsHashTable", bez.this.q.nf());
         var1.q("fields", bez.this.q.nf());
         var1.q("offsetInWordsToField", bez.this.q.nf());
         var1.q("interfaces", bez.this.q.nf());
         var1.q("script", bez.this.q.nf());
         var1.q("library", bez.this.q.nf());
         var1.q("typeParameters", bez.this.q.nf());
         var1.q("superType", bez.this.q.nf());
         var1.q("signatureFunction", bez.this.q.nf());
         var1.q("constants", bez.this.q.nf());
         var1.q("declarationType", bez.this.q.nf());
         var1.q("invocationDispatcherCache", bez.this.q.nf());
         var1.q("allocationStub", bez.this.q.nf());
         if (!bez.this.q.io) {
            var1.q("directImplementors", bez.this.q.nf());
            var1.q("directSubclasses", bez.this.q.nf());
            if (!bez.this.q.HF) {
               var1.q("dependentCode", bez.this.q.nf());
               if (!bez.this.q.LK) {
                  throw new RuntimeException("Expected kind to be Full, FullJIT, or FullAOT");
               }
            }
         }
      }
   }

   class EE extends bez.oM {
      @Override
      public void q() {
         this.q(bey.zz);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            if (!bez.this.q.io) {
               if (!bez.this.q.PV) {
                  var2.q("savedInitialValue", bez.this.q.Uv());
               }

               var2.q("guardedListLength", bez.this.q.Uv());
            }

            if (bez.this.q.LK) {
               var2.q("dependentCode", bez.this.q.Uv());
            }

            if (!bez.this.q.io) {
               var2.q("tokenPos", bez.this.q.gO());
               var2.q("endTokenPos", bez.this.q.gO());
               var2.q("guardedCid", bez.this.q.oW());
               var2.q("isNullable", bez.this.q.oW());
               var2.q("staticTypeExactnessState", bez.this.q.JY());
               if (!bez.this.q.PV) {
                  var2.q("binaryDeclaration", bez.this.q.gP());
               }
            }

            int var3 = bez.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bez.this.q.Uv();
            if (bez.this.q.Uv(var3)) {
               long var6 = bez.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bez.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bez.this.q.nf());
         var1.q("owner", bez.this.q.nf());
         var1.q("type", bez.this.q.nf());
         var1.q("initializerFunction", bez.this.q.nf());
      }
   }

   class HA extends bez.oM {
      @Override
      public void q() {
         this.q(bey.GY);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            var2.q("typeTestStub", bez.this.q.nf());
            var2.q("type", bez.this.q.nf());
         }
      }
   }

   class KZ extends bez.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bez.this.q.jq;
         long var1 = bez.this.q.nf();
         bez.this.q.q(var1, bey.Ri.q());
         this.RF = bez.this.q.jq;
         this.q(bey.Ri);
      }

      @Override
      public void RF() {
         for (int var1 = this.q; var1 < this.RF; var1++) {
            this.q(bez.this.q.xK(var1), true);
         }

         for (int var2 = this.Dw; var2 < this.Uv; var2++) {
            this.q(bez.this.q.xK(var2), false);
         }
      }

      void q(bew var1, boolean var2) {
         this.q(var1);
         var1.q("isCanonical", var2);
         var1.q("tokenPos", bez.this.q.gO());
         long var3 = bez.this.q.zz();
         var1.q("typeState", var3 >> (int)bez.this.q.gP.Rr);
         var1.q("nullability", var3 & bez.this.q.gP.EB);
      }

      void q(bew var1) {
         var1.q("typeTestStub", bez.this.q.nf());
         var1.q("typeClassId", bez.this.q.nf());
         var1.q("arguments", bez.this.q.nf());
         var1.q("hash", bez.this.q.nf());
         var1.q("signature", bez.this.q.nf());
      }
   }

   class LR extends bez.oM {
      bey q;

      public LR(bey var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", bez.this.q.Dw());
            int var4 = var3 * bez.this.q.HF(this.q.q());
            var2.q("data", bez.this.q.Dw(var4));
         }
      }
   }

   class Nt extends bez.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q(bey.qa);
         this.q = bez.this.q.jq;
         long var1 = bez.this.q.nf();
         bez.this.q.q(var1, bey.qa.q());
         this.RF = bez.this.q.jq;
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            this.q(var1, false);
         }

         for (int var2 = this.q; var2 < this.RF; var2++) {
            this.q(var2, true);
         }
      }

      void q(int var1, boolean var2) {
         bew var3 = bez.this.q.xK(var1);
         this.q(var3, var2);
         if (bez.this.q.io && bez.this.q.oQ) {
            var3.q("objectPool", null);
         } else {
            var3.q("objectPool", bez.this.q.Uv());
         }

         var3.q("owner", bez.this.q.Uv());
         var3.q("exceptionHandlers", bez.this.q.Uv(), bey.cC.toString());
         var3.q("pcDescriptors", bez.this.q.Uv());
         var3.q("catchEntry", bez.this.q.Uv());
         var3.q("compressedStackMaps", bez.this.q.Uv());
         var3.q("inlinedIdToFunction", bez.this.q.Uv());
         var3.q("codeSourceMap", bez.this.q.Uv());
         if (!bez.this.q.PV && bez.this.q.LK) {
            var3.q("deoptInfoArray", bez.this.q.Uv());
            var3.q("staticCallsTargetTable", bez.this.q.Uv());
         }

         if (!bez.this.q.Me) {
            var3.q("returnAddressMetadata", bez.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bez.this.q.Gf ? bez.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         var3.q("stateBits", bez.this.q.za());
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bez.this.q.PV && bez.this.q.oQ) {
            bez.this.q.Rv = (int)(bez.this.q.Rv + bez.this.q.nf());
            long var3 = bez.this.q.zx + bez.this.q.Rv;
            long var5 = bez.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bez.this.q.gP.oQ : 0L;
            long var12 = var9 ? bez.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class Nz extends bez.kY {
      Nz() {
         super(bey.cY);
      }

      protected String q(int var1) {
         bez.this.q.gO.position(var1);
         bez.this.q.gO.i32();
         long var2;
         if (bez.this.q.wF) {
            bez.this.q.gO.i32();
            var2 = bez.this.q.gO.i64();
         } else {
            var2 = bez.this.q.gO.i32();
            bez.this.q.gO.i32();
         }

         Assert.a(var2 <= 2147483647L);
         byte[] var4 = bez.this.q.gO.get((int)var2 / 2);
         return Strings.decodeASCII(var4);
      }
   }

   interface PY {
      void q();

      void RF();
   }

   class Uz extends bez.oM {
      @Override
      public void q() {
         this.q(bey.oW);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            if (!bez.this.q.PV && !bez.this.q.io) {
               var2.q("libraryKernelOffset", bez.this.q.lm());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bez.this.q.nf());
         var1.q("originClass", bez.this.q.nf());
         var1.q("script", bez.this.q.nf());
         if (!bez.this.q.io) {
            var1.q("libraryKernelData", bez.this.q.nf());
         }
      }
   }

   class Vj extends bez.oM {
      @Override
      public void q() {
         this.q(bey.EB);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            var2.q("parent", bez.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bez.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class Xa extends bez.oM {
      @Override
      public void q() {
         this.q(bey.JY);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            var2.q("lineOffset", bez.this.q.qa());
            var2.q("colOffset", bez.this.q.qa());
            var2.q("flags", bez.this.q.zz());
            var2.q("kernelScriptIndex", bez.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bez.this.q.nf());
         if (bez.this.q.HF || bez.this.q.LK) {
            var1.q("resolvedUrl", bez.this.q.nf());
            var1.q("compileTimeConstants", bez.this.q.nf());
            var1.q("lineStarts", bez.this.q.nf());
            var1.q("debugPositions", bez.this.q.nf());
            var1.q("kernelProgramInfo", bez.this.q.nf());
         }
      }
   }

   class bK extends bez.oM {
      @Override
      public void q() {
         long var1 = bez.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bez.this.q.q(bey.Yp.q());
            var5.q("isCanonical", bez.this.q.Dw());
            var5.q("value", bez.this.q.za());
            bez.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class eM extends bez.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bez.this.q.jq;
         long var1 = bez.this.q.nf();
         bez.this.q.q(var1, bey.Wx.q());
         this.RF = bez.this.q.jq;
         this.q(bey.Wx);
      }

      @Override
      public void RF() {
         for (int var1 = this.q; var1 < this.RF; var1++) {
            this.q(bez.this.q.xK(var1), true);
         }

         for (int var2 = this.Dw; var2 < this.Uv; var2++) {
            this.q(bez.this.q.xK(var2), false);
         }
      }

      void q(bew var1, boolean var2) {
         this.q(var1);
         var1.q("isCanonical", var2);
         var1.q("parameterizedClassId", bez.this.q.qa());
         var1.q("tokenPos", bez.this.q.gO());
         var1.q("index", bez.this.q.LK());
         long var3 = bez.this.q.zz();
         var1.q("typeState", var3 >> (int)bez.this.q.gP.Rr);
         var1.q("nullability", var3 & bez.this.q.gP.EB);
      }

      void q(bew var1) {
         var1.q("typeTestStub", bez.this.q.nf());
         var1.q("name", bez.this.q.nf());
         var1.q("hash", bez.this.q.nf());
         var1.q("bound", bez.this.q.nf());
         var1.q("parameterizedFunction", bez.this.q.nf());
      }
   }

   class ej extends bez.oM {
      @Override
      public void q() {
         this.q(bey.AB);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            var2.q("isCanonical", bez.this.q.Dw());
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bez.this.q.nf());
         var1.q("functionTypeArguments", bez.this.q.nf());
         var1.q("delayedTypeArguments", bez.this.q.nf());
         var1.q("function", bez.this.q.nf());
         var1.q("context", bez.this.q.nf());
         var1.q("hash", bez.this.q.nf());
      }
   }

   class eo extends bez.oM {
      bey q;

      eo(bey var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", bez.this.q.Dw());
            var2.q("typeArguments", bez.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bez.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bez.oM {
      @Override
      public void q() {
         this.q(bey.Gu);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            var2.q("isCanonical", bez.this.q.Dw());
            var2.q("value", bez.this.q.za());
         }
      }
   }

   class kY extends bez.oM {
      bey RF;

      kY(bey var2) {
         this.RF = var2;
      }

      @Override
      public void q() {
         long var1 = bez.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bez.this.q.nf() << (int)bez.this.q.gP.za));
            Object var6 = this.RF(var3);
            bew var7 = bez.this.q.q(this.RF.q());
            var7.q("data", var6);
            bez.this.q.q(var7);
         }
      }

      protected Object RF(int var1) {
         return Strings.ff("ROData_object_at_0x%X", var1);
      }

      @Override
      public void RF() {
      }
   }

   class nI extends bez.oM {
      @Override
      public void q() {
         this.q(bey.nf);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            if (bez.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bez.this.q.Uv());
            }

            var2.q("parentFunction", bez.this.q.Uv());
            var2.q("signatureType", bez.this.q.Uv());
            var2.q("closure", bez.this.q.Uv());
         }
      }
   }

   class oL extends bez.oM {
      @Override
      public void q() {
         this.q(bey.HF);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bez.this.q.qa());
            var2.q("numImports", bez.this.q.HF());
            var2.q("loadState", bez.this.q.JY());
            var2.q("flags", bez.this.q.zz());
            if (!bez.this.q.PV && !bez.this.q.io) {
               var2.q("binaryDeclaration", bez.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bez.this.q.nf());
         var1.q("url", bez.this.q.nf());
         var1.q("privateKey", bez.this.q.nf());
         var1.q("dictionary", bez.this.q.nf());
         var1.q("metadata", bez.this.q.nf());
         var1.q("toplevelClass", bez.this.q.nf());
         var1.q("usedScripts", bez.this.q.nf());
         var1.q("loadingUnit", bez.this.q.nf());
         var1.q("imports", bez.this.q.nf());
         var1.q("exports", bez.this.q.nf());
         if (bez.this.q.HF || bez.this.q.LK) {
            var1.q("dependencies", bez.this.q.nf());
            var1.q("kernelData", bez.this.q.nf());
         }
      }
   }

   abstract class oM implements bez.PY {
      int Dw;
      int Uv;

      @Override
      public void q() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void RF() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void q(bey var1) {
         this.Dw = bez.this.q.jq;
         long var2 = bez.this.q.nf();
         bez.this.q.q(var2, var1.q());
         this.Uv = bez.this.q.jq;
      }

      protected final void RF(bey var1) {
         this.Dw = bez.this.q.jq;
         long var2 = bez.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bez.this.q.q(var1.q());
            long var4 = bez.this.q.nf();
            var3.q("length", var4);
            bez.this.q.q(var3);
         });
         this.Uv = bez.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bez.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bez.oM {
      @Override
      public void q() {
         this.q(bey.gO);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            if (bez.this.q.HF) {
               throw new ToDoException();
            }

            if (!bez.this.q.io) {
               throw new ToDoException();
            }

            var2.q("code", bez.this.q.Uv());
            if (!bez.this.q.PV && !bez.this.q.io) {
               var2.q("tokenPos", bez.this.q.gO());
               var2.q("endTokenPos", bez.this.q.gO());
               var2.q("binaryDeclaration", bez.this.q.nf());
            }

            var2.q("packedFields", bez.this.q.nf());
            var2.q("kindTag", bez.this.q.nf());
            if (!bez.this.q.io && !bez.this.q.PV) {
               var2.q("usageCounter", 0);
               var2.q("optimizedInstructionCount", 0);
               var2.q("optimizedCallSiteCount", 0);
               var2.q("deoptimizationCounter", 0);
               var2.q("stateBits", 0);
               var2.q("inliningDepth", 0);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bez.this.q.nf());
         var1.q("owner", bez.this.q.nf());
         var1.q("resultType", bez.this.q.nf());
         var1.q("parameterTypes", bez.this.q.nf());
         var1.q("parameterNames", bez.this.q.nf());
         var1.q("typeParameters", bez.this.q.nf());
         var1.q("data", bez.this.q.nf());
      }
   }

   class qa extends bez.oM {
      @Override
      public void q() {
         this.q(bey.Dz);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bez.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bez.this.q.nf());
         var1.q("argsDescriptor", bez.this.q.nf());
      }
   }

   class tl extends bez.oM {
      @Override
      public void q() {
         this.RF(bey.oQ);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bez.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bez.this.q.oW(var6);
               if (var8 == bez.this.q.gP.cC || var8 == bez.this.q.gP.xW) {
                  var7.put("rawObj", bez.this.q.Uv());
               } else if (var8 == bez.this.q.gP.KT) {
                  var7.put("rawValue", bez.this.q.za());
               } else {
                  if (var8 != bez.this.q.gP.Gf) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   class tw extends bez.oM {
      @Override
      public void q() {
         this.RF(bey.cC);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bez.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bez.this.q.gP());
               var6.put("outerTryIndex", bez.this.q.LK());
               var6.put("needsStacktrace", bez.this.q.Dw());
               var6.put("hasCatchAll", bez.this.q.Dw());
               var6.put("isGenerated", bez.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class vX extends bez.oM {
      @Override
      public void q() {
         this.q(bey.Rr);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            var2.q("cache", bez.this.q.Uv());
         }
      }
   }

   class vb extends bez.oM {
      int q;
      int RF;
      int xK;

      vb(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.Dw = bez.this.q.jq;
         long var1 = bez.this.q.nf();
         this.RF = bez.this.q.lm();
         this.xK = bez.this.q.lm();
         bez.this.q.q(var1, this.q);
         this.Uv = bez.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bez.this.q.gP.oW;
         int var2 = (int)bez.this.q.q(this.xK * bez.this.q.gP.Uv, bez.this.q.gP.gP);
         Long var3 = (Long)bez.this.q.TX.get(this.q);

         for (int var4 = this.Dw; var4 < this.Uv; var4++) {
            bew var5 = bez.this.q.xK(var4);
            var5.q("isCanonical", bez.this.q.Dw());
            ArrayList var6 = new ArrayList();
            var5.q("data", var6);

            int var7;
            for (var7 = bez.this.q.wF ? 8 : 4; var7 < var1; var7 = (int)(var7 + bez.this.q.gP.Uv)) {
               if (bez.this.q.RF(var3, var7 / (int)bez.this.q.gP.Uv)) {
                  bez.this.q.lm();
                  bez.this.q.lm();
               } else {
                  bez.this.q.Uv();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bez.this.q.gP.Uv);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class vn extends bez.oM {
      @Override
      public void q() {
         this.q(bey.lF);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            var2.q("isCanonical", bez.this.q.Dw());
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bez.this.q.nf());
         var1.q("length", bez.this.q.nf());
         var1.q("data", bez.this.q.nf());
      }
   }

   class zJ extends bez.oM {
      @Override
      public void q() {
         this.RF(bey.zx);
      }

      @Override
      public void RF() {
         for (int var1 = this.Dw; var1 < this.Uv; var1++) {
            bew var2 = bez.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", bez.this.q.Dw());
            var2.q("hash", bez.this.q.lm());
            var2.q("nullabity", bez.this.q.nf());
            var2.q("instantiations", bez.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bez.this.q.Uv());
            }
         }
      }
   }
}
