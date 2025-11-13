package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.PrettyPrinter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class blf {
   private static final ILogger q = GlobalLog.getLogger(blf.class);
   private ble RF;
   private List xK;
   private blf.CU Dw;
   private blf.eo Uv;
   private static final Set oW = new HashSet();
   private static final Set gO = new HashSet();
   private static final Set nf = new HashSet();
   private static final Set gP = new HashSet();

   public blf(ble var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   public void q() {
      if (this.xK != null) {
         throw new IllegalStateException();
      } else {
         this.xK = new ArrayList();
         IDState var1 = this.RF.getEmulatedAndroid().getState();
         this.Dw = new blf.CU();
         var1.registerNativeEmulatorHooks(this.Dw);
         var1.registerNativeEmulatorMemoryHooks(this.Dw);
         this.Uv = new blf.eo();
         var1.registerSandboxHooks(this.Uv);
      }
   }

   public ble RF() {
      return this.RF;
   }

   public blf.eo xK() {
      if (this.xK == null) {
         throw new IllegalStateException();
      } else {
         return this.Uv;
      }
   }

   public blf.CU Dw() {
      if (this.xK == null) {
         throw new IllegalStateException();
      } else {
         return this.Dw;
      }
   }

   public List Uv() {
      if (this.xK == null) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableList(this.xK);
      }
   }

   public String oW() {
      return this.q(0);
   }

   public String q(int var1) {
      if (this.xK == null) {
         throw new IllegalStateException();
      } else {
         StringBuilder var2 = new StringBuilder();
         if (var1 == -1) {
            if (this.Uv != null) {
               var2.append(this.Uv.q(var1));
            }

            if (this.Dw != null) {
               var2.append(this.Dw.q(var1));
            }

            if (!this.xK.isEmpty()) {
               var2.append(S.L("INTERESTING RECORDS BY ORDER OF EXECUTION (JAVA, NATIVE):\n"));

               for (blh var4 : this.xK) {
                  Strings.ff(var2, "- %s\n", var4);
               }
            }
         } else {
            if (!this.xK.isEmpty()) {
               var2.append(S.L("INTERESTING RECORDS BY ORDER OF EXECUTION (JAVA, NATIVE):\n"));

               for (blh var6 : this.xK) {
                  Strings.ff(var2, "- %s\n", var6);
               }
            }

            if (this.Dw != null) {
               var2.append("\n");
               var2.append(this.Dw.q(var1));
            }

            if (this.Uv != null) {
               var2.append("\n");
               var2.append(this.Uv.q(var1));
            }
         }

         return var2.toString();
      }
   }

   static {
      oW.add(
         cvm.q(
            new byte[]{
               -115,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               42,
               10,
               7,
               17,
               8,
               86,
               22,
               19,
               89,
               2,
               17,
               36,
               34,
               29,
               31,
               21,
               23,
               6,
               13,
               81,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92
            },
            1,
            193
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               -87,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               42,
               10,
               7,
               17,
               8,
               86,
               22,
               19,
               89,
               2,
               17,
               36,
               34,
               29,
               31,
               21,
               23,
               6,
               13,
               81,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               119,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92
            },
            1,
            229
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               48,
               80,
               83,
               69,
               92,
               84,
               8,
               1,
               30,
               94,
               95,
               83,
               80,
               96,
               73,
               45,
               0,
               0,
               17,
               21,
               77,
               108,
               11,
               15,
               18,
               78,
               64,
               30,
               65,
               7,
               19,
               92,
               115,
               21,
               20,
               15,
               7,
               11,
               82,
               72,
               34
            },
            2,
            190
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               -34,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               125,
               39,
               27,
               26,
               29,
               4,
               8,
               94,
               22,
               19,
               82,
               3,
               14,
               5,
               40,
               37,
               11,
               16,
               19,
               19,
               11,
               81,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               127
            },
            1,
            146
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               -6,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               42,
               10,
               7,
               17,
               8,
               86,
               22,
               19,
               82,
               3,
               14,
               5,
               76,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               127
            },
            1,
            182
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               -106,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               125,
               39,
               27,
               26,
               29,
               4,
               8,
               94,
               22,
               19,
               82,
               3,
               14,
               5,
               76,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               127
            },
            1,
            218
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               -107,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               108,
               47,
               13,
               18,
               0,
               63,
               35,
               14,
               5,
               1,
               23,
               73,
               22,
               19,
               88,
               15,
               7,
               10,
               40,
               37,
               11,
               16,
               19,
               19,
               11,
               81,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92
            },
            1,
            217
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               51,
               91,
               79,
               82,
               92,
               74,
               64,
               110,
               85,
               91,
               92,
               86,
               81,
               94,
               27,
               98,
               76,
               2,
               12,
               1,
               8,
               65,
               15,
               10,
               76,
               116,
               35,
               24,
               65,
               31,
               21,
               92,
               76,
               0,
               8,
               1,
               70,
               63,
               29,
               19,
               29,
               11,
               20,
               21,
               9,
               13,
               6,
               13,
               86,
               19,
               70,
               11,
               9,
               26,
               20,
               15,
               34,
               23,
               28,
               6,
               23,
               5,
               22,
               38,
               91,
               73,
               47,
               11,
               21,
               11,
               73
            },
            2,
            46
         )
      );
      oW.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 27, 28, 7, 93, 86, 64, 69, 70, 85, 26, 29, 118}, 2, 8));
      oW.add(
         cvm.q(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               14,
               7,
               91,
               102,
               65,
               15,
               76,
               27,
               28,
               7,
               75,
               86,
               66,
               65,
               95,
               85,
               102,
               91,
               4,
               108,
               37,
               19,
               23,
               2,
               67,
               12,
               79,
               78,
               40,
               13,
               67,
               10,
               73,
               9,
               51
            },
            2,
            157
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               109,
               45,
               15,
               10,
               22,
               29,
               6,
               13,
               75,
               76,
               12,
               1,
               26,
               17,
               11,
               26,
               91,
               93,
               23,
               22,
               92,
               110,
               50,
               0,
               22,
               17,
               57,
               44,
               15,
               15,
               6,
               2,
               23,
               73,
               22,
               19,
               95,
               5,
               0,
               37,
               50,
               0,
               22,
               17,
               36,
               49,
               21,
               28,
               64,
               100,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               39,
               6,
               27,
               7,
               9,
               92,
               18,
               96
            },
            1,
            33
         )
      );
      oW.add(
         cvm.q(
            new byte[]{
               -73,
               45,
               15,
               10,
               22,
               29,
               6,
               13,
               75,
               78,
               17,
               0,
               95,
               108,
               44,
               1,
               26,
               17,
               29,
               12,
               61,
               36,
               29,
               28,
               87,
               22,
               19,
               83,
               61,
               49,
               2,
               8,
               10,
               6,
               2,
               44,
               39,
               8,
               9,
               85,
               118,
               45,
               15,
               10,
               22,
               29,
               6,
               13,
               75,
               78,
               17,
               0,
               95,
               99,
               35,
               14,
               5,
               1,
               1,
               37,
               49,
               27,
               80
            },
            1,
            251
         )
      );
      gO.add(cvm.q(new byte[]{5, 40, 5, 13, 26, 31, 2, 68, 92, 10, 10, 7, 17, 8, 66}, 1, 73));
      gO.add(cvm.q(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 107, 85, 88, 85, 93}, 2, 178));
      gO.add(cvm.q(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 109, 69, 83, 76, 94}, 2, 229));
      gO.add(cvm.q(new byte[]{56, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 127, 34, 29, 12, 6, 22, 0}, 1, 116));
      gO.add(cvm.q(new byte[]{0, 45, 15, 10, 22, 29, 6, 13, 75, 64, 28, 92, 124, 42, 10, 7, 17, 8, 46, 47, 3, 12, 8}, 1, 76));
      gO.add(
         cvm.q(
            new byte[]{
               15, 14, 30, 29, 0, 6, 14, 12, 91, 67, 71, 13, 93, 69, 95, 77, 22, 67, 65, 15, 115, 64, 66, 88, 69, 67, 46, 6, 8, 12, 2, 44, 78, 7, 1, 95
            },
            2,
            152
         )
      );
      gO.add(cvm.q(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 65, 88, 19, 6, 108, 94, 88, 93, 86, 72, 97, 66, 91, 9}, 2, 219));
      gO.add(cvm.q(new byte[]{26, 45, 15, 10, 22, 29, 6, 13, 75, 78, 17, 0, 95, 110, 34, 23, 29, 31, 31, 29, 13, 45, 60, 26, 23, 4, 5}, 1, 86));
      gO.add(
         cvm.q(
            new byte[]{
               -85,
               45,
               15,
               10,
               22,
               29,
               6,
               13,
               75,
               76,
               12,
               1,
               26,
               17,
               11,
               26,
               91,
               93,
               23,
               22,
               92,
               110,
               50,
               0,
               22,
               17,
               57,
               44,
               15,
               15,
               6,
               2,
               23,
               73,
               22,
               19,
               81,
               31,
               21,
               11
            },
            1,
            231
         )
      );
      gO.add(
         cvm.q(
            new byte[]{
               -84,
               38,
               11,
               23,
               23,
               25,
               87,
               76,
               17,
               11,
               9,
               4,
               27,
               64,
               92,
               3,
               21,
               6,
               76,
               124,
               54,
               6,
               17,
               23,
               17,
               63,
               46,
               28,
               42,
               35,
               21,
               6,
               88,
               22,
               19,
               2,
               85,
               7,
               7,
               29,
               74,
               22
            },
            1,
            224
         )
      );
      gO.add(cvm.q(new byte[]{-115, 38, 11, 23, 23, 25, 87, 76, 17, 11, 9, 4, 27, 64, 108, 42, 25, 24, 13, 23, 73, 22, 19, 87, 7, 7, 29, 92}, 1, 193));
      gO.add(
         cvm.q(
            new byte[]{15, 5, 17, 15, 19, 17, 72, 11, 6, 89, 88, 23, 70, 15, 114, 80, 73, 91, 73, 82, 9, 29, 12, 83, 73, 84, 6, 28, 18, 23, 13, 11, 67, 4, 70},
            2,
            77
         )
      );
      gO.add(
         cvm.q(new byte[]{-89, 38, 11, 23, 23, 78, 90, 1, 29, 5, 67, 85, 19, 25, 95, 117, 51, 25, 54, 47, 5, 9, 94, 22, 19, 2, 85, 7, 7, 29, 74, 22}, 1, 235)
      );
      gO.add(
         cvm.q(
            new byte[]{
               15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 111, 68, 77, 73, 70, 88, 115, 70, 66, 87, 85, 65, 27, 98, 76, 93, 10, 2, 12, 84, 95, 70
            },
            2,
            210
         )
      );
      gO.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 27, 28, 7, 84, 88, 72, 73, 64, 24, 27, 110}, 2, 162));
      gO.add(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 27, 28, 7, 84, 88, 72, 73, 64, 67, 26, 29, 118}, 2, 25));
      nf.add(
         cvm.q(
            new byte[]{
               28,
               19,
               95,
               21,
               0,
               21,
               2,
               11,
               42,
               35,
               18,
               22,
               38,
               44,
               1,
               26,
               17,
               29,
               12,
               92,
               100,
               45,
               15,
               10,
               22,
               29,
               6,
               13,
               75,
               76,
               12,
               1,
               26,
               17,
               11,
               26,
               91,
               108,
               44,
               1,
               26,
               17,
               29,
               12,
               79,
               18,
               127
            },
            1,
            49
         )
      );
   }

   public class CU implements IEStateHooks, IEEmulatorHooks {
      private int RF;
      private Map xK = new HashMap();
      private AddressSegmentMap Dw = new AddressSegmentMap(64);
      private AddressSegmentMap Uv = new AddressSegmentMap(64);
      private ReferenceCounter oW = new ReferenceCounter();
      private ReferenceCounter gO = new ReferenceCounter();

      private CU() {
      }

      public int q() {
         return this.RF;
      }

      @Override
      public int getPriority() {
         return Integer.MAX_VALUE;
      }

      long q(EEmulator var1) {
         return var1.getTruncatedRegisterValue("X0");
      }

      long RF(EEmulator var1) {
         return var1.getTruncatedRegisterValue("X1");
      }

      @Override
      public Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
         this.RF++;
         return null;
      }

      @Override
      public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
         long var4 = var1.getReturnAddress().getValueAsAddress();
         if (this.Dw(var4)) {
            this.oW.inc(var2);
         }

         switch (cvm.xK(var2)) {
            case -2055685377:
               long var13 = (int)this.q(var1);
               this.xK.put(var1.currentRequestId(), var13);
               break;
            case -1052079544:
               long var12 = (int)this.q(var1);
               if (var12 != 0L) {
                  this.Dw.remove(var12);
               }

               int var8 = (int)this.RF(var1);
               this.xK.put(var1.currentRequestId(), var8);
               break;
            case 500988114:
               blf.this.xK.add(new blh.oM(var1.getState().getEvaluationCount(), var1.getPCAddress(), blh.Nt.oW, var2));
               break;
            case 1037717026:
               int var11 = (int)this.q(var1);
               this.xK.put(var1.currentRequestId(), var11);
               break;
            case 1147150343:
               int var10 = (int)this.q(var1) * (int)this.RF(var1);
               this.xK.put(var1.currentRequestId(), var10);
               break;
            case 1222364617:
               int var9 = (int)this.RF(var1);
               this.xK.put(var1.currentRequestId(), var9);
               break;
            case 1332620714:
               long var6 = this.q(var1);
               this.xK.put(var1.currentRequestId(), var6);
         }

         return null;
      }

      @Override
      public void postEvaluateExternal(EEmulator var1, String var2, INativeMethodItem var3, long var4, boolean var6) {
         if (var6) {
            Object var7 = this.xK.remove(var4);
            if (var7 != null) {
               switch (cvm.xK(var2)) {
                  case -2055685377:
                     int var13 = (int)this.q(var1);
                     if (var13 == 0) {
                        long var14 = (Long)var7;
                        this.Uv.remove(var14);
                     }
                     break;
                  case -1052079544:
                  case 1037717026:
                  case 1147150343:
                     int var12 = (Integer)var7;
                     long var9 = this.q(var1);
                     if (var9 != 0L) {
                        this.Dw.add(new blf.CU.eo(var9, var12));
                     }
                     break;
                  case 1222364617:
                     long var11 = this.q(var1);
                     if (var11 != -1L) {
                        int var10 = (Integer)var7;
                        var10 = var10 + 4095 & -4096;
                        this.Uv.add(new blf.CU.eo(var11, var10));
                     }
                     break;
                  case 1332620714:
                     long var8 = (Long)var7;
                     this.Dw.remove(var8);
               }
            }
         }
      }

      @Override
      public Long evaluateSyscall(EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8) {
         if (var6 == null) {
            var6 = cvm.q(new byte[]{48, 22, 3, 26, 19, 5, 11, 55}, 2, 20) + var5;
         }

         if (this.Dw(var2)) {
            this.gO.inc(var6);
         }

         switch (cvm.xK(var6)) {
            case -2055685377:
               long var11 = (int)this.q(var1);
               this.xK.put(var1.currentRequestId(), var11);
               break;
            case 1222364617:
               int var9 = (int)this.RF(var1);
               this.xK.put(var1.currentRequestId(), var9);
         }

         return null;
      }

      @Override
      public void postEvaluateSyscall(
         EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8, long var9, long var11
      ) {
         Object var13 = this.xK.remove(var9);
         if (var13 != null) {
            switch (cvm.xK(var6)) {
               case -2055685377:
                  int var14 = (int)var11;
                  if (var14 == 0) {
                     long var15 = (Long)var13;
                     this.Uv.remove(var15);
                  }
                  break;
               case 1222364617:
                  if (var11 != -1L) {
                     int var16 = (Integer)var13;
                     var16 = var16 + 4095 & -4096;
                     this.Uv.add(new blf.CU.eo(var11, var16));
                  }
            }
         }
      }

      @Override
      public Integer onReadMemory(EState var1, long var2, byte[] var4) {
         if (this.q(var2)) {
            return null;
         } else {
            int var5 = var1.getEvaluationCount();
            long var6 = var1.getProgramCounter().getValueAsAddress();
            if (!this.Dw(var6)) {
               return null;
            } else {
               byte[] var9 = new byte[var4.length];

               String var8;
               try {
                  var1.getMemory().read(var2, var9.length, var9, 0);
                  var8 = Formatter.formatBinaryLine(var9).toString();
               } catch (MemoryException var11) {
                  var8 = "<unreadable>";
                  var9 = null;
               }

               String var10 = this.xK(var2);
               Object[] var10000 = new Object[]{var5, var6, var2, var8};
               blf.this.xK.add(new blh.ej(var5, var6, blh.Nt.q, var2, var4.length, var9, var10));
               return null;
            }
         }
      }

      @Override
      public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
         if (this.RF(var2)) {
            return null;
         } else {
            int var5 = var1.getEvaluationCount();
            long var6 = var1.getProgramCounter().getValueAsAddress();
            if (!this.Dw(var6)) {
               return null;
            } else {
               String var8 = this.xK(var2);
               String var9 = Formatter.formatBinaryLine(var4).toString();
               Object[] var10000 = new Object[]{var5, var6, var2, var9};
               blf.this.xK.add(new blh.ej(var5, var6, blh.Nt.RF, var2, var4.length, var4, var8));
               return null;
            }
         }
      }

      boolean q(long var1) {
         if (this.RF(var1)) {
            return true;
         } else {
            return Long.compareUnsigned(var1, 2063663104L) >= 0 && Long.compareUnsigned(var1, 2063667200L) < 0 ? true : var1 == 499921592360L;
         }
      }

      boolean RF(long var1) {
         if (Long.compareUnsigned(var1, 2080374784L) >= 0 && Long.compareUnsigned(var1, 2130706432L) < 0) {
            return true;
         } else if (Long.compareUnsigned(var1, 4294967296L) >= 0 && Long.compareUnsigned(var1, 8589934592L) < 0) {
            return true;
         } else {
            return this.Dw.getSegmentContaining(var1) != null ? true : this.Uv.getSegmentContaining(var1) != null;
         }
      }

      String xK(long var1) {
         cjo var3 = ((bye)blf.this.RF.getEmulatedAndroid().getState()).lm().xK();
         if (var3 == null) {
            return null;
         } else {
            cjx.eo var4 = var3.HF.RF(var1);
            if (var4 == null) {
               return null;
            } else {
               long var5 = var4.getBegin();
               String var7 = var4.oW();
               long var8 = var1 - var5;
               return Strings.ff("mapping %s+0x%X", var7, var8);
            }
         }
      }

      public boolean Dw(long var1) {
         if (Long.compareUnsigned(var1, 4294967296L) >= 0 && Long.compareUnsigned(var1, 8589934592L) < 0) {
            return true;
         } else {
            return this.Dw.getSegmentContaining(var1) != null ? true : this.Uv.getSegmentContaining(var1) != null;
         }
      }

      @Override
      public void monitorHLSpecial(EEmulator var1, int var2, List var3) {
         int var4 = var1.getState().getEvaluationCount();
         long var5 = var1.getState().getProgramCounter().getValueAsAddress();
         switch (var2) {
            case 0:
               String var11 = (String)var3.get(0);
               int var12 = (Integer)var3.get(1);
               Object[] var13 = new Object[]{var4, var5, var11, var12};
               blf.this.xK.add(new blh.eo(var4, var5, blh.Nt.xK, var11, var12));
               break;
            case 10:
            case 11:
               blh.Nt var7 = var2 == 10 ? blh.Nt.Dw : blh.Nt.Uv;
               String var8 = (String)var3.get(0);
               long var9 = (Long)var3.get(1);
               Object[] var10000 = new Object[]{var4, var5, var7, var8, var9};
               blf.this.xK.add(new blh.nI(var4, var5, var7, var8, var9));
         }
      }

      public String q(int var1) {
         StringBuilder var2 = new StringBuilder(S.L("NATIVE CODE MONITORING REPORT:\n"));
         if (this.RF == 0) {
            var2.append(S.L("N/A")).append("\n");
         } else {
            Strings.ff(var2, S.L("=> Iteration count: %d\n"), this.RF);
            Strings.ff(var2, S.L("=> API calls:\n%s"), this.oW.formatAllReferences());
            Strings.ff(var2, S.L("=> System calls:\n%s"), this.gO.formatAllReferences());
         }

         return var2.toString();
      }

      @SerDisabled
      class eo implements ISegment {
         long q;
         int RF;

         eo(long var2, int var4) {
            this.q = var2;
            this.RF = var4;
         }

         public Long q() {
            return this.q;
         }

         public Long RF() {
            return this.q + this.RF;
         }
      }
   }

   public class eo implements IDSandboxHooks {
      private IDState RF;
      private PrettyPrinter xK;
      private ReferenceCounter Dw = new ReferenceCounter();
      private Map Uv = new HashMap();

      private eo() {
         this.RF = blf.this.RF.getEmulatedAndroid().getState();
         File var2 = blf.this.RF.getEmulatedAndroid().getDropboxFolder();
         this.xK = new blg(this, false, -1, blf.this, var2);
      }

      @Override
      public Wrapper newInstance(long var1, String var3, String var4, List var5) {
         String var6 = "NEW_" + var4;
         this.Dw.inc(var6);
         this.q(blh.Nt.nf, var4, null, var5);
         return null;
      }

      @Override
      public Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) {
         String var7 = "SET_" + var4;
         this.Dw.inc(var7);
         this.q(blh.Nt.za, var4, var5, Arrays.asList(var6));
         return null;
      }

      @Override
      public Wrapper getField(long var1, String var3, String var4, Object var5) {
         String var6 = "GET_" + var4;
         this.Dw.inc(var6);
         blh var7 = this.q(blh.Nt.lm, var4, var5, null);
         if (var7 != null) {
            this.Uv.put(var1, var7);
         }

         return null;
      }

      @Override
      public Wrapper examineFieldValue(long var1, Object var3) {
         blh var4 = (blh)this.Uv.remove(var1);
         if (var4 != null) {
            String[] var5 = new String[]{this.q(var3)};
            ((blh.CU)var4).oW = var5;
         }

         return null;
      }

      @Override
      public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) {
         String var7 = "CALL_" + var4;
         this.Dw.inc(var7);
         this.q(blh.Nt.gP, var4, var5, var6);
         return null;
      }

      blh q(blh.Nt var1, String var2, Object var3, List var4) {
         boolean var5 = false;
         if (!var5) {
            var5 = blf.oW.contains(var2);
         }

         if (!var5) {
            for (String var7 : blf.gO) {
               if (var2.startsWith(var7)) {
                  var5 = true;
                  break;
               }
            }
         }

         if (!var5) {
            for (String var14 : blf.nf) {
               if (var2.endsWith(var14)) {
                  var5 = true;
                  break;
               }
            }
         }

         if (!var5) {
            for (String var15 : blf.gP) {
               if (var2.contains(var15)) {
                  var5 = true;
                  break;
               }
            }
         }

         if (var5) {
            String var13 = null;
            if (var3 != null) {
               var13 = this.q(var3);
            }

            String[] var16 = null;
            if (var4 != null) {
               var16 = new String[var4.size()];
               int var8 = 0;

               for (Object var10 : var4) {
                  var16[var8] = this.q(var10);
                  var8++;
               }
            }

            blh.CU var17 = new blh.CU(var1, this.RF.getCurrentIterationCount(), var2, var13, var16);
            blf.this.xK.add(var17);
            return var17;
         } else {
            return null;
         }
      }

      private String q(Object var1) {
         return this.xK.format(var1);
      }

      public String q(int var1) {
         StringBuilder var2 = new StringBuilder(S.L("JAVA MONITORING REPORT:\n"));
         int var3 = this.RF.getCurrentIterationCount();
         Strings.ff(var2, S.L("=> Iteration count: %d\n"), var3);
         Strings.ff(var2, S.L("=> Events:\n%s"), this.Dw.formatAllReferences());
         return var2.toString();
      }
   }
}
