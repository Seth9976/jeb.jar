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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bhi {
   private static final ILogger pC = GlobalLog.getLogger(bhi.class);
   private bhh A;
   private List kS;
   private bhi.Sv wS;
   private bhi.Av UT;
   private static final Set E = new HashSet();
   private static final Set sY = new HashSet();
   private static final Set ys = new HashSet();
   private static final Set ld = new HashSet();

   public bhi(bhh var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   public void pC() {
      if (this.kS != null) {
         throw new IllegalStateException();
      } else {
         this.kS = new ArrayList();
         IDState var1 = this.A.getEmulatedAndroid().getState();
         this.wS = new bhi.Sv();
         var1.registerNativeEmulatorHooks(this.wS);
         var1.registerNativeEmulatorMemoryHooks(this.wS);
         this.UT = new bhi.Av();
         var1.registerSandboxHooks(this.UT);
      }
   }

   public String pC(int var1) {
      if (this.kS == null) {
         throw new IllegalStateException();
      } else {
         StringBuilder var2 = new StringBuilder();
         if (var1 == -1) {
            if (this.UT != null) {
               var2.append(this.UT.pC(var1));
            }

            if (this.wS != null) {
               var2.append(this.wS.pC(var1));
            }

            if (!this.kS.isEmpty()) {
               var2.append(S.L("INTERESTING RECORDS BY ORDER OF EXECUTION (JAVA, NATIVE):\n"));

               for (bhk var4 : this.kS) {
                  Strings.ff(var2, "- %s\n", var4);
               }
            }
         } else {
            if (!this.kS.isEmpty()) {
               var2.append(S.L("INTERESTING RECORDS BY ORDER OF EXECUTION (JAVA, NATIVE):\n"));

               for (bhk var6 : this.kS) {
                  Strings.ff(var2, "- %s\n", var6);
               }
            }

            if (this.wS != null) {
               var2.append("\n");
               var2.append(this.wS.pC(var1));
            }

            if (this.UT != null) {
               var2.append("\n");
               var2.append(this.UT.pC(var1));
            }
         }

         return var2.toString();
      }
   }

   static {
      E.add(
         ckx.pC(
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
               85,
               85,
               70,
               101,
               94,
               79,
               63,
               23,
               19,
               23,
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
               56,
               15,
               18,
               88,
               65,
               110,
               0,
               13,
               78,
               21,
               70,
               52,
               28,
               6,
               26,
               78,
               21,
               94
            },
            2,
            56
         )
      );
      E.add(
         ckx.pC(
            new byte[]{
               22,
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
            90
         )
      );
      E.add(
         ckx.pC(
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
               81,
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
            254
         )
      );
      E.add(
         ckx.pC(
            new byte[]{
               102,
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
            42
         )
      );
      E.add(
         ckx.pC(
            new byte[]{
               -61,
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
            143
         )
      );
      E.add(
         ckx.pC(
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
               49,
               92,
               78,
               69,
               80,
               84,
               86,
               23,
               13,
               12,
               92,
               93,
               84,
               72,
               8,
               3,
               24,
               0,
               21,
               13,
               74,
               76,
               0,
               0,
               3,
               0,
               60,
               6,
               82,
               0,
               26,
               20,
               27,
               72,
               48
            },
            2,
            20
         )
      );
      E.add(
         ckx.pC(
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
               32,
               69,
               65,
               66,
               74,
               117,
               92,
               77,
               68,
               87,
               66,
               9,
               24,
               18,
               70,
               38,
               28,
               5,
               47,
               5,
               7,
               82,
               0,
               28,
               29,
               7,
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
               33,
               17,
               1,
               12,
               28,
               17,
               94
            },
            2,
            83
         )
      );
      E.add(
         ckx.pC(
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
               80,
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
               42,
               3,
               20,
               28,
               0,
               82
            },
            2,
            109
         )
      );
      E.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 27, 28, 7, 93, 86, 64, 69, 70, 85, 26, 28, 118}, 2, 220));
      E.add(
         ckx.pC(
            new byte[]{
               -14,
               38,
               11,
               23,
               23,
               78,
               70,
               6,
               64,
               105,
               47,
               5,
               9,
               94,
               22,
               19,
               76,
               23,
               11,
               15,
               12,
               8,
               49,
               59,
               71,
               100,
               38,
               11,
               23,
               23,
               78,
               70,
               6,
               64,
               105,
               47,
               5,
               9,
               94,
               18,
               115
            },
            1,
            190
         )
      );
      E.add(
         ckx.pC(
            new byte[]{
               15,
               14,
               30,
               29,
               0,
               6,
               14,
               12,
               91,
               67,
               71,
               13,
               93,
               69,
               95,
               77,
               22,
               65,
               73,
               83,
               29,
               113,
               65,
               70,
               73,
               84,
               2,
               19,
               15,
               2,
               11,
               0,
               82,
               90,
               67,
               90,
               78,
               11,
               22,
               97,
               26,
               7,
               22,
               84,
               49,
               7,
               18,
               1,
               68,
               37,
               11,
               21,
               19,
               18,
               1,
               76,
               32,
               2,
               11,
               15,
               33,
               29,
               21,
               1,
               26,
               20,
               27,
               91,
               44
            },
            2,
            18
         )
      );
      E.add(
         ckx.pC(
            new byte[]{
               102,
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
            42
         )
      );
      sY.add(ckx.pC(new byte[]{15, 11, 17, 21, 4, 0, 12, 71, 7, 89, 91, 23, 76, 77, 30}, 2, 27));
      sY.add(ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 107, 85, 88, 85, 93}, 2, 91));
      sY.add(ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 109, 69, 83, 76, 94}, 2, 246));
      sY.add(ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 121, 82, 94, 90, 92, 64, 95}, 2, 212));
      sY.add(ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 79, 91, 76, 122, 89, 66, 77, 92, 94, 111, 76, 93, 83, 89}, 2, 148));
      sY.add(
         ckx.pC(
            new byte[]{
               15, 14, 30, 29, 0, 6, 14, 12, 91, 67, 71, 13, 93, 69, 95, 77, 22, 67, 65, 15, 115, 64, 66, 89, 69, 67, 46, 6, 8, 12, 2, 44, 78, 7, 1, 95
            },
            2,
            19
         )
      );
      sY.add(ckx.pC(new byte[]{89, 45, 15, 10, 22, 29, 6, 13, 75, 78, 17, 0, 95, 99, 35, 14, 5, 1, 1, 37, 49, 27, 80}, 1, 21));
      sY.add(ckx.pC(new byte[]{15, 14, 30, 29, 0, 6, 14, 12, 91, 65, 88, 19, 6, 97, 82, 77, 80, 69, 69, 84, 75, 100, 90, 71, 73, 65, 43}, 2, 48));
      sY.add(
         ckx.pC(
            new byte[]{
               15,
               14,
               30,
               29,
               0,
               6,
               14,
               12,
               91,
               67,
               71,
               13,
               93,
               69,
               95,
               77,
               22,
               65,
               73,
               83,
               29,
               113,
               65,
               70,
               73,
               84,
               2,
               19,
               15,
               2,
               11,
               0,
               82,
               90,
               67,
               90,
               64,
               31,
               23,
               78
            },
            2,
            86
         )
      );
      sY.add(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               17,
               72,
               11,
               6,
               89,
               88,
               23,
               70,
               15,
               66,
               73,
               92,
               80,
               3,
               115,
               87,
               83,
               64,
               80,
               88,
               107,
               42,
               11,
               50,
               19,
               9,
               6,
               27,
               76,
               80,
               88,
               70,
               1,
               27,
               84,
               87,
               92
            },
            2,
            28
         )
      );
      sY.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 17, 72, 11, 6, 89, 88, 23, 70, 15, 114, 80, 73, 91, 73, 82, 9, 29, 12, 92, 66, 73, 59, 90}, 2, 222));
      sY.add(
         ckx.pC(
            new byte[]{-63, 38, 11, 23, 23, 25, 87, 76, 17, 11, 9, 4, 27, 64, 108, 42, 25, 24, 13, 23, 73, 22, 19, 89, 2, 17, 61, 39, 29, 7, 21, 15, 13, 6, 77},
            1,
            141
         )
      );
      sY.add(
         ckx.pC(new byte[]{56, 38, 11, 23, 23, 78, 90, 1, 29, 5, 67, 85, 19, 25, 95, 117, 51, 25, 54, 47, 5, 9, 94, 22, 19, 2, 85, 7, 7, 29, 74, 22}, 1, 116)
      );
      sY.add(
         ckx.pC(
            new byte[]{
               15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 111, 68, 77, 73, 70, 88, 115, 70, 66, 87, 84, 65, 27, 98, 76, 93, 10, 2, 12, 84, 95, 70
            },
            2,
            141
         )
      );
      sY.add(ckx.pC(new byte[]{-23, 38, 11, 23, 23, 78, 70, 6, 64, 105, 47, 5, 9, 94, 22, 19, 83, 6, 15, 13, 27, 90, 1, 115}, 1, 165));
      sY.add(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 14, 7, 91, 102, 65, 15, 76, 27, 28, 7, 84, 88, 72, 73, 64, 67, 26, 28, 118}, 2, 97));
      ys.add(
         ckx.pC(
            new byte[]{
               67,
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
            110
         )
      );
   }

   public class Av implements IDSandboxHooks {
      private IDState A;
      private PrettyPrinter kS;
      private ReferenceCounter wS = new ReferenceCounter();
      private Map UT = new HashMap();

      private Av() {
         this.A = bhi.this.A.getEmulatedAndroid().getState();
         File var2 = bhi.this.A.getEmulatedAndroid().getDropboxFolder();
         this.kS = new bhj(this, false, -1, bhi.this, var2);
      }

      @Override
      public Wrapper newInstance(long var1, String var3, String var4, List var5) {
         String var6 = "NEW_" + var4;
         this.wS.inc(var6);
         this.pC(bhk.cq.ys, var4, null, var5);
         return null;
      }

      @Override
      public Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) {
         String var7 = "SET_" + var4;
         this.wS.inc(var7);
         this.pC(bhk.cq.gp, var4, var5, Arrays.asList(var6));
         return null;
      }

      @Override
      public Wrapper getField(long var1, String var3, String var4, Object var5) {
         String var6 = "GET_" + var4;
         this.wS.inc(var6);
         bhk var7 = this.pC(bhk.cq.oT, var4, var5, null);
         if (var7 != null) {
            this.UT.put(var1, var7);
         }

         return null;
      }

      @Override
      public Wrapper examineFieldValue(long var1, Object var3) {
         bhk var4 = (bhk)this.UT.remove(var1);
         if (var4 != null) {
            String[] var5 = new String[]{this.pC(var3)};
            ((bhk.Sv)var4).E = var5;
         }

         return null;
      }

      @Override
      public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) {
         String var7 = "CALL_" + var4;
         this.wS.inc(var7);
         this.pC(bhk.cq.ld, var4, var5, var6);
         return null;
      }

      bhk pC(bhk.cq var1, String var2, Object var3, List var4) {
         boolean var5 = false;
         if (!var5) {
            var5 = bhi.E.contains(var2);
         }

         if (!var5) {
            for (String var7 : bhi.sY) {
               if (var2.startsWith(var7)) {
                  var5 = true;
                  break;
               }
            }
         }

         if (!var5) {
            for (String var14 : bhi.ys) {
               if (var2.endsWith(var14)) {
                  var5 = true;
                  break;
               }
            }
         }

         if (!var5) {
            for (String var15 : bhi.ld) {
               if (var2.contains(var15)) {
                  var5 = true;
                  break;
               }
            }
         }

         if (var5) {
            String var13 = null;
            if (var3 != null) {
               var13 = this.pC(var3);
            }

            String[] var16 = null;
            if (var4 != null) {
               var16 = new String[var4.size()];
               int var8 = 0;

               for (Object var10 : var4) {
                  var16[var8] = this.pC(var10);
                  var8++;
               }
            }

            bhk.Sv var17 = new bhk.Sv(var1, this.A.getCurrentIterationCount(), var2, var13, var16);
            bhi.this.kS.add(var17);
            return var17;
         } else {
            return null;
         }
      }

      private String pC(Object var1) {
         return this.kS.format(var1);
      }

      public String pC(int var1) {
         StringBuilder var2 = new StringBuilder(S.L("JAVA MONITORING REPORT:\n"));
         int var3 = this.A.getCurrentIterationCount();
         Strings.ff(var2, S.L("=> Iteration count: %d\n"), var3);
         Strings.ff(var2, S.L("=> Events:\n%s"), this.wS.formatAllReferences());
         return var2.toString();
      }
   }

   public class Sv implements IEStateHooks, IEEmulatorHooks {
      private int A;
      private Map kS = new HashMap();
      private AddressSegmentMap wS = new AddressSegmentMap(64);
      private AddressSegmentMap UT = new AddressSegmentMap(64);
      private ReferenceCounter E = new ReferenceCounter();
      private ReferenceCounter sY = new ReferenceCounter();

      private Sv() {
      }

      @Override
      public int getPriority() {
         return Integer.MAX_VALUE;
      }

      long pC(EEmulator var1) {
         return var1.getTruncatedRegisterValue("X0");
      }

      long A(EEmulator var1) {
         return var1.getTruncatedRegisterValue("X1");
      }

      @Override
      public Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
         this.A++;
         return null;
      }

      @Override
      public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
         long var4 = var1.getReturnAddress().getValueAsAddress();
         if (this.wS(var4)) {
            this.E.inc(var2);
         }

         switch (ckx.kS(var2)) {
            case -2055685377:
               long var13 = (int)this.pC(var1);
               this.kS.put(var1.currentRequestId(), var13);
               break;
            case -1052079544:
               long var12 = (int)this.pC(var1);
               if (var12 != 0L) {
                  this.wS.remove(var12);
               }

               int var8 = (int)this.A(var1);
               this.kS.put(var1.currentRequestId(), var8);
               break;
            case 500988114:
               bhi.this.kS.add(new bhk.bO(var1.getState().getEvaluationCount(), var1.getPCAddress(), bhk.cq.E, var2));
               break;
            case 1037717026:
               int var11 = (int)this.pC(var1);
               this.kS.put(var1.currentRequestId(), var11);
               break;
            case 1147150343:
               int var10 = (int)this.pC(var1) * (int)this.A(var1);
               this.kS.put(var1.currentRequestId(), var10);
               break;
            case 1222364617:
               int var9 = (int)this.A(var1);
               this.kS.put(var1.currentRequestId(), var9);
               break;
            case 1332620714:
               long var6 = this.pC(var1);
               this.kS.put(var1.currentRequestId(), var6);
         }

         return null;
      }

      @Override
      public void postEvaluateExternal(EEmulator var1, String var2, INativeMethodItem var3, long var4, boolean var6) {
         if (var6) {
            Object var7 = this.kS.remove(var4);
            if (var7 != null) {
               switch (ckx.kS(var2)) {
                  case -2055685377:
                     int var13 = (int)this.pC(var1);
                     if (var13 == 0) {
                        long var14 = (Long)var7;
                        this.UT.remove(var14);
                     }
                     break;
                  case -1052079544:
                  case 1037717026:
                  case 1147150343:
                     int var12 = (Integer)var7;
                     long var9 = this.pC(var1);
                     if (var9 != 0L) {
                        this.wS.add(new bhi.Sv.Av(var9, var12));
                     }
                     break;
                  case 1222364617:
                     long var11 = this.pC(var1);
                     if (var11 != -1L) {
                        int var10 = (Integer)var7;
                        var10 = var10 + 4095 & -4096;
                        this.UT.add(new bhi.Sv.Av(var11, var10));
                     }
                     break;
                  case 1332620714:
                     long var8 = (Long)var7;
                     this.wS.remove(var8);
               }
            }
         }
      }

      @Override
      public Long evaluateSyscall(EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8) {
         if (var6 == null) {
            var6 = ckx.pC(new byte[]{48, 22, 3, 26, 19, 5, 11, 55}, 2, 171) + var5;
         }

         if (this.wS(var2)) {
            this.sY.inc(var6);
         }

         switch (ckx.kS(var6)) {
            case -2055685377:
               long var11 = (int)this.pC(var1);
               this.kS.put(var1.currentRequestId(), var11);
               break;
            case 1222364617:
               int var9 = (int)this.A(var1);
               this.kS.put(var1.currentRequestId(), var9);
         }

         return null;
      }

      @Override
      public void postEvaluateSyscall(
         EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8, long var9, long var11
      ) {
         Object var13 = this.kS.remove(var9);
         if (var13 != null) {
            switch (ckx.kS(var6)) {
               case -2055685377:
                  int var14 = (int)var11;
                  if (var14 == 0) {
                     long var15 = (Long)var13;
                     this.UT.remove(var15);
                  }
                  break;
               case 1222364617:
                  if (var11 != -1L) {
                     int var16 = (Integer)var13;
                     var16 = var16 + 4095 & -4096;
                     this.UT.add(new bhi.Sv.Av(var11, var16));
                  }
            }
         }
      }

      @Override
      public Integer onReadMemory(EState var1, long var2, byte[] var4) {
         if (this.pC(var2)) {
            return null;
         } else {
            int var5 = var1.getEvaluationCount();
            long var6 = var1.getProgramCounter().getValueAsAddress();
            if (!this.wS(var6)) {
               return null;
            } else {
               byte[] var9 = new byte[var4.length];

               String var8;
               try {
                  var1.getMemory().read(var2, var9.length, var9, 0);
                  var8 = Formatter.formatBinaryBlock(var9).toString().trim();
               } catch (MemoryException var11) {
                  var8 = "<unreadable>";
                  var9 = null;
               }

               String var10 = this.kS(var2);
               Object[] var10000 = new Object[]{var5, var6, var2, var8};
               bhi.this.kS.add(new bhk.Ws(var5, var6, bhk.cq.pC, var2, var4.length, var9, var10));
               return null;
            }
         }
      }

      @Override
      public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
         if (this.A(var2)) {
            return null;
         } else {
            int var5 = var1.getEvaluationCount();
            long var6 = var1.getProgramCounter().getValueAsAddress();
            if (!this.wS(var6)) {
               return null;
            } else {
               String var8 = this.kS(var2);
               String var9 = Formatter.formatBinaryBlock(var4).toString().trim();
               Object[] var10000 = new Object[]{var5, var6, var2, var9};
               bhi.this.kS.add(new bhk.Ws(var5, var6, bhk.cq.A, var2, var4.length, var4, var8));
               return null;
            }
         }
      }

      boolean pC(long var1) {
         if (this.A(var1)) {
            return true;
         } else if (Long.compareUnsigned(var1, 2063663104L) >= 0 && Long.compareUnsigned(var1, 2063667200L) < 0) {
            return true;
         } else {
            cdz var3 = ((btp)bhi.this.A.getEmulatedAndroid().getState()).oT().kS();
            if (var3 != null) {
               long var4 = var1 - var3.UT();
               if (var4 == 40L) {
                  return true;
               }
            }

            return false;
         }
      }

      boolean A(long var1) {
         if (Long.compareUnsigned(var1, 2080374784L) >= 0 && Long.compareUnsigned(var1, 2130706432L) < 0) {
            return true;
         } else if (Long.compareUnsigned(var1, 4294967296L) >= 0 && Long.compareUnsigned(var1, 8589934592L) < 0) {
            return true;
         } else {
            return this.wS.getSegmentContaining(var1) != null ? true : this.UT.getSegmentContaining(var1) != null;
         }
      }

      String kS(long var1) {
         cdz var3 = ((btp)bhi.this.A.getEmulatedAndroid().getState()).oT().kS();
         if (var3 == null) {
            return null;
         } else {
            long var4 = var1 - var3.UT();
            if (var4 >= 0L && var4 <= 256L) {
               return Strings.ff("@TLS+0x%X", var4);
            } else {
               cem.Av var6 = var3.NS.pC(var1);
               if (var6 != null) {
                  long var7 = var6.getBegin();
                  String var9 = var6.pC();
                  long var10 = var1 - var7;
                  return Strings.ff("mapping %s+0x%X", var9, var10);
               } else {
                  return null;
               }
            }
         }
      }

      public boolean wS(long var1) {
         if (Long.compareUnsigned(var1, 4294967296L) >= 0 && Long.compareUnsigned(var1, 8589934592L) < 0) {
            return true;
         } else {
            return this.wS.getSegmentContaining(var1) != null ? true : this.UT.getSegmentContaining(var1) != null;
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
               bhi.this.kS.add(new bhk.Av(var4, var5, bhk.cq.kS, var11, var12));
               break;
            case 10:
            case 11:
               bhk.cq var7 = var2 == 10 ? bhk.cq.wS : bhk.cq.UT;
               String var8 = (String)var3.get(0);
               long var9 = (Long)var3.get(1);
               Object[] var10000 = new Object[]{var4, var5, var7, var8, var9};
               bhi.this.kS.add(new bhk.K(var4, var5, var7, var8, var9));
         }
      }

      public String pC(int var1) {
         StringBuilder var2 = new StringBuilder(S.L("NATIVE CODE MONITORING REPORT:\n"));
         if (this.A == 0) {
            var2.append(S.L("N/A")).append("\n");
         } else {
            Strings.ff(var2, S.L("=> Iteration count: %d\n"), this.A);
            Strings.ff(var2, S.L("=> API calls:\n%s"), this.E.formatAllReferences());
            Strings.ff(var2, S.L("=> System calls:\n%s"), this.sY.formatAllReferences());
         }

         return var2.toString();
      }

      @SerDisabled
      class Av implements ISegment {
         long pC;
         int A;

         Av(long var2, int var4) {
            this.pC = var2;
            this.A = var4;
         }

         public Long pC() {
            return this.pC;
         }

         public Long A() {
            return this.pC + this.A;
         }
      }
   }
}
