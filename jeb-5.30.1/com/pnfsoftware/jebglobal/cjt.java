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
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cjt implements IEEmulatorHooks {
   private static final ILogger gP = GlobalLog.getLogger(cjt.class);
   private static final int za = 0;
   cjo q;
   EEmulator RF;
   IEGlobalContext xK;
   ITypeManager Dw;
   IVirtualMemory Uv;
   private RoutineIOHandler lm;
   long oW = 4074722352L;
   Map gO = new HashMap();
   long nf = 0L;
   private BiMap zz = new BiMap();
   private Map JY = new HashMap();
   private boolean HF = true;

   public cjt(cjo var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var1.za();
         this.xK = this.RF.getGlobalContext();
         this.Dw = this.xK.getNativeContext().getTypeManager();
         this.Uv = this.RF.getVirtualMemory();
         this.lm = new RoutineIOHandler(this.RF);
      }
   }

   @Override
   public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      Assert.a(var1 == this.RF);
      ICallingConvention var4;
      if (var3 != null && var3.getPrototype() != null) {
         var4 = var3.getPrototype().getCallingConvention();
      } else {
         var4 = var1.getGlobalContext().getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
      }

      this.lm.reset(var4);
      String var5 = var2;
      if (var3 == null) {
         var3 = this.Dw.getTypeLibraryService().findRoutineByName(var2, ProcessorType.ARM64);
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
               String var17 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var14.getValueAsAddress(), 4096);
               if (var17 != null) {
                  var16 = "'" + Formatter.escapeString(var17) + "'";
               }
            }

            Strings.ff(var6, "%s=%s", var15, var16);
            var10++;
         }

         var5 = var2 + "(" + var6 + ")";
      }

      Object[] var10000 = new Object[]{var5};
      boolean var18;
      switch (cvm.xK(var2)) {
         case -2069450001:
            var18 = this.zz();
            break;
         case -2055685377:
            var18 = this.AB();
            break;
         case -1944472958:
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
         case 1278051349:
         case 1437727406:
            var18 = this.xK(-1);
            break;
         case -1893433791:
            var18 = this.io();
            break;
         case -1887384875:
            var18 = this.Dz();
            break;
         case -1788596673:
            var18 = this.Hk();
            break;
         case -1753512469:
            var18 = this.PV();
            break;
         case -1556587059:
            var18 = this.jq();
            break;
         case -1530992518:
            var18 = this.LK();
            break;
         case -1466589959:
            var18 = this.If();
            break;
         case -1264898490:
            var18 = this.q();
            break;
         case -1242444983:
         case -934955810:
         case -752397165:
         case -524757057:
         case -508820755:
         case -451119380:
         case 264468366:
         case 866270326:
         case 1374766545:
         case 1769507002:
            var18 = this.xK(0);
            break;
         case -1147287343:
            var18 = this.za();
            break;
         case -1063251825:
            var18 = this.sH();
            break;
         case -1052079544:
            var18 = this.YN();
            break;
         case -1021420582:
            var18 = this.Tq();
            break;
         case -995070892:
            var18 = this.GY();
            break;
         case -941801913:
            var18 = this.JY();
            break;
         case -915678521:
            var18 = this.Rr();
            break;
         case -877577566:
            var18 = this.oQ();
            break;
         case -756032636:
            var18 = this.wF();
            break;
         case -745109865:
            var18 = this.CE();
            break;
         case -734137099:
            var18 = this.Uv();
            break;
         case -649001576:
            var18 = this.KT();
            break;
         case -639175072:
            var18 = this.Ri();
            break;
         case -635782928:
            var18 = this.CY();
            break;
         case -605590590:
            var18 = this.mI();
            break;
         case -542865598:
            var18 = this.IN();
            break;
         case -479818627:
            var18 = this.Bu();
            break;
         case -467332693:
            var18 = this.gO();
            break;
         case -441948227:
            var18 = this.zz();
            break;
         case -319146128:
            var18 = this.oW();
            break;
         case -306238177:
            var18 = this.HF();
            break;
         case -295546894:
            var18 = this.xK();
            break;
         case -257316586:
            var18 = this.Ef();
            break;
         case -182474562:
            var18 = this.qa();
            break;
         case -122030654:
            var18 = this.WI();
            break;
         case 44553075:
            var18 = this.TX();
            break;
         case 116615793:
            var18 = this.EB();
            break;
         case 286502636:
            var18 = this.lm();
            break;
         case 314430578:
            var18 = this.Xo();
            break;
         case 481358749:
            var18 = this.Dw();
            break;
         case 515350850:
            var18 = this.cC();
            break;
         case 783397622:
            var18 = this.xW();
            break;
         case 812347249:
            var18 = this.gP();
            break;
         case 1037717026:
            var18 = this.rL();
            break;
         case 1079255280:
            var18 = this.lm();
            break;
         case 1106275357:
            var18 = this.Yp();
            break;
         case 1147150343:
            var18 = this.eJ();
            break;
         case 1222364617:
            var18 = this.Wx();
            break;
         case 1318178061:
            var18 = this.xK(-1);
            break;
         case 1332620714:
            var18 = this.Rv();
            break;
         case 1511956781:
            var18 = this.ZT();
            break;
         case 1607196023:
            var18 = this.Me();
            break;
         case 1831337705:
            var18 = this.Gu();
            break;
         case 1864785885:
            var18 = this.xK();
            break;
         case 1938485116:
            var18 = this.nf();
            break;
         case 1955023519:
            var18 = this.RF();
            break;
         case 2083525904:
            var18 = this.Gf();
            break;
         default:
            return null;
      }

      if (!var18) {
         var10000 = new Object[]{var2};
         return null;
      } else {
         var10000 = new Object[]{var2};
         var1.processStoredReturnAddress(var4.getReturnAddressSlot());
         return true;
      }
   }

   private boolean xK(int var1) {
      this.lm.retInt(var1);
      return true;
   }

   private boolean q() {
      int var1 = this.lm.nextAsInt();
      if (var1 <= 0) {
         return false;
      } else {
         long var2 = this.RF.heapAlloc(var1);
         this.lm.retPtr(var2);
         return true;
      }
   }

   private boolean RF() {
      long var1 = this.lm.nextAsPtr();
      if (var1 == 0L) {
         return false;
      } else {
         this.RF.heapFree(var1);
         return true;
      }
   }

   private boolean xK() {
      this.lm.nextAsInt();
      return true;
   }

   private boolean Dw() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      if (var1 == 0L) {
         return false;
      } else {
         String var4 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 4096);
         Object[] var10000 = new Object[]{var4, var3};
         int var5 = this.q.wF.q(var4, var3);
         this.lm.retInt(var5);
         return true;
      }
   }

   private boolean Uv() {
      this.lm.nextAsInt();
      this.lm.nextAsInt();
      this.lm.retInt(0);
      return true;
   }

   private boolean oW() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      if (var1 != 0L && var3 != 0L) {
         Long var5 = null;
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var3, 256);
         if (var6.equals("r") || var6.equals("rb")) {
            var5 = this.oW(Arrays.asList(0L, var1, 0L));
         } else if (var6.equals("w") || var6.equals("wb")) {
            var5 = this.oW(Arrays.asList(0L, var1, 577L, 1911L));
         }

         if (var5 == null) {
            return false;
         } else {
            this.lm.retPtr(var5);
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean gO() {
      long var1 = this.lm.nextAsPtr();
      Long var3 = this.gO(Arrays.asList(var1));
      if (var3 == null) {
         return false;
      } else {
         this.lm.retLong(var3);
         return true;
      }
   }

   private boolean nf() {
      ArrayList var1 = new ArrayList();
      var1.add(this.lm.nextAsPtr());
      int var2 = this.lm.nextAsInt();
      var1.add((long)var2);
      if ((var2 & this.q.wF.lm) != 0) {
         var1.add((long)this.lm.nextAsInt());
      }

      Long var3 = this.Uv(var1);
      if (var3 == null) {
         return false;
      } else {
         this.lm.retInt(var3.intValue());
         return true;
      }
   }

   private boolean gP() {
      int var1 = this.lm.nextAsInt();
      Long var2 = this.gO(Arrays.asList((long)var1));
      if (var2 == null) {
         return false;
      } else {
         this.lm.retLong(var2);
         return true;
      }
   }

   private boolean za() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      Long var4 = this.JY(Arrays.asList(var1, (long)var3));
      if (var4 == null) {
         return false;
      } else {
         this.lm.retInt(var4.intValue());
         return true;
      }
   }

   private boolean lm() {
      int var1 = this.lm.nextAsInt();
      long var2 = this.lm.nextAsPtr();
      long var4 = this.lm.nextAsSizet();
      Long var6 = this.nf(Arrays.asList((long)var1, var2, var4));
      if (var6 == null) {
         return false;
      } else {
         this.lm.retSizet(var6);
         return true;
      }
   }

   private boolean zz() {
      int var1 = this.lm.nextAsInt();
      long var2 = this.lm.nextAsPtr();
      long var4 = this.lm.nextAsSizet();
      Long var6 = this.gP(Arrays.asList((long)var1, var2, var4));
      if (var6 == null) {
         return false;
      } else {
         this.lm.retSizet(var6);
         return true;
      }
   }

   private boolean JY() {
      int var1 = this.lm.nextAsInt();
      long var2 = this.lm.nextAsPtr();
      long var4 = this.lm.nextAsSizet();
      long var6 = this.lm.nextAsOfft();
      Long var8 = this.za(Arrays.asList((long)var1, var2, var4, var6));
      if (var8 == null) {
         return false;
      } else {
         this.lm.retSizet(var8);
         return true;
      }
   }

   private boolean HF() {
      int var1 = this.lm.nextAsInt();
      long var2 = this.lm.nextAsPtr();
      long var4 = this.lm.nextAsSizet();
      long var6 = this.lm.nextAsOfft();
      Long var8 = this.lm(Arrays.asList((long)var1, var2, var4, var6));
      if (var8 == null) {
         return false;
      } else {
         this.lm.retSizet(var8);
         return true;
      }
   }

   private boolean LK() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      long var5 = this.lm.nextAsSizet();
      Long var7 = this.LK(Arrays.asList(var1, var3, var5));
      if (var7 == null) {
         return false;
      } else {
         this.lm.retSizet(var7);
         return true;
      }
   }

   private boolean io() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      Long var4 = this.xK(Arrays.asList(var1, (long)var3));
      if (var4 == null) {
         return false;
      } else {
         this.lm.retInt(var4.intValue());
         return true;
      }
   }

   private boolean qa() {
      int var1 = this.lm.nextAsInt();
      int var2 = this.lm.nextAsInt();
      Long var3 = this.Dw(Arrays.asList((long)var1, (long)var2));
      if (var3 == null) {
         return false;
      } else {
         this.lm.retInt(var3.intValue());
         return true;
      }
   }

   private boolean Hk() {
      int var1 = this.lm.nextAsInt();
      long var2 = this.lm.nextAsPtr();
      Long var4 = this.q(Arrays.asList((long)var1, var2), false);
      if (var4 == null) {
         return false;
      } else {
         this.lm.retLong(var4);
         return true;
      }
   }

   private boolean Me() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      Long var5 = this.RF(Arrays.asList(var1, var3), false);
      if (var5 == null) {
         return false;
      } else {
         this.lm.retLong(var5);
         return true;
      }
   }

   private boolean PV() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      Long var5 = this.xK(Arrays.asList(var1, var3), false);
      if (var5 == null) {
         return false;
      } else {
         this.lm.retLong(var5);
         return true;
      }
   }

   private boolean oQ() {
      long var1 = this.lm.nextAsPtr();
      int var3 = (int)this.lm.nextAsSizet();
      int var4 = (int)this.lm.nextAsSizet();
      long var5 = this.lm.nextAsPtr();
      if (var1 != 0L && var5 != 0L && var3 != 0 && var4 != 0) {
         int var7 = var4 * var3;
         byte[] var8 = new byte[var7];
         int var9 = this.q.wF.RF((int)var5, var7, var8, 0);
         int var10 = var9 / var3;
         if (var9 <= 0) {
            this.lm.retPtr(0L);
            return true;
         } else {
            VirtualMemoryUtil.writeBytes(this.Uv, var1, var8, 0, var10 * var3);
            this.lm.retSizet(var10);
            return true;
         }
      } else {
         this.lm.retPtr(0L);
         return true;
      }
   }

   private boolean xW() {
      long var1 = this.lm.nextAsPtr();
      int var3 = (int)this.lm.nextAsSizet();
      int var4 = (int)this.lm.nextAsSizet();
      long var5 = this.lm.nextAsPtr();
      if (var1 != 0L && var5 != 0L && var3 != 0 && var4 != 0) {
         int var7 = var4 * var3;
         byte[] var8 = new byte[var7];
         VirtualMemoryUtil.readBytes(this.Uv, var1, var8, 0, var7);
         int var9 = this.q.wF.q((int)var5, var7, var8, 0);
         int var10 = var9 / var3;
         this.lm.retSizet(var10);
         return true;
      } else {
         this.lm.retPtr(0L);
         return true;
      }
   }

   private boolean KT() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      long var4 = this.lm.nextAsPtr();
      if (var1 != 0L && var3 > 0 && var4 != 0L) {
         byte[] var6 = new byte[var3];
         int var7 = this.q.wF.q((int)var4, var3 - 1, 10, true, var6, 0);
         if (var7 == 0) {
            this.lm.retPtr(0L);
            return true;
         } else {
            VirtualMemoryUtil.writeBytes(this.Uv, var1, var6, 0, var7);
            VirtualMemoryUtil.writeByteSafe(this.Uv, var1 + var7, (byte)0);
            this.lm.retPtr(var1);
            return true;
         }
      } else {
         this.lm.retPtr(0L);
         return true;
      }
   }

   private boolean Gf() {
      long var1 = this.lm.nextAsPtr();
      this.q.wF.q((int)var1, 0, this.q.wF.Uv);
      return true;
   }

   private boolean Ef() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      int var4 = this.lm.nextAsInt();
      int var5 = this.q.wF.q((int)var1, var3, var4);
      this.lm.retInt(var5 >= 0 ? 0 : -1);
      return true;
   }

   private boolean cC() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.q.wF.q((int)var1, 0, this.q.wF.oW);
      this.lm.retInt(var3);
      return true;
   }

   private boolean sH() {
      return this.Ef();
   }

   private boolean CE() {
      return this.cC();
   }

   private boolean wF() {
      long var1 = this.lm.nextAsPtr();
      this.lm.nextAsInt();
      String var3;
      if (var1 == 0L) {
         var3 = "";
      } else {
         var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 256);
      }

      long var4 = this.oW;
      this.oW += 16L;
      this.gO.put(var4, var3);
      this.lm.retPtr(var4);
      return true;
   }

   private boolean If() {
      long var1 = this.lm.nextAsPtr();
      String var3 = (String)this.gO.remove(var1);
      int var4 = var3 == null ? -1 : 0;
      this.lm.retInt(var4);
      return true;
   }

   private boolean Dz() {
      this.lm.retPtr(this.nf);
      return true;
   }

   private boolean mI() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      if (var3 == 0L) {
         this.lm.retPtr(0L);
         return true;
      } else {
         String var5 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var3, 256);
         Object[] var10000 = new Object[]{var1, var5};
         long var6;
         if (var1 == 0L) {
            var6 = this.q.JY.RF(var5);
         } else {
            if (var1 == -1L) {
               this.lm.retPtr(0L);
               return true;
            }

            String var8 = (String)this.gO.get(var1);
            if (var8 == null) {
               var10000 = new Object[]{var8, var5};
               this.lm.retPtr(0L);
               return true;
            }

            cjq.CU var9 = this.q.JY.q(var8);
            if (var9 == null) {
               var10000 = new Object[]{var8, var5};
               this.lm.retPtr(0L);
               return true;
            }

            var6 = var9.q(var5);
         }

         if (var6 != 0L) {
            this.zz.put(var6, var5);
         }

         this.lm.retPtr(var6);
         return true;
      }
   }

   @Override
   public Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
      String var5 = (String)this.zz.get(var2);
      if (var5 == null) {
         return null;
      } else {
         Object[] var10000 = new Object[]{var5};
         return var1.hooksEvaluateExternal(var5, null);
      }
   }

   private boolean jq() {
      long var1 = this.lm.nextAsPtr();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 4096);
      int var4 = Conversion.stringToInt(var3);
      this.lm.retInt(var4);
      return true;
   }

   private boolean ui() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      int var5 = this.lm.nextAsInt();
      if (var3 != 0L) {
         return false;
      } else {
         long var6 = 0L;

         try {
            byte var8 = 0;
            int var9 = 0;
            long var10 = var1 - 1L;

            while (true) {
               char var12 = (char)this.Uv.readByte(++var10);
               if (var12 != ' ' && var12 != '\r' && var12 != 'n' && var12 != '\t') {
                  if (var8 == 0) {
                     var8 = 1;
                  }

                  if (var12 == '+') {
                     if (var8 != 1) {
                        break;
                     }

                     var8 = 2;
                  } else if (var12 == '-') {
                     if (var8 != 1) {
                        break;
                     }

                     var8 = 2;
                  } else {
                     if (var8 == 1) {
                        var8 = 2;
                     }

                     if (var9 == 1 && var5 == 0) {
                        if (var6 == 0L) {
                           if (var12 == 'x') {
                              var5 = 16;
                              continue;
                           }

                           var5 = 8;
                        } else {
                           var5 = 10;
                        }
                     }

                     int var13;
                     if (var12 >= '0' && var12 <= '9') {
                        var13 = var12 - '0';
                     } else {
                        var12 = Character.toUpperCase(var12);
                        if (var12 < 'A' || var12 > 'Z') {
                           break;
                        }

                        var13 = 10 + (var12 - 'A');
                     }

                     if (var5 == 0 && var13 != 0) {
                        var5 = 10;
                     }

                     if (var5 != 0 && var13 >= var5) {
                        break;
                     }

                     var6 = var6 * var5 + var13;
                     var9++;
                  }
               } else if (var8 != 0) {
                  break;
               }
            }
         } catch (MemoryException var14) {
            return false;
         }

         Object[] var10000 = new Object[]{var1, var6};
         this.lm.retLong(var6);
         return true;
      }
   }

   private boolean TX() {
      long var1 = this.lm.nextAsPtr();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 65536);
      int var4 = var3.length();
      this.lm.retInt(var4);
      return true;
   }

   private boolean Rr() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      long var5 = this.lm.nextAsLong();

      try {
         for (int var7 = 0; var7 < var5; var7++) {
            byte var8 = this.Uv.readByte(var3 + var7);
            this.Uv.writeByte(var1 + var7, (byte)var8);
            if (var8 == 0) {
               break;
            }
         }
      } catch (MemoryException var9) {
         return false;
      }

      this.lm.retPtr(var1);
      return true;
   }

   private boolean EB() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      int var4 = this.lm.nextAsInt();
      byte[] var5 = new byte[var4];
      Arrays.fill(var5, (byte)var3);
      if (!VirtualMemoryUtil.writeSafe(this.Uv, var1, var5)) {
         return false;
      } else {
         this.lm.retPtr(var1);
         return true;
      }
   }

   private boolean Xo() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      int var5 = this.lm.nextAsInt();
      byte[] var6 = new byte[var5];
      if (!VirtualMemoryUtil.readSafe(this.Uv, var3, var6)) {
         return false;
      } else if (!VirtualMemoryUtil.writeSafe(this.Uv, var1, var6)) {
         return false;
      } else {
         this.lm.retPtr(var1);
         return true;
      }
   }

   private boolean Bu() {
      return this.Xo();
   }

   private boolean IN() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      int var5 = this.lm.nextAsInt();
      byte[] var6 = new byte[var5];
      if (!VirtualMemoryUtil.readSafe(this.Uv, var1, var6)) {
         return false;
      } else {
         byte[] var7 = new byte[var5];
         if (!VirtualMemoryUtil.readSafe(this.Uv, var3, var7)) {
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
            this.lm.retInt(var8);
            return true;
         }
      }
   }

   private boolean rL() {
      int var1 = this.lm.nextAsInt();
      if (var1 >= 0 && var1 < 10485760) {
         long var2 = this.RF.heapAlloc(var1);
         this.lm.retPtr(var2);
         Object[] var10000 = new Object[]{var1, var2};
         return true;
      } else {
         return false;
      }
   }

   private boolean eJ() {
      int var1 = this.lm.nextAsInt();
      int var2 = this.lm.nextAsInt();
      int var3 = var1 * var2;
      if (var3 >= 0 && var3 < 10485760) {
         long var4 = this.RF.heapAlloc(var3);
         this.lm.retPtr(var4);
         Object[] var10000 = new Object[]{var3, var4};
         return true;
      } else {
         return false;
      }
   }

   private boolean YN() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      if (var3 == 0 && var1 != 0L) {
         this.RF.heapFree(var1);
         return true;
      } else if (var3 >= 0 && var3 < 10485760) {
         long var4;
         if (var1 == 0L) {
            var4 = this.RF.heapAlloc(var3);
         } else {
            var4 = this.RF.heapRealloc(var1, var3);
         }

         Object[] var10000 = new Object[]{var3, var4};
         this.lm.retPtr(var4);
         return true;
      } else {
         return false;
      }
   }

   private boolean Rv() {
      long var1 = this.lm.nextAsPtr();
      if (var1 != 0L) {
         this.RF.heapFree(var1);
      }

      return true;
   }

   private boolean zx() {
      long var1 = this.lm.nextAsPtr();
      int var3 = this.lm.nextAsInt();
      long var4 = this.lm.nextAsLong();
      long var6 = VirtualMemoryUtil.findByte(this.Uv, var1, var1 + var4, var3);
      if (var6 == -1L) {
         var6 = 0L;
      }

      Object[] var10000 = new Object[]{var1, var3, var4};
      this.lm.retPtr(var6);
      return true;
   }

   private boolean ZT() {
      long var1 = this.lm.nextAsLong();
      long var3 = 0L;
      if (var1 == 16L) {
         var3 = this.q.If;
      } else if (var1 == 26L) {
         var3 = this.q.Dz;
      }

      this.lm.retLong(var3);
      return true;
   }

   private boolean Ri() {
      this.lm.retInt(536879650);
      return true;
   }

   private boolean GY() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      if (var1 != 0L) {
         INativeType var5 = TypeUtil.getNonAlias(this.Dw.getType(cvm.q(new byte[]{55, 6, 29, 28, 4, 8, 11}, 2, 206)));
         VMWriter var6 = new VMWriter(this.Uv, var1, var5);
         long var7 = System.currentTimeMillis();
         var6.set(cvm.q(new byte[]{86, 2, 41, 44, 22, 6}, 1, 34), var7 / 1000L);
         var6.set(cvm.q(new byte[]{55, 25, 47, 12, 1, 12, 4}, 2, 243), var7 % 1000L * 1000L);
      }

      if (var3 != 0L) {
         INativeType var9 = TypeUtil.getNonAlias(this.Dw.getType(cvm.q(new byte[]{-48, 29, 4, 8, 31, 21, 1, 11}, 1, 164)));
         VMWriter var10 = new VMWriter(this.Uv, var3, var9);
         var10.set(cvm.q(new byte[]{55, 21, 47, 20, 27, 7, 18, 28, 17, 83, 95, 6, 90, 84}, 2, 75), 0);
         var10.set(cvm.q(new byte[]{55, 21, 47, 29, 1, 29, 19, 1, 25, 69}, 2, 219), 0);
      }

      this.lm.retInt(0);
      return true;
   }

   private boolean Wx() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsSizet();
      int var5 = this.lm.nextAsInt();
      int var6 = this.lm.nextAsInt();
      int var7 = this.lm.nextAsInt();
      long var8 = this.lm.nextAsSizet();
      Long var10 = this.io(Arrays.asList(var1, var3, (long)var5, (long)var6, (long)var7, var8));
      if (var10 == null) {
         return false;
      } else {
         this.lm.retPtr(var10);
         return true;
      }
   }

   private boolean AB() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsSizet();
      Long var5 = this.qa(Arrays.asList(var1, var3));
      if (var5 == null) {
         return false;
      } else {
         this.lm.retInt(var5.intValue());
         return true;
      }
   }

   private boolean CY() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsSizet();
      int var5 = this.lm.nextAsInt();
      Long var6 = this.Hk(Arrays.asList(var1, var3, (long)var5));
      if (var6 == null) {
         return false;
      } else {
         this.lm.retInt(var6.intValue());
         return true;
      }
   }

   private boolean WI() {
      long var1 = this.lm.nextAsPtr();
      if (var1 == 0L) {
         this.lm.retPtr(0L);
         return true;
      } else {
         String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 4096);
         if (var3 != null && var3.length() != 0 && !var3.contains("=")) {
            Object[] var10000 = new Object[]{var3};
            long var4 = (Long)this.JY.getOrDefault(var3, 0L);
            this.lm.retPtr(var4);
            return true;
         } else {
            this.lm.retPtr(0L);
            return true;
         }
      }
   }

   private boolean Tq() {
      long var1 = this.lm.nextAsPtr();
      long var3 = this.lm.nextAsPtr();
      int var5 = this.lm.nextAsInt();
      if (var1 == 0L) {
         this.lm.retInt(-1);
         return true;
      } else {
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 4096);
         if (var6 == null || var6.length() == 0 || var6.contains("=")) {
            this.lm.retInt(-1);
            return true;
         } else if (var3 == 0L) {
            this.lm.retInt(-1);
            return true;
         } else {
            String var7 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var3, 4096);
            if (var7 == null) {
               this.lm.retInt(-1);
               return true;
            } else {
               Object[] var10000 = new Object[]{var6, var7, var5};
               if (this.JY.containsKey(var6) && var5 == 0) {
                  this.lm.retInt(0);
                  return true;
               } else {
                  byte[] var8 = Strings.encodeASCII(var7);
                  long var9 = this.RF.heapAlloc(var8.length + 1);
                  if (VirtualMemoryUtil.writeSafe(this.Uv, var9, var8) && VirtualMemoryUtil.writeByteSafe(this.Uv, var9 + var8.length, (byte)0)) {
                     this.JY.put(var6, var9);
                     this.lm.retInt(0);
                     return true;
                  } else {
                     this.lm.retInt(-1);
                     return true;
                  }
               }
            }
         }
      }
   }

   private boolean Yp() {
      long var1 = this.lm.nextAsPtr();
      if (var1 == 0L) {
         this.lm.retInt(-1);
         return true;
      } else {
         String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 4096);
         if (var3 != null && var3.length() != 0 && !var3.contains("=")) {
            Long var4 = (Long)this.JY.remove(var3);
            if (var4 != null) {
               this.RF.heapFree(var4);
            }

            this.lm.retInt(0);
            return true;
         } else {
            this.lm.retInt(-1);
            return true;
         }
      }
   }

   private boolean Gu() {
      this.lm.nextAsPtr();
      this.lm.nextAsPtr();
      this.lm.nextAsPtr();
      this.lm.nextAsPtr();
      this.lm.retInt(-1);
      return true;
   }

   @Override
   public Long evaluateSyscall(EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8) {
      if (var6 == null) {
         return null;
      } else {
         Object[] var10000 = new Object[]{var6};
         Assert.a(var1 == this.RF);
         Long var10 = this.q(var6, var8);
         if (var10 != null) {
            return var10;
         } else if (this.HF) {
            JebCoreService.notifySilentExceptionToClient(
               new RuntimeException(
                  cvm.q(
                        new byte[]{34, 3, 21, 20, 7, 73, 20, 17, 7, 67, 73, 15, 69, 0, 95, 86, 77, 19, 69, 77, 66, 92, 87, 89, 73, 78, 59, 23, 5, 67, 86, 69},
                        2,
                        196
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

   private Long q(String var1, List var2) {
      switch (cvm.xK(var1)) {
         case -2069450001:
            return this.gP(var2);
         case -2055685377:
            return this.qa(var2);
         case -2000255321:
            return this.Ef(var2);
         case -1910595826:
            return this.oQ(var2);
         case -1893433791:
            return this.xK(var2);
         case -1788596673:
            return this.q(var2, false);
         case -1753512469:
            return this.xK(var2, false);
         case -1743549659:
            return this.xK(var2, true);
         case -1566396846:
            return this.xW(var2);
         case -1549952515:
            return this.oW(var2, false);
         case -1160625442:
            return this.q(var2);
         case -1147287343:
            return this.JY(var2);
         case -941801913:
            return this.za(var2);
         case -818866670:
            return this.Dw(var2, false);
         case -773446183:
            return this.zz(var2);
         case -635782928:
            return this.Hk(var2);
         case -565384625:
            return this.HF(var2);
         case -523254825:
            return this.Dw(var2, true);
         case -421950200:
            return this.PV(var2);
         case -306238177:
            return this.lm(var2);
         case -182474562:
            return this.Dw(var2);
         case 135431766:
            return this.Uv(var2, true);
         case 286502636:
            return this.nf(var2);
         case 812347249:
            return this.gO(var2);
         case 833236970:
            return this.oW(var2);
         case 1013613088:
            return this.Me(var2);
         case 1200506280:
            return this.KT(var2);
         case 1222364617:
            return this.io(var2);
         case 1229968655:
            return this.Uv(var2, false);
         case 1460207480:
            return this.oW(var2, true);
         case 1607196023:
            return this.RF(var2, false);
         case 1659165536:
            return this.Gf(var2);
         case 1675016108:
            return this.q(var2, true);
         case 1764028985:
            return this.RF(var2);
         case 1805940393:
            return this.RF(var2, true);
         case 1938485116:
            return this.Uv(var2);
         default:
            return null;
      }
   }

   private Long q(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      String var5 = this.q.wF.q();
      byte[] var6 = Strings.encodeUTF8(var5);
      int var7 = var6.length + 1;
      if (var7 > var4) {
         return 0L;
      } else {
         var6 = Arrays.copyOf(var6, var7);
         VirtualMemoryUtil.writeBytes(this.Uv, var2, var6, 0, var7);
         return var2;
      }
   }

   private Long RF(List var1) {
      return this.q(Arrays.asList((Long)var1.get(0), (long)this.q.wF.nf));
   }

   private Long xK(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      String var5 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var2, 4096);
      Object[] var10000 = new Object[]{var5, var4};
      return 0L;
   }

   private Long Dw(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      Object[] var10000 = new Object[]{var2, var3};
      return 0L;
   }

   private Long Uv(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      int var5 = 0;
      if ((var4 & this.q.wF.lm) != 0) {
         var5 = ((Long)var1.get(2)).intValue();
      }

      if (var2 == 0L) {
         return -1L;
      } else {
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var2, 4096);
         Object[] var10000 = new Object[]{var6, var4};
         if (!var6.startsWith("/")) {
            return null;
         } else {
            int var7 = this.q.wF.q(var6, var4, var5);
            return var7 < 0 ? null : (long)var7;
         }
      }
   }

   private Long oW(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      int var6 = 0;
      if ((var5 & this.q.wF.lm) != 0) {
         var6 = ((Long)var1.get(3)).intValue();
      }

      if (var3 == 0L) {
         return -1L;
      } else {
         String var7 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var3, 4096);
         Object[] var10000 = new Object[]{var2, var7, var5};
         if (!var7.startsWith("/")) {
            return null;
         } else {
            int var8 = this.q.wF.q(var7, var5, var6);
            return var8 < 0 ? null : (long)var8;
         }
      }
   }

   private Long gO(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = this.q.wF.RF(var2);
      return (long)var3;
   }

   private Long q(List var1, boolean var2) {
      int var3 = ((Long)var1.get(0)).intValue();
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.q.wF.q(var3, this.Uv, var4, var2);
      return (long)var6;
   }

   private Long RF(List var1, boolean var2) {
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(0), 4096);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.q.wF.q(var3, this.Uv, var4, var2);
      return (long)var6;
   }

   private Long xK(List var1, boolean var2) {
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(0), 4096);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.q.wF.RF(var3, this.Uv, var4, var2);
      return (long)var6;
   }

   private Long Dw(List var1, boolean var2) {
      int var3 = ((Long)var1.get(0)).intValue();
      String var4 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(1), 4096);
      long var5 = (Long)var1.get(2);
      int var7 = ((Long)var1.get(3)).intValue();
      Object[] var10000 = new Object[]{var3, var4, var5, var7};
      if (!var4.startsWith("/")) {
         return null;
      } else {
         int var8 = this.q.wF.q(var4, this.Uv, var5, var2);
         return (long)var8;
      }
   }

   private Long Uv(List var1, boolean var2) {
      int var3 = ((Long)var1.get(0)).intValue();
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.q.wF.RF(var3, this.Uv, var4, var2);
      return (long)var6;
   }

   private Long oW(List var1, boolean var2) {
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(0), 4096);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var3, var4};
      int var6 = this.q.wF.xK(var3, this.Uv, var4, var2);
      return (long)var6;
   }

   private Long nf(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var5};
      int var6 = this.q.wF.q(var2, var5, this.Uv, var3, null);
      return (long)var6;
   }

   private Long gP(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var5};
      int var6 = this.q.wF.RF(var2, var5, this.Uv, var3, null);
      return (long)var6;
   }

   private Long za(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      int var6 = (int)((Long)var1.get(3)).longValue();
      Object[] var10000 = new Object[]{var2, var3, var5};
      int var7 = this.q.wF.q(var2, var5, this.Uv, var3, var6);
      return (long)var7;
   }

   private Long lm(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      int var5 = ((Long)var1.get(2)).intValue();
      int var6 = (int)((Long)var1.get(3)).longValue();
      Object[] var10000 = new Object[]{var2, var3, var5, var6};
      int var7 = this.q.wF.RF(var2, var5, this.Uv, var3, var6);
      return (long)var7;
   }

   private Long zz(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var4};
      int var5 = this.q.wF.q(var2, var3, var4);
      return (long)var5;
   }

   private Long JY(List var1) {
      String var2 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(0), 4096);
      int var3 = ((Long)var1.get(1)).intValue();
      Object[] var10000 = new Object[]{var2, var3};
      if (!var2.startsWith("/")) {
         return null;
      } else {
         int var4 = this.q.wF.RF(var2, var3);
         return (long)var4;
      }
   }

   private Long HF(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      String var3 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(1), 4096);
      int var4 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var3, var4};
      if (!var3.startsWith("/")) {
         return null;
      } else {
         int var5 = this.q.wF.RF(var3, var4);
         return (long)var5;
      }
   }

   private Long LK(List var1) {
      String var2 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, (Long)var1.get(0), 256);
      String var3 = this.q.wF.xK(var2);
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
            long var9 = this.Uv.write(var6, var5, var4, 0);
            return var9;
         } catch (MemoryException var11) {
            return null;
         }
      }
   }

   public static int q(int var0) {
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

   public static String RF(int var0) {
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

   private Long io(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      int var6 = ((Long)var1.get(2)).intValue();
      int var7 = ((Long)var1.get(3)).intValue();
      int var8 = ((Long)var1.get(4)).intValue();
      long var9 = (Long)var1.get(5);
      if (var2 != 0L && var2 % this.Uv.getPageSize() != 0L) {
         return -1L;
      } else if (var4 > 0L && var4 < 2147483647L) {
         if (var9 >= 0L && var9 < 2147483647L && var9 % this.Uv.getPageSize() == 0L) {
            int var11 = q(var6);
            var2 = VirtualMemoryUtil.allocate(this.Uv, 5242880L, (int)var4, var11);
            if (var2 == -1L) {
               return -1L;
            } else {
               if ((var7 & 32) == 0) {
                  this.q.wF.q(var8, (int)var9, this.q.wF.Uv);
                  this.q.wF.q(var8, (int)var4, this.Uv, var2, null);
               }

               Object[] var10000 = new Object[]{var2, var2 + var4, RF(var11)};
               return var2;
            }
         } else {
            return -1L;
         }
      } else {
         return -1L;
      }
   }

   private Long qa(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      Object[] var10000 = new Object[]{var2, var4};
      if (var2 != 0L && var2 % this.Uv.getPageSize() != 0L) {
         return -1L;
      } else if (var4 <= 0L || var4 >= 2147483647L) {
         return -1L;
      } else if (!VirtualMemoryUtil.deallocate(this.Uv, var2, (int)var4)) {
         return -1L;
      } else {
         var10000 = new Object[]{var2, var2 + var4};
         return 0L;
      }
   }

   private Long Hk(List var1) {
      long var2 = (Long)var1.get(0);
      long var4 = (Long)var1.get(1);
      int var6 = ((Long)var1.get(2)).intValue();
      Object[] var10000 = new Object[]{var2, var4, var6};
      if (var2 % this.Uv.getPageSize() != 0L) {
         return -1L;
      } else if (var4 < 0L) {
         return -1L;
      } else {
         if (var2 == 5730304L) {
            var4 += 8192L;
         }

         int var7 = q(var6);

         try {
            long var8 = var2;

            for (long var10 = var2 + var4; Long.compareUnsigned(var8, var10) < 0; var8 += this.Uv.getPageSize()) {
               if (var8 == 5758976L) {
                  var10000 = new Object[0];
               }

               this.Uv.setPageProtection(var8, var7);
            }
         } catch (MemoryException var12) {
            return -1L;
         }

         var10000 = new Object[]{var2, var2 + var4, RF(var7)};
         return 0L;
      }
   }

   private Long Me(List var1) {
      long var2 = (Long)var1.get(0);
      int[] var4 = new int[2];
      int var5 = this.q.wF.q(var4);
      if (var5 == -1) {
         return -1L;
      } else {
         return !VirtualMemoryUtil.writeInts(this.Uv, var2, var4, 0, 2) ? -1L : 0L;
      }
   }

   private Long PV(List var1) {
      long var2 = (Long)var1.get(0);
      int var4 = ((Long)var1.get(1)).intValue();
      int[] var5 = new int[2];
      int var6 = this.q.wF.q(var5, var4);
      if (var6 == -1) {
         return -1L;
      } else {
         return !VirtualMemoryUtil.writeInts(this.Uv, var2, var5, 0, 2) ? -1L : 0L;
      }
   }

   private Long oQ(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = this.q.wF.xK(var2);
      return (long)var3;
   }

   private Long xW(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = this.q.wF.q(var2, var3);
      return (long)var4;
   }

   private Long KT(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      int var3 = ((Long)var1.get(1)).intValue();
      int var4 = ((Long)var1.get(2)).intValue();
      int var5 = this.q.wF.q(var2, var3, Integer.valueOf(var4));
      return (long)var5;
   }

   private Long Gf(List var1) {
      return 0L;
   }

   private Long Ef(List var1) {
      int var2 = ((Long)var1.get(0)).intValue();
      long var3 = (Long)var1.get(1);
      long var5 = (Long)var1.get(2);
      long var7 = (Long)var1.get(3);
      long var9 = (Long)var1.get(4);
      Object[] var10000 = new Object[]{var2, var3, var5, var7, var9};
      if (var2 == 4) {
         var10000 = new Object[]{var5};
         return 0L;
      } else if (var2 == 1398164801 && var3 == 0L) {
         String var17 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var9, 256);
         var10000 = new Object[]{var5, var7, var17};
         return 0L;
      } else {
         return -1L;
      }
   }
}
