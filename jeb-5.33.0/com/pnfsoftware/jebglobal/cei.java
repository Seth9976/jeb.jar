package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.RoutineIOHandler;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VMReader;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VMWriter;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.BiMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class cei implements IEEmulatorHooks {
   private static final ILogger ld = GlobalLog.getLogger(cei.class);
   cdz pC;
   EEmulator A;
   IEGlobalContext kS;
   ITypeManager wS;
   IVirtualMemory UT;
   private int gp;
   private RoutineIOHandler oT;
   long E = 4074722352L;
   Map sY = new HashMap();
   long ys = 0L;
   private BiMap fI = new BiMap();
   private Map WR = new HashMap();
   private static boolean NS = true;
   private static Set vP = Set.of("nanosleep", "clock_gettime");
   private String xC;

   public cei(cdz var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var1.fI();
         this.kS = this.A.getGlobalContext();
         this.wS = this.kS.getNativeContext().getTypeManager();
         this.UT = this.A.getVirtualMemory();
         this.oT = new RoutineIOHandler(this.A);
      }
   }

   @Override
   public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      Assert.a(var1 == this.A);
      this.gp++;
      ICallingConvention var4;
      if (var3 != null && var3.getPrototype() != null) {
         var4 = var3.getPrototype().getCallingConvention();
      } else {
         var4 = var1.getGlobalContext().getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
      }

      this.oT.reset(var4);
      if (var3 == null) {
         var3 = this.wS.getTypeLibraryService().findRoutineByName(var2, ProcessorType.ARM64);
      }

      if (var3 != null && var3.getPrototype() != null) {
         StringBuilder var6 = new StringBuilder();
         IStorageEntryGenerator var7 = var4.getInputsGenerator();
         IPrototypeItem var8 = var3.getPrototype();
         List var9 = var8.getParameterNames();
         int var10 = 0;

         for (INativeType var12 : var8.getParameterTypes()) {
            if (var10 > 0) {
               var6.append(", ");
            }

            StorageEntry var13 = var7.next(TypeUtil.getLayoutInfo(var12));
            IEImm var14 = var1.readStorage(var13);
            String var15 = "arg" + var10;
            if (var9 != null && var10 < var9.size()) {
               var15 = (String)var9.get(var10);
            }

            String var16 = var14.toString();
            if (TypeUtil.getNonAlias(var12).getSignature().equals("char*")) {
               String var17 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var14.getValueAsAddress(), 4096);
               if (var17 != null) {
                  var16 = "'" + Formatter.escapeString(var17) + "'";
               }
            }

            Strings.ff(var6, "%s=%s", var15, var16);
            var10++;
         }

         var2 + "(" + var6 + ")";
      }

      boolean var18;
      switch (ckx.kS(var2)) {
         case -2069450001:
            var18 = this.WR();
            break;
         case -2055685377:
            var18 = this.KV();
            break;
         case -2000255321:
            var18 = this.sO();
            break;
         case -1944472958:
         case 1278051349:
            var18 = this.wS(0);
            break;
         case -1893433791:
            var18 = this.Ek();
            break;
         case -1890024024:
            var18 = this.eP();
            break;
         case -1887384875:
            var18 = this.OI();
            break;
         case -1884150040:
            var18 = this.Sc();
            break;
         case -1844232258:
         case -1715743475:
         case -1455090862:
         case -1374615775:
         case -686303436:
         case -596844213:
         case 336476899:
         case 620804547:
         case 970944427:
         case 1035782044:
         case 1437727406:
            var18 = this.wS(-1);
            break;
         case -1818248748:
            var18 = this.Ab();
            break;
         case -1788596673:
            var18 = this.Er();
            break;
         case -1753512469:
            var18 = this.Aj();
            break;
         case -1556587059:
            var18 = this.Pe();
            break;
         case -1530992518:
            var18 = this.xC();
            break;
         case -1466589959:
            var18 = this.Bc();
            break;
         case -1264898490:
            var18 = this.pC();
            break;
         case -1242444983:
         case -934955810:
         case -752397165:
         case -524757057:
         case -508820755:
         case -451119380:
         case 264468366:
         case 396552647:
         case 866270326:
         case 1374766545:
         case 1769507002:
            var18 = this.wS(0);
            break;
         case -1219532917:
            var18 = this.ys();
            break;
         case -1162543950:
            var18 = this.os();
            break;
         case -1147287343:
            var18 = this.oT();
            break;
         case -1063251825:
            var18 = this.ZN();
            break;
         case -1052079544:
            var18 = this.Nq();
            break;
         case -1021420582:
            var18 = this.wQ();
            break;
         case -995070892:
            var18 = this.UH();
            break;
         case -990385290:
            var18 = this.EX();
            break;
         case -941801913:
            var18 = this.NS();
            break;
         case -915678521:
            var18 = this.RW();
            break;
         case -877577566:
            var18 = this.Cu();
            break;
         case -773446183:
            var18 = this.UO();
            break;
         case -756032636:
            var18 = this.pF();
            break;
         case -745109865:
            var18 = this.OB();
            break;
         case -734137099:
            var18 = this.UT();
            break;
         case -649001576:
            var18 = this.UW();
            break;
         case -639175072:
            var18 = this.DL();
            break;
         case -635782928:
            var18 = this.FK();
            break;
         case -605590590:
            var18 = this.Bf();
            break;
         case -542865598:
            var18 = this.Kq();
            break;
         case -479818627:
            var18 = this.kU();
            break;
         case -467332693:
            var18 = this.sY();
            break;
         case -441948227:
            var18 = this.WR();
            break;
         case -421950200:
            var18 = this.mv();
            break;
         case -319146128:
            var18 = this.E();
            break;
         case -306238177:
            var18 = this.vP();
            break;
         case -295546894:
            var18 = this.kS();
            break;
         case -257316586:
            var18 = this.cX();
            break;
         case -182474562:
            var18 = this.hK();
            break;
         case -122030654:
            var18 = this.Bi();
            break;
         case 44553075:
            var18 = this.ck();
            break;
         case 88140425:
            var18 = this.ED();
            break;
         case 116615793:
            var18 = this.e();
            break;
         case 286502636:
            var18 = this.fI();
            break;
         case 314430578:
            var18 = this.xM();
            break;
         case 368595378:
            var18 = this.rl();
            break;
         case 481358749:
            var18 = this.wS();
            break;
         case 515350850:
            var18 = this.DQ();
            break;
         case 783397622:
            var18 = this.hZ();
            break;
         case 812347249:
            var18 = this.gp();
            break;
         case 837498592:
            var18 = this.ah();
            break;
         case 869955841:
            var18 = this.Fm();
            break;
         case 1013613088:
            var18 = this.LM();
            break;
         case 1037717026:
            var18 = this.go();
            break;
         case 1079255280:
            var18 = this.fI();
            break;
         case 1106275357:
            var18 = this.PZ();
            break;
         case 1147150343:
            var18 = this.JF();
            break;
         case 1222364617:
            var18 = this.Xs();
            break;
         case 1247000149:
            var18 = this.z();
            break;
         case 1318178061:
            var18 = this.wS(-1);
            break;
         case 1332620714:
            var18 = this.pg();
            break;
         case 1511956781:
            var18 = this.ZD();
            break;
         case 1607196023:
            var18 = this.FE();
            break;
         case 1831337705:
            var18 = this.Ip();
            break;
         case 1835629133:
            var18 = this.gj();
            break;
         case 1864785885:
            var18 = this.kS();
            break;
         case 1880577101:
            var18 = this.VD();
            break;
         case 1938485116:
            var18 = this.ld();
            break;
         case 1955023519:
            var18 = this.A();
            break;
         case 2083525904:
            var18 = this.PR();
            break;
         default:
            this.gp--;
            return null;
      }

      if (!var18) {
         Object[] var19 = new Object[]{var2};
         this.gp--;
         return null;
      } else {
         Object[] var10000 = new Object[]{var2};
         var1.processStoredReturnAddress(var4.getReturnAddressSlot());
         this.gp--;
         return true;
      }
   }

   private boolean wS(int var1) {
      this.oT.retInt(var1);
      return true;
   }

   private boolean pC() {
      int var1 = this.oT.nextAsInt();
      if (var1 <= 0) {
         return false;
      } else {
         long var2 = this.A.heapAlloc(var1);
         this.oT.retPtr(var2);
         return true;
      }
   }

   private boolean A() {
      long var1 = this.oT.nextAsPtr();
      if (var1 == 0L) {
         return false;
      } else {
         this.A.heapFree(var1);
         return true;
      }
   }

   private boolean kS() {
      this.oT.nextAsInt();
      return true;
   }

   private boolean wS() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      if (var1 == 0L) {
         return false;
      } else {
         String var4 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 4096);
         Object[] var10000 = new Object[]{var4, var3};
         int var5 = this.pC.Aj.pC(var4, var3);
         this.oT.retInt(var5);
         return true;
      }
   }

   private boolean UT() {
      this.oT.nextAsInt();
      this.oT.nextAsInt();
      this.oT.retInt(0);
      return true;
   }

   private boolean E() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      if (var1 != 0L && var3 != 0L) {
         Long var5 = null;
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var3, 256);
         if (var6.equals("r") || var6.equals("rb")) {
            var5 = this.gp(Arrays.asList(0L, var1, 0L));
         } else if (var6.equals("w") || var6.equals("wb")) {
            var5 = this.gp(Arrays.asList(0L, var1, 577L, 1911L));
         }

         if (var5 == null) {
            return false;
         } else {
            this.oT.retPtr(var5);
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean sY() {
      long var1 = this.oT.nextAsPtr();
      Long var3 = this.oT(Arrays.asList(var1));
      if (var3 == null) {
         return false;
      } else {
         this.oT.retLong(var3);
         return true;
      }
   }

   private boolean ys() {
      long var1 = this.oT.nextAsPtr();
      cel.Av var3 = this.pC.Aj.pC((int)var1);
      if (var3 == null) {
         return false;
      } else {
         Boolean var4 = this.pC.Aj.wS((int)var1);
         if (var4 == null) {
            return false;
         } else {
            this.oT.retInt(var4 ? 1 : 0);
            return true;
         }
      }
   }

   private boolean ld() {
      ArrayList var1 = new ArrayList();
      var1.add(this.oT.nextAsPtr());
      int var2 = this.oT.nextAsInt();
      var1.add((long)var2);
      if ((var2 & this.pC.Aj.ys) != 0) {
         var1.add((long)this.oT.nextAsInt());
      }

      Long var3 = this.ld(var1);
      if (var3 == null) {
         return false;
      } else {
         this.oT.retInt(var3.intValue());
         return true;
      }
   }

   private boolean gp() {
      int var1 = this.oT.nextAsInt();
      Long var2 = this.oT(Arrays.asList((long)var1));
      if (var2 == null) {
         return false;
      } else {
         this.oT.retLong(var2);
         return true;
      }
   }

   private boolean oT() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      Long var4 = this.ah(Arrays.asList(var1, (long)var3));
      if (var4 == null) {
         return false;
      } else {
         this.oT.retInt(var4.intValue());
         return true;
      }
   }

   private boolean fI() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsPtr();
      long var4 = this.oT.nextAsSizet();
      Long var6 = this.fI(Arrays.asList((long)var1, var2, var4));
      if (var6 == null) {
         return false;
      } else {
         this.oT.retSizet(var6);
         return true;
      }
   }

   private boolean WR() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsPtr();
      long var4 = this.oT.nextAsSizet();
      Long var6 = this.WR(Arrays.asList((long)var1, var2, var4));
      if (var6 == null) {
         return false;
      } else {
         this.oT.retSizet(var6);
         return true;
      }
   }

   private boolean NS() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsPtr();
      long var4 = this.oT.nextAsSizet();
      long var6 = this.oT.nextAsOfft();
      Long var8 = this.NS(Arrays.asList((long)var1, var2, var4, var6));
      if (var8 == null) {
         return false;
      } else {
         this.oT.retSizet(var8);
         return true;
      }
   }

   private boolean vP() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsPtr();
      long var4 = this.oT.nextAsSizet();
      long var6 = this.oT.nextAsOfft();
      Long var8 = this.vP(Arrays.asList((long)var1, var2, var4, var6));
      if (var8 == null) {
         return false;
      } else {
         this.oT.retSizet(var8);
         return true;
      }
   }

   private boolean xC() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      long var5 = this.oT.nextAsSizet();
      Long var7 = this.UO(Arrays.asList(var1, var3, var5));
      if (var7 == null) {
         return false;
      } else {
         this.oT.retSizet(var7);
         return true;
      }
   }

   private boolean ED() {
      long var1 = this.oT.nextAsInt();
      long var3 = this.oT.nextAsPtr();
      long var5 = this.oT.nextAsPtr();
      long var7 = this.oT.nextAsSizet();
      Long var9 = this.Ab(Arrays.asList(var1, var3, var5, var7));
      if (var9 == null) {
         return false;
      } else {
         this.oT.retSizet(var9);
         return true;
      }
   }

   private boolean Sc() {
      Long var1 = this.Cu(Arrays.asList());
      if (var1 == null) {
         return false;
      } else {
         this.oT.retInt(var1.intValue());
         return true;
      }
   }

   private boolean ah() {
      Long var1 = this.hZ(Arrays.asList());
      if (var1 == null) {
         return false;
      } else {
         this.oT.retInt(var1.intValue());
         return true;
      }
   }

   private boolean eP() {
      Long var1 = this.UW(Arrays.asList());
      if (var1 == null) {
         return false;
      } else {
         this.oT.retInt(var1.intValue());
         return true;
      }
   }

   private boolean UO() {
      int var1 = this.oT.nextAsInt();
      int var2 = this.oT.nextAsInt();
      int var3 = this.oT.nextAsInt();
      Long var4 = this.xC(Arrays.asList((long)var1, (long)var2, (long)var3));
      if (var4 == null) {
         return false;
      } else {
         this.oT.retInt(var4.intValue());
         return true;
      }
   }

   private boolean Ab() {
      return this.rl();
   }

   private boolean rl() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsLong();
      int var4 = this.oT.nextAsInt();
      if (var2 >= -2147483648L && var2 <= 2147483647L) {
         int var5 = this.pC.Aj.pC(var1, (int)var2, var4);
         this.oT.retLong(var5);
         return true;
      } else {
         return false;
      }
   }

   private boolean z() {
      int var1 = this.oT.nextAsInt();
      int var2 = this.oT.nextAsInt();
      int var3 = this.oT.nextAsInt();
      long var4 = this.oT.nextAsPtr();
      int var6 = this.oT.nextAsInt();
      Long var7 = this.ED(Arrays.asList((long)var1, (long)var2, (long)var3, var4, (long)var6));
      if (var7 == null) {
         return false;
      } else {
         this.oT.retInt(var7.intValue());
         return true;
      }
   }

   private boolean Ek() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      Long var4 = this.sY(Arrays.asList(var1, (long)var3));
      if (var4 == null) {
         return false;
      } else {
         this.oT.retInt(var4.intValue());
         return true;
      }
   }

   private boolean hK() {
      int var1 = this.oT.nextAsInt();
      int var2 = this.oT.nextAsInt();
      Long var3 = this.ys(Arrays.asList((long)var1, (long)var2));
      if (var3 == null) {
         return false;
      } else {
         this.oT.retInt(var3.intValue());
         return true;
      }
   }

   private boolean Er() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsPtr();
      Long var4 = this.pC(Arrays.asList((long)var1, var2), false);
      if (var4 == null) {
         return false;
      } else {
         this.oT.retLong(var4);
         return true;
      }
   }

   private boolean FE() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      Long var5 = this.A(Arrays.asList(var1, var3), false);
      if (var5 == null) {
         return false;
      } else {
         this.oT.retLong(var5);
         return true;
      }
   }

   private boolean Aj() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      Long var5 = this.kS(Arrays.asList(var1, var3), false);
      if (var5 == null) {
         return false;
      } else {
         this.oT.retLong(var5);
         return true;
      }
   }

   private boolean EX() {
      Long var1 = this.wS(Arrays.asList());
      if (var1 == null) {
         return false;
      } else {
         this.oT.retInt(var1.intValue());
         return true;
      }
   }

   private boolean LM() {
      long var1 = this.oT.nextAsPtr();
      Long var3 = this.Er(Arrays.asList(var1));
      if (var3 == null) {
         return false;
      } else {
         this.oT.retInt(var3.intValue());
         return true;
      }
   }

   private boolean mv() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      Long var4 = this.FE(Arrays.asList(var1, (long)var3));
      if (var4 == null) {
         return false;
      } else {
         this.oT.retInt(var4.intValue());
         return true;
      }
   }

   private boolean sO() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsLong();
      long var4 = this.oT.nextAsLong();
      long var6 = this.oT.nextAsLong();
      long var8 = this.oT.nextAsLong();
      Long var10 = this.sO(Arrays.asList((long)var1, var2, var4, var6, var8));
      if (var10 == null) {
         return false;
      } else {
         this.oT.retInt(var10.intValue());
         return true;
      }
   }

   private boolean os() {
      Long var1 = this.PR(Arrays.asList());
      if (var1 == null) {
         return false;
      } else {
         this.oT.retInt(var1.intValue());
         return true;
      }
   }

   private boolean Cu() {
      long var1 = this.oT.nextAsPtr();
      int var3 = (int)this.oT.nextAsSizet();
      int var4 = (int)this.oT.nextAsSizet();
      long var5 = this.oT.nextAsPtr();
      if (var1 != 0L && var5 != 0L && var3 != 0 && var4 != 0) {
         int var7 = var4 * var3;
         byte[] var8 = new byte[var7];
         int var9 = this.pC.Aj.A((int)var5, var7, var8, 0);
         int var10 = var9 / var3;
         if (var9 <= 0) {
            this.oT.retPtr(0L);
            return true;
         } else {
            VirtualMemoryUtil.writeBytes(this.UT, var1, var8, 0, var10 * var3);
            this.oT.retSizet(var10);
            return true;
         }
      } else {
         this.oT.retPtr(0L);
         return true;
      }
   }

   private boolean hZ() {
      long var1 = this.oT.nextAsPtr();
      int var3 = (int)this.oT.nextAsSizet();
      int var4 = (int)this.oT.nextAsSizet();
      long var5 = this.oT.nextAsPtr();
      if (var1 != 0L && var5 != 0L && var3 != 0 && var4 != 0) {
         int var7 = var4 * var3;
         byte[] var8 = new byte[var7];
         VirtualMemoryUtil.readBytes(this.UT, var1, var8, 0, var7);
         int var9 = this.pC.Aj.pC((int)var5, var7, var8, 0);
         int var10 = var9 / var3;
         this.oT.retSizet(var10);
         return true;
      } else {
         this.oT.retPtr(0L);
         return true;
      }
   }

   private boolean UW() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      long var4 = this.oT.nextAsPtr();
      if (var1 != 0L && var3 > 0 && var4 != 0L) {
         byte[] var6 = new byte[var3];
         int var7 = this.pC.Aj.pC((int)var4, var3 - 1, 10, true, var6, 0);
         if (var7 == 0) {
            this.oT.retPtr(0L);
            return true;
         } else {
            VirtualMemoryUtil.writeBytes(this.UT, var1, var6, 0, var7);
            VirtualMemoryUtil.writeByteSafe(this.UT, var1 + var7, (byte)0);
            this.oT.retPtr(var1);
            return true;
         }
      } else {
         this.oT.retPtr(0L);
         return true;
      }
   }

   private boolean PR() {
      long var1 = this.oT.nextAsPtr();
      this.pC.Aj.pC((int)var1, 0, this.pC.Aj.A);
      return true;
   }

   private boolean cX() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      int var4 = this.oT.nextAsInt();
      int var5 = this.pC.Aj.pC((int)var1, var3, var4);
      this.oT.retInt(var5 >= 0 ? 0 : -1);
      return true;
   }

   private boolean DQ() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.pC.Aj.pC((int)var1, 0, this.pC.Aj.kS);
      this.oT.retInt(var3);
      return true;
   }

   private boolean ZN() {
      return this.cX();
   }

   private boolean OB() {
      return this.DQ();
   }

   private boolean pF() {
      long var1 = this.oT.nextAsPtr();
      this.oT.nextAsInt();
      String var3;
      if (var1 == 0L) {
         var3 = "";
      } else {
         var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 256);
      }

      long var4 = this.E;
      this.E += 16L;
      this.sY.put(var4, var3);
      this.oT.retPtr(var4);
      return true;
   }

   private boolean Bc() {
      long var1 = this.oT.nextAsPtr();
      String var3 = (String)this.sY.remove(var1);
      int var4 = var3 == null ? -1 : 0;
      this.oT.retInt(var4);
      return true;
   }

   private boolean OI() {
      this.oT.retPtr(this.ys);
      return true;
   }

   private boolean Bf() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      if (var3 == 0L) {
         this.oT.retPtr(0L);
         return true;
      } else {
         String var5 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var3, 256);
         Object[] var10000 = new Object[]{var1, var5};
         long var6;
         if (var1 == 0L) {
            var6 = this.pC.WR.A(var5);
         } else {
            if (var1 == -1L) {
               this.oT.retPtr(0L);
               return true;
            }

            String var8 = (String)this.sY.get(var1);
            if (var8 == null) {
               var10000 = new Object[]{var8, var5};
               this.oT.retPtr(0L);
               return true;
            }

            cee.Sv var9 = this.pC.WR.pC(var8);
            if (var9 == null) {
               var10000 = new Object[]{var8, var5};
               this.oT.retPtr(0L);
               return true;
            }

            var6 = var9.pC(var5);
         }

         if (var6 != 0L) {
            this.fI.put(var6, var5);
         }

         this.oT.retPtr(var6);
         return true;
      }
   }

   @Override
   public Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
      String var5 = (String)this.fI.get(var2);
      if (var5 == null) {
         return null;
      } else {
         Object[] var10000 = new Object[]{var5};
         return var1.hooksEvaluateExternal(var5, null);
      }
   }

   private boolean Pe() {
      long var1 = this.oT.nextAsPtr();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 4096);
      int var4 = Conversion.stringToInt(var3);
      this.oT.retInt(var4);
      return true;
   }

   private boolean ck() {
      long var1 = this.oT.nextAsPtr();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 65536);
      int var4 = var3.length();
      this.oT.retInt(var4);
      return true;
   }

   private boolean RW() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      long var5 = this.oT.nextAsLong();

      try {
         for (int var7 = 0; var7 < var5; var7++) {
            byte var8 = this.UT.readByte(var3 + var7);
            this.UT.writeByte(var1 + var7, (byte)var8);
            if (var8 == 0) {
               break;
            }
         }
      } catch (MemoryException var9) {
         return false;
      }

      this.oT.retPtr(var1);
      return true;
   }

   private boolean e() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      int var4 = this.oT.nextAsInt();
      byte[] var5 = new byte[var4];
      Arrays.fill(var5, (byte)var3);
      if (!VirtualMemoryUtil.writeSafe(this.UT, var1, var5)) {
         return false;
      } else {
         this.oT.retPtr(var1);
         return true;
      }
   }

   private boolean xM() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      int var5 = this.oT.nextAsInt();
      byte[] var6 = new byte[var5];
      if (!VirtualMemoryUtil.readSafe(this.UT, var3, var6)) {
         return false;
      } else if (!VirtualMemoryUtil.writeSafe(this.UT, var1, var6)) {
         return false;
      } else {
         this.oT.retPtr(var1);
         return true;
      }
   }

   private boolean kU() {
      return this.xM();
   }

   private boolean Kq() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      int var5 = this.oT.nextAsInt();
      byte[] var6 = new byte[var5];
      if (!VirtualMemoryUtil.readSafe(this.UT, var1, var6)) {
         return false;
      } else {
         byte[] var7 = new byte[var5];
         if (!VirtualMemoryUtil.readSafe(this.UT, var3, var7)) {
            return false;
         } else {
            int var8 = 0;

            for (int var9 = 0; var9 < var5; var9++) {
               var8 = Integer.compare(var6[var9] & 255, var7[var9] & 255);
               if (var8 != 0) {
                  break;
               }
            }

            Object[] var10000 = new Object[]{var5, Formatter.escapeBytes(var6), Formatter.escapeBytes(var7), var8};
            this.oT.retInt(var8);
            return true;
         }
      }
   }

   private boolean go() {
      int var1 = this.oT.nextAsInt();
      if (var1 >= 0 && var1 < 10485760) {
         long var2 = this.A.heapAlloc(var1);
         this.oT.retPtr(var2);
         Object[] var10000 = new Object[]{var1, var2};
         return true;
      } else {
         return false;
      }
   }

   private boolean JF() {
      int var1 = this.oT.nextAsInt();
      int var2 = this.oT.nextAsInt();
      int var3 = var1 * var2;
      if (var3 >= 0 && var3 < 10485760) {
         long var4 = this.A.heapAlloc(var3);
         this.oT.retPtr(var4);
         Object[] var10000 = new Object[]{var3, var4};
         return true;
      } else {
         return false;
      }
   }

   private boolean Nq() {
      long var1 = this.oT.nextAsPtr();
      int var3 = this.oT.nextAsInt();
      if (var3 == 0 && var1 != 0L) {
         this.A.heapFree(var1);
         return true;
      } else if (var3 >= 0 && var3 < 10485760) {
         long var4;
         if (var1 == 0L) {
            var4 = this.A.heapAlloc(var3);
         } else {
            var4 = this.A.heapRealloc(var1, var3);
         }

         Object[] var10000 = new Object[]{var3, var4};
         this.oT.retPtr(var4);
         return true;
      } else {
         return false;
      }
   }

   private boolean pg() {
      long var1 = this.oT.nextAsPtr();
      if (var1 != 0L) {
         this.A.heapFree(var1);
      }

      return true;
   }

   private boolean gj() {
      int var1 = this.oT.nextAsInt();
      int var2 = -1;
      if (var1 == 39) {
         var2 = this.UT.getPageSize();
      }

      this.oT.retInt(var2);
      return true;
   }

   private boolean ZD() {
      long var1 = this.oT.nextAsLong();
      long var3;
      if (var1 == 16L) {
         var3 = this.pC.EX;
      } else {
         if (var1 != 26L) {
            return false;
         }

         var3 = this.pC.LM;
      }

      this.oT.retLong(var3);
      return true;
   }

   private boolean DL() {
      this.oT.retInt(536879650);
      return true;
   }

   private boolean UH() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      Long var5 = this.kS(Arrays.asList(var1, var3));
      if (var5 == null) {
         return false;
      } else {
         this.oT.retInt(var5.intValue());
         return true;
      }
   }

   private boolean VD() {
      int var1 = this.oT.nextAsInt();
      long var2 = this.oT.nextAsPtr();
      Long var4 = this.A(Arrays.asList((long)var1, var2));
      if (var4 == null) {
         return false;
      } else {
         this.oT.retInt(var4.intValue());
         return true;
      }
   }

   private boolean Xs() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsSizet();
      int var5 = this.oT.nextAsInt();
      int var6 = this.oT.nextAsInt();
      int var7 = this.oT.nextAsInt();
      long var8 = this.oT.nextAsSizet();
      Long var10 = this.rl(Arrays.asList(var1, var3, (long)var5, (long)var6, (long)var7, var8));
      if (var10 == null) {
         return false;
      } else {
         this.oT.retPtr(var10);
         return true;
      }
   }

   private boolean KV() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsSizet();
      Long var5 = this.z(Arrays.asList(var1, var3));
      if (var5 == null) {
         return false;
      } else {
         this.oT.retInt(var5.intValue());
         return true;
      }
   }

   private boolean FK() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsSizet();
      int var5 = this.oT.nextAsInt();
      Long var6 = this.Ek(Arrays.asList(var1, var3, (long)var5));
      if (var6 == null) {
         return false;
      } else {
         this.oT.retInt(var6.intValue());
         return true;
      }
   }

   private boolean Bi() {
      long var1 = this.oT.nextAsPtr();
      if (var1 == 0L) {
         this.oT.retPtr(0L);
         return true;
      } else {
         String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 4096);
         if (var3 != null && var3.length() != 0 && !var3.contains("=")) {
            long var4 = (Long)this.WR.getOrDefault(var3, 0L);
            Object[] var10000 = new Object[]{var3, var4};
            this.oT.retPtr(var4);
            return true;
         } else {
            this.oT.retPtr(0L);
            return true;
         }
      }
   }

   private boolean wQ() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      int var5 = this.oT.nextAsInt();
      if (var1 == 0L) {
         this.oT.retInt(-1);
         return true;
      } else {
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 4096);
         if (var6 == null || var6.length() == 0 || var6.contains("=")) {
            this.oT.retInt(-1);
            return true;
         } else if (var3 == 0L) {
            this.oT.retInt(-1);
            return true;
         } else {
            String var7 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var3, 4096);
            if (var7 == null) {
               this.oT.retInt(-1);
               return true;
            } else {
               Object[] var10000 = new Object[]{var6, var7, var5};
               if (this.WR.containsKey(var6) && var5 == 0) {
                  this.oT.retInt(0);
                  return true;
               } else {
                  byte[] var8 = Strings.encodeASCII(var7);
                  long var9 = this.A.heapAlloc(var8.length + 1);
                  if (VirtualMemoryUtil.writeSafe(this.UT, var9, var8) && VirtualMemoryUtil.writeByteSafe(this.UT, var9 + var8.length, (byte)0)) {
                     this.WR.put(var6, var9);
                     this.oT.retInt(0);
                     return true;
                  } else {
                     this.oT.retInt(-1);
                     return true;
                  }
               }
            }
         }
      }
   }

   private boolean PZ() {
      long var1 = this.oT.nextAsPtr();
      if (var1 == 0L) {
         this.oT.retInt(-1);
         return true;
      } else {
         String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 4096);
         if (var3 != null && var3.length() != 0 && !var3.contains("=")) {
            Long var4 = (Long)this.WR.remove(var3);
            if (var4 != null) {
               this.A.heapFree(var4);
            }

            this.oT.retInt(0);
            return true;
         } else {
            this.oT.retInt(-1);
            return true;
         }
      }
   }

   private boolean Ip() {
      long var1 = this.oT.nextAsPtr();
      long var3 = this.oT.nextAsPtr();
      long var5 = this.oT.nextAsPtr();
      long var7 = this.oT.nextAsPtr();
      Object[] var10000 = new Object[]{var1, var3, var5, var7};
      int var9 = 10000 + (int)(10000.0 + Math.random());
      if (!VirtualMemoryUtil.writeLEIntSafe(this.UT, var1, var9)) {
         this.oT.retInt(-1);
         return true;
      } else {
         var10000 = new Object[]{var9, var9, var5};
         this.oT.retInt(0);
         return true;
      }
   }

   private boolean Fm() {
      long var1 = this.oT.nextAsInt();
      Object[] var10000 = new Object[]{var1};
      this.oT.retInt(0);
      return true;
   }

   @Override
   public Long evaluateSyscall(EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8) {
      if (var6 == null) {
         return null;
      } else {
         if (vP.contains(var6)) {
            var6.equals(this.xC);
         }

         this.xC = var6;
         Assert.a(var1 == this.A);
         Long var10 = this.pC(var6, var8);
         if (var10 != null) {
            return var10;
         } else if (NS) {
            JebCoreService.notifySilentExceptionToClient(
               new RuntimeException(
                  ckx.pC(
                        new byte[]{34, 3, 21, 20, 7, 73, 20, 17, 7, 67, 73, 15, 69, 0, 95, 86, 77, 19, 69, 77, 66, 92, 87, 88, 73, 78, 59, 23, 5, 67, 86, 69},
                        2,
                        169
                     )
                     + var6
               )
            );
            if (var7 != null && var7.getPrototype() != null) {
               INativeType var11 = var7.getPrototype().getReturnType();
               if (TypeUtil.isPointer(var11)) {
                  return 0L;
               }
            }

            return -1L;
         } else {
            return null;
         }
      }
   }

   private Long pC(String var1, List var2) {
      switch (ckx.kS(var1)) {
         case -2069450001:
            return this.WR(var2);
         case -2055685377:
            return this.z(var2);
         case -2000255321:
            return this.sO(var2);
         case -1910595826:
            return this.Aj(var2);
         case -1893433791:
            return this.sY(var2);
         case -1890024024:
            return this.UW(var2);
         case -1884150040:
            return this.Cu(var2);
         case -1788596673:
            return this.pC(var2, false);
         case -1753512469:
            return this.kS(var2, false);
         case -1743549659:
            return this.kS(var2, true);
         case -1679424452:
            return this.hK(var2);
         case -1566396846:
            return this.EX(var2);
         case -1549952515:
            return this.E(var2, false);
         case -1530992518:
            return this.UO(var2);
         case -1162543950:
            return this.PR(var2);
         case -1160625442:
            return this.UT(var2);
         case -1147287343:
            return this.ah(var2);
         case -995070892:
            return this.kS(var2);
         case -990385290:
            return this.wS(var2);
         case -941801913:
            return this.NS(var2);
         case -887424065:
            return this.Sc(var2);
         case -818866670:
            return this.wS(var2, false);
         case -773446183:
            return this.xC(var2);
         case -635782928:
            return this.Ek(var2);
         case -565384625:
            return this.eP(var2);
         case -523254825:
            return this.wS(var2, true);
         case -421950200:
            return this.FE(var2);
         case -306238177:
            return this.vP(var2);
         case -295546894:
            return this.cX(var2);
         case -182474562:
            return this.ys(var2);
         case 88140425:
            return this.Ab(var2);
         case 135431766:
            return this.UT(var2, true);
         case 286502636:
            return this.fI(var2);
         case 368595378:
            return this.ED(var2);
         case 386081339:
            return this.DQ(var2);
         case 699403593:
            return this.pC(var2);
         case 812347249:
            return this.oT(var2);
         case 833236970:
            return this.gp(var2);
         case 837498592:
            return this.hZ(var2);
         case 1013613088:
            return this.Er(var2);
         case 1200506280:
            return this.LM(var2);
         case 1222364617:
            return this.rl(var2);
         case 1229968655:
            return this.UT(var2, false);
         case 1460207480:
            return this.E(var2, true);
         case 1607196023:
            return this.A(var2, false);
         case 1659165536:
            return this.mv(var2);
         case 1675016108:
            return this.pC(var2, true);
         case 1760742602:
            return this.os(var2);
         case 1764028985:
            return this.E(var2);
         case 1805940393:
            return this.A(var2, true);
         case 1880577101:
            return this.A(var2);
         case 1938485116:
            return this.ld(var2);
         default:
            return null;
      }
   }

   private Long pC(List var1) {
      long var2 = (Long)var1.get(0);
      var1.get(1);
      INativeType var4 = TypeUtil.getNonAlias(this.wS.getType(ckx.pC(new byte[]{55, 6, 29, 28, 1, 25, 2, 11}, 2, 117)));
      VMReader.Buf var5 = new VMReader(this.UT).read(var2, var4);
      long var6 = var5.getLong(ckx.pC(new byte[]{55, 25, 47, 10, 23, 10}, 2, 45));
      long var8 = var5.getLong(ckx.pC(new byte[]{55, 25, 47, 23, 1, 12, 4}, 2, 29));
      if (var6 != 0L && var8 != 0L) {
         Object[] var10000 = new Object[]{var6, var8};
      }

      return 0L;
   }

   private Long A(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      if (var3 == 0L) {
         return null;
      } else {
         long var5;
         if (var2 == 0 || var2 == 5 || var2 == 8) {
            var5 = 1760152083L;
         } else if (var2 != 1 && var2 != 6 && var2 != 4 && var2 != 7 && var2 != 9) {
            if (var2 != 2 && var2 != 3) {
               Object[] var10000 = new Object[]{var2};
               return null;
            }

            var5 = 0L;
         } else {
            var5 = 7331L;
         }

         int var7 = this.A.getState().getEvaluationCount();
         int var8 = var7 / 10000000;
         int var9 = var7 % 10000000;
         int var10 = var9 * 100;
         INativeType var11 = TypeUtil.getNonAlias(this.wS.getType(ckx.pC(new byte[]{55, 6, 29, 28, 1, 25, 2, 11}, 2, 232)));
         VMWriter var12 = new VMWriter(this.UT, var3, var11);
         var12.set(ckx.pC(new byte[]{94, 2, 41, 44, 22, 6}, 1, 42), var5 + var8);
         var12.set(ckx.pC(new byte[]{31, 2, 41, 49, 29, 22, 6}, 1, 107), var10);
         return 0L;
      }
   }

   private Long kS(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      if (var2 != 0L) {
         int var8 = this.A.getState().getEvaluationCount();
         int var9 = var8 / 10000000;
         int var10 = var8 % 10000000;
         int var11 = var10 * 100;
         INativeType var12 = TypeUtil.getNonAlias(this.wS.getType(ckx.pC(new byte[]{-82, 29, 4, 8, 19, 23, 13}, 1, 218)));
         VMWriter var13 = new VMWriter(this.UT, var2, var12);
         var13.set(ckx.pC(new byte[]{43, 2, 41, 44, 22, 6}, 1, 95), 1760152083L + var9);
         var13.set(ckx.pC(new byte[]{55, 25, 47, 12, 1, 12, 4}, 2, 28), var11 / 1000);
      }

      if (var4 != 0L) {
         INativeType var6 = TypeUtil.getNonAlias(this.wS.getType(ckx.pC(new byte[]{55, 6, 29, 28, 8, 6, 9, 13}, 2, 170)));
         VMWriter var7 = new VMWriter(this.UT, var4, var6);
         var7.set(ckx.pC(new byte[]{94, 14, 37, 50, 4, 7, 27, 1, 17, 22, 4, 18, 22, 7}, 1, 42), 0);
         var7.set(ckx.pC(new byte[]{-57, 14, 37, 59, 23, 7, 0, 29, 4, 8}, 1, 179), 0);
      }

      return 0L;
   }

   private Long wS(List var1) {
      int var2 = this.UT.getPageSize();
      return (long)var2;
   }

   private Long UT(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      String var5 = this.pC.Aj.pC();
      byte[] var6 = Strings.encodeUTF8(var5);
      int var7 = var6.length + 1;
      if (var7 > var4) {
         return 0L;
      } else {
         var6 = Arrays.copyOf(var6, var7);
         VirtualMemoryUtil.writeBytes(this.UT, var2, var6, 0, var7);
         return var2;
      }
   }

   private Long E(List var1) {
      return this.UT(Arrays.asList((Long)var1.get(0), (long)this.pC.Aj.UT));
   }

   private Long sY(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      String var5 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var2, 4096);
      Object[] var10000 = new Object[]{var5, var4};
      return 0L;
   }

   private Long ys(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      Object[] var10000 = new Object[]{var2, var3};
      return 0L;
   }

   private Long ld(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      int var5 = 0;
      if ((var4 & this.pC.Aj.ys) != 0) {
         var5 = ((Long)var1.get(2)).intValue();
      }

      if (var2 == 0L) {
         return -1L;
      } else {
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var2, 4096);
         Object[] var10000 = new Object[]{var6, var4};
         return !var6.startsWith("/") ? null : this.pC(var6, var4, var5);
      }
   }

   private Long gp(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      int var6 = 0;
      if ((var5 & this.pC.Aj.ys) != 0) {
         var6 = ((Long)var1.get(3)).intValue();
      }

      if (var3 == 0L) {
         return -1L;
      } else {
         String var7 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var3, 4096);
         Object[] var10000 = new Object[]{var2, var7, var5};
         return !var7.startsWith("/") ? null : this.pC(var7, var5, var6);
      }
   }

   private Long pC(String var1, int var2, int var3) {
      int var4 = this.pC.Aj.pC(var1, var2, var3);
      if (var4 < 0) {
         if (!var1.startsWith("/proc/")) {
            return null;
         }

         Object[] var10000 = new Object[]{var1};
      }

      Object[] var5 = new Object[]{var4, var4};
      return (long)var4;
   }

   private Long oT(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = this.pC.Aj.A(var2);
      return (long)var3;
   }

   private Long pC(List var1, boolean var2) {
      int var3 = ((Long)var1.get(0)).intValue();
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.pC.Aj.pC(var3, this.UT, var4, var2);
      return (long)var6;
   }

   private Long A(List var1, boolean var2) {
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(0), 4096);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.pC.Aj.pC(var3, this.UT, var4, var2);
      return (long)var6;
   }

   private Long kS(List var1, boolean var2) {
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(0), 4096);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.pC.Aj.A(var3, this.UT, var4, var2);
      return (long)var6;
   }

   private Long wS(List var1, boolean var2) {
      int var3 = ((Long)var1.get(0)).intValue();
      String var4 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(1), 4096);
      long var5 = (Long)var1.get(2);
      int var7 = ((Long)var1.get(3)).intValue();
      Object[] var10000 = new Object[]{var3, var4, var5, var7};
      if (!var4.startsWith("/")) {
         return null;
      } else {
         int var8 = this.pC.Aj.pC(var4, this.UT, var5, var2);
         return (long)var8;
      }
   }

   private Long UT(List var1, boolean var2) {
      int var3 = ((Long)var1.get(0)).intValue();
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.pC.Aj.A(var3, this.UT, var4, var2);
      return (long)var6;
   }

   private Long E(List var1, boolean var2) {
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(0), 4096);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.pC.Aj.kS(var3, this.UT, var4, var2);
      return (long)var6;
   }

   private Long fI(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var5};
      int var6 = this.pC.Aj.pC(var2, var5, this.UT, var3, null);

      try {
         byte[] var7 = VirtualMemoryUtil.dump(this.UT, 5242880L, 6291456L);
         IO.writeFile(new File("C:\\Users\\nicol\\jeb2\\jeb2-plugin-dexdec\\testdata\\packers\\temp_verimatrix\\dump"), var7);
      } catch (Exception var8) {
      }

      return (long)var6;
   }

   private Long WR(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var5};
      int var6 = this.pC.Aj.A(var2, var5, this.UT, var3, null);
      return (long)var6;
   }

   private Long NS(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      int var6 = (int)((Long)var1.get(3)).longValue();
      Object[] var10000 = new Object[]{var2, var3, var5};
      int var7 = this.pC.Aj.pC(var2, var5, this.UT, var3, var6);
      return (long)var7;
   }

   private Long vP(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      int var6 = (int)((Long)var1.get(3)).longValue();
      Object[] var10000 = new Object[]{var2, var3, var5, var6};
      int var7 = this.pC.Aj.A(var2, var5, this.UT, var3, var6);
      return (long)var7;
   }

   private Long xC(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var4};
      int var5 = this.pC.Aj.pC(var2, var3, var4);
      return (long)var5;
   }

   private Long ED(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = ((Long)var1.get(2)).intValue();
      long var5 = (Long)var1.get(3);
      int var7 = ((Long)var1.get(4)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var4, var5, var7};
      long var8 = (long)var3 << 32 | var4 & 4294967295L;
      if (var8 <= 2147483647L && var8 >= -2147483648L) {
         int var10 = this.pC.Aj.pC(var2, (int)var8, var7);
         if (var10 < 0) {
            return -1L;
         } else {
            if (var5 != 0L) {
               VirtualMemoryUtil.writeLELongSafe(this.UT, var5, var10);
            }

            return 0L;
         }
      } else {
         return null;
      }
   }

   private Long Sc(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      long var4 = 0L;
      Object[] var10000 = new Object[]{var2, var3};
      cel.Av var6 = this.pC.Aj.pC(var2);
      if (var6 == null) {
         return -1L;
      } else {
         int var7 = var3 & 0xFF;
         int var8 = var3 >> 8 & 0xFF;
         int var9 = var3 >> 16 & 16383;
         int var10 = var3 >> 30 & 3;
         if (var10 != 0) {
            var4 = (Long)var1.get(2);
            if ((var10 & 1) != 0) {
               byte[] var11 = new byte[var9];
               if (VirtualMemoryUtil.readSafe(this.UT, var4, var11)) {
                  var10000 = new Object[]{var9, Formatter.byteArrayToHex(var11)};
               }
            }
         }

         long var36 = -1L;
         if ("/dev/binder".equals(var6.E()) && var8 == 98) {
            if (var7 == 9 && var9 >= 4 && var10 == 3) {
               if (VirtualMemoryUtil.writeLEIntSafe(this.UT, var4, 8)) {
                  var36 = 0L;
               }
            } else if (var7 == 1 && var9 == 48 && var10 == 3) {
               var10000 = new Object[0];
               byte[] var13 = new byte[48];
               if (VirtualMemoryUtil.readSafe(this.UT, var4, var13)) {
                  long var14 = EndianUtil.littleEndianBytesToLong(var13, 0);
                  long var16 = EndianUtil.littleEndianBytesToLong(var13, 16);
                  long var18 = EndianUtil.littleEndianBytesToLong(var13, 24);
                  long var20 = EndianUtil.littleEndianBytesToLong(var13, 40);
                  byte[] var22 = new byte[(int)var14];
                  VirtualMemoryUtil.readSafe(this.UT, var16, var22);
                  var10000 = new Object[]{var14, Formatter.byteArrayToHex(var22)};
                  VirtualMemoryUtil.writeLELongSafe(this.UT, var4 + 8L, var14);
                  int var23 = EndianUtil.littleEndianBytesToInt(var22, 0);
                  int var24 = var23 & 0xFF;
                  int var25 = var23 >> 8 & 0xFF;
                  if (var25 == 99) {
                     if (var24 == 4) {
                        var36 = 0L;
                     } else if (var24 == 5) {
                        var36 = 0L;
                     } else if (var24 == 0) {
                        VMReader.Buf var26 = new VMReader.Buf(var22, 4, null, this.UT);
                        long var27 = var26.getLongAt(0);
                        int var29 = var26.getIntAt(16);
                        int var30 = var26.getIntAt(20);
                        long var31 = var26.getLongAt(32);
                        var26.getLongAt(40);
                        long var33 = var26.getLongAt(48);
                        var26.getLongAt(56);
                        var10000 = new Object[]{var27, var29, var30};
                        byte[] var35 = new byte[(int)var31];
                        VirtualMemoryUtil.readBytes(this.UT, var33, var35, 0, var35.length);
                        var10000 = new Object[]{Formatter.formatBinaryBlock(var35)};
                        if (var18 >= 12L) {
                           VirtualMemoryUtil.writeLEIntSafe(this.UT, var20, 29196);
                           VirtualMemoryUtil.writeLEIntSafe(this.UT, var20 + 4L, 29190);
                           VirtualMemoryUtil.writeLEIntSafe(this.UT, var20 + 8L, 29189);
                           VirtualMemoryUtil.writeLELongSafe(this.UT, var4 + 8L, var14);
                           VirtualMemoryUtil.writeLELongSafe(this.UT, var4 + 32L, 12L);
                        }

                        var36 = 0L;
                     }
                  }
               }
            } else {
               var10000 = new Object[0];
            }
         }

         return var36;
      }
   }

   private Long ah(List var1) {
      String var2 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(0), 4096);
      int var3 = ((Long)var1.get(1)).intValue();
      Object[] var10000 = new Object[]{var2, var3};
      if (!var2.startsWith("/")) {
         return null;
      } else {
         int var4 = this.pC.Aj.A(var2, var3);
         return (long)var4;
      }
   }

   private Long eP(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(1), 4096);
      int var4 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var4};
      if (!var3.startsWith("/")) {
         return null;
      } else {
         int var5 = this.pC.Aj.A(var3, var4);
         return (long)var5;
      }
   }

   private Long UO(List var1) {
      String var2 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(0), 256);
      Object[] var10000 = new Object[]{var2};
      String var3 = this.pC.Aj.wS(var2);
      if (var3 == null) {
         return -1L;
      } else {
         byte[] var4 = Strings.encodeASCII(var3);
         int var5 = var4.length + 1;
         var4 = Arrays.copyOf(var4, var5);
         long var6 = (Long)var1.get(1);
         int var8 = ((Long)var1.get(2)).intValue();
         if (var5 > var8) {
            var5 = var8;
         }

         try {
            long var9 = this.UT.write(var6, var5, var4, 0);
            return var9;
         } catch (MemoryException var11) {
            return null;
         }
      }
   }

   private Long Ab(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, (Long)var1.get(1), 256);
      Object[] var10000 = new Object[]{var2, var3};
      if (!var3.startsWith("/")) {
         return null;
      } else {
         String var4 = this.pC.Aj.wS(var3);
         if (var4 == null) {
            return -1L;
         } else {
            byte[] var5 = Strings.encodeASCII(var4);
            int var6 = var5.length + 1;
            var5 = Arrays.copyOf(var5, var6);
            long var7 = (Long)var1.get(2);
            int var9 = ((Long)var1.get(3)).intValue();
            if (var6 > var9) {
               var6 = var9;
            }

            try {
               long var10 = this.UT.write(var7, var6, var5, 0);
               return var10;
            } catch (MemoryException var12) {
               return null;
            }
         }
      }
   }

   public static String pC(int var0) {
      if (var0 == 0) {
         return "0";
      } else {
         String var1 = "";
         if ((var0 & 1) != 0) {
            var0 &= -2;
            var1 = var1 + "R";
         }

         if ((var0 & 2) != 0) {
            var0 &= -3;
            var1 = var1 + "W";
         }

         if ((var0 & 4) != 0) {
            var0 &= -5;
            var1 = var1 + "X";
         }

         if (var0 != 0) {
            var1 = var1 + "|" + var0;
         }

         return var1;
      }
   }

   public static int A(int var0) {
      byte var1 = 0;
      if ((var0 & 1) == 1) {
         var1 |= 1;
      }

      if ((var0 & 2) == 2) {
         var1 |= 2;
      }

      if ((var0 & 4) == 4) {
         var1 |= 4;
      }

      return var1;
   }

   public static String kS(int var0) {
      StringBuilder var1 = new StringBuilder();
      if ((var0 & 1) != 0) {
         var1.append("R");
      }

      if ((var0 & 2) != 0) {
         var1.append("W");
      }

      if ((var0 & 4) != 0) {
         var1.append("X");
      }

      return var1.toString();
   }

   private Long rl(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      int var6 = ((Long)var1.get(2)).intValue();
      int var7 = ((Long)var1.get(3)).intValue();
      int var8 = ((Long)var1.get(4)).intValue();
      long var9 = (Long)var1.get(5);
      if (var2 != 0L && var2 % this.UT.getPageSize() != 0L) {
         return -1L;
      } else if (var4 > 0L && var4 < 2147483647L) {
         if (var9 >= 0L && var9 < 2147483647L && var9 % this.UT.getPageSize() == 0L) {
            int var11 = A(var6);
            var2 = VirtualMemoryUtil.allocate(this.UT, 5242880L, (int)var4, var11);
            if (var2 == -1L) {
               return -1L;
            } else {
               if ((var7 & 32) == 0) {
                  this.pC.Aj.pC(var8, (int)var9, this.pC.Aj.A);
                  int var12 = this.pC.Aj.pC(var8, (int)var4, this.UT, var2, null);
                  if (var12 != (int)var4) {
                     Object[] var10000 = new Object[]{var4, var12};
                  }
               }

               Object[] var14 = new Object[]{var2, var2 + var4, kS(var11)};
               return var2;
            }
         } else {
            return -1L;
         }
      } else {
         return -1L;
      }
   }

   private Long z(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var2, var4};
      if (var2 != 0L && var2 % this.UT.getPageSize() != 0L) {
         return -1L;
      } else if (var4 <= 0L || var4 >= 2147483647L) {
         return -1L;
      } else if (!VirtualMemoryUtil.deallocate(this.UT, var2, (int)var4)) {
         return -1L;
      } else {
         var10000 = new Object[]{var2, var2 + var4};
         return 0L;
      }
   }

   private Long Ek(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      int var6 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var4, pC(var6)};
      if (var2 % this.UT.getPageSize() != 0L) {
         return -1L;
      } else if (var4 < 0L) {
         return -1L;
      } else {
         int var7 = A(var6);

         try {
            long var8 = var2;

            for (long var10 = var2 + var4; Long.compareUnsigned(var8, var10) < 0; var8 += this.UT.getPageSize()) {
               this.UT.setPageProtection(var8, var7);
            }
         } catch (MemoryException var12) {
            return -1L;
         }

         var10000 = new Object[]{var2, var2 + var4, kS(var7)};
         return 0L;
      }
   }

   private Long hK(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      int var6 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var4, var6};
      if (var2 % this.UT.getPageSize() != 0L) {
         return -1L;
      } else {
         return var4 < 0L ? -1L : 0L;
      }
   }

   private Long Er(List var1) {
      long var2 = (Long)var1.get(0);
      int[] var4 = new int[2];
      int var5 = this.pC.Aj.pC(var4);
      if (var5 == -1) {
         return -1L;
      } else {
         return !VirtualMemoryUtil.writeInts(this.UT, var2, var4, 0, 2) ? -1L : 0L;
      }
   }

   private Long FE(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      int[] var5 = new int[2];
      int var6 = this.pC.Aj.pC(var5, var4);
      if (var6 == -1) {
         return -1L;
      } else {
         return !VirtualMemoryUtil.writeInts(this.UT, var2, var5, 0, 2) ? -1L : 0L;
      }
   }

   private Long Aj(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = this.pC.Aj.UT(var2);
      return (long)var3;
   }

   private Long EX(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = this.pC.Aj.pC(var2, var3);
      return (long)var4;
   }

   private Long LM(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = ((Long)var1.get(2)).intValue();
      int var5 = this.pC.Aj.pC(var2, var3, Integer.valueOf(var4));
      return (long)var5;
   }

   private Long mv(List var1) {
      return 0L;
   }

   private Long sO(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      long var5 = (Long)var1.get(2);
      long var7 = (Long)var1.get(3);
      long var9 = (Long)var1.get(4);
      Object[] var10000 = new Object[]{var2, var3, var5, var7, var9};
      if (var2 == 4) {
         var10000 = new Object[]{var5};
         return 0L;
      } else {
         if (var2 == 1398164801) {
            if (var3 == 0L) {
               String var17 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var9, 256);
               var10000 = new Object[]{var5, var7, var17};
               return 0L;
            }
         } else if (var2 == 1499557217) {
            var10000 = new Object[]{var3};
            return 0L;
         }

         return -1L;
      }
   }

   private Long os(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var2, var3};
      return 0L;
   }

   private Long Cu(List var1) {
      if (this.pC.kS() == null) {
         return -1L;
      } else {
         int var2 = this.pC.kS().getProcessId();
         return (long)var2;
      }
   }

   private Long hZ(List var1) {
      return this.Cu(var1);
   }

   private Long UW(List var1) {
      if (this.pC.kS() == null) {
         return -1L;
      } else {
         int var2 = (Integer)this.pC.kS().getValue(10);
         return (long)var2;
      }
   }

   private Long PR(List var1) {
      int var2 = 10000 + (int)(10000.0 * Math.random());
      return (long)var2;
   }

   private Long cX(List var1) {
      ((Long)var1.get(0)).intValue();
      return 0L;
   }

   private Long DQ(List var1) {
      ((Long)var1.get(0)).intValue();
      return 0L;
   }
}
